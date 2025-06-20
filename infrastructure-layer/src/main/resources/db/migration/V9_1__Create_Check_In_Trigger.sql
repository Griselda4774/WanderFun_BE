-- Trigger to handle insert check-in records
DELIMITER $$
CREATE TRIGGER trg_after_checkin_insert
AFTER INSERT ON check_ins
FOR EACH ROW
BEGIN
    DECLARE point_value INT DEFAULT 0;
    DECLARE check_in_count_at_place INT DEFAULT 0;

    -- Get the check-in point value for the place
    SELECT pd.check_in_point INTO point_value
    FROM place_details pd
    WHERE pd.place_id = NEW.place_id;

    -- Increment user's check-in count and add points
    UPDATE users
    SET check_in_count = check_in_count + 1,
        point = point + point_value
    WHERE id = NEW.user_id;

    -- Check if this is the first check-in for the user at this place
    SELECT COUNT(*) INTO check_in_count_at_place
    FROM check_ins
    WHERE user_id = NEW.user_id AND place_id = NEW.place_id;

    -- If this is the first check-in at this place, increment the place_check_in_count
    IF check_in_count_at_place = 1 THEN
        UPDATE users
        SET place_check_in_count = place_check_in_count + 1
        WHERE id = NEW.user_id;
    END IF;
END$$
DELIMITER ;

-- Trigger to handle delete check-in records
DELIMITER $$
CREATE TRIGGER trg_after_checkin_delete
AFTER DELETE ON check_ins
FOR EACH ROW
BEGIN
    DECLARE point_value INT DEFAULT 0;
    DECLARE check_in_count_at_place INT DEFAULT 0;

    -- Get the check-in point value for the place
    SELECT pd.check_in_point INTO point_value
    FROM place_details pd
    WHERE pd.place_id = OLD.place_id;

    -- Decrement user's check-in count and remove points
    UPDATE users
    SET check_in_count = check_in_count - 1,
        point = point - point_value
    WHERE id = OLD.user_id AND point >= point_value AND check_in_count > 0;

    -- Check if there are still check-ins at this place for the user
    SELECT COUNT(*) INTO check_in_count_at_place
    FROM check_ins
    WHERE user_id = OLD.user_id AND place_id = OLD.place_id;

    -- If there are no more check-ins at this place, decrement the place_check_in_count
    IF check_in_count_at_place = 0 THEN
        UPDATE users
        SET place_check_in_count = place_check_in_count - 1
        WHERE id = OLD.user_id AND place_check_in_count > 0;
    END IF;
END$$
DELIMITER ;
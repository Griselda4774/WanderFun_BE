-- Trigger for INSERT check-ins
DELIMITER $$
CREATE TRIGGER trg_after_checkin_insert
AFTER INSERT ON check_ins
FOR EACH ROW
BEGIN
    -- Single atomic UPDATE to prevent race conditions
    UPDATE users u
    INNER JOIN places p ON p.id = NEW.place_id
    SET
        u.check_in_count = u.check_in_count + 1,
        u.point = u.point + p.check_in_point,
        u.place_check_in_count = CASE
            WHEN NOT EXISTS (
                SELECT 1 FROM check_ins ci
                WHERE ci.user_id = NEW.user_id
                AND ci.place_id = NEW.place_id
                AND ci.id != NEW.id
            )
            THEN u.place_check_in_count + 1
            ELSE u.place_check_in_count
        END
    WHERE u.id = NEW.user_id;
END$$
DELIMITER ;

-- Trigger for DELETE check-ins
DELIMITER $$
CREATE TRIGGER trg_after_checkin_delete
AFTER DELETE ON check_ins
FOR EACH ROW
BEGIN
    -- Single atomic UPDATE with safety guards
    UPDATE users u
    INNER JOIN places p ON p.id = OLD.place_id
    SET
        u.check_in_count = GREATEST(u.check_in_count - 1, 0),
        u.point = GREATEST(u.point - p.check_in_point, 0),
        u.place_check_in_count = CASE
            WHEN NOT EXISTS (
                SELECT 1 FROM check_ins ci
                WHERE ci.user_id = OLD.user_id
                AND ci.place_id = OLD.place_id
            )
            THEN GREATEST(u.place_check_in_count - 1, 0)
            ELSE u.place_check_in_count
        END
    WHERE u.id = OLD.user_id;
END$$
DELIMITER ;

-- Trigger for UPDATE check-ins (when place_id changes)
DELIMITER $$
CREATE TRIGGER trg_after_checkin_update
AFTER UPDATE ON check_ins
FOR EACH ROW
BEGIN
    -- Only process if place_id or user_id changed
    IF OLD.place_id != NEW.place_id OR OLD.user_id != NEW.user_id THEN

        -- Handle old place/user (subtract)
        UPDATE users u
        INNER JOIN places p ON p.id = OLD.place_id
        SET
            u.check_in_count = GREATEST(u.check_in_count - 1, 0),
            u.point = GREATEST(u.point - p.check_in_point, 0),
            u.place_check_in_count = CASE
                WHEN NOT EXISTS (
                    SELECT 1 FROM check_ins ci
                    WHERE ci.user_id = OLD.user_id
                    AND ci.place_id = OLD.place_id
                    AND ci.id != NEW.id
                )
                THEN GREATEST(u.place_check_in_count - 1, 0)
                ELSE u.place_check_in_count
            END
        WHERE u.id = OLD.user_id;

        -- Handle new place/user (add)
        UPDATE users u
        INNER JOIN places p ON p.id = NEW.place_id
        SET
            u.check_in_count = u.check_in_count + 1,
            u.point = u.point + p.check_in_point,
            u.place_check_in_count = CASE
                WHEN NOT EXISTS (
                    SELECT 1 FROM check_ins ci
                    WHERE ci.user_id = NEW.user_id
                    AND ci.place_id = NEW.place_id
                    AND ci.id != NEW.id
                )
                THEN u.place_check_in_count + 1
                ELSE u.place_check_in_count
            END
        WHERE u.id = NEW.user_id;

    END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER trg_checkin_insert_place
AFTER INSERT ON check_ins
FOR EACH ROW
BEGIN
    UPDATE places
    SET check_in_count = check_in_count + 1
    WHERE id = NEW.place_id;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER trg_checkin_delete_place
AFTER DELETE ON check_ins
FOR EACH ROW
BEGIN
    UPDATE places
    SET check_in_count = GREATEST(check_in_count - 1, 0)
    WHERE id = OLD.place_id;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER trg_checkin_update_place
AFTER UPDATE ON check_ins
FOR EACH ROW
BEGIN
    IF OLD.place_id != NEW.place_id THEN
        UPDATE places
        SET check_in_count = GREATEST(check_in_count - 1, 0)
        WHERE id = OLD.place_id;

        UPDATE places
        SET check_in_count = check_in_count + 1
        WHERE id = NEW.place_id;
    END IF;
END$$
DELIMITER ;
-- Trigger for INSERT
DELIMITER $$
CREATE TRIGGER tr_feedbacks_after_insert
AFTER INSERT ON feedbacks
FOR EACH ROW
BEGIN
    DECLARE current_count INT DEFAULT 0;
    DECLARE current_total_rating INT DEFAULT 0;

    SELECT COALESCE(feedback_count, 0), COALESCE(total_rating, 0)
    INTO current_count, current_total_rating
    FROM places
    WHERE id = NEW.place_id;

    UPDATE places p
    SET
        p.feedback_count = current_count + 1,
        p.total_rating = current_total_rating + NEW.rating,
        p.rating = ROUND((current_total_rating + NEW.rating) / (current_count + 1), 1)
    WHERE p.id = NEW.place_id;
END$$
DELIMITER ;


-- Trigger for DELETE
DELIMITER $$
CREATE TRIGGER tr_feedbacks_after_delete
AFTER DELETE ON feedbacks
FOR EACH ROW
BEGIN
    DECLARE current_count INT DEFAULT 0;
    DECLARE current_total_rating INT DEFAULT 0;

    SELECT COALESCE(feedback_count, 0), COALESCE(total_rating, 0)
    INTO current_count, current_total_rating
    FROM places
    WHERE id = OLD.place_id;

    UPDATE places p
    SET
        p.feedback_count = GREATEST(current_count - 1, 0),
        p.total_rating = GREATEST(current_total_rating - OLD.rating, 0),
        p.rating = CASE
            WHEN current_count <= 1 THEN 0
            ELSE ROUND((current_total_rating - OLD.rating) / (current_count - 1), 1)
        END
    WHERE p.id = OLD.place_id;
END$$
DELIMITER ;


-- Trigger for UPDATE
DELIMITER $$
CREATE TRIGGER tr_feedbacks_after_update
AFTER UPDATE ON feedbacks
FOR EACH ROW
BEGIN
    DECLARE old_count INT DEFAULT 0;
    DECLARE old_total_rating INT DEFAULT 0;
    DECLARE new_count INT DEFAULT 0;
    DECLARE new_total_rating INT DEFAULT 0;

    -- Only proceed if rating or place_id changed
    IF OLD.rating != NEW.rating OR OLD.place_id != NEW.place_id THEN

        IF OLD.place_id != NEW.place_id THEN
            -- Update old place
            SELECT COALESCE(feedback_count, 0), COALESCE(total_rating, 0)
            INTO old_count, old_total_rating
            FROM places
            WHERE id = OLD.place_id;

            UPDATE places p
            SET
                p.feedback_count = GREATEST(old_count - 1, 0),
                p.total_rating = GREATEST(old_total_rating - OLD.rating, 0),
                p.rating = CASE
                    WHEN old_count <= 1 THEN 0
                    ELSE ROUND((old_total_rating - OLD.rating) / (old_count - 1), 1)
                END
            WHERE p.id = OLD.place_id;

            -- Update new place
            SELECT COALESCE(feedback_count, 0), COALESCE(total_rating, 0)
            INTO new_count, new_total_rating
            FROM places
            WHERE id = NEW.place_id;

            UPDATE places p
            SET
                p.feedback_count = new_count + 1,
                p.total_rating = new_total_rating + NEW.rating,
                p.rating = ROUND((new_total_rating + NEW.rating) / (new_count + 1), 1)
            WHERE p.id = NEW.place_id;

        ELSE
            -- Only rating changed - same place
            SELECT COALESCE(feedback_count, 0), COALESCE(total_rating, 0)
            INTO old_count, old_total_rating
            FROM places
            WHERE id = NEW.place_id;

            UPDATE places p
            SET
                p.total_rating = old_total_rating - OLD.rating + NEW.rating,
                p.rating = ROUND((old_total_rating - OLD.rating + NEW.rating) / old_count, 1)
            WHERE p.id = NEW.place_id;
        END IF;
    END IF;
END$$
DELIMITER ;

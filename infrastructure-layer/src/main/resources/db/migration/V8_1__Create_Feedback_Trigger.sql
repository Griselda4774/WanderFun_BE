-- Trigger for INSERT
DELIMITER $$
CREATE TRIGGER tr_feedbacks_after_insert
AFTER INSERT ON feedbacks
FOR EACH ROW
BEGIN
    DECLARE current_count INT DEFAULT 0;
    DECLARE current_rating DECIMAL(3,2) DEFAULT 0;

    SELECT COALESCE(feedback_count, 0), COALESCE(rating, 0)
    INTO current_count, current_rating
    FROM places
    WHERE id = NEW.place_id;

    UPDATE places p
    SET
        p.feedback_count = current_count + 1,
        p.rating = CASE
            WHEN current_count = 0 THEN NEW.rating
            ELSE (current_rating * current_count + NEW.rating) / (current_count + 1)
        END
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
    DECLARE current_rating DECIMAL(3,2) DEFAULT 0;

    SELECT COALESCE(feedback_count, 0), COALESCE(rating, 0)
    INTO current_count, current_rating
    FROM places
    WHERE id = OLD.place_id;

    UPDATE places p
    SET
        p.feedback_count = GREATEST(current_count - 1, 0),
        p.rating = CASE
            WHEN current_count <= 1 THEN 0
            ELSE (current_rating * current_count - COALESCE(OLD.rating, 0)) / (current_count - 1)
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
    DECLARE old_rating DECIMAL(3,2) DEFAULT 0;
    DECLARE new_count INT DEFAULT 0;
    DECLARE new_rating DECIMAL(3,2) DEFAULT 0;

    -- Only proceed if rating or place_id changed
    IF OLD.rating != NEW.rating OR OLD.place_id != NEW.place_id THEN

        IF OLD.place_id != NEW.place_id THEN
            -- Get old place values
            SELECT COALESCE(feedback_count, 0), COALESCE(rating, 0)
            INTO old_count, old_rating
            FROM places
            WHERE id = OLD.place_id;

            -- Remove from old place
            UPDATE places p
            SET
                p.feedback_count = GREATEST(old_count - 1, 0),
                p.rating = CASE
                    WHEN old_count <= 1 THEN 0
                    ELSE (old_rating * old_count - COALESCE(OLD.rating, 0)) / (old_count - 1)
                END
            WHERE p.id = OLD.place_id;

            -- Get new place values
            SELECT COALESCE(feedback_count, 0), COALESCE(rating, 0)
            INTO new_count, new_rating
            FROM places
            WHERE id = NEW.place_id;

            -- Add to new place
            UPDATE places p
            SET
                p.feedback_count = new_count + 1,
                p.rating = CASE
                    WHEN new_count = 0 THEN NEW.rating
                    ELSE (new_rating * new_count + NEW.rating) / (new_count + 1)
                END
            WHERE p.id = NEW.place_id;
        ELSE
            -- Only rating changed - same place
            SELECT COALESCE(feedback_count, 0), COALESCE(rating, 0)
            INTO old_count, old_rating
            FROM places
            WHERE id = NEW.place_id;

            UPDATE places p
            SET
                p.rating = CASE
                    WHEN old_count = 0 THEN NEW.rating
                    ELSE (old_rating * old_count - COALESCE(OLD.rating, 0) + COALESCE(NEW.rating, 0)) / old_count
                END
            WHERE p.id = NEW.place_id;
        END IF;
    END IF;
END$$
DELIMITER ;
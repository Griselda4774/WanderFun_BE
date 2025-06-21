-- Trigger when inserting
DELIMITER $$
CREATE TRIGGER tr_feedbacks_after_insert
AFTER INSERT ON feedbacks
FOR EACH ROW
BEGIN
    UPDATE places
    SET
        feedback_count = (
            SELECT COUNT (*)
            FROM feedbacks
            WHERE place_id = NEW.place_id
        ),
        rating = (
            SELECT COALESCE(AVG(rating), 0)
            FROM feedbacks
            WHERE place_id = NEW.place_id
        )
    WHERE id = NEW.place_id;
END$$
DELIMITER ;

-- Trigger when deleting
DELIMITER $$
CREATE TRIGGER tr_feedbacks_after_delete
AFTER DELETE ON feedbacks
FOR EACH ROW
BEGIN
    UPDATE places
    SET
        feedback_count = (
            SELECT COUNT (*)
            FROM feedbacks
            WHERE place_id = NEW.place_id
        ),
        rating = (
            SELECT COALESCE(AVG(rating), 0)
            FROM feedbacks
            WHERE place_id = OLD.place_id
        )
    WHERE id = OLD.place_id;
END$$
DELIMITER ;

-- Trigger when updating (in case rating is modified)
DELIMITER $$
CREATE TRIGGER tr_feedbacks_after_update
AFTER UPDATE ON feedbacks
FOR EACH ROW
BEGIN
    -- Only recalculate if the rating or place_id changed
    IF OLD.rating != NEW.rating OR OLD.place_id != NEW.place_id THEN
        -- Update the old place if place_id changed
        IF OLD.place_id != NEW.place_id THEN
            UPDATE places
            SET
                feedback_count = (
                    SELECT COUNT (*)
                    FROM feedbacks
                    WHERE place_id = OLD.place_id
                ),
                rating = (
                    SELECT COALESCE(AVG(rating), 0)
                    FROM feedbacks
                    WHERE place_id = OLD.place_id
                )
            WHERE id = OLD.place_id;

            -- Update the new place
            UPDATE places
            SET
                feedback_count = (
                    SELECT COUNT (*)
                    FROM feedbacks
                    WHERE place_id = NEW.place_id
                ),
                rating = (
                    SELECT COALESCE(AVG(rating), 0)
                    FROM feedbacks
                    WHERE place_id = NEW.place_id
                )
            WHERE id = NEW.place_id;
        ELSE
            -- Just update the rating if only rating changed
            UPDATE places
            SET rating = (
                SELECT COALESCE(AVG(rating), 0)
                FROM feedbacks
                WHERE place_id = NEW.place_id
            )
            WHERE id = NEW.place_id;
        END IF;
    END IF;
END$$
DELIMITER ;
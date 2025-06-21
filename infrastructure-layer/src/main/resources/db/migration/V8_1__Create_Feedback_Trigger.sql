-- Optimized trigger for INSERT
DELIMITER $$
CREATE TRIGGER tr_feedbacks_after_insert
AFTER INSERT ON feedbacks
FOR EACH ROW
BEGIN
    UPDATE places p
    SET
        p.feedback_count = COALESCE(p.feedback_count, 0) + 1,
        p.rating = CASE
            WHEN COALESCE(p.feedback_count, 0) = 0 THEN NEW.rating
            ELSE (COALESCE(p.rating, 0) * COALESCE(p.feedback_count, 0) + NEW.rating) / (COALESCE(p.feedback_count, 0) + 1)
        END
    WHERE p.id = NEW.place_id;
END$$
DELIMITER ;

-- Optimized trigger for DELETE
DELIMITER $$
CREATE TRIGGER tr_feedbacks_after_delete
AFTER DELETE ON feedbacks
FOR EACH ROW
BEGIN
    UPDATE places p
    SET
        p.feedback_count = GREATEST(COALESCE(p.feedback_count, 1) - 1, 0),
        p.rating = CASE
            WHEN COALESCE(p.feedback_count, 1) <= 1 THEN 0
            ELSE (COALESCE(p.rating, 0) * COALESCE(p.feedback_count, 1) - COALESCE(OLD.rating, 0)) / (COALESCE(p.feedback_count, 1) - 1)
        END
    WHERE p.id = OLD.place_id;
END$$
DELIMITER ;

-- Optimized trigger for UPDATE
DELIMITER $$
CREATE TRIGGER tr_feedbacks_after_update
AFTER UPDATE ON feedbacks
FOR EACH ROW
BEGIN
    -- Only proceed if rating or place_id changed
    IF OLD.rating != NEW.rating OR OLD.place_id != NEW.place_id THEN

        IF OLD.place_id != NEW.place_id THEN
            -- Remove from old place
            UPDATE places p
            SET
                p.feedback_count = GREATEST(COALESCE(p.feedback_count, 1) - 1, 0),
                p.rating = CASE
                    WHEN COALESCE(p.feedback_count, 1) <= 1 THEN 0
                    ELSE (COALESCE(p.rating, 0) * COALESCE(p.feedback_count, 1) - COALESCE(OLD.rating, 0)) / (COALESCE(p.feedback_count, 1) - 1)
                END
            WHERE p.id = OLD.place_id;

            -- Add to new place
            UPDATE places p
            SET
                p.feedback_count = COALESCE(p.feedback_count, 0) + 1,
                p.rating = CASE
                    WHEN COALESCE(p.feedback_count, 0) = 0 OR p.rating IS NULL THEN NEW.rating
                    WHEN NEW.rating IS NULL THEN COALESCE(p.rating, 0)
                    ELSE (COALESCE(p.rating, 0) * COALESCE(p.feedback_count, 0) + NEW.rating) / (COALESCE(p.feedback_count, 0) + 1)
                END
            WHERE p.id = NEW.place_id;
        ELSE
            -- Only rating changed
            UPDATE places p
            SET
                p.rating = CASE
                    WHEN COALESCE(p.feedback_count, 0) = 0 THEN NEW.rating
                    WHEN OLD.rating IS NULL AND NEW.rating IS NOT NULL THEN
                        (COALESCE(p.rating, 0) * COALESCE(p.feedback_count, 0) + NEW.rating) / COALESCE(p.feedback_count, 1)
                    WHEN OLD.rating IS NOT NULL AND NEW.rating IS NULL THEN
                        CASE
                            WHEN COALESCE(p.feedback_count, 1) <= 1 THEN 0
                            ELSE (COALESCE(p.rating, 0) * COALESCE(p.feedback_count, 1) - OLD.rating) / (COALESCE(p.feedback_count, 1) - 1)
                        END
                    ELSE (COALESCE(p.rating, 0) * COALESCE(p.feedback_count, 1) - COALESCE(OLD.rating, 0) + COALESCE(NEW.rating, 0)) / COALESCE(p.feedback_count, 1)
                END
            WHERE p.id = NEW.place_id;
        END IF;
    END IF;
END$$
DELIMITER ;
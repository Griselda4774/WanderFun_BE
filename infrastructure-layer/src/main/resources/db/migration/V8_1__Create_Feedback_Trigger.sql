-- Trigger when inserting
DELIMITER $$
CREATE TRIGGER tr_feedbacks_after_insert
AFTER INSERT ON feedbacks
FOR EACH ROW
BEGIN
    UPDATE places
    SET feedback_count = feedback_count + 1
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
    SET feedback_count = feedback_count - 1
    WHERE id = OLD.place_id
    AND feedback_count > 0;
END$$
DELIMITER ;
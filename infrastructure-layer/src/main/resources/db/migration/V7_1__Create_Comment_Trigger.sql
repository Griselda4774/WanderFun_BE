-- Trigger when inserting
DELIMITER $$
CREATE TRIGGER tr_comments_after_insert
AFTER INSERT ON comments
FOR EACH ROW
BEGIN
    UPDATE posts
    SET comment_count = comment_count + 1
    WHERE id = NEW.post_id;
END$$
DELIMITER ;

-- Trigger when deleting
DELIMITER $$
CREATE TRIGGER tr_comments_after_delete
AFTER DELETE ON comments
FOR EACH ROW
BEGIN
    UPDATE posts
    SET comment_count = comment_count - 1
    WHERE id = OLD.post_id
    AND comment_count > 0;
END$$
DELIMITER ;
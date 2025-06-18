-- Trigger when inserting like
DELIMITER $$
CREATE TRIGGER tr_likes_after_insert
AFTER INSERT ON likes
FOR EACH ROW
BEGIN
    -- If target_type is 'post', increase like_count of post
    IF NEW.target_type = 'post' THEN
        UPDATE posts
        SET like_count = like_count + 1
        WHERE id = NEW.target_id;

    -- If target_type is 'comment', increase like_count of comment
    ELSEIF NEW.target_type = 'comment' THEN
        UPDATE comments
        SET like_count = like_count + 1
        WHERE id = NEW.target_id;
    END IF;
END$$
DELIMITER ;

-- Trigger when deleting like
DELIMITER $$
CREATE TRIGGER tr_likes_after_delete
AFTER DELETE ON likes
FOR EACH ROW
BEGIN
    -- If target_type is 'post', decrease like_count of post
    IF OLD.target_type = 'post' THEN
        UPDATE posts
        SET like_count = like_count - 1
        WHERE id = OLD.target_id
        AND like_count > 0;

    -- If target_type is 'comment', decrease like_count of comment
    ELSEIF OLD.target_type = 'comment' THEN
        UPDATE comments
        SET like_count = like_count - 1
        WHERE id = OLD.target_id
        AND like_count > 0;
    END IF;
END$$
DELIMITER ;
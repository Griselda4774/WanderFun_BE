CREATE TABLE favorite_places (
    id BIGINT NOT NULL AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    place_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY unique_user_place (user_id, place_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (place_id) REFERENCES places(id)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_unicode_ci;
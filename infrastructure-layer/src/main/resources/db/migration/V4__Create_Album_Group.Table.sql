CREATE TABLE albums (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    cover_image_id BIGINT,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    place_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,

    CONSTRAINT fk_album_cover_image FOREIGN KEY (cover_image_id) REFERENCES images(id),
    CONSTRAINT fk_album_place FOREIGN KEY (place_id) REFERENCES places(id)
)  ENGINE=InnoDB
   DEFAULT CHARSET=utf8mb4
   COLLATE=utf8mb4_unicode_ci;

CREATE TABLE album_images (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    image_url VARCHAR(255) NOT NULL,
    image_public_id VARCHAR(255) NOT NULL,
    album_id BIGINT NOT NULL,

    CONSTRAINT fk_album_image_album FOREIGN KEY (album_id) REFERENCES albums(id) ON DELETE CASCADE
);

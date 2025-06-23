CREATE TABLE place_categories (
    id INTEGER NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    name_en VARCHAR(255) NOT NULL,
    icon_image_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (icon_image_id) REFERENCES images(id)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_unicode_ci;

CREATE TABLE places (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    longitude DOUBLE NOT NULL,
    latitude DOUBLE NOT NULL,
    address_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    category_id INTEGER NOT NULL,
    cover_image_id BIGINT,
    rating FLOAT DEFAULT 0,
    total_rating INT DEFAULT 0,
    feedback_count INT DEFAULT 0,
    check_in_count INT DEFAULT 0,
    FOREIGN KEY (category_id) REFERENCES place_categories(id),
    FOREIGN KEY (address_id) REFERENCES addresses(id),
    FOREIGN KEY (cover_image_id) REFERENCES images(id)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_unicode_ci;

CREATE TABLE place_details (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    place_id BIGINT NOT NULL,
    description TEXT,
    check_in_point INTEGER DEFAULT 0,
    check_in_range_meter FLOAT DEFAULT 100,
    time_open TIME,
    time_close TIME,
    is_closed BIT DEFAULT 0,
    is_open_all_day BIT DEFAULT 0,
    best_time_to_visit VARCHAR(255),
    price_range_top INT,
    price_range_bottom INT,
    is_verified BIT DEFAULT 0,
    alternative_name VARCHAR(255),
    operator VARCHAR(255),
    url VARCHAR(1024),
    FOREIGN KEY (place_id) REFERENCES places(id)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_unicode_ci;

CREATE TABLE sections (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    content TEXT,
    image_id BIGINT,
    place_detail_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (place_detail_id) REFERENCES place_details(id),
    FOREIGN KEY (image_id) REFERENCES images(id)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_unicode_ci;
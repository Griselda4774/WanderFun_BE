CREATE TABLE accounts (
    id BIGINT NOT NULL AUTO_INCREMENT,
    role VARCHAR(20) NOT NULL,
    email VARCHAR(320) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    is_verified BIT DEFAULT 0,
    is_active BIT DEFAULT 1,
    is_deleted BIT DEFAULT 0,
    create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE refresh_tokens (
    id BIGINT NOT NULL AUTO_INCREMENT,
    account_id BIGINT NOT NULL,
    token VARCHAR(512) NOT NULL UNIQUE,
    PRIMARY KEY (id),
    FOREIGN KEY (account_id) REFERENCES accounts(id)
);


CREATE TABLE images (
    id BIGINT NOT NULL AUTO_INCREMENT,
    image_url VARCHAR(1024) NOT NULL,
    image_public_id VARCHAR(1024),
    target_type VARCHAR(20) NOT NULL,
    target_id BIGINT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE place_categories (
    id INTEGER NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    name_en VARCHAR(255) NOT NULL,
    icon_image_id BIGINT,
    PRIMARY KEY (id)
);



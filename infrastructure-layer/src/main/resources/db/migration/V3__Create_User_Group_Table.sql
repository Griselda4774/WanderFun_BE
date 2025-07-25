CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    is_created_profile BIT DEFAULT 0,
    avatar_image_id BIGINT,
    date_of_birth DATE,
    gender VARCHAR(20),
    phone_number VARCHAR(20),
    address_id BIGINT,
    account_id BIGINT NOT NULL,
    point INT DEFAULT 0,
    check_in_count INT DEFAULT 0,
    place_check_in_count INT DEFAULT 0,
    CONSTRAINT fk_users_account FOREIGN KEY (account_id) REFERENCES accounts(id)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_general_ci;
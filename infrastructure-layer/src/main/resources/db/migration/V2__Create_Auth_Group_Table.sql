CREATE TABLE accounts (
    id BIGINT NOT NULL AUTO_INCREMENT,
    role VARCHAR(20) NOT NULL,
    email VARCHAR(320) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    is_verified BIT DEFAULT 0,
    is_active BIT DEFAULT 1,
    is_deleted BIT DEFAULT 0,
    create_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_general_ci;

CREATE TABLE refresh_tokens (
    id BIGINT NOT NULL AUTO_INCREMENT,
    account_id BIGINT NOT NULL,
    token VARCHAR(512) NOT NULL UNIQUE,
    PRIMARY KEY (id),
    FOREIGN KEY (account_id) REFERENCES accounts(id)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_general_ci;
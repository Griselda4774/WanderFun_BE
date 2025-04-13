CREATE TABLE `accounts` (
                            `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                            `role` VARCHAR(20) NOT NULL,
                            `email` VARCHAR(320) NOT NULL UNIQUE,
                            `password` VARCHAR(255) NOT NULL,
                            `is_verified` BIT(1) DEFAULT b'0',
                            `is_active` BIT(1) DEFAULT b'1',
                            `is_deleted` BIT(1) DEFAULT b'0',
                            `create_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

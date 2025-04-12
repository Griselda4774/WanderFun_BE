CREATE TABLE `administrative_regions` (
                                          `id` INTEGER NOT NULL PRIMARY KEY,
                                          `name` VARCHAR(255) NOT NULL,
                                          `name_en` VARCHAR(255) NOT NULL,
                                          `code_name` VARCHAR(255) NULL,
                                          `code_name_en` VARCHAR(255) NOT NULL
);

CREATE TABLE `administrative_units` (
                                        `id` INTEGER PRIMARY KEY ,
                                        `full_name` varchar(255),
                                        `full_name_en` varchar(255),
                                        `short_name` varchar(255),
                                        `short_name_en` varchar(255),
                                        `code_name` varchar(255),
                                        `code_name_en` varchar(255)
);

CREATE TABLE `provinces` (
                             `code` varchar(255) PRIMARY KEY,
                             `name` varchar(255),
                             `name_en` varchar(255),
                             `full_name` varchar(255),
                             `full_name_en` varchar(255),
                             `code_name` varchar(255),
                             `region_id` bigint,
                             `unit_id` bigint
);

CREATE TABLE `province_details` (
                                    `province_code` varchar(255),
                                    `longitude` double,
                                    `latitude` double,
                                    `area_km2` double,
                                    `description` text,
                                    `best_time_to_visit` varchar(255),
                                    `festivals` text,
                                    `local_transport` text,
                                    `local_food` text,
                                    `how_to_get_there` text
);

CREATE TABLE `districts` (
                             `code` varchar(255) PRIMARY KEY,
                             `name` varchar(255),
                             `name_en` varchar(255),
                             `full_name` varchar(255),
                             `full_name_en` varchar(255),
                             `code_name` varchar(255),
                             `province_code` varchar(255),
                             `unit_id` bigint
);

CREATE TABLE `wards` (
                         `code` varchar(255) PRIMARY KEY,
                         `name` varchar(255),
                         `name_en` varchar(255),
                         `full_name` varchar(255),
                         `full_name_en` varchar(255),
                         `code_name` varchar(255),
                         `district_code` varchar(255),
                         `unit_id` bigint
);

CREATE TABLE `addresses` (
                             `id` bigint PRIMARY KEY,
                             `province_code` varchar(255),
                             `district_code` varchar(255),
                             `ward_code` varchar(255),
                             `street` varchar(255),
                             `target_type` varchar(255),
                             `target_id` bigint
);

CREATE TABLE `images` (
                          `id` bigint PRIMARY KEY,
                          `image_url` varchar(255),
                          `image_public_id` varchar(255),
                          `target_type` varchar(255),
                          `target_id` bigint
);

CREATE TABLE `albums` (
                          `id` bigint PRIMARY KEY,
                          `name` varchar(255),
                          `description` text,
                          `updated_at` timestamp,
                          `place_id` bigint,
                          `user_id` bigint
);

CREATE TABLE `users` (
                         `id` bigint PRIMARY KEY,
                         `role` varchar(255),
                         `email` varchar(255),
                         `password` varchar(255),
                         `first_name` varchar(255),
                         `last_name` varchar(255),
                         `is_verified` boolean,
                         `is_created_profile` boolean,
                         `avatar_image_id` bigint,
                         `date_of_birth` date,
                         `gender` varchar(255),
                         `phone_number` varchar(255),
                         `address_id` bigint
);

CREATE TABLE `favourite_places` (
                                    `id` bigint PRIMARY KEY,
                                    `place_id` bigint,
                                    `user_id` bigint
);

CREATE TABLE `places` (
                          `id` bigint PRIMARY KEY,
                          `longitude` double,
                          `latitude` double,
                          `address_id` bigint,
                          `name` varchar(255),
                          `category_id` bigint,
                          `cover_image_id` bigint,
                          `place_detail_id` bigint
);

CREATE TABLE `place_categories` (
                                    `id` tinyint,
                                    `name` varchar(255),
                                    `name_en` varchar(255),
                                    `icon_image_id` bigint
);

CREATE TABLE `place_details` (
                                 `id` bigint PRIMARY KEY,
                                 `description` text,
                                 `check_in_point` int,
                                 `check_in_range` float,
                                 `category` varchar(255),
                                 `time_open` time,
                                 `time_close` time,
                                 `is_open` boolean,
                                 `best_time_to_visit` varchar(255),
                                 `price_range_top` int,
                                 `price_range_bottom` int,
                                 `is_verified` boolean,
                                 `alternative_name` varchar(255),
                                 `operator` varchar(255),
                                 `link` varchar(255)
);

CREATE TABLE `sections` (
                            `id` bigint PRIMARY KEY,
                            `title` varchar(255),
                            `content` text,
                            `image_id` bigint,
                            `place_detail_id` bigint
);

CREATE TABLE `feedbacks` (
                             `id` bigint PRIMARY KEY,
                             `place_id` bigint,
                             `user_id` bigint,
                             `rating` float,
                             `comment` text,
                             `create_at` timestamp
);

CREATE TABLE `trips` (
                         `id` bigint PRIMARY KEY,
                         `name` varchar(255),
                         `start_time` date,
                         `end_time` date,
                         `author_id` bigint
);

CREATE TABLE `trip_places` (
                               `id` bigint PRIMARY KEY,
                               `place_id` bigint,
                               `trip_id` bigint,
                               `start_time` date,
                               `end_time` date
);

CREATE TABLE `refresh_tokens` (
                                  `id` bigint PRIMARY KEY,
                                  `token` varchar(255),
                                  `user_id` bigint
);

CREATE TABLE `posts` (
                         `id` bigint PRIMARY KEY,
                         `user_id` bigint,
                         `content` text,
                         `create_at` timestamp,
                         `is_trip_share` boolean,
                         `trip_id` bigint
);

CREATE TABLE `comments` (
                            `id` bigint PRIMARY KEY,
                            `user_id` bigint,
                            `content` text,
                            `create_at` timestamp,
                            `post_id` bigint,
                            `parent_id` bigint
);

CREATE TABLE `likes` (
                         `id` bigint PRIMARY KEY,
                         `user_id` bigint,
                         `target_id` bigint,
                         `target_type` varchar(255)
);

CREATE TABLE `check_ins` (
                             `id` bigint PRIMARY KEY,
                             `place_id` bigint,
                             `user_id` bigint,
                             `check_in_time` timestamp
);

CREATE UNIQUE INDEX `likes_index_0` ON `likes` (`user_id`, `target_id`, `target_type`);

ALTER TABLE `provinces` ADD FOREIGN KEY (`region_id`) REFERENCES `administrative_regions` (`id`);

ALTER TABLE `provinces` ADD FOREIGN KEY (`unit_id`) REFERENCES `administrative_units` (`id`);

ALTER TABLE `province_details` ADD FOREIGN KEY (`province_code`) REFERENCES `provinces` (`code`);

ALTER TABLE `districts` ADD FOREIGN KEY (`province_code`) REFERENCES `provinces` (`code`);

ALTER TABLE `districts` ADD FOREIGN KEY (`unit_id`) REFERENCES `administrative_units` (`id`);

ALTER TABLE `wards` ADD FOREIGN KEY (`district_code`) REFERENCES `districts` (`code`);

ALTER TABLE `wards` ADD FOREIGN KEY (`unit_id`) REFERENCES `administrative_units` (`id`);

ALTER TABLE `addresses` ADD FOREIGN KEY (`province_code`) REFERENCES `provinces` (`code`);

ALTER TABLE `addresses` ADD FOREIGN KEY (`district_code`) REFERENCES `districts` (`code`);

ALTER TABLE `addresses` ADD FOREIGN KEY (`ward_code`) REFERENCES `wards` (`code`);

ALTER TABLE `albums` ADD FOREIGN KEY (`place_id`) REFERENCES `places` (`id`);

ALTER TABLE `albums` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `favourite_places` ADD FOREIGN KEY (`place_id`) REFERENCES `places` (`id`);

ALTER TABLE `favourite_places` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `places` ADD FOREIGN KEY (`category_id`) REFERENCES `place_categories` (`id`);

ALTER TABLE `places` ADD FOREIGN KEY (`place_detail_id`) REFERENCES `place_details` (`id`);

ALTER TABLE `sections` ADD FOREIGN KEY (`place_detail_id`) REFERENCES `place_details` (`id`);

ALTER TABLE `feedbacks` ADD FOREIGN KEY (`place_id`) REFERENCES `places` (`id`);

ALTER TABLE `feedbacks` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `trips` ADD FOREIGN KEY (`author_id`) REFERENCES `users` (`id`);

ALTER TABLE `trip_places` ADD FOREIGN KEY (`place_id`) REFERENCES `places` (`id`);

ALTER TABLE `trip_places` ADD FOREIGN KEY (`trip_id`) REFERENCES `trips` (`id`);

ALTER TABLE `refresh_tokens` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `posts` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `posts` ADD FOREIGN KEY (`trip_id`) REFERENCES `trips` (`id`);

ALTER TABLE `comments` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `comments` ADD FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`);

ALTER TABLE `likes` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `check_ins` ADD FOREIGN KEY (`place_id`) REFERENCES `places` (`id`);

ALTER TABLE `check_ins` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

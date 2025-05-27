-- Auths Group --

-- ----------------------------------

-- Users Group --

-- ----------------------------------

-- Images Group --
CREATE TABLE images (
    id BIGINT NOT NULL AUTO_INCREMENT,
    image_url VARCHAR(1024) NOT NULL,
    image_public_id VARCHAR(1024),
    PRIMARY KEY (id)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_unicode_ci;
-- ----------------------------------

-- Addresses Group --
CREATE TABLE administrative_regions (
    id INTEGER NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    name_en VARCHAR(255) NOT NULL,
    code_name VARCHAR(255),
    code_name_en VARCHAR(255)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_unicode_ci;

CREATE TABLE administrative_units (
    id INTEGER NOT NULL PRIMARY KEY,
    full_name VARCHAR(255),
    full_name_en VARCHAR(255),
    short_name VARCHAR(255),
    short_name_en VARCHAR(255),
    code_name VARCHAR(255),
    code_name_en VARCHAR(255)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_unicode_ci;

CREATE TABLE provinces (
    code VARCHAR(20) NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    name_en VARCHAR(255),
    full_name VARCHAR(255) NOT NULL,
    full_name_en VARCHAR(255),
    code_name VARCHAR(255),
    administrative_region_id INT,
    administrative_unit_id INT,
    FOREIGN KEY (administrative_region_id) REFERENCES administrative_regions(id),
    FOREIGN KEY (administrative_unit_id) REFERENCES administrative_units(id)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_unicode_ci;

CREATE TABLE province_details (
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    province_code VARCHAR(20) NOT NULL UNIQUE,
    longitude DOUBLE NOT NULL,
    latitude DOUBLE NOT NULL,
    area_km2 DOUBLE,
    description TEXT,
    best_time_to_visit VARCHAR(255),
    festivals TEXT,
    local_transport TEXT,
    local_food TEXT,
    how_to_get_there TEXT,
    cover_image_id BIGINT,
    FOREIGN KEY (province_code) REFERENCES provinces(code),
    FOREIGN KEY (cover_image_id) REFERENCES images(id)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_unicode_ci;

CREATE TABLE districts (
    code VARCHAR(20) NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    name_en VARCHAR(255),
    full_name VARCHAR(255) NOT NULL,
    full_name_en VARCHAR(255),
    code_name VARCHAR(255),
    province_code VARCHAR(20) NOT NULL,
    administrative_unit_id INTEGER,
    FOREIGN KEY (province_code) REFERENCES provinces(code),
    FOREIGN KEY (administrative_unit_id) REFERENCES administrative_units(id)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_unicode_ci;

CREATE TABLE wards (
    code VARCHAR(20) NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    name_en VARCHAR(255),
    full_name VARCHAR(255) NOT NULL,
    full_name_en VARCHAR(255),
    code_name VARCHAR(255),
    district_code VARCHAR(20) NOT NULL,
    administrative_unit_id INTEGER,
    FOREIGN KEY (district_code) REFERENCES districts(code),
    FOREIGN KEY (administrative_unit_id) REFERENCES administrative_units(id)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_unicode_ci;

CREATE TABLE addresses (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    province_code VARCHAR(20) NOT NULL,
    district_code VARCHAR(20) NOT NULL,
    ward_code VARCHAR(20),
    street VARCHAR(255),
    CONSTRAINT fk_addresses_province FOREIGN KEY (province_code) REFERENCES provinces(code),
    CONSTRAINT fk_addresses_district FOREIGN KEY (district_code) REFERENCES districts(code),
    CONSTRAINT fk_addresses_ward FOREIGN KEY (ward_code) REFERENCES wards(code)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_unicode_ci;
-- ----------------------------------

-- Places Group --

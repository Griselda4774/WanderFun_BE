CREATE TABLE trips (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    start_time DATE NOT NULL,
    end_time DATE NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT fk_trips_user FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE trip_places (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    place_id BIGINT NOT NULL,
    trip_id BIGINT NOT NULL,
    start_time DATE NOT NULL,
    end_time DATE NOT NULL,
    place_notes TEXT,
    CONSTRAINT fk_trip_places_place FOREIGN KEY (place_id) REFERENCES places(id),
    CONSTRAINT fk_trip_places_trip FOREIGN KEY (trip_id) REFERENCES trips(id)
);

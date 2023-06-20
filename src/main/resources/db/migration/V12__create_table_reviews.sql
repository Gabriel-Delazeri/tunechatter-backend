CREATE TABLE reviews (
                         id BINARY(16) PRIMARY KEY,
                         album_id BINARY(16),
                         user_id BINARY(16),
                         comment VARCHAR(255),
                         likes INT DEFAULT 0,
                         rating float,
                         posted_at TIMESTAMP,
                         FOREIGN KEY (album_id) REFERENCES album (id),
                         FOREIGN KEY (user_id) REFERENCES users (id)
);
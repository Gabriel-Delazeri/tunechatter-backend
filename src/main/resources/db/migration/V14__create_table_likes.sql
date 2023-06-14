CREATE TABLE likes (
                       id BINARY(16) PRIMARY KEY,
                       review_id BINARY(16),
                       user_id BINARY(16),
                       FOREIGN KEY (review_id) REFERENCES reviews (id),
                       FOREIGN KEY (user_id) REFERENCES users (id)
);
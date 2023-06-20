CREATE TABLE IF NOT EXISTS `users` (
    `id` binary(16) NOT NULL,
    `email` varchar(255) DEFAULT NULL,
    `user_name` varchar(255) DEFAULT NULL,
    `first_name` varchar(255) DEFAULT NULL,
    `last_name` varchar(255) DEFAULT NULL,
    `password` varchar(255) DEFAULT NULL,
    `account_non_expired` bit(1) DEFAULT NULL,
    `account_non_locked` bit(1) DEFAULT NULL,
    `credentials_non_expired` bit(1) DEFAULT NULL,
    `enabled` bit(1) DEFAULT NULL,
    `image_url` VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_k8d0f2n7n88w1a16yhua64onx` (`user_name`)
    )


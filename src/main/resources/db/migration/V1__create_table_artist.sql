CREATE TABLE IF NOT EXISTS `artist` (
    `id` binary(16) NOT NULL,
    `external_url` varchar(255) DEFAULT NULL,
    `name` varchar(255) DEFAULT NULL,
    `slug` varchar(255) DEFAULT NULL,
    `spotify_id` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
    );

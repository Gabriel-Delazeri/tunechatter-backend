CREATE TABLE IF NOT EXISTS `album` (
    `id` binary(16) NOT NULL,
    `external_url` varchar(255) DEFAULT NULL,
    `image_url` varchar(255) DEFAULT NULL,
    `label` varchar(255) DEFAULT NULL,
    `name` varchar(255) DEFAULT NULL,
    `release_date` date DEFAULT NULL,
    `slug` varchar(255) DEFAULT NULL,
    `spotify_id` varchar(255) DEFAULT NULL,
    `total_tracks` int NOT NULL,
    PRIMARY KEY (`id`)
    );
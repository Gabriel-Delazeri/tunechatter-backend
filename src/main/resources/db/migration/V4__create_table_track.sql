CREATE TABLE IF NOT EXISTS `track` (
    `id` binary(16) NOT NULL,
    `duration_ms` bigint DEFAULT NULL,
    `external_url` varchar(255) DEFAULT NULL,
    `image_url` varchar(255) DEFAULT NULL,
    `name` varchar(255) DEFAULT NULL,
    `number` int DEFAULT NULL,
    `slug` varchar(255) DEFAULT NULL,
    `spotify_id` varchar(255) DEFAULT NULL,
    `album_id` binary(16) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKaxm9pbgk7ptorfyk3o6911q05` (`album_id`),
    CONSTRAINT `FKaxm9pbgk7ptorfyk3o6911q05` FOREIGN KEY (`album_id`) REFERENCES `album` (`id`)
    );
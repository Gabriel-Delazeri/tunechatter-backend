CREATE TABLE IF NOT EXISTS `copyright` (
    `id` binary(16) NOT NULL,
    `text` varchar(255) DEFAULT NULL,
    `type` varchar(255) DEFAULT NULL,
    `album_id` binary(16) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKqshowrx8orydexvqr6dtfg3yt` (`album_id`),
    CONSTRAINT `FKqshowrx8orydexvqr6dtfg3yt` FOREIGN KEY (`album_id`) REFERENCES `album` (`id`)
    );
CREATE TABLE IF NOT EXISTS `track_artist` (
    `track_id` binary(16) NOT NULL,
    `artist_id` binary(16) NOT NULL,
    PRIMARY KEY (`track_id`,`artist_id`),
    KEY `FKft34v2bt5lkrka3owel2o8h9n` (`artist_id`),
    CONSTRAINT `FKft34v2bt5lkrka3owel2o8h9n` FOREIGN KEY (`artist_id`) REFERENCES `artist` (`id`),
    CONSTRAINT `FKsgkjn42o7yk09cpb0l3gsud1v` FOREIGN KEY (`track_id`) REFERENCES `track` (`id`)
    );
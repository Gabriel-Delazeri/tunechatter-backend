CREATE TABLE IF NOT EXISTS `album_artist` (
    `album_id` binary(16) NOT NULL,
    `artist_id` binary(16) NOT NULL,
    PRIMARY KEY (`album_id`,`artist_id`),
    KEY `FK5o0o77w1ed46h3ggftpo7c9kl` (`artist_id`),
    CONSTRAINT `FK5o0o77w1ed46h3ggftpo7c9kl` FOREIGN KEY (`artist_id`) REFERENCES `artist` (`id`),
    CONSTRAINT `FKewu7m144qnl94v79vwwpb47cd` FOREIGN KEY (`album_id`) REFERENCES `album` (`id`)
    );
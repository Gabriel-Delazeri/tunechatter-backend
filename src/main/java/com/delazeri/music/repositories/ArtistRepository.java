package com.delazeri.music.repositories;

import com.delazeri.music.domain.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ArtistRepository extends JpaRepository<Artist, UUID> {
    Optional<Artist> findBySpotifyId(String spotifyId);
}

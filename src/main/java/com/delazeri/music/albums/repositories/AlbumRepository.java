package com.delazeri.music.albums.repositories;

import com.delazeri.music.albums.entities.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AlbumRepository extends JpaRepository<Album, UUID> {
    Optional<Album> findBySpotifyId(String spotifyId);
}

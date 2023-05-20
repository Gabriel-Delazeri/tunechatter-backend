package com.delazeri.music.albums.repositories;

import com.delazeri.music.albums.dtos.SimpleAlbumDTO;
import com.delazeri.music.albums.entities.Album;
import com.delazeri.music.artists.dtos.ArtistDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface AlbumRepository extends JpaRepository<Album, UUID> {
    @Query("SELECT new com.delazeri.music.albums.dtos.SimpleAlbumDTO(a.id, a.spotifyId, a.name, a.slug, a.imageUrl) FROM Album a order by a.name DESC ")
    List<SimpleAlbumDTO> findAllAlbums();

    @Query("SELECT new com.delazeri.music.artists.dtos.ArtistDTO(a.id, a.spotifyId, a.externalUrl, a.name, a.slug) FROM Album a WHERE a.id = :albumId")
    Set<ArtistDTO> findAlbumArtists(@Param("albumId") UUID albumId);

    Optional<Album> findBySpotifyId(String spotifyId);

    Optional<Album> findBySlug(String slug);
}

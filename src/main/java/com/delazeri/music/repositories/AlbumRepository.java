package com.delazeri.music.repositories;

import com.delazeri.music.dtos.albums.SimpleAlbumDTO;
import com.delazeri.music.domain.Album;
import com.delazeri.music.domain.Artist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface AlbumRepository extends JpaRepository<Album, UUID> {
    @Query("SELECT new com.delazeri.music.dtos.albums.SimpleAlbumDTO(a.id, a.spotifyId, a.name, a.slug, a.imageUrl, a.releaseDate) FROM Album a order by a.name DESC ")
    Page<SimpleAlbumDTO> findAllAlbums(Pageable pageable);

    @Query("SELECT a.artists FROM Album a WHERE a.id = :albumId")
    Set<Artist> findAlbumArtists(@Param("albumId") UUID albumId);

    Optional<Album> findBySpotifyId(String spotifyId);

    Optional<Album> findBySlug(String slug);

    @Query("SELECT new com.delazeri.music.dtos.albums.SimpleAlbumDTO(a.id, a.spotifyId, a.name, a.slug, a.imageUrl, a.releaseDate), COUNT(r.id) AS countReviews " +
            "FROM Album a " +
            "LEFT JOIN Review r ON r.album = a " +
            "WHERE r.postedAt >= :startDate " +
            "GROUP BY a.id, a.spotifyId, a.name, a.slug, a.imageUrl, a.releaseDate " +
            "ORDER BY count(r.id) DESC")
    Page<SimpleAlbumDTO> findAllReviewdAlbumsOrderedByPopularity(Pageable pageable, LocalDateTime startDate);
}

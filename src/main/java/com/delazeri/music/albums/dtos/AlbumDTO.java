package com.delazeri.music.albums.dtos;

import com.delazeri.music.artists.entities.Artist;

import java.util.Set;
import java.util.UUID;

public class AlbumDTO {

    private UUID id;
    private String spotifyId;
    private String name;
    private String slug;
    private String imageUrl;
    private Set<Artist> artists;

    public AlbumDTO(UUID id, String spotifyId, String name, String slug, String imageUrl) {
        this.id = id;
        this.spotifyId = spotifyId;
        this.name = name;
        this.slug = slug;
        this.imageUrl = imageUrl;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSpotifyId() {
        return spotifyId;
    }

    public void setSpotifyId(String spotifyId) {
        this.spotifyId = spotifyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Set<Artist> getArtists() {
        return artists;
    }

    public void setArtists(Set<Artist> artists) {
        this.artists = artists;
    }
}



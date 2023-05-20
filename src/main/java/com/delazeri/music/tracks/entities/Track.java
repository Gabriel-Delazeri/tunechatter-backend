package com.delazeri.music.tracks.entities;

import com.delazeri.music.albums.entities.Album;
import com.delazeri.music.artists.entities.Artist;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
public class Track implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String spotifyId;

    private String name;

    private Integer number;

    private String slug;

    @ManyToMany
    @JoinTable(
            name = "track_artist",
            joinColumns = @JoinColumn(name = "track_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    @Cascade(CascadeType.PERSIST)
    private Set<Artist> artists;

    private Long durationMs;

    private String externalUrl;

    private String imageUrl;

    @ManyToOne(cascade = jakarta.persistence.CascadeType.PERSIST)
    @JoinColumn(name = "album_id")
    @JsonIgnore
    private Album album;

    public Track() {
    }

    public Track(String spotifyId, String name, Integer number, String slug, Set<Artist> artists, Long durationMs, String externalUrl, String imageUrl, Album album) {
        this.spotifyId = spotifyId;
        this.name = name;
        this.number = number;
        this.slug = slug;
        this.artists = artists;
        this.durationMs = durationMs;
        this.externalUrl = externalUrl;
        this.imageUrl = imageUrl;
        this.album = album;
    }

    public Track(UUID id, String spotifyId, String name, Integer number, String slug, Set<Artist> artists, Long durationMs, String externalUrl, String imageUrl, Album album) {
        this.id = id;
        this.spotifyId = spotifyId;
        this.name = name;
        this.number = number;
        this.slug = slug;
        this.artists = artists;
        this.durationMs = durationMs;
        this.externalUrl = externalUrl;
        this.imageUrl = imageUrl;
        this.album = album;
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Set<Artist> getArtists() {
        return artists;
    }

    public void setArtists(Set<Artist> artists) {
        this.artists = artists;
    }

    public Long getDurationMs() {
        return durationMs;
    }

    public void setDurationMs(Long durationMs) {
        this.durationMs = durationMs;
    }

    public String getExternalUrl() {
        return externalUrl;
    }

    public void setExternalUrl(String externalUrl) {
        this.externalUrl = externalUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}

package com.delazeri.music.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

@Entity
public class Artist implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String spotifyId;
    private String externalUrl;
    private String name;
    private String slug;
    @ManyToMany(mappedBy = "artists")
    @JsonIgnore
    private Set<Album> albums;
    @ManyToMany(mappedBy = "artists")
    @JsonIgnore
    private Set<Track> tracks;

    public Artist() {
        this.albums = new HashSet<>();
    }

    public Artist(UUID id, String spotifyId, String externalUrl, String name, String slug, Set<Track> tracks) {
        this.id = id;
        this.spotifyId = spotifyId;
        this.externalUrl = externalUrl;
        this.name = name;
        this.slug = slug;
        this.albums = new HashSet<>();
    }

    public Artist(String spotifyId, String externalUrl, String name, String slug) {
        this.spotifyId = spotifyId;
        this.externalUrl = externalUrl;
        this.name = name;
        this.slug = slug;
        this.albums = new HashSet<>();
    }

    public Artist(UUID id, String name) {
        this.id = id;
        this.name= name;
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

    public String getExternalUrl() {
        return externalUrl;
    }

    public void setExternalUrl(String externalUrl) {
        this.externalUrl = externalUrl;
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

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    public Set<Track> getTracks() {
        return tracks;
    }

    public void setTracks(Set<Track> tracks) {
        this.tracks = tracks;
    }
}

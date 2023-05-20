package com.delazeri.music.albums.entities;

import com.delazeri.music.artists.entities.Artist;
import com.delazeri.music.tracks.entities.Track;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Entity
public class Album implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String spotifyId;

    private String name;

    private String slug;

    private int totalTracks;

    private String externalUrl;

    private LocalDate releaseDate;

    private String label;

    private String imageUrl;

    @OneToMany(mappedBy = "album")
    @Cascade(CascadeType.PERSIST)
    private Set<Copyright> copyrights;

    @ManyToMany
    @JoinTable(
            name = "album_artist",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    @Cascade(CascadeType.PERSIST)
    private Set<Artist> artists;

    @OneToMany(mappedBy = "album", cascade = jakarta.persistence.CascadeType.PERSIST)
    @Cascade(CascadeType.PERSIST)
        private Set<Track> tracks;

    public Album() {
        this.copyrights = new HashSet<>();
        this.artists = new HashSet<>();
        this.tracks = new HashSet<>();
    }

    public Album(UUID id, String spotifyId, String name, String slug, int totalTracks, String externalUrl, LocalDate releaseDate, String label, String imageUrl, HashSet<Copyright> copyrights, Set<Artist> artists, Set<Track> tracks) {
        this.id = id;
        this.spotifyId = spotifyId;
        this.name = name;
        this.slug = slug;
        this.totalTracks = totalTracks;
        this.externalUrl = externalUrl;
        this.releaseDate = releaseDate;
        this.label = label;
        this.imageUrl = imageUrl;
        this.copyrights = copyrights;
        this.artists = artists;
        this.tracks = tracks;
    }

    public Album(UUID id, String spotifyId, String name, String slug) {
        this.id = id;
        this.spotifyId = spotifyId;
        this.name = name;
        this.slug = slug;
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

    public int getTotalTracks() {
        return totalTracks;
    }

    public void setTotalTracks(int totalTracks) {
        this.totalTracks = totalTracks;
    }

    public String getExternalUrl() {
        return externalUrl;
    }

    public void setExternalUrl(String externalUrl) {
        this.externalUrl = externalUrl;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Set<Copyright> getCopyrights() {
        return copyrights;
    }

    public void setCopyrights(Set<Copyright> copyrights) {
        this.copyrights = copyrights;
    }

    public Set<Artist> getArtists() {
        return artists;
    }

    public void setArtists(Set<Artist> artists) {
        this.artists = artists;
    }

    public Set<Track> getTracks() {
        return tracks;
    }

    public void setTracks(Set<Track> tracks) {
        this.tracks = tracks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return Objects.equals(id, album.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

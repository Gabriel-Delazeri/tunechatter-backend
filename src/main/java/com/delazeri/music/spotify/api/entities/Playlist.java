package com.delazeri.music.spotify.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Playlist {

    private String name;

    @JsonProperty(value = "tracks")
    private TrackList tracks;

    public Playlist() {
    }

    public Playlist(String name, TrackList tracks) {
        this.name = name;
        this.tracks = tracks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TrackList getTracks() {
        return tracks;
    }

    public void setTracks(TrackList tracks) {
        this.tracks = tracks;
    }
}

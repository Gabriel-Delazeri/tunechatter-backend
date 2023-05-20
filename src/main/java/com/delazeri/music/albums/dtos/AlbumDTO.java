package com.delazeri.music.albums.dtos;

import com.delazeri.music.artists.dtos.ArtistDTO;
import com.delazeri.music.tracks.dtos.TrackDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private UUID id;

    @JsonProperty(value = "spotify_id")
    private String spotifyId;

    private String name;

    private String slug;

    @JsonProperty(value = "total_tracks")
    private int totalTracks;

    @JsonProperty(value = "external_url")
    private String externalUrl;

    @JsonProperty(value = "release_date")
    private LocalDate releaseDate;

    private String label;

    @JsonProperty(value = "image_url")
    private String imageUrl;

    private Set<CopyrightDTO> copyrights;

    private Set<ArtistDTO> artists;

    private Set<TrackDTO> tracks;
}

package com.delazeri.music.tracks.dtos;

import com.delazeri.music.artists.dtos.ArtistDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private UUID id;

    @JsonProperty(value = "spotify_id")
    private String spotifyId;

    private String name;

    private Integer number;

    private String slug;

    private Set<ArtistDTO> artists;

    @JsonProperty(value = "duration_ms")
    private Long durationMs;

    @JsonProperty(value = "external_url")
    private String externalUrl;

    @JsonProperty(value = "image_url")
    private String imageUrl;
}

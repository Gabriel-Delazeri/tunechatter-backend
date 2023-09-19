package com.delazeri.music.dtos.artists;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ArtistDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private UUID id;
    @JsonProperty(value = "spotify_id")
    private String spotifyId;
    @JsonProperty(value = "external_url")
    private String externalUrl;
    private String name;
    private String slug;

    public ArtistDTO(UUID id, String spotifyId, String externalUrl, String name, String slug) {
        this.id = id;
        this.spotifyId = spotifyId;
        this.externalUrl = externalUrl;
        this.name = name;
        this.slug = slug;
    }
}

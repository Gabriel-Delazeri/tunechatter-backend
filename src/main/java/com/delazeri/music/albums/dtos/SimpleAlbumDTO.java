package com.delazeri.music.albums.dtos;

import com.delazeri.music.artists.dtos.ArtistDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.internal.bytebuddy.build.Plugin;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleAlbumDTO {

    private UUID id;
    @JsonProperty(value = "spotify_id")
    private String spotifyId;
    private String name;
    private String slug;
    @JsonProperty(value = "image_url")
    private String imageUrl;
    private Set<ArtistDTO> artists;

    public SimpleAlbumDTO(UUID id, String spotifyId, String name, String slug, String imageUrl) {
        this.id = id;
        this.spotifyId = spotifyId;
        this.name = name;
        this.slug = slug;
        this.imageUrl = imageUrl;
    }
}



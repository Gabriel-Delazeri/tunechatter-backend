package com.delazeri.music.albums.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumSummaryDTO {

    private UUID id;
    private String name;
    private String slug;
    @JsonProperty(value = "image_url")
    private String imageUrl;
    @JsonProperty(value = "release_date")
    private LocalDate releaseDate;
}

package com.delazeri.music.albums.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumSummaryDTO {

    private UUID id;
    private String name;
    private String slug;
    private String imageUrl;
}

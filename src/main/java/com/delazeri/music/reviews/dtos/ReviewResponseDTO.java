package com.delazeri.music.reviews.dtos;

import com.delazeri.music.albums.dtos.AlbumSummaryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private UUID id;
    private AlbumSummaryDTO album;
    private UUID userID;
    private String comment;
}

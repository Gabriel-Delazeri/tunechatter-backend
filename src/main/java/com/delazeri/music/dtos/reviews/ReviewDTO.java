package com.delazeri.music.dtos.reviews;

import com.delazeri.music.dtos.albums.AlbumSummaryDTO;
import com.delazeri.music.dtos.users.UserSummaryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private UUID id;
    private AlbumSummaryDTO album;
    private UserSummaryDTO user;
    private String comment;
    private double rating;
    private LocalDateTime postedAt;
    private long likeCount;
}

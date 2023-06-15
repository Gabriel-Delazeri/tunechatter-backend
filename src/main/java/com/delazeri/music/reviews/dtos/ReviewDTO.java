package com.delazeri.music.reviews.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private UUID id;
    private UUID albumID;
    private UUID userID;
    private String comment;
}

package com.delazeri.music.infra.exceptions.custom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;
import java.io.Serializable;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserStillNotLikedReviewException extends IllegalArgumentException implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public UserStillNotLikedReviewException(String ex) {
        super(ex);
    }
}

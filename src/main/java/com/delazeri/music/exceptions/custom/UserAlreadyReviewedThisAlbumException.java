package com.delazeri.music.exceptions.custom;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyReviewedThisAlbumException extends AuthenticationException implements Serializable {

    private static final long serialVersionUID = 1L;

    public UserAlreadyReviewedThisAlbumException(String ex) {
        super(ex);
    }
}

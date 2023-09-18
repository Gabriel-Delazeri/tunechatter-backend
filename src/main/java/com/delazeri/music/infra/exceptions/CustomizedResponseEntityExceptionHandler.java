package com.delazeri.music.infra.exceptions;

import com.delazeri.music.infra.exceptions.custom.InvalidJwtAuthenticationException;
import com.delazeri.music.infra.exceptions.custom.UserAlreadyLikedThisReviewException;
import com.delazeri.music.infra.exceptions.custom.UserAlreadyReviewedThisAlbumException;
import com.delazeri.music.infra.exceptions.custom.UserStillNotLikedReviewException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(
            Exception ex,
            WebRequest request
    ) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidJwtAuthenticationException.class)
    public final ResponseEntity<ExceptionResponse> handleInvalidJwtAuthenticationException(
            Exception ex,
            WebRequest request
    ) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UserAlreadyReviewedThisAlbumException.class)
    public final ResponseEntity<ExceptionResponse> handleUserAlreadyReviewedThisAlbumException(
            Exception ex,
            WebRequest request
    ) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserAlreadyLikedThisReviewException.class)
    public final ResponseEntity<ExceptionResponse> handleUserAlreadyLikedThisReviewException(
            Exception ex,
            WebRequest request
    ) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserStillNotLikedReviewException.class)
    public final ResponseEntity<ExceptionResponse> handleUserStillNotLikedThisReviewException(
            Exception ex,
            WebRequest request
    ) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }
}

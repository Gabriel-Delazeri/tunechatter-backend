package com.delazeri.music.dtos.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RegisterUserRequest(
        String username,
        @JsonProperty(value = "first_name") String firstName,
        @JsonProperty(value = "last_name") String lastName,
        String email,
        String password
) {
}

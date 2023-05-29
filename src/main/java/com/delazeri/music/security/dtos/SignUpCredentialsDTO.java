package com.delazeri.music.security.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpCredentialsDTO {

    @NotBlank
    @JsonProperty(value = "username")
    private String userName;
    @NotBlank
    @JsonProperty(value = "first_name")
    private String firstName;
    @NotBlank
    @JsonProperty(value = "last_name")
    private String lastName;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String imageUrl;
}

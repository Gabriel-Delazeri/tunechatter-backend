package com.delazeri.music.security.dtos;

import com.delazeri.music.users.dtos.UserDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class TokenDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private UserDTO user;
    private Boolean authenticated;
    private Date created;
    private Date expiration;
    @JsonProperty(value = "access_token")
    private String accessToken;
    @JsonProperty(value = "refresh_token")
    private String refreshToken;

    public TokenDTO(
            UserDTO user,
            Boolean authenticated,
            Date created,
            Date expiration,
            String accessToken,
            String refreshToken
    ) {
        this.user = user;
        this.authenticated = authenticated;
        this.created = created;
        this.expiration = expiration;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public TokenDTO() {
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Boolean getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenDTO tokenDTO = (TokenDTO) o;
        return Objects.equals(user, tokenDTO.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user);
    }
}

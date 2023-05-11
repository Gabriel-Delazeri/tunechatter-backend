package com.delazeri.music.spotify.api.services;

import com.delazeri.music.spotify.api.dtos.AccessTokenDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;

@Service
public class SpotifyAuthService {

    @Value("${spotify.api.client.id}")
    private String clientId;

    @Value("${spotify.api.client.secret}")
    private String clientSecret;

    @Value("${spotify.api.scopes}")
    private String scopes;

    @Value("${spotify.api.redirect-uri}")
    private String redirectUri;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getAuthorizationUrl() {
        return "https://accounts.spotify.com/authorize?client_id=" + clientId +
                "&response_type=code" +
                "&redirect_uri=" + redirectUri +
                "&scope=" + URLEncoder.encode(scopes);
    }

    public String getAccessToken(String authorizationCode) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", "authorization_code");
        requestBody.add("code", authorizationCode);
        requestBody.add("redirect_uri", redirectUri);
        requestBody.add("client_id", clientId);
        requestBody.add("client_secret", clientSecret);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<AccessTokenDTO> responseEntity = restTemplate.postForEntity(
                "https://accounts.spotify.com/api/token",
                requestEntity,
                AccessTokenDTO.class
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            AccessTokenDTO response = responseEntity.getBody();
            if (response != null) {
                return response.getAccessToken();
            }
        }

        throw new IllegalStateException("Failed to retrieve access token from Spotify.");
    }
}

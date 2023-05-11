package com.delazeri.music.spotify.api.services;

import com.delazeri.music.spotify.api.entities.Playlist;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SpotifyApiService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${spotify.api.base-url}")
    private String baseUrl;

    private static HttpEntity<String> setHeaders(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", accessToken);
        return new HttpEntity<>(headers);
    }

    public Playlist getPlaylistTracks(String playlistId, String accessToken) throws JsonProcessingException {
        HttpEntity<String> entity = setHeaders(accessToken);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                baseUrl + "/playlists/" + playlistId,
                HttpMethod.GET,
                entity,
                String.class
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            String responseBody = responseEntity.getBody();
            ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.readValue(responseBody, Playlist.class);
        }

        throw new IllegalStateException("Failed to search playlist on Spotify.");
    }
}

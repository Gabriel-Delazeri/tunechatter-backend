package com.delazeri.music.controllers;

import com.delazeri.music.services.SpotifyAuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spotify")
public class SpotifyAuthController {
    private final SpotifyAuthService spotifyService;

    public SpotifyAuthController(SpotifyAuthService spotifyService) {
        this.spotifyService = spotifyService;
    }

    @GetMapping("/login")
    public String login() {
        String authorizationUrl = spotifyService.getAuthorizationUrl();
        return "redirect:" + authorizationUrl;
    }

    @GetMapping("/callback")
    public String callback(@RequestParam("code") String authorizationCode) {
        return spotifyService.getAccessToken(authorizationCode);
    }
}

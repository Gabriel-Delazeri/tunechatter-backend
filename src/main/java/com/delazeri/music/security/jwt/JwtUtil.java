package com.delazeri.music.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class JwtUtil {

    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey = "secret";

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public DecodedJWT decodedToken(String token) {
        if (token.contains("Bearer ")) token =
                token.substring("Bearer ".length());

        Algorithm alg = Algorithm.HMAC256(secretKey.getBytes());
        JWTVerifier verifier = JWT.require(alg).build();
        return verifier.verify(token);
    }
}

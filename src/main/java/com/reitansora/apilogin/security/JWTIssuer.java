package com.reitansora.apilogin.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
@RequiredArgsConstructor
public class JWTIssuer {

    private final JwtProperties properties;

    public String issue(String encryptedUserId, String nickname,String email, String createdAt){
        return JWT.create()
                .withSubject(String.valueOf(encryptedUserId))
                .withExpiresAt(Instant.now().plus(Duration.of(1, ChronoUnit.DAYS)))
                .withClaim("nickname", nickname)
                .withClaim("email", email)
                .withClaim("createdAt", createdAt)
                .sign(Algorithm.HMAC256(properties.getSecretKey()));
    }
}

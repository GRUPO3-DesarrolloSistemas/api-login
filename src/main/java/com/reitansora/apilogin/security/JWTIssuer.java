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

    public String issue(long userId, String nickname,String email){
        return JWT.create()
                .withSubject(String.valueOf(userId))
                .withExpiresAt(Instant.now().plus(Duration.of(1, ChronoUnit.DAYS)))
                .withClaim("nickname", nickname)
                .withClaim("email", email)
                .sign(Algorithm.HMAC256(properties.getSecretKey()));
    }
}

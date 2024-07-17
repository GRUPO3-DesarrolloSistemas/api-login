package com.reitansora.apilogin.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.catalina.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtToPrincipalConverter {
    public UserPrincipal convert(DecodedJWT decodedJWT) {
        return UserPrincipal.builder()
                .userId(Long.valueOf(decodedJWT.getSubject()))
                .email(decodedJWT.getClaim("email").asString())
                .build();


    }

    private List<SimpleGrantedAuthority> getAuthorities(DecodedJWT decodedJWT) {
        var claim = decodedJWT.getClaim("authorities");
        if (claim.isNull() || claim.isMissing()) return List.of();
        return claim.asList(SimpleGrantedAuthority.class);
    }
}

package com.reitansora.apilogin.security;

import com.reitansora.apilogin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userService.findByEmail(username).orElseThrow();
        return UserPrincipal.builder()
                .userId(user.getUser_id())
                .username(user.getUser_nickname())
                .email(user.getUser_email())
                .password(user.getUser_password())
                .build();
    }
}

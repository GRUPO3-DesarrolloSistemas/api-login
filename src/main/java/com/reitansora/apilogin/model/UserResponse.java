package com.reitansora.apilogin.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {

    private final Long id;
    private final String userId;
    private final String username;
    private final String email;
    private final String password;
    private final String createdAt;
}

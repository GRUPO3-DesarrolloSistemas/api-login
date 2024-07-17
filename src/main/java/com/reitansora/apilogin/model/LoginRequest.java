package com.reitansora.apilogin.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginRequest {

    public String email;
    public String password;

}

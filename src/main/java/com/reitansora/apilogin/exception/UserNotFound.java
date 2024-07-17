package com.reitansora.apilogin.exception;

public class UserNotFound extends Exception {
    public UserNotFound(long id) {
        super("User with id: " + id + " did not exist");
    }
}

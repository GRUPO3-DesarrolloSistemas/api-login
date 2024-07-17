package com.reitansora.apilogin.exception;

public class ValidationFailed extends Exception {
    public ValidationFailed() {
        super("Validation failed, email or password is incorrect");
    }
}

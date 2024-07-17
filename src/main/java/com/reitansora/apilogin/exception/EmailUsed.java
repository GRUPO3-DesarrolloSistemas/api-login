package com.reitansora.apilogin.exception;

public class EmailUsed extends Exception {
    public EmailUsed(String email) {
        super("The email '" + email + "' is in use by another account");
    }
}

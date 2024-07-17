package com.reitansora.apilogin.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidation {

    public boolean EmailValidator (String email) {
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9.-]+([_A-Za-z0-9.-]+)*@[\\.a-z]+([\\.a-z]{3})+([\\.a-z]?{3})$");
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

    public boolean PasswordValidator (String password) {
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9.-]{8,}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }
}

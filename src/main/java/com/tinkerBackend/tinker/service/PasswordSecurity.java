package com.tinkerBackend.tinker.service;

import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class PasswordSecurity {
    public String encodePassword(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }

    public String decodePassword(String encodedPassword) {
        return new String (Base64.getMimeDecoder().decode(encodedPassword));
    }
}

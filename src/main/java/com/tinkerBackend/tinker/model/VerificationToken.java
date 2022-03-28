package com.tinkerBackend.tinker.model;

public class VerificationToken {

    String verificationCode;
    String email;

    public VerificationToken(String verificationCode, String email) {
        this.verificationCode = verificationCode;
        this.email = email;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public String getEmail() {
        return email;
    }
}

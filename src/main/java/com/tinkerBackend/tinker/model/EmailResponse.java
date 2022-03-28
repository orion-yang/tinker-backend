package com.tinkerBackend.tinker.model;

public class EmailResponse {
    String message;

    public EmailResponse() {
        this.message = "";
    }

    public EmailResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

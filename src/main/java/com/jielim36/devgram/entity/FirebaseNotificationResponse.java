package com.jielim36.devgram.entity;

public class FirebaseNotificationResponse {
    private int status;
    private String message;

    public FirebaseNotificationResponse() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

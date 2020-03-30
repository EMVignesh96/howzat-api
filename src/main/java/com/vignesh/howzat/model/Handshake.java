package com.vignesh.howzat.model;

public class Handshake {
    private long timeInMillis;
    private String message;

    public Handshake(long timeInMillis, String message) {
        this.timeInMillis = timeInMillis;
        this.message = message;
    }

    public long getTimeInMillis() {
        return timeInMillis;
    }

    public void setTimeInMillis(long timeInMillis) {
        this.timeInMillis = timeInMillis;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

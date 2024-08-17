package com.leodubosclard.model;

public class Packet {
    private String message;

    public Packet() {}

    public Packet(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String value) {
        message = value;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[message=\"" + message + "\"]";
    }
}

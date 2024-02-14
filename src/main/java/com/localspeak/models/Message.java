package com.localspeak.models;

import java.util.Date;

public class Message {
    String data;
    String timestamp;

    public Message(String data, String timestamp) {
        this.data = data;
        this.timestamp = new Date().toString();
    }

    public byte[] getBytes() {
        return toString().getBytes();
    }

    public String toString() {
        return this.timestamp+":"+this.data;
    }
}

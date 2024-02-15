package com.localspeak;

import java.util.Date;

public class Packet {
    private PacketListener listener;
    String data;
    Date timestamp;

    public Packet(String data) {
        this.data = data;
        this.timestamp = new Date();
        listener.newMessage(data, timestamp);
    }

    public void setListener(PacketListener newListener) {
        listener = newListener;
    }

    public byte[] getBytes() {
        return toString().getBytes();
    }

    public String toString() {
        return this.timestamp+":"+this.data;
    }
}

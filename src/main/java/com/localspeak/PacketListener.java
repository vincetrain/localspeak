package com.localspeak;

import java.util.Date;

public interface PacketListener {
    public void newMessage(String data, Date timestamp);
}

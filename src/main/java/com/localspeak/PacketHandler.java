package com.localspeak;

import java.util.Date;

public class PacketHandler {

    String data_prev = "";

    public void newMessage(String data, Date timestamp) {
        if (!data.equals(data_prev)) {
            System.out.println(new String(data));
            data_prev = new String(data);
        }
    }
    
}

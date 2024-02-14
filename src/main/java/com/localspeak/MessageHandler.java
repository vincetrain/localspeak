package com.localspeak;

public class MessageHandler implements MessageListener {

    String data_prev = "";

    @Override
    public void newMessage(String data) {
        if (!data.equals(data_prev)) {
            System.out.println(new String(data));
            data_prev = new String(data);
        }
    }
    
}

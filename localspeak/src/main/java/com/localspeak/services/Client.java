package com.localspeak.services;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.net.Socket;

public class Client {

    Socket socket;
    String response;

    DataOutputStream dout;
    DataInputStream din;

    public void createConnection() throws IOException{
        socket = new Socket("localhost", 17763);
    }

    public void closeConnection() throws IOException {
        dout.close();
        socket.close();
    }

    public void sendMessage(String msg) throws IOException {
        if (!socket.isClosed()) {
            dout = new DataOutputStream(socket.getOutputStream());
            dout.writeUTF(msg);
        }
        else {
            System.out.println("Socket is not connected. No message sent.");
        }
    }

    public void printResponse() throws IOException {
        if (!socket.isClosed()) {
            din = new DataInputStream(socket.getInputStream());
            System.out.println((String)din.readUTF());
        }
        else {
            System.out.println("Socket is not connected. No response received.");
        }
    }
}

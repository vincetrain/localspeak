package com.localspeak.services;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    Socket socket;
    String response;

    DataOutputStream dout;
    DataInputStream din;

    public Client(Inet4Address ip, int port) throws IOException{
        // Automatically closes socket if not closed already.
        if (socket != null) {
            this.close();
        }
        socket = new Socket(ip, port);
        dout = new DataOutputStream(socket.getOutputStream());
        ClientResponseReader ClientResponseReader = new ClientResponseReader(socket);
        ClientResponseReader.start();
        this.sendMessage("connected.");
    }

    public void close() throws IOException {
        dout.close();
        socket.close();
    }

    public boolean isConnected() { 
       return !socket.isClosed(); 
    }

    public void sendMessage(String msg) throws IOException {
        if (!socket.isClosed()) {
            dout.writeUTF(msg);
        }
        else {
            System.out.println("Socket is not connected. No message sent.");
        }
    }
}

class ClientResponseReader extends Thread {
    
    Socket socket;
    DataInputStream din;

    public ClientResponseReader(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            din = new DataInputStream(socket.getInputStream());
            String serverInput = "";
            // Reads the server output (that should've been put into socket input) and prints
            do {
                System.out.println(serverInput);
            } while ((serverInput=din.readUTF()) != null);
        } catch (IOException e) {
            if (socket == null) {
                System.out.println("Failed getting I/O: " + e);
            }
        }
    }
}
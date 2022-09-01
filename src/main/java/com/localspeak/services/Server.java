package com.localspeak.services;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    ServerSocket serverSocket;
    Socket socket;

    DataInputStream din;
    DataOutputStream dout;


    String clientmsg;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port); // Initialize server socket under provided port
        serverSocket.setReuseAddress(true);
        while (true) {
            socket = serverSocket.accept(); // Accepts incoming connection into socket
            ClientHandler clientHandler = new ClientHandler(socket);
            clientHandler.run();
        }
    }
}

class ClientHandler implements Runnable {

    DataInputStream din;
    DataOutputStream dout;

    Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            String userInput = "";
            din = new DataInputStream(socket.getInputStream());
            dout = new DataOutputStream(socket.getOutputStream());
            dout.writeUTF(socket.getInetAddress().getHostAddress() + " connected.");
            while ((userInput = din.readUTF()) != null) {
                dout.writeUTF(socket.getInetAddress().getHostAddress() + ": " + userInput);
            }
        }
        catch (Exception e) {
            System.out.println("An error occured: " + e);
        }
        finally {
            try {
                if (dout != null) {
                    dout.close();
                }
                if (din != null) {
                    din.close();
                    din.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


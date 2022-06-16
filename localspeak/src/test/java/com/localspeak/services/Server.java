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
        socket = serverSocket.accept(); // Accepts incoming connection into socket
        din = new DataInputStream(socket.getInputStream());
        dout = new DataOutputStream(socket.getOutputStream());



        while (!socket.isClosed()) {
            clientmsg = (String)din.readUTF();
            dout.writeUTF((socket.getInetAddress() + ": " + clientmsg));
        }

        dout.close();
        din.close();
    }
}

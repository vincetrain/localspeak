package com.localspeak;

import java.util.Arrays;

import java.net.Socket;
import java.net.ServerSocket;

import java.io.IOException;

import java.io.InputStream;
import java.io.OutputStream;


public class Connection {
    public static final int TIMEOUT_DEFAULT = 500;

    private ServerSocket listener;
    private SocketThread client;

    public Connection() {
        listener = null;
        client = null;
    }

    public void listen(int desiredPort) throws IOException, PortOutOfRangeException {
        if (desiredPort < 1024 || desiredPort > 65535) {
            throw new PortOutOfRangeException(); 
        }
        listener = new ServerSocket(desiredPort);
        client = new SocketThread(listener);
        client.start();
        send("Connection established.");
    }

    public void connect(String ip, int port) throws IOException {
        client = new SocketThread(ip, port);
        client.start();
    }

    public void send(String data) throws IOException {
    }

    public void close() throws IOException {
        listener.close();
    }

    class SocketThread extends Thread {
        PacketHandler packetHandler;
        ServerSocket server;
        Socket client;

        String ip;
        int port;

        public SocketThread(String desiredIp, int desiredPort) {
            server = null;
            ip = desiredIp;
            port = desiredPort;
        }
        
        public SocketThread(ServerSocket serverSocket) {
            server = serverSocket;
        }

        public void run() {
            try {
                if (server != null) {
                    client = server.accept();
                }
                else {
                    client = new Socket(ip, port);
                }

                OutputStream out = client.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class PortOutOfRangeException extends Exception {
    public PortOutOfRangeException() {
        super("Port is out of range 1024-65535");
    }
}
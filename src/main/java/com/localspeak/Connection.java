package com.localspeak;

import com.localspeak.models.Message;

import java.util.Arrays;

import java.net.Socket;
import java.net.ServerSocket;

import java.io.IOException;

import java.io.InputStream;
import java.io.OutputStream;


public class Connection {
    public static final int PORT_DEFAULT = 7763;
    public static final int TIMEOUT_DEFAULT = 500;

    private ServerSocket listener;
    private Socket client;

    public Connection() {
        listener = null;
        client = null;
    }

    public Connection(byte[] desiredKey) {
        listener = null;
        client = null;
    }

    public void listen() throws IOException {
        listener = new ServerSocket(PORT_DEFAULT);
        client = listener.accept();
        send("Connection established.");
    }

    public void listen(int desiredPort) throws IOException, PortOutOfRangeException {
        if (desiredPort < 1024 || desiredPort > 65535) {
            throw new PortOutOfRangeException(); 
        }
        listener = new ServerSocket(desiredPort);
        client = listener.accept();
        send("Connection established.");
    }

    public void listen(int desiredPort, String authKey) throws IOException, PortOutOfRangeException {
        if (desiredPort < 1024 || desiredPort > 65535) {
            throw new PortOutOfRangeException(); 
        }
        listener = new ServerSocket(desiredPort);
        client = filterAccept(listener, authKey.getBytes());
        send("Connection established.");
    }

    public void connect(String ip, int port) throws IOException {
        client = new Socket(ip, port);
    }

    public void send(String data) throws IOException {
        client.getOutputStream().write(data.getBytes());
    }

    public void close() throws IOException {
        listener.close();
        client.close();
    }

    public String connectedTo() {
        String returnString;
        try {
            returnString = client.getInetAddress().toString()+":"+client.getPort();
        } catch (NullPointerException e) {
            returnString = "null";
        }
        return returnString;
    }

    // public boolean isConnected() {
    //     return client.isConnected();
    // }

    private Socket filterAccept(ServerSocket server, byte[] authKey) throws IOException {
        Socket acceptedSocket = null;
        while (acceptedSocket == null) {
            Socket tempSocket = server.accept();
            if (tempSocket.isConnected()) {
                InputStream in = tempSocket.getInputStream();
                byte[] recievedKey = new byte[authKey.length];
                int bytesRead = in.read(recievedKey);
                if (bytesRead == authKey.length) {
                    if (!Arrays.equals(authKey, recievedKey)) {
                        tempSocket.close();
                    }
                    else {
                        acceptedSocket = tempSocket;
                    }
                }
                in.close();
            }
        }
        return acceptedSocket;
    }
}

class PortOutOfRangeException extends Exception {
    public PortOutOfRangeException() {
        super("Port is out of range 1024-65535");
    }
}

class MessageHandler {

}
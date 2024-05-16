package com.localspeak;

import java.net.Socket;
import java.net.ServerSocket;

import java.io.IOException;

import java.io.InputStream;
import java.io.OutputStream;


public class ConnectionServer {
    public static final int TIMEOUT_DEFAULT = 3000;

    private ServerSocket listener;
    private SocketThread[] clients;
    private PointerQueue pointerQueue;

    /**
     * Creates a new ConnectionServer instance. Defaults to maximum of 2 clients.
     */
    public ConnectionServer() {
        listener = null;
        clients = new SocketThread[2];
        // Creates new PointerQueue object, and queues both available indices of client arr
        pointerQueue = new PointerQueue();
        pointerQueue.enqueue(0);
        pointerQueue.enqueue(1);
    }

    /**
     * Creates a new ConnectionServer instance with specified maxConnections as maximum amount of clients.
     * @param maxConnections Maximum amount of connections/clients for ConnectionServer instance.
     */
    public ConnectionServer(int maxConnections) {
        listener = null;
        clients = new SocketThread[maxConnections];
        // Creates new PointerQueue object, and iteratively queues each pointer with respect to maxConnections
        pointerQueue = new PointerQueue();
        for (int i = 0; i < maxConnections; i++) {
            pointerQueue.enqueue(i);
        }
    }

    /**
     * Starts listening for connections using Java ServerSocket, listening on specified desiredPort. 
     * @param desiredPort Integer specifying port to listen on with ServerSocket
     * @throws IOException
     * @throws PortOutOfRangeException
     */
    public void listen(int desiredPort) throws IOException, PortOutOfRangeException {
        int currentPointer;
        
        if (desiredPort < 1024 || desiredPort > 65535) {
            throw new PortOutOfRangeException(); 
        }
        listener = new ServerSocket(desiredPort);
        while (true) {
            currentPointer = pointerQueue.dequeue();
            clients[currentPointer] = new SocketThread(listener);
            // TODO find a way to update pointerQueue whenever a SocketThread disconnects, probably heartbeats + observers or something?
        }

    }

    public void close() throws IOException {
        listener.close();
        pointerQueue.clear();
    }

    class SocketThread extends Thread {
        private ServerSocket server;
        private Socket client;

        private InputStream in;

        private Boolean isOpen; // refreshes every heartbeat
        private Boolean occupied;

        public SocketThread(ServerSocket connectionServer) {
            server = connectionServer;
            occupied = true;
        }

        public boolean isOccupied() {
            return occupied;
        }

        public void send(String sendString) {
            
        }

        public void run() {
            try {
                client = server.accept();
                in = client.getInputStream();
                isOpen = true;

            } catch (IOException e) {
                isOpen = false;
                e.printStackTrace();
            }

            while (isOpen) {
                try {
                    System.out.println(in.read());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void interrupt() {
            // TODO handle interrupting/stopping the thread
            try {
                client.close();
                in.close();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}

class PortOutOfRangeException extends Exception {
    public PortOutOfRangeException() {
        super("Failed to start Server Socket: port is out of range (1024-65535)");
    }
}
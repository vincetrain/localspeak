package com.localspeak;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.localspeak.services.*;
import com.localspeak.utility.*;

public final class App {
    static Scanner reader;

    private App() {
    }

    public static void main(String[] args) {
        // Initializes new client instance
        Inet4Address ip = MainUtil.getInet4Address();
        int port = MainUtil.getPort();

        Client client = new Client();
        try {
            client.createConnection(ip, port);
            String userInput = ";";
            while(!userInput.equals("__close")) {
                reader = new Scanner(System.in);
                userInput = reader.nextLine();
                if (!userInput.equals("__close")) {
                    client.sendMessage(userInput); 
                }
            }
            client.closeConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        t1.interrupt();
        t1.stop();
    }

    public static void startServerThread(int port) {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    Server server = new Server();
                    server.startServer(port);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    System.out.println("Unable to start listener server. Exiting...");
                    System.exit(0);
                }
            }
        });

        t1.start();
    }
}

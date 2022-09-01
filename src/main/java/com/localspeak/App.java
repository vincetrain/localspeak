package com.localspeak;

import java.io.IOException;
import java.net.Inet4Address;
import java.util.Scanner;

import com.localspeak.services.*;
import com.localspeak.utility.*;

public final class App {
    private App() {
    }

    public static void main(String[] args) {
        int serverport = 17763;
        Scanner reader = null;

        // Starts new "serverThread" in background
        Thread serverThread = new Thread() {
            public void run() {
                try {
                    Server server = new Server(serverport);
                }
                catch (IOException e) {
                    System.out.println("Failed to get I/O: " + e);   
                }
            }
        };


        // Initializes new client instance
        Inet4Address ip = MainUtil.getInet4Address(reader);
        int port = MainUtil.getPort(reader);

        try {
            if (ip == (Inet4Address)Inet4Address.getByName("localhost")) {
                serverThread.start();
            }
        } catch (Exception e) {
            System.out.println("how the fuck did localhost fail: " + e);
        }

        try {
            Client client = new Client(ip, port);
            String userInput = "";
            do {
                reader = new Scanner(System.in);
                userInput = reader.nextLine();
                if (!userInput.equals("__exit")) {
                    client.sendMessage(userInput); 
                }
            } while(!userInput.equals("__exit"));
            client.close();
        } catch (IOException e) {
            System.out.println("Failed, no I/O: " + e);
        }

        serverThread.interrupt();
        serverThread.stop();
    }


}

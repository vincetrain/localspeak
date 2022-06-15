package com.localspeak;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.localspeak.services.*;

/**
 * Hello world!
 */
public final class App {
    static Scanner reader;

    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        // Starts server on another thread for listening on port
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    Server server = new Server();
                    server.startServer(17763);
                    System.out.println("worked");
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        
        // Initializes new client instance

        int port;
        int ip;

        Client client = new Client();
        try {
            client.createConnection();
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

    public static Inet4Address getInet4Address() throws UnknownHostException {
        Inet4Address ip = null;
        String userInput = "";
        do {
            reader = new Scanner(System.in);
            userInput = reader.nextLine();
        } while(!confirm(userInput));
        ip = (Inet4Address) Inet4Address.getByName(userInput);
        return ip;
    }

    public static boolean confirm(String userInput) {
        System.out.print("Confirm \"" + userInput + "\"? Y/N: ");
        reader = new Scanner(System.in);
        if (reader.nextLine().equals("y")) {
            return true;
        }
        return false;
    }
}

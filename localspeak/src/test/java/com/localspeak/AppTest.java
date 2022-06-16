package com.localspeak;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.localspeak.services.*;
import com.localspeak.utility.*;

public final class AppTest {
    static final String menu = (".__                       .__                                __    \n"
    .concat("|  |   ____   ____ _____  |  |   ____________   ____ _____  |  | __\n")
    .concat("|  |  /  _ \\_/ ___\\\\__  \\ |  |  /  ___/\\____ \\_/ __ \\\\__  \\ |  |/ /\n")
    .concat("|  |_(  <_> )  \\___ / __ \\|  |__\\___ \\ |  |_> >  ___/ / __ \\|    < \n")
    .concat("|____/\\____/ \\___  >____  /____/____  >|   __/ \\___  >____  /__|_ \\\n")
    .concat("                 \\/     \\/          \\/ |__|        \\/     \\/     \\/\n"
    .concat("---------------------------------------------------------------\n")
    .concat("1) Chat\n")
    .concat("2) Read logs\n")
    .concat("3) Settings\n")
    .concat("9) Exit")));
    
    
    

    private AppTest() {
    }

    public static void main(String[] args) {
        final String exitCondition = "9";

        Scanner reader;
        String userInput = "";

        // Menu loop
        do {
            System.out.println(menu);
            reader = new Scanner(System.in);
            userInput = reader.nextLine();

            if (userInput.equals("1")) {
                chatMenu(reader);
            }
            else if (userInput.equals("2")) {

            }
            else if (userInput.equals("3")) {

            }
        } while(!userInput.equals(exitCondition));


    }

    /**
     * Starts asynchronous listener server thread
     * 
     * @param thread Thread object to be started
     * @param port Port to open server on
     */
    public static void startServerThread(Thread thread) {
        int port = 17763;

        thread = new Thread(new Runnable() {
            public void run() {
                try {
                    Server server = new Server();
                    server.startServer(port);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    System.out.println("Unable to start listener server. Exiting...");
                }
            }
        });
        thread.start();
    }

    public static void chatMenu(Scanner reader) throws UnknownHostException {
        Thread serverThread = null;
        int port = 17763;
        Inet4Address localip = (Inet4Address) Inet4Address.getByName("127.0.0.1");
        Inet4Address ip;

        Client client = new Client();
        try {
            String userInput = "";
            System.out.println("Enter \"__connect\" to connect to another IPv4 address, \"__close\" to exit, or \"__disconnect\" to disconnect.");
            startServerThread(serverThread);  // Starts server thread
            while(!userInput.equals("__close")) {
                client.createConnection(localip, port);
                reader = new Scanner(System.in);
                userInput = reader.nextLine();
                if (userInput.equals("__close") || userInput.equals("__disconnect")) {
                    System.out.println("You disconnected from the server.");
                    client.closeConnection();
                }
                else if (userInput.equals("__connect") && !client.isConnected()) { 
                    ip = MainUtil.getInet4Address(reader);
                    client.createConnection(ip, port);
                }
                else {
                    client.sendMessage(userInput); 
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        serverThread.interrupt();
        serverThread.stop();
    }

    public static void chatHome(Scanner reader) throws UnknownHostException{
        Thread serverThread = null;
        int port = 17763;
        Inet4Address localip = (Inet4Address) Inet4Address.getByName("127.0.0.1");
        Inet4Address ip;

        Client client = new Client();
        try {
            String userInput = "";
            System.out.println("Enter \"__connect\" to connect to another IPv4 address, \"__close\" to exit, or \"__disconnect\" to disconnect.");
            startServerThread(serverThread);  // Starts server thread
            while(!userInput.equals("__close")) {
                client.createConnection(localip, port);
                reader = new Scanner(System.in);
                userInput = reader.nextLine();
                if (userInput.equals("__close")) {
                    System.out.println("Closing chat...");
                    client.closeConnection();
                }
                else if (userInput.equals("__connect") && !client.isConnected()) { 
                    ip = MainUtil.getInet4Address(reader);
                    client.createConnection(ip, port);
                }
                else {
                    client.sendMessage(userInput); 
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.localspeak;

import java.util.Scanner;
import java.io.IOException;

public class App {

    final static int PORT_DEFAULT = 7763;

    final static Scanner reader = new Scanner(System.in);

    public static void main( String[] args ) {
        Connection connection = new Connection();
        PacketHandler messageHandler = new PacketHandler();
        try {
            connection.listen(PORT_DEFAULT);
            while (true) {
                
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 
     */
    public static void settings() {

    }

    /**
     * Prints localspeak's main menu
     */
    public static void printMenu() {
        System.out.println(
            " _                 _                      _    \n" + 
            "| |               | |                    | |   \n" + 
            "| | ___   ___ __ _| |___ _ __   ___  __ _| | __\n" + 
            "| |/ _ \\ / __/ _` | / __| '_ \\ / _ \\/ _` | |/ /\n" +
            "| | (_) | (_| (_| | \\__ \\ |_) |  __/ (_| |   < \n" +
            "|_|\\___/ \\___\\__,_|_|___/ .__/ \\___|\\__,_|_|\\_\\\n" +
            "                        | |                    \n" +
            "                        |_|                    \n" +
            "------------------------------------------------\n" +
            "1) Connect to host\n" +
            "2) Start server\n" +
            "9) Exit\n"
        );
    }
}

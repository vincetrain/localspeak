package com.localspeak;

import java.util.Scanner;
import java.io.FileReader;

public class App {

    final static Scanner reader = new Scanner(System.in);

   
    final static String EXITCONDITION = "9";

    public static void main( String[] args ) {
        String userIn;
        // Loop printing main menu until user wishes to exit
        do {
            printMenu();
            userIn = reader.nextLine();
            if (userIn.equals("1")) {
                // do something
            }
            else if (userIn.equals("2")) {
                // do something else
            }
        } while(!userIn.equals(EXITCONDITION));

        reader.close();
        System.exit(0);
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
            "1) Connect via IPv4\n" +
            "2) Settings\n" +
            "9) Exit\n"
        );
    }
}

package com.localspeak;

import com.localspeak.util.*;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{

    static Scanner reader;

    final static String EXITCONDITION = "9";

    public static void main( String[] args )
    {
        String userIn;
        // Loop printing main menu until user wishes to exit
        do {
            printMenu();
            reader = new Scanner(System.in);
            userIn = reader.nextLine();
        } while(!userIn.equals(EXITCONDITION));
        System.exit(0);
        
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

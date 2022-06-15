package com.localspeak.utility;

import java.net.Inet4Address;
import java.util.Scanner;

public class MainUtil {
    static Scanner reader;

    /**
     * Gets IPv4 address to connect to
     * 
     * @return Inet4Address
     * @throws UnknownHostException
     */
    public static Inet4Address getInet4Address() {
        Inet4Address ip = null;
        String userInput = "";
        do {
            System.out.println("Enter an IPv4 Address: ");
            reader = new Scanner(System.in);
            userInput = reader.nextLine();
        } while(!confirm(userInput));
        // If address is not a valid address, then recursively call function to get another address
        try {
            ip = (Inet4Address) Inet4Address.getByName(userInput);
        }
        catch (Exception e) {
            System.out.println("That address is not a valid IPv4 address.");
            ip = getInet4Address();
        }
        return ip;
    }

    /**
     * Gets specified port to connect to
     * 
     * @return int port number
     */
    public static int getPort() {
        int port = 17763;
        try {
            do {
                System.out.println("Enter a port to connect to: ");
                reader = new Scanner(System.in);
                port = reader.nextInt();
            } while(!confirm(""+port));
            if (port < 1024 || port > 65536) {
                port = getPort();
            }
        }
        catch (Exception e) {
            System.out.println("Invalid port.");
        }
        return port;
    }

    /**
     * Confirms with user that userInput is correct and returns t/f accordingly
     * 
     * @param userInput
     * @return true/false depending on user confirmation
     */
    public static boolean confirm(String userInput) {
        System.out.print("Confirm \"" + userInput + "\"? Y/N: ");
        reader = new Scanner(System.in);
        if (reader.nextLine().equals("y")) {
            return true;
        }
        return false;
    }
}

package com.localspeak;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

/**
 * Concepts:
 * - Multithreading,
 * - Java Socket,
 * - Java Selectors (?)
 */

/**
 * Multithreaded echo server to handle multiple Socket connections to one ServerSocket
 */
public class Server {
  ServerSocket serverSocket;

  /**
   * Executed when running this file standalone.
   * @param args Takes an argument specifying the port to be used. Defaults to 7763 if unspecified.
   */
  public static void main(String[] args) {
    int port = 7763;
    ServerSocket serverSocket = null;
    Socket clientSocket = null;

    try {
      port = Integer.parseInt(args[0]);
    }
    catch (Exception e) {
      System.out.println("Port not specified, defaulting to 7763.");      
    }

    try {
      serverSocket = new ServerSocket(port);
      System.out.println("Server is running...\n" +
                          "Press \"q\" to quit.");
    }
    catch (IOException e) {
      e.printStackTrace();
    }

    while (true) {
      try {
        clientSocket = serverSocket.accept();
      }
      catch (IOException e) {
        System.out.println("I/O Error: " + e);
      }
    }
  }

  /**
   * Initializes Server instance with Java's ServerSocket object.
   * @param port Integer containing desired port
   * @throws IOException
   */
  public Server(int port) throws IOException {
    serverSocket = new ServerSocket(port);

  }

  /**
   * Initializes a new thread of 
   * @param socket
   */
  public void createConnection(Socket socket) {

  }
}

class ClientThread {

}

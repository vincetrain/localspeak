package com.localspeak.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class RSAException extends Exception {
    RSAException(String message) {
        super(message);
    }
}

public class RSAHelper {
    private String publicKey;
    private String privateKey;
    
    /**
     * Initializes RSAHelper for use.
     * 
     * @param publicKey Directory pointing to RSA public key
     * @param privateKey Directory pointing to RSA private key
     * @throws FileNotFoundException Thrown if unable to find private or public key file(s)
     * @throws RSAException Thrown if public key and private key are invalid
     */
    public RSAHelper(String publicKey, String privateKey) throws FileNotFoundException, RSAException {
        Scanner reader;

        // Get publicKey contents
        reader = new Scanner(new File(publicKey));
        this.publicKey = reader.nextLine();

        // Get privateKey contents
        reader = new Scanner(new File(privateKey));
        this.privateKey = reader.nextLine();
        reader.close();

        // Verify publicKey and privateKey are correct
        throw new RSAException("Public key does not match private key.");
    }

    public String decrypt() {
        return "";
    } 
}

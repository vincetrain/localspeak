package com.localspeak.model;

import java.util.Date;

// Consider implementing toBytes to convert object to Byte array?

public class Message {
    String content;
    Date date;
    String publicKey;

    /**
     * Initializes Message object without RSA support.
     * @param content String content of the message
     * @param date Date containing when the message was sent
     */
    public Message(String content, Date date) {
        this.content = content;
        this.date = date;
        publicKey = null;
    }

    /**
     * Initializes Message object.
     * @param content String content of the message
     * @param date Date containing when the message was sent
     * @param publicKey Associated publicKey to be used for decryption
     */
    public Message(String content, Date date, String publicKey) {
        this.content = content;
        this.date = date;
        this.publicKey = publicKey;
        // Perform RSA encryption
    }

    /**
     * Returns date of when the Message was sent.
     * @return date of when the message was sent
     */
    public String getDate() {
        return date.toString();
    }

    /**
     * Returns content of message.
     * @return content of message
     */
    public String getContent() {
        if (publicKey != null) {
            // Perform RSA decryption
        } 
        return content; // Returns content; may need handle special symbols like "\"
    }

    public String toString() {
        return (
            publicKey != null ? 
                "[ content: " +  content + ", date: " + date.toString() + ", publicKey: " + publicKey + " ]" 
                : 
                "[ content: " + content + ", date: " + date.toString() + " ]"
        );
    }
}

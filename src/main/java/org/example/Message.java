package org.example;
import java.time.LocalDateTime;

public class Message {
    private User sender;
    private User recipient;
    private String message;
    LocalDateTime timestamp;

    Message(User sender, User recipient, String message) {
        this.sender = sender;
        this.recipient = recipient;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public User getSender() {
        return sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public String getMessage() {
        return message;
    }

    public void display(){
        String greenColor = "\u001B[32m";
        String defaultColor = "\u001B[0m";
        System.out.println("Timestamp: " + timestamp);
        System.out.print("From " + sender.getName());
        System.out.println(" to " + recipient.getName() + ": " + greenColor + message + defaultColor);
    }
}


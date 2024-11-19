package org.example;
import java.util.*;

public class ChatServer {
    List<User> registeredUsers;
    private Map<User, User> blockMap = new HashMap<>();

    ChatServer() {
        registeredUsers = new ArrayList<>();
    }

    public void registerUser(User user){
        registeredUsers.add(user);
    }

    public void unregisterUser(User user){
        registeredUsers.remove(user);
    }

    public void sendMessage(User sender, User recipient, String message){
        if (registeredUsers.contains(recipient)){
            if (blockMap.containsKey(recipient) && blockMap.get(recipient) == sender) {
                System.out.println("Sorry, " + sender.getName() + ". You are blocked by this user.");
                Message currMessage = new Message(sender, recipient, message + " (Blocked)");
                sender.receiveMessage(currMessage);
                sender.autoSave();
                recipient.autoSave();
            }
            else {
                Message currMessage = new Message(sender, recipient, message);
                sender.receiveMessage(currMessage);
                recipient.receiveMessage(currMessage);
                sender.autoSave();
                recipient.autoSave();
            }
        }
        else {
            System.out.println("Sorry, " + sender.getName() + ". The recipient is unregistered.");
        }
    }

    public void blockMessagesFrom(User blocker, User blockedUser){
        blockMap.put(blocker, blockedUser);
    }
}
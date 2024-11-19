package org.example;
import java.util.*;

public class ChatHistory implements IterableByUser {
    List<Message> messages;

    ChatHistory() {
        messages = new ArrayList<>();
    }

    public void addMessage(Message m) {
        messages.add(m);
    }

    public List<Message> getMessages() {
        return messages;
    }

    public Message getLastSent() {
        if (!messages.isEmpty()) return messages.get(messages.size() - 1);
        return null;
    }

    public void displayMessagesWith(User user1, User user2) {
        System.out.println(user1.getName() + "'s chat history with " + user2.getName() + ":");
        for (Message message : messages) {
            if (message.getSender().equals(user2) || message.getRecipient().equals(user2)) {
                message.display();
            }
        }
    }

    @Override
    public Iterator<Message> iterator(User userToSearchWith){
        return new SearchMessagesByUser(messages, userToSearchWith);
    }
}


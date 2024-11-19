package org.example;
import java.util.*;

public class SearchMessagesByUser implements Iterator<Message>{
    private int index = 0;
    private final List<Message> filteredMessages;

    public SearchMessagesByUser(List<Message> messages, User userToSearchWith) {
        filteredMessages = new ArrayList<>();
        for (Message message : messages) {
            if (message.getSender().equals(userToSearchWith) || message.getRecipient().equals(userToSearchWith)) {
                filteredMessages.add(message);
            }
        }
    }

    @Override
    public boolean hasNext() {
        return (index < filteredMessages.size());
    }

    @Override
    public Message next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        else {
            return filteredMessages.get(index++);
        }
    }
}

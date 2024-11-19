package org.example;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //initiation
        ChatServer serverOne = new ChatServer();
        User Sean = new User("Sean", serverOne);
        User Gio = new User("Gio", serverOne);
        User Han = new User("Han", serverOne);
        serverOne.registerUser(Sean);
        serverOne.registerUser(Gio);
        serverOne.registerUser(Han);
        List<User> gioAndHan = new ArrayList<>();
        gioAndHan.add(Han);
        gioAndHan.add(Gio);

        //sending and receiving messages, undo sent message and display chat history
        Sean.sendMessage(Han, "Hey, girl~");
        Han.sendMessage(Sean, "Hey, boy lol");
        Sean.sendMessage(gioAndHan, "Wanna go to a movie?");
        Han.sendMessage(Sean, "Sure. When?");

        displayMessagesByUser(Sean, Han);
        displayMessagesByUser(Sean, Gio);
    }

    public static void displayMessagesByUser(User user1, User user2) {
        Iterator<Message> messageIterator = new SearchMessagesByUser(user1.getChatHistory().getMessages(), user2);
        System.out.println(user1.getName() + "'s chat history with " + user2.getName() + ":");
        while (messageIterator.hasNext()) {
            Message message = messageIterator.next();
            message.display();
        }
        System.out.println();
    }
}
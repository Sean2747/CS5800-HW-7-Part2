package org.example;
import java.util.*;

public class User implements IterableByUser {
    private String name;
    private ChatServer chatServer;
    private ChatHistory chatHistory;
    private Stack<MessageMemento> mementos;

    User(String name, ChatServer chatServer) {
        this.name = name;
        this.chatServer = chatServer;
        chatHistory = new ChatHistory();
        mementos = new Stack<>();
    }

    public String getName() {
        return name;
    }

    public void sendMessage(User recipient, String message){
        chatServer.sendMessage(this, recipient, message);
    }

    public void sendMessage(List<User> recipients, String message){
        for (User recipient : recipients){
            sendMessage(recipient, message);
        }
    }

    public void receiveMessage(Message message){
        chatHistory.addMessage(message);
    }

    public void blockUser(User blockedUser){
        chatServer.blockMessagesFrom(this, blockedUser);
    }

    public void displayChatHistoryWith(User user){
        chatHistory.displayMessagesWith(this, user);
    }

    public void autoSave(){
        mementos.push(new MessageMemento(chatHistory));
    }

    public void replaceCurrentChatHisotryWithBackUp(ChatHistory backUp){
        this.chatHistory = backUp;
    }

    public void theOtherUserUndo(){
        mementos.pop();
        ChatHistory backUp = mementos.pop().getBackUp();
        replaceCurrentChatHisotryWithBackUp(backUp);
    }

    public void undoLastSent(){
        ChatHistory latest = mementos.pop().getBackUp();
        ChatHistory backUp = mementos.pop().getBackUp();
        replaceCurrentChatHisotryWithBackUp(backUp);
        latest.getLastSent().getRecipient().theOtherUserUndo();
        System.out.println(this.getName() + ", the last message is undone.");
    }

    public ChatHistory getChatHistory(){
        return chatHistory;
    }

    @Override
    public Iterator<Message> iterator(User userToSearchWith) {
        return chatHistory.iterator(userToSearchWith);
    }
}
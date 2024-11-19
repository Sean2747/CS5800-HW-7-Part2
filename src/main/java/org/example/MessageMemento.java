package org.example;

public class MessageMemento {
    private ChatHistory chatHistoryBackUp;

    MessageMemento(ChatHistory currChatHistory) {
        copy(currChatHistory);
    }

    public ChatHistory getBackUp() {
        return chatHistoryBackUp;
    }

    private ChatHistory copy(ChatHistory currChatHistory) {
        chatHistoryBackUp = new ChatHistory();
        for (Message message : currChatHistory.getMessages()) {
            Message copiedMessage = new Message(message.getSender(), message.getRecipient(), message.getMessage());
            chatHistoryBackUp.addMessage(copiedMessage);
        }
        return chatHistoryBackUp;
    }
}

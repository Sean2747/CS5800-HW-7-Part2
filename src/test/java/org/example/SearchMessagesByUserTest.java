package org.example;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class SearchMessagesByUserTest {

    private ChatServer server = new ChatServer();
    private User Sean = new User("Sean", server);
    private User Han = new User("Han", server);
    private User Gio = new User("Gio", server);


    @Test
    public void iteratorFiltersMessagesByUserTest() {
        server.registerUser(Sean);
        server.registerUser(Han);
        server.registerUser(Gio);
        Sean.sendMessage(Han, "Hey Han!");
        Gio.sendMessage(Sean, "Hey Sean!");
        Han.sendMessage(Sean, "How are you?");

        SearchMessagesByUser iterator = new SearchMessagesByUser(Sean.getChatHistory().getMessages(), Han);
        List<Message> filteredMessages = new ArrayList<>();
        while (iterator.hasNext()) {
            filteredMessages.add(iterator.next());
        }
        assertEquals(2, filteredMessages.size());
    }

    @Test
    public void testIteratorHandlesEmptyChatHistory() {
        server.registerUser(Sean);
        SearchMessagesByUser iterator = new SearchMessagesByUser(new ArrayList<>(), Sean);
        assertFalse(iterator.hasNext());
    }
}
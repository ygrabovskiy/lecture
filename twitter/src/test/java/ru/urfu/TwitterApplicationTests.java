package ru.urfu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.urfu.model.Message;
import ru.urfu.model.User;
import ru.urfu.storage.MessageStorage;
import ru.urfu.storage.UserStorage;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TwitterApplicationTests {

    @Autowired
    private UserStorage users;

    @Autowired
    private MessageStorage messages;

    @Test
    public void checkAutowiring() {
        assertNotNull(users);
        assertNotNull(messages);
    }

    @Test
    public void userAdd() {
        User someUser = new User("tester", "ololo@naumen.ru", "123456");
        assertNull(someUser.getId());
        someUser = users.addUser(someUser);
        assertNotNull(someUser.getId());
    }

    @Test
    public void messageAdd() {
        User someUser = new User("tester", "ololo@naumen.ru", "123456");
        someUser = users.addUser(someUser);

        Message unsaved = new Message(someUser, new Date(), "Hi there, guys!");
        assertNull(unsaved.getId());
        Message saved = messages.addMessage(unsaved);
        assertNotNull(saved.getId());
        assertTrue(messages.getMessages().contains(saved));
    }
}

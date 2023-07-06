package com.example.socialnet.service;

import com.example.socialnet.SocialNetworkingRunner;
import com.example.socialnet.model.Command;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CommandExecutorTest {

    @Autowired
    private CommandExecutor executor;

    @MockBean
    SocialNetworkingRunner runner;

    @BeforeAll
    public void setup() {
        postAMessage("Alice", "I love the weather today");
        postAMessage("Bob", "Damn! We lost!");
        postAMessage("Bob", "Good game though.");
        postAMessage("Charlie", "I'm in New York today! Anyone wants to have a coffee?");
    }

    @Test
    void givenAReadingCommand_thenTheMessageIsReturned() {
        Command command = Command.builder()
                .username("Alice")
                .type(Command.CommandType.READING)
                .build();


        List<String> output = executor.execute(command);
        assertEquals("I love the weather today (less than 1 second ago)", output.get(0));
    }

    @Test
    void givenAReadingCommand_thenMultipleMessagesAreReturnedOrderByTsDesc() {
        Command command = Command.builder()
                .username("Bob")
                .type(Command.CommandType.READING)
                .build();


        List<String> output = executor.execute(command);
        assertEquals("Good game though. (less than 1 second ago)", output.get(0));
        assertEquals("Damn! We lost! (less than 1 second ago)", output.get(1));
    }

    @Test
    void givenAWallCommand_thenTheMessagesAreCorrect(){
        Command command = Command.builder()
                .username("Charlie")
                .type(Command.CommandType.WALL)
                .build();

        List<String> output = executor.execute(command);
        assertEquals(1, output.size());
        assertEquals("Charlie - I'm in New York today! Anyone wants to have a coffee? (less than 1 second ago)", output.get(0));
    }

    private void postAMessage(String username, String message) {
        executor.execute(Command.builder()
                .username(username)
                .type(Command.CommandType.POSTING)
                .message(message)
                .build());
    }

}
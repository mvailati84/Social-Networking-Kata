package com.example.socialnet.service;

import com.example.socialnet.SocialNetworkingRunner;
import com.example.socialnet.model.Command;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommandExecutorTest {

    @Autowired
    private CommandExecutor executor;

    @MockBean
    SocialNetworkingRunner runner;

    @Test
    void givenAPostingMessage_thenTheMessageIsReturned() {
        Command alicePosting = Command.builder()
                .username("Alice")
                .type(Command.CommandType.POSTING)
                .message("I love the weather today")
                .build();

        assertEquals("", executor.execute(alicePosting));

        Command aliceReading = Command.builder()
                .username("Alice")
                .type(Command.CommandType.READING)
                .build();

        assertEquals("I love the weather today (less than 1 second ago)", executor.execute(aliceReading));
    }

}
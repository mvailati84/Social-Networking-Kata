package com.example.socialnet.service;

import com.example.socialnet.SocialNetworkingRunner;
import com.example.socialnet.model.Command;
import com.example.socialnet.repo.FollowersRepo;
import com.example.socialnet.repo.UserMessageRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CommandExecutorTest {

    @Autowired
    private CommandExecutor executor;

    @Autowired
    private UserMessageRepo userMessageRepo;
    @Autowired
    private FollowersRepo followersRepo;

    @MockBean
    SocialNetworkingRunner runner;

    @BeforeEach
    public void setup() {
        postAMessage("Alice", "I love the weather today");
        postAMessage("Bob", "Damn! We lost!");
        postAMessage("Bob", "Good game though.");
        postAMessage("Charlie", "I'm in New York today! Anyone wants to have a coffee?");
    }

    @AfterEach void teardown(){
        userMessageRepo.deleteAll();
        followersRepo.deleteAll();
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

    @Test
    void givenAFollower_whenWall_thenMessagesAreCorrect() {
        followAUser("Charlie", "Alice");

        Command command = Command.builder()
                .username("Charlie")
                .type(Command.CommandType.WALL)
                .build();

        List<String> output = executor.execute(command);
        assertEquals(2, output.size());
        assertEquals("Charlie - I'm in New York today! Anyone wants to have a coffee? (less than 1 second ago)", output.get(0));
        assertEquals("Alice - I love the weather today (less than 1 second ago)", output.get(1));
    }

    @Test
    void givenTwoFollowers_whenWall_thenMessagesAreCorrect() {
        followAUser("Charlie", "Alice");
        followAUser("Charlie", "Bob");

        Command command = Command.builder()
                .username("Charlie")
                .type(Command.CommandType.WALL)
                .build();

        List<String> output = executor.execute(command);
        assertEquals(4, output.size());
        assertEquals("Charlie - I'm in New York today! Anyone wants to have a coffee? (less than 1 second ago)", output.get(0));
        assertEquals("Bob - Good game though. (less than 1 second ago)", output.get(1));
        assertEquals("Bob - Damn! We lost! (less than 1 second ago)", output.get(2));
        assertEquals("Alice - I love the weather today (less than 1 second ago)", output.get(3));
    }

    private void followAUser(String username, String userToFollow) {
        Command command = Command.builder()
                .username(username)
                .type(Command.CommandType.FOLLOWING)
                .message(userToFollow)
                .build();
        executor.execute(command);
    }

    private void postAMessage(String username, String message) {
        executor.execute(Command.builder()
                .username(username)
                .type(Command.CommandType.POSTING)
                .message(message)
                .build());
    }

}
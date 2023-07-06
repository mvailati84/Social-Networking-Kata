package com.example.socialnet.service;

import com.example.socialnet.model.Command;
import com.example.socialnet.model.Command.CommandType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommandParserTest {

    private CommandParser parser;

    @BeforeEach
    public void setup(){
        parser = new CommandParser();
    }

    @Test
    void whenLineIsExit_thenCommandIsExit() {
        Command command = parser.parse("exit");
        assertTrue(command.isExit());
    }

    @Test
    void whenPostingMessage_thenCommandIsPosting() {
        Command command = parser.parse("Alice -> I love the weather today");

        assertEquals(CommandType.POSTING, command.getType());
        assertEquals("Alice", command.getUsername());
        assertEquals("I love the weather today", command.getMessage());

    }
    @Test
    void whenReadingMessage_thenCommandIsReading() {
        Command command = parser.parse("Alice");

        assertEquals(CommandType.READING, command.getType());
        assertEquals("Alice", command.getUsername());
    }

    @Test
    void whenWallMessage_thenCommandIsWall() {
        Command command = parser.parse("Alice wall");

        assertEquals(CommandType.WALL, command.getType());
        assertEquals("Alice", command.getUsername());
    }

    @Test
    void whenFollowingMessage_thenCommandIsFollowing() {
        Command command = parser.parse("Alice follows Bob");

        assertEquals(CommandType.FOLLOWING, command.getType());
        assertEquals("Alice", command.getUsername());
        assertEquals("Bob", command.getMessage());
    }

}
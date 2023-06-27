package com.example.socialnet.service;

import com.example.socialnet.model.Command;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

}
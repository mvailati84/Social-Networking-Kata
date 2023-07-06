package com.example.socialnet.service;

import com.example.socialnet.model.Command;
import org.springframework.stereotype.Service;

@Service
public class CommandParser {

    public Command parse(String line) {
        String[] message = line.trim().split(" ", 3);

        if (isAPostingMessage(message)) {
            return Command.builder()
                    .type(Command.CommandType.POSTING)
                    .username(message[0])
                    .message(message[2])
                    .build();
        }

        if (isAReadingMessage(message)) {
            return Command.builder()
                    .type(Command.CommandType.READING)
                    .username(message[0])
                    .build();
        }

        if (isAFollowingMessage(message)) {
            return Command.builder()
                    .type(Command.CommandType.FOLLOWING)
                    .username(message[0])
                    .message(message[2])
                    .build();
        }

        if (isAWallMessage(message)) {
            return Command.builder()
                    .type(Command.CommandType.WALL)
                    .username(message[0])
                    .build();
        }

        if (isAnExistMessage(message)){
            return Command.builder()
                    .type(Command.CommandType.EXIT)
                    .build();
        }

        return null;
    }

    private boolean isAWallMessage(String[] message) {
        return message.length == 2 && "wall".equalsIgnoreCase(message[1]);
    }

    private boolean isAFollowingMessage(String[] message) {
        return message.length == 3 && "follows".equalsIgnoreCase(message[1]);
    }

    private boolean isAReadingMessage(String[] message) {
        return message.length == 1 && !"exit".equalsIgnoreCase(message[0]);
    }

    private boolean isAnExistMessage(String[] message) {
        return message.length == 1 && "exit".equalsIgnoreCase(message[0]);
    }

    private boolean isAPostingMessage(String[] message) {
        return message.length == 3 && "->".equalsIgnoreCase(message[1]);
    }
}

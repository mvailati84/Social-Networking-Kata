package com.example.socialnet.model;

import lombok.Builder;

@Builder
public class Command {

    private final CommandType type;
    private final String user;
    private final String argument;


    public boolean isExit() {
        return type == CommandType.EXIT;
    }

    public enum CommandType {
        POSTING,
        READING,
        FOLLOWING,
        WALL,
        EXIT;
    }
}

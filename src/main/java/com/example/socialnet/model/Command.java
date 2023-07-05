package com.example.socialnet.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Command {

    private final CommandType type;
    private final String username;
    private final String message;

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

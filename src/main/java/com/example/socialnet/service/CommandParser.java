package com.example.socialnet.service;

import com.example.socialnet.model.Command;
import org.springframework.stereotype.Service;

@Service
public class CommandParser {


    public Command parse(String line) {
        String input = line.trim();

        if ("exit".equalsIgnoreCase(input)){
            return Command.builder()
                    .type(Command.CommandType.EXIT)
                    .build();
        }

        return null;
    }
}

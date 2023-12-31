package com.example.socialnet;

import com.example.socialnet.model.Command;
import com.example.socialnet.service.CommandExecutor;
import com.example.socialnet.service.CommandParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
@Slf4j
public class SocialNetworkingRunner implements CommandLineRunner {

    final CommandParser parser;
    final CommandExecutor executor;

    public SocialNetworkingRunner(CommandParser parser, CommandExecutor executor) {
        this.parser = parser;
        this.executor = executor;
    }

    @Override
    @SuppressWarnings("squid:S106")
    public void run(String... args) throws Exception {
        log.info("Starting Social Networking using " + String.join(",", args));

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            Command command = parser.parse(scanner.nextLine());

            if (command.isExit()){
                System.exit(0);
            }

            List<String> output = executor.execute(command);
            output.forEach(System.out::println);
        }

    }
}

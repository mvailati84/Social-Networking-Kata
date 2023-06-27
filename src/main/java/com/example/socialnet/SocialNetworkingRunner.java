package com.example.socialnet;

import com.example.socialnet.model.Command;
import com.example.socialnet.service.CommandParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@Slf4j
public class SocialNetworkingRunner implements CommandLineRunner {

    final CommandParser parser;

    public SocialNetworkingRunner(CommandParser parser) {
        this.parser = parser;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Social Networking starts with args " + String.join(",", args));

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            Command command = parser.parse(scanner.nextLine());

            if (command.isExit()){
                System.exit(0);
            }
        }

    }
}

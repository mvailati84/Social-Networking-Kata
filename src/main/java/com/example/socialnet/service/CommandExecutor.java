package com.example.socialnet.service;

import com.example.socialnet.model.Command;
import com.example.socialnet.repo.FollowersRepo;
import com.example.socialnet.model.UserMessage;
import com.example.socialnet.repo.UserMessageRepo;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommandExecutor {

    public static final String EMPTY = "";
    private final UserMessageRepo msgRepo;
    private final FollowersRepo followersRepo;


    public CommandExecutor(UserMessageRepo msgRepo, FollowersRepo followers) {
        this.msgRepo = msgRepo;
        this.followersRepo = followers;
    }

    public String execute(Command command) {
        switch (command.getType()) {
            case POSTING:
                return post(command.getUsername(), command.getMessage());
            case READING:
                return read(command.getUsername());
            case FOLLOWING:
                return follow(command.getUsername(), command.getMessage());
            case WALL:
                return wall(command.getUsername());
            default:
                return EMPTY;
        }
    }

    private String wall(String username) {
        Collection<String> userList = followersRepo.user(username).getFollowing();
        userList.add(username);

        return printMessages(msgRepo.findByUsernameContainingOrderByTimeDesc(userList));
    }

    private String follow(String username, String userToFollow) {
        followersRepo.user(username).follows(userToFollow);
        return EMPTY;
    }

    private String read(String user) {
        return printMessages(msgRepo.findByUsernameOrderByTimeDesc(user));
    }

    private static String printMessages(List<UserMessage> messages) {
        return messages.stream()
                .map(UserMessage::print)
                .collect(Collectors.joining("\n"));
    }

    private String post(String user, String message) {
        msgRepo.save(new UserMessage(user, message));
        return EMPTY;
    }

}

package com.example.socialnet.service;

import com.example.socialnet.model.Command;
import com.example.socialnet.model.UserMessage;
import com.example.socialnet.repo.FollowersRepo;
import com.example.socialnet.repo.UserMessageRepo;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class CommandExecutor {

    private final UserMessageRepo msgRepo;
    private final FollowersRepo followersRepo;


    public CommandExecutor(UserMessageRepo msgRepo, FollowersRepo followers) {
        this.msgRepo = msgRepo;
        this.followersRepo = followers;
    }

    public List<String> execute(Command command) {
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
                return Collections.emptyList();
        }
    }

    private List<String> wall(String username) {
        Collection<String> userList = followersRepo.findByUser(username).getFollowing();
        userList.add(username);

        return msgRepo.findByUsernameInOrderByTimeDesc(userList).stream()
                .map(UserMessage::printMessageWithUserName)
                .toList();
    }

    private List<String> follow(String username, String userToFollow) {
        followersRepo.findByUser(username).follows(userToFollow);
        return Collections.emptyList();
    }

    private List<String> read(String user) {
        return msgRepo.findByUsernameOrderByTimeDesc(user).stream()
                .map(UserMessage::printMessage)
                .toList();
    }

    private List<String> post(String user, String message) {
        msgRepo.save(new UserMessage(user, message));
        return Collections.emptyList();
    }

}

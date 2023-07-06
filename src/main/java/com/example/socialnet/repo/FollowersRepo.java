package com.example.socialnet.repo;

import com.example.socialnet.model.Follower;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class FollowersRepo {
    HashMap<String, Follower> followers = new HashMap<>();

    public Follower findByUser(String user) {
        return followers.computeIfAbsent(user, Follower::new);
    }

    public void deleteAll() {
        followers = new HashMap<>();
    }
}

package com.example.socialnet.repo;

import com.example.socialnet.model.Follower;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Repository
public class FollowersRepo {

    HashMap<String, Follower> followers;

    public Follower user(String user) {
        Follower out = followers.get(user);

        if (out == null){
            out = new Follower(user);
            followers.put(user, out);
        }

        return out;
    }
}

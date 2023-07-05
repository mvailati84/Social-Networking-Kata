package com.example.socialnet.model;


import java.util.*;

public class Follower {
    String name;

    Set<String> following = new HashSet<>();

    public Follower (String username){
        this.name = username;
    }

    public void follows(String userToFollow) {
        following.add(userToFollow);
    }

    public Collection<String> getFollowing(){
        return new HashSet<>(following);
    }
}

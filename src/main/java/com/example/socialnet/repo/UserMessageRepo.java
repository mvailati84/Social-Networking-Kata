package com.example.socialnet.repo;


import com.example.socialnet.model.UserMessage;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface UserMessageRepo extends CrudRepository<UserMessage, Long> {
    List<UserMessage> findByUsernameOrderByTimeDesc(String user);
    List<UserMessage> findByUsernameInOrderByTimeDesc(Collection<String> following);
}

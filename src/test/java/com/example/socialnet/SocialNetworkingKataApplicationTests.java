package com.example.socialnet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SocialNetworkingKataApplicationTests {

    @MockBean
    SocialNetworkingRunner runner;

    @Test
    @SuppressWarnings("squid:S2699")
    void contextLoads() {
    }

}

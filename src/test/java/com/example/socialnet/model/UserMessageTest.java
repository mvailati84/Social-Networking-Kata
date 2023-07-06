package com.example.socialnet.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserMessageTest {

    @Test
    void givenATimeLessThanAMinute_thenTheMessageIsCorrect() {
        UserMessage msg = new UserMessage("Alice", "I love this weather");
        msg.setTime(System.currentTimeMillis() - 30 * 1000 );

        assertEquals("I love this weather (30 seconds ago)", msg.printMessage());
    }
    @Test
    void givenATimeOfOneMinute_thenTheMessageIsCorrect() {
        UserMessage msg = new UserMessage("Alice", "I love this weather");
        msg.setTime(System.currentTimeMillis() - 65 * 1000 );

        assertEquals("I love this weather (1 minute ago)", msg.printMessage());
    }
    @Test
    void givenATimeLessThanAnHour_thenTheMessageIsCorrect() {
        UserMessage msg = new UserMessage("Alice", "I love this weather");
        msg.setTime(System.currentTimeMillis() - 60 * 15 * 1000 );

        assertEquals("I love this weather (15 minutes ago)", msg.printMessage());
    }

    @Test
    void givenATimeLessThanADay_thenTheMessageIsCorrect() {
        UserMessage msg = new UserMessage("Alice", "I love this weather");
        msg.setTime(System.currentTimeMillis() - 60 * 60 * 5 * 1000 );

        assertEquals("I love this weather (5 hours ago)", msg.printMessage());
    }

    @Test
    void givenATimeGreaterThanADay_thenTheMessageIsCorrect() {
        UserMessage msg = new UserMessage("Alice", "I love this weather");
        msg.setTime(System.currentTimeMillis() - 60 * 60 * 26 * 1000 );

        assertEquals("I love this weather (1 day ago)", msg.printMessage());
    }

    @Test
    void givenATimeGreaterThanADay_thenTheMessageWithUserIsCorrect() {
        UserMessage msg = new UserMessage("Alice", "I love this weather");
        msg.setTime(System.currentTimeMillis() - 60 * 60 * 26 * 1000 );

        assertEquals("Alice - I love this weather (1 day ago)", msg.printMessageWithUserName());
    }
}
package com.example.socialnet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class UserMessage {
    private static final double A_SECOND = 1000;
    private static final double A_MINUTE = A_SECOND * 60;
    private static final double AN_HOUR = A_MINUTE * 60;
    private static final double A_DAY = AN_HOUR * 24;
    @Id
    @GeneratedValue
    private Long id;
    private final String username;
    private final String message;
    private long time = System.currentTimeMillis();

    public String printMessage(){
        return message + " " + printElapsed();
    }

    public String printMessageWithUserName(){
        return username + " - " + printMessage();
    }

    private String printElapsed() {
        long elapsed = System.currentTimeMillis() - time;

        if (elapsed < A_SECOND){
            return "(less than 1 second ago)";
        }else if (elapsed < A_MINUTE){
            return printElapsedTime(elapsed, A_SECOND, "second");
        } else if (elapsed < AN_HOUR) {
            return printElapsedTime(elapsed, A_MINUTE, "minute");
        } else if (elapsed < A_DAY) {
            return printElapsedTime(elapsed, AN_HOUR, "hour");
        }
        return printElapsedTime(elapsed, A_DAY, "day");
    }

    private String printElapsedTime(long elapsed, double timeUnit, String timeLabel) {
        long minutesElapsed = Math.round(elapsed / timeUnit);
        return "(" + minutesElapsed + " " + timeLabel + plural(minutesElapsed) + " ago)";
    }


    private String plural(double num) {
        return num > 1 ? "s" : "";
    }

}

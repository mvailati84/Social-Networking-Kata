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
    private static final double AN_HOUR = A_SECOND * 60 * 60;
    private static final double A_DAY = AN_HOUR * 24;
    @Id
    @GeneratedValue
    private Long id;
    private final String username;
    private final String message;
    private final long time = System.currentTimeMillis();

    public String print(){
        return message + " " + printElapsed();
    }

    private String printElapsed() {
        long elapsed = System.currentTimeMillis() - time;

        if (elapsed < A_SECOND){
            return "(less than 1 second ago)";
        }else if (elapsed < AN_HOUR){
            double hoursElapsed = elapsed / AN_HOUR;
            return "(" + hoursElapsed + " hour" + plural(hoursElapsed) + " ago)";
        }

        double daysElapsed = elapsed / A_DAY;
        return "(" + daysElapsed + " day" + plural(daysElapsed) + " ago)";
    }

    private String plural(double num) {
        return num > 1 ? "s" : "";
    }

}

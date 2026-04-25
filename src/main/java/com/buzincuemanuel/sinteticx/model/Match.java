package com.buzincuemanuel.sinteticx.model;


import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Match {

    private Long id;
    private String location;
    private String type;
    private LocalDate date;
    private LocalTime time;
    private User createdBy;
    private List<User> participants = new ArrayList<>();

    public void addParticipant(User user) {
        if (!participants.contains(user)) {
            participants.add(user);
        }
    }

}

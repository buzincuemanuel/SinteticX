package com.buzincuemanuel.sinteticx.model;


import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

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

}

package com.buzincuemanuel.sinteticx.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MatchDTO {

    private Long id;
    private String location;
    private String type;
    private LocalDate date;
    private LocalTime time;
    private UserDTO createdBy;
    private List<UserDTO> participants;
}
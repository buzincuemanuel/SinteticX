package com.buzincuemanuel.sinteticx.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MatchDTO {

    @NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message = "Match type is required")
    private String type;

    @NotEmpty(message = "The date of the match is required")
    @FutureOrPresent(message = "The date of the match can't be in the past")
    private LocalDate date;

    @NotEmpty(message = "The time of the match is required")
    private LocalTime time;




}

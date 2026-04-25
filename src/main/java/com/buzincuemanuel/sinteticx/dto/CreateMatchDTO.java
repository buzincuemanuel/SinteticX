package com.buzincuemanuel.sinteticx.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateMatchDTO {

    @NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message = "Match type is required")
    private String type;

    @NotNull(message = "Match date is required")
    @FutureOrPresent(message = "Match date cannot be in the past")
    private LocalDate date;

    @NotNull(message = "Match time is required")
    private LocalTime time;
}
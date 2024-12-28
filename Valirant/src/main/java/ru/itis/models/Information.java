package ru.itis.models;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Information {
    private int id;
    private String text;
    private LocalDate date;
    private int time_to_read;
}

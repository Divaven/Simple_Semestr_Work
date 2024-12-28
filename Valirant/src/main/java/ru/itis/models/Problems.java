package ru.itis.models;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Problems {
    private int id;
    private String description;
    private String answer;
    private LocalDate date;
}

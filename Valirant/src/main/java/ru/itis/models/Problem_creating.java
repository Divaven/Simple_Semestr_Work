package ru.itis.models;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Problem_creating {
    private int id;
    private int problem_id;
    private int test_id;
}

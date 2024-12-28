package ru.itis.models;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Test {
    private int id;
    private String problem;
    private int max_score;
    private int time;
}

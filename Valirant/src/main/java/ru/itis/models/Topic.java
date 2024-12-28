package ru.itis.models;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Topic {
    private int id;
    private String topic;
    private int info_id;
}

package com.epam.mentoring.poll.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Result {

    private long id;

    private String explanation;

    private int minScore;

    private int maxScore;
}

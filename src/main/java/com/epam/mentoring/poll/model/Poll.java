package com.epam.mentoring.poll.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Poll {

    private long id;

    private String name;

    private String description;

    private List<Question> questions;

    private List<Result> results;
}

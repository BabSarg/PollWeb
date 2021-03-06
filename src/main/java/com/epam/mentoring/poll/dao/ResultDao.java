package com.epam.mentoring.poll.dao;


import com.epam.mentoring.poll.model.Result;

import java.util.List;

public interface ResultDao extends Dao<Result> {

    List<Result> findByPollId(long pollId);

    Result findByScore(long pollId, int score);

    Result findByScoreBetween(int weight);
}

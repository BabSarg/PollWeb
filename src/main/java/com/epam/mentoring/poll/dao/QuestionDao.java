package com.epam.mentoring.poll.dao;


import com.epam.mentoring.poll.model.Question;

import java.util.List;

public interface QuestionDao extends Dao<Question> {

    List<Question> findByPollId(long pollId);
}

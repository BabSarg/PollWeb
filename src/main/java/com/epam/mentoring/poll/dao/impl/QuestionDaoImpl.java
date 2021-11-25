package com.epam.mentoring.poll.dao.impl;

import com.epam.mentoring.poll.dao.QuestionDao;
import com.epam.mentoring.poll.dao.config.ConnectionFactory;
import com.epam.mentoring.poll.model.Question;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QuestionDaoImpl implements QuestionDao {

    private Connection connection;

    public QuestionDaoImpl(){
        this.connection = ConnectionFactory.getInstance().getConnection();
    }

    @Override
    @SneakyThrows
    public List<Question> findAll() {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from question");
        List<Question> questionList = new ArrayList<>();
        while (resultSet.next()){
            questionList.add(Question
                    .builder()
                    .id(resultSet.getLong("id"))
                    .text(resultSet.getString("text"))
                    .build());
        }
        return questionList;
    }

    @Override
    @SneakyThrows
    public Question findById(long id) {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from question where id = ?");
        preparedStatement.setLong(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            return Question
                    .builder()
                    .id(resultSet.getLong("id"))
                    .text(resultSet.getString("text"))
                    .build();
        }
        return null;
    }

    @Override
    @SneakyThrows
    public List<Question> findByPollId(long pollId) {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from question where poll_id = ?");
        preparedStatement.setLong(1,pollId);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Question> questionList = new ArrayList<>();
        while (resultSet.next()){
            questionList.add(Question
                    .builder()
                    .id(resultSet.getLong("id"))
                    .text(resultSet.getString("text"))
                    .build());
        }
        return questionList;
    }
}

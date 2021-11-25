package com.epam.mentoring.poll.dao.impl;

import com.epam.mentoring.poll.dao.AnswerDao;
import com.epam.mentoring.poll.dao.config.ConnectionFactory;
import com.epam.mentoring.poll.model.Answer;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AnswerDaoImpl implements AnswerDao {

    private Connection connection;

    public AnswerDaoImpl(){
        this.connection = ConnectionFactory.getInstance().getConnection();
    }


    @Override
    @SneakyThrows
    public List<Answer> findAll() {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from answer");
        List<Answer> answerList = new ArrayList<>();
        while (resultSet.next()){
            answerList.add(Answer
                    .builder()
                    .id(resultSet.getLong("id"))
                    .text(resultSet.getString("text"))
                    .weight(resultSet.getInt("weight"))
                    .build());
        }
        return answerList;
    }

    @Override
    @SneakyThrows
    public Answer findById(long id) {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from answer where id = ?");
        preparedStatement.setLong(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            return Answer
                    .builder()
                    .id(resultSet.getLong("id"))
                    .text(resultSet.getString("text"))
                    .weight(resultSet.getInt("weight"))
                    .build();
        }
        return null;
    }

    @Override
    @SneakyThrows
    public List<Answer> findByQuestionId(long questionId) {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from answer where question_id = ?");
        preparedStatement.setLong(1,questionId);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Answer> answerList = new ArrayList<>();
        while (resultSet.next()){
            answerList.add(Answer
                    .builder()
                    .id(resultSet.getLong("id"))
                    .text(resultSet.getString("text"))
                    .weight(resultSet.getInt("weight"))
                    .build());
        }
        return answerList;
    }
}

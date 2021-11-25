package com.epam.mentoring.poll.dao.impl;

import com.epam.mentoring.poll.dao.ResultDao;
import com.epam.mentoring.poll.dao.config.ConnectionFactory;
import com.epam.mentoring.poll.model.Result;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ResultDaoImpl implements ResultDao {

    private Connection connection;

    public ResultDaoImpl(){
        this.connection = ConnectionFactory.getInstance().getConnection();
    }


    @Override
    @SneakyThrows
    public List<Result> findAll() {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from result");
        List<Result> resultList = new ArrayList<>();
        while (resultSet.next()){
            resultList.add(Result
                    .builder()
                    .id(resultSet.getLong("id"))
                    .explanation(resultSet.getString("explanation"))
                    .minScore(resultSet.getInt("min_score"))
                    .maxScore(resultSet.getInt("max_score"))
                    .build());
        }
        return resultList;
    }

    @Override
    @SneakyThrows
    public Result findById(long id) {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from result where id = ?");
        preparedStatement.setLong(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            return Result
                    .builder()
                    .id(resultSet.getLong("id"))
                    .explanation(resultSet.getString("explanation"))
                    .minScore(resultSet.getInt("min_score"))
                    .maxScore(resultSet.getInt("max_score"))
                    .build();
        }
        return null;
    }

    @Override
    @SneakyThrows
    public List<Result> findByPollId(long pollId) {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from result where poll_id = ?");
        preparedStatement.setLong(1,pollId);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Result> resultList = new ArrayList<>();
        while (resultSet.next()){
            resultList.add(Result
                    .builder()
                    .id(resultSet.getLong("id"))
                    .explanation(resultSet.getString("explanation"))
                    .minScore(resultSet.getInt("min_score"))
                    .maxScore(resultSet.getInt("max_score"))
                    .build());
        }
        return resultList;

    }

    @Override
    @SneakyThrows
    public Result findByScore(long pollId, int score) {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from result where poll_id = ? and max_score = ?");
        preparedStatement.setLong(1,pollId);
        preparedStatement.setLong(2,score);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            return Result
                    .builder()
                    .id(resultSet.getLong("id"))
                    .explanation(resultSet.getString("explanation"))
                    .minScore(resultSet.getInt("min_score"))
                    .maxScore(resultSet.getInt("max_score"))
                    .build();
        }
        return null;
    }

    @Override
    @SneakyThrows
    public Result findByScoreBetween(int weight) {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from result where ? between min_score and max_score");
        preparedStatement.setLong(1,weight);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            return Result
                    .builder()
                    .id(resultSet.getLong("id"))
                    .explanation(resultSet.getString("explanation"))
                    .minScore(resultSet.getInt("min_score"))
                    .maxScore(resultSet.getInt("max_score"))
                    .build();
        }
        return null;

    }
}

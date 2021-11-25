package com.epam.mentoring.poll.dao.impl;

import com.epam.mentoring.poll.dao.Dao;
import com.epam.mentoring.poll.dao.config.ConnectionFactory;
import com.epam.mentoring.poll.model.Poll;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PollDaoImpl implements Dao<Poll> {

    private Connection connection;

    public PollDaoImpl(){
        this.connection = ConnectionFactory.getInstance().getConnection();
    }

    @Override
    @SneakyThrows
    public List<Poll> findAll() {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from poll");
        List<Poll> pollList = new ArrayList<>();
        while (resultSet.next()){
            pollList.add(Poll
                    .builder()
                    .id(resultSet.getLong("id"))
                    .name(resultSet.getString("name"))
                    .description(resultSet.getString("description"))
                    .build());
        }
        return pollList;
    }

    @Override
    @SneakyThrows
    public Poll findById(long id) {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from poll where id = ?");
        preparedStatement.setLong(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            return Poll
                    .builder()
                    .id(resultSet.getLong("id"))
                    .name(resultSet.getString("name"))
                    .description(resultSet.getString("description"))
                    .build();
        }
        return null;
    }
}

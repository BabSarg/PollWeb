package com.epam.mentoring.poll.dao.config;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    private static final String DRIVER_CLASS_NAME = "org.h2.Driver";
    private static final String URL = "jdbc:h2:mem:poll;" +
            "MODE=PostgreSQL;" +
            "INIT=runscript from 'classpath:/schema_init.sql';" +
            "DB_CLOSE_DELAY=-1;" +
            "DB_CLOSE_ON_EXIT=FALSE";

    private final Connection connection;
    private static ConnectionFactory connectionFactory;

    @SneakyThrows
    private ConnectionFactory(){
        Class.forName(DRIVER_CLASS_NAME);
        this.connection =  DriverManager.getConnection(URL);
    }

    public static ConnectionFactory getInstance(){
        synchronized (ConnectionFactory.class){
            if(connectionFactory == null){
                connectionFactory = new ConnectionFactory();
            }
        }
        return connectionFactory;
    }

    public Connection getConnection() {
        return connection;
    }
}

package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Config conf = new Config("src/main/resources/app.properties");
        conf.load();
        Class.forName(conf.value("job4j.datasource.driver"));
        try (Connection connection = DriverManager
                .getConnection(conf.value("job4j.datasource.url"),
                        conf.value("job4j.datasource.username"),
                        conf.value("job4j.datasource.password"))) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
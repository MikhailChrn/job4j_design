package ru.job4j.jdbc;

import java.io.InputStream;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;

    private Properties config;

    public TableEditor(Properties properties) {
        this.config = properties;
        initConnection();
    }

    private void initConnection() {
        try (InputStream is = TableEditor.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            config.load(is);
            String driver = config.getProperty("job4j.datasource.driver");
            String url = config.getProperty("job4j.datasource.url");
            String username = config.getProperty("job4j.datasource.username");
            String password = config.getProperty("job4j.datasource.password");
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (IOException | SQLException | ClassNotFoundException exception) {
            System.out.println(exception.getMessage());
            throw new RuntimeException(exception);
        }
    }

    public void dbExecute(String sql) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public void createTable(String tableName) throws SQLException {
        dbExecute(String.format(
                "create table if not exists %s "
                        + "(id serial primary key);",
                tableName));
    }

    public void dropTable(String tableName) throws SQLException {
        dbExecute(String.format("drop table if exists %s;", tableName));
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        dbExecute(String.format(
                    "alter table if exists %s add column %s %s;",
                    tableName, columnName, type));
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        dbExecute(String.format(
                    "alter table if exists %s drop column %s;",
                    tableName, columnName));
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        dbExecute(String.format(
                    "alter table if exists %s rename column %s to %s;",
                    tableName, columnName, newColumnName));
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        String table = "cars";
        Properties properties = new Properties();
        try (TableEditor tableEditor = new TableEditor(properties)) {
            tableEditor.createTable(table);
            System.out.println(tableEditor.getTableScheme(table));
            tableEditor.addColumn(table, "name", "text");
            System.out.println(tableEditor.getTableScheme(table));
            tableEditor.renameColumn(table, "name", "title");
            System.out.println(tableEditor.getTableScheme(table));
            tableEditor.dropColumn(table, "title");
            System.out.println(tableEditor.getTableScheme(table));
            tableEditor.dropTable(table);
            System.out.println("bye");
        }
    }
}

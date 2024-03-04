package ru.job4j.jdbc.preparedstatement;

import ru.job4j.jdbc.TableEditor;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PreparedStatementDemo {

    private static Connection connection;

    private Properties config;

    public PreparedStatementDemo() throws Exception {
        config = new Properties();
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

    public void insert(City city) {
        try (PreparedStatement statement =
                     connection.prepareStatement("INSERT INTO cities(id, name, population) VALUES (?, ?, ?)")) {
            statement.setInt(1, city.getId());
            statement.setString(2, city.getName());
            statement.setInt(3, city.getPopulation());
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean update(City city) {
        boolean result = false;
        try (PreparedStatement statement =
                     connection.prepareStatement("UPDATE cities SET name = ?, population = ? WHERE id = ?")) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.setInt(3, city.getId());
            result = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void delete(int id) {
        try (PreparedStatement statement =
                     connection.prepareStatement("DELETE FROM cities WHERE id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<City> findAll() {
        List<City> cities = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM cities")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cities.add(new City(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("population")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cities;
    }

    public static void main(String[] args) throws Exception {
        City city = new City(1, "Samara", 1_300_000);
        PreparedStatementDemo preparedStatementDemo = new PreparedStatementDemo();
        preparedStatementDemo.insert(city);
        List<City> result = preparedStatementDemo.findAll();
        result.forEach(ct -> {
                    System.out.printf("%d - %s - %d\n",
                    ct.getId(), ct.getName(), ct.getPopulation());
        });
        city.setName("Samara-city");
        preparedStatementDemo.update(city);
        result = preparedStatementDemo.findAll();
        result.forEach(ct -> {
            System.out.printf("%d - %s - %d\n",
                    ct.getId(), ct.getName(), ct.getPopulation());
        });
        preparedStatementDemo.delete(1);
        try (Statement statement = connection.createStatement()) {
            statement.execute("delete from cities;");
        }
    }
}

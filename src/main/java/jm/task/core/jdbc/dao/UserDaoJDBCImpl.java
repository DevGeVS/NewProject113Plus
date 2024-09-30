package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.util.Util;
import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS User (" +
                "id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(50), " +
                "lastName VARCHAR(50), " +
                "age TINYINT)";

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String dropTableSQL = "DROP TABLE IF EXISTS User";

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(dropTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String insertUserSQL = "INSERT INTO User (name, lastName, age) VALUES (?, ?, ?)";

        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertUserSQL)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User " + name + " added to the database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String deleteUserSQL = "DELETE FROM User WHERE id = ?";

        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteUserSQL)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String getUsersSQL = "SELECT * FROM User";

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(getUsersSQL)) {

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        String cleanTableSQL = "TRUNCATE TABLE User";

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(cleanTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

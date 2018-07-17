package com.babich.userstore.dao.jdbc;

import com.babich.userstore.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUserDao {

    // получаем Connection, создаем Statement (или PreparedStatement)
    // генерируем запрос на основании данных о продукте

    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM USERS;");

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");

                User user = new User();
                user.setId(id);
                user.setFirstName(name);

                list.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private Connection getConnection() {
        String url = "jdbc:mysql://danit.cukm9c6zpjo8.us-west-2.rds.amazonaws.com:3306/fs3";
        String user = "fs3_user";
        String password = "bostoN";

        // create connection
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("Cannot create connection");
        }
        return connection;
    }

    public void addUser(User user) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        String sql = "INSERT INTO " + USERS + " (firstName, lastName, salary, dateOfBirth) VALUES( ?, ?, ?, ?);";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getFirstName());
        preparedStatement.setString(2, user.getLastName());
        preparedStatement.setInt(3, user.getSalary());
        Date date = Date.valueOf(user.getDateOfBirth());
        preparedStatement.setDate(4, date);

        preparedStatement.executeUpdate();
    }
    public void deleteUser(int userId) {
        try (Connection connection = getConnection()) {
            String sqlDelete = "DELETE FROM " + USERS + " WHERE id=?";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlDelete);
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("This user couldn't be deleted: " + userId, e);
        }
    }


    public void updateUser(User user) {
        try (Connection connection = getConnection()) {
            String sqlUpdate = "UPDATE " + USERS + " SET firstName=?, lastName=?, salary=?, dateOfBirth=? WHERE id=?";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setInt(3, user.getSalary());
            Date date = Date.valueOf(user.getDateOfBirth());
            preparedStatement.setDate(4, date);
            preparedStatement.setInt(5, user.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Couldn't make update for user: " + user.getId(), e);
        }
    }
}
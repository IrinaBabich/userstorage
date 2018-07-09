package com.babich.dao;

import com.babich.entity.User;

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
            // используем объект ProductMapper для получения пользователя из ResultSet
            // Использование логики ProductMapper приходит на смену описания логики ниже
            // Мы хотим разделить ответственности
            // ProductDao обращается к базе и выполняет запрос
            // ProductMapper обрабаывает ResultSet и на его основании возвращает продукт
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
            e.printStackTrace();
        }
        return connection;
    }

    public void save(User user) throws SQLException {
        Connection connection = getConnection();

        String sql = "INSERT INTO USERS (salary, firstName, lastName, datePfBirth)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, String.valueOf(user.getSalary()));
        preparedStatement.setString(2, user.getFirstName());
        preparedStatement.setString(3, user.getLastName());
        preparedStatement.setString(4, String.valueOf(user.getDateOfBirth()));
    }

}
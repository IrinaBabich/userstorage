package com.babich.userstore.dao.jdbc.mapper;

import com.babich.userstore.entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class UserRowMapper {

// класс, который умеет мапить строку базы данных на сущность продукта
    public User mapRow(ResultSet resultSet) throws SQLException{

        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setSalary(resultSet.getInt("salary"));
        user.setFirstName(resultSet.getString("name"));
        user.setLastName(resultSet.getString("surname"));

        Timestamp dateOfBirthTimestamp = resultSet.getTimestamp("dateOfBirth");
        LocalDateTime dateOfBirth = dateOfBirthTimestamp.toLocalDateTime();
        user.setDateOfBirth(resultSet.getString("dateOfBirth"));

        return user;

    }
}

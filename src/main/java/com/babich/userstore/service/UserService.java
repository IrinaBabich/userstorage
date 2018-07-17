package com.babich.userstore.service;

import com.babich.userstore.dao.jdbc.JdbcUserDao;
import com.babich.userstore.entity.User;

import java.util.List;

public class UserService {
    private JdbcUserDao jdbcUserDao = new JdbcUserDao();

    public void setJdbcUserDao(JdbcUserDao jdbcUserDao) {
        this.jdbcUserDao = jdbcUserDao;
    }

    public List<User> getAll() {
        return jdbcUserDao.getAll();
    }
}
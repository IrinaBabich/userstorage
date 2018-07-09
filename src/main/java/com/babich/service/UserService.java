package com.babich.service;

import com.babich.dao.JdbcUserDao;
import com.babich.entity.User;

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
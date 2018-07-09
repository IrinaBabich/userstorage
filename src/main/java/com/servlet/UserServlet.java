package com.servlet;

import com.babich.dao.JdbcUserDao;
import com.babich.entity.User;
import com.babich.service.UserService;
import com.babich.web.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet (name = "users", loadOnStartup = 1, urlPatterns = "/users")
public class UserServlet extends HttpServlet {
    UserService userService = new UserService();
    JdbcUserDao jdbcUserDao = new JdbcUserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userService.getAll();
        PageGenerator pageGenerator = PageGenerator.instance();
        Map<String, Object> map = new HashMap<>();
        map.put("users", users);

        String page = pageGenerator.getPage("index.ftl", map);
        resp.getWriter().write(page);
    }

    public void setUserService(UserService userService) {
        userService.setJdbcUserDao(jdbcUserDao);
    }
}
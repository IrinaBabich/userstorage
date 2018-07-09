package com.babich.servlet;

import com.babich.dao.JdbcUserDao;
import com.babich.entity.User;
import com.babich.web.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet (urlPatterns = "/user/add")
public class AddUserServlet extends HttpServlet {
    private JdbcUserDao jdbcUserDao = new JdbcUserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PageGenerator pageGenerator = PageGenerator.instance();
        String form = "<html><body>" +
                "<form method = 'post' action = 'users/add'>" +
                "<input name = 'salary' placeholder = 'salary'></input><br> " +
                "<input name = 'firstName' placeholder = 'firstName'></input><br> " +
                "<input name = 'lastName' placeholder = 'lastName'></input><br> " +
                "<input name = 'dateOfBirth' placeholder = 'dateOfBirth'></input><br>" +
                "<button type = 'submit'> Add new user</button>" +
                "</body></html>";
        resp.getWriter().write(form);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String salary = req.getParameter("salary");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String dateOfBirth = req.getParameter("dateOfBirth");

        User user = new User();
        user.setSalary(Integer.parseInt(salary));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setDateOfBirth(String.valueOf(dateOfBirth));

        try {
            jdbcUserDao.save(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
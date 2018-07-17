package com.babich.userstore;

import com.babich.userstore.dao.jdbc.JdbcUserDao;
import com.babich.userstore.service.UserService;
import com.babich.userstore.servlet.FreeMarkerServlet;
import com.babich.userstore.servlet.UserServlet;
import freemarker.ext.servlet.FreemarkerServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Start {

    public static void main(String[] args) throws Exception {
        // daos
        JdbcUserDao jdbcUserDao = new JdbcUserDao();

        // services
        UserService userService = new UserService();
        userService.setJdbcUserDao(jdbcUserDao);

        // servlets
        UserServlet servlet = new UserServlet();
        servlet.setUserService(userService);


        Server server = new Server(3000);
        ServletContextHandler handler = new ServletContextHandler();

        ServletHolder usersHolder = new ServletHolder(new UserServlet());
        handler.addServlet(usersHolder, "/users");

        ServletHolder freemarkerHolder = new ServletHolder(new FreeMarkerServlet());
        handler.addServlet(freemarkerHolder, "/users/add");

        server.setHandler(handler);
        server.start();
        server.join();
    }
}

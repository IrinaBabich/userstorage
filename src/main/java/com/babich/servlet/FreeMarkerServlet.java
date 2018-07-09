package com.babich.servlet;

import com.babich.entity.User;
import com.babich.web.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FreeMarkerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        PageGenerator pageGenerator = PageGenerator.instance();
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        String page = pageGenerator.getPage("index.ftl", map);
        resp.getWriter().write(page);
    }
}

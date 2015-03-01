package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.LoggerWrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vladislav.fedotov on 01.03.2015.
 */
public class UserMealRest extends HttpServlet {
    private static final LoggerWrapper LOG = LoggerWrapper.get(UserMealRest.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("GET request received");
        request.getRequestDispatcher("/userMealRest.jsp").forward(request, response);
    }
}

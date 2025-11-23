package com.mycompany.m9087_samartfitness.controller;

import com.mycompany.m9087_samartfitness.model.User;
import com.mycompany.m9087_samartfitness.service.UserService;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = userService.loginUser(email, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("userName", user.getName());
            response.sendRedirect("dashboard");
        } else {
            request.setAttribute("error", "Invalid credentials!");
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }
    }
}

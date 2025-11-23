package com.mycompany.m9087_samartfitness.controller;

import com.mycompany.m9087_samartfitness.model.User;
import com.mycompany.m9087_samartfitness.service.UserService;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String ageStr = request.getParameter("age");
        String heightStr = request.getParameter("height");
        String weightStr = request.getParameter("weight");

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setGender(gender);
        if (ageStr != null && !ageStr.isEmpty()) user.setAge(Integer.parseInt(ageStr));
        if (heightStr != null && !heightStr.isEmpty()) user.setHeight(Double.parseDouble(heightStr));
        if (weightStr != null && !weightStr.isEmpty()) user.setWeight(Double.parseDouble(weightStr));

        String result = userService.registerUser(user);

        if ("success".equals(result)) {
            response.sendRedirect("index.jsp?msg=registered");
        } else {
            request.setAttribute("error", result);
            RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
            rd.forward(request, response);
        }
    }
}

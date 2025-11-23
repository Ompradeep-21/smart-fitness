package com.mycompany.m9087_samartfitness.controller;

import com.mycompany.m9087_samartfitness.model.User;
import com.mycompany.m9087_samartfitness.service.UserService;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        int userId = (int) session.getAttribute("userId");
        User user = userService.getUserById(userId);
        request.setAttribute("user", user);
        RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int userId = Integer.parseInt(request.getParameter("userId"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String ageStr = request.getParameter("age");
        String heightStr = request.getParameter("height");
        String weightStr = request.getParameter("weight");

        User user = new User();
        user.setUserId(userId);
        user.setName(name);
        user.setEmail(email);
        user.setGender(gender);
        if (ageStr != null && !ageStr.isEmpty()) user.setAge(Integer.parseInt(ageStr));
        if (heightStr != null && !heightStr.isEmpty()) user.setHeight(Double.parseDouble(heightStr));
        if (weightStr != null && !weightStr.isEmpty()) user.setWeight(Double.parseDouble(weightStr));

        String result = userService.updateProfile(user);

        if ("success".equals(result)) {
            response.sendRedirect("profile?msg=updated");
        } else {
            request.setAttribute("error", result);
            request.setAttribute("user", user);
            RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
            rd.forward(request, response);
        }
    }
}

package com.mycompany.m9087_samartfitness.controller;

import com.mycompany.m9087_samartfitness.model.Workout;
import com.mycompany.m9087_samartfitness.service.WorkoutService;
import java.io.IOException;
import java.util.List;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/viewWorkouts")
public class ViewWorkoutServlet extends HttpServlet {

    private WorkoutService workoutService = new WorkoutService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        int userId = (int) session.getAttribute("userId");
        List<Workout> workouts = workoutService.getUserWorkouts(userId);

        request.setAttribute("workouts", workouts);
        RequestDispatcher rd = request.getRequestDispatcher("viewWorkouts.jsp");
        rd.forward(request, response);
    }
}

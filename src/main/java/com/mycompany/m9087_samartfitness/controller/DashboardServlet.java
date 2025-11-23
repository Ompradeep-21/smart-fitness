package com.mycompany.m9087_samartfitness.controller;

import com.mycompany.m9087_samartfitness.model.Workout;
import com.mycompany.m9087_samartfitness.service.WorkoutService;
import java.io.IOException;
import java.util.List;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

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
        request.setAttribute("totalCalories", workoutService.getTotalCalories(workouts));
        request.setAttribute("totalDuration", workoutService.getTotalDuration(workouts));
        request.setAttribute("workoutCount", workoutService.getWorkoutCount(workouts));

        RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
        rd.forward(request, response);
    }
}

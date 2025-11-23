package com.mycompany.m9087_samartfitness.controller;

import com.mycompany.m9087_samartfitness.service.WorkoutService;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/deleteWorkout")
public class DeleteWorkoutServlet extends HttpServlet {

    private WorkoutService workoutService = new WorkoutService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int workoutId = Integer.parseInt(request.getParameter("id"));
        workoutService.deleteWorkout(workoutId);
        response.sendRedirect("viewWorkouts");
    }
}

package com.mycompany.m9087_samartfitness.controller;

import com.mycompany.m9087_samartfitness.model.Workout;
import com.mycompany.m9087_samartfitness.service.WorkoutService;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/addWorkout")
public class AddWorkoutServlet extends HttpServlet {

    private WorkoutService workoutService = new WorkoutService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        int userId = (int) session.getAttribute("userId");
        String activityType = request.getParameter("activity_type");
        double duration = Double.parseDouble(request.getParameter("duration"));
        double distance = Double.parseDouble(request.getParameter("distance"));
        double calories = Double.parseDouble(request.getParameter("calories"));
        String dateStr = request.getParameter("workout_date");
        String notes = request.getParameter("notes");

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Workout workout = new Workout();
            workout.setUserId(userId);
            workout.setActivityType(activityType);
            workout.setDuration(duration);
            workout.setDistance(distance);
            workout.setCalories(calories);
            workout.setWorkoutDate(sdf.parse(dateStr));
            workout.setNotes(notes);

            String result = workoutService.addWorkout(workout);

            if ("success".equals(result)) {
                response.sendRedirect("viewWorkouts");
            } else {
                request.setAttribute("error", result);
                RequestDispatcher rd = request.getRequestDispatcher("addWorkout.jsp");
                rd.forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("addWorkout.jsp?error=Invalid Data");
        }
    }
}

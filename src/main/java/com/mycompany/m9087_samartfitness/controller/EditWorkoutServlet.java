package com.mycompany.m9087_samartfitness.controller;

import com.mycompany.m9087_samartfitness.model.Workout;
import com.mycompany.m9087_samartfitness.service.WorkoutService;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/editWorkout")
public class EditWorkoutServlet extends HttpServlet {

    private WorkoutService workoutService = new WorkoutService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        int workoutId = Integer.parseInt(request.getParameter("id"));
        int userId = (int) session.getAttribute("userId");

        // Get all workouts of this user and find the one we want
        Workout target = null;
        for (Workout w : workoutService.getUserWorkouts(userId)) {
            if (w.getWorkoutId() == workoutId) {
                target = w;
                break;
            }
        }

        if (target == null) {
            response.sendRedirect("viewWorkouts?error=WorkoutNotFound");
            return;
        }

        request.setAttribute("workout", target);
        RequestDispatcher rd = request.getRequestDispatcher("editWorkout.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int workoutId = Integer.parseInt(request.getParameter("workoutId"));
            int userId = Integer.parseInt(request.getParameter("userId"));
            String activityType = request.getParameter("activity_type");
            double duration = Double.parseDouble(request.getParameter("duration"));
            String distanceStr = request.getParameter("distance");
            double distance = 0;
            try {
                distance = Double.parseDouble(distanceStr);
            } catch (NumberFormatException e) {
                distance = 0; // if "Not Applicable"
            }
            double calories = Double.parseDouble(request.getParameter("calories"));
            String dateStr = request.getParameter("workout_date");
            String notes = request.getParameter("notes");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Workout workout = new Workout();
            workout.setWorkoutId(workoutId);
            workout.setUserId(userId);
            workout.setActivityType(activityType);
            workout.setDuration(duration);
            workout.setDistance(distance);
            workout.setCalories(calories);
            workout.setWorkoutDate(sdf.parse(dateStr));
            workout.setNotes(notes);

            String result = workoutService.updateWorkout(workout);

            if ("success".equals(result)) {
                response.sendRedirect("viewWorkouts?msg=updated");
            } else {
                request.setAttribute("error", result);
                request.setAttribute("workout", workout);
                RequestDispatcher rd = request.getRequestDispatcher("editWorkout.jsp");
                rd.forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("viewWorkouts?error=InvalidInput");
        }
    }
}

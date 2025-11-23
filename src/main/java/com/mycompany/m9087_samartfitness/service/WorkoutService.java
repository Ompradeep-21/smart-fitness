package com.mycompany.m9087_samartfitness.service;

import com.mycompany.m9087_samartfitness.dao.WorkoutDAO;
import com.mycompany.m9087_samartfitness.model.Workout;
import com.mycompany.m9087_samartfitness.util.InputValidator;
import java.util.Date;
import java.util.List;

public class WorkoutService {

    private WorkoutDAO workoutDAO = new WorkoutDAO();

    // Add workout
    public String addWorkout(Workout workout) {
        if (!InputValidator.isNotEmpty(workout.getActivityType())) {
            return "Activity type is required.";
        }

        if (!InputValidator.isPositive(workout.getDuration()) ||
            !InputValidator.isPositive(workout.getDistance()) ||
            !InputValidator.isPositive(workout.getCalories())) {
            return "Duration, distance, and calories must be non-negative.";
        }

        if (workout.getWorkoutDate() == null || workout.getWorkoutDate().after(new Date())) {
            return "Invalid workout date.";
        }

        boolean success = workoutDAO.addWorkout(workout);
        return success ? "success" : "Failed to save workout.";
    }

    // Get all workouts for a user
    public List<Workout> getUserWorkouts(int userId) {
        return workoutDAO.getWorkoutsByUser(userId);
    }

    // Delete a workout
    public boolean deleteWorkout(int workoutId) {
        return workoutDAO.deleteWorkout(workoutId);
    }

    // Update a workout
    public String updateWorkout(Workout workout) {
        boolean success = workoutDAO.updateWorkout(workout);
        return success ? "success" : "Failed to update workout.";
    }

    // Calculate total calories for dashboard
    public double getTotalCalories(List<Workout> workouts) {
        return workouts.stream().mapToDouble(Workout::getCalories).sum();
    }

    // Calculate total duration
    public double getTotalDuration(List<Workout> workouts) {
        return workouts.stream().mapToDouble(Workout::getDuration).sum();
    }

    // Count workouts
    public int getWorkoutCount(List<Workout> workouts) {
        return workouts.size();
    }
}

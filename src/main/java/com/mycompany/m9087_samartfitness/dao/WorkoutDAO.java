package com.mycompany.m9087_samartfitness.dao;

import com.mycompany.m9087_samartfitness.model.Workout;
import com.mycompany.m9087_samartfitness.util.DBConnection;
import java.sql.*;
import java.util.*;

public class WorkoutDAO {

    // Add a workout
    public boolean addWorkout(Workout workout) {
        String sql = "INSERT INTO workouts (user_id, activity_type, duration, distance, calories, workout_date, notes) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, workout.getUserId());
            ps.setString(2, workout.getActivityType());
            ps.setDouble(3, workout.getDuration());
            ps.setDouble(4, workout.getDistance());
            ps.setDouble(5, workout.getCalories());
            ps.setDate(6, new java.sql.Date(workout.getWorkoutDate().getTime()));
            ps.setString(7, workout.getNotes());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error in addWorkout(): " + e.getMessage());
        }
        return false;
    }

    // Get all workouts for a user
    public List<Workout> getWorkoutsByUser(int userId) {
        List<Workout> workouts = new ArrayList<>();
        String sql = "SELECT * FROM workouts WHERE user_id = ? ORDER BY workout_date DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Workout w = new Workout();
                w.setWorkoutId(rs.getInt("workout_id"));
                w.setUserId(rs.getInt("user_id"));
                w.setActivityType(rs.getString("activity_type"));
                w.setDuration(rs.getDouble("duration"));
                w.setDistance(rs.getDouble("distance"));
                w.setCalories(rs.getDouble("calories"));
                w.setWorkoutDate(rs.getDate("workout_date"));
                w.setNotes(rs.getString("notes"));
                workouts.add(w);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error in getWorkoutsByUser(): " + e.getMessage());
        }
        return workouts;
    }

    // Delete a workout
    public boolean deleteWorkout(int workoutId) {
        String sql = "DELETE FROM workouts WHERE workout_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, workoutId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error in deleteWorkout(): " + e.getMessage());
        }
        return false;
    }

    // Update a workout
    public boolean updateWorkout(Workout workout) {
        String sql = "UPDATE workouts SET activity_type=?, duration=?, distance=?, calories=?, workout_date=?, notes=? WHERE workout_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, workout.getActivityType());
            ps.setDouble(2, workout.getDuration());
            ps.setDouble(3, workout.getDistance());
            ps.setDouble(4, workout.getCalories());
            ps.setDate(5, new java.sql.Date(workout.getWorkoutDate().getTime()));
            ps.setString(6, workout.getNotes());
            ps.setInt(7, workout.getWorkoutId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error in updateWorkout(): " + e.getMessage());
        }
        return false;
    }
}

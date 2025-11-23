package com.mycompany.m9087_samartfitness.model;

import java.util.Date;

public class Workout {
    private int workoutId;
    private int userId;
    private String activityType;
    private double duration;
    private double distance;
    private double calories;
    private Date workoutDate;
    private String notes;

    // Constructors
    public Workout() {}

    public Workout(int workoutId, int userId, String activityType, double duration, double distance, double calories, Date workoutDate, String notes) {
        this.workoutId = workoutId;
        this.userId = userId;
        this.activityType = activityType;
        this.duration = duration;
        this.distance = distance;
        this.calories = calories;
        this.workoutDate = workoutDate;
        this.notes = notes;
    }

    // Getters and Setters
    public int getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public Date getWorkoutDate() {
        return workoutDate;
    }

    public void setWorkoutDate(Date workoutDate) {
        this.workoutDate = workoutDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}

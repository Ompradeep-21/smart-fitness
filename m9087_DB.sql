-- Create database
CREATE DATABASE IF NOT EXISTS M9087_smartfitnessdb;
USE M9087_smartfitnessdb;

-- Users table
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    age INT,
    gender VARCHAR(10),
    height DOUBLE,
    weight DOUBLE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Workouts table
CREATE TABLE workouts (
    workout_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    activity_type VARCHAR(50) NOT NULL,
    duration DOUBLE NOT NULL,
    distance DOUBLE,
    calories DOUBLE,
    workout_date DATE NOT NULL,
    notes TEXT,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);



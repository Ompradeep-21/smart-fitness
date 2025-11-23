package com.mycompany.m9087_samartfitness.dao;

import com.mycompany.m9087_samartfitness.model.User;
import com.mycompany.m9087_samartfitness.util.DBConnection;
import java.sql.*;

public class UserDAO {

    // Register a new user
    public boolean registerUser(User user) {
        String sql = "INSERT INTO users (name, email, password, age, gender, height, weight) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setObject(4, user.getAge());
            ps.setString(5, user.getGender());
            ps.setObject(6, user.getHeight());
            ps.setObject(7, user.getWeight());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error in registerUser(): " + e.getMessage());
        }
        return false;
    }

    // Validate login credentials
    public User validateLogin(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setAge(rs.getInt("age"));
                user.setGender(rs.getString("gender"));
                user.setHeight(rs.getDouble("height"));
                user.setWeight(rs.getDouble("weight"));
                return user;
            }

        } catch (SQLException e) {
            System.out.println("❌ Error in validateLogin(): " + e.getMessage());
        }
        return null;
    }

    // Get user by ID
    public User getUserById(int userId) {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setAge(rs.getInt("age"));
                user.setGender(rs.getString("gender"));
                user.setHeight(rs.getDouble("height"));
                user.setWeight(rs.getDouble("weight"));
                return user;
            }

        } catch (SQLException e) {
            System.out.println("❌ Error in getUserById(): " + e.getMessage());
        }
        return null;
    }

    // Update user profile
    public boolean updateUserProfile(User user) {
        String sql = "UPDATE users SET name=?, email=?, age=?, gender=?, height=?, weight=? WHERE user_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setObject(3, user.getAge());
            ps.setString(4, user.getGender());
            ps.setObject(5, user.getHeight());
            ps.setObject(6, user.getWeight());
            ps.setInt(7, user.getUserId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error in updateUserProfile(): " + e.getMessage());
        }
        return false;
    }
}

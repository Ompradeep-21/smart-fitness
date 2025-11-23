package com.mycompany.m9087_samartfitness.service;

import com.mycompany.m9087_samartfitness.dao.UserDAO;
import com.mycompany.m9087_samartfitness.model.User;
import com.mycompany.m9087_samartfitness.util.InputValidator;

public class UserService {

    private UserDAO userDAO = new UserDAO();

    // Register a new user
    public String registerUser(User user) {
        if (!InputValidator.isNotEmpty(user.getName()) ||
            !InputValidator.isNotEmpty(user.getEmail()) ||
            !InputValidator.isNotEmpty(user.getPassword())) {
            return "All fields are required.";
        }

        if (!InputValidator.isValidEmail(user.getEmail())) {
            return "Invalid email format.";
        }

        boolean success = userDAO.registerUser(user);
        return success ? "success" : "Registration failed. Try again.";
    }

    // Login user
    public User loginUser(String email, String password) {
        if (!InputValidator.isNotEmpty(email) || !InputValidator.isNotEmpty(password)) {
            return null;
        }
        return userDAO.validateLogin(email, password);
    }

    // Get user by ID
    public User getUserById(int userId) {
        return userDAO.getUserById(userId);
    }

    // Update user profile
    public String updateProfile(User user) {
        boolean success = userDAO.updateUserProfile(user);
        return success ? "success" : "Update failed. Try again.";
    }
}

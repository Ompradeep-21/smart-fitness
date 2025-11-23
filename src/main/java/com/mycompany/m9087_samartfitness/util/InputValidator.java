package com.mycompany.m9087_samartfitness.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class InputValidator {

    // Validate email format
    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    // Validate non-empty string
    public static boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

    // Validate positive numbers
    public static boolean isPositive(double number) {
        return number >= 0;
    }

    // Validate date (yyyy-MM-dd format)
    public static boolean isValidDate(String dateStr) {
        if (dateStr == null || dateStr.isEmpty()) return false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}

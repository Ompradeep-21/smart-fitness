package com.mycompany.m9087_samartfitness.model;

public class User {
    private int userId;
    private String name;
    private String email;
    private String password;
    private Integer age;
    private String gender;
    private Double height;
    private Double weight;

    // Constructors
    public User() {}

    public User(int userId, String name, String email, String password, Integer age, String gender, Double height, Double weight) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}

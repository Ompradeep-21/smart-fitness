<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>FitLife - Register</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow p-4">
                <h3 class="text-center mb-4 text-success">Create Your Account</h3>
                <form action="register" method="post">
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label>Name</label>
                            <input type="text" name="name" class="form-control" required>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label>Email</label>
                            <input type="email" name="email" class="form-control" required>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label>Password</label>
                        <input type="password" name="password" class="form-control" required>
                    </div>

                    <div class="row">
                        <div class="col-md-4 mb-3">
                            <label>Age</label>
                            <input type="number" name="age" class="form-control">
                        </div>
                        <div class="col-md-4 mb-3">
                            <label>Gender</label>
                            <select name="gender" class="form-control">
                                <option value="">Select</option>
                                <option>Male</option>
                                <option>Female</option>
                            </select>
                        </div>
                        <div class="col-md-4 mb-3">
                            <label>Height (cm)</label>
                            <input type="number" name="height" class="form-control">
                        </div>
                    </div>

                    <div class="mb-3">
                        <label>Weight (kg)</label>
                        <input type="number" name="weight" class="form-control">
                    </div>

                    <button class="btn btn-success w-100">Register</button>
                </form>

                <p class="text-center mt-3 mb-0">Already have an account? <a href="index.jsp">Login</a></p>

                <% if (request.getAttribute("error") != null) { %>
                    <div class="alert alert-danger mt-3"><%= request.getAttribute("error") %></div>
                <% } %>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>FitLife - Login</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-4">
            <div class="card shadow p-4">
                <h3 class="text-center mb-4 text-primary">FitLife Login</h3>
                <form action="login" method="post">
                    <div class="mb-3">
                        <label>Email</label>
                        <input type="email" name="email" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label>Password</label>
                        <input type="password" name="password" class="form-control" required>
                    </div>
                    <button class="btn btn-primary w-100">Login</button>
                </form>
                <p class="text-center mt-3 mb-0">Don’t have an account? <a href="register.jsp">Register</a></p>
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

<%@ page import="com.mycompany.m9087_samartfitness.model.User" %>
<%@ include file="includes/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Profile - FitLife</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-4">
    <div class="card shadow p-4">
        <h3 class="mb-4 text-primary">Your Profile</h3>
        <%
            User user = (User) request.getAttribute("user");
        %>
        <form action="profile" method="post">
            <input type="hidden" name="userId" value="<%= user.getUserId() %>">
            <div class="mb-3">
                <label>Name</label>
                <input type="text" name="name" value="<%= user.getName() %>" class="form-control" required>
            </div>
            <div class="mb-3">
                <label>Email</label>
                <input type="email" name="email" value="<%= user.getEmail() %>" class="form-control" required>
            </div>
            <div class="row">
                <div class="col-md-4 mb-3">
                    <label>Age</label>
                    <input type="number" name="age" value="<%= user.getAge() %>" class="form-control">
                </div>
                <div class="col-md-4 mb-3">
                    <label>Gender</label>
                    <input type="text" name="gender" value="<%= user.getGender() %>" class="form-control">
                </div>
                <div class="col-md-4 mb-3">
                    <label>Height (cm)</label>
                    <input type="number" name="height" value="<%= user.getHeight() %>" class="form-control">
                </div>
            </div>
            <div class="mb-3">
                <label>Weight (kg)</label>
                <input type="number" name="weight" value="<%= user.getWeight() %>" class="form-control">
            </div>
            <button class="btn btn-primary w-100">Update Profile</button>
        </form>
    </div>
</div>
</body>
</html>

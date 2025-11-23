<%@ include file="includes/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard - FitLife</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h3 class="mb-4 text-primary">Welcome, <%= session.getAttribute("userName") %> ?</h3>
    <div class="row g-3">
        <div class="col-md-4">
            <div class="card text-center p-3">
                <h5>Total Workouts</h5>
                <h3><%= request.getAttribute("workoutCount") %></h3>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card text-center p-3">
                <h5>Total Duration (mins)</h5>
                <h3><%= request.getAttribute("totalDuration") %></h3>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card text-center p-3">
                <h5>Total Calories Burned</h5>
                <h3><%= request.getAttribute("totalCalories") %></h3>
            </div>
        </div>
    </div>

    <div class="text-center mt-4">
        <a href="addWorkout.jsp" class="btn btn-success">Add New Workout</a>
        <a href="viewWorkouts" class="btn btn-primary">View Workouts</a>
    </div>
</div>
</body>
</html>

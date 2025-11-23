<%@ page import="java.util.*, com.mycompany.m9087_samartfitness.model.Workout" %>
<%@ include file="includes/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Workouts - FitLife</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        .btn-sm {
            padding: 3px 10px;
            font-size: 0.875rem;
        }
        .table td {
            vertical-align: middle;
        }
    </style>

    <script>
        // Simple front-end search
        function searchWorkouts() {
            const input = document.getElementById("searchInput").value.toLowerCase();
            const rows = document.querySelectorAll("#workoutTable tbody tr");
            rows.forEach(row => {
                const text = row.textContent.toLowerCase();
                row.style.display = text.includes(input) ? "" : "none";
            });
        }
    </script>
</head>

<body class="bg-light">
<div class="container mt-4">
    <h3 class="mb-4 text-primary">Your Workouts</h3>

    <input type="text" id="searchInput" class="form-control mb-3" placeholder="Search..." onkeyup="searchWorkouts()">

    <table class="table table-bordered table-striped table-hover shadow-sm" id="workoutTable">
        <thead class="table-dark text-center">
            <tr>
                <th>Activity</th>
                <th>Duration (mins)</th>
                <th>Distance (km)</th>
                <th>Calories</th>
                <th>Date</th>
                <th>Notes</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Workout> workouts = (List<Workout>) request.getAttribute("workouts");
                if (workouts != null && !workouts.isEmpty()) {
                    for (Workout w : workouts) {
            %>
            <tr>
                <td><%= w.getActivityType() %></td>
                <td><%= w.getDuration() %></td>
                <td><%= w.getDistance() %></td>
                <td><%= w.getCalories() %></td>
                <td><%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(w.getWorkoutDate()) %></td>
                <td><%= w.getNotes() %></td>
                <td class="text-center">
                    <!-- Edit & Delete Buttons -->
                    <a href="editWorkout?id=<%= w.getWorkoutId() %>" class="btn btn-warning btn-sm me-1">Edit</a>
                    <a href="deleteWorkout?id=<%= w.getWorkoutId() %>" 
                       class="btn btn-danger btn-sm"
                       onclick="return confirm('Are you sure you want to delete this workout?');">
                       Delete
                    </a>
                </td>
            </tr>
            <%  }
                } else { %>
            <tr>
                <td colspan="7" class="text-center text-muted py-3">
                    No workouts found. <a href="addWorkout.jsp" class="text-primary">Add one now</a>!
                </td>
            </tr>
            <% } %>
        </tbody>
    </table>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

<%@ page import="com.mycompany.m9087_samartfitness.model.Workout" %>
<%@ include file="includes/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Workout - FitLife</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>.hidden { display: none; }</style>
</head>
<body class="bg-light">

<%
    Workout workout = (Workout) request.getAttribute("workout");
%>

<div class="container mt-4">
    <div class="card shadow p-4">
        <h3 class="mb-4 text-warning">Edit Workout</h3>

        <form action="editWorkout" method="post" onsubmit="return validateForm();">
            <input type="hidden" name="workoutId" value="<%= workout.getWorkoutId() %>">
            <input type="hidden" name="userId" value="<%= workout.getUserId() %>">

            <div class="mb-3">
                <label>Activity Type</label>
                <select name="activity_type" id="activityType" class="form-control" required>
                    <option <%= workout.getActivityType().equals("Running") ? "selected" : "" %>>Running</option>
                    <option <%= workout.getActivityType().equals("Walking") ? "selected" : "" %>>Walking</option>
                    <option <%= workout.getActivityType().equals("Cycling") ? "selected" : "" %>>Cycling</option>
                    <option <%= workout.getActivityType().equals("Gym Workout") ? "selected" : "" %>>Gym Workout</option>
                </select>
            </div>

            <div class="mb-3">
                <label>Duration (mins)</label>
                <input type="number" name="duration" class="form-control" value="<%= workout.getDuration() %>" required>
            </div>

            <div class="mb-3" id="distanceField">
                <label>Distance (km)</label>
                <input type="text" id="distanceInput" name="distance" class="form-control" value="<%= workout.getDistance() == 0 ? "Not Applicable" : workout.getDistance() %>">
            </div>

            <div class="mb-3">
                <label>Calories Burned</label>
                <input type="number" step="0.1" name="calories" class="form-control" value="<%= workout.getCalories() %>">
            </div>

            <div class="mb-3">
                <label>Workout Date</label>
                <input type="date" id="workoutDate" name="workout_date" class="form-control" value="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(workout.getWorkoutDate()) %>" required>
            </div>

            <div class="mb-3">
                <label>Notes</label>
                <textarea name="notes" class="form-control"><%= workout.getNotes() %></textarea>
            </div>

            <button class="btn btn-warning w-100">Update Workout</button>
        </form>
    </div>
</div>

<!-- Smart JS for Gym Workout -->
<script>
    const activityType = document.getElementById("activityType");
    const distanceInput = document.getElementById("distanceInput");

    function updateDistanceBehavior() {
        if (activityType.value === "Gym Workout") {
            distanceInput.value = "Not Applicable";
            distanceInput.readOnly = true;
            distanceInput.classList.add("bg-light");
        } else {
            if (distanceInput.value === "Not Applicable") distanceInput.value = "";
            distanceInput.readOnly = false;
            distanceInput.classList.remove("bg-light");
        }
    }

    activityType.addEventListener("change", updateDistanceBehavior);
    updateDistanceBehavior(); // Run on load
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

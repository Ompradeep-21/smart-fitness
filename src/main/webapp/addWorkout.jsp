<%@ include file="includes/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Workout - FitLife</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .hidden { display: none; }
    </style>
</head>
<body class="bg-light">
<div class="container mt-4">
    <div class="card shadow p-4">
        <h3 class="mb-4 text-success">Add Workout</h3>

        <!-- Alert for invalid date -->
        <div id="dateAlert" class="alert alert-warning hidden" role="alert">
            ?? Please enter a valid date (past or today). You can?t log a future workout!
        </div>

        <form action="addWorkout" method="post" onsubmit="return validateForm();">

            <div class="mb-3">
                <label>Activity Type</label>
                <select name="activity_type" id="activityType" class="form-control" required>
                    <option value="">-- Select Activity --</option>
                    <option>Running</option>
                    <option>Walking</option>
                    <option>Cycling</option>
                    <option>Gym Workout</option>
                </select>
            </div>

            <div class="mb-3">
                <label>Duration (mins)</label>
                <input type="number" name="duration" class="form-control" required>
            </div>

            <!-- Distance Field -->
            <div class="mb-3" id="distanceField">
                <label>Distance (km)</label>
                <input type="text" id="distanceInput" name="distance" class="form-control" placeholder="Enter distance or '000.00'">
            </div>

            <div class="mb-3">
                <label>Calories Burned</label>
                <input type="number" step="0.1" name="calories" class="form-control">
            </div>

            <div class="mb-3">
                <label>Workout Date</label>
                <input type="date" id="workoutDate" name="workout_date" class="form-control" required>
            </div>

            <div class="mb-3">
                <label>Notes</label>
                <textarea name="notes" class="form-control" rows="2"></textarea>
            </div>

            <button class="btn btn-success w-100">Save Workout</button>
        </form>
    </div>
</div>

<!-- Smart Frontend Logic -->
<script>
    const activityType = document.getElementById("activityType");
    const distanceInput = document.getElementById("distanceInput");
    const workoutDate = document.getElementById("workoutDate");
    const dateAlert = document.getElementById("dateAlert");

    // Automatically handle distance input
    activityType.addEventListener("change", () => {
        if (activityType.value === "Gym Workout") {
            distanceInput.value = "000.00";
            distanceInput.readOnly = true;
            distanceInput.classList.add("bg-light");
        } else {
            distanceInput.value = "";
            distanceInput.readOnly = false;
            distanceInput.classList.remove("bg-light");
        }
    });

    // Normalize date to ignore time
    function normalizeDate(date) {
        const d = new Date(date);
        d.setHours(0, 0, 0, 0);
        return d;
    }

    // Validate date on change
    workoutDate.addEventListener("change", () => {
        const selectedDate = normalizeDate(workoutDate.value);
        const today = normalizeDate(new Date());
        if (selectedDate > today) {
            dateAlert.classList.remove("hidden");
            workoutDate.classList.add("is-invalid");
        } else {
            dateAlert.classList.add("hidden");
            workoutDate.classList.remove("is-invalid");
        }
    });

    // Validate before submission
    function validateForm() {
        const selectedDate = normalizeDate(workoutDate.value);
        const today = normalizeDate(new Date());
        if (selectedDate > today) {
            alert("?? Please enter a valid workout date (not in the future).");
            return false;
        }

        // If Gym Workout, force 00.0
        if (activityType.value === "Gym Workout" && distanceInput.value.trim() === "") {
            distanceInput.value = "000.00";
        }
        return true;
    }
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

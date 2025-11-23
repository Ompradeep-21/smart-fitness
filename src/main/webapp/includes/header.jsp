<%@ page session="true" %>
<%
    if (session.getAttribute("userId") == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand fw-bold" href="dashboard">FitLife</a>
    <div class="collapse navbar-collapse">
      <ul class="navbar-nav ms-auto">
        <li class="nav-item"><a class="nav-link" href="dashboard">Dashboard</a></li>
        <li class="nav-item"><a class="nav-link" href="viewWorkouts">Workouts</a></li>
        <li class="nav-item"><a class="nav-link" href="profile">Profile</a></li>
        <li class="nav-item"><a class="nav-link text-danger" href="logout">Logout</a></li>
      </ul>
    </div>
  </div>
</nav>

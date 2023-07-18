<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 14-07-2023
  Time: 04:12 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  // Retrieve the userId and userType attributes from the session
  Integer userId = (Integer) session.getAttribute("userId");
  Integer userType = (Integer) session.getAttribute("userType");
  // Check if the userId is null or the userType is not 1
  if (userId == null || userType != 3) {
    // User is not logged in or doesn't have access, redirect to the error page
    response.sendRedirect("error.jsp");
  } else {
    // User is logged in and has access, show the restricted content
%>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
          integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
          crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
          integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
          crossorigin="anonymous"></script>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css">
  <link rel="stylesheet" href="css/style.css">
</head>

<body>
<jsp:include page="navbar.jsp"></jsp:include>

<div class="container-fluid pb-3 flex-grow-1 d-flex flex-column flex-sm-row overflow-auto">
  <div class="row flex-grow-1">
    <jsp:include page="sidebar.jsp"></jsp:include>
    <main class="col-sm-10 overflow-auto h-100">
      <div class="bg-light border rounded-3 p-3">
        Welcome, <%= session.getAttribute("userName") %> ( Accountant )
      </div>
      <% String message = request.getParameter("message"); %>
      <% if (message != null && !message.isEmpty()) { %>
      <div class="alert alert-success" role="alert">
        <%= message %>
      </div>
      <% } %>
    </main>
  </div>
</div>
</body>
</html>
<% } %>


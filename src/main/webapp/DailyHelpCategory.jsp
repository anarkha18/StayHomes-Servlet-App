<%@ page import="com.stayhome.modal.flats" %>
<%@ page import="java.util.List" %>
<%@ page import="com.stayhome.modal.DailyHelpCategory" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  // Retrieve the userId and userType attributes from the session
  Integer userId = (Integer) session.getAttribute("userId");
  Integer userType = (Integer) session.getAttribute("userType");
  // Check if the userId is null or the userType is not 1
  if (userId == null || userType != 4) {
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
<%-- Display error message if it exists --%>

<div class="container-fluid pb-3 flex-grow-1 d-flex flex-column flex-sm-row overflow-auto">
  <div class="row flex-grow-1">
    <jsp:include page="sidebar.jsp"></jsp:include>
    <main class="col-sm-10 overflow-auto h-100">
      <div class="bg-light border rounded-3 p-3">
        <div class="d-flex justify-content-between align-items-center">
          <h3>Daily Help Category</h3>
          <button type="button" class="btn btn-outline-primary" data-bs-toggle="modal" data-bs-target="#dhc">
            <i class="bi bi-plus-lg"></i> Add Category
          </button>
        </div>
        <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
        <% if (errorMessage != null && !errorMessage.isEmpty()) { %>
        <div class="alert alert-danger" role="alert">
          <%= errorMessage %>
        </div>
        <% } %>
        <table class="table table-striped table-hover">
          <thead>
          <tr class="">
            <th scope="col">Id</th>
            <th scope="col">Category Name</th>
            <th scope="col">Action</th>
          </tr>
          </thead>
          <tbody>
          <% List<DailyHelpCategory> DHCList = (List<DailyHelpCategory>) request.getAttribute("DHCList"); %>
          <% for (DailyHelpCategory dhc : DHCList) { %>
          <tr>
            <th scope="row"><%= dhc.getId() %></th>
            <td><%= dhc.getName() %></td>
            <td>
              <a href="editDhcCat?id=<%= dhc.getId() %>">
                <button type="button" class="btn btn-outline-primary me-2">Edit</button>
              </a>
              <a href="deleteDhc?id=<%= dhc.getId() %>">
                <button type="button" class="btn btn-primary">Delete</button>
              </a>
            </td>
          </tr>
          <% } %>
          </tbody>
        </table>
      </div>
    </main>
  </div>
</div>
<!-- Modal for add user-->
<div class="modal fade" id="dhc" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">ADD Daily Help Category</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form class="needs-validation" novalidate action="addDhcCat" method="post">
          <div class="mb-3">
            <label for="cname" class="form-label">Category Name</label>
            <input type="text" class="form-control" id="cname"  name="cname" minlength="3"  required>
            <div class="invalid-feedback">Please enter a valid name.</div>
          </div>
          <button type="submit" class="btn btn-primary">Submit</button>
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        </form>

      </div>
    </div>
  </div>
</div>


<script>
  (function () {
    'use strict';

    // Fetch the add user form element
    var dhc = document.querySelector('#dhc .needs-validation');

    // Add event listener for add user form submission
    dhc.addEventListener('submit', function (event) {
      if (!dhc.checkValidity()) {
        event.preventDefault();
        event.stopPropagation();
      }

      dhc.classList.add('was-validated');
    }, false);
  })();
</script>

</body>
</html>
<% } %>
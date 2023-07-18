<%@ page import="com.stayhome.modal.flats" %>
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
<div class="container-fluid pb-3 flex-grow-1 d-flex flex-column flex-sm-row overflow-auto">
  <div class="row flex-grow-1">
    <jsp:include page="sidebar.jsp"></jsp:include>
    <main class="col-sm-10 overflow-auto h-100">
      <div class="bg-light border rounded-3 p-3">
        <div class="d-flex justify-content-between align-items-center">
          <h3>EDIT FLAT</h3>
        </div>
        <% flats flat = (flats) request.getAttribute("myFlat"); %>
       <div class="container">
        <div class="col-6" id="editFlats">
          <form class="needs-validation" novalidate action="updateFlat" method="post">
            <div class="mb-3">
              <label for="id" class="form-label">Flat Id</label>
              <input type="text" class="form-control" id="id" value="<%= flat.getId()%>"  name="id" minlength="3"  readonly>
              <div class="invalid-feedback">Please enter a valid No.</div>
            </div>
            <div class="mb-3">
              <label for="flatNo" class="form-label">Flat No</label>
              <input type="text" class="form-control" id="flatNo" value="<%= flat.getFlatNo()%>"  name="flatNo" minlength="3"  required>
              <div class="invalid-feedback">Please enter a valid No.</div>
            </div>
            <div class="mb-3">
              <label for="flatType" class="form-label">Flat Type</label>
              <input type="text" class="form-control" id="flatType" value="<%= flat.getFlatType()%>"  name="flatType" minlength="3"  required>
              <div class="invalid-feedback">Please enter a valid type.</div>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
            <a href="Flats">
            <button type="button" class="btn btn-secondary">Back</button>
            </a>
          </form>
        </div>
        </div>
      </div>
    </main>
  </div>
</div>
<script>
  (function () {
    'use strict';

    // Fetch the add user form element
    var flats = document.querySelector('#editFlats .needs-validation');

    // Add event listener for add user form submission
    flats.addEventListener('submit', function (event) {
      if (!flats.checkValidity()) {
        event.preventDefault();
        event.stopPropagation();
      }

      flats.classList.add('was-validated');
    }, false);
  })();
</script>

</body>
</html>
<% } %>
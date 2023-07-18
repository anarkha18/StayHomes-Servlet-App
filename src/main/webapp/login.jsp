<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 11-07-2023
  Time: 04:33 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</head>
<body>
<div class="container">

  <div class="col-6 bg-light border rounded-3 p-3 mt-5">
    <div class="alert alert-info" role="alert">
      WELCOME TO STAY HOME SOCIETY
    </div>
    <% String message = request.getParameter("message"); %>
    <% if (message != null && !message.isEmpty()) { %>
    <div class="alert alert-success" role="alert">
      <%= message %>
    </div>
    <% } %>
    <div class="">
      <div class="col-6" id="login">
        <h3>LOG IN</h3>
        <form class="needs-validation" novalidate action="auth" method="post">
          <div class="mb-3">
            <label for="userEmail" class="form-label">Email address</label>
            <input type="email" class="form-control" id="userEmail"  name="userEmail" aria-describedby="emailHelp" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" required>
            <div class="invalid-feedback">Please enter a valid email address.</div>
          </div>
          <div class="mb-3">
            <label for="userPassword" class="form-label">Password</label>
            <input type="text" class="form-control" id="userPassword"  name="userPassword" minlength="3" required>
            <div class="invalid-feedback">Password must be at least 3 characters.</div>
          </div>
          <button type="submit" class="btn btn-primary">Submit</button>
        </form>
      </div>
    </div>
  </div>
</div>
<script>
  (function () {
    'use strict';

    // Fetch the add user form element
    var loginForm = document.querySelector('#login .needs-validation');

    // Add event listener for add user form submission
    loginForm.addEventListener('submit', function (event) {
      if (!loginForm.checkValidity()) {
        event.preventDefault();
        event.stopPropagation();
      }

      loginForm.classList.add('was-validated');
    }, false);
  })();
</script>

</body>
</html>

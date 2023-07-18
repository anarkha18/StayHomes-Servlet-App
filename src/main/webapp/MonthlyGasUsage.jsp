
<%@ page import="java.util.List" %>
<%@ page import="com.stayhome.modal.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

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
          <h3>Monthly Gas Usage</h3>
          <button type="button" class="btn btn-outline-primary" data-bs-toggle="modal" data-bs-target="#gasusage">
            <i class="bi bi-plus-lg"></i> Add Gas Usage
          </button>
        </div>

        <table class="table table-striped table-hover">
          <thead>
          <tr class="">
            <th scope="col">Id</th>
            <th scope="col">Flat No</th>
            <th scope="col">Usage Month </th>
            <th scope="col">Units Consumed </th>
            <th scope="col">Rate per unit</th>
            <th scope="col">Amount</th>
            <th scope="col">Action</th>
          </tr>
          </thead>
          <tbody>
          <% List<GasUsage> gasUsages = (List<GasUsage>) request.getAttribute("monthlyGasUsages"); %>
          <% for (GasUsage i : gasUsages) { %>

          <tr>
            <th scope="row"><%= i.getId() %></th>
            <td><%= i.getFlatId().getFlatNo() %></td>
            <td><%= i.getUsageMonth() %></td>
            <td><%= i.getUnitsConsumed() %></td>
            <td><%= i.getRateId().getRate() %></td>
            <td><%= i.getAmount() %></td>

            <td>
              <a href="editUser?id=<%= i.getId() %>"> <button type="button" class="btn btn-outline-primary me-2">Edit</button></a>
              <a href="deleteUser?id=<%= i.getId() %>"> <button type="button" class="btn btn-primary">Delete</button></a>
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
<div class="modal fade" id="gasusage" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">Add Monthly gas usage</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <!-- Form in the popup -->
        <form class="needs-validation" novalidate action="AddGasUsage" method="post">
          <div class="mb-3">
            <label for="flat_id" class="form-label">Flat Number</label>
            <select class="form-control" id="flat_id" name="flat_id" required>
              <!-- Populate the select box with flat numbers from the flats table -->
              <option value="">Select Flat Number</option>
              <% List<flats> myFlats = (List<flats>) request.getAttribute("myFlats"); %>
              <% for (flats flat : myFlats) { %>
              <option value="<%= flat.getId() %>"><%= flat.getFlatNo() %></option>
              <% } %>
              <!-- Add more options as needed -->
            </select>
            <div class="invalid-feedback">Please select a flat number.</div>
          </div>
          <div class="mb-3">
            <label for="usage_month" class="form-label">Usage Month</label>
            <input type="date" class="form-control" id="usage_month" name="usage_month" required>
            <div class="invalid-feedback">Please enter a valid usage month.</div>
          </div>
          <div class="mb-3">
            <label for="units_consumed" class="form-label">Units Consumed</label>
            <input type="number" class="form-control" id="units_consumed" name="units_consumed" required>
            <div class="invalid-feedback">Please enter the units consumed.</div>
          </div>
            <% GasUnitRate myGas = (GasUnitRate) request.getAttribute("myGas"); %>
            <input type="number" class="form-control" id="rate_id" name="rate_id" value="<%=myGas.getId() %>" required hidden>
          <div class="mb-3">
            <label for="rate" class="form-label">Gas rate per unit</label>
            <input type="number" class="form-control" id="rate" name="rate" value="<%=myGas.getRate() %>" required readonly>
            <div class="invalid-feedback">Please enter the rate ID.</div>
          </div>
          <div class="mb-3">
            <label for="amount" class="form-label">Amount</label>
            <input type="number" class="form-control" id="amount" name="amount" required>
            <div class="invalid-feedback">Please enter the amount.</div>
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
    var gasusage = document.querySelector('#gasusage .needs-validation');

    // Add event listener for add user form submission
    gasusage.addEventListener('submit', function (event) {
      if (!gasusage.checkValidity()) {
        event.preventDefault();
        event.stopPropagation();
      }

      gasusage.classList.add('was-validated');
    }, false);
  })();
</script>

</body>
</html>
<% } %>

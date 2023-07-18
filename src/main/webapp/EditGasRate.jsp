
<%@ page import="com.stayhome.modal.flats" %>
<%@ page import="com.stayhome.modal.DailyHelpCategory" %>
<%@ page import="com.stayhome.modal.GasUnitRate" %>
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
                    <h3>Edit gas rate per unit</h3>
                </div>
                <% GasUnitRate gasRate = (GasUnitRate) request.getAttribute("gasRate"); %>
                <div class="container">
                    <div class="col-6" id="editGasRate">
                        <form class="needs-validation" novalidate action="UpdateGasRate" method="post">
                            <div class="mb-3">
                                <label for="id" class="form-label">Gas Rate Id</label>
                                <input type="text" class="form-control" id="id" value="<%= gasRate.getId()%>"  name="id" minlength="3"  readonly>
                                <div class="invalid-feedback">Please enter a valid ID.</div>
                            </div>
                            <div class="mb-3">
                                <label for="rate" class="form-label">Gas Rate</label>
                                <input type="text" class="form-control" id="rate" value="<%= gasRate.getRate()%>"  name="rate" required>
                                <div class="invalid-feedback">Please enter a valid rate.</div>
                            </div>
                            <button type="submit" class="btn btn-primary">Submit</button>
                            <a href="">
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

        // Fetch the edit gas rate form element
        var gasRateForm = document.querySelector('#editGasRate .needs-validation');

        // Add event listener for edit gas rate form submission
        gasRateForm.addEventListener('submit', function (event) {
            if (!gasRateForm.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
            }

            gasRateForm.classList.add('was-validated');
        }, false);
    })();
</script>

</body>
</html>
<% } %>
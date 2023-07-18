<%@ page import="com.stayhome.modal.flats" %>
<%@ page import="java.util.List" %>
<%@ page import="com.stayhome.modal.users" %>
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
                    <h3>Ownership</h3>
                    <button type="button" class="btn btn-outline-primary" data-bs-toggle="modal" data-bs-target="#ownership">
                        <i class="bi bi-plus-lg"></i> Add ownership
                    </button>
                </div>

                <table class="table table-striped table-hover">
                    <thead>
                    <tr class="">
                        <th scope="col">Id</th>
                        <th scope="col">Flat No</th>
                        <th scope="col">Flat Type</th>
                        <th scope="col">Owner Name</th>
                        <th scope="col">Owner Phone</th>
                        <th scope="col">Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% List<flats> flatsWithOwners = (List<flats>) request.getAttribute("flatsWithOwners"); %>
                    <% for (flats flat : flatsWithOwners) { %>
                    <tr>
                        <th scope="row"><%= flat.getId() %></th>
                        <td><%= flat.getFlatNo() %></td>
                        <td><%= flat.getFlatType() %></td>
                        <td><%= flat.getFlatOwner().getUserName() %></td>
                        <td><%= flat.getFlatOwner().getUserPhone() %></td>
                        <td>
                            <a href="editOwnership?id=<%= flat.getId() %>"> <button type="button" class="btn btn-outline-primary me-2">Edit</button></a>
                            <a href="deleteOwnership?id=<%= flat.getId() %>"> <button type="button" class="btn btn-primary">Delete</button></a>
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
<div class="modal fade" id="ownership" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="staticBackdropLabel">REGISTER OWNERSHIP</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form class="needs-validation" novalidate action="addOwner" method="post">
                    <div class="mb-3">
                        <label for="flatNo" class="form-label">Flat No</label>
                        <select class="form-select" id="flatNo" name="flatNo" required aria-label="select example">
                            <option value="">Choose Flat</option>
                            <% List<flats> flats = (List<flats>) request.getAttribute("flatsWithNoOwners"); %>
                            <% for (flats flatNoOwner : flats) { %>
                            <option value="<%= flatNoOwner.getFlatNo() %>"><%= flatNoOwner.getFlatNo() + " " +flatNoOwner.getFlatType()  %></option>
                            <% } %>
                        </select>
                        <div class="invalid-feedback">Invalid select feedback</div>
                    </div>
                    <div class="mb-3">
                        <label for="flatOwner" class="form-label">Flat owner</label>
                        <select class="form-select" id="flatOwner" name="flatOwner" required aria-label="select example">
                            <option value="">Select Owner</option>
                            <% List<users> ownerList = (List<users>) request.getAttribute("allOwners"); %>
                            <% for (users owner : ownerList) { %>
                            <option value="<%= owner.getId() %>"><%= owner.getUserName() + " (" + owner.getId() + ")" %></option>
                            <% } %>
                        </select>
                        <div class="invalid-feedback">Invalid select feedback</div>
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
        var ownership = document.querySelector('#ownership .needs-validation');

        // Add event listener for add user form submission
        ownership.addEventListener('submit', function (event) {
            if (!ownership.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
            }

            ownership.classList.add('was-validated');
        }, false);
    })();
</script>

</body>
</html>
<% } %>
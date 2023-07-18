<%@ page import="com.stayhome.modal.flats" %>
<%@ page import="java.util.List" %>
<%@ page import="com.stayhome.modal.rental" %>
<%@ page import="com.stayhome.modal.users" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
                    <h3>Rental</h3>
                    <button type="button" class="btn btn-outline-primary" data-bs-toggle="modal" data-bs-target="#rental">
                        <i class="bi bi-plus-lg"></i> Add Rental
                    </button>
                </div>

                <table class="table table-striped table-hover">
                    <thead>
                    <tr class="">
                        <th scope="col">Id</th>
                        <th scope="col">Flat No</th>
                        <th scope="col">Tenant Name</th>
                        <th scope="col">Flat Type</th>
                        <th scope="col">Tenant  Phone</th>
                        <th scope="col">Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% List<rental> rentalList = (List<rental>) request.getAttribute("rentalList"); %>
                    <% for (rental rental : rentalList) { %>
                    <tr>
                        <th scope="row"><%= rental.getId() %></th>
                        <td><%= rental.getFlat().getFlatNo() %></td>
                        <td><%= rental.getTenant().getUserName() %></td>
                        <td><%= rental.getFlat().getFlatType() %></td>
                        <td><%= rental.getTenant().getUserPhone() %></td>
                        <td>
                            <a href="editRental?id=<%= rental.getId() %>"> <button type="button" class="btn btn-outline-primary me-2">Edit</button></a>
                            <a href="deleteRental?id=<%= rental.getId() %>"> <button type="button" class="btn btn-primary">Delete</button></a>
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
<div class="modal fade" id="rental" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="staticBackdropLabel">REGISTER RENTAL</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form class="needs-validation" novalidate action="addRental" method="post">
                    <div class="mb-3">
                        <label for="flatId" class="form-label">Flat No</label>
                        <select class="form-select" id="flatId" name="flatId" required aria-label="select example">
                            <option value="">Choose Flat</option>
                            <% List<flats> flats = (List<flats>) request.getAttribute("flatsWithNoRental"); %>
                            <% for (flats myFlats : flats) { %>
                            <option value="<%= myFlats.getId() %>"><%= myFlats.getFlatNo() + " " +myFlats.getFlatType()  %></option>
                            <% } %>
                        </select>
                        <div class="invalid-feedback">Invalid select feedback</div>
                    </div>
                    <div class="mb-3">
                        <label for="tenantId" class="form-label">Flat owner</label>
                        <select class="form-select" id="tenantId" name="tenantId" required aria-label="select example">
                            <option value="">Select Tenant</option>
                            <% List<users> tenants = (List<users>) request.getAttribute("tenantsWithNoFlats"); %>
                            <% for (users tenant : tenants) { %>
                            <option value="<%= tenant.getId() %>"><%= tenant.getUserName() + " (" + tenant.getId() + ")" %></option>
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
        var rental = document.querySelector('#rental .needs-validation');

        // Add event listener for add user form submission
        rental.addEventListener('submit', function (event) {
            if (!rental.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
            }

            rental.classList.add('was-validated');
        }, false);
    })();
</script>

</body>
</html>
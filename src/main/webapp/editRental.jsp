<%@ page import="com.stayhome.modal.flats" %>
<%@ page import="com.stayhome.modal.users" %>
<%@ page import="java.util.List" %>
<%@ page import="com.stayhome.modal.rental" %>
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
                    <h3>EDIT RENTAL</h3>
                </div>
                <% rental myRental = (rental) request.getAttribute("myRental"); %>
                <div class="container">
                    <div class="col-6">
                        <form class="needs-validation" novalidate action="updateRental" method="post">
                            <div class="mb-3">
                                <label for="rentalId" class="form-label">Rental Id</label>
                                <input type="text" class="form-control" id="rentalId" value="<%= myRental.getId()%>"  name="rentalId" minlength="3" readonly>
                                <div class="invalid-feedback">Please enter a valid No.</div>
                            </div>
                            <div class="mb-3">
                                <label for="flatNo" class="form-label">Flat No</label>
                                <input type="text" class="form-control" id="flatNo" value="<%= myRental.getFlat().getFlatNo()%>"  name="flatNo" minlength="3" readonly>
                                <div class="invalid-feedback">Please enter a valid No.</div>
                            </div>
                            <div class="mb-3">
                                <label for="tenantId" class="form-label">Flat Tenant</label>
                                <% List<users> tenants = (List<users>) request.getAttribute("tenants"); %>
                                <select class="form-select" id="tenantId" name="tenantId" required aria-label="select example">
                                    <% for (users tenant : tenants) { %>
                                    <option value="<%= myRental.getTenant().getId() %>" selected><%= myRental.getTenant().getUserName() %></option>
                                    <option value="<%= tenant.getId() %>"><%= tenant.getUserName() %></option>
                                    <% } %>
                                </select>
                                <div class="invalid-feedback">Invalid select feedback</div>
                            </div>
                            <button type="submit" class="btn btn-primary">Submit</button>
                            <a href="rentals">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Back</button>
                            </a>
                        </form>
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>

</body>
</html>
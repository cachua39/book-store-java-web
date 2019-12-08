<%-- 
    Document   : customer_history
    Created on : Nov 23, 2019, 3:22:28 PM
    Author     : LeVaLu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">

        <link href="https://fonts.googleapis.com/css?family=Raleway:300,400,700,800&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/styles.css">


        <title>Book Store</title>
    </head>

    <body>
        <div class="container">
            <!-- User Option -->
            <div class="row mt-3 form-inline">
                <div class="col">
                    <h1 class="text-primary font-weight-bold">Book Store</h1>
                </div>
                <c:if test="${not empty sessionScope.ACCOUNT}" var="account">
                    <div class="ml-auto mx-3">
                        <a href="index.jsp">
                            <h3 class="font-italic font-weight-bold">${sessionScope.ACCOUNT.userId},</h3>
                        </a>
                    </div>
                    <div class="mx-3">
                        <a href="customer_cart.jsp">
                            <i class="fa fa-2x fa-shopping-cart"></i>
                        </a>
                    </div>
                    <div class="mx-3">
                        <a href="customer_history.jsp">
                            <h4 class="fa fa-lg fa-clipboard"></h4>
                        </a>
                    </div>
                    <div class="mx-3">
                        <a href="logout">
                            <h5 class="fa fa-2x fa-sign-out"></h5>
                        </a>
                    </div>
                </c:if>

                <c:if test="${not account}">
                    <a href="login.jsp">
                        <h4 class="text-primary font-weight-bold font-italic"><i class="fa fa-lg fa-sign-in"></i> Login</h4>
                    </a>
                </c:if>
            </div>

            <!-- Search Bar -->
            <div class="row mt-4">
                <div class="col">
                    <form action="searchHistory" method="POST" class="form-inline" id="formSearch">
                        <div class="form-group ml-5 ml-auto">
                            <label class="mx-3">Search By Date</label> 
                            <input type="date" name="txtDate" class="form-control input-material mr-5" placeholder="Search by Date" size="18">
                            <input type="text" name="txtName" class="form-control input-material" placeholder="Search by Name" size="18">
                            <button type="submit" class="btn btn-primary btn-material ml-4">Search</button>
                        </div>
                    </form>
                </div>
            </div>

            <!-- List orders -->
            <div class="row mt-4">
                <div class="col">
                    <table class="table mt-3">
                        <thead>
                        <th>No.</th>
                        <th>Order Code</th>
                        <th>Details</th>
                        <th>Total</th>
                        <th>Buy Date</th>
                        </thead>
                        <tbody id="tblHistory">
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <script src="js/jquery.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/customer_history.js"></script>
    </body>

</html>
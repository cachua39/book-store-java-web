<%-- 
    Document   : index.jsp
    Created on : Nov 22, 2019, 7:32:34 AM
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
                    <form action="searchByUser" method="POST" class="form-inline" id="formSearch">
                        <div class="form-group">
                            <label for="categorySelect">Category</label>
                            <select name="category" id="categorySelect" class="form-control input-material ml-2">
                            </select>
                        </div>
                        <div class="form-group ml-auto">
                            <label for="minPriceInput">min</label>
                            <input type="number" name="txtMin" placeholder="Min" id="minPriceInput" class="form-control input-material width-input-number ml-2 mr-1"
                                   min="0" pattern="^[0-9]+(\.[0-9]{1,2})?$" style="width: 120px">
                        </div>
                        <div class="form-group mr-3">
                            <input type="number" name="txtMax" placeholder="Max" id="maxPriceInput" class="form-control input-material width-input-number mr-2 ml-1"
                                   min="0" pattern="^[0-9]+(\.[0-9]{1,2})?$" style="width: 120px">
                            <label for="maxPriceInput">max</label>
                        </div>
                        <div class="form-group ml-5 ml-auto">
                            <input type="text" name="txtTitle" class="form-control input-material" placeholder="Search by Name" size="18">
                            <button type="submit" class="btn btn-primary btn-material ml-4">Search</button>
                        </div>
                    </form>
                </div>
            </div>

            <!-- List Book Products -->
            <div class="row mt-4">
                <div class="col">
                    <table class="table mt-3">
                        <thead>
                        <th>No.</th>
                        <th class="width-5"></th>
                        <th>Title</th>
                        <th>Author</th>
                        <th>Category</th>
                        <th>Price</th>
                        <th class="width-5"></th>
                        </thead>
                        <tbody id="tblBookByUser">

                        </tbody>
                    </table>
                </div>
            </div>

            <div class="modal fade" id="addToCartModal" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header" id="titleHeader">
                        </div>
                        <div class="modal-body" id="msgAddSuccess">
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary btn-material" data-dismiss="modal">OK</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="js/jquery.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/index.js"></script>
    </body>

</html>
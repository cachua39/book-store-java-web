<%-- 
    Document   : customer_cart
    Created on : Nov 23, 2019, 3:21:33 PM
    Author     : LeVaLu
--%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
            </div>

            <div class="row mt-3 mb-4">
                <div class="col text-center">
                    <h2 class="text-primary font-weight-bold">Your Cart</h2>
                </div>
            </div>

            <!-- List Book Products -->
            <div class="row mt-4">
                <div class="col">
                    <c:if test="${not empty sessionScope.CART.cart}" var="checkCart">
                        <form action="updateCart" method="POST">
                            <table class="table mt-3">
                                <thead>
                                <th>No.</th>
                                <th>Title</th>
                                <th class="text-center">Amount</th>
                                <th>Price</th>
                                <th>Total</th>
                                <th class="width-5"></th>
                                </thead>
                                <tbody>
                                    <c:forEach items="${sessionScope.CART.cart.values()}" var="item" varStatus="counter">
                                        <tr>
                                            <td>${counter.count}</td>
                                            <td>${item.title}</td>
                                            <td>
                                                <div class="input-group input-group-sm w-50 mx-auto">
                                                    <div class="input-group-prepend">
                                                        <button type="button" class="btn btn-primary btn-material" onclick="minus('${counter.count}')">
                                                            <i class="fa fa-minus"></i>
                                                        </button>
                                                    </div>
                                                    <input type="text" name="txtQuantities" class="form-control text-center" id="${counter.count}" value="${item.quantityCart}"
                                                           readonly>
                                                    <div class="input-group-append">
                                                        <button type="button" class="btn btn-primary btn-material" onclick="plus('${counter.count}')">
                                                            <i class="fa fa-plus"></i>
                                                        </button>
                                                    </div>
                                                </div>
                                            </td>
                                            <td>$ <p id="bookPrice${counter.count}" class="d-inline">${item.price}</p></td>
                                            <fmt:formatNumber var="subTotal" type="number" maxFractionDigits="2" minIntegerDigits="1" value="${item.quantityCart * item.price}"/>
                                            <td class="text-primary font-weight-bold">$ <p class="d-inline" id="bookSubTotal${counter.count}">${subTotal}</p></td>
                                            <td class="width-5">
                                                <c:url value="deleteFromCart" var="deleteLink">
                                                    <c:param name="bookId" value="${item.bookId}"/>
                                                </c:url>
                                                <a href="${deleteLink}" class="text-primary"><i class="fa fa-lg fa-times"></i></a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <div class="tbl-divider mt-5"></div>
                            <div class="row">
                                <div class="col-2 ml-auto">
                                    <h5 class="font-weight-bold">Tổng cộng: </h5>
                                </div>
                                <div class="col-2">
                                    <h5 class="text-primary font-weight-bold" >$ <p class="d-inline text-primary font-weight-bold" id="bookTotal">${sessionScope.CART.getTotal()}</p></h5>
                                </div>
                            </div>
                            <div class="tbl-divider"></div>
                            <div class="row mt-4">
                                <div class="col">
                                    <a href="loadInfoBeforeCheckOut" class="btn btn-primary btn-material float-right mx-3 px-5">Check Out</a>
                                    <button type="submit" class="btn btn-primary float-right mx-3 px-5 btn-material">Update Cart</a>
                                </div>
                            </div>
                        </form>
                    </c:if>
                    <c:if test="${not checkCart}">
                        <h5 class="text-primary font-weight-bold text-center">Your cart is empty</h5>
                    </c:if>
                </div>
            </div>
            <c:if test="${not empty requestScope.RESULT}">
                <c:if test="${requestScope.RESULT eq 'success'}">
                    <input type="hidden" value="showModal" id="toggleModal">
                    <c:remove var="RESULT" scope="request"/>
                </c:if>
            </c:if>


            <div class="modal fade" id="modalUpdateCart" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="text-danger">Success</h5>
                        </div>
                        <div class="modal-body">
                            Update Cart Success :)
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-dark" data-dismiss="modal">OK</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="js/jquery.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/user_cart.js"></script>
    </body>

</html>
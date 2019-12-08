<%-- 
    Document   : user_checkout
    Created on : Nov 24, 2019, 8:09:33 PM
    Author     : LeVaLu
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

            <!-- Customer information -->
            <div class="row mt-4">
                <div class="col">
                    <table class="table table-borderless">
                        <thead>
                        <th>
                            Customer Infomation
                        </th>
                        </thead>
                        <tbody>
                            <tr>
                                <td>UserId:</td>
                                <td>${CUSTOMER_INFO.userId}</td>
                            </tr>
                            <tr>
                                <td>Fullname:</td>
                                <td>${CUSTOMER_INFO.fullname}</td>
                            </tr>
                            <tr>
                                <td>Phone: </td>
                                <td>${CUSTOMER_INFO.phone}</td>
                            </tr>
                            <tr>
                                <td>Email:</td>
                                <td>${CUSTOMER_INFO.email}</td>
                            </tr>
                            <tr>
                                <td>Address:</td>
                                <td>${CUSTOMER_INFO.address}</td>
                            </tr>
                        </tbody>
                    </table>

                </div>
                <div class="col-1 vertical-divider"></div>
                <div class="col">
                    <table class="table mt-4">
                        <thead>
                        <th>Your Order</th>
                        <th class="text-right">Total</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${sessionScope.CART.cart.values()}" var="item">
                                <tr>
                                    <td>${item.title} - x${item.quantityCart}</td>
                                    <fmt:formatNumber value="${item.price * item.quantityCart}" var="subTotal" maxFractionDigits="2" minFractionDigits="1"/>
                                    <td class="text-right font-weight-bold"><i class="fa fa-dollar"></i> ${subTotal}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div class="tbl-divider"></div>

                    <div class="row mt-3">
                        <div class="col">
                            <button type="button" class="btn btn-primary btn-material" id="btnCheckCode">Check Code</button>
                        </div>
                        <div class="col-6">
                            <input type="text" id="txtCode" class="form-control input-material" placeholder="Discount Code">
                        </div>
                    </div>
                    <div class="row mt-2">
                        <div class="col">
                            <p class="h4 text-secondary font-weight-bold float-right" id="couponValue"><i class="fa fa-dollar"></i> <i>-0.0</i></p>
                        </div>
                    </div>
                    <div class="horizontal-divider"></div>
                    <div class="row mt-3">
                        <div class="col">
                            <p class="h4 font-weight-bold">Total</p>
                        </div>
                        <div class="col" id="txtTotal">
                            <p class="h3 text-primary font-weight-bold float-right"> ${sessionScope.CART.getTotal()}</p><i class="fa fa-dollar h3 text-primary font-weight-bold float-right"></i> 
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col">
                            <i class="text-danger font-weight-bold float-right">${requestScope.INVALID.orderError}</i>
                        </div>
                    </div>
                    <form method="POST" id="formCheckout">
                        <input type="hidden" name="txtDiscountCode" id="hiddenCode"/>
                        <div class="row mt-5">
                            <div class="col">
                                <button type="button" id="btnCheckout"
                                        class="btn btn-primary btn-material float-right font-weight-bold py-2 px-5 ml-4">Checkout</button>

                                <button type="button" id="btnPaypal" class="float-right btn p-0">
                                    <img src="https://www.paypalobjects.com/webstatic/en_US/i/buttons/checkout-logo-large.png"
                                         alt="Check out with PayPal" class="btn-material" style="width: 90% !important"/>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <script src="js/jquery.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/customer_checkout.js"></script>
    </body>

</html>
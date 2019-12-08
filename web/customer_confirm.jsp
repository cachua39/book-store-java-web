<%-- 
    Document   : customer_confirm
    Created on : Nov 25, 2019, 1:00:24 AM
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

            <!-- Confirm -->
            <div class="row mt-5 ml-5 mb-5">
                <c:if test="${not empty requestScope.ORDER.listBook}" var="list">
                    <div class="col-7 mx-auto">
                        <div class="row text-center">
                            <div class="col-12">
                                <h5>Cám ơn bạn đã mua hàng</h5>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col">

                                <table class="table">
                                    <thead>
                                    <th>Đơn hàng của bạn</th>
                                    <th class="text-right">Tổng</th>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${requestScope.ORDER.listBook}" var="item">
                                            <tr>
                                                <td>
                                                    <h5 class="font-weight-bold">${item.title} - x${item.quantityCart}</h5>
                                                </td>
                                                <td class="text-right">
                                                    <fmt:formatNumber value="${item.price * item.quantityCart}" var="subTotal" maxFractionDigits="2" minFractionDigits="1"/>
                                                    ${subTotal} 
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>

                            </div>
                        </div>
                        <div class="tbl-divider"></div>
                        <div class="row mt-3">
                            <div class="col-3 ml-auto text-right">
                                <h4 class="text-primary font-weight-bold font-italic">- ${requestScope.ORDER.discountValue}</h4>
                            </div>
                        </div>
                        <div class="tbl-divider"></div>
                        <div class="row mt-3">
                            <div class="col-3 ml-2">
                                <h4 class="text-primary font-weight-bold">Tổng</h4>
                            </div>
                            <div class="col-8 ml-auto text-right">
                                <h4 class="text-primary font-weight-bold">${requestScope.ORDER.total}</h4>
                            </div>
                        </div>

                    </div>
                </c:if>
                <c:if test="${not list}">
                    <h3>No data</h3>
                </c:if>
            </div>
        </div>

        <script src="js/jquery.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>

</html>
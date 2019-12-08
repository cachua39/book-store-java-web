<%-- 
    Document   : error.jsp
    Created on : Nov 22, 2019, 8:52:08 AM
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

        <title>Error Page</title>

    </head>

    <body>
        <div class="container">
            <div class="row mt-4">
                <div class="col">
                    <c:if test="${not empty sessionScope.ACCOUNT}" var="account">
                        <c:if test="${sessionScope.ACCOUNT.role eq 'Admin'}">
                            <a href="admin.jsp"><i class="fa fa-lg fa-arrow-left"></i> Home Page</i></a>
                        </c:if>
                        <c:if test="${sessionScope.ACCOUNT.role eq 'Customer'}">
                            <a href="index.jsp"><i class="fa fa-lg fa-arrow-left"></i> Home Page</a>
                        </c:if>
                    </c:if>
                    <c:if test="${not account}">
                        <a href="index.jsp"><i class="fa fa-lg fa-arrow-left"></i> Home Page</a>
                    </c:if>
                </div>
                <div class="col">
                    <c:if test="${not empty sessionScope.ACCOUNT}">
                        <a href="logout" class="float-right">Logout <i class="fa fa-lg fa-sign-out"></i></a>
                        </c:if>
                </div>
            </div>
            <div class="row mt-2">
                <div class="col-5">
                    <div class="row">
                        <img src="assets/error.png" alt="" class="img-error">
                    </div>
                </div>
                <div class="col-7">
                    <div class="row mt-5">
                        <h1 class="font-weight-bold text-danger">Something went wrong!</h1>
                    </div>
                </div>
            </div>
        </div>
        <script src="js/jquery.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>

</html>
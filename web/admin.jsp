<%-- 
    Document   : admin
    Created on : Nov 23, 2019, 3:26:41 PM
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
            <div class="row mt-3 form-inline">
                <div class="col">
                    <h1 class="text-primary font-weight-bold">Book Store</h1>
                </div>
                <div class="ml-auto">
                    <div class="mx-3">
                        <a href="admin.jsp">
                            <i class="h4 font-italic font-weight-bold">${sessionScope.ACCOUNT.userId}</i>
                        </a>
                        <a href="logout">
                            <i class="h5 font-weight-bold ml-3"><i class="fa fa-lg fa-sign-out"></i></i>
                        </a>
                    </div>
                </div>
            </div>
            <div class="row mt-4">
                <div class="col">
                    <h2 class="text-primary text-center font-weight-bold">Manage Book</h2>
                </div>
            </div>

            <div class="row mt-3">
                <div class="col">
                    <a href="admin_create_account.jsp" class="btn btn-primary btn-material mx-3">Create Account</a>
                    <a href="admin_create_book.jsp" class="btn btn-primary btn-material">Create Book</a>
                </div>
            </div>


            <!-- List Book Products -->
            <div class="row">
                <div class="col">
                    <c:if test="${empty sessionScope.LIST_BOOK}" var="listBook">
                        <table class="table mt-3">
                            <thead>
                            <th>No.</th>
                            <th class="width-5"></th>
                            <th>Title</th>
                            <th>Author</th>
                            <th>Category</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th class="width-5"></th>
                            <th class="width-5"></th>
                            </thead>
                            <tbody id="tblBookByAdmin">
                            </tbody>
                        </table>
                    </c:if>
                    <c:if test="${not listBook}">
                        <h3 class="mt-5 ml-4">No Book Found!</h3>
                    </c:if>
                </div>
            </div>
            
            <div class="modal fade" id="modalRemoveBook" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="text-warning">Warning</h5>
                        </div>
                        <div class="modal-body">
                            <p>Delete Book.</p>
                            <p>Are you sure?</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-dark" data-dismiss="modal">NO</button>
                            <button type="button" id="btnDeleteBook"  class="btn btn-primary btn-material btnYes">YES</button>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="modal fade" id="modalDeleteFaild" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="text-danger">Failed</h5>
                        </div>
                        <div class="modal-body">
                           Delete book failed!
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
        <script src="js/admin.js"></script>
    </body>

</html>
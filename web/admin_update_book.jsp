<%-- 
    Document   : admin_update_book.jsp
    Created on : Nov 24, 2019, 4:55:15 PM
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
            <div class="row my-3">
                <div class="col">
                    <h3 class="text-primary text-center font-weight-bold">Update Book</h3>
                </div>
            </div>

            <!-- Create Book Products -->
            <div class="row">
                <div class="col-2"></div>
                <div class="col-5 mx-auto">
                    <form action="UpdateBookByAdminController" method="POST" id="formAddBook" enctype="multipart/form-data">
                        <div class="form-group row">
                            <label for="bookIdInput" class="col-3 col-form-label">Book ID</label>
                            <div class="col-9">
                                <input type="hidden" name="txtBookId" value="${requestScope.BOOK.bookId}">
                                <input type="text" value="${requestScope.BOOK.bookId}" placeholder="Book ID" id="bookIdInput" class="form-control" disabled>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="titleInput" class="col-3 col-form-label">Book Title</label>
                            <div class="col-9">
                                <input type="text" name="txtTitle" value="${requestScope.BOOK.title}" placeholder="Book Title" id="titleInput" class="form-control input-material" required>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="authorInput" class="col-3 col-form-label">Author</label>
                            <div class="col-9">
                                <input type="text" name="txtAuthor" value="${requestScope.BOOK.author}" placeholder="Author" class="form-control input-material" id="authorInput" required>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="categorySelect" class="col-3 col-form-label">Category</label>
                            <div class="col-9">
                                <select name="optionCategory" id="categorySelect" class="form-control input-material">
                                    <c:forEach items="${requestScope.LIST_CATEGORY}" var="item">
                                        <option <c:if test="${requestScope.BOOK.categoryName eq item.name}">selected</c:if>>${item.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="priceInput" class="col-3 col-form-label">Price</label>
                            <div class="col-9">
                                <input type="text" name="txtPrice" value="${requestScope.BOOK.price}" pattern="^[0-9]+(\.[0-9]{1,2})?$" placeholder="Price" id="priceInput" class="form-control input-material" required>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="fileInput" class="col-3 col-form-label">Photo</label>
                            <div class="col-9">
                                <div class="custom-file input-file-material">
                                    <input type="file" name="filePhoto" class="custom-file-input" id="fileInput"/>
                                    <label for="fileInput" class="custom-file-label input-file-material">Choose file</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="quantityInput" class="col-3 col-form-label">Quantity</label>
                            <div class="col-9">
                                <input type="number" name="txtQuantity" value="${requestScope.BOOK.quantity}" placeholder="Quantity" min="0" id="quantityInput" class="form-control input-material" required>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="descInput" class="col-3 col-form-label">Description</label>
                            <div class="col-9">
                                <textarea name="txtDescription" id="descInput" class="form-control input-material" rows="5">${requestScope.BOOK.description}</textarea>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary btn-material float-right mt-3 mb-5" id="submitButton">Update Product</button>
                    </form>
                </div>
                <div class="col-3 float-left">
                    <img class="img-user" src="${requestScope.BOOK.photo}" id="imgPreview" alt="Your Image Here"/>
                </div>
            </div>
        </div>

        <script src="js/jquery.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/admin_update_book.js"></script>
    </body>

</html>
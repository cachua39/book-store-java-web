<%-- 
    Document   : admin_create_book
    Created on : Nov 23, 2019, 9:38:43 PM
    Author     : LeVaLu
--%>

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
                    <h3 class="text-primary text-center font-weight-bold">Create Book</h3>
                </div>
            </div>

            <!-- Create Book Products -->
            <div class="row">
                <div class="col-2"></div>
                <div class="col-5 mx-auto">
                    <form action="InsertBookByAdminController" method="POST" id="formAddBook" enctype="multipart/form-data">
                        <div class="form-group row">
                            <label for="bookIdInput" class="col-3 col-form-label">Book ID</label>
                            <div class="col-9">
                                <input type="text" name="txtBookId" placeholder="Book ID" id="bookIdInput" class="form-control input-material" required pattern="^B[0-9]{4,}">
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-3"></div>
                            <div class="col-9">
                                <i class="text-danger">${requestScope.INVALID.bookIdError}</i>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="titleInput" class="col-3 col-form-label">Book Title</label>
                            <div class="col-9">
                                <input type="text" name="txtTitle" placeholder="Book Title" id="titleInput" class="form-control input-material" required>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="authorInput" class="col-3 col-form-label">Author</label>
                            <div class="col-9">
                                <input type="text" name="txtAuthor" placeholder="Author" class="form-control input-material" id="authorInput" required>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="categorySelect" class="col-3 col-form-label">Category</label>
                            <div class="col-9">
                                <select name="optionCategory" id="categorySelect" class="form-control input-material"></select>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="priceInput" class="col-3 col-form-label">Price</label>
                            <div class="col-9">
                                <input type="text" name="txtPrice" pattern="^[0-9]+(\.[0-9]{1,2})?$" placeholder="Price" id="priceInput" class="form-control input-material" required>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="fileInput" class="col-3 col-form-label">Photo</label>
                            <div class="col-9">
                                <div class="custom-file input-file-material">
                                    <input type="file" name="filePhoto" class="custom-file-input" id="fileInput" required/>
                                    <label for="fileInput" class="custom-file-label input-file-material">Choose file</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="quantityInput" class="col-3 col-form-label">Quantity</label>
                            <div class="col-9">
                                <input type="number" name="txtQuantity" placeholder="Quantity" min="0" id="quantityInput" class="form-control input-material" required>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="descInput" class="col-3 col-form-label">Description</label>
                            <div class="col-9">
                                <textarea name="txtDescription" id="descInput" class="form-control input-material" rows="5"></textarea>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary btn-material float-right mt-3 mb-5" id="submitButton">Add Product</button>
                    </form>
                </div>
                <div class="col-3 float-left">
                    <img class="img-user" src="" id="imgPreview" alt="Your Image Here"/>
                </div>
            </div>
        </div>

        <script src="js/jquery.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/admin_create_book.js"></script>
    </body>

</html>
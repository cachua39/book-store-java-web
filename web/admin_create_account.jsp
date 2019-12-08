<%-- 
    Document   : admin_create_account
    Created on : Nov 23, 2019, 9:37:20 PM
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

        <title>Add User Page</title>
    </head>

    <body>
        <div class="container">
            <div class="row">
                <div class="col-3"></div>
                <div class="col-6">
                    <h2 class="text-center my-5 ml-5 font-weight-bold">Add User Page, <a href="admin.jsp" class="h4 text-primary font-italic font-weight-bold">go back ${sessionScope.USER_NAME}</a></h2>
                    <form action="insertAccount" method="POST">
                        <div class="form-group row mb-0">
                            <label for="usernameInput" class="col-3 col-form-label">UserId</label>
                            <div class="col-9">
                                <input type="text" name="txtUserId" placeholder="Username" id="usernameInput" class="form-control input-material" required>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-3"></div>
                            <div class="col-9">
                                <i class="text-danger">${requestScope.INVALID.accountError}</i>
                            </div>
                        </div>
                        <div class="form-group row mt-3">
                            <label for="passwordInput" class="col-3 col-form-label">Password</label>
                            <div class="col-9">
                                <input type="password" name="txtPassword" placeholder="Password" class="form-control input-material" required>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="fullNameInput" class="col-3 col-form-label">Fullname</label>
                            <div class="col-9">
                                <input type="text" name="txtFullname" placeholder="Fullname" id="fullNameInput"
                                       class="form-control btn-rounded-5 input-material" required>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="emailInput" class="col-3 col-form-label">Email</label>
                            <div class="col-9">
                                <input type="email" name="txtEmail" placeholder="Email" id="emailInput" class="form-control input-material" required>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="phoneInput" class="col-3 col-form-label">Phone</label>
                            <div class="col-9">
                                <input type="tel" name="txtPhone" placeholder="Phone" id="phoneInput" class="form-control input-material" required>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="addressInput" class="col-3 col-form-label">Address</label>
                            <div class="col-9">
                                <input type="text" name="txtAddress" placeholder="Address" id="addressInput" class="form-control input-material" required>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="selectRole" class="col-3 col-form-label">Role</label>
                            <div class="col-5">
                                <select name="cbRole" id="selectRole" class="form-control input-material">
                                </select>
                            </div>
                        </div>



                        <button type="submit" name="action" value="Insert" class="btn btn-primary btn-material float-right mt-4" id="submitButton">Create</button>
                    </form>
                </div>
               
            </div>
        </div>
        <script src="js/jquery.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/admin_insert_user.js"></script>
    </body>

</html>
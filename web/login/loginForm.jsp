<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login Form</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!--Bootstrap CDN link-->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <!--Font awesome CDN link-->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css"
          integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">

    <!--Google font CDN link-->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">

    <!--custom css -->
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<%
    String mess = (String) request.getAttribute("mess");
    if (mess != null) {
%>
<h1><%=mess%>
</h1>
<%}%>
<%--<form action="http://localhost:8080/ParkingLot/DoLogin" method="post">--%>
<%--    Tài khoản:--%>
<%--    <input name="email" type="text" value="nhap tai khoan">--%>
<%--    Mật khẩu:--%>
<%--    <input name="pass" type="password" value="nhap mat khau">--%>
<%--    <input type="submit">--%>
<div class="container" style="margin-top: 100px;">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="panel panel-default">
                <h4>Login With</h4><br>
                <div class="face-gg ">
                    <div class="btn-face" style="margin-top: 10px">
                        <%@include file="loginFB.jsp" %>
                    </div>
                    <div class="btn-gg ">
                        <%@include file="loginGG.jsp" %>
                    </div>
                </div>

                <br><br>
                <h4>OR</h4><br>
                <hr>
                <div class="panel-body">

                    <form action="http://localhost:8080/ParkingLot/LoginController" method="post">
                        <div class="form-group">
                            <input type="email" name="email" class="form-control" placeholder="Enter E-mail" required>
                        </div> <!--end of form group-->
                        <%--thêm hàm validateForm()--%>
                        <div class="form-group">
                            <input type="password" name="pass" class="form-control" placeholder="Enter Password"
                                   required>
                        </div> <!--end of form group-->

                        <div class="form-group">
                            <input type="submit" class="btn btn-success btn-lg btn-block" value="Submit">
                        </div> <!--end of form group-->

                        <a href="http://localhost:8080/ParkingLot/forgotPass/forgotPass.jsp" id="forget-btn" class="float-left">Forgot Password?</a>
                        <a href="http://localhost:8080/ParkingLot/signup/SignUp.jsp" id="forget-btn1" class="float-right">Sign Up a new Account?</a>
                    </form> <!--end of form-->

                </div> <!--end of panel-body-->
                <div class="login_pic"><i class="fa fa-lock fa-2x"></i></div>
            </div> <!--end of panel-default-->
        </div> <!--end of colmun-->
    </div> <!--end of row-->
</div> <!--end of container class-->

</body>
</html>

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
    <link rel="stylesheet" href="http://localhost:8080/ParkingLot/css/style.css">
    <script type="text/javascript">
        var onloadCallback = function () {
            grecaptcha.render('html_element', {
                'sitekey': '6LeQErkZAAAAAF5a-a6Ty8Cy5Ns-DB4CUku2hnbX'
            });
        };
    </script>
    <script type="text/javascript">
        function validateForm() {
            var username = document.myform.username.value;
            var pass = document.myform.pass.value;
            var repass = document.myform.repass.value;
            var email = document.myform.email.value
            var address = document.myform.address.value
            var phonenum = document.myform.phonenum.value
            var valid = true;
            if (fullname.trim() == "") {
                document.getElementById("errorfullname").innerHTML = "Hãy nhập họ tên";
                // myform["fullname"].focus();
                valid = false;
            } else {
                document.getElementById("errorfullname").innerHTML = "";
            }


            if (email.trim() == "") {
                document.getElementById("erroremail").innerHTML = "Hãy nhập email";
                // myform["email"].focus();
                valid = false;
            } else {
                document.getElementById("erroremail").innerHTML = "";
            }


            if (address.trim() == "") {
                document.getElementById("erroraddress").innerHTML = "Hãy nhập địa chỉ";
                // myform["address"].focus();
                valid = false;
            } else {
                document.getElementById("erroraddress").innerHTML = "";
            }


            if (phonenum.trim() == "") {
                document.getElementById("errorphonenum").innerHTML = "Hãy nhập số điện thoại";
                //myform["phonenum"].focus();
                valid = false;
            } else {
                document.getElementById("errorphonenum").innerHTML = "";
            }

            if (pass.trim() == "") {
                document.getElementById("errorpassword").innerHTML = "Hãy nhập mật khẩu";
                // myform["pass"].focus();
                valid = false;
            } else {
                document.getElementById("errorpassword").innerHTML = "";
            }


            if (repass.trim() == "") {
                document.getElementById("errorrepassword").innerHTML = "Hãy nhập mật khẩu một lần nữa";
                // myform["repass"].focus();
                valid = false;
            } else {
                document.getElementById("errorrepassword").innerHTML = "";
                return valid;
            }
        }
    </script>
</head>
<body>

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
                    <%
                        String mess = (String) request.getAttribute("mess");
                        if (mess != null) {
                    %>
                    <p style="color: red"><%=mess%>
                    </p>
                    <%}%>
                    <form action="http://localhost:8080/ParkingLot/LoginController" method="post" name="myform"> <%--4. doPost(HttpServletRequest request, HttpServletResponse response)--%>
                        <div class="form-group">
                            <input type="email" name="email" class="form-control" placeholder="Enter E-mail" required>
                            <p style="color: red;" id="erroremail"></p>
                        </div> <!--end of form group-->
                        <%--thêm hàm validateForm()--%>
                        <div class="form-group">
                            <input type="password" name="pass" class="form-control" placeholder="Enter Password"
                                   required>
                            <p style="color: red;" id="errorrepassword"></p>
                        </div> <!--end of form group-->
                        <%--                         captcha--%>
                        <div id="html_element"></div>
                        <br>
                        <%
                            String mess1 = (String) request.getAttribute("mess1");
                            if (mess1 != null) {
                        %>
                        <p style="color: red"><%=mess1%>
                        </p>
                        <%}%>
                        <div class="form-group">
                            <input type="submit" class="btn btn-success btn-lg btn-block" value="Submit">
                        </div> <!--end of form group-->

                        <a href="http://localhost:8080/ParkingLot/forgotPass/forgotPass.jsp" id="forget-btn"

                        <%--                           1.Click "Forgot Password" trên trang login--%>
                           class="float-left">Forgot Password?</a>
<%--                        2. Chuyển về trang quên mật khẩu--%>
                        <a href="http://localhost:8080/ParkingLot/signup/SignUp.jsp" id="forget-btn1"
                           class="float-right">Sign Up a new Account?</a>
                    </form> <!--end of form-->

                </div> <!--end of panel-body-->
                <div class="login_pic"><i class="fa fa-lock fa-2x"></i></div>
            </div> <!--end of panel-default-->
        </div> <!--end of colmun-->
    </div> <!--end of row-->
</div> <!--end of container class-->
<%
    if (session.getAttribute("count") != null) {
        int count = (int) session.getAttribute("count");
        if (count >= 3) {

%>

<script src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&render=explicit"
        async defer>

</script>
<%
        }
    }
%>


</body>
</html>

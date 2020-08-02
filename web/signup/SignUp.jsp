<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<%--    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>--%>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Sign Up Account</title>
	

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
	<script type="text/javascript">
		function validateForm() {
			var username = document.myform.username.value;
			var pass = document.myform.pass.value;
			var repass = document.myform.repass.value;
			var fullname = document.myform.fullname.value
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


			if (username.trim() == "") {
				document.getElementById("errorusername").innerHTML = "Hãy điền tên đăng nhập";
				// myform["username"].focus();
				valid = false;
			} else {
				document.getElementById("errorusername").innerHTML = "";
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

<!-- /SECTION -->
<div class="container" style="margin-top: 100px;">
	<div class="row">
		<div class="col-md-4 col-md-offset-4">
			<div class="panel panel-default">
				<h4>Sign Up</h4><br>
				<hr>
				<div class="panel-body">

					<%--                    <form action="http://localhost:8080/ParkingLot/DoLogin" method="post">--%>
						<c:url value="${request.getContextPath()}/RegisterController?vertifydk=true" var="urlregis"></c:url>
						<form action="/ParkingLot/RegisterController" name="myform" onsubmit="return validateForm()" method="post">
						<%--insert --%>
						<div class="form-group">
							<input class="form-control" type="text" name="fullname" placeholder="Họ và tên"
								   onkeyup="validateForm()" required>
							<p style="color: red;" id="errorfullname"></p>
						</div>
						<div class="form-group">
							<input class="form-control" type="email" name="email" placeholder="Nhập email"
								   onkeyup="validateForm()"required>
							<p style="color: red;" id="erroremail"></p>
						</div>
						<div class="form-group">
							<input class="form-control" type="text" name="address" placeholder="Địa chỉ"
								   onkeyup="validateForm()"required>
							<p style="color: red;" id="erroraddress"></p>
						</div>
						<div class="form-group">
							<input class="form-control" type="number" name="phonenum" placeholder="Nhập số điện thoại"
								   onkeyup="validateForm()"required>
							<p style="color: red;" id="errorphonenum"></p>
						</div>
						<div class="form-group">
							<input class="form-control" type="text" name="username" placeholder="Nhập tên truy cập"
								   onkeyup="validateForm()"required>
							<p style="color: red;" id="errorusername" ></p>
						</div>
						<div class="form-group">
							<input class="form-control" type="password" name="pass" placeholder="Mật khẩu"
								   onkeyup="validateForm()"required>
							<p style="color: red;" id="errorpassword"></p>
						</div>
						<div class="form-group">
							<input class="form-control" type="password" name="repass" placeholder="Nhập lại mật khẩu"
								   onkeyup="validateForm()"required>
							<p style="color: red;" id="errorrepassword"></p>
						</div>
						<div class="form-group">
							<input type="submit" class="btn btn-success btn-lg btn-block" value="Submit">
						</div>
						<%--                        --%>
						<%--               rule         --%>
						<div class="input-checkbox">
							<input type="checkbox" id="terms"> I have accept the <a href="#">terms & conditions</a><br>

						</div>
						<%--                        --%>
						<br>
						<a href="#" id="forget-btn" class="float-left"></a>
						<a href="http://localhost:8080/ParkingLot/login/loginForm.jsp" id="" class="float-right"
						   style="color: #5e7c23; font-size: 28px">Sign In</a>
					</form> <!--end of form-->

				</div> <!--end of panel-body-->
				<div class="login_pic"><i class="fa fa-lock fa-2x"></i></div>
			</div> <!--end of panel-default-->
		</div> <!--end of colmun-->
	</div> <!--end of row-->
</div>

</body>
</html>


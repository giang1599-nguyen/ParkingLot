<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>--%>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Sign Up Account</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!--Bootstrap CDN link-->
	<link rel="stylesheet"
		  href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
			src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<!--Font awesome CDN link-->
	<link rel="stylesheet"
		  href="https://use.fontawesome.com/releases/v5.2.0/css/all.css"
		  integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ"
		  crossorigin="anonymous">
	<!--Google font CDN link-->
	<link href="https://fonts.googleapis.com/css?family=Open+Sans"
		  rel="stylesheet">
	<!--custom css -->
	<link rel="stylesheet" href="http://localhost:8080/ParkingLot/css/style.css">

	<script type="text/javascript">
		//3.1 kiểm tra thông tin người dùng nhập
		function validateForm() {
//
			var pass = document.myform.pass.value;
			var repass = document.myform.repass.value;
			var fullname = document.myform.fullname.value
			var email = document.myform.email.value
			var address = document.myform.address.value
			var phonenum = document.myform.phonenum.value
			var valid = true;

			//pattern định dạng email
			var emailPattern = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
			//pattern định dạng sdt: chỉ chấp nhập số điện thoại có 10 chữ số
			var phonePattern = /^\(?([0-9]{3})\)?([ .-]?)([0-9]{3})\2([0-9]{4})$/;
			//pattern định dạng password: tối thiểu 8 kí tự, gồm chữ hoa, chữ thường, số và kí tự đặc biệt
			var passPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{8,})/

			//Các thuộc tính không được để trống
			if (fullname.trim() == "") {
				document.getElementById("errorfullname").innerHTML = "Hãy nhập họ tên";
				valid = false;
			} else {
				document.getElementById("errorfullname").innerHTML = "";
			}

			/////////////////////////////

			if (!emailPattern.test(email.trim())) {
				document.getElementById("erroremail").innerHTML = "Vui lòng nhập đúng định dạng email";
				valid = false;
			} else {
				document.getElementById("erroremail").innerHTML = "";
			}

			/////////////////////////////

			if (address.trim() == "") {
				document.getElementById("erroraddress").innerHTML = "Hãy nhập địa chỉ";
				valid = false;
			} else {
				document.getElementById("erroraddress").innerHTML = "";
			}

			/////////////////////////////

			if (!phonePattern.test(phonenum.trim())) {
				document.getElementById("errorphonenum").innerHTML = "Hãy nhập số điện thoại đúng định dạng";
				valid = false;
			} else {
				document.getElementById("errorphonenum").innerHTML = "";
			}

			/////////////////////////////

			if (!passPattern.test(pass.trim())) {
				document.getElementById("errorpassword").innerHTML = "Mật khẩu phải có 8 kí tự gồm chữ hoa, chữ thường, số, kí tự đặt biệt ";
				valid = false;
			} else {
				document.getElementById("errorpassword").innerHTML = "";
			}

			/////////////////////////////

			if (repass.trim() != pass.trim()) {
				document.getElementById("errorrepassword").innerHTML = "Hãy nhập lại mật khẩu một lần nữa";
				valid = false;
			} else {
				document.getElementById("errorrepassword").innerHTML = "";
			}

			/////////////////////////////

			return valid;
		}
	</script>
</head>
<body>
<%-- 2.Hiển thị form --%>
<!-- /SECTION -->
<div class="container" style="margin-top: 100px;">
	<div class="row">
		<div class="col-md-4 col-md-offset-4">
			<div class="panel panel-default">
				<h4>Sign Up</h4>
				<br>
				<hr>
				<div class="panel-body">
					<%-- <c:url
                        value="${request.getContextPath()}/RegisterController?vertifydk=true"
                        var="urlregis"></c:url>--%>

					<form action="/ParkingLot/RegisterController" name="myform"
						  onsubmit="return validateForm()" method="post">
						<%--insert --%>

						<div class="form-group">
							<input class="form-control" type="text" name="fullname"
								   placeholder="Họ và tên" onkeyup="validateForm()">
							<p style="color: red;" id="errorfullname"></p>
						</div>


						<div class="form-group">
							<input class="form-control" type="email" name="email"
								   placeholder="Nhập email" onkeyup="validateForm()">
							<p style="color: red;" id="erroremail"></p>
						</div>
							<%
								String mess = (String) request.getAttribute("mess");
								if (mess != null) {
							%>
							<p style="color: red"><%=mess%>
							</p>
							<%}%>

						<div class="form-group">
							<input class="form-control" type="text" name="address"
								   placeholder="Địa chỉ" onkeyup="validateForm()">
							<p style="color: red;" id="erroraddress"></p>
						</div>


						<div class="form-group">
							<input class="form-control" type="number" name="phonenum"
								   placeholder="Nhập số điện thoại:" onkeyup="validateForm()">
							<p style="color: red;" id="errorphonenum"></p>
						</div>


						<div class="form-group">
							<input class="form-control" type="password" name="pass"
								   placeholder="Mật khẩu" onkeyup="validateForm()">
							<p style="color: red;" id="errorpassword"></p>
						</div>


						<div class="form-group">
							<input class="form-control" type="password" name="repass"
								   placeholder="Nhập lại mật khẩu" onkeyup="validateForm()">
							<p style="color: red;" id="errorrepassword"></p>
						</div>


						<div class="form-group">
							<input type="submit" class="btn btn-success btn-lg btn-block"
								   value="Submit">
						</div>


						<%-- --%>
						<br> <a href="#" id="forget-btn" class="float-left"></a> <a
							href="http://localhost:8080/ParkingLot/login/loginForm.jsp"
							id="" class="float-right"
							style="color: #5e7c23; font-size: 28px">Login</a>
					</form>
					<!--end of form-->
				</div>
				<!--end of panel-body-->
				<div class="login_pic">
					<i class="fa fa-lock fa-2x"></i>
				</div>
			</div>
			<!--end of panel-default-->
		</div>
		<!--end of colmun-->
	</div>
	<!--end of row-->
</div>
</body>
</html>

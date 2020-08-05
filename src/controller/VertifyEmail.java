package controller;

import dao.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/vertifyEmail")
public class VertifyEmail extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
//7 Cho phep nguoi dung truy cap voi account da dang ki (chuyen active thanh 1)
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		System.out.println("Email : " + email);
		
		// 7.1 kiem tra lại su ton tai cua user
		Database db = new Database();
		if (db.getUser(email) != null)
			
			// 7.2 chuyen trang thai active thanh 1
			db.changeActive(email, 1);
		
			//7.3 Thong bao ket qua cho ng dung biet
		response.getWriter().write("<div style=\"width: 100%;height: 50px\">\n"
				+ "<h6 style=\"padding: 10px;text-align: center;font-size: 20px;\">Thank you for signing up.</h6>\n"
				+ "</div>");
	}
}

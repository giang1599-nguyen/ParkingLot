package controller;

import dao.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CompleteChangePass")
public class CompleteChangePass extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String email = request.getParameter("email");
             System.out.println("email là: " + email);
             if (email != null) {
                 String pass = request.getParameter("pass");
                 Database.changePass(email, pass);
                 Database.deleteToken(email);
                 ///chuyern trang login
                 response.sendRedirect("http://localhost:8080/ParkingLot/login/loginForm.jsp");
                 System.out.println(" thay đổi mật khẩu thành công");
             }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }


}

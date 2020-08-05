package controller;

import dao.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CompleteChangePass")
//11.1 Dung phuong thuc post de xu ly data
public class CompleteChangePass extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String email = request.getParameter("email");
             System.out.println("email là: " + email);
             // 11.2 lay du lieu tu form changePass.jps khong null thi goi toi method changePass cua database
             if (email != null) {
                 String pass = request.getParameter("pass");
                 Database.changePass(email, pass);
                 //11.3 doi pass thanh cong thi xoa token da luu trong database
                 Database.deleteToken(email);
                 //12 chuyen ve trang login
                 response.sendRedirect("http://localhost:8080/ParkingLot/login/loginForm.jsp");
                 System.out.println(" thay đổi mật khẩu thành công");
             }
    }
// 11. dung phuong thuc get de truy xuat du lieu
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }


}

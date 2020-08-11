package controller;

import dao.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/CompleteChangePass")
public class CompleteChangePass extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        System.out.println("email là: " + email);
        Database db= null;
        try {
            db = new Database();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (email != null) {
            //11.2 . gọi tới phương thức changePass trong database
            db.changePass(email, pass);
            db.deleteToken(email);
            //12 chuyern trang login
            response.sendRedirect("http://localhost:8080/ParkingLot/login/loginForm.jsp");
            System.out.println(" thay đổi mật khẩu thành công");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


}

package controller;

import dao.Database;
import dao.MD5Hashing;
import dao.SendMail;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

@WebServlet("/ForgotPassController")
public class ForgotPassController extends HttpServlet {
    //    4 doPost(HttpServletRequest request,HttpServletResponse response)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        Database db = null;
        try {
            db = new Database();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (db.getUser(email) == null) {
//            4a. return mess "Email không tồn tại'
            request.setAttribute("mess", "Email không tồn tại!");
            request.getRequestDispatcher("/forgotPass/forgotPass.jsp").forward(request, response);

        } else {
            String token = MD5Hashing.getRandomString(10);
//            5.1.1 gửi link vào mail người dùng
            SendMail.sendMail(email, "reset password for " + email, "click the following link to reset pass: " +
                    "http://localhost:8080/ParkingLot/ChangePass?email=" + email + "&token=" + token);
            db.createToken(email, token);
//7.  gửi thông báo check mail cho người dùng
            response.getWriter().write("<div style=\"width: 100%;height: 50px\">\n" +
                    "<h6 style=\"padding: 10px;text-align: center;font-size: 20px;\">Check mail to reset password.</h6>\n" +
                    "</div>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

package controller;

import dao.Database;
import dao.MD5Hashing;
import dao.SendMail;
import model.User;

import javax.mail.Session;
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
        // lấy thông tin email mà người dùng nhập từ form forgotPass
        String email = request.getParameter("email");

        Database db = null;
        try {
            //4.1. khởi tạo database ,lấy kết nối đến cơ sở dữ liệu
            db = new Database();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //4.2: kiểm tra có tồn tại 1 người dùng với email lấy từ request
        if (db.getUser(email) == null) {
//            4a. thông báo "Email không tồn tại'
            request.setAttribute("mess", "Email không tồn tại!");
//            4a1: chuyển người dùng về lại trang quên mật khẩu
            request.getRequestDispatcher("/forgotPass/forgotPass.jsp").forward(request, response);

        } else {
//            5. random 1 token ngẫu nhiên có độ dài bất kỳ
            String token = MD5Hashing.getRandomString(10);
//            5.1 Gửi link token tới email người dùng
            SendMail.sendMail(email, "reset password for " + email, "click the following link to reset pass: " +
                    "http://localhost:8080/ParkingLot/ChangePass?email=" + email + "&token=" + token);
            //6. lưu token xuống database
            db.updateToken(email, token);
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

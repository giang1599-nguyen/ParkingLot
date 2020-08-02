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
import java.util.Date;

@WebServlet("/ForgotPass")
public class ForgotPass extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        // random token và hash bằng MD5 tăng bảo mật
        String token = MD5Hashing.getRandomString(10);
        // gửi mail cho người dùng dưới dạng tiken đã hash
        SendMail.sendMail(email,"reset password for "+email,"click the following link to reset pass: " +
                "http://localhost:8080/ParkingLot/ChangePass?email="+email+"&token="+token);
        Database.createToken(email,token);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}


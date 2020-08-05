package controller;

import dao.Database;
import dao.GetConnectDatabase;
import dao.GoogleUtils;
import model.GooglePojo;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

@WebServlet("/LoginGGController")
public class LoginGGController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = "", email = "";
        User user;
// lấy code từ gg gửi về
        String code = request.getParameter("code");
        if (code == null || code.isEmpty()) {
            RequestDispatcher dis = request.getRequestDispatcher("url error page");
            dis.forward(request, response);
            System.out.println("code is empty");
            return;
        } else {
//lấy access token từ code
            String accessToken = GoogleUtils.getToken(code);
            System.out.println("access token: " + accessToken);
// dùng access token để lấy thông tin người dùng
            GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);
            name = googlePojo.getName();
            email = googlePojo.getEmail();
//nếu user chưa có trong database thì add vào
            if ((user = Database.getUser(email)) == null) {
                Database.addUser(email, name);
                    user = Database.getUser(email);
                
            }
//tạo session
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            request.setAttribute("mess", "Dang nhap thanh cong");
//chuyen qua trang home
response.sendRedirect("http://localhost:8080/ParkingLot/ParkingLot/");
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

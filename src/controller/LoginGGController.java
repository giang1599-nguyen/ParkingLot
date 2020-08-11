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
        Database db = null;
        try {
            db = new Database();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String code = request.getParameter("code");
        if (code == null || code.isEmpty()) {
            RequestDispatcher dis = request.getRequestDispatcher("url error page");
            dis.forward(request, response);
            System.out.println("code is empty");
            return;
        } else {
            String accessToken = GoogleUtils.getToken(code);
            System.out.println("access token: " + accessToken);
            GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);
            name = googlePojo.getName();
            email = googlePojo.getEmail();
            if ((user = db.getUser(email)) == null) {
               db.addUser(email, name);
                    user = db.getUser(email);

            }

            HttpSession session = request.getSession();
            session.setAttribute("user", user);
//            request.setAttribute("mess", "Dang nhap thanh cong");
            response.sendRedirect("http://localhost:8080/ParkingLot/");
//chuyen qua trang home
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

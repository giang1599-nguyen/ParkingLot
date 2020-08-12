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
        
        String code = request.getParameter("code");
        if (code == null || code.isEmpty()) {
            RequestDispatcher dis = request.getRequestDispatcher("url error page");
            dis.forward(request, response);
            System.out.println("code is empty");
            return;
        } else {
//5 getToken(code: String)
            String accessToken = GoogleUtils.getToken(code);
            System.out.println("access token: " + accessToken);
//6 getUserInfo(accessToken)
            GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);
//7 getName(), getEmail()
            name = googlePojo.getName();
            email = googlePojo.getEmail();
//8 tạo database
try {
            db = new Database();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//9 getUser(email)
            if ((user = db.getUser(email)) == null) {
//10 addUser(email,name)
               db.addUser(email, name);
//11 getUser(email)
                    user = db.getUser(email);

            }
//12 set session
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
//13. redirect home
            response.sendRedirect("http://localhost:8080/ParkingLot/");
//chuyen qua trang home
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

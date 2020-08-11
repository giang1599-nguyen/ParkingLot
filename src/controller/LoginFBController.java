package controller;

import dao.Database;
import dao.GetConnectDatabase;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

@WebServlet("/LoginFB")
public class LoginFBController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = "", email = "";
        User user = null;
        name = request.getParameter("name");
        email = request.getParameter("email");
        Database db = null;
        try {
            db = new Database();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Ten la: " + name + " email la: " + email);

        if ((user = db.getUser(email)) == null) {
           db.addUser(email, name);
                user = db.getUser(email);

        }

        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        request.setAttribute("mess", "Dang nhap thanh cong");
        // chuyen qua trang home
        response.sendRedirect("http://localhost:8080/ParkingLot/index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

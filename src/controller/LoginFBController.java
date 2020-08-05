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
        String name="",email="";
User user =null;
// lấy emai với tên
        name = request.getParameter("name");
        email = request.getParameter("email");

        System.out.println("Ten la: "+name+" email la: "+email);
// nếu user chưa có dưới database thì thêm vào
       if((user = Database.getUser(email)) == null)  {
           Database.addUser(email,name);
                user = Database.getUser(email);
        }
// tạo session cho người dùng
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        // chuyen qua trang home
        response.sendRedirect("http://localhost:8080/ParkingLot");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

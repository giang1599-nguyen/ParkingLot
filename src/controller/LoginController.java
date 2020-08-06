package controller;

import dao.Database;
import dao.GetConnectDatabase;
import model.User;
import model.VerifyRecaptcha;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {


    public LoginController() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        String email = "", passWord = "";
// lấy email,password và captcha
        email = request.getParameter("email");
        passWord = request.getParameter("pass");
	String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
        System.out.println("recaptcha " + gRecaptchaResponse);
// kiểm tra captcha có đúng không
 	boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
        System.out.println("email la: " + email);
        System.out.println("pass la: " + passWord)
       
        System.out.println("verify is: " + verify);
// tạo session để đếm số lần đăng nhập sai
        HttpSession session = request.getSession();

           if (gRecaptchaResponse == null || verify == true ) {
            System.out.println("captcha = null hoặc  verify = true");
            try {
                if (Database.checkUser(email.trim(), passWord.trim())) {
                    User user = Database.getUser(email);

                    session.setAttribute("count", 0);
                    session.setAttribute("user", user);

     
                    response.sendRedirect("http://localhost:8080/ParkingLot/");

                    System.out.println("count: " + session.getAttribute("count"));
                    return;
                }else{
    request.setAttribute("mess", "sai email hoac mat khau");
                    if (session.getAttribute("count") == null) {
                        session.setAttribute("count", 1);
                    } else session.setAttribute("count", (int) session.getAttribute("count") + 1);
                    System.out.println("count: " + session.getAttribute("count"));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("captcha != null và verify = false");
    request.setAttribute("mess1", "sai captcha");
            if (session.getAttribute("count") == null) {
                session.setAttribute("count", 1);
            } else session.setAttribute("count", (int) session.getAttribute("count") + 1);

            System.out.println("count: " + session.getAttribute("count"));

        }
   response.sendRedirect("http://localhost:8080/ParkingLot/login/loginForm.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        doPost(request, response);
    }
}

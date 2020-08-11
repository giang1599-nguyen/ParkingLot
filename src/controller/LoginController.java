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
        //lấy captcha, email và password
        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
        System.out.println("recaptcha " + gRecaptchaResponse);

        String email = "", passWord = "";

        email = request.getParameter("email");
        passWord = request.getParameter("pass");

// kiểm tra captcha có đúng không
        boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
        System.out.println("verify is: " + verify);

        HttpSession session = request.getSession();
// nếu không phải nhập captcha hoặc nhập captcha đúng
        if (gRecaptchaResponse == null || verify == true ) {
            System.out.println("captcha = null hoặc  verify = true");
            Database db;
            try {
                db = new Database();
                // nếu tài khoản mật khẩu đúng thì đăng nhập thành công
                if (db.checkUser(email.trim(), passWord.trim())) {
                    User user = db.getUser(email);

                    session.setAttribute("count", 0);
                    session.setAttribute("user", user);
//chuyển về trang home
                    response.sendRedirect("http://localhost:8080/ParkingLot/");

                    System.out.println("count: " + session.getAttribute("count"));
                    return;
                }else{
                    // nếu tài khoản mật khẩu sai thì thông báo email hoặc mật khẩu sai và chuyển về trang đăng nhập
                    request.setAttribute("mess","Email hoac mat khau sai");
                    System.out.println("ssss");
                    if (session.getAttribute("count") == null) {
                        session.setAttribute("count", 1);
                    } else session.setAttribute("count", (int) session.getAttribute("count") + 1);
                    System.out.println("count: " + session.getAttribute("count"));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else{
            // nếu có captcha mà không xác thực captcha thì thông báo captcha sai
            System.out.println("captcha != null và verify = false");
            request.setAttribute("mess1","Sai captcha");
            if (session.getAttribute("count") == null) {
                session.setAttribute("count", 1);
            } else session.setAttribute("count", (int) session.getAttribute("count") + 1);
//                    request.getRequestDispatcher("/login/loginForm.jsp").forward(request,response);
            System.out.println("count: " + session.getAttribute("count"));

        }
        request.getRequestDispatcher("login/loginForm.jsp").forward(request,response);
//        response.sendRedirect("http://localhost:8080/ParkingLot/login/loginForm.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        doPost(request, response);
    }
}

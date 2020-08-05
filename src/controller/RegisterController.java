package controller;

import dao.Database;
import dao.SendMail;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String email="",fullname="",password="",address="",phone="";
       email = request.getParameter("email");
        fullname = request.getParameter("fullname");
        password = request.getParameter("pass");
        address = request.getParameter("address");
        phone = request.getParameter("phonenum");


        User user = new User();
        user.setEmail(email);
        user.setFullname(fullname);
        user.setPassword(password);
        user.setAddress(address);
        user.setPhone(phone);
        if(Database.addUser(user)!=0){
            String link ="http://localhost:8080/ParkingLot/vertifyEmail?email="+email;
            SendMail.sendMail(email,"xac thuc email","click vao link sau de xac thuc email: "+ link  );
            
            response.getWriter().write("<div style=\"width: 100%;height: 50px\">\n" +
                    "<h6 style=\"padding: 10px;text-align: center;font-size: 20px;\">Please check your email to active your account.</h6>\n" +
                    "</div>");
        }
        else{
            request.setAttribute("mess","Email existed");
            request.getRequestDispatcher("signup/SignUp.jsp").forward(request,response);
        }

    }
}
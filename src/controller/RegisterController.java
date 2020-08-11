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
import java.sql.SQLException;


@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    //5.Lay thong tin nguoi dung nhap vao dua vao bien user: User va tien hanh dang ki thong tin nguoi dung
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

        //5.1Them user vao DB va check xem user ton tai chua, neu chua return 0
        //5.2. get Connection
        Database db = null;
        try {
            db = new Database();
        } catch (SQLException e) {
            e.printStackTrace();
        }
       //5.1
        if(db.addUser(user)!=0){

            //5.5Gui link xac nhan ve mail cho ng dung
            String link ="http://localhost:8080/ParkingLot/vertifyEmail?email="+email;
            SendMail.sendMail(email,"xac thuc email","click vao link sau de xac thuc email: "+ link  );

            //5.6 Hien thi thong bao yeu cau ng dung xac nhan link
            response.getWriter().write("<div style=\"width: 100%;height: 50px\">\n" +
                    "<h6 style=\"padding: 10px;text-align: center;font-size: 20px;\">Please check your email to active your account..</h6>\n" +
                    "</div>");
        }
        //5a.Neu email ton tai thi chuyen ve trang sign up va hien thi "Existed email"
        else{
            //5a chuyen ve trang sign up
            request.setAttribute("mess","Existed email");
            request.getRequestDispatcher("signup/SignUp.jsp").forward(request,response);
        }

    }
}
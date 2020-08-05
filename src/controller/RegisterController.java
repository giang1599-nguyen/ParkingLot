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

    //5.Lấy thông tin người dùng nhập đưa vào biến user: User và tiến hành đăng ký thông tin người dùng
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
        
        //5.1Thêm user vào database và kiểm tra xem đã tồn tại hay chưa, nếu chưa sẽ trả về 0
        Database db = new Database();
        if(db.addUser(user)!=0){
        	
        	//5.4Gửi link xác nhận về mail cho người dùng 
            String link ="http://localhost:8080/ParkingLot/vertifyEmail?email="+email;
            SendMail.sendMail(email,"xac thuc email","click vao link sau de xac thuc email: "+ link  );
            
            //5.5 Hiển thị thông báo yêu cầu người dùng xác nhận link
            response.getWriter().write("<div style=\"width: 100%;height: 50px\">\n" +
                    "<h6 style=\"padding: 10px;text-align: center;font-size: 20px;\">Vui lòng nhấn vào link xác nhận tại email vừa nhập.</h6>\n" +
                    "</div>");
        }
        //5a.Chuyển về trang SingUp để hiển thị lỗi
        else{	
            request.setAttribute("mess","Email đã tồn tại");
            request.getRequestDispatcher("signup/SignUp.jsp").forward(request,response);
        }

    }
}
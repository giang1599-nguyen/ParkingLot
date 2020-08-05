package controller;

import dao.Database;
import dao.MD5Hashing;
import dao.SendMail;
import model.User;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@WebServlet("/ForgotPassController")
public class ForgotPassController extends HttpServlet {
// 4.1 Dung phuong thuc post de xu ly data dam bao bao mat thong tin
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        // 4.2 goi phuong thuc kiểm tra user trong database

         if (Database.getUser(email) == null) {
         // 4a. thong bao email khong ton tai
                    request.setAttribute("mess", "Email không tồn tại!");
                    request.getRequestDispatcher("/forgotPass/forgotPass.jsp").forward(request, response);

                } else {
                //5. goi toi lop MD5Hashing random chuoi token voi 10 ky tu
        // random token và hash bằng MD5 tăng bảo mật
        String token = MD5Hashing.getRandomString(10);
        //5.1 goi lop SendMail gửi mail cho người dùng dưới dạng token đã hash
        SendMail.sendMail(email,"reset password for "+email,"click the following link to reset pass: " +
                "http://localhost:8080/ParkingLot/ChangePass?email="+email+"&token="+token);
                //6. Sau khi gui mail xong tao token xuong database
        Database.createToken(email,token);
        // 7. gui phan hoi cho nguoi dung biet la he thong da gui mail va nguoi dung hay check mail de doi mat khau
     response.getWriter().write("<div style=\"width: 100%;height: 50px\">\n" +
                             "<h6 style=\"padding: 10px;text-align: center;font-size: 20px;\">Check mail to reset password.</h6>\n" +
                             "</div>");
        }

    }
// 4. dung phuong thuc get de truy xuat du lieu nhanh hon va goi toi phuong thuc post (vi webbrowser se luu lai cached)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}


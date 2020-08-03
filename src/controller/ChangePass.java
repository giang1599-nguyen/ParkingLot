package controller;

import dao.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ChangePass")
public class ChangePass extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email= request.getParameter("email");
        String token = request.getParameter("token");
        //nếu kiểm tra trùng email và token thì cho đổi mật khẩu
               if (Database.checkToken(email, token)) {
                   request.setAttribute("email", email);
                   request.getRequestDispatcher("forgotPass/changePass.jsp").forward(request, response);
                   return;
               } else {
                   // không thì thông báo check lại mail cho lựa chọn chuyển về trang quên mật khẩu
                   response.getWriter().write("<div style=\"width: 100%;height: 50px\">\n" +
                           "<h6 style=\"padding: 10px;text-align: center;font-size: 20px;\">Check mail to reset password.</h6>\n" +
                           "</div>" + "<a href='#'>" + "Forgot password page" + "</a>");
               }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

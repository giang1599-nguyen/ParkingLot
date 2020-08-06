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
//9.1 Dung phuong thuc post de xu ly data
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email= request.getParameter("email");
        String token = request.getParameter("token");
        //9.1.1 kiem tra link ma nguoi dung nhap vao trung email va token thi cho doi mat khau
        Database db = new Database();
               if (db.checkToken(email, token)) {
                   request.setAttribute("email", email);
                   //9.1.2 kiem tra ok chuyen va trang doi mat khau
                   request.getRequestDispatcher("forgotPass/changePass.jsp").forward(request, response);
                   return;
               } else {
                   // 9.1.1a khong trung thong bao link het han
                  response.getWriter().write("<div style=\"width: 100%;height: 50px\">\n" +
                    "<h6 style=\"padding: 10px;text-align: center;font-size: 20px;\">Link expired</h6>\n" +
                    "</div>" + "<a href='http://localhost:8080/ParkingLot/forgotPass/forgotPass.jsp' style=\"\n" +
                    "    /* margin: auto; */\n" +
                    "    display: flex;\n" +
                    "    justify-content: center;\n" +
                    "\">" + "Forgot password page" + "</a>");
        }
    }
// 9. dung phuong thuc get de truy xuat du lieu
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

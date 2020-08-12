package controller;

import dao.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/ChangePass")
public class ChangePass extends HttpServlet {
    //    9. doPost(HttpServletRequest request,HttpServletResponse response)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // lấy dữ liệu request từ link token
        String email = request.getParameter("email");
        String token = request.getParameter("token");
        Database db = null;
        try {
//            9.1 tạo database lấy kết nối xg csdl
            db = new Database();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //9.2 nếu kiểm tra trùng email và token thì cho đổi mật khẩu
        if (db.checkToken(email, token)) {
            // set email lấy từ link token
            request.setAttribute("email", email);
            //9.2.1 : chuyển về trang change pass
            request.getRequestDispatcher("forgotPass/changePass.jsp").forward(request, response);
            return;
        } else {
            // 9.2.2 không thì thông báo check lại mail cho lựa chọn chuyển về trang quên mật khẩu

            response.getWriter().write("<div style=\"width: 100%;height: 50px\">\n" +
                    "<h6 style=\"padding: 10px;text-align: center;font-size: 20px;\">Link expired</h6>\n" +
                    "</div>" + "<a href='http://localhost:8080/ParkingLot/forgotPass/forgotPass.jsp' style=\"\n" +
                    "    /* margin: auto; */\n" +
                    "    display: flex;\n" +
                    "    justify-content: center;\n" +
                    "\">" + "Forgot password page" + "</a>");
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

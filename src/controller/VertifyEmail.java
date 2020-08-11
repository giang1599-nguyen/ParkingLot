package controller;

import dao.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/vertifyEmail")
public class VertifyEmail extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    //7 Hệ thống đổi active của user thành 1
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        System.out.println("Email : " + email);

        // 7.1 kiem tra lại su ton tai cua user
        Database db = null;
        try {
            db = new Database();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (db.getUser(email) != null)

            // 7.2 chuyen trang thai active thanh 1
            db.changeActive(email, 1);


//        response.getWriter().write("<div style=\"width: 100%;height: 50px\">\n"
//                + "<h6 style=\"padding: 10px;text-align: center;font-size: 20px;\">Thank you for signing up.</h6>\n"
//                + "</div>"+ "<a href='http://localhost:8080/ParkingLot/' style=\"\n" +
//                "    /* margin: auto; */\n" +
//                "    display: flex;\n" +
//                "    justify-content: center;\n" +
//                "\">" + "Home Page" + "</a>");
        //7.3 Chuyen ve trang chu
        response.sendRedirect("http://localhost:8080/ParkingLot/");
    }
}

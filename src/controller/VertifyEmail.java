package controller;

import dao.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/vertifyEmail")
public class VertifyEmail extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        System.out.println("Email : " + email);
        Database db = new Database();
        if (db.getUser(email) != null)
            db.changeActive(email, 1);

        response.getWriter().write("<div style=\"width: 100%;height: 50px\">\n" +
                "<h6 style=\"padding: 10px;text-align: center;font-size: 20px;\">Thank you for signing up.</h6>\n" +
                "</div>");
    }
}

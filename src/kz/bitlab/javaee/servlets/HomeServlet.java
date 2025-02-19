package kz.bitlab.javaee.servlets;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bitlab.javaee.database.DBConnector;
import kz.bitlab.javaee.models.User;

import java.io.IOException;

@WebServlet(value = "/home")
public class HomeServlet extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("CURRENT_USER");
        if (user == null) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        request.setAttribute("user", user);
        request.setAttribute("news", DBConnector.getNews());
        request.setAttribute("comments", DBConnector.getComments());
        request.setAttribute("categories", DBConnector.getCategories());

        String page = user.getRole_id().equals("1") ? "admin.jsp" : "index.jsp";
        request.getRequestDispatcher(page).forward(request, response);

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}
package kz.bitlab.javaee.servlets;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bitlab.javaee.database.DBConnector;
import kz.bitlab.javaee.models.Category;
import kz.bitlab.javaee.models.News;
import kz.bitlab.javaee.models.User;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/home")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("CURRENT_USER");
        if(user==null){
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }else{
            String page = user.getRole_id().equals("1") ? "admin.jsp" : "index.jsp";
            ArrayList<Category> categories = DBConnector.getCategories();
            ArrayList<News> news = DBConnector.getNews();
            req.setAttribute("news", news);
            req.setAttribute("categories", categories);
            req.setAttribute("user", user);
            req.getRequestDispatcher(page).forward(req, resp);

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("CURRENT_USER");
        if(user==null){
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }else{
            ArrayList<Category> categories = DBConnector.getCategories();
            ArrayList<News> news = DBConnector.getNews();
            req.setAttribute("news", news);
            req.setAttribute("categories", categories);
            req.setAttribute("user", user);
            String page = user.getRole_id().equals("1") ? "admin.jsp" : "index.jsp";
            req.getRequestDispatcher(page).forward(req, resp);
        }
    }
}
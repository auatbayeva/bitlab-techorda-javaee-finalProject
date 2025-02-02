package kz.bitlab.javaee.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bitlab.javaee.database.DBConnector;
import kz.bitlab.javaee.models.Category;
import kz.bitlab.javaee.models.User;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/users")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("CURRENT_USER");
        if(user==null){
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }else{
            if(user.getRole_id().equals("1")){
                ArrayList<User> users = DBConnector.getUsers();
                req.setAttribute("users", users);
                req.setAttribute("user", user);
                req.getRequestDispatcher("users.jsp").forward(req, resp);
            }else{
                resp.sendRedirect("/home");
            }

        }
    }
}

package kz.bitlab.javaee.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bitlab.javaee.database.DBConnector;
import kz.bitlab.javaee.models.User;

import java.io.IOException;

@WebServlet(value = "/comments")
public class CommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("CURRENT_USER");
        if(user==null){
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }else{
            if(user.getRole_id().equals("1")){
                req.setAttribute("user", user);
                req.setAttribute("comments", DBConnector.getComments());
                req.getRequestDispatcher("comments.jsp").forward(req, resp);
            }else{
                resp.sendRedirect("/home");
            }

        }
    }
}

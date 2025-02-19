package kz.bitlab.javaee.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bitlab.javaee.database.DBConnector;
import kz.bitlab.javaee.models.Category;
import kz.bitlab.javaee.models.Comment;
import kz.bitlab.javaee.models.News;
import kz.bitlab.javaee.models.User;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value="/commentDetail")
public class CommentDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("CURRENT_USER");
        if(user==null){
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }else{
            if(user.getRole_id().equals("1")){
                Long commentId = Long.parseLong(req.getParameter("id"));
                req.setAttribute("user", user);
                Comment comment = DBConnector.getCommentById(commentId);
                req.setAttribute("comment", comment);
                req.getRequestDispatcher("commentDetail.jsp").forward(req, resp);
            }else{
                resp.sendRedirect("/home");
            }

        }
    }
}

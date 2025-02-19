package kz.bitlab.javaee.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bitlab.javaee.database.DBConnector;
import kz.bitlab.javaee.models.Comment;
import kz.bitlab.javaee.models.User;

import java.io.IOException;

@WebServlet(value = "/deleteComment")
public class DeleteCommentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("CURRENT_USER");
        if(user==null){
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }else{
            Long commentId = Long.parseLong(req.getParameter("commentId"));
            Comment comment = DBConnector.getCommentById(commentId);
            if(user.getRole_id().equals("2")){
                if(comment!=null && comment.getUser().getId().equals(user.getId())){
                    DBConnector.deleteComment(commentId);
                    resp.sendRedirect("/");
                }
            }else{
                DBConnector.deleteComment(commentId);
                resp.sendRedirect("/comments");
            }


        }
    }
}

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

import java.io.Console;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet(value = "/addComment")
public class AddCommentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("CURRENT_USER");
        if(user==null){
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }else{
            if(user.getRole_id().equals("2")){
                Long postId = Long.parseLong(req.getParameter("postId"));
                String commentText = req.getParameter("comment");
                System.out.println(commentText + " " + postId + " userid=" + user.getId());
                Comment comment = new Comment();
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                comment.setComment(commentText);
                comment.setUser(user);
                comment.setPostDate(timestamp);
                News post = DBConnector.getNewsById(postId);
                comment.setNews(post);
                DBConnector.addComment(comment);
                resp.sendRedirect("/");
            }


        }


    }
}

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
import java.sql.Timestamp;

@WebServlet(value = "/editNews")
public class EditNewsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("CURRENT_USER");
        if(user==null){
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }else{
            if(user.getRole_id().equals("1")){
                Long id = Long.parseLong(req.getParameter("postId"));
                String title = req.getParameter("title");
                String content = req.getParameter("content");
                Long categoryId = Long.parseLong(req.getParameter("categoryId"));
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());

                Category category = new Category();
                category.setId(categoryId);

                News news = new News();
                news.setTitle(title);
                news.setContent(content);
                news.setCategory(category);
                news.setId(id);
                news.setPostDate(timestamp);

                DBConnector.updateNews(news);
                resp.sendRedirect("/home");


            }else{
                resp.sendRedirect("/home");
            }

        }
    }
}

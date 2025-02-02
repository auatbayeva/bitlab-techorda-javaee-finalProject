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
import java.sql.Timestamp;
import java.io.IOException;

@WebServlet(value = "/addNews")
public class AddNewsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("CURRENT_USER");
        if(user==null){
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }else{
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
            news.setPostDate(timestamp);

            DBConnector.addNews(news);
            resp.sendRedirect("/home");

        }

    }
}

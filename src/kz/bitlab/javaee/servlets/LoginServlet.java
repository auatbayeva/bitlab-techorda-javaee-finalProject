package kz.bitlab.javaee.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bitlab.javaee.database.DBConnector;
import kz.bitlab.javaee.models.User;

import java.io.IOException;

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("CURRENT_USER");
        if(user!=null){
            resp.sendRedirect("/home");
        }
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = DBConnector.getUserByEmail(email);

        if(user!=null&& password.equals(user.getPassword())){
            req.getSession().setAttribute("CURRENT_USER", user);
            req.setAttribute("user", user);
            System.out.println(user.getFirstName() + " " + user.getLastName());
            req.getRequestDispatcher("/home").forward(req, resp);
        }else{
            System.out.println("Sdfsdfdf");
            resp.sendRedirect("/login?error");
        }


    }
}

package kz.bitlab.javaee.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bitlab.javaee.database.DBConnector;
import kz.bitlab.javaee.models.User;

import java.io.IOException;

@WebServlet(value = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("CURRENT_USER");
        if(user!=null){
            resp.sendRedirect("/home");
        }
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        String role = req.getParameter("role");

        System.out.println( firstName + " " + lastName + " " + email + " " + password + " " + confirmPassword );
        String redirect = "/register?emailerror";
        User user  = DBConnector.getUserByEmail(email);
        if(user==null) {
            redirect = "/register?passworderror";
            if (password.equals(confirmPassword)) {
                redirect = "/home";
                user = new User();
                user.setEmail(email);
                user.setPassword(password);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setRole_id(role);
                DBConnector.addUser(user);
                req.getSession().setAttribute("CURRENT_USER", user);
                req.getRequestDispatcher("index.jsp").forward(req, resp);

            }
        }
        resp.sendRedirect(redirect);
    }
}

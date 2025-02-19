package kz.bitlab.javaee.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bitlab.javaee.database.DBConnector;
import kz.bitlab.javaee.models.User;

import java.io.IOException;

@WebServlet(value = "/editUser")
public class EditUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("CURRENT_USER");
        if(user==null){
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }else{
            if(user.getRole_id().equals("1")){
                Long userId = Long.parseLong(req.getParameter("userId"));
                String email = req.getParameter("email");
                String firstName = req.getParameter("firstName");
                String lastName = req.getParameter("lastName");
                String password = req.getParameter("password");
                String roleId = req.getParameter("roleId");
                User newUser = new User();
                newUser.setId(userId);
                newUser.setEmail(email);
                newUser.setFirstName(firstName);
                newUser.setLastName(lastName);
                newUser.setPassword(password);
                newUser.setRole_id(roleId);

                DBConnector.updateUser(newUser);
                resp.sendRedirect("/users");


            }else{
                resp.sendRedirect("/home");
            }

        }
    }
}

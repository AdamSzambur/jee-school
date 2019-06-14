package pl.coderslab.controller;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.db.dao.UserDao;
import pl.coderslab.db.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("email")!="" && request.getParameter("password")!="") {
            UserDao userDao = new UserDao();
            User user = null;

            try {
                user = userDao.readByEmail(request.getParameter("email"), request.getParameter("password"));
            } catch(RuntimeException ex) {


                request.setAttribute("msg",ex.getMessage());
                getServletContext().getRequestDispatcher("/login.jsp").forward(request,response);
            }

            if (user != null) {
                // zalogowalismy sie jako uzytkownik dodajemy go do session jako logedUser
                HttpSession session = request.getSession();
                session.setAttribute("logedUser", user);
                response.sendRedirect(getServletContext().getContextPath()+"/");
            } else {
                request.setAttribute("msg","Nie ma uzytkownika o podanym adresie e-mail w bazie.");
                getServletContext().getRequestDispatcher("/login.jsp").forward(request,response);
            }
        } else {
            request.setAttribute("msg","Niepoprawnie wypełniony formularz.");
            getServletContext().getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/login.jsp").forward(request,response);
    }
}

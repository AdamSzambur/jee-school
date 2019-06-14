package pl.coderslab.controller.app.user;

import pl.coderslab.db.dao.UserDao;
import pl.coderslab.db.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/app/user/userParam")
public class UserParameters extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("logedUser");

        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("rePassword");



        if (!userName.equals("") && (!email.equals("")) && (password.equals(rePassword) && (!password.equals("")))) {
            user.setUserName(userName);
            user.setEmail(email);
            user.setPassword(password);
            try {
                new UserDao().update(user);
            } catch (RuntimeException ex) {
                request.setAttribute("msg", ex.getMessage());
                getServletContext().getRequestDispatcher("/userParam.jsp").forward(request,response);
            }
            session.setAttribute("logedUser", user);
            response.sendRedirect(getServletContext().getContextPath()+"/");
        } else {
            request.setAttribute("msg", "Źle wypełniony formularz.");
            getServletContext().getRequestDispatcher("/userParam.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/userParam.jsp").forward(request,response);
    }


    public static Integer getIntParameter(HttpServletRequest request, String parameterName) {
        try {
            return Integer.parseInt(request.getParameter(parameterName));
        } catch (Exception ex) {
            return null;
        }
    }
}

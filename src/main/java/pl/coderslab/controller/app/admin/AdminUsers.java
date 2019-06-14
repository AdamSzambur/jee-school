package pl.coderslab.controller.app.admin;

import pl.coderslab.db.dao.UserDao;
import pl.coderslab.db.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/admin/adminUsers")
public class AdminUsers extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userDeleteId = getIntParameter(request,"userDeleteId");

        if (userDeleteId!=null) {

            try {
                new UserDao().delete(userDeleteId);
            } catch (RuntimeException ex) {
                request.setAttribute("msg",ex.getMessage());
            }
            UserDao userDao = new UserDao();
            List<User> userList = userDao.findAll();
            request.setAttribute("userList", userList);
            getServletContext().getRequestDispatcher("/adminUsers.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userDeleteId = getIntParameter(request,"userDeleteId");
        if (userDeleteId!=null) {
            request.setAttribute("userDelete", new UserDao().read(userDeleteId));
            request.setAttribute("userDeleteId", userDeleteId);
        } else {
            UserDao userDao = new UserDao();
            List<User> userList = userDao.findAll();
            request.setAttribute("userList", userList);
        }

        getServletContext().getRequestDispatcher("/adminUsers.jsp").forward(request,response);
    }

    public static Integer getIntParameter(HttpServletRequest request, String parameterName) {
        try {
            return Integer.parseInt(request.getParameter(parameterName));
        } catch (Exception ex) {
            return null;
        }
    }
}

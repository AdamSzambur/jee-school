package pl.coderslab.controller.app.admin;

import pl.coderslab.db.dao.GroupDao;
import pl.coderslab.db.dao.UserDao;
import pl.coderslab.db.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/app/admin/adminUserAdd")
public class AdminUserAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Integer groupId = getIntParameter(request,"groupId");
        User user = new User(userName,email,password,groupId);

        if (!email.equals("") && !password.equals("") && !userName.equals("")) {
            UserDao userDao = new UserDao();
            try {
                userDao.create(user);
            }catch (RuntimeException ex) {
                request.setAttribute("user",user);
                request.setAttribute("groupList", new GroupDao().findAll());
                request.setAttribute("msg",ex.getMessage());
                getServletContext().getRequestDispatcher("/adminUserAdd.jsp").forward(request,response);
            }
            response.sendRedirect(getServletContext().getContextPath()+"/app/admin/adminUsers");
        } else {
            request.setAttribute("user",user);
            request.setAttribute("groupList", new GroupDao().findAll());
            request.setAttribute("msg","Niepoprawnie wypełniony formularz.");
            getServletContext().getRequestDispatcher("/adminUserAdd.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("groupList", new GroupDao().findAll());
        getServletContext().getRequestDispatcher("/adminUserAdd.jsp").forward(request,response);
    }

    public static Integer getIntParameter(HttpServletRequest request, String parameterName) {
        try {
            return Integer.parseInt(request.getParameter(parameterName));
        } catch (Exception ex) {
            return null;
        }
    }
}

package pl.coderslab.controller.app.admin;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.db.dao.GroupDao;
import pl.coderslab.db.dao.UserDao;
import pl.coderslab.db.models.Group;
import pl.coderslab.db.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/admin/adminUserEdit")
public class AdminUserEdit extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = getIntParameter(request,"userId");
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Integer groupId = getIntParameter(request,"groupId");

        if (userId!=null && !email.equals("") && !password.equals("") && !userName.equals("")) {
            User user = new User(userName,email,password,groupId);
            user.setId(userId);
            UserDao userDao = new UserDao();
            try {
                userDao.update(user);
            }catch (Exception ex) {
                request.setAttribute("user",user);
                request.setAttribute("groupList", new GroupDao().findAll());
                request.setAttribute("msg",ex.getMessage());
                getServletContext().getRequestDispatcher("/adminUserEdit.jsp").forward(request,response);
            }

            response.sendRedirect(getServletContext().getContextPath()+"/app/admin/adminUsers");
        } else {
            User user = new User(userName,email,password,groupId);
            user.setId(userId);
            request.setAttribute("user",user);
            request.setAttribute("groupList", new GroupDao().findAll());
            request.setAttribute("msg","Niepoprawnie wypełniony formularz.");
            getServletContext().getRequestDispatcher("/adminUserEdit.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = getIntParameter(request,"userId");
        if (userId != null) {
            UserDao userDao = new UserDao();
            User user = userDao.read(userId);
            request.setAttribute("user", user);
            request.setAttribute("groupList", new GroupDao().findAll());
            getServletContext().getRequestDispatcher("/adminUserEdit.jsp").forward(request,response);
        } else {
            response.sendRedirect(getServletContext().getContextPath()+"/app/admin/adminUsers");
        }
    }


    public static Integer getIntParameter(HttpServletRequest request, String parameterName) {
        try {
            return Integer.parseInt(request.getParameter(parameterName));
        } catch (Exception ex) {
            return null;
        }
    }
}

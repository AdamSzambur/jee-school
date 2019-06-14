package pl.coderslab.controller.app.admin;

import pl.coderslab.db.dao.GroupDao;
import pl.coderslab.db.dao.GroupPrivilegesDao;
import pl.coderslab.db.models.Group;
import pl.coderslab.db.models.GroupPrivileges;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/app/admin/adminGroupAdd")
public class AdminGroupAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String groupName = request.getParameter("groupName");
        Integer learner = getIntParameter(request, "learner");
        Integer teacher = getIntParameter(request, "teacher");

        if (learner == null) learner = 0;
        if (teacher == null) teacher = 0;


        Group group = new Group(groupName);

        if (!groupName.equals("")) {
            try {
                GroupDao groupDao = new GroupDao();
                groupDao.create(group);
                GroupPrivileges groupPrivileges = new GroupPrivileges(group.getId(), learner, teacher);
                new GroupPrivilegesDao().create(groupPrivileges);
            } catch (RuntimeException ex) {
                request.setAttribute("group",group);
                request.setAttribute("groupPrivileges", new GroupPrivileges(0,learner,teacher));
                request.setAttribute("msg",ex.getMessage());
                getServletContext().getRequestDispatcher("/adminGroupAdd.jsp").forward(request, response);
            }
            response.sendRedirect(getServletContext().getContextPath() + "/app/admin/adminGroups");
        } else {
            request.setAttribute("group",group);
            request.setAttribute("groupPrivileges", new GroupPrivileges(0,learner,teacher));
            request.setAttribute("msg", "Niepoprawnie wypełniony formularz.");
            getServletContext().getRequestDispatcher("/adminGroupAdd.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/adminGroupAdd.jsp").forward(request, response);
    }

    public static Integer getIntParameter(HttpServletRequest request, String parameterName) {
        try {
            return Integer.parseInt(request.getParameter(parameterName));
        } catch (Exception ex) {
            return null;
        }
    }
}

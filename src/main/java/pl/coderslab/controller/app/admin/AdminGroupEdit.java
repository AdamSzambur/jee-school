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

@WebServlet("/app/admin/adminGroupEdit")
public class AdminGroupEdit extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer groupId = getIntParameter(request,"groupId");
        String groupName = request.getParameter("groupName");
        Integer learner = getIntParameter(request,"learner");
        Integer teacher = getIntParameter(request,"teacher");
        if (learner==null) learner=0;
        if (teacher==null) teacher=0;
        Group group = new Group(groupName);
        group.setId(groupId);
        GroupPrivileges groupPrivileges = new GroupPrivileges(groupId,learner,teacher);


        if (!group.getName().equals("")) {
            new GroupDao().update(group);
            new GroupPrivilegesDao().setUpdateForGroupidQuery(groupPrivileges);
            response.sendRedirect(getServletContext().getContextPath()+"/app/admin/adminGroups");
        } else {
            request.setAttribute("group",group);
            request.setAttribute("groupPrivileges", groupPrivileges);
            request.setAttribute("msg","Niepoprawnie wypełniony formularz.");
            getServletContext().getRequestDispatcher("/adminGroupEdit.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer groupId = getIntParameter(request,"groupId");
        if (groupId != null) {
            request.setAttribute("group", new GroupDao().read(groupId));
            request.setAttribute("groupPrivileges", new GroupPrivilegesDao().read(groupId));
            getServletContext().getRequestDispatcher("/adminGroupEdit.jsp").forward(request,response);
        } else {
            response.sendRedirect(getServletContext().getContextPath()+"/app/admin/adminGroups");
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

package pl.coderslab.controller.app.admin;

import pl.coderslab.db.dao.GroupDao;
import pl.coderslab.db.models.Group;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/admin/adminGroups")
public class AdminGroups extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer groupDeleteId = getIntParameter(request,"groupDeleteId");

        if (groupDeleteId!=null) {

            try {
                new GroupDao().delete(groupDeleteId);
            } catch (RuntimeException ex) {
                request.setAttribute("msg",ex.getMessage());
            }
            GroupDao groupDao = new GroupDao();
            List<Group> groupList = groupDao.findAll();
            request.setAttribute("groupList", groupList);
            getServletContext().getRequestDispatcher("/adminGroups.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer groupDeleteId = getIntParameter(request,"groupDeleteId");
        if (groupDeleteId!=null) {
            request.setAttribute("groupDelete", new GroupDao().read(groupDeleteId));
            request.setAttribute("groupDeleteId", groupDeleteId);
        } else {
            GroupDao groupDao = new GroupDao();
            List<Group> groupList = groupDao.findAll();
            request.setAttribute("groupList", groupList);
        }
        getServletContext().getRequestDispatcher("/adminGroups.jsp").forward(request,response);
    }

    public static Integer getIntParameter(HttpServletRequest request, String parameterName) {
        try {
            return Integer.parseInt(request.getParameter(parameterName));
        } catch (Exception ex) {
            return null;
        }
    }
}

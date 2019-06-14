package pl.coderslab.controller.app.admin;

import pl.coderslab.db.dao.GroupDao;
import pl.coderslab.db.dao.SolutionDao;
import pl.coderslab.db.models.Group;
import pl.coderslab.db.models.Solution;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/admin/adminSolutions")
public class AdminSolutions extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer solutionDeleteId = getIntParameter(request,"solutionDeleteId");

        if (solutionDeleteId!=null) {

            try {
                new SolutionDao().delete(solutionDeleteId);
            } catch (RuntimeException ex) {
                request.setAttribute("msg",ex.getMessage());
            }
            SolutionDao solutionDao = new SolutionDao();
            List<Solution> solutionList = solutionDao.findAll();
            request.setAttribute("solutionList", solutionList);
            getServletContext().getRequestDispatcher("/adminSolutions.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer solutionDeleteId = getIntParameter(request,"solutionDeleteId");
        if (solutionDeleteId!=null) {
            request.setAttribute("solutionDelete", new SolutionDao().read(solutionDeleteId));
            request.setAttribute("solutionDeleteId", solutionDeleteId);
        } else {
            SolutionDao solutionDao = new SolutionDao();
            List<Solution> solutionList = solutionDao.findAll();
            request.setAttribute("solutionList", solutionList);
        }
        getServletContext().getRequestDispatcher("/adminSolutions.jsp").forward(request,response);
    }

    public static Integer getIntParameter(HttpServletRequest request, String parameterName) {
        try {
            return Integer.parseInt(request.getParameter(parameterName));
        } catch (Exception ex) {
            return null;
        }
    }
}

package pl.coderslab.controller;

import pl.coderslab.db.dao.SolutionDao;
import pl.coderslab.db.models.Solution;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("")
public class MainPage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (getIntParameter(request,"logout")!=null) {
            HttpSession session = request.getSession();
            session.removeAttribute("logedUser");
        }

        SolutionDao solutionDao = new SolutionDao();
        List<Solution> solutions = solutionDao.findRecent(Integer.parseInt(getServletContext().getInitParameter("solutionsNum")));
        request.setAttribute("solutions", solutions);
        getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
    }

    public static Integer getIntParameter(HttpServletRequest request, String parameterName) {
        try {
            return Integer.parseInt(request.getParameter(parameterName));
        } catch (Exception ex) {
            return null;
        }
    }
}

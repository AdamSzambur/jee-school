package pl.coderslab.controller;

import pl.coderslab.db.dao.CommentDao;
import pl.coderslab.db.dao.ExerciseDao;
import pl.coderslab.db.dao.SolutionDao;
import pl.coderslab.db.dao.UserDao;
import pl.coderslab.db.models.Exercise;
import pl.coderslab.db.models.Solution;
import pl.coderslab.db.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/solution")
public class SolutionDetails extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer solutionId = getIntParameter(request,"solutionId");
        if (solutionId != null) {
            request.setAttribute("solution", new SolutionDao().read(solutionId));
            request.setAttribute("commentList", new CommentDao().findByIdSolution(solutionId));
            request.setAttribute("commentLastURI","/solution");
            getServletContext().getRequestDispatcher("/solution.jsp").forward(request,response);
        } else {
            response.sendRedirect(getServletContext().getContextPath()+"/");
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

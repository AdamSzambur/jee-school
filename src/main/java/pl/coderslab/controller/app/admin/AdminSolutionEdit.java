package pl.coderslab.controller.app.admin;

import pl.coderslab.db.dao.CommentDao;
import pl.coderslab.db.dao.ExerciseDao;
import pl.coderslab.db.dao.SolutionDao;
import pl.coderslab.db.models.Exercise;
import pl.coderslab.db.models.Solution;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/app/admin/adminSolutionEdit")
public class AdminSolutionEdit extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer solutionId = getIntParameter(request,"solutionId");
        Integer solutionRating = getIntParameter(request,"solutionRating");
        if (solutionRating == null)  solutionRating = 0;
        SolutionDao solutionDao = new SolutionDao();
        Solution solution = solutionDao.read(solutionId);
        solution.setRating(solutionRating);
        solutionDao.update(solution);
        response.sendRedirect(getServletContext().getContextPath()+"/app/admin/adminSolutions");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer solutionId = getIntParameter(request,"solutionId");
        if (solutionId != null) {
            request.setAttribute("solution", new SolutionDao().read(solutionId));
            request.setAttribute("commentList", new CommentDao().findByIdSolution(solutionId));
            request.setAttribute("commentLastURI","/app/admin/adminSolutionEdit");
            getServletContext().getRequestDispatcher("/adminSolutionEdit.jsp").forward(request,response);
        } else {
            response.sendRedirect(getServletContext().getContextPath()+"/app/admin/adminSolutions");
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

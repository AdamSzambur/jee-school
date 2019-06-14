package pl.coderslab.controller.app.user;

import pl.coderslab.db.dao.CommentDao;
import pl.coderslab.db.dao.SolutionDao;
import pl.coderslab.db.models.Solution;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@WebServlet("/app/user/userSolutionEdit")
public class UserSolutionEdit extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer solutionId = getIntParameter(request,"solutionId");
        String solutionDescription = request.getParameter("solutionDescription");
        SolutionDao solutionDao = new SolutionDao();
        Solution solution = solutionDao.read(solutionId);
        solution.setDescription(solutionDescription);
        solution.setUpdated(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Timestamp(System.currentTimeMillis())));
        solutionDao.update(solution);
        response.sendRedirect(getServletContext().getContextPath()+"/app/user/userSolutions");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer solutionId = getIntParameter(request,"solutionId");
        if (solutionId != null) {
            request.setAttribute("solution", new SolutionDao().read(solutionId));
            request.setAttribute("commentList", new CommentDao().findByIdSolution(solutionId));
            request.setAttribute("commentLastURI","/app/user/userSolutionEdit");
            getServletContext().getRequestDispatcher("/userSolutionEdit.jsp").forward(request,response);
        } else {
            response.sendRedirect(getServletContext().getContextPath()+"/app/user/userSolutions");
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

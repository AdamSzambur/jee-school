package pl.coderslab.controller.app.user;

import pl.coderslab.db.dao.SolutionDao;
import pl.coderslab.db.models.Solution;
import pl.coderslab.db.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/user/userSolutions")
public class UserSolutions extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        SolutionDao solutionDao = new SolutionDao();
        List<Solution> solutionList = solutionDao.findAllByUserId(((User)session.getAttribute("logedUser")).getId());
        request.setAttribute("solutionList", solutionList);

        getServletContext().getRequestDispatcher("/userSolutions.jsp").forward(request,response);
    }

    public static Integer getIntParameter(HttpServletRequest request, String parameterName) {
        try {
            return Integer.parseInt(request.getParameter(parameterName));
        } catch (Exception ex) {
            return null;
        }
    }
}

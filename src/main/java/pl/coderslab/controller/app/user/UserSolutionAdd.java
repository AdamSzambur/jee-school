package pl.coderslab.controller.app.user;

import pl.coderslab.db.dao.ExerciseDao;
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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@WebServlet("/app/user/userSolutionAdd")
public class UserSolutionAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("logedUser");
        Integer exerciseId = getIntParameter(request,"exerciseId");
        String solutionDescription = request.getParameter("solutionDescription");


        Solution solution = new Solution();
        solution.setUsers_id(user.getId());
        solution.setDescription(solutionDescription);
        solution.setExercise_id(exerciseId);
        solution.setCreated(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Timestamp(System.currentTimeMillis())));
        solution.setRating(0);

        try {
            new SolutionDao().create(solution);
            response.sendRedirect(getServletContext().getContextPath()+"/app/user/userExercises");
        } catch (RuntimeException ex) {
            request.setAttribute("msg", ex.getMessage());
            ExerciseDao exerciseDao = new ExerciseDao();
            request.setAttribute("exercise", exerciseDao.read(exerciseId));
            request.setAttribute("solutionDescription", solutionDescription);
            request.setAttribute("exerciseId", exerciseId);
            getServletContext().getRequestDispatcher("/userSolutionAdd.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer exerciseId = getIntParameter(request,"exerciseId");

        if (exerciseId!=null) {
            ExerciseDao exerciseDao = new ExerciseDao();
            request.setAttribute("exercise", exerciseDao.read(exerciseId));
            getServletContext().getRequestDispatcher("/userSolutionAdd.jsp").forward(request,response);
        } else {
            response.sendRedirect(getServletContext().getContextPath()+"/user/userExercises");

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

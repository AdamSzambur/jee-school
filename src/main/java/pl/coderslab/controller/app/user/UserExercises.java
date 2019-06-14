package pl.coderslab.controller.app.user;

import pl.coderslab.db.dao.ExerciseDao;
import pl.coderslab.db.models.Exercise;
import pl.coderslab.db.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/user/userExercises")
public class UserExercises extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ExerciseDao exerciseDao = new ExerciseDao();
        List<Exercise> exerciseList = exerciseDao.findAllNotSolvedByUserId(((User)session.getAttribute("logedUser")).getId());
        request.setAttribute("exerciseList", exerciseList);

        getServletContext().getRequestDispatcher("/userExercises.jsp").forward(request,response);
    }
}

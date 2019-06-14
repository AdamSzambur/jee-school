package pl.coderslab.controller.app.admin;

import pl.coderslab.db.dao.ExerciseDao;
import pl.coderslab.db.models.Exercise;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/app/admin/adminExerciseAdd")
public class AdminExerciseAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String exerciseTitle = request.getParameter("exerciseTitle");
        String exerciseDescription = request.getParameter("exerciseDescription");
        Exercise exercise = new Exercise(exerciseTitle,exerciseDescription);

        if (!exerciseTitle.equals("")) {
            ExerciseDao exerciseDao = new ExerciseDao();
            try {
                exerciseDao.create(exercise);
            }catch (RuntimeException ex) {
                request.setAttribute("exercise",exercise);
                request.setAttribute("msg",ex.getMessage());
                getServletContext().getRequestDispatcher("/adminExerciseAdd.jsp").forward(request,response);
            }
            response.sendRedirect(getServletContext().getContextPath()+"/app/admin/adminExercises");
        } else {
            request.setAttribute("exercise",exercise);
            request.setAttribute("msg","Niepoprawnie wypełniony formularz.");
            getServletContext().getRequestDispatcher("/adminExerciseAdd.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/adminExerciseAdd.jsp").forward(request,response);
    }

    public static Integer getIntParameter(HttpServletRequest request, String parameterName) {
        try {
            return Integer.parseInt(request.getParameter(parameterName));
        } catch (Exception ex) {
            return null;
        }
    }
}

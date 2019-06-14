package pl.coderslab.controller.app.admin;

import pl.coderslab.db.dao.ExerciseDao;
import pl.coderslab.db.models.Exercise;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/app/admin/adminExerciseEdit")
public class AdminExerciseEdit extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer exerciseId = getIntParameter(request,"exerciseId");
        String exerciseTitle = request.getParameter("exerciseTitle");
        String exerciseDescription = request.getParameter("exerciseDescription");

        Exercise exercise = new Exercise(exerciseTitle,exerciseDescription);
        exercise.setId(exerciseId);

        if (!exercise.getTitle().equals("")) {
            new ExerciseDao().update(exercise);
            response.sendRedirect(getServletContext().getContextPath()+"/app/admin/adminExercises");
        } else {
            request.setAttribute("exercise",exercise);
            request.setAttribute("msg","Niepoprawnie wypełniony formularz.");
            getServletContext().getRequestDispatcher("/adminExerciseEdit.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer exerciseId = getIntParameter(request,"exerciseId");
        if (exerciseId != null) {
            request.setAttribute("exercise", new ExerciseDao().read(exerciseId));
            getServletContext().getRequestDispatcher("/adminExerciseEdit.jsp").forward(request,response);
        } else {
            response.sendRedirect(getServletContext().getContextPath()+"/app/admin/adminExercises");
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

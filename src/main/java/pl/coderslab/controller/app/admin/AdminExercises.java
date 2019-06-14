package pl.coderslab.controller.app.admin;

import pl.coderslab.db.dao.ExerciseDao;
import pl.coderslab.db.models.Exercise;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/admin/adminExercises")
public class AdminExercises extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer exerciseDeleteId = getIntParameter(request,"exerciseDeleteId");

        if (exerciseDeleteId!=null) {

            try {
                new ExerciseDao().delete(exerciseDeleteId);
            } catch (RuntimeException ex) {
                request.setAttribute("msg",ex.getMessage());
            }
            ExerciseDao exerciseDao = new ExerciseDao();
            List<Exercise> exerciseList = exerciseDao.findAll();
            request.setAttribute("exerciseList", exerciseList);
            getServletContext().getRequestDispatcher("/adminExercises.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer exerciseDeleteId = getIntParameter(request,"exerciseDeleteId");
        if (exerciseDeleteId!=null) {
            request.setAttribute("exerciseDelete", new ExerciseDao().read(exerciseDeleteId));
            request.setAttribute("exerciseDeleteId", exerciseDeleteId);
        } else {
            ExerciseDao exerciseDao = new ExerciseDao();
            List<Exercise> exerciseList = exerciseDao.findAll();
            request.setAttribute("exerciseList", exerciseList);
        }
        getServletContext().getRequestDispatcher("/adminExercises.jsp").forward(request,response);
    }

    public static Integer getIntParameter(HttpServletRequest request, String parameterName) {
        try {
            return Integer.parseInt(request.getParameter(parameterName));
        } catch (Exception ex) {
            return null;
        }
    }
}

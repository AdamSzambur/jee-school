package pl.coderslab.controller;

import pl.coderslab.db.dao.CommentDao;
import pl.coderslab.db.models.Comment;
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

@WebServlet("/commentDel")
public class CommentDel extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer solutionId = getIntParameter(request,"commentSolutionId");
        Integer commentId = getIntParameter(request, "commentId");
        String lastURI = request.getParameter("commentLastURI");
        new CommentDao().delete(commentId);
        response.sendRedirect(getServletContext().getContextPath()+lastURI+"?solutionId="+solutionId);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public static Integer getIntParameter(HttpServletRequest request, String parameterName) {
        try {
            return Integer.parseInt(request.getParameter(parameterName));
        } catch (Exception ex) {
            return null;
        }
    }
}

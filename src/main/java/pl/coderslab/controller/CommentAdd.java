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

@WebServlet("/commentAdd")
public class CommentAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("logedUser");
        Integer solutionId = getIntParameter(request,"commentSolutionId");
        String lastURI = request.getParameter("commentLastURI");
        String description = request.getParameter("commentDescription");
        Comment comment = new Comment();
        comment.setDescription(description);
        comment.setSolutionId(solutionId);
        comment.setCreated(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Timestamp(System.currentTimeMillis())));
        if (user!=null) {
            comment.setUserName(user.getUserName());
        } else {
            comment.setUserName("unknown");
        }
        new CommentDao().create(comment);
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

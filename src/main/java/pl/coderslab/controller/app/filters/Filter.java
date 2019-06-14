package pl.coderslab.controller.app.filters;

import pl.coderslab.db.dao.UserDao;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class Filter implements javax.servlet.Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest)req;

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        chain.doFilter(req, resp);
//        // tymczasowo logujemy Adama
//        HttpSession session = request.getSession();
//        session.setAttribute("logedUser",new UserDao().read(2));
    }

    public void init(FilterConfig config) throws ServletException {

    }

}

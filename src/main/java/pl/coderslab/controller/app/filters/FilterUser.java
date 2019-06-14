package pl.coderslab.controller.app.filters;

import pl.coderslab.db.dao.GroupDao;
import pl.coderslab.db.dao.GroupPrivilegesDao;
import pl.coderslab.db.models.Group;
import pl.coderslab.db.models.GroupPrivileges;
import pl.coderslab.db.models.User;

import javax.servlet.*;
import javax.servlet.Filter;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/app/user/*")
public class FilterUser implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("logedUser");
        if (user!=null) {
            Group group = new GroupDao().read(user.getGroupId());
            GroupPrivileges groupPrivileges = new GroupPrivilegesDao().read(group.getId());
            if (groupPrivileges.getLearner() == 1) {
                chain.doFilter(request, response);
            } else {
                response.sendRedirect("../../?msg=Brak%20dostepu");
            }
        } else {
            response.sendRedirect("../../?msg=Zaloguj%20sie");
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}

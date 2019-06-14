<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: szambur
  Date: 25.05.19
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<div class="pure-g">
    <div class="pure-u-1 pure-u-md-1-2 pure-u-lg-1-3"><p></p></div>
    <div class="pure-u-1 pure-u-md-1-2 pure-u-lg-1-3"><p>
        <p style="color :red;text-align: center">${param.msg}<br><br></p>
        <h2>Lista ostatnich rozwiązań</h2>
        <table class="pure-table pure-table-striped">
            <thead>
            <tr>
                <th> #</th>
                <th>Zadanie</th>
                <th>Uzytkownik</th>
                <th>Data dodania</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="solution" items="${solutions}" varStatus="i">
            <tr>
                <td><a href="${pageContext.request.contextPath}/solution?solutionId=${solution.id}">${i.index+1}</a></td>
                <td><a href="${pageContext.request.contextPath}/solution?solutionId=${solution.id}">${solution.exerciseTitle}</a></td>
                <td><a href="${pageContext.request.contextPath}/solution?solutionId=${solution.id}">${solution.userName}</a></td>
                <td><a href="${pageContext.request.contextPath}/solution?solutionId=${solution.id}">${solution.created}</a></td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="pure-u-1 pure-u-md-1-2 pure-u-lg-1-3"><p></p></div>
</div>
<jsp:include page="footer.jsp"/>

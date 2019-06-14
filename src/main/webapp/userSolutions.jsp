<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: szambur
  Date: 12.06.19
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<div class="pure-g">
    <div class="pure-u-1 pure-u-md-1-2 pure-u-lg-1-6"><p></p></div>
    <div class="pure-u-1 pure-u-md-1-2 pure-u-lg-5-6"><p>
        <c:if test="${msg!=null}"><span style="color: red">${msg}</span></c:if>
        <h2>Lista wszystkich rozwiązań uzytkownika ${logedUser.userName}</h2>
        <table class="pure-table pure-table-striped">
            <thead>
            <tr>
                <th> #</th>
                <th>id</th>
                <th>Utworzono</th>
                <th>Zaktualizowano</th>
                <th>Zadanie</th>
                <th>Użytkownik</th>
                <th>Ocena</th>
                <th>Akcje</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="solution" items="${solutionList}" varStatus="i">
                <tr>
                    <td>${i.index+1}</td>
                    <td>${solution.id}</td>
                    <td>${solution.created}</td>
                    <td>${solution.updated}</td>
                    <td>${solution.exerciseTitle}</td>
                    <td>${solution.userName}</td>
                    <td>${solution.rating}</td>
                    <td style="white-space: nowrap;">
                        <a class="button-success pure-button" href="${pageContext.request.contextPath}/app/user/userSolutionEdit?solutionId=${solution.id}">Zmodyfikuj lub dodaj komentarz</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="pure-u-1 pure-u-md-1-2 pure-u-lg-1-6"><p></p></div>
</div>
<jsp:include page="footer.jsp"/>

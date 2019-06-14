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
    <div class="pure-u-1 pure-u-md-1-2 pure-u-lg-1-3"><p></p></div>
    <div class="pure-u-1 pure-u-md-1-2 pure-u-lg-1-3"><p>
        <c:if test="${msg!=null}"><span style="color: red">${msg}</span></c:if>
        <c:if test="${groupDeleteId!=null}">
        <form method="post" action="${pageContext.request.contextPath}/app/admin/adminGroups">
            <input type="hidden" value="${groupDeleteId}" name="groupDeleteId">
            <br><br><br>
            <h3 style="text-align: center;">Czy napewno chcesz usunąć grupę : ${groupDelete.name}</h3>
            <br>
            <p style="white-space: nowrap; text-align: center;">
                <button type="submit" class="button-error pure-button">Usuń</button>
                &nbsp
                <a class="button-success pure-button" href="${pageContext.request.contextPath}/app/admin/adminGroups">Anuluj</a>
            </p>
        </form>
        </c:if>
        <c:if test="${groupDeleteId==null}">
        <h2>Lista wszystkich grup</h2>
        <table class="pure-table pure-table-striped">
            <thead>
            <tr>
                <th> #</th>
                <th>id</th>
                <th>Nazwa</th>
                <th>Akcja</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="group" items="${groupList}" varStatus="i">
                <tr>
                    <td>${i.index+1}</td>
                    <td>${group.id}</td>
                    <td>${group.name}</td>
                    <td style="white-space: nowrap;">
                        <a class="button-success pure-button" href="${pageContext.request.contextPath}/app/admin/adminGroupEdit?groupId=${group.id}">Edytuj</a>
                        &nbsp;
                        <a class="button-error pure-button" href="${pageContext.request.contextPath}/app/admin/adminGroups?groupDeleteId=${group.id}">Usuń</a>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="3" style="text-align: right">Dodaj nową grupę</td>
                <td><a class="button-secondary pure-button" href="${pageContext.request.contextPath}/app/admin/adminGroupAdd">Dodaj</a></td>
            </tr>
            </tbody>
        </table>
        </c:if>
    </div>
    <div class="pure-u-1 pure-u-md-1-2 pure-u-lg-1-3"><p></p></div>
</div>
<jsp:include page="footer.jsp"/>

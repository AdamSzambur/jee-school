<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: szambur
  Date: 12.06.19
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<div class="pure-g">
    <div class="pure-u-1 pure-u-md-1-2 pure-u-lg-1-3"><p></p></div>
    <div class="pure-u-1 pure-u-md-1-2 pure-u-lg-1-3"><p>
        <h2>Dodawanie nowej grupy</h2>
        <c:if test="${msg!=null}"><p style="color :red;text-align: center">${msg}</p></c:if>
        <form class="pure-form pure-form-aligned" action="${pageContext.request.contextPath}/app/admin/adminGroupAdd" method="post">
            <fieldset>
                <div class="pure-control-group">
                    <label for="name">Nazwa grupy</label>
                    <input id="name" type="text" placeholder="Group name" name="groupName" value="${group.name}">
                </div>
                <div class="pure-control-group">
                    <label for="learner">Dostęp jako : </label>
                    <input id="learner" type="checkbox" name="learner" value="1" <c:if test="${groupPrivileges.learner ==1}">checked</c:if>>&nbsp;&nbsp;Uczeń<br>
                </div>
                <div class="pure-control-group">
                    <label for="teacher">&nbsp;</label>
                    <input id="teacher" type="checkbox" name="teacher" value="1" <c:if test="${groupPrivileges.teacher ==1}">checked</c:if>>&nbsp;&nbsp;Nauczyciel
                </div>
                <div class="pure-controls">
                    <button type="submit" class="pure-button pure-button-primary">Dodaj grupę</button>
                </div>
            </fieldset>
        </form>
    </div>
    <div class="pure-u-1 pure-u-md-1-2 pure-u-lg-1-3"><p></p></div>
</div>
<jsp:include page="footer.jsp"/>

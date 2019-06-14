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
    <div class="pure-u-1 pure-u-md-1-2 pure-u-lg-1-6"><p></p></div>
    <div class="pure-u-1 pure-u-md-1-2 pure-u-lg-4-6"><p>
        <h2>Edycja zadania</h2>
        <c:if test="${msg!=null}"><p style="color :red;text-align: center">${msg}</p></c:if>
        <form class="pure-form pure-form-aligned" action="${pageContext.request.contextPath}/app/admin/adminExerciseEdit" method="post">
            <input type="hidden" value="${exercise.id}" name="exerciseId">
            <fieldset class="pure-group">
                <input type="text" class="pure-input-1" placeholder="Tytuł zadania" name="exerciseTitle" value="${exercise.title}">
                <textarea style="height: 200px" class="pure-input-1" placeholder="Tresc zadania" name="exerciseDescription">${exercise.description}</textarea>
            </fieldset>
            <button type="submit" class="pure-button pure-input-1 pure-button-primary">Zapisz zmiany</button>
        </form>
    </div>
    <div class="pure-u-1 pure-u-md-1-2 pure-u-lg-1-6"><p></p></div>
</div>
<jsp:include page="footer.jsp"/>
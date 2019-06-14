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
        <h2>Dodaj rozwiązanie do zadania</h2>
        <c:if test="${msg!=null}"><p style="color :red;text-align: center">${msg}</p></c:if>
        <h4>Szczegóły zadania</h4>
        <form class="pure-form pure-form-aligned">
            <fieldset class="pure-group">
                <input readonly type="text" class="pure-input-1" placeholder="Tytuł zadania" name="exerciseTitle" value="${exercise.title}">
                <textarea readonly style="height: 150px" class="pure-input-1" placeholder="Tresc zadania" name="exerciseDescription">${exercise.description}</textarea>
            </fieldset>
        </form>

        <h4>Twoje rozwiązanie</h4>
        <form class="pure-form pure-form-aligned" action="${pageContext.request.contextPath}/app/user/userSolutionAdd" method="post">
            <input type="hidden" value="${exercise.id}" name="exerciseId">
            <fieldset class="pure-group">
                <textarea style="height: 200px" class="pure-input-1" placeholder="Tresc rozwiązania" name="solutionDescription"></textarea>
            </fieldset>
            <button type="submit" class="pure-button pure-input-1 pure-button-primary">Dodaj rozwiązanie</button>
        </form>
    </div>
    <div class="pure-u-1 pure-u-md-1-2 pure-u-lg-1-6"><p></p></div>
</div>
<jsp:include page="footer.jsp"/>
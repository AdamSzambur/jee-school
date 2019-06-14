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
    <div class="pure-u-1 pure-u-md-1-4 pure-u-lg-1-6"><p></p></div>
    <div class="pure-u-1 pure-u-md-1-2 pure-u-lg-4-6"><p>
        <h2>Zmodyfikuj lub dodaj komentarz do rozwiązania</h2>
        <c:if test="${msg!=null}"><p style="color :red;text-align: center">${msg}</p></c:if>
        <form class="pure-form pure-form-aligned" action="${pageContext.request.contextPath}/app/user/userSolutionEdit" method="post">
            <input type="hidden" value="${solution.id}" name="solutionId">
            <fieldset>
                <div class="pure-control-group">
                    <label for="exerciseTitle">Zadanie</label>
                    <input id="exerciseTitle" type="text" placeholder="Tytuł zadania" name="solutionExerciseTitle" readonly value="${solution.exerciseTitle}">
                </div>

                <div class="pure-control-group">
                    <label for="userName">Uzytkownik</label>
                    <input id="userName" type="text" placeholder="userName" name="solutionUserName" value="${solution.userName}" readonly>
                </div>
                <div class="pure-control-group">
                    <label for="created">Utworzono</label>
                    <input id="created" type="text" name="solutionCreated" readonly value="${solution.created}">
                </div>
                <div class="pure-control-group">
                    <label for="updated">Zaktualizowano</label>
                    <input id="updated" type="text" name="solutionUpdated" readonly value="${solution.updated}">
                </div>
                <div class="pure-control-group">
                    <label for="rating">Ocena</label>
                    <input readonly id="rating" type="text" placeholder="rating" name="solutionRating" value="${solution.rating}">

                </div>
                <div class="pure-control-group">
                    <label for="description">Rozwiązanie</label>
                    <textarea id="description" style="height: 200px" class="pure-input-3-4" placeholder="User solution" name="solutionDescription">${solution.description}</textarea>
                </div>
                <div class="pure-control-group">
                    <label for="button"></label>
                    <button id="button" type="submit" class="pure-button pure-input-3-4 pure-button-primary">Zapisz zmiany</button>
                </div>
            </fieldset>
        </form>

        <form class="pure-form" method="post" action="${pageContext.request.contextPath}/commentAdd">
            <input hidden name="commentSolutionId" value="${solution.id}">
            <input hidden name="commentLastURI" value="${commentLastURI}">

            <input type="text" class="pure-input-rounded" style="width: 80%" placeholder="Dodaj komentarz" name="commentDescription">
            <button type="submit" class="button-secondary pure-button">Dodaj komentarz</button>
        </form>
        <table class="pure-table pure-table-striped" style="width: 100%">
            <thead>
            <tr>
                <th> #</th>
                <th>Utworzono</th>
                <th>Użytkownik</th>
                <th>Komentarz</th>
                <th>Akcja</th>

            </tr>
            </thead>
            <tbody>
            <c:forEach var="comment" items="${commentList}" varStatus="i">
                <tr>
                    <td>${i.index+1}</td>
                    <td>${comment.created}</td>
                    <td>${comment.userName}</td>
                    <td>${comment.description}</td>
                    <form method="post" action="${pageContext.request.contextPath}/commentDel">
                        <input hidden name="commentSolutionId" value="${solution.id}">
                        <input hidden name="commentLastURI" value="${commentLastURI}">
                        <input hidden name="commentId" value="${comment.id}">
                        <td><button type="submit" class="button-error pure-button">Usuń</button></td>
                    </form>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="pure-u-1 pure-u-md-1-4 pure-u-lg-1-6"><p></p></div>
</div>
<jsp:include page="footer.jsp"/>
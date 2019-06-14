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
        <h2>Edycja danych użytkownika</h2>
        <c:if test="${msg!=null}"><p style="color :red;text-align: center">${msg}</p></c:if>
        <form class="pure-form pure-form-aligned" action="${pageContext.request.contextPath}/app/userParam" method="post">
            <fieldset>
                <div class="pure-control-group">
                    <label for="name">Nazwa użytkownika</label>
                    <input id="name" type="text" placeholder="Username" name="userName" value="${logedUser.userName}">
                </div>

                <div class="pure-control-group">
                    <label for="password">Hasło</label>
                    <input id="password" type="password" placeholder="Password" name="password">
                </div>
                <div class="pure-control-group">
                    <label for="password">Powtórz hasło</label>
                    <input id="rePassword" type="password" placeholder="Repassword" name="rePassword">
                </div>
                <div class="pure-control-group">
                    <label for="email">Adres e-mail</label>
                    <input id="email" type="email" placeholder="Email Address" name="email" value="${logedUser.email}">
                    <span class="pure-form-message-inline">Pole wymagane.</span>
                </div>
                <div class="pure-control-group">
                    <label for="groupName">Nazwa grupy</label>
                    <input readonly id="groupName" type="text" placeholder="Group name" name="groupName" value="${logedUser.groupName}">
                </div>
                <div class="pure-controls">
                    <button type="submit" class="pure-button pure-button-primary">Zatwierdź zmiany</button>
                </div>
            </fieldset>
        </form>
    </div>
    <div class="pure-u-1 pure-u-md-1-2 pure-u-lg-1-3"><p></p></div>
</div>
<jsp:include page="footer.jsp"/>
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
        <h2>Dodawanie nowego użytkownika</h2>
        <c:if test="${msg!=null}"><p style="color :red;text-align: center">${msg}</p></c:if>
        <form class="pure-form pure-form-aligned" action="${pageContext.request.contextPath}/app/admin/adminUserAdd" method="post">
            <fieldset>
                <div class="pure-control-group">
                    <label for="name">Username</label>
                    <input id="name" type="text" placeholder="Username" name="userName" value="${user.userName}">
                </div>

                <div class="pure-control-group">
                    <label for="password">Password</label>
                    <input id="password" type="password" placeholder="Password" name="password">
                </div>

                <div class="pure-control-group">
                    <label for="email">Email Address</label>
                    <input id="email" type="email" placeholder="Email Address" name="email" value="${user.email}">
                    <span class="pure-form-message-inline">This is a required field.</span>
                </div>

                <div class="pure-control-group">
                    <label for="group">Grupa</label>
                    <select id="group" name="groupId">
                        <c:forEach var="group" items="${groupList}">
                            <option value="${group.id}" <c:if test="${group.id == user.groupId}">selected</c:if>>${group.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="pure-controls">
                    <button type="submit" class="pure-button pure-button-primary">Dodaj użytkownika</button>
                </div>
            </fieldset>
        </form>
    </div>
    <div class="pure-u-1 pure-u-md-1-2 pure-u-lg-1-3"><p></p></div>
</div>
<jsp:include page="footer.jsp"/>

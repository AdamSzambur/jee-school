<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: szambur
  Date: 12.06.19
  Time: 14:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<div class="pure-g">
    <div class="pure-u-1 pure-u-md-1-2 pure-u-lg-1-3"><p></p></div>
    <div class="pure-u-1 pure-u-md-1-2 pure-u-lg-1-3"><p>
        <h2>Logowanie</h2>
        <form class="pure-form pure-form-stacked" method="post" action="login">
            <fieldset>
                <legend>
                    <c:if test="${msg!=null}">
                        <span style="color: red">${msg}</span>
                    </c:if>
                    <c:if test="${msg==null}">
                        Podaj adres e-mail i hasło
                    </c:if>
                </legend>

                <label for="email" >Email</label>
                <input name="email" id="email" type="email" placeholder="Email">
                <span class="pure-form-message">This is a required field.</span>

                <label for="password">Password</label>
                <input name="password" id="password" type="password" placeholder="Password">
                <p>
                    <button type="submit" class="pure-button pure-button-primary">Sign in</button>
                </p>
            </fieldset>
        </form>
    </div>
    <div class="pure-u-1 pure-u-md-1-2 pure-u-lg-1-3"><p></p></div>
</div>
<jsp:include page="footer.jsp"/>

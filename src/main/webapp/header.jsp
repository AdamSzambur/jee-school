<%--
  Created by IntelliJ IDEA.
  User: szambur
  Date: 25.05.19
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>JEE School</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css" integrity="sha384-nn4HPE8lTHyVtfCBi5yW9d20FjT8BJwUXyWZT9InLYax14RDjBj46LmSztkmNP9w" crossorigin="anonymous">
    <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/grids-responsive-min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

    <script src="https://kit.fontawesome.com/da6d9e6874.js"></script>
</head>
<body>
<div class="pure-g">
    <div class="pure-u-1 pure-u-md-1-3 pure-u-lg-1-4"></div>
    <div class="pure-u-1 pure-u-md-1-3 pure-u-lg-2-4">
        <p>
        <div class="pure-menu pure-menu-horizontal">
            <ul class="pure-menu-list">
                <li class="pure-menu-item pure-menu-selected"><a href="${pageContext.request.contextPath}/" class="pure-menu-link">Strona główna</a></li>
                <li class="pure-menu-item"><a href="${pageContext.request.contextPath}/app/user/userExercises" class="pure-menu-link">Zadania</a></li>
                <li class="pure-menu-item"><a href="${pageContext.request.contextPath}/app/user/userSolutions" class="pure-menu-link">Rozwiązania</a></li>
                <li class="pure-menu-item"><a href="${pageContext.request.contextPath}/app/user/userParam" class="pure-menu-link">Ustawienia konta</a></li>
                <li class="pure-menu-item pure-menu-has-children pure-menu-allow-hover">
                    <a href="#" id="menuLink1" class="pure-menu-link">Panel Administratora</a>
                    <ul class="pure-menu-children">
                        <li class="pure-menu-item"><a href="${pageContext.request.contextPath}/app/admin/adminExercises" class="pure-menu-link">Zadania</a></li>
                        <li class="pure-menu-item"><a href="${pageContext.request.contextPath}/app/admin/adminSolutions" class="pure-menu-link">Rozwiązania</a></li>
                        <li class="pure-menu-item"><a href="${pageContext.request.contextPath}/app/admin/adminUsers" class="pure-menu-link">Uzytkownicy</a></li>
                        <li class="pure-menu-item"><a href="${pageContext.request.contextPath}/app/admin/adminGroups" class="pure-menu-link">Grupy</a></li>
                    </ul>
                </li>
            </ul>
        </div>
        </p>
    </div>
    <div class="pure-u-1 pure-u-md-1-3 pure-u-lg-1-4">
        <p style="text-align: right;">
            <c:if test="${logedUser==null}">
            <a class="pure-button" href="${pageContext.request.contextPath}/login">
                <i class="fas fa-sign-in-alt"></i>
                Zaloguj się
            </a>
            </c:if>
            <c:if test="${logedUser!=null}">
                Uzytkownik : ${logedUser.userName}&nbsp;&nbsp;
                <a class="pure-button" href="${pageContext.request.contextPath}/?logout=1">
                    <i class="fas fa-sign-out-alt"></i>
                    Wyloguj się
                </a>
            </c:if>
        </p>
    </div>
</div>
<hr>

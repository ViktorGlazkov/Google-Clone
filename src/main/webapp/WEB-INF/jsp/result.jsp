<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="core.model.Message" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/resources/index.html" %>
</head>
<body>

<div class="header1">
    <div class="header2">
        <div class="logo-wrapper">
            <a href="/">
                <img class="logo"
                     src="https://www.gstatic.com/images/branding/googlelogo/2x/googlelogo_color_284x96dp.png"/>
            </a>
        </div>

        <form action="/search" method="GET">
            <div class="header-input">
                <input class="search-input" type="text" name="q" value="${query}"/>
                <input type="hidden" name="p" value="1"/>
                <img class="microphone" src="../../resources/input/microphone.png"/>
                <div class="search-img">
                    <input class="s-i" type="submit" value="">
                </div>
                </input>
            </div>
        </form>
    </div>

    <div class="header" style="margin-top: -42px;">
        <a class="icon-link" style="margin-left: 100px;" href="/">
            <img class="icon" src="../../resources/header/apps.png"/>
        </a>
        <button class="sign-in-button">Sign in</button>
    </div>
</div>

<div class="results-wrapper">
    <div class="results">
        <c:if test="${results != null}">
            <br>
            <div class="count">About ${count} results</div>
            <% for (Message message : (List<Message>) request
                    .getAttribute("results")) { %>
            <br>
            <div class="title">
                <a href=" <%= message.getUrl() %>"><%= message.getTitle() %>
                </a>
            </div>
            <div class="url">
                <a href=" <%= message.getUrl() %>"><%= message.getUrl() %>
                </a>
            </div>
            <% } %>
            <br>
            <form action="/search" method="GET">
                <input type="hidden" name="q" value="${query}">

                <input type="submit" name="p" value="${page-1}" <c:if test="${page-1 <= 0}"> disabled </c:if>>

                <span class="count"> ${page} </span>

                <input type="submit" name="p" value="${page+1}" <c:if test="${page+1 >= (count/10)+1}">
                       disabled </c:if>>
            </form>
        </c:if>
    </div>
</div>
</body>
</html>

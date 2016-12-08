<%@ page import="core.model.Message" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="/resources/html/head.html" %>
<%@include file="/resources/html/header.html" %>
<%@include file="/resources/html/logo.html" %>
<%@include file="/resources/html/input.html" %>
<%--<%@include file="/resources/html/index.html" %>--%>
<%--<body>--%>
<%--<%@include file="/resources/html/search_form.html" %>--%>

<%--<div class="results-wrapper">--%>
    <%--<div class="results">--%>
        <%--<c:if test="${results != null}">--%>
            <%--<% for (Message message : (List<Message>) request--%>
                    <%--.getAttribute("results")) { %>--%>
            <%--<br>--%>
            <%--<div class="title">--%>
                <%--<a href=" <%= message.getUrl() %>"><%= message.getTitle() %>--%>
                <%--</a>--%>
            <%--</div>--%>
            <%--<div class="url">--%>
                <%--<a href=" <%= message.getUrl() %>"> <%= message.getUrl() %> </a>--%>
            <%--</div>--%>
            <%--<% } %>--%>
        <%--</c:if>--%>
    <%--</div>--%>
<%--</div>--%>

</body>
</html>

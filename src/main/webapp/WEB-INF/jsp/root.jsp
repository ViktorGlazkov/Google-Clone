<%@ page import="core.model.Message" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="/resources/html/head.html" %>
<body>
<%@include file="/resources/html/search_form.html" %>
<br>
<c:if test="${results != null}">
    <% for (Message message : (List<Message>) request
            .getAttribute("results")) { %>
    <div class="title">
        <a href=" <%= message.getUrl() %>"><%= message.getTitle() %>
        </a>
    </div>
    <div>
        <a href=" <%= message.getUrl() %>"><%= message.getUrl() %>
        </a>
    </div>
    <br>
    <br>
    <% } %>
</c:if>

</body>
</html>

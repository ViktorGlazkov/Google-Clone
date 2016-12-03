<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="shortcut icon" href="../../resources/google.ico">
    <title>iGoogle</title>
</head>
<body>
<div>
    <form action="/index" method="POST">
        <input type="url" name="q" placeholder="url" required>
        <input type="submit">
    </form>
</div>
<div>
    <c:if test="${q != null}">
        ${q}
    </c:if>
</div>
</body>
</html>
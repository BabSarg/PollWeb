<%@ page import="com.epam.mentoring.poll.model.Result" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello World</title>
</head>
<body>

<%
    Result result = (Result) request.getAttribute("result");
%>
<h1><%=result.getExplanation()%></h1>
<br>
<a href="/poll">к списку  ответам</a>
</body>
</html>

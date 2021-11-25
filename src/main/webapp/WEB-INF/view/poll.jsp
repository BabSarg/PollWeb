<%@ page import="java.util.List" %>
<%@ page import="com.epam.mentoring.poll.model.Poll" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello World</title>
</head>
<body>

<%
    List<Poll> pollList = (List<Poll>) request.getAttribute("pollList");
    for (Poll poll : pollList) {
        %>
<a href="/poll/beginpoll?pollId=<%=poll.getId()%>"><%=poll.getName()%></a>
<br/>
<%
    }
%>

</body>
</html>

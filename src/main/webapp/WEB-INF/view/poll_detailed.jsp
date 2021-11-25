<%@ page import="java.util.List" %>
<%@ page import="com.epam.mentoring.poll.model.Question" %>
<%@ page import="com.epam.mentoring.poll.model.Answer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello World</title>
</head>
<body>

<form action="/poll/result" method="post">
<%
    List<Question> questionList = (List<Question>) request.getAttribute("questionList");
    for (Question question : questionList) {
        %>
<h1><%=question.getText()%></h1>
<br/>
<%
    for (Answer answer : question.getAnswers()) {
        %>
 <div style="display: flex">
     <input type="radio" name="<%=question.getId()%>_question" value="<%=answer.getId()%>"/>
     <p><%=answer.getText()%></p>
 </div>
<%
        }
    }
%>
    <br/>
    <input type="submit" value="Save"/>
</form>
</body>
</html>

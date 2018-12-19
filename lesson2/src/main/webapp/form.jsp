<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Form</title>
</head>
<body>
    <h1>Hello from FormServlet</h1>
    <%
        out.println("Your IP address is " + request.getRemoteAddr());
    %>
</body>
</html>

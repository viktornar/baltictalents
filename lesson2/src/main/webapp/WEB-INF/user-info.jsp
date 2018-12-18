<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="lt.baltictalents.lesson2.app.UserInfoBean" %>
<html>
<head>
    <title>User input</title>
</head>
<body>
<%
    if (request.getAttribute("userInfoBean") != null) {
        UserInfoBean userInfoBean = (UserInfoBean) request.getAttribute("userInfoBean");
%>
    <ul>
        <li>Username: <c:out value="${userInfoBean.name}"/></li>
        <li>Password: <c:out value="${userInfoBean.password}"/></li>
        <li>Age: <c:out value="${userInfoBean.age}"/></li>
        <li>Gender: <c:out value="${userInfoBean.gender}"/></li>
        <li>Languages: <c:out value="${userInfoBean.languages}"/></li>
    </ul>

<%
    } else {
        response.sendRedirect("user-input");
    }
%>
</body>
</html>

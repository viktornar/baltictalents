<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <title>Examples</title>
</head>
<body>

<h3>Welcome, Enter The Employee Details</h3>
<table>
    <tr>
        <td>Name :</td>
        <td><c:out value="${name}"/></td>
    </tr>
    <tr>
        <td>ID :</td>
        <td>${id}</td>
    </tr>
    <tr>
        <td>Contact Number :</td>
        <td>${contactNumber}</td>
    </tr>
</table>

</body>
</html>

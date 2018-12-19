<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
    <title>Examples</title>
</head>
<body>

If you see something below, then you have passed request params
<c:if test="${name1 != null}">Value name1 is: <c:out value="${name1}" /></c:if>
<c:if test="${name2 != null}">Value name2 is: <c:out value="${name2}" /></c:if>

Path variable: <c:out  value="${id}"/>

</body>
</html>

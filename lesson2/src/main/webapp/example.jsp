<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.GregorianCalendar" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>My age</title>
</head>
<body>
<%!
    private int getMyAge() {
        Calendar myBirthday = new GregorianCalendar(1986, Calendar.MARCH, 5);
        Calendar now = GregorianCalendar.getInstance();

        int myAge = now.get(Calendar.YEAR) - myBirthday.get(Calendar.YEAR);
        myBirthday.add(Calendar.YEAR, myAge);

        // If today's date is before March 5, subtract a year from my age
        if (now.before(myBirthday)) {
            myAge--;
        }

        return myAge;
    }

%>

<h1>Hi all :)</h1>
I have a <%=getMyAge()%> years :) <br />

Count my years with loop: <br />

<c:forEach var="age" begin="1" end="<%=getMyAge()%>" step="1">
    <c:if test="${age == 22}">
        <% out.println("I had already 22 years :("); %><br />
    </c:if>

    <c:if test="${age != 22}">
        <c:out value="${age}"/><br />
    </c:if>
</c:forEach>

</body>
</html>
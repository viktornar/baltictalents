<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.GregorianCalendar" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Dukes age</title>
</head>
<body>
<%!
    private int getDukeAge() {
        // Create a new Calendar for Duke's birthday
        Calendar dukesBirthday = new GregorianCalendar(1995, Calendar.MAY, 23);
        // Create a new Calendar for today
        Calendar now = GregorianCalendar.getInstance();

        // Subtract today's year from Duke's birth year, 1995
        int dukesAge = now.get(Calendar.YEAR) - dukesBirthday.get(Calendar.YEAR);
        dukesBirthday.add(Calendar.YEAR, dukesAge);

        // If today's date is before May 23, subtract a year from Duke's age
        if (now.before(dukesBirthday)) {
            dukesAge--;
        }

        return dukesAge;
    }

%>

<h1>Hello duke !!!</h1>
Duke have a <%=getDukeAge()%> years :) <br />

Count years with loop: <br />

<c:forEach var="age" begin="1" end="<%=getDukeAge()%>" step="1">
    <c:if test="${age == 22}">
        <% out.println("I have already 22 years"); %><br />
    </c:if>
    <c:if test="${age != 22}">
        <c:out value="${age}"/><br />
    </c:if>


</c:forEach>

</body>
</html>

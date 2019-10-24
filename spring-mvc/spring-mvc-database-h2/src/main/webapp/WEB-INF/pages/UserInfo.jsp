<%@page import="com.springmvc.model.User"%>
<%@ page import="java.util.List"%>
<html>
<body>
<% List<User> users = (List<User>)request.getAttribute("users");
    out.println(users);
%>
<c:forEach items="${users}" var="user">
    <tr>
        <td>User name: <c:out value="${user.name}"/></td>
        <td>User age: <c:out value="${user.age}"/></td>
    </tr>
</c:forEach>
</body>
</html>
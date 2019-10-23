<%@page import="com.springmvc.validation.model.User"%>
<html>
<body>
<% User user = (User)request.getAttribute("user");%>
<p> name : <%=user.getName()%></p>
<p> age : <%=user.getAge()%></p>
</body>
</html>
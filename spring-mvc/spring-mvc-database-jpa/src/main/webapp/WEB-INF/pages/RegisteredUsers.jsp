<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Start Page</title>
        <meta charset="UTF-8">
    </head>
    <body>
        <table>
            <tr>
                <th>First Name</th>
                <th>Age</th>
            </tr>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>"${user.getName()}"</td>
                    <td>"${user.getName()}"</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
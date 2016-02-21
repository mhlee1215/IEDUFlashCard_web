<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<div id="wrapper">
	<h1>Hello World!</h1>
	<br>
	${myMessage}<br>

	<ul>
	 <c:forEach items="${userList}" var="user" varStatus="list_status">
     	<li>${user.name}, ${user.email}</li>
     </c:forEach>
    </ul>
	
</div>
</body>
</html>


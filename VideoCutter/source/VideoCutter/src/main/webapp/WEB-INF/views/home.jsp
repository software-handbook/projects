<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
	<body>
		<form action = "null" method = "POST">
			<h1>Video Player</h1>
			<table>
			 <c:forEach begin="1" end="5">
			  <tr>
			  <c:forEach begin="1" end="3">  
			    <td>
			      <img alt="No picture" src="resources/images/limon1.jpg">
			      <input type="checkbox"/>
			    </td>
			  </c:forEach>
			  </tr>
			</c:forEach>
			</table>
		</form>
	</body>
</html>
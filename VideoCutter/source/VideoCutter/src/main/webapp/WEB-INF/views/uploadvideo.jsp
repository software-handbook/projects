<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action = "upload" method = "POST" enctype = "multipart/form-data" modelAttribute ="model">
<table>
	<tr>
<!-- 		<td>Your Video:<form:input path ="videoTitle"/></td> -->
		<td>YourVideo</td>
		<td><input type = "text"  name = "videoTitle"  value = ""/></td>
		<td><input type = "file" name = "file" value = "Browse..."/></td>
	</tr>
	<tr>
		<td><input type = "submit" name = "upload" value = "Upload"/> </td>
	</tr>
	<tr></tr>
	<tr>	
		<td>Uploaded video:</td>
	</tr>
	<tr>
		<td>Name:</td>
		<td>${fileName} </td>
	</tr>
	<tr>
		<td>Size:</td>
		<td>${fileSize}</td>
	</tr>
 </table>
</form>
</body>
</html>
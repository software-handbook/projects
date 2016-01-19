<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<form method = "POST" action = "viewframes" enctype = "multipart/form-data">
Upload file <input type = "file" name = "file">
Name: <input type = "text" name="name">
    <input type="submit" value="Upload">

</form>
<body>
<table>
	<tr>
		<td>${message}</td>
	</tr>
</table>

</body>
</html>
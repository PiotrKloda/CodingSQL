<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h3>To login into admin, please enter password: </h3>
<form action="http://localhost:8080/codingSQL/admin_login" method="post">
	<label>Enter password: <input type="password" name="password" />   </label>
	<input type="submit" value="Submit password">
</form>

<p><a href="http://localhost:8080/codingSQL/hp">Go back</a>


</body>
</html>
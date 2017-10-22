<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="../WEB-INF/header.jsp" %>
	<a href="http://localhost:8080/codingSQL/hp">Go back </a> <br>
	
	<h1>Solution details</h1>
	<p>Exercise: ${exercise.title}</p>
	<p>Description: ${exercise.description}</p>
	<p> User: ${user.username} </p>
	
	<p>Created: ${solution.created}</p>
	<p>Updated: ${solution.updated}</p>
	<h4>SOLUTION: ${solution.description}</h4>
	

</body>
<%@ include file="../WEB-INF/footer.jsp" %>
</html>
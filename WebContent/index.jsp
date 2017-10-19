<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<% response.sendRedirect("http://localhost:8080/codingSQL/hp"); %>
	
	<%@ include file="WEB-INF/header.jsp" %>
	<p>INDEX</p>
	
	<p></p>
	<a href="http://localhost:8080/codingSQL/hp"> go to HomePage </a>
	

</body>
	<%@ include file="WEB-INF/footer.jsp" %>
</html>
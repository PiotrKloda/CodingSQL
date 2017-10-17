s<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="WEB-INF/header.jsp" %>
	This is nice body
	<a href="http://localhost:8080/codingSQL/"> go to HomePage </a>
	
	<ul>
	<c:forEach var="sol" items="${solList}">
		<li> ${sol} +1 </li>
	</c:forEach>
	</ul>

</body>
	<%@ include file="WEB-INF/footer.jsp" %>
</html>
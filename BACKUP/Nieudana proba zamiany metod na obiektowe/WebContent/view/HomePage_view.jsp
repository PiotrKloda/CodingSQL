<%@page import="com.codingSQL.model.Exercise"%>
<%@page import="com.codingSQL.model.User"%>
<%@page import="com.codingSQL.model.Solution"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="view/css/style.css">
</head>
<body>

	<%@ include file="../WEB-INF/header.jsp" %>
	HOMEPAGE VIEW
	
	<h3>Recent solutions</h3>
	<table>
		<thead>
			<tr>
				<th>Exercise title</th>
				<th>Author</th>
				<th>Adding date</th>
				<th>Solution details</th>
			</tr>
		</thead>
		<tbody>
		<% 
			Solution[] sList = (Solution[]) request.getAttribute("sList");
			for (int i=0; i< sList.length ; i++) {
			%><tr>
				<td><%= sList[i].getExercise_id_obj().getTitle() %></td>
				<td><%= sList[i].getUsers_id_obj().getUsername()%></td>
				<td><%= sList[i].getCreated() %></td>
				<td> <a href="http://localhost:8080/codingSQL/solutionDetails?sol_id=<%=sList[i].getId() %>">Solution detail</a> </td>
			</tr>
			<%  } %>
		</tbody>
	</table>

</body>
	<%@ include file="../WEB-INF/footer.jsp" %>
</html>





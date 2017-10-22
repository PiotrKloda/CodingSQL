<%@page import="com.codingSQL.model.Exercise"%>
<%@page import="com.codingSQL.model.Solution"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<style>
			thead {
			  background-color: blue;
			  color:white;
			  font-weight: bolder;
			  border-spacing: 10px;
			}
			td , th {
			  border: 1px solid black;
			  padding: 5px;
			}
			table {
			  border: 1px solid black;
			  border-collapse: separate;
			}
	</style>
</head>
<body>
	<%@ include file="../WEB-INF/header.jsp" %>
	<a href="http://localhost:8080/codingSQL/solutionDetails">Go back </a> <br>
	
	<h3>Details of User: ${u.username}</h3>
	<h5>Name: ${u.username}</h5>
	<h5>Email: ${u.email}</h5>
	<br>
	
	<h4>Added exercises solutions: </h4>
	<table>
		<thead>
			<tr>
				<th>Exercise title</th>
				<th>Adding date</th>
				<th>Solution details</th>
			</tr>
		</thead>
		<tbody>
		
		<% 
		Solution[] sList = (Solution[]) request.getAttribute("sList");
		Exercise[] eeList = (Exercise[]) request.getAttribute("eList");
		int length = sList.length;
		for (int i=0; i<length ;i++) { %>
		
			<tr>
				<td><%= eeList[i].getTitle()%></td>
				<td><%= sList[i].getCreated()%></td>
				<td> <a href="http://localhost:8080/codingSQL/solutionDetails?sol_id=<%=sList[i].getId()%>">Solution detail</a> </td>
			</tr>
			<% } %>
		</tbody>
	</table>
	
	


</body>
	<%@ include file="../WEB-INF/footer.jsp" %>
</html>
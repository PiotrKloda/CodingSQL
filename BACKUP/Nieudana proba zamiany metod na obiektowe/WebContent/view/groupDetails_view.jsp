<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
	<a href="http://localhost:8080/codingSQL/groupsList">Go back </a> <br>
	
	<h1>List of members of group: ${ug.name}</h1>
	<table>
		<thead>
			<tr>
				<th>User name</th>
				<th>User details</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="user" items="${u}">
				<tr>
					<td> ${user.username} </td>
					<td> <a href="http://localhost:8080/codingSQL/userDetails?u_id=${user.id}">User details</a> </td>
				</tr>
			</c:forEach>
		</tbody>
		
	
	</table>
	
	
	
</body>
	<%@ include file="../WEB-INF/footer.jsp" %>
</html>
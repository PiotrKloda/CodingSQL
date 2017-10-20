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
	
	<h3>Manage User Groups</h3>
	
	<h4><a href="http://localhost:8080/codingSQL/view/form_ug_add.jsp">Add new Group</a></h4>
	<table>
		<thead>
			<tr>
				<th>Group name</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="ug" items="${ugList}">
				<tr>
					<td>${ug.name}</td>
					<td><a href="http://localhost:8080/codingSQL/view/form_ug_edit.jsp?ug_name=${ug.name}&ugId=${ug.id}">edit</a></td>
					<td><a href="http://localhost:8080/codingSQL/view/form_ug_delete.jsp?ug_name=${ug.name}&ugId=${ug.id}">delete</a></td>
					
					
				</tr>
			</c:forEach> 
		</tbody>
	</table>


</body>
	<%@ include file="../WEB-INF/footer.jsp" %>
</html>
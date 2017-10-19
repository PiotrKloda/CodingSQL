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
	
	<form action="http://localhost:8080/codingSQL/admin_ug" method="post">
		<label>Are you sure you want to delete: ${param.ug_name} ?  </label> <br>
		<label> If so, please retype number: ${param.ugId} <input type="text" name="num" id="num" />  </label>
		
		<!--  
		<% //String id = request.getParameter("ugId"); %>
		 <select name="choice">
	    	<option value=id>Yes</option>
	    	<option value="-50">No</option>
	    </select>
	    -->
	    
		<input type="submit" value="Submit" />
	</form>
	
	<p><a href="http://localhost:8080/codingSQL/admin_ug">Go back</a>
	
</body>
</html>
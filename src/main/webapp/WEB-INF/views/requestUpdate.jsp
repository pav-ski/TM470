<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Request an update</title>
</head>
<body>

	<div align="center">
	The id is ${job.jobId}
	<form:form method="POST" action="postRequestUpdate?id${job.jobId}" modelAttribute="updateForm">
	<div><form:textarea path="message"/></div>
	<form:hidden path="updateAbout" value="${job.jobId}" name="id"/>
	<input type="submit">
	</form:form>
</div>
        
        
        


</body>
</html>
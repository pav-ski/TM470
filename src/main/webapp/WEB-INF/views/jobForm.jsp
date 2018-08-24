<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration</title>
</head>

Id is ${id}
area is ${area.id}
<body>

	<div align="center">
    <form:form method="POST" action="postJob" modelAttribute="jobModel">
    
    <div>Description of issue: <form:input path="description"/></div>
    <div><form:hidden path="isFor" value="${area.id}" name="areaId"/></div>
	
	

	
	<div>
	Please select the item from the list:
    <form:select path="isFaulty">
    <c:forEach items="${elements}" var="element" varStatus="status">
    <form:option value="${element.id}" label="${element.description}"></form:option>
    </c:forEach>
    </form:select>
	</div>





	<div>Please choose option which best describes the issue:
    <form:select path="severity">
    <form:option value="1">Broken</form:option>
    <form:option value="2">Faulty</form:option>
    <form:option value="3">Unsure</form:option>
    <form:option value="4">Not working correctly</form:option>
    <form:option value="5">Working fine</form:option>
    </form:select>
    </div>
    
    <input type="submit"/>
	</form:form>

	</div>
	
	
	</body>
</html>


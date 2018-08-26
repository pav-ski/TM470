<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<div align="center">
            <h1>Job List</h1>
            <table border="1">
                <th>Description</th>
                <th>Date Posted</th>
                <th>Posted By</th>
                <th>Name</th>
                 
                <c:forEach var="eachJob" items="${jobs}" varStatus="status">
                <tr>
					<td>${eachJob.description}</td>
					<td>${eachJob.datePosted}</td>
					<td>${eachJob.postedBy.name}</td>
					<td><a href="location?id=${eachlocation.locationId}">View Location</a></td>

                             
                </tr>
                </c:forEach>             
            </table>
        </div>
    
        
        
        


</body>
</html>
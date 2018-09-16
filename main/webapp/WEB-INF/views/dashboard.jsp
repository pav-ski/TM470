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
            <h1>Location List</h1>
            <table border="1">
                <th>Name</th>
                 
                <c:forEach var="eachlocation" items="${locations}" varStatus="status">
                <tr>
					<td>${eachlocation.name}</td>
					<td><a href="location?id=${eachlocation.locationId}&name=${eachlocation.name}">View Location</a></td>

                             
                </tr>
                </c:forEach>             
            </table>
        </div>
        
        
        <div align="center">
            <h1>Active Job List</h1>
            <table border="1">
                <th>Job</th>
                <th>Date Posted</th>
                <th>Location</th>
                <th>Area</th>
                <th>Posted by</th>
                <th>Faulty Element</th>
                 
                <c:forEach var="eachJob" items="${jobs}" varStatus="status">
                <tr>
					<td>${eachJob.description}</td>
					<td>${eachJob.datePosted}</td>
					<td>${eachJob.isFor.isInLocation.name}</td>
					<td>${eachJob.isFor.areaID}</td>
					<td>${eachJob.postedBy.name}</td>
					<td>${eachJob.isFaulty.description}</td>
					<td><a href="viewJob?id=${eachJob.jobId}">View Job</a></td>

                             
                </tr>
                </c:forEach>             
            </table>
        </div>
        
        <div align="center">
            <h1>Past Job List</h1>
            <table border="1">
                <th>Completed Job</th>
                <th>Date Posted</th>
                <th>Location</th>
                <th>Area</th>
                <th>Posted by</th>
                <th>Faulty Element</th>
                 
                <c:forEach var="pastJob" items="${pastJobs}" varStatus="status">
                <tr>
					<td>${pastJob.description}</td>
					<td>${pastJob.datePosted}</td>
					<td>${pastJob.wasFor.isInLocation.name}</td>
					<td>${pastJob.wasFor.areaID}</td>
					<td>${pastJob.postedBy.name}</td>
					<td>${pastJob.isFaulty.description}</td>
					<td><a href="viewJob?id=${pastJob.jobId}">View Job</a></td>

                             
                </tr>
                </c:forEach>             
            </table>
        </div>
        
        <div align="center">
            <h1>Notifications</h1>
            <table border="1">
                <th>Notification</th>
                 
                <c:forEach var="eachNotification" items="${notifications}" varStatus="status">
                <tr>
					<td>${eachNotification.message}</td>

                             
                </tr>
                </c:forEach>             
            </table>
        </div>
        
        
        


</body>
</html>
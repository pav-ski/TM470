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
View Detailed job
<div align="center">
            <h1>Job ${job.jobId}</h1>
            <table border="1">
                <th>Description</th>
                <th>Date Posted</th>
                <th>Posted By</th>
                <th>Name</th>
                <tr>
					<td>${job.description}</td>
					<td>${job.datePosted}</td>
					<td>${job.postedBy.name}</td>
					<td><a href="">View Location</a></td>

                             
                </tr>            
            </table>
        </div>
        
        <div align="center">
            <h1>Job List</h1>
            <table border="1">
                <th>Job</th>
                 
                <c:forEach var="eachUpdate" items="${job.hasUpdate}" varStatus="status">
                <tr>
					<td>${eachUpdate}</td>
					<td>${eachUpdate}</td>
					<td>${eachUpdate}</td>
					<td>${eachUpdate}</td>
					<td>${eachUpdate}</td>
					<td>${eachUpdate}</td>

                             
                </tr>
                </c:forEach>             
            </table>
        </div>
        
        
        <div align="center">
        <a href="requestUpdate?id=${job.jobId}"><input type="submit" value="Request update" name="button" />
   		 </div>
        
        
        


</body>
</html>
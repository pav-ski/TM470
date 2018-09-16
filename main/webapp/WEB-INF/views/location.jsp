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
            <h1>You are in location:${name}</h1>
            <table border="1">
            	<th>Room description</th>
                <th>Room number</th>
                <th>Room score</th>
                <th>Edit</th>
                <th>Remove</th> 
                <th>Check Jobs</th>
                <th>See Inventory</th>
                <th>Add Jobs</th>
                <c:forEach var="area" items="${areas}">
                <tr>

                    <td>${area.descriptionOfArea}</td>
                    <td>${area.areaID}</td>
                    <td>${area.roomScore}</td>
                    <td><a href="edit?id=${area.id}"><input type="submit" value="Edit" name="button" /></a></td>
                    <td><a href="remove?id=${area.id}"><input type="submit" value="Remove" name="button" /></a></td>
                    <td><a href="viewJobs?id=${area.id}"><input type="submit" value="Check Jobs" name="button" /></a></td>
                    <td><a href="inventory?id=${area.id}"><input type="submit" value="Inventory" name="button" /></a></td> 
                    <td><a href="jobForm?id=${area.id}"><input type="submit" value="Post Job" name="button" /></a></td>      
                </tr>
                </c:forEach>             
            </table>
            
        </div>


</body>
</html>
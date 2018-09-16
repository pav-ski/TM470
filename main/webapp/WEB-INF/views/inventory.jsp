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
Welcome to inventory page<br>

<a href="addElement?${area.id}"><input type="submit" value="Add Element" name="button" /></a> 



<div align="center">
            <h1>Element List</h1>
            <table border="1">
                <th>Element Id</th>
                 <th>Element Description</th>
                 <th>Element Score</th>
                 <th>Remove Element</th>
                 <th>Change Element</th>
                <c:forEach var="element" items="${elements}">
                <tr>

                    <td>${element.id}</td>
                    <td>${element.description}</td> 
                    <td>${element.score}</td>
                    <td><a href=""><input type="submit" value="Remove - stud" name="button" /></a></td> 
                    <td><a href=""><input type="submit" value="Change - stud" name="button" /></a></td>      
                </tr>
                </c:forEach>             
            </table>
        </div>

</body>
</html>
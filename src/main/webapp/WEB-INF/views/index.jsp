<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

Welcome to dashboard

<div align="center">
            <h1>Contact List</h1>
            <table border="1">
                <th>No</th>
                <th>Username</th>
                <th>Email</th>
                 
                <c:forEach var="company" items="${companyList}" varStatus="status">
                <tr>
                    <td>${status.index + 1}</td>
                    <td>${company.name}</td>
                    <td>${company.id}</td>
                             
                </tr>
                </c:forEach>             
            </table>
        </div>


</body>
</html>
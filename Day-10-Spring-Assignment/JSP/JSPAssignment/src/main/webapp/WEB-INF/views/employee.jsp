<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EMPLOYEE</title>
</head>
<body>
<c:if test="${not empty employee}">
	<h1>Employee Details </h1>
	<table border="1px solid black" cellpadding="10px">
		<tr>
			<th>Employee Id</th>
			<td>${employee.getEmployeeId()}</td>
		</tr>
	
		<tr>
			<th>Name</th>
			<td>${employee.getEmployeeName()}</td>
		</tr>
	
		<tr>
			<th>Salary</th>
			<td>${employee.getSalary()}</td>
		</tr>

	</table>
</c:if>
<c:if test="${not empty employees}">
	<h1>Employees </h1>
	<table border="1px solid black" cellpadding="10px">
		<tr>
			<th>Employee Id </th>
			<th>Name</th>
			<th>Salary</th>
			
		</tr>
		<c:forEach items="${employees}" var="employee">
			<tr>
	            <td> ${employee.getEmployeeId()} </td>
	            <td> ${employee.getEmployeeName()} </td>
	            <td> ${employee.getSalary()} </td>
	        </tr>
	    </c:forEach>
    </table>
</c:if>

</body>
</html>
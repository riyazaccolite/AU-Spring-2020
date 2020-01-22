<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:th="https://www.thymeleaf.org">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Employee</title>
</head>
<body>
<h1>Add Employee</h1>
<form method = "POST" action="#" th:action="@{/add}">
	<input type="number" placeholder="Enter Employee Id" name="employeeId" /> <br/>
	<input type="text" placeholder="Enter Employee Name" name="employeeName"/> <br/>
	<input type="number" placeholder="Enter Employee Salary" name="salary"/> <br/>
	<input type="submit" />
</form>
</body>
</html>
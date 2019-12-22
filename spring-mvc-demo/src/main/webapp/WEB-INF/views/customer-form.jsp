<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Form</title>

<!-- Reference Bootstrap files -->
	<link rel="stylesheet"
		 href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
	<h3><em>Fill out the form.Asterisk (*) mean required</em></h3>
	<form:form action="processForm" modelAttribute="customer" method="POST">
	
		First name: 
		<form:input path="firstName" type="text" />
		<form:errors path="firstName" cssClass="text-danger" /><br><br>
		
		Last name (*): 
		<form:input path="lastName" type="text" />
		<form:errors path="lastName" cssClass="text-danger" /><br><br>
		
		Free passes: 
		<form:input path="freePasses" type="text" />
		<form:errors path="freePasses" cssClass="text-danger" /><br><br>
		
		Postal code: 
		<form:input path="postalCode" type="text" />
		<form:errors path="postalCode" cssClass="text-danger" /><br><br>
		
		Course code: 
		<form:input path="courseCode" type="text" />
		<form:errors path="courseCode" cssClass="text-danger" /><br><br>
		
		<input type="submit" value="Submit" />
	
	</form:form>
</body>
</html>
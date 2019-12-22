<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>

<!-- Reference Bootstrap files -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h2>luv2code Company Home Page</h2>
		<hr/>
		<p>Welcome to the luv2code Company Home Page!</p>
		<hr/>
		
		<p>
			User: <security:authentication property="principal.username" />
			<br><br>
			Role(s): <security:authentication property="principal.authorities" />
		</p>
		
		<hr>
		
		<security:authorize access="hasRole('MANAGER')">
			<!-- Add a link to point to /leaders ... this is for the managers -->
		
			<p>
				<a href="${pageContext.request.contextPath}/leaders">Leadership Meeting</a>
				(Only for Manager peeps)
			</p>
		</security:authorize>
		
		<security:authorize access="hasRole('ADMIN')">
			<!-- Add a link to point to /systems ... this is for the admins -->
		
			<p>
				<a href="${pageContext.request.contextPath}/systems">IT Systems Meeting</a>
				(Only for Admin peeps)
			</p>
		</security:authorize>
		<hr>
		
		<form:form action="${pageContext.request.contextPath}/logout" method="POST">
			<button type="submit" class="btn btn-info">Logout</button>
		</form:form>
	</div>
	
</body>
</html>
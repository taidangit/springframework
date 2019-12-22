<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register New User Form</title>

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
		<div class=" col-md-4 col-md-offset-4" style="margin-top: 50px;">
			<div class="panel panel-primary">

				<div class="panel-heading">
					<h3 class="panel-title">Register New User</h3>
				</div>

				<div class="panel-body">

					<!-- Login Form -->
					<form:form action="${pageContext.request.contextPath}/register/processRegistrationForm" 
						  	   modelAttribute="crmUser"
						  	   class="form-horizontal">

						<!-- Check for registration error -->
						<c:if test="${registrationError != null}">
					
							<div class="alert alert-danger">
								${registrationError}
							</div>

						</c:if>

					
						<!-- User name -->
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> 
							<form:input path="userName" placeholder="username (*)" class="form-control" />
							<form:errors path="userName" cssClass="text-danger" />
						</div>

						<!-- Password -->
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span> 
							<form:password path="password" placeholder="password (*)" class="form-control" />
							<form:errors path="password" cssClass="text-danger" />
						</div>
						
						<!-- Confirm Password -->
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span> 
							<form:password path="matchingPassword" placeholder="confirm password (*)" class="form-control" />
							<form:errors path="matchingPassword" cssClass="text-danger" />
						</div>
					
						
						<!-- First name -->
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> 
							<form:input path="firstName" placeholder="first name (*)" class="form-control" />
							<form:errors path="firstName" cssClass="text-danger" />
						</div>
						
						<!-- Last name -->
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> 
							<form:input path="lastName" placeholder="last name (*)" class="form-control" />
							<form:errors path="lastName"  cssClass="text-danger" />
						</div>
						
						<!-- Email -->
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> 
							<form:input path="email" placeholder="email (*)" class="form-control" />
							<form:errors path="email" cssClass="text-danger" />
						</div>
						

						<!-- Register Button -->
						<button type="submit" class="btn btn-primary btn-block">Register</button>
						
					</form:form>

				</div>

			</div>
			
		</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>

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
					<h3 class="panel-title">Sign In</h3>
				</div>

				<div class="panel-body">

					<!-- Login Form -->
					<form
						action="${pageContext.request.contextPath}/authenticateTheUser"
						method="POST" class="form-horizontal">

						<!-- Place for messages: error, alert etc ... -->
						<c:if test="${param.error != null}">

							<div class="alert alert-danger" role="alert">Invalid username and
								password.</div>

						</c:if>

						<c:if test="${param.logout != null}">

							<div class="alert alert-success" role="alert">You have been logged out.
							</div>

						</c:if>

						<!-- User name -->
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span> <input type="text"
								name="username" placeholder="username" class="form-control"
								required="required">
						</div>

						<!-- Password -->
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-lock"></i></span> <input type="password"
								name="password" placeholder="password" class="form-control"
								required="required">
						</div>

						<!-- Login/Submit Button -->
						<button type="submit" class="btn btn-success btn-block">Sign in</button>
						
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />

					</form>

				</div>

			</div>
			<a href="${pageContext.request.contextPath}/register/showRegistrationForm" 
				class="btn btn-primary" role="button" aria-pressed="true">Register New User</a>
		</div>
		
	</div>
</body>
</html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
	 <jsp:include page="header-login.jsp"/>
</head>
<body>
	
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<div class="login100-form-title" style="background-image: url(${pageContext.request.contextPath}/resources/images/bg-01.jpg);">
					<span class="login100-form-title-1">
						Sign Up
					</span>
					
				</div>

				
				<form:form class="login100-form validate-form" 
					action="${pageContext.request.contextPath}/register/processRegistrationForm"
					modelAttribute="user"
					method="POST">
				
					<c:if test="${msgsuccess != null}">

						<div class="alert alert-success">
							${msgsuccess}
						</div>
					
					</c:if>
																	
					<c:if test="${msgerror != null}">

						<div class="alert alert-danger">
							${msgerror}
						</div>
					
					</c:if>		
					
					<div class="wrap-input100 m-b-26">
						<span class="label-input100">Full Name</span>
						<form:input class="input100" type="text" path="fullname" placeholder="Enter full name" />
						<span class="focus-input100"></span>
						<form:errors path="fullname" cssClass="text-danger" />
					</div>
					
					<div class="wrap-input100  m-b-26">
						<span class="label-input100">Username</span>
						<form:input class="input100" type="text" path="username" placeholder="Enter username" />
						<span class="focus-input100"></span>
						<form:errors path="username" cssClass="text-danger" />
					</div>
					
					<div class="wrap-input100  m-b-26">
						<span class="label-input100">Phone</span>
						<form:input class="input100" type="text" path="phone" placeholder="Enter phone number" />
						<span class="focus-input100"></span>
						<form:errors path="phone" cssClass="text-danger" />
					</div>
					
					<div class="wrap-input100  m-b-26">
						<span class="label-input100">Email</span>
						<form:input class="input100" type="text" path="email" placeholder="Enter email" />
						<span class="focus-input100"></span>
						<form:errors path="email" cssClass="text-danger" />
					</div>

					<div class="wrap-input100  m-b-18">
						<span class="label-input100">Password</span>
						<form:input class="input100" type="password" path="password" placeholder="Enter password" />
						<span class="focus-input100"></span>
						<form:errors path="password" cssClass="text-danger" />
					</div>

					<div class="wrap-input100  m-b-18">
						<span class="label-input100">Repeat Password</span>
						<input class="input100" type="password" name="repeat-pass" placeholder="Enter repeat password" >
						<span class="focus-input100"></span>
					</div>

					<div class="container-login100-form-btn">
						<button class="login100-form-btn" type="submit">
							Register
						</button>
					</div>
					
					<a href="${pageContext.request.contextPath}/login">
					<i class="fa fa-long-arrow-left m-r-5"></i>Sign In</a>
				</form:form>
				
				
				
				
			</div>
		</div>
	</div>
	
	<jsp:include page="script-login.jsp"/>
	
</body>
</html>
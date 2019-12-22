<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="header-login.jsp" />
</head>
<body>

	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<div class="login100-form-title"
					style="background-image: url(${pageContext.request.contextPath}/resources/images/bg-01.jpg);">
					<span class="login100-form-title-1"> Sign In </span>

				</div>


				<div class="login100-form validate-form">

					<span id="result-login"></span>

					<div class="wrap-input100 validate-input m-b-26"
						data-validate="Username is required">
						<span class="label-input100">Username</span> <input
							class="input100" type="text" name="username" id="username"
							placeholder="Enter username"> <span
							class="focus-input100"></span> 
					</div>

					<div class="wrap-input100 validate-input m-b-18"
						data-validate="Password is required">
						<span class="label-input100">Password</span> <input
							class="input100" type="password" name="pass" id="pass"
							placeholder="Enter password"> <span
							class="focus-input100"></span>
					</div>


					<div class="container-login100-form-btn">
						<button id="btnLogin" class="login100-form-btn">Login</button>
					</div>
					
					<a
						href="${pageContext.request.contextPath}/register/showRegistrationForm">Sign
						Up<i class="fa fa-long-arrow-right m-l-5"></i>
					</a>
				</div>

				
			</div>
		</div>
	</div>

	<jsp:include page="script-login.jsp" />

</body>
</html>
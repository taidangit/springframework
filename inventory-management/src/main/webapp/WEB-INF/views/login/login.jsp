<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Inventory Management |</title>

<!-- Bootstrap -->
<link
	href="${pageContext.request.contextPath}/resources/vendors/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Font Awesome -->
<link
	href="${pageContext.request.contextPath}/resources/vendors/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<!-- NProgress -->
<link
	href="${pageContext.request.contextPath}/resources/vendors/nprogress/nprogress.css"
	rel="stylesheet">
<!-- jQuery custom content scroller -->
<link
	href="${pageContext.request.contextPath}/resources/vendors/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.min.css"
	rel="stylesheet" />

<!-- Custom Theme Style -->
<link
	href="${pageContext.request.contextPath}/resources/build/css/custom.min.css"
	rel="stylesheet">
</head>

<body class="login">
	<div>



		<div class="login_wrapper">
			<div class="animate form login_form">
				<section class="login_content">
					<form:form 
						action="${pageContext.request.contextPath}/processLogin"
						modelAttribute="user"
						method="POST">
						<h1>Login Form</h1>

						<c:if test="${msgerrorusername != null}">

							<div class="alert alert-danger">${msgerrorusername}</div>

						</c:if>

						<c:if test="${msgerrorpass != null}">

							<div class="alert alert-danger">${msgerrorpass}</div>

						</c:if>

						<div>
							<form:input type="text" path="username" cssClass="form-control" required="required"
								placeholder="Enter username" />
							
						</div>
						<div>
							<form:input type="password" path="password" required="required"
								cssClass="form-control" placeholder="Enter password" />
							
						</div>
						<div>
							<button class="btn btn-default submit" type="submit">Login</button>
						</div>

						<div class="clearfix"></div>

						<div class="separator">

							<div class="clearfix"></div>
							<br />

							<div>
								<h1>
									<i class="fa fa-paw"></i> Inventory Management System!
								</h1>
								<p>©2019 Inventory Management System. Privacy and Terms</p>
							</div>
						</div>
					</form:form>
				</section>
			</div>


		</div>
	</div>
</body>
</html>

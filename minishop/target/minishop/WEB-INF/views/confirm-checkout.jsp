
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="head.jsp" />
</head>
<body class="goto-here">
	<jsp:include page="header.jsp" />

	<jsp:include page="navbar.jsp" />

	<div class="hero-wrap hero-bread"
		style="background-image: url(${pageContext.request.contextPath}/resources/frontend//images/bg_6.jpg);">
		<div class="container">
			<div
				class="row no-gutters slider-text align-items-center justify-content-center">
				<div class="col-md-9 ftco-animate text-center">
					<p class="breadcrumbs">
						<span class="mr-2"><a href="index.html">Home</a></span> <span>Checkout</span>
					</p>
					<h1 class="mb-0 bread">Checkout</h1>
				</div>
			</div>
		</div>
	</div>

	<c:if test="${msgordersuccess != null}">

		<div class="alert alert-success">
			${msgordersuccess}
		</div>
	
	</c:if>
	

	<jsp:include page="footer.jsp" />

	<jsp:include page="script.jsp" />


	
</body>
</html>
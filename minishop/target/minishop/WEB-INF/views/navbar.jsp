<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav
	class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light"
	id="ftco-navbar">
	<div class="container">
		<a class="navbar-brand" href="${pageContext.request.contextPath}/home">Minishop</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#ftco-nav" aria-controls="ftco-nav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="oi oi-menu"></span> Menu
		</button>

		<div class="collapse navbar-collapse" id="ftco-nav">
			<ul class="navbar-nav ml-auto">
				
				<li class="nav-item active"><a href="${pageContext.request.contextPath}/home"
					class="nav-link">Home</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="dropdown04"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Catalog</a>
					<div class="dropdown-menu" aria-labelledby="dropdown04">
						<a class="dropdown-item" href="${pageContext.request.contextPath}/shop">Shop</a>
						<a class="dropdown-item" href="${pageContext.request.contextPath}/cart">Cart</a> <a
							class="dropdown-item" href="${pageContext.request.contextPath}/checkout">Checkout</a>
					</div></li>
				<li class="nav-item"><a href="${pageContext.request.contextPath}/about" class="nav-link">About</a></li>
				<li class="nav-item"><a href="${pageContext.request.contextPath}/contact" class="nav-link">Contact</a></li>
				
				<c:choose>
					<c:when test="${username != null}">
						<li class="nav-item"><a href="#" class="nav-link">Welcome ${username}</a></li>
						<li class="nav-item"><a href="${pageContext.request.contextPath}/logout" class="nav-link">Sign out</a></li>
					</c:when>
					<c:otherwise>
						<li class="nav-item"><a href="${pageContext.request.contextPath}/login" class="nav-link">Sign in</a></li>
					</c:otherwise>
				</c:choose>
				
				<li class="nav-item cta cta-colored" id="cart-size"><a href="${pageContext.request.contextPath}/cart"
					class="nav-link"><span class="icon-shopping_cart"></span>[${cartSize}]</a></li>

			</ul>
		</div>
	</div>
</nav>


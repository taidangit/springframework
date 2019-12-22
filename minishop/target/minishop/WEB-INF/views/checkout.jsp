<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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

	<section class="ftco-section">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-xl-10 ftco-animate">
					<form:form class="billing-form"
						action="${pageContext.request.contextPath}/processCheckout"
						modelAttribute="bill"
						method="POST">
						
						<h3 class="mb-4 billing-heading">Billing Details</h3>
						<div class="row align-items-end">
							<div class="col-md-12">
								<div class="form-group">
									<label for="firstname">Full Name</label>
									<form:input type="text" path="name" class="form-control"
										placeholder="" />
									<form:errors path="name" cssClass="text-danger" />
								</div>
							</div>

							<div class="w-100"></div>

							<div class="w-100"></div>
							<div class="col-md-12">
								<div class="form-group">
									<label for="streetaddress">Address</label>
									<form:input type="text" class="form-control" path="address"
										placeholder="House number and street name" />
									<form:errors path="address" cssClass="text-danger" />
								</div>
							</div>

							<div class="w-100"></div>

							<div class="w-100"></div>
							<div class="col-md-6">
								<div class="form-group">
									<label for="phone">Phone</label>
									<form:input type="text" path="phone" class="form-control"
										placeholder="" />
									<form:errors path="phone" cssClass="text-danger" />
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label for="emailaddress">Email Address</label>
									<form:input path="emailAddress" type="text"
										class="form-control" placeholder="" />
									<form:errors path="emailAddress" cssClass="text-danger" />
								</div>
							</div>
							<div class="w-100"></div>

						</div>
						<p>
							<button type="submit" class="btn btn-primary py-3 px-4">Place
								an order</button>
						</p>
					</form:form>

					<div class="row mt-5 pt-3 d-flex">
						<div class="col-md-6 d-flex">
							<div class="cart-detail cart-total bg-light p-3 p-md-4">
								<h3 class="billing-heading mb-4">Cart Total</h3>
								<p class="d-flex">
									<span>Subtotal</span> <span>${carTotal}</span>
								</p>



							</div>

						</div>
					</div>
					<!-- .col-md-8 -->
				</div>

				<!-- END -->




			</div>
		</div>
	</section>
	<!-- .section -->



	<jsp:include page="footer.jsp" />

	<jsp:include page="script.jsp" />


	<script>
		$(document).ready(function() {

			var quantitiy = 0;
			$('.quantity-right-plus').click(function(e) {

				// Stop acting like a button
				e.preventDefault();
				// Get the field name
				var quantity = parseInt($('#quantity').val());

				// If is not undefined

				$('#quantity').val(quantity + 1);

				// Increment

			});

			$('.quantity-left-minus').click(function(e) {
				// Stop acting like a button
				e.preventDefault();
				// Get the field name
				var quantity = parseInt($('#quantity').val());

				// If is not undefined

				// Increment
				if (quantity > 0) {
					$('#quantity').val(quantity - 1);
				}
			});

		});
	</script>

</body>
</html>
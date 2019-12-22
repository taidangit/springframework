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
						<span class="mr-2"><a href="index.html">Home</a></span> <span>Cart</span>
					</p>
					<h1 class="mb-0 bread">My Wishlist</h1>
				</div>
			</div>
		</div>
	</div>

	<section class="ftco-section ftco-cart">
		<div class="container">
			<div class="row">
				<div class="col-md-12 ftco-animate">
					<div class="cart-list">
						<table class="table">
							<thead class="thead-primary">
								<tr class="text-center">
									<th>&nbsp;</th>
									<th>&nbsp;</th>
									<th>Product</th>
									<th>Price</th>
									<th>Quantity</th>
									<th>Total</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="tempCart" items="${carts}">
									<tr class="text-center">
										<td class="product-remove"><a href="#"><span
												class="ion-ios-close"></span></a></td>

										<td class="image-prod"><div class="img"
												style="background-image:url(${pageContext.request.contextPath}/resources/frontend/images/${tempCart.image});"></div></td>

										<td class="product-name">
											<h3>${tempCart.product}</h3>
											<p>${tempCart.size}</p>
										</td>

										<td class="price" data-cart-price="${tempCart.price}">${tempCart.price}</td>

										<td class="quantity">
											<div class="input-group mb-3">
												<input type="number" name="quantity"
													class="quantity form-control input-number cart-quantity"
													value="${tempCart.quantity}" min="1" max="100">
											</div>
										</td>

										<td class="total"></td>
									</tr>
									<!-- END TR-->
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="row justify-content-start">
				<div class="col col-lg-5 col-md-6 mt-5 cart-wrap ftco-animate">
					<div class="cart-total mb-3">
						<h3>Cart Totals</h3>
						<p class="d-flex">
							<span>Subtotal</span> <span class="cart-totals"></span>
						</p>

					</div>
					<p class="text-center">
						<a href="${pageContext.request.contextPath}/checkout" class="btn btn-primary py-3 px-4">Proceed
							to Checkout</a>
					</p>
				</div>
			</div>
		</div>
	</section>


	<jsp:include page="footer.jsp" />

	<jsp:include page="script.jsp" />


	<script>
		$(document).ready(function(){

		var quantitiy=0;
		   $('.quantity-right-plus').click(function(e){
		        
		        // Stop acting like a button
		        e.preventDefault();
		        // Get the field name
		        var quantity = parseInt($('#quantity').val());
		        
		        // If is not undefined
		            
		            $('#quantity').val(quantity + 1);

		          
		            // Increment
		        
		    });

		     $('.quantity-left-minus').click(function(e){
		        // Stop acting like a button
		        e.preventDefault();
		        // Get the field name
		        var quantity = parseInt($('#quantity').val());
		        
		        // If is not undefined
		      
		            // Increment
		            if(quantity>0){
		            $('#quantity').val(quantity - 1);
		            }
		    });
		    
		});
	</script>

</body>
</html>
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
		style="background-image: url(${pageContext.request.contextPath}/resources/frontend/images/bg_6.jpg);">
		<div class="container">
			<div
				class="row no-gutters slider-text align-items-center justify-content-center">
				<div class="col-md-9 ftco-animate text-center">
					<p class="breadcrumbs">
						<span class="mr-2"><a href="index.html">Home</a></span> <span>Shop</span>
					</p>
					<h1 class="mb-0 bread">Shop</h1>
				</div>
			</div>
		</div>
	</div>

	<section class="ftco-section">
		<div class="container">
			<div class="row">
				<div class="col-lg-6 mb-5 ftco-animate">
					<a
						href="${pageContext.request.contextPath}/resources/frontend/images/${product.image}"
						class="image-popup prod-img-bg"><img id="product-image" data-image="${product.image}"
						src="${pageContext.request.contextPath}/resources/frontend/images/${product.image}"
						class="img-fluid" alt="${product.name}"></a>
				</div>
				<div class="col-lg-6 product-details pl-md-5 ftco-animate">
				
					<h3 id="product-name" data-product-id="${product.id}">${product.name}</h3>
					
					<div class="rating d-flex">
						<p class="text-left mr-4">
							<a href="#" class="mr-2">5.0</a> <a href="#"><span
								class="ion-ios-star-outline"></span></a> <a href="#"><span
								class="ion-ios-star-outline"></span></a> <a href="#"><span
								class="ion-ios-star-outline"></span></a> <a href="#"><span
								class="ion-ios-star-outline"></span></a> <a href="#"><span
								class="ion-ios-star-outline"></span></a>
						</p>
						<p class="text-left mr-4">
							<a href="#" class="mr-2" style="color: #000;">100 <span
								style="color: #bbb;">Rating</span></a>
						</p>
						<p class="text-left">
							<a href="#" class="mr-2" style="color: #000;">500 <span
								style="color: #bbb;">Sold</span></a>
						</p>
					</div>
					<p class="price">
						<span id="product-price">${product.price}</span>

					</p>
					<p>${product.description}</p>

					<div class="row mt-4">
						<div class="col-md-6">
							<div class="form-group d-flex">
								<div class="select-wrap">
									<div class="icon">
										<span class="ion-ios-arrow-down"></span>
									</div>
									<select name="size" id="product-size" class="form-control">
										<c:forEach var="tempSize" items="${productDetails}">

											<option value="${tempSize.size.sizeId}">${tempSize.size.name}</option>

										</c:forEach>

									</select>
								</div>
							</div>
						</div>
						<div class="w-100"></div>
						<div class="input-group col-md-6 d-flex mb-3">
							<span class="input-group-btn mr-2">
								<button type="button" class="quantity-left-minus btn"
									data-type="minus" data-field="">
									<i class="ion-ios-remove"></i>
								</button>
							</span> <input type="text" name="quantity"
								class="quantity form-control input-number" value="1" min="1"
								max="100"> <span class="input-group-btn ml-2">
								<button type="button" class="quantity-right-plus btn"
									data-type="plus" data-field="">
									<i class="ion-ios-add"></i>
								</button>
							</span>
						</div>
						<div class="w-100"></div>

					</div>

					<p>
						<a href="#" class="btn btn-black py-3 px-5 mr-2" id="btnCart">Add
							to Cart</a>
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
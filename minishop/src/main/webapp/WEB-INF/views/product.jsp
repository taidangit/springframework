<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<section class="ftco-section bg-light">
	<div class="container">
		<div class="row justify-content-center mb-3 pb-3">
			<div class="col-md-12 heading-section text-center ftco-animate">
				<h2 class="mb-4">New Shoes Arrival</h2>
				<p>Far far away, behind the word mountains, far from the
					countries Vokalia and Consonantia</p>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<c:forEach var="tempProduct" items="${products}">	
				<div class="col-sm-12 col-md-6 col-lg-3 ftco-animate d-flex">
					<div class="product d-flex flex-column">
						<a href="${pageContext.request.contextPath}/productSingle/${tempProduct.id}" class="img-prod">
							<img class="img-fluid"
								src="${pageContext.request.contextPath}/resources/frontend/images/${tempProduct.image}" alt="${tempProduct.name}">
						</a>
						<div class="text py-3 pb-4 px-3">
							<div class="d-flex">
								<div class="cat">
									<span>Lifestyle</span>
								</div>
								<div class="rating">
									<p class="text-right mb-0">
										<a href="#"><span class="ion-ios-star-outline"></span></a> <a
											href="#"><span class="ion-ios-star-outline"></span></a> <a
											href="#"><span class="ion-ios-star-outline"></span></a> <a
											href="#"><span class="ion-ios-star-outline"></span></a> <a
											href="#"><span class="ion-ios-star-outline"></span></a>
									</p>
								</div>
							</div>
							<h3>
								<a href="${pageContext.request.contextPath}/productSingle/${tempProduct.id}">${tempProduct.name}</a>
							</h3>
							<div class="pricing">
								<p class="price">
									<span>${tempProduct.price}</span>
								</p>
							</div>
							
						</div>
					</div>
				</div>
			</c:forEach>
			
		</div>
	</div>
</section>

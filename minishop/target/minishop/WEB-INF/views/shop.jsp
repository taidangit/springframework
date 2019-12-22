<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
 <jsp:include page="head.jsp" />
</head>
<body class="goto-here">

	<jsp:include page="header.jsp" />

	<jsp:include page="navbar.jsp" />

	<div class="hero-wrap hero-bread"
		style="background-image: url(${pageContext.request.contextPath}/resources/frontend/images/bg_6.jpg');">
		<div class="container">
			<div
				class="row no-gutters slider-text align-items-center justify-content-center">
				<div class="col-md-9 ftco-animate text-center">
					<p class="breadcrumbs">
						<span class="mr-2"><a href="${pageContext.request.contextPath}/home">Home</a></span> <span>Shop</span>
					</p>
					<h1 class="mb-0 bread">Shop</h1>
				</div>
			</div>
		</div>
	</div>

	<section class="ftco-section bg-light">
		<div class="container">
			<div class="row">
				<div class="col-md-8 col-lg-10 order-md-last">
					<div class="row">
						<c:forEach var="tempProduct" items="${productByCategory}">	
							<div class="col-sm-12 col-md-12 col-lg-4 ftco-animate d-flex">
								<div class="product d-flex flex-column">
									<a href="${pageContext.request.contextPath}/productSingle/${tempProduct.id}" class="img-prod"><img class="img-fluid"
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
											<a href="${pageContext.request.contextPath}/productSingle/${tempProduct.id}">
												${tempProduct.name}
											</a>
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
					<div class="row mt-5">
						<div class="col text-center">
							<div class="block-27">
								<ul>
									<li><a href="#">&lt;</a></li>
									<li class="active"><span>1</span></li>
									<li><a href="#">2</a></li>
									<li><a href="#">3</a></li>
									<li><a href="#">4</a></li>
									<li><a href="#">5</a></li>
									<li><a href="#">&gt;</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>

				<div class="col-md-4 col-lg-2">
					<div class="sidebar">
						<div class="sidebar-box-2">
							<h2 class="heading">Categories</h2>
							<div class="fancy-collapse-panel">
								<div class="panel-group" id="accordion" role="tablist"
									aria-multiselectable="true">
									
									<div class="panel panel-default">
										<div class="panel-heading" role="tab" id="headingFour">
											<h4 class="panel-title">
												<a class="collapsed" data-toggle="collapse"
													data-parent="#accordion" href="#collapseFour"
													aria-expanded="false" aria-controls="collapseThree">Clothing
												</a>
											</h4>
										</div>
										<div id="collapseFour" class="panel-collapse collapse"
											role="tabpanel" aria-labelledby="headingFour">
											<div class="panel-body">
												<ul>
													<c:forEach var="tempCategory" items="${categories}">	
														<li><a href="${pageContext.request.contextPath}/productByCategory/${tempCategory.categoryId}">${tempCategory.name}</a></li>
													</c:forEach>
												</ul>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</section>

	<jsp:include page="gallery.jsp" />

	<jsp:include page="footer.jsp" />

	<jsp:include page="script.jsp" />


</body>
</html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="head-admin.jsp" />

</head>

<body class="animsition">
	<div class="page-wrapper">

		<jsp:include page="header-admin.jsp" />

		<jsp:include page="menu-sidebar-admin.jsp" />

		<!-- PAGE CONTAINER-->
		<div class="page-container">


			<!-- MAIN CONTENT-->
			<div class="main-content">
				<div class="section__content section__content--p30">
					<div class="container-fluid">
						<div class="row">

							<div class="col-lg-12">
								<div class="col-lg-12">
									<div class="card">

										<span id="result-save"></span>
										
										
										<div class="card-header">Product Form</div>
										<div class="card-body">
											<div class="card-title">
												<h3 class="text-center title-2"></h3>
											</div>
											<hr>

											<form id="product-form" action="">
												<div class="form-group">
													<label for="" class="control-label mb-1">Product
														name</label> <input name="productName" type="text"
														class="form-control" placeholder="Enter product name">
												</div>

												<div class="form-group">
													<label for="" class="control-label mb-1">Image </label> <input
														id="product-image"  type="file"
														class="form-control">

												</div>

												<div class="form-group">
													<label for="" class="control-label mb-1">Price </label> <input
														name="price" type="text" class="form-control"
														placeholder="Enter product price" />

												</div>

												<div class="form-group">
													<label for="" class="control-label mb-1">Category</label> <select
														name="category" class="form-control">
														<c:forEach var="tempCategory" items="${categories}">
															<option value="${tempCategory.categoryId}">${tempCategory.name}</option>
														</c:forEach>


													</select>
												</div>

												<div class="form-group">
													<label class="control-label mb-1" for="">Description</label>

													<textarea rows="5" name="description"
														placeholder="Enter description" class="form-control"></textarea>
												</div>


											</form>

											<div id="product-detail-container">

												<div id="product-detail" class="product-detail">
													<span>Product Detail</span>


													<div class="form-group">
														<label class="control-label mb-1" for="">Size</label> <select
															name="size" class="form-control">
															<c:forEach items="${sizes}" var="tempSize">
																<option value="${tempSize.sizeId}">${tempSize.name}</option>
															</c:forEach>
														</select>

													</div>

													<div class="form-group">
														<label class="control-label mb-1" for="">Quantity</label>
														<input type="number" name="quantity"  min="1"
															placeholder="Enter quantity" class="form-control" />

													</div>
												</div>

											</div>



											<div>
												<button id="btnDetail" class="btn btn-lg btn-info btn-block">Add
													detail</button>
											</div>
											<br />
											<div>
												<button id="btnSave" class="btn btn-lg btn-info btn-block">Save</button>
											</div>
										</div>



									</div>
								</div>
							</div>
						</div>

					</div>

					

					<jsp:include page="footer-admin.jsp" />
				</div>
			</div>
		</div>

	</div>

	</div>

	<jsp:include page="script-admin.jsp" />

</body>

</html>
<!-- end document-->

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
					
						 <form class="form-header" action="" >
                                <input class="au-input au-input--xl" type="text" name="search" placeholder="Search..." />
                                <button class="au-btn--submit" id="btnSearch">
                                    <i class="zmdi zmdi-search"></i>
                                </button>
                            </form>

						<div class="row">
							<div class="col-md-12">
								<div class="overview-wrap">
									<h2 class="title-1"></h2>
									<a
										href="${pageContext.request.contextPath}/admin/showFormForAdd"
										class="au-btn au-btn-icon au-btn--blue"> <i
										class="zmdi zmdi-plus"></i>add item
									</a>


								</div>
								<br />
								<div class="overview-wrap">
									<h2 class="title-1"></h2>
									<button id="product-delete"
										class="au-btn au-btn-icon au-btn--blue">
										<i class="zmdi zmdi-delete"></i>delete item
									</button>


								</div>

							</div>
							<div class="col-lg-12">
								<h2 class="title-1 m-b-25"></h2>
								<div class="table-responsive table--no-card m-b-40">

									<span id="result-delete"></span>
									<span id="result-update"></span>
									
									<table id="product-table"
										class="table table-borderless table-striped table-earning">
										<thead>
											<tr>
												<th class="text-center">
													<div class="checkbox">
														<label><input id="checkall"
															class="checkbox-product" type="checkbox"></label>
													</div>
												</th>
												<th class="text-center">Product</th>
												<th class="text-center">Image</th>
												<th class="text-center">Price</th>

												<th class="text-center">Action</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="tempProduct" items="${productsLimit}">
												<tr>
													<td class="text-center">
														<div class="checkbox">
															<label><input class="checkbox-product"
																type="checkbox" value="${tempProduct.id}"></label>
														</div>
													</td>
													<td class="text-center">${tempProduct.name}</td>
													<td class="text-center">${tempProduct.image}</td>
													<td class="text-center">${tempProduct.price}</td>

													<td class="text-center"><button
															class="btn btn-info btnEdit" data-toggle="modal"
															data-target=".my-modal"
															data-product-id="${tempProduct.id}">
															<i class="zmdi zmdi-edit"></i>Edit
														</button></td>

												</tr>
											</c:forEach>
										</tbody>
									</table>


								</div>


								<ul class="pagination pagination-sm">
									<li class="page-item"><a class="page-link" href="#">First</a></li>
									<li class="page-item"><a class="page-link" href="#">Previous</a></li>
									<c:forEach var="i" begin="1" end="${totalPages}">
										<c:choose>
											<c:when test="${i==1}">
												<li class="page-item active"><a class="page-link"
													href="#">${i}</a></li>
											</c:when>
											<c:otherwise>
												<li class="page-item"><a class="page-link" href="#">${i}</a></li>
											</c:otherwise>

										</c:choose>


									</c:forEach>
									<li class="page-item"><a class="page-link" href="#">Next</a></li>
									<li class="page-item"><a class="page-link" href="#">End</a></li>
								</ul>
							</div>

						</div>



						<jsp:include page="footer-admin.jsp" />
					</div>
				</div>
			</div>

		</div>

		<div class="modal fade my-modal" role="dialog">
		
			
		
			<div class="modal-dialog modal-lg"
				style="overflow-y: scroll; max-height: 70%; margin-top: 50px; margin-bottom: 50px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Update Product</h4>
					</div>
					<div class="modal-body">

						<form id="product-form" action="">
							<div class="form-group">
								<label for="" class="control-label mb-1">Product name</label> <input
									name="productName" type="text" class="form-control"
									placeholder="Enter product name">
							</div>

							<div class="form-group">
								<label for="" class="control-label mb-1">Image </label> <input
									id="product-image" name="" type="file" class="form-control">

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
								<label class="control-label mb-1" for="">Quantity</label> <input
									type="number" name="quantity" min="1"
									placeholder="Enter quantity" class="form-control" />

							</div>
						</div>
						
						<div id="product-detail-container"></div>
						
					</div>

					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary " id="btnUpdate">Save
							changes</button>
					</div>
				</div>
			</div>
		</div>

	</div>

	<jsp:include page="script-admin.jsp" />
	<!-- 
	<script>
		$(document).ready(function() {
			$("#productListTable").DataTable({
				"lengthMenu": [[5,10,15,20,-1],[5,10,15,20,"All"]],
				"ordering": false,
				stateSave: true
			});
		});
	</script> -->

</body>

</html>
<!-- end document-->

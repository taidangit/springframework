
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script src="//cdnjs.cloudflare.com/ajax/libs/numeral.js/2.0.6/numeral.min.js"></script> 
<style>
	.price{
		font-size: 14px;
	}
</style>
<div class="right_col" role="main">
	<div class="">

		<div class="clearfix"></div>
		<div class="col-md-12 col-sm-12 col-xs-12">
			<div class="x_panel">
				<div class="x_title">
					<h2>History List</h2>

					<div class="page-title">

						<div class="title_right">

							
							<%-- <form action="" id="searchForm">
								<div
									class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
									<div class="input-group">
										<input type="text" class="form-control" value="${actionName}"
											placeholder="Search for..."> <span
											class="input-group-btn">
											<button id="btnSearch" class="btn btn-default">Search!</button>
										</span>
									</div>
								</div>
							</form> --%>
							
							
						</div>
					</div>

					<div class="clearfix"></div>
				</div>


				<div class="x_content">
					<div class="container" style="padding: 50px;">
						<form id="searchForm">
							<div class="form-group">
	
								<label class="control-label col-md-3 col-sm-3 col-xs-12" for="">Type
									<span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<select class="form-control">
										<c:choose>
											<c:when test="${type==0}">
												<option value="0" selected>All</option>
											</c:when>
											<c:otherwise>
												<option value="0">All</option>
											</c:otherwise>
										</c:choose>
										
										<c:choose>
											<c:when test="${type==1}">
												<option value="1" selected>Goods Receipt</option>
											</c:when>
											<c:otherwise>
												<option value="1">Goods Receipt</option>
											</c:otherwise>
										</c:choose>
										
										<c:choose>
											<c:when test="${type==2}">
												<option value="2" selected>Goods Issue</option>
											</c:when>
											<c:otherwise>
												<option value="2">Goods Issue</option>
											</c:otherwise>
										</c:choose>
										
										
									</select>
	
								</div>
	
							</div>
							<br/><br/>
							<div class="form-group">
								<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
									<a href="#" class="btn btn-success" id="btnSearch"><i class="fa fa-search"></i>Search</a>
								</div>
							</div>
						</form>
					</div>

					<div class="table-responsive">
						<table class="table table-striped jambo_table bulk_action">
							<thead>
								<tr class="headings">
									<th class="text-center">#</th>
									<th class="text-center">ID</th>
									<th class="text-center">Category</th>
									<th class="text-center">Code</th>
									<th class="text-center">Name</th>
									<th class="text-center">Quantity</th>
									<th class="text-center">Price</th>
									<th class="text-center">Type</th>
									<th class="text-center">Action</th>
								</tr>
							</thead>

							<tbody>
								<c:forEach items="${histories}" var="history"
									varStatus="loop">

									<c:choose>
										<c:when test="${loop.index%2==0 }">
											<tr class="even pointer">
										</c:when>
										<c:otherwise>
											<tr class="odd pointer">
										</c:otherwise>
									</c:choose>
									<td class="text-center">${pageInfo.offset+loop.index+1}</td>
									<td class="text-center">${history.historyId}</td>
									<td class="text-center">${history.product.category.name }</td>
									<td class="text-center">${history.product.code }</td>
									<td class="text-center">${history.product.name }</td>
									<td class="text-center">${history.quantity}</td>
									<td class="text-center price">${history.price }</td>
									
									<c:choose>
										<c:when test="${history.type == 1}">
											<td class="text-center">Goods Receipt</td>
										</c:when>
										<c:otherwise>
											<td class="text-center">Goods Issues</td>
										</c:otherwise>
									</c:choose>
									<td class="text-center">${history.actionName}</td>
									</tr>
								</c:forEach>

							</tbody>
						</table>

						<ul class="pagination">
							<c:forEach begin="1" end="${pageInfo.totalPages}"
								varStatus="loop">
								<c:choose>
									<c:when test="${pageInfo.currentPage == loop.index}">
										<li class="page-item active"><a href="#">${loop.index}</a></li>
									</c:when>
									<c:otherwise>
										<li class="page-item"><a href="#">${loop.index}</a></li>
									</c:otherwise>
								</c:choose>

								<li></li>
							</c:forEach>
						</ul>

					</div>
				</div>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
	$(document).ready(function() {
		processMessage();

		$("#btnSearch").click(function() {
			var valueSearch = $("#searchForm select").val();
			
			$(this).attr("href", "${pageContext.request.contextPath}/history/search/1/"+ valueSearch);

					
		});

		$(document).on("click",".page-item", function() {
			$(".page-item").removeClass("active");
			$(this).addClass("active");

			var currentPage = $(this).text();

			var valueSearch = $("#searchForm select").val();

			if (valueSearch == 0) {
				$(this).find("a").attr("href", "${pageContext.request.contextPath}/history/list/"+ currentPage);
			} else {
				$(this).find("a").attr("href", "${pageContext.request.contextPath}/history/search/"+ currentPage + "/"+ valueSearch);
				
			}

		});

	});

	$(".price").each(function(){ 
		 $(this).text(numeral($(this).text()).format('0,0'));
	 });

	function processMessage() {
		var msgSuccess = '${msgSuccess}';
		var msgError = '${msgError}';
		if (msgSuccess) {
			new PNotify({
				title : ' Success',
				text : msgSuccess,
				type : 'success',
				styling : 'bootstrap3'
			});
		}
		if (msgError) {
			new PNotify({
				title : ' Error',
				text : msgError,
				type : 'error',
				styling : 'bootstrap3'
			});
		}
	}
</script>
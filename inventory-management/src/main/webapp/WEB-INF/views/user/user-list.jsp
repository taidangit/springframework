<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="right_col" role="main">
	<div class="">

		<div class="clearfix"></div>
		<div class="col-md-12 col-sm-12 col-xs-12">
			<div class="x_panel">
				<div class="x_title">
					<h2>User List</h2>

					<div class="page-title">

						<div class="title_right">
						
							<form id="searchForm">
								<div class="col-md-7 col-sm-7 col-xs-12 form-group pull-right top_search">
									<div class="input-group">
										<input type="text" class="form-control" value="${name}"
											placeholder="Enter name..."> <span
											class="input-group-btn">
											<a href="#" id="btnSearch" class="btn btn-default"><i class="fa fa-search"></i>Search</a>
											<a href="${pageContext.request.contextPath}/user/list" class="btn btn-default"><i class="fa fa-arrow-left"></i>Back</a>
										</span>
									</div>
								</div>
							</form>
							
						</div>
					</div>

					<div class="clearfix"></div>
				</div>


				<div class="x_content">
					<a href="${pageContext.request.contextPath}/user/add"
						class="btn btn-app"><i class="fa fa-plus"></i>Add</a>

					<div class="table-responsive">
						<table class="table table-striped jambo_table bulk_action">
							<thead>
								<tr class="headings">
									<th class="text-center">#</th>
									<th class="text-center">Id</th>
									<th class="text-center">User Name</th>
									<th class="text-center">Name</th>
									<th class="text-center">Email</th>
									<th class="text-center" colspan="3"><span class="nobr">Action</span></th>
								</tr>
							</thead>

							<tbody>
								<c:forEach items="${users}" var="user" varStatus="loop">

									<c:choose>
										<c:when test="${loop.index%2==0 }">
											<tr class="even pointer">
										</c:when>
										<c:otherwise>
											<tr class="odd pointer">
										</c:otherwise>
									</c:choose>
									<td class="text-center">${pageInfo.offset+loop.index+1}</td>
									<td class="text-center">${user.userId}</td>
									<td class="text-center">${user.username}</td>
									<td class="text-center">${user.name}</td>
									<td class="text-center">${user.email}</td>
									<td class="text-center"><a
										href="${pageContext.request.contextPath}/user/view/${user.userId}"
										class="btn btn-round btn-success"><i class="fa fa-check"></i>View</a>
									</td>
									<td class="text-center"><a
										href="${pageContext.request.contextPath}/user/edit/${user.userId}"
										class="btn btn-round btn-primary"><i class="fa fa-pencil"></i>Edit</a>
									</td>
									<td class="text-center"><a
										href="${pageContext.request.contextPath}/user/delete/${user.userId}"
										class="btn btn-round btn-danger"
										onclick="if (!(confirm('Are you sure you want to delete this user ?'))) return false"><i
											class="fa fa-remove"></i>Delete</a></td>
									</tr>
								</c:forEach>

							</tbody>
						</table>
						
						<ul class="pagination">
							<c:forEach begin="1" end="${pageInfo.totalPages}" varStatus="loop">
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
			var name = $("#searchForm input").val();

			$(this).attr("href", "${pageContext.request.contextPath}/user/search/1/"+name);
		
			
		});
		
		$(document).on("click", ".page-item", function() { 
			$(".page-item").removeClass("active");
			$(this).addClass("active");
			
			var currentPage = $(this).text();
		
			var name = $("#searchForm input").val();

			if(name == "") {
				$(this).find("a").attr("href", "${pageContext.request.contextPath}/user/list/"+currentPage);
			} else {
				$(this).find("a").attr("href", "${pageContext.request.contextPath}/user/search/"+currentPage+"/"+name);
				
			}
		
		});
		
	}); 

	
	function processMessage(){
		 var msgSuccess = '${msgSuccess}';
		 var msgError = '${msgError}';
		 if(msgSuccess){
			 new PNotify({
                title: ' Success',
                text: msgSuccess,
                type: 'success',
                styling: 'bootstrap3'
            });
		 }
		 if(msgError){
			 new PNotify({
                title: ' Error',
                text: msgError,
                type: 'error',
                styling: 'bootstrap3'
            });
		 }
	 }

	
</script>
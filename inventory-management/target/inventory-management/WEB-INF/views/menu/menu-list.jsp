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
					<h2>Menu List</h2>


					<div class="clearfix"></div>
				</div>


				<div class="x_content">
					<a href="${pageContext.request.contextPath}/menu/permission" class="btn btn-app"><i class="fa fa-plus"></i>Permission</a>

					<div class="table-responsive">
						<table class="table table-striped jambo_table bulk_action">
							<thead>
								<tr class="headings">
									<th rowspan="2" class="text-center">#</th>
									<th rowspan="2" class="text-center">Id</th>
									<th rowspan="2" class="text-center">Url</th>
									<th rowspan="2" class="text-center">Status</th>
									<th class="text-center" colspan="${roles.size()}">Role</th>
									
								</tr>
								
								<tr>
										<c:forEach var="role" items="${roles}">
											<th class="text-center">${role.roleName}</th>
										</c:forEach>
								</tr>
									
								
							</thead>

							<tbody>
								<c:forEach items="${menus}" var="menu" varStatus="loop">

									<c:choose>
										<c:when test="${loop.index%2==0 }">
											<tr class="even pointer">
										</c:when>
										<c:otherwise>
											<tr class="odd pointer">
										</c:otherwise>
									</c:choose>
									<td class="text-center">${pageInfo.offset+loop.index+1}</td>
									<td class="text-center">${menu.menuId}</td>
									<td class="text-center">${menu.url}</td>
									
									<c:choose>
										<c:when test="${menu.activeFlag == 1}">
											<td class="text-center"><a href="${pageContext.request.contextPath}/menu/change-status/${menu.menuId}" 
											class="btn btn-round btn-danger"
											onclick="if (!(confirm('Are you sure you want to disable this menu ?'))) return false">Disable</a></td></td>
										</c:when>
										<c:otherwise>
											<td class="text-center"><a href="${pageContext.request.contextPath}/menu/change-status/${menu.menuId}" class="btn btn-round btn-primary">Enable</a></td></td>
										</c:otherwise>

									</c:choose>
									
									<%-- <c:forEach items="${roles}" var="role">
										<c:forEach items="${role.auths}" var="auth">
											<c:choose>
												<c:when test="${auth.permission == 1}">
													<td class="text-center"><i class="fa fa-check" style="color: green;"></i></td>
												</c:when>
												<c:otherwise>
													<td class="text-center"><i class="fa fa-times" style="color:red;"></i></td>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</c:forEach> --%>
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
			var roleName = $("#searchForm input").val();

			$("#searchForm").attr("action", "${pageContext.request.contextPath}/role/search/1/"+roleName);
		
			$("#searchForm").submit();
		});
		
		$(document).on("click", ".page-item", function() { 
			$(".page-item").removeClass("active");
			$(this).addClass("active");
			
			var currentPage = $(this).text();
		
			var name = $("#searchForm input").val();

			if(name == "") {
				$(this).find("a").attr("href", "${pageContext.request.contextPath}/menu/list/"+currentPage);
			} else {
				$("#searchForm").attr("action", "${pageContext.request.contextPath}/role/search/"+currentPage+"/"+roleName);
				$("#searchForm").submit();
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
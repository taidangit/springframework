<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="right_col" role="main">
	<div class="">
		<div class="page-title">
			<div class="title_left">
				<h2>${titlePage}</h2>
			</div>
		</div>
		<div class="clearfix"></div>
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">

					
					
					<div class="x_content">
						<br />
						<form:form modelAttribute="product"
							cssClass="form-horizontal form-label-left"
							action="${pageContext.request.contextPath}/product/save" enctype="multipart/form-data"
							method="POST">
							<form:hidden path="productId" />

							<c:if test="${errorUpload != null}">
								<div class="alert alert-danger">${errorUpload}</div>
							</c:if>

							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="code">Code <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<form:input path="code"
										cssClass="form-control col-md-7 col-xs-12" disabled="${viewOnly}" />
									<form:errors path="code" cssClass="text-danger" />
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="name">Name <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<form:input path="name"
										cssClass="form-control col-md-7 col-xs-12" disabled="${viewOnly}" />
									<form:errors path="name" cssClass="text-danger" />
								</div>
							</div>
							<c:if test="${!viewOnly }">
								<div class="form-group">
									<label for="multipartFile"
										class="control-label col-md-3 col-sm-3 col-xs-12">Image
										Upload</label>
									<div class="col-md-6 col-sm-6 col-xs-12">
										<form:input path="multipartFile"
											class="form-control col-md-7 col-xs-12" type="file" />
									
									</div>
								</div> 
							</c:if>
							
							<c:choose>
								<c:when test="${!viewOnly}">
								
									<div class="form-group">
	
										<label class="control-label col-md-3 col-sm-3 col-xs-12" for="cateId">Category <span class="required">*</span>
										</label>
										<div class="col-md-6 col-sm-6 col-xs-12">
											<form:select path="category.categoryId" class="form-control">
												<c:forEach var="category" items="${categories}">
													<form:option value="${category.categoryId}">${category.name}</form:option>
												</c:forEach>	
											</form:select>
													
										</div>
		
									</div>
								</c:when>
								<c:otherwise>
									<div class="form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="">Category <span class="required">*</span>
									</label>
										<div class="col-md-6 col-sm-6 col-xs-12">
											
											<form:input path="category.name" disabled="true" cssClass="form-control col-md-7 col-xs-12"/>
									
										</div>
									</div>
								</c:otherwise>
							</c:choose>
									
							<div class="form-group">
								<label for="description"
									class="control-label col-md-3 col-sm-3 col-xs-12">Description</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<form:textarea path="description" rows="6"
										cssClass="form-control col-md-7 col-xs-12" disabled="${viewOnly}" />
									<form:errors path="description" cssClass="text-danger" />
								</div>
								
							</div>
							<div class="ln_solid"></div>
							<div class="form-group">
								<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
									<button class="btn btn-primary" type="button" id="btnCancel">Cancel</button>
									<c:if test="${!viewOnly}">
										<button class="btn btn-primary" type="reset">Reset</button>
										<button type="submit" class="btn btn-success">Submit</button>
									</c:if>
								</div>
							</div>

						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		$('#productListId').addClass('current-page').siblings()
				.removeClass('current-page');
		var parent = $('#productListId').parents('li');
		parent.addClass('active').siblings().removeClass('active');
		$('#productListId').parents().show();

		$("#btnCancel").click(function() {
			
			window.location.href = '${pageContext.request.contextPath}/product/list';
			
		});
	}); 

	
</script>
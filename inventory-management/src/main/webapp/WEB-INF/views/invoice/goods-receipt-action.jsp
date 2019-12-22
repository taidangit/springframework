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
						<form:form modelAttribute="invoice"
							cssClass="form-horizontal form-label-left"
							action="${pageContext.request.contextPath}/goods-receipt/save"
							method="POST">
							<form:hidden path="invoiceId" />
							
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
							
							<c:choose>
								<c:when test="${!viewOnly}">
									<div class="form-group">
			
										<label class="control-label col-md-3 col-sm-3 col-xs-12" for="">Product <span class="required">*</span>
										</label>
										<div class="col-md-6 col-sm-6 col-xs-12">
											<form:select path="product.productId" class="form-control">
												<c:forEach var="product" items="${products}">
													<form:option value="${product.productId}">${product.name}</form:option>
												</c:forEach>	
											</form:select>
													
										</div>
			
									</div>
								</c:when>
								
								<c:otherwise>
									<div class="form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="">Product <span class="required">*</span>
									</label>
										<div class="col-md-6 col-sm-6 col-xs-12">
											
											<form:input path="product.name" disabled="true" cssClass="form-control col-md-7 col-xs-12"/>
									
										</div>
									</div>
								</c:otherwise>
							</c:choose>
							<div class="form-group">
								<label for="description"
									class="control-label col-md-3 col-sm-3 col-xs-12">Quantity</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<form:input path="quantity" min="1" type="number"
										cssClass="form-control col-md-7 col-xs-12" disabled="${viewOnly}" />
									
								</div>
							</div>
							
							<div class="form-group">
								<label for="description"
									class="control-label col-md-3 col-sm-3 col-xs-12">Price</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<form:input path="price"
										cssClass="form-control col-md-7 col-xs-12" disabled="${viewOnly}" />
									
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
		$('#categoryListId').addClass('current-page').siblings()
				.removeClass('current-page');
		var parent = $('#categoryListId').parents('li');
		parent.addClass('active').siblings().removeClass('active');
		$('#categoryListId').parents().show();

		$("#btnCancel").click(function() {
			
			window.location.href = '${pageContext.request.contextPath}/goods-receipt/list';
			
		});
	}); 

	
</script>
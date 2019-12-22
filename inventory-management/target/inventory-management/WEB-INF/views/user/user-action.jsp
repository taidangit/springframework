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
						<form:form modelAttribute="user"
							cssClass="form-horizontal form-label-left"
							action="${pageContext.request.contextPath}/user/save"
							method="POST">
							<form:hidden path="userId" />
							
							<c:if test="${usernameAlreadyExist != null}">
								<div class="alert alert-danger">
									${usernameAlreadyExist}
								</div>
							</c:if>
							
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="name">Full Name<span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<form:input path="name"
										cssClass="form-control col-md-7 col-xs-12" disabled="${viewOnly}" />
									<form:errors path="name" cssClass="text-danger" />
								</div>
							</div>
							
							<div class="form-group">
								<label for="description"
									class="control-label col-md-3 col-sm-3 col-xs-12">Username</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<form:input path="username"
										cssClass="form-control col-md-7 col-xs-12" disabled="${viewOnly}" />
									<form:errors path="username" cssClass="text-danger" />
								</div>
							</div>
							
							<div class="form-group">
								<label for="description"
									class="control-label col-md-3 col-sm-3 col-xs-12">Email</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<form:input path="email"
										cssClass="form-control col-md-7 col-xs-12" disabled="${viewOnly}" />
									<form:errors path="email" cssClass="text-danger" />
								</div>
							</div>
							
							<c:if test="${!editMode}">
								<div class="form-group">
									<label for="description"
										class="control-label col-md-3 col-sm-3 col-xs-12">Password</label>
									<div class="col-md-6 col-sm-6 col-xs-12">
										<form:input path="password" type="password"
											cssClass="form-control col-md-7 col-xs-12" disabled="${viewOnly}" />
										<form:errors path="password" cssClass="text-danger" />
									</div>
								</div>
							</c:if>
							
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
		$('#userListId').addClass('current-page').siblings()
				.removeClass('current-page');
		var parent = $('#userListId').parents('li');
		parent.addClass('active').siblings().removeClass('active');
		$('#userListId').parents().show();

		$("#btnCancel").click(function() {
			
			window.location.href = '${pageContext.request.contextPath}/user/list';
			
		});
	}); 

	
</script>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="right_col" role="main">
	<div class="">
		<div class="page-title">
			<div class="title_left">
				<h2>Update Permission</h2>
			</div>
		</div>
		<div class="clearfix"></div>
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">

					<div class="x_content">
						<br />
						<form:form modelAttribute="auth"
							cssClass="form-horizontal form-label-left"
							action="${pageContext.request.contextPath}/menu/update-permission" 
							
							method="POST">
						
							<div class="form-group">

								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="">Role<span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<form:select path="role.roleId" class="form-control">
										<c:forEach var="role" items="${roles}">
											<form:option value="${role.roleId}">${role.roleName}</form:option>
										</c:forEach>
									</form:select>

								</div>

							</div>

							<div class="form-group">

								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="">Menu <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<form:select path="menu.menuId" class="form-control">
										<c:forEach var="menu" items="${menus}">
											<form:option value="${menu.menuId}">${menu.url}</form:option>
										</c:forEach>
									</form:select>

								</div>

							</div>
							
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12" for="code">
									Permission
									<span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<form:radiobutton path="permission" value="1" />
									Yes
									<form:radiobutton path="permission" value="0" />
									No
								</div>
							</div>
							
							<div class="ln_solid"></div>
							<div class="form-group">
								<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
									<button class="btn btn-primary" type="button" id="btnCancel">Cancel</button>
									
										<button type="submit" class="btn btn-success">Submit</button>
									
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
		$('#menuListId').addClass('current-page').siblings()
				.removeClass('current-page');
		var parent = $('#menuListId').parents('li');
		parent.addClass('active').siblings().removeClass('active');
		$('#menuListId').parents().show();

		$("#btnCancel").click(function() {
			
			window.location.href = '${pageContext.request.contextPath}/menu/list';
			
		});
	}); 

	
</script>
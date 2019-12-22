<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="col-md-12">
	<h3>Send Message</h3>
	
	<form:form method="POST" modelAttribute="message">

		<input type="hidden" name="_flowExecutionKey"
			value="${flowExecutionKey}" />
			
		<input type="hidden" name="_eventId" value="send" />

		<div class="form-group">
			<label class="control-label col-md-2">Name</label>
			<div class="col-md-10">
				<form:input path="name" class="form-control" value="${fromName}"/>
				<form:errors path="name" cssClass="text-danger" />
			</div>
		</div>

		<div class="form-group">
			<label class="control-label col-md-2">Email </label>
			<div class="col-md-10">
				<form:input path="email" class="form-control"  value="${fromEmail}" />
				<form:errors path="email" cssClass="text-danger" />
			</div>
		</div>

		<div class="form-group">
			<label class="control-label col-md-2">Subject </label>
			<div class="col-md-10">
				<form:input path="subject" class="form-control" value="${fromSubject}" />
				<form:errors path="subject" cssClass="text-danger" />
			</div>
		</div>

		<div class="form-group">
			<label class="control-label col-md-2">Content </label>
			<div class="col-md-10">
				<form:textarea rows="6" path="content" class="form-control" value="${fromContent}" />
				<form:errors path="content" cssClass="text-danger" />
			</div>
		</div>

		<div class="form-group">
			<div class="col-md-6 col-md-offset-2">

				<button type="submit" class="btn btn-success">Send message</button>

			</div>
		</div>

	</form:form>

</div>




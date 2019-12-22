<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>

<html>

<head>
	<title>List Customers</title>
	
	
	<!-- reference our style sheet -->
	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css" />
	
	<!-- Reference Bootstrap files -->
	<link rel="stylesheet"
		 href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
	<!-- data table -->
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
  
	<script type="text/javascript" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
	
</head>

<body>

	<div class="container">
		<div id="header">
			<h2 style="text-align: center;">CRM - Customer Relationship Manager</h2>
		</div>
	</div>
	
	<div class="container">
	
		<div id="content">
		
			<!-- put new button: Add Customer -->
		
			<input type="button" value="Add Customer"
				   onclick="window.location.href='showFormForAdd'; return false;"
				   class="add-button"
			/>
		
			<!--  add a search box -->
			<%-- <form:form action="search">
				Search customer: <input type="text" name="theSearchName" />
				
				<input type="submit" value="Search" class="add-button" />
			</form:form> --%>
			
			<!--  add our html table here -->
		
			<table id="customerListTable">
				<thead>
					<tr>
						<th class="text-center">First Name</th>
						<th class="text-center">Last Name</th>
						<th class="text-center">Email</th>
						<th class="text-center">Action</th>
					</tr>
				</thead>
				
				<tbody>
				<!-- loop over and print our customers -->
					<c:forEach var="tempCustomer" items="${customers}">		
					
						<tr>
							<td class="text-center"> ${tempCustomer.firstName} </td>
							<td class="text-center"> ${tempCustomer.lastName} </td>
							<td class="text-center"> ${tempCustomer.email} </td>
						
							<td class="text-center">
								
								<a href="${pageContext.request.contextPath}/customer/showFormForUpdate/${tempCustomer.id}">Update</a>
								|
								<a href="${pageContext.request.contextPath}/customer/delete/${tempCustomer.id}"
							   	onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a>
							</td>
						
						</tr>
				
					</c:forEach>
				</tbody>	
			</table>
				
		</div>
	
	</div>
	
	<script>
		$(document).ready(function() {
			$("#customerListTable").DataTable({
				"lengthMenu": [[5,10,15,20,-1],[5,10,15,20,"All"]],
				"ordering": false,
				stateSave: true
			});
		});
	</script>
</body>

</html>










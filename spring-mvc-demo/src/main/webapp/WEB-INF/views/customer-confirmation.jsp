<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Confirmation</title>
</head>
<body>
	The Customer is confirmed: ${customer.firstName} ${customer.lastName}<br><br>
	Free passes: ${customer.freePasses}<br><br>
	Postal code: ${customer.postalCode}<br><br>
	Course code: ${customer.courseCode}<br><br>
</body>
</html>
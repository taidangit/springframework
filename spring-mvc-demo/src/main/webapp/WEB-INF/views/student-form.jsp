<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Form</title>
</head>
<body>
	<form:form action="processForm" modelAttribute="student" method="POST">
	
		First name: <form:input path="firstName" type="text" /><br><br>
		Last name: <form:input path="lastName" type="text" /><br><br>
		
		Country:
		<form:select path="country">
			<%-- <form:options items="${student.countryOptions}" /> --%>
			<form:options items="${theCountryOptions}" />
		</form:select>
		<br><br>
		
		Favorite Language:
		<%-- Java <form:radiobutton path="favoriteLanguage" value="Java"/>
		Python <form:radiobutton path="favoriteLanguage" value="Python"/>
		C# <form:radiobutton path="favoriteLanguage" value="C#"/>
		PHP <form:radiobutton path="favoriteLanguage" value="PHP"/>
		Ruby <form:radiobutton path="favoriteLanguage" value="Ruby"/> --%>
		
		<form:radiobuttons path="favoriteLanguage" items="${student.favoriteLanguageOptions}"  />
		
		<br><br>
		
		Operation Systems:
		Linux <form:checkbox path="operatingSystems" value="Linux" />
		Mac OS <form:checkbox path="operatingSystems" value="Mac OS" />
		MS Windows <form:checkbox path="operatingSystems" value="MS Wimdows" />
		
		<br><br>
		<input type="submit" value="Submit" />
		
	</form:form>
</body>
</html>
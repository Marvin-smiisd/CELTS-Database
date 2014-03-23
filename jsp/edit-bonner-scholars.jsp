<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Volunteers - Edit </title>
<link rel="stylesheet" href="<c:url value="/resources/styles/main.css" />"/>

<style>
table td {
	border: 0px;
}
</style>

<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
</head>
<body>

	
	<c:url var="homeUrl" value="/home" />
	<section id="banner" style="margin-bottom:-30px;">
		<p style="float: left; font-size: 28px;">
	    <a href="${homeUrl}"><img src="<c:url value="/resources/images/logo.png" />" style="width: 50px; height: 50px; margin-right:10px;" /> </a>
		</p>
		<p
			style="font-size: 28px;  margin-left: 0px; margin-top:26px; width:500px; line-height:27px">
			<span>Center for Excellence in Learning</span> through Service (CELTS) Database</p>

	</section>

	<section id="contentWraper">
		<p
			style="color: black; font-weight: bold; margin-top: -17px; margin-left: 945px;">
			<span style="padding-right: 10px;"> <a href="${homeUrl}"
				style="color: darkblue; text-decoration: none;">Home</a></span> <span><a
				style="color: darkblue; text-decoration: none;" href="../signin.jsp">Sign
					out</a></span>
		</p>

		<section
			style="float: left; width: 1015px; margin-top: 0px; margin-left: 16px; margin-bottom: 15px; border-bottom: 1px solid silver;">
			<p
				style="color: brown; font-size: 18px; margin-top: 15px; margin-left: 1px; margin-bottom: 0px;">Community
				Service &#8250; Student Volunteers &#8250; Edit</p>
		</section>

		<section id="stuTable">

<c:url var="saveUrl" value="/bnrSchlr/editBnrScholars?id=${bonnerScholarsAttribute.id}" />
<form:form modelAttribute="bonnerScholarsAttribute" method="POST" action="${saveUrl}">
	<table  style="border: 0px solid; width: 245px; padding:0px; text-align:right; margin:17px;">

		<tr>
			<td><form:label path="firstName">First Name:</form:label></td>
			<td><form:input path="firstName"/></td>
		</tr>

		<tr>
			<td><form:label path="lastName">Last Name:</form:label></td>
			<td><form:input path="lastName"/></td>
		</tr>
		
		<tr>
		<td></td><td><input type="submit" value="Save" style="float:right; margin-top:-7px; margin-bottom:"/></td>
		</tr>
	</table>
	
</form:form>

		</section>
	</section>

</body>
</html>

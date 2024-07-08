<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- JSTL functions -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- Formatting Tags -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<!-- for Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<!-- YOUR own local CSS -->
<link rel="stylesheet" href="/css/main.css" />
</head>
<body class="bg-dark text-light">
	<div style="display: flex" class="container">
		<div class="container">
			<h1>Register</h1>
			<form:form action="/register" method="post" modelAttribute="newUser">
				<div class="mb-3">
					<form:label class="form-label" path="userName">User Name:</form:label>
					<form:input class="form-control" path="userName" />
					<form:errors path="userName" class="text-danger" />
				</div>
				<div class="mb-3">
					<form:label class="form-label" path="email">Email:</form:label>
					<form:input class="form-control" path="email" />
					<form:errors path="email" class="text-danger" />
				</div>
				<div class="mb-3">
					<form:label class="form-label" path="password">Password:</form:label>
					<form:input class="form-control" type="password" path="password" />
					<form:errors path="password" class="text-danger" />
				</div>
				<div class="mb-3">
					<form:label class="form-label" path="confirm">Confirm Password:</form:label>
					<form:input class="form-control" type="password" path="confirm" />
					<form:errors path="confirm" class="text-danger" />
				</div>
				<input type="submit" value="Submit" class="btn btn-warning" />
			</form:form>
		</div>
		<div class="container">
			<h1>Log-In</h1>
			<form:form action="/login" method="post" modelAttribute="newLogin">
				<div class="mb-3">
					<form:label class="form-label" path="email">Email:</form:label>
					<form:input class="form-control" path="email" />
					<form:errors path="email" class="text-danger" />
				</div>
				<div class="mb-3">
					<form:label class="form-label" path="password">Password:</form:label>
					<form:input class="form-control" type="password" path="password" />
					<form:errors path="password" class="text-danger" />
				</div>
				<input type="submit" value="Submit" class="btn btn-warning" />
			</form:form>
		</div>

	</div>
</body>
</html>
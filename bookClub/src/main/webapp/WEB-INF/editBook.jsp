<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!-- JSTL functions --> 
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<!-- Formatting Tags -->
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<!-- for Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<!-- YOUR own local CSS -->
<link rel="stylesheet" href="/css/main.css"/>
</head>
<body class="bg-dark text-light">
<div class="container">
	<h1> Edit ${editBook.title}</h1>
	<form:form action="/books/edit/${editBook.getId()}" method="put" modelAttribute="editBook">
		<div class="mb-3">
			<form:label class="form-label" path="title">Title:</form:label>
			<form:input class="form-control" path="title"/>
			<form:errors path="title"/>
		</div>
		<div class="mb-3">
			<form:label class="form-label" path="author">Author:</form:label>
			<form:input class="form-control" path="author" />
			<form:errors path="author"/>
		</div>
		<div class="mb-3">
			<form:label class="form-label" path="thoughts">My Thoughts:</form:label>
			<form:input class="form-control" path="thoughts"/>
			<form:errors path="thoughts"/>
		</div>
			<form:hidden path="reader"/>
			<input type="submit" value="Submit" class="btn btn-warning" />
			<a href="/books/${editBook.getId()}" class="btn btn-danger">Cancel</a>
		</form:form>
	</div>
</body>
</html>
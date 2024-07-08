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
	<div class="container">
		<h1>
			<c:out value="${genre.genre }"></c:out>
		</h1>


		<table class="table text-light">
			<thead>
				<tr>
					<th scope="col">Genres</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="book" items="${genre.genreBooks}">
					<tr>
						<td><a href="/books/${book.id}"><c:out value="${book.title}" /></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<a href="/books" class="btn btn-warning">Back</a>
	</div>
</body>
</html>
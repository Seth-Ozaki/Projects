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
		<div class="container">
			<h1>
				<c:out value="${book.title}"></c:out>
			</h1>
			<c:if test="${book.reader.id == id }">
				<h3>
					<span style="color: red;">You</span> read <span
						style="color: purple;"><c:out value="${book.title}" /></span> by
					<span style="color: green;"><c:out value="${book.author}" /></span>
				</h3>
				<h3>Here are your thoughts:</h3>
			</c:if>
			<c:if test="${book.reader.id != id }">
				<h3>
					<span style="color: red;"> <c:out
							value="${book.reader.userName}" />
					</span> read <span style="color: purple;"> <c:out
							value="${book.title}" />
					</span> by <span style="color: green;"> <c:out
							value="${book.author}" />
					</span>
				</h3>
				<h3>
					Here are
					<c:out value="${book.reader.userName}" />
					thoughts:
				</h3>
			</c:if>
			<p style="border: 2px solid gray">
				<c:out value="${book.thoughts}"></c:out>
			</p>

			<div class="container">

				<table class="table text-light">
					<thead>
						<tr>
							<th scope="col">Genres</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="genre" items="${book.genres}">
							<tr>
								<td><a href="/genres/${genre.id}"><c:out value="${genre.genre}" /></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="column align-items-start d-flex gap-2">
				<a href="/books" class="btn btn-warning">Back</a>
				<c:if test="${book.reader.id == id}">
					<a href="/books/edit/${book.id}" class="btn btn-info">Edit</a>
					<form action="/books/delete/${book.id}" method="post">
						<input type="hidden" name="_method" value="delete"> <input
							type="submit" value="Delete" class="btn btn-danger">
					</form>
				
					<form action="/books/genre/${book.id}" method="post">
						<label class="form-label">add genre:</label> 
						<select name="genreId"	class="form-select">
							<c:forEach var="genre" items="${genres}">
							<c:if test="${not book.genres.contains(genre)}">
								<option value="${genre.id}">
									<c:out value="${genre.genre}" />
								</option>
							</c:if>
							</c:forEach>
						</select>
						<input type="submit" value="add"class="btn btn-success">
					</form>
				</c:if>
		
			</div>
		</div>

	</div>
</body>
</html>
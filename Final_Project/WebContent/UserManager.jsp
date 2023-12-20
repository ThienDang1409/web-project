<%@page import="java.util.List"%>
<%@page import="cn.techtutorial.model.User"%>
<%@page import="cn.techtutorial.connection.DbCon"%>
<%@page import="cn.techtutorial.dao.UserDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
UserDao userData = new UserDao(DbCon.getConnection());
List<User> user = userData.getAllUser();
request.setAttribute("BOOKS_LIST", user);
%>


<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<title>CRUD Application</title>

<style>
.inner {
	margin: 15px 0;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<nav class="navbar navbar-light">
			<a  href="adminindex.jsp" class="navbar-brand">Fruit Shop</a>
			<form class="form-inline">
				<input class="form-control mr-sm-2" type="search"
					placeholder="Search" aria-label="Search">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form>
		</nav>
	</div>
	<div class="container">
		<div class="inner">
			<div class="row">
				<div class="col-md-9">
					<h3>User Information</h3>
					<table class="table">
						<thead class="bg-light">
							<tr>
								<th scope="col">Name</th>
								<th scope="col">Email</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="tempBook" items="${BOOKS_LIST}">
								<tr>
									<td>${tempBook.name }</td>
									<td>${tempBook.email }</td>
									<td>
										<a href="DeleteUserServlet?id=${tempBook.id}">Delete</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</body>
</html>
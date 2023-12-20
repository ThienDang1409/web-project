
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.List"%>
<%@page import="cn.techtutorial.model.Product"%>
<%@page import="cn.techtutorial.connection.DbCon"%>
<%@page import="cn.techtutorial.dao.ProductDao"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
ProductDao productData = new ProductDao(DbCon.getConnection());
List<Product> product = productData.getAllProducts();
request.setAttribute("BOOKS_LIST", product);
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
			<!--<form class="form-inline">
				<input class="form-control mr-sm-2" type="search"
					placeholder="Search" aria-label="Search">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form>-->
			<form class="form-inline" action="SearchProductServlet" method="get">
			    <input class="form-control mr-sm-2" type="search" name="search" placeholder="Search" aria-label="Search">
			    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form>
			
		</nav>
	</div>
	<div class="container">
		<div class="inner">
			<div class="row">
				<div class="col-md-3">
					<h3>Fruit Information</h3>
					<form action="AddProductServlet" method="post">
						<div class="form-group">
							<label>Fruit Name</label> <input class="form-control"
								name="pname" place-holder="Book Name" required>
						</div>
						<div class="form-group">
							<label>Category</label> <input class="form-control"
								name="pcat" place-holder="Book Name" required>
						</div>
						<div class="form-group">
							<label>Price</label> <input class="form-control"
								name="price" place-holder="Book Name" required>
						</div>
						<!--<div class="form-group">
							<label>Image</label> <input class="form-control"
								name="image" place-holder="Book Name" required>
						</div>-->
						<div class="form-group">
					        <label for="image">Image</label>
					        	<input type="file" class="form-control" name="image" id="image" required>
				    	</div>
						<button type="submit" class="btn btn-primary">Submit</button>
						<button type="reset" class="btn btn-primary">Reset</button>
					</form>
				</div>
				<div class="col-md-9">
					<h3>Fruit Information</h3>
					<table class="table">
						<thead class="bg-light">
							<tr>
								<th scope="col">Name</th>
								<th scope="col">Category</th>
								<th scope="col">Price</th>
								<th scope="col">Image</th>
							</tr>
						</thead>
						<!--<tbody>
							<c:forEach var="tempBook" items="${BOOKS_LIST}">
								<tr>
									<td>${tempBook.name }</td>
									<td>${tempBook.category }</td>
									<td>${tempBook.price }</td>
									<td>${tempBook.image}</td>
									<td>
										<a href="editproduct.jsp?id=${tempBook.id }">Edit</a> 
										<a href="DeleteProductServlet?id=${tempBook.id}">Delete</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>-->
						<tbody>
                            <c:if test="${not empty SEARCH_RESULTS}">
                                <c:forEach var="tempBook" items="${SEARCH_RESULTS}">
                                    <tr>
                                        <td>${tempBook.name}</td>
                                        <td>${tempBook.category}</td>
                                        <td>${tempBook.price}</td>
                                        <td>${tempBook.image}</td>
                                        <td>
                                            <a href="editproduct.jsp?id=${tempBook.id}">Edit</a> 
                                            <a href="DeleteProductServlet?id=${tempBook.id}">Delete</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <c:if test="${empty SEARCH_RESULTS}">
                                <c:forEach var="tempBook" items="${BOOKS_LIST}">
                                    <tr>
                                        <td>${tempBook.name}</td>
                                        <td>${tempBook.category}</td>
                                        <td>${tempBook.price}</td>
                                        <td>${tempBook.image}</td>
                                        <td>
                                            <a href="editproduct.jsp?id=${tempBook.id}">Edit</a> 
                                            <a href="DeleteProductServlet?id=${tempBook.id}">Delete</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
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
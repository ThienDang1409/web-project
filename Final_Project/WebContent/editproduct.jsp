<%@page import="cn.techtutorial.connection.DbCon"%>
<%@page import="cn.techtutorial.dao.ProductDao"%>
<%@page import="cn.techtutorial.model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
//int id = Integer.parseInt(request.getParameter("id"));
int id = 14;
ProductDao pdao = new ProductDao(DbCon.getConnection());
Product product = pdao.getSingleProduct(id);
request.setAttribute("edit_books", product);
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

<title>Edit Product Data</title>
<style>
.inner {
	position: relative;
	margin: 0 auto;
	width: 650px;
	display: block;
	padding: 50px 0;
}

h3 {
	text-align: center;
	border-bottom: 2px solid midnightblue;
	margin-bottom: 20px;
}

nav li a {
	font-size: 25px;
	font-weight: 500;
}

a {
	color: #fff;
	text-decoration: none;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<nav class="navbar navbar-light">
			<a class="navbar-brand">Fruit Shop</a>
			<ul class="navbar-nav ml-auto mt-3 mx-5">
				<li class="nav-item"><a class="nav-link active"
					href="ProductManager.jsp">Home</a></li>
			</ul>
		</nav>
	</div>
	<div class="inner">
		<div class=" container">
			<div class="row">
				<div class="col-12">
					<h3>Edit Product Details</h3>
					<form action="EditBooksServlet" method="post">
						<div class="form-group">
							<label>Product ID</label> <input class="form-control" name="id"
								value="${edit_books.id }" required>
						</div>
						<div class="form-group">
							<label>Product Name</label> <input class="form-control"
								name="pname" value="${edit_books.name }" required>
						</div>
						<div class="form-group">
							<label>Category</label> <input class="form-control"
								name="pcat" value="${edit_books.category}" required>
						</div>
						<div class="form-group">
							<label>Price</label> <input class="form-control"
								name="price" value="${edit_books.price}" required>
						</div>
						<div class="form-group">

							<label>Image</label> <input class="form-control"
								name="image" value="${edit_books.price}" required>
						</div>
						<button type="submit" class="btn btn-primary">Submit</button>
						<a href="ProductManager.jsp" class="btn btn-primary">Cancel</a>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

</body>
</html>
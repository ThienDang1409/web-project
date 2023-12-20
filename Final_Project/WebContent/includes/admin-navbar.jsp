<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="container">
		<a class="navbar-brand" href="index.jsp">Fruit Store</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
	
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link" href="adminindex.jsp">Home</a></li>
				<%
				if (auth != null) {%>
					<li class="nav-item"><a class="nav-link" href="ProductManager.jsp">Product Manager</a></li>
					<li class="nav-item"><a class="nav-link" href="admin-orders.jsp">Orders Manager</a></li>
					<li class="nav-item"><a class="nav-link" href="UserManager.jsp">User Manager</a></li>
					<li class="nav-item"><a class="nav-link" href="log-out">Logout</a></li>
				<%
				} else {
				%>
					<li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
				<%
				}
				%>
			</ul>
		</div>
	</div>
</nav>
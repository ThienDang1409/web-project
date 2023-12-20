<%@page import="cn.techtutorial.connection.DbCon"%>
<%@page import="cn.techtutorial.dao.ProductDao"%>
<%@page import="cn.techtutorial.dao.CategoryDao"%>
<%@page import="cn.techtutorial.model.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%    
    
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
    request.setAttribute("person", auth);
}
ProductDao pd = new ProductDao(DbCon.getConnection());
List<Product> products = pd.getAllProducts();
ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
if (cart_list != null) {
	request.setAttribute("cart_list", cart_list);
}

CategoryDao categoryDao = new CategoryDao(DbCon.getConnection());
List<Category> categories = categoryDao.getAllCategories();
request.setAttribute("categories", categories);


%>

<%
String selectedCategory = request.getParameter("category");

if (selectedCategory != null && !selectedCategory.isEmpty() && selectedCategory != "All Products") {
    products = pd.getProductsByCategory(selectedCategory);
    request.setAttribute("selectedCategory", selectedCategory);
} else {
    products = pd.getAllProducts();
    request.setAttribute("selectedCategory", "All Products");
    selectedCategory = "All Products";
}
%>


<script>
    function showProductsByCategory(category) {
        window.location.href = 'your-products-page.jsp?category=' + encodeURIComponent(category);
    }
</script>


<!DOCTYPE html>
<html>
<head>
<%@include file="/includes/head.jsp"%>
<title>Fruit Store</title>
</head>
<body>
	<%@include file="/includes/navbar.jsp"%>
	
	<div class="container">
            <div class="card-header my-3">
                <label for="categorySelect">Select Category: </label>
                <select id="categorySelect" onchange="showProductsByCategory(this.value)">
                <option value="-1">All Products</option>
                <% 
                for (Category category : categories) {
                %>
                <option value="<%= category.getCategoryName() %>" 
                    <% if (category.getCategoryName().equals(selectedCategory)) { %>
                        selected
                    <% } %>
                ><%= category.getCategoryName() %></option>
                <%
                }
                %>
            </select>
            </div>
                <h3><%= selectedCategory %></h3>
		<div class="row">
			<%
			if (!products.isEmpty()) {
				for (Product p : products) {
			%>
			<div class="col-md-3 my-3">
				<div class="card w-100">
					<img class="card-img-top " width = 300 height = 200 src="product-image/<%=p.getImage() %>"
						alt="Card image cap">
					<div class="card-body">
						<h5 class="card-title"><%=p.getName() %></h5>
						<h6 class="price">Price: $<%=p.getPrice() %></h6>
						<h6 class="category">Category: <%=p.getCategory() %></h6>
						<div class="mt-3 d-flex justify-content-between">
							<a class="btn btn-dark" href="add-to-cart?id=<%=p.getId()%>">Add to Cart</a> <a
								class="btn btn-primary" href="order-now?quantity=1&id=<%=p.getId()%>">Buy Now</a>
						</div>
					</div>
				</div>
			</div>
			<%
			}
			} else {
			out.println("There is no products");
			}
			%>

		</div>
	</div>

                        
        <script>
        function showProductsByCategory(category) {
            if (category === "-1") {
                window.location.href = 'your-products-page.jsp';
            } else {
                window.location.href = 'your-products-page.jsp?category=' + encodeURIComponent(category);
            }
        }
        </script>
	<%@include file="/includes/footer.jsp"%>
</body>
</html>
package cn.techtutorial.dao;

import java.sql.*;
import java.util.*;

import cn.techtutorial.model.Cart;
import cn.techtutorial.model.Product;

public class ProductDao {
	private Connection con;

	private String query;
    private PreparedStatement pst;
    private ResultSet rs;
    

	public ProductDao(Connection con) {
		super();
		this.con = con;
	}
	
	public void deleteProduct(int id){
        try{
            
           String query= "delete from products where id=?";
           PreparedStatement pt = this.con.prepareStatement(query);
           pt.setInt(1, id);
           pt.execute();
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
	
	public boolean editProductInfo(Product product){
		boolean test = false;
		String query = "update products set name=?, category=?, price=?, image=? where id=?";
		try{
            PreparedStatement pt = this.con.prepareStatement(query);
            pt.setString(1, product.getName());  
            pt.setString(2, product.getCategory());
            pt.setDouble(3, product.getPrice());
            pt.setString(4, product.getImage());
            pt.setInt(5, product.getId());
            
            pt.executeUpdate();
            test= true;
        }catch(Exception ex){
            ex.printStackTrace();;
        }
		return test;
	}
	public boolean addProduct(Product product){
        boolean test = false;
        String query =  "insert into products (name,category,price,image) values(?,?,?,?)";
        try{
            PreparedStatement pst = this.con.prepareStatement(query);
            pst.setString(1, product.getName());
            pst.setString(2, product.getCategory());
            pst.setDouble(3, product.getPrice());
            pst.setString(4, product.getImage());
            pst.executeUpdate();
            test= true;

        }catch(Exception e){
            e.printStackTrace();
        }
        return test;
    }
	
	public List<Product> searchProducts(String searchTerm) {
	    List<Product> products = new ArrayList<>();
	    String query = "SELECT * FROM products WHERE LOWER(name) LIKE LOWER(?)";

	    try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
	        preparedStatement.setString(1, "%" + searchTerm + "%");

	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            while (resultSet.next()) {
	                int id = resultSet.getInt("id");
	                String name = resultSet.getString("name");
	                String category = resultSet.getString("category");
	                double price = resultSet.getDouble("price");
	                String image = resultSet.getString("image");

	                Product product = new Product(id, name, category, price, image);
	                products.add(product);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return products;
	}

	
	public List<Product> getAllProducts() {
        List<Product> book = new ArrayList<>();
        try {

            query = "select * from products";
            pst = this.con.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
            	Product row = new Product();
                row.setId(rs.getInt("id"));
                row.setName(rs.getString("name"));
                row.setCategory(rs.getString("category"));
                row.setPrice(rs.getDouble("price"));
                row.setImage(rs.getString("image"));

                book.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return book;
    }
	
	
	 public Product getSingleProduct(int id) {
		 Product row = null;
	        try {
	            query = "select * from products where id=? ";

	            pst = this.con.prepareStatement(query);
	            pst.setInt(1, id);
	            ResultSet rs = pst.executeQuery();

	            while (rs.next()) {
	            	row = new Product();
	                row.setId(rs.getInt("id"));
	                row.setName(rs.getString("name"));
	                row.setCategory(rs.getString("category"));
	                row.setPrice(rs.getDouble("price"));
	                row.setImage(rs.getString("image"));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println(e.getMessage());
	        }

	        return row;
	    }
	
	public double getTotalCartPrice(ArrayList<Cart> cartList) {
        double sum = 0;
        try {
            if (cartList.size() > 0) {
                for (Cart item : cartList) {
                    query = "select price from products where id=?";
                    pst = this.con.prepareStatement(query);
                    pst.setInt(1, item.getId());
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        sum+=rs.getDouble("price")*item.getQuantity();
                    }

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return sum;
    }

    
    public List<Cart> getCartProducts(ArrayList<Cart> cartList) {
        List<Cart> book = new ArrayList<>();
        try {
            if (cartList.size() > 0) {
                for (Cart item : cartList) {
                    query = "select * from products where id=?";
                    pst = this.con.prepareStatement(query);
                    pst.setInt(1, item.getId());
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        Cart row = new Cart();
                        row.setId(rs.getInt("id"));
                        row.setName(rs.getString("name"));
                        row.setCategory(rs.getString("category"));
                        row.setPrice(rs.getDouble("price")*item.getQuantity());
                        row.setQuantity(item.getQuantity());
                        book.add(row);
                    }

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return book;
    }
    public List<Product> getProductsByCategory(String category) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products WHERE category = ?";
        
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, category);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    double price = resultSet.getDouble("price");
                    String image = resultSet.getString("image");

                    Product product = new Product(id, name, category, price, image);
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }
}

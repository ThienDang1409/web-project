package cn.techtutorial.dao;

import java.sql.*;
import cn.techtutorial.model.*;

public class UserDao {
	private Connection con;

	private String query;
    private PreparedStatement pst;
    private ResultSet rs;

	public UserDao(Connection con) {
		this.con = con;
	}
	
	public User userLogin(String email, String password) {
		User user = null;
        try {
            query = "select * from users where email=? and password=?";
            pst = this.con.prepareStatement(query);
            pst.setString(1, email);
            pst.setString(2, password);
            rs = pst.executeQuery();
            if(rs.next()){
            	user = new User();
            	user.setId(rs.getInt("id"));
            	user.setName(rs.getString("name"));
            	user.setEmail(rs.getString("email"));
            	user.setRole(rs.getString("role"));
            }
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
        return user;
    }
	 public boolean registerUser(User newUser) {
	        boolean registrationSuccess = false;
	        try {
	            query = "INSERT INTO users (name, email, password, role) VALUES (?, ?, ?, ?)";
	            pst = this.con.prepareStatement(query);
	            pst.setString(1, newUser.getName());
	            pst.setString(2, newUser.getEmail());
	            pst.setString(3, newUser.getPassword());
	            pst.setString(4, newUser.getRole());

	            int rowsAffected = pst.executeUpdate();
	            registrationSuccess = rowsAffected > 0;
	        } catch (SQLException e) {
	            System.out.print(e.getMessage());
	        }
	        return registrationSuccess;
	    }
}

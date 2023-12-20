package cn.techtutorial.servlet;



import javax.servlet.RequestDispatcher;
import cn.techtutorial.dao.*;
import cn.techtutorial.model.*;
import cn.techtutorial.connection.DbCon;
import java.util.List;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
/**
 * Servlet implementation class SearchProductServlet
 */
@WebServlet("/SearchProductServlet")
public class SearchProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
	        String searchTerm = request.getParameter("search");
	    	ProductDao productDao = new ProductDao(DbCon.getConnection());
	        List<Product> searchResults;
	
	        if (searchTerm != null && !searchTerm.isEmpty()) {
	            // Thực hiện tìm kiếm và lưu kết quả vào attribute
	            searchResults = productDao.searchProducts(searchTerm);
	            request.setAttribute("SEARCH_RESULTS", searchResults);
	        } else {
	            // Nếu không có dữ liệu tìm kiếm, lấy toàn bộ sản phẩm
	        	// Trong một phương thức không tĩnh (non-static method)
	        	List<Product> products = productDao.getAllProducts();
	
	            request.setAttribute("BOOKS_LIST", products);
	        }
	
	        // Forward request về trang hiện tại (AddProductServlet) để hiển thị kết quả
	        RequestDispatcher dispatcher = request.getRequestDispatcher("ProductManager.jsp");
	        dispatcher.forward(request, response);
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.getWriter().println("An error occurred. Please check the server logs.");
	    }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

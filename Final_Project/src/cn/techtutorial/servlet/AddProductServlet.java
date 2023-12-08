package cn.techtutorial.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.techtutorial.model.Product;
import cn.techtutorial.dao.ProductDao;
import cn.techtutorial.connection.DbCon;


@WebServlet("/AddProductServlet")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html;charset=UTF-8");
    	try (PrintWriter out = response.getWriter()){
    		out.println("<!DOCTYPE html>");
    		out.println("<html>");
    		out.println("<head>");
    		out.println("<title>Add Product</title>");
    		out.println("</head>");
    		out.println("<body>");
    		String pname = request.getParameter("pname");
    		String pcat = request.getParameter("pcat");
    		Double price = Double.parseDouble(request.getParameter("price"));
    		String image = request.getParameter("image");
    		Product product = new Product(pname,pcat,price,image);
    		try{
                ProductDao pdao = new ProductDao(DbCon.getConnection());
                if(pdao.addProduct(product)){
                    response.sendRedirect("ProductManager.jsp");
                }else{
                    out.print("wrong cre3dential");
                }
                
            }catch(Exception e){
                e.printStackTrace();
            }
    		out.println("</body>");
    		out.println("</html>");
    		
    	}
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html;charset=UTF-8");
    	try (PrintWriter out = response.getWriter()){
    		out.println("<!DOCTYPE html>");
    		out.println("<html>");
    		out.println("<head>");
    		out.println("<title>Add Product</title>");
    		out.println("</head>");
    		out.println("<body>");
    		String pname = request.getParameter("pname");
    		String pcat = request.getParameter("pcat");
    		Double price = Double.parseDouble(request.getParameter("price"));
    		String image = request.getParameter("image");
    		Product product = new Product(pname,pcat,price,image);
    		try{
                ProductDao pdao = new ProductDao(DbCon.getConnection());
                if(pdao.addProduct(product)){
                    response.sendRedirect("editproduct.jsp");
                }else{
                    out.print("wrong cre3dential");
                }
                
            }catch(Exception e){
                e.printStackTrace();
            }
    		out.println("</body>");
    		out.println("</html>");
    		
    	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

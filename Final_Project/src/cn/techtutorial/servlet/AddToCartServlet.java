package cn.techtutorial.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.techtutorial.model.*;


@WebServlet(name = "AddToCartServlet", urlPatterns = "/add-to-cart")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("text/html;charset=UTF-8");

	    try (PrintWriter out = response.getWriter()) {
	        int id = Integer.parseInt(request.getParameter("id"));
	        HttpSession session = request.getSession();
	        ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cart-list");

	        if (cartList == null) {
	            // Nếu giỏ hàng chưa tồn tại, tạo mới và thêm sản phẩm vào
	            cartList = new ArrayList<>();
	            Cart cm = new Cart();
	            cm.setId(id);
	            cm.setQuantity(1);
	            cartList.add(cm);
	            session.setAttribute("cart-list", cartList);
	        } else {
	            // Nếu giỏ hàng đã tồn tại, kiểm tra sản phẩm
	            boolean productExists = false;

	            for (Cart c : cartList) {
	                if (c.getId() == id) {
	                    // Nếu sản phẩm đã tồn tại, tăng số lượng lên 1
	                    c.setQuantity(c.getQuantity() + 1);
	                    productExists = true;
	                    break;
	                }
	            }

	            if (!productExists) {
	                Cart cm = new Cart();
	                cm.setId(id);
	                cm.setQuantity(1);
	                cartList.add(cm);
	            }

	            session.setAttribute("cart-list", cartList);
	        }

	        response.sendRedirect("index.jsp");
	    }
	}
	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("text/html;charset=UTF-8");
//
//        try (PrintWriter out = response.getWriter()) {
////        	out.print("add to cart servlet");
//
//            ArrayList<Cart> cartList = new ArrayList<>();
//            int id = Integer.parseInt(request.getParameter("id"));
//            Cart cm = new Cart();
//            cm.setId(id);
//            cm.setQuantity(1);
//            HttpSession session = request.getSession();
//            ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
//            if (cart_list == null) {
//                cartList.add(cm);
//                session.setAttribute("cart-list", cartList);
//                response.sendRedirect("index.jsp");
//            } else {
//                cartList = cart_list;
//
//                boolean exist = false;
//                for (Cart c : cart_list) {
//                    if (c.getId() == id) {
//                        exist = true;
//                        out.println("<h3 style='color:crimson; text-align: center'>Item Already in Cart. <a href='cart.jsp'>GO to Cart Page</a></h3>");
//                    }
//                }
//
//                if (!exist) {
//                    cartList.add(cm);
//                    response.sendRedirect("index.jsp");
//                }
//            }
//        }
//	}

}

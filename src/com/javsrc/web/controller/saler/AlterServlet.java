package com.javsrc.web.controller.saler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javsrc.entity.Product;
//import com.javsrc.entity.common.Page;
import com.javsrc.service.ProductService;

/**
 * Servlet implementation class AlterServlet
 */
@WebServlet("/saler/alter")
public class AlterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer pid=Integer.parseInt(request.getParameter("id"));
		ProductService service = new ProductService();
		Product product = service.findOne(pid);
		request.setAttribute("product", product);
		request.getRequestDispatcher("/saler/product/alterOne.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

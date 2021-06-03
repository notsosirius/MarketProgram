package com.javsrc.web.controller.saler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javsrc.entity.Product;
import com.javsrc.entity.Saler;
import com.javsrc.entity.common.Page;
import com.javsrc.service.ProductService;

/**
 * 处理 根据 商品名 模糊查询商品的Servlet
 */
@WebServlet("/saler/delete/search")
public class DeleteSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//
		String keyword = request.getParameter("keyword");
		
		int number = 1;
		int size = 10;
		String numberStr = request.getParameter("number");
		if(numberStr != null && !"".equals(numberStr)){
			number = Integer.parseInt(numberStr);
		}
		if(number < 1){
			number = 1;
		}
		
		//
		ProductService service = new ProductService();
		Saler curr_mbr = (Saler)request.getSession().getAttribute("curr_mbr");
		request.getSession().setAttribute("curr_mbr", curr_mbr);
		int category=curr_mbr.getCategory();
		Page<Product> page = service.salerFindByLikeName(keyword, category, number, size);
		
		//
		request.setAttribute("page", page);
		request.getRequestDispatcher("/saler/product/delete.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

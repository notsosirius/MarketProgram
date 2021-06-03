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
 * 会员中心-->商品列表
 */
@WebServlet("/saler/products")
public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 2964566478709855605L;
	private ProductService productService = new ProductService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int number = 1;
		int size = 10;
		String n = req.getParameter("number");
		if(n != null && !"".equals(n)){
			number = Integer.parseInt(n);
		}
		if(number < 1){
			number = 1;
		}
		String s = req.getParameter("size");
		if(s != null && !"".equals(s)){
			size = Integer.parseInt(s);
		}
		if(size <= 0){
			size = 10;
		}
		
		Saler curr_mbr = (Saler)req.getSession().getAttribute("curr_mbr");
		req.getSession().setAttribute("curr_mbr", curr_mbr);
		int category=curr_mbr.getCategory();
		
		Page<Product> page = productService.findAllByCate(number, size, category);
		req.setAttribute("page", page);
		req.getRequestDispatcher("/saler/index.jsp").forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}

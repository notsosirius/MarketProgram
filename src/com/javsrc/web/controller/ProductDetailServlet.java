package com.javsrc.web.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javsrc.entity.Category;
import com.javsrc.entity.Member;
import com.javsrc.entity.Product;
import com.javsrc.entity.SessionInfo;
import com.javsrc.entity.common.Page;
import com.javsrc.service.CategoryService;
import com.javsrc.service.ProductService;
import com.javsrc.service.SessionInfoService;

/**
 * 商品的详情展示
 */
@WebServlet("/product_detail")
public class ProductDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//
		String idStr = request.getParameter("id");
		Integer id = Integer.valueOf(idStr);
		
		//
		ProductService productService = new ProductService();
		Product product = productService.findOne(id);
		request.setAttribute("product", product);
		
		Member mbr = (Member)request.getSession().getAttribute("curr_mbr");
		SessionInfo sessionInfo=new SessionInfo();
		sessionInfo.setUser_cate(0);
		if(mbr!=null) {
			sessionInfo.setEmail(mbr.getEmail());
			sessionInfo.setMobile(mbr.getMobile());
			sessionInfo.setUser_id(mbr.getId());
		}
		sessionInfo.setIp(request.getRemoteAddr());
		sessionInfo.setTime(new Date());
		sessionInfo.setGoods(product.getName());
		sessionInfo.setOperation("浏览");
		SessionInfoService service=new SessionInfoService();
    	service.save(sessionInfo);
		
		//查询商品所属的类目对象
		Integer cate_id = product.getCate_id();
		CategoryService categoryService = new CategoryService();
		Category cate = categoryService.findOne(cate_id);
		request.setAttribute("cate", cate);
		
		//当前商品所属类目下的热门商品列表
		Page<Product> hotsPage = productService.findBySubCategory(cate_id, 1, 3);
		request.setAttribute("hots", hotsPage.getItems());
		
		//
		request.getRequestDispatcher("/product_detail.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

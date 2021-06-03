package com.javsrc.web.controller.saler;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.javsrc.entity.Member;
//import com.javsrc.entity.Member;
import com.javsrc.entity.Orders;
import com.javsrc.entity.common.Page;
import com.javsrc.service.OrdersService;

/**
 * 会员中心-->订单列表
 * @author qiujy
 */
@WebServlet("/saler/sale/order")
public class OrderListServlet extends HttpServlet implements Serializable{
	private static final long serialVersionUID = 8396356046718027896L;
	
	private OrdersService ordersService = new OrdersService();


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
		
		//Member curr_mbr = (Member)req.getSession().getAttribute("curr_mbr");
		
		Page<Orders> page = ordersService.findAll(number, size);
		req.setAttribute("page", page);
		req.getRequestDispatcher("/saler/sale/order.jsp").forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
/*public class OrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 2964566478709855605L;
	
}*/

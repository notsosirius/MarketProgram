package com.javsrc.web.controller.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javsrc.service.OrdersService;

/**
 * Servlet implementation class AcceptServlet
 */
@WebServlet("/member/orders/accept")
public class AcceptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private OrdersService ordersService = new OrdersService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcceptServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer oid=Integer.parseInt(request.getParameter("id"));
		ordersService.updateStatus(oid,5);	
		request.getRequestDispatcher("/administrator/sale/order.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

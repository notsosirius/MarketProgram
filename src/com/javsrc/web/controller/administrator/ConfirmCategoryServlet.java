package com.javsrc.web.controller.administrator;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javsrc.service.OrdersService;
import com.javsrc.service.SalerService;

/**
 * Servlet implementation class ConfirmCategoryServlet
 */
@WebServlet("/ConfirmCategoryServlet")
public class ConfirmCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SalerService salerService = new SalerService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmCategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id=Integer.parseInt(request.getParameter("id"));
		String category=request.getParameter("category_id");
		Integer category_id=Integer.parseInt(category);
		//Integer category_id=Integer.parseInt(request.getParameter("category_id"));
		salerService.setCategory(category_id,id);	
		request.getRequestDispatcher("/administrator/manage/addSaler.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

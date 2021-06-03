package com.javsrc.web.controller.administrator;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javsrc.entity.Product;
import com.javsrc.entity.Saler;
import com.javsrc.service.ProductService;
import com.javsrc.service.SalerService;

/**
 * Servlet implementation class AlterSalerServlet
 */
@WebServlet("/AlterSalerServlet")
public class AlterSalerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlterSalerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer pid=Integer.parseInt(request.getParameter("id"));
		SalerService service = new SalerService();
		Saler saler = service.findOne(pid);
		request.setAttribute("product", saler);
		request.getRequestDispatcher("/administrator/manage/alterOneSaler.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

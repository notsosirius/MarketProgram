package com.javsrc.web.controller.administrator;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javsrc.entity.Saler;
import com.javsrc.entity.common.Page;
import com.javsrc.service.SalerService;

/**
 * Servlet implementation class AlterSalerSearchServlet
 */
@WebServlet("/AlterSalerSearchServlet")
public class AlterSalerSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlterSalerSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword = request.getParameter("salerkeyword");
		
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
		SalerService service = new SalerService();
		Page<Saler> page = service.findByLikeName(keyword, number, size);
		
		//
		request.setAttribute("page", page);
		request.getRequestDispatcher("/administrator/manage/alterSaler.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

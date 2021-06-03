package com.javsrc.web.controller.administrator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javsrc.entity.Category;
import com.javsrc.entity.Revenue;
import com.javsrc.entity.SessionInfo;
import com.javsrc.entity.common.Page;
import com.javsrc.service.CategoryService;
import com.javsrc.service.SessionInfoService;

/**
 * Servlet implementation class LoginoutServlet
 */
@WebServlet("/LoginoutServlet")
public class LoginoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SessionInfoService service = new SessionInfoService();
		List<SessionInfo> sessionList = service.findAllInout();
		Page<SessionInfo> page = new Page<>(1, 10);
		
		// 将类别列表传到界面，否则下拉框将没有类别
		/*
		 * if(cateList != null) { request.setAttribute("cateList", cateList); }else {
		 * //request.setAttribute("msg", "初始站站点为空");
		 * request.getSession().setAttribute("msg", "类别为空"); }
		 */
		
		String action=request.getParameter("action");
		//TicketService ticketService = new TicketServiceImpl();
		if("showall".equals(action)) {
			if(sessionList != null) {
				page.setItems(sessionList);
				request.setAttribute("page", page);
				//System.out.println(revenueList);
			}else {
				request.setAttribute("msg", "没有任何信息");
			}
		}
		String msg = (String) request.getSession().getAttribute("msg");
		request.setAttribute("msg", msg);
		request.getRequestDispatcher("/administrator/logs/loginout.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package com.javsrc.web.controller.saler;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp; 

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javsrc.service.CategoryService;
import com.javsrc.entity.Category;
import com.javsrc.entity.Revenue;
import com.javsrc.entity.Saler;
import com.javsrc.entity.common.Page;

/**
 * Servlet implementation class AdministratorReportFormServlet
 */
@WebServlet("/SalerReportFormServlet")
public class SalerReportFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalerReportFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategoryService cateService = new CategoryService();
		//List<Category> cateList = cateService.findAllSub();
		//Page<Revenue> page = new Page<>(1, 10);
		
		// 将类别列表传到界面，否则下拉框将没有类别
		/*
		 * if(cateList != null) { request.setAttribute("cateList", cateList); }else {
		 * //request.setAttribute("msg", "初始站站点为空");
		 * request.getSession().setAttribute("msg", "类别为空"); }
		 */
		
		String action=request.getParameter("action");
		Saler curr_mbr = (Saler)request.getSession().getAttribute("curr_mbr");
		request.getSession().setAttribute("curr_mbr", curr_mbr);
		int cateId=curr_mbr.getCategory();
		
		if("showall".equals(action)) {
			//List<Category> catelist = cateService.findAllSub();
			/*
			 * if(catelist != null) { for(Category cate:catelist) { //Double cateWhole =
			 * Double.parseDouble(cateService.getRevenueByName(cate.getName()));
			 * 
			 * revenueList.add(revenue); page.setItems(revenueList); }
			 * request.setAttribute("page", page); //System.out.println(revenueList); }else
			 * { request.setAttribute("msg", "没有任何营收信息"); }
			 */
			Double cateWhole = cateService.getRevenueById(cateId);
			String cateName = cateService.getNameById(cateId);
			Revenue revenue = new Revenue(cateId, cateName, cateWhole);
			request.setAttribute("revenue", revenue);
		}
		String msg = (String) request.getSession().getAttribute("msg");
		request.setAttribute("msg", msg);
		request.getRequestDispatcher("/saler/sale/report.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Saler curr_mbr = (Saler)request.getSession().getAttribute("curr_mbr");
		request.getSession().setAttribute("curr_mbr", curr_mbr);
		int cateId=curr_mbr.getCategory();
		//String cate = request.getParameter("category");
		//String endStation = request.getParameter("endStation");
		String startTimeString=request.getParameter("startTime");
		startTimeString=startTimeString.replace("T", " ");
		Timestamp startTime=Timestamp.valueOf(startTimeString);
		String endTimeString=request.getParameter("endTime");
		endTimeString=endTimeString.replace("T", " ");
		Timestamp endTime=Timestamp.valueOf(endTimeString);

		/*
		 * System.out.println(cate); System.out.println(startTime);
		 * System.out.println(endTime);
		 */
		
		CategoryService cateService = new CategoryService();
		/*
		 * List<Category> cateList = cateService.findAllSub(); Page<Revenue> page = new
		 * Page<>(1, 10);
		 */
		
		
		/*
		 * if(cateList != null) { request.setAttribute("cateList", cateList); }else {
		 * //request.setAttribute("msg", "初始站站点为空");
		 * request.getSession().setAttribute("msg", "类别为空"); }
		 */

		//List<Category> catelist = cateService.findAllSub();
		/*
		 * List<Revenue> revenueList=new ArrayList<Revenue>(); Double cateWhole =
		 * cateService.getRevenueByCondition(cate, startTime, endTime); //Long cateWhole
		 * = cateService.getRevenueByCondition(cate); Integer cateId =
		 * cateService.getIdByName(cate); Revenue revenue = new Revenue(cateId, cate,
		 * cateWhole); revenueList.add(revenue); page.setItems(revenueList);
		 */

		String cateName = cateService.getNameById(cateId);
		Double cateWhole = cateService.getRevenueByCondition(cateName, startTime, endTime);
		Revenue revenue = new Revenue(cateId, cateName, cateWhole);

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		
		if(!revenue.isEmpty()) {
			// 查到了，将结果转发到界面
			request.setAttribute("revenue", revenue);
			request.getRequestDispatcher("/saler/sale/report.jsp").forward(request, response);
		} else {
			if(startTimeString.length()==0||endTimeString.length()==0) {
				
				String path = "<script>alert('请输入查询日期！');"
					    + "window.location.href=ctx+'/SalerReportFormServlet?action=showall'</script>";
				
				out.write(path);
				
			}
			else {
			// 没有查到
			
			String path = "<script>alert('很抱歉，没有查询到相关营收信息！');"
				    + "window.location.href=ctx+'/SalerReportFormServlet?action=showall'</script>";
			
			out.write(path);
		}
		}
		out.close();
	}

}

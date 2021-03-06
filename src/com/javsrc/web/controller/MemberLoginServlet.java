package com.javsrc.web.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javsrc.entity.Address;
import com.javsrc.entity.Member;
import com.javsrc.entity.Orders;
import com.javsrc.entity.SessionInfo;
import com.javsrc.service.AddressService;
import com.javsrc.service.MemberService;
import com.javsrc.web.listener.SessionListener;

/**
 * 处理会员登录的Servlet
 */
@WebServlet("/member_login")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//step1： 获取客户端提交的数据
		String mobile = request.getParameter("mobile");
		String pwd = request.getParameter("pwd");
		
		//step2: 业务逻辑处理
		
		
		MemberService service = new MemberService();
		Member mbr = service.findByMobile(mobile);
		
		//step3: 执行跳转
		if(mbr != null){
			if(mbr.getPwd().equals(pwd)){
				//登录成功
				//在会话中记录当前登录的会员信息
				request.getSession().setAttribute("curr_mbr", mbr);
				request.getSession().setAttribute("curr_mbr_cate",0);//0为顾客，1为管理员，2为销售员
				SessionInfo sessionInfo=new SessionInfo();
				sessionInfo.setUser_cate(0);
				sessionInfo.setEmail(mbr.getEmail());
				sessionInfo.setMobile(mbr.getMobile());
				sessionInfo.setIp(request.getRemoteAddr());
				sessionInfo.setTime(new Date());
				sessionInfo.setOperation("登录");
				sessionInfo.setUser_id(mbr.getId());
				SessionListener sessionListener=new SessionListener(sessionInfo);  //对于每一个会话过程均启动一个监听器
				request.getSession().setAttribute("listener",sessionListener);  //将监听器植入HttpSession，这将激发监听器调用valueBound方法，从而记录日志文件。
				
				//如果登录后的会员，有提交订单，跳转到/orders.jsp; 没有就跳转到会员的首页
				Orders order = (Orders)request.getSession().getAttribute("curr_order");
				if(order != null){
					AddressService service2 = new AddressService();
					List<Address> addressList = service2.findByMember(mbr.getId());
					request.setAttribute("addressList", addressList);

					request.getRequestDispatcher("/orders.jsp").forward(request, response);;
				}else{
					response.sendRedirect(request.getContextPath() + "/member/orders");
				}
				
			}else{//密码有误
				request.setAttribute("msg", "密码不正确！");
				request.getRequestDispatcher("/member_login.jsp").forward(request, response);
			}
		}else{ //用户名不存在
			request.setAttribute("msg", "用户名不存在！");
			request.getRequestDispatcher("/member_login.jsp").forward(request, response);
		}
	}

}

package com.javsrc.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javsrc.entity.Administrator;


/**
 * 对会员的操作进行安全验证的过滤器
 * @author Administrator
 */
@WebFilter("/administrator/*")
public class AdSecurityFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		HttpSession session = request.getSession();
		
		Administrator mbr = (Administrator)session.getAttribute("curr_mbr");
		if(mbr != null){ //登录后的会员发起的请求，通过
			chain.doFilter(request, res);
		}else{ //未登录的会员发起的请求，要阻止
			
			session.setAttribute("msg", "管理员的相关操作，需要登录！");
			
			response.sendRedirect(request.getContextPath() + "/administrator_login.jsp");
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}

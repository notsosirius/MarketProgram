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
import com.javsrc.entity.Saler;

/**
 * Servlet Filter implementation class SalerSecurityFilter
 */
@WebFilter("/saler/*")
public class SalerSecurityFilter implements Filter {

    /**
     * Default constructor. 
     */
    public SalerSecurityFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		HttpSession session = request.getSession();
		
		Saler mbr = (Saler)session.getAttribute("curr_mbr");
		if(mbr != null){ //登录后的会员发起的请求，通过
			chain.doFilter(request, res);
		}else{ //未登录的会员发起的请求，要阻止
			
			session.setAttribute("msg", "销售员的相关操作，需要登录！");
			
			response.sendRedirect(request.getContextPath() + "/saler_login.jsp");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

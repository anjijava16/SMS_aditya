package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ValidateFilter implements Filter {

	private ServletContext servletContext;

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	System.out.println("filter executed..");
		
	}
	
	public void destroy() {
		// TODO Auto-generated method stub

	}
       
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain filter) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest)req	;
		HttpServletResponse response = (HttpServletResponse)resp;
		HttpSession session = ((HttpServletRequest)request).getSession();
		
		String url = request.getRequestURL().toString();
		//http://localhost:8080/SMSApplication/sms/logout
		String username= (String)session.getAttribute("username");
		
		if(null == username || "".equals(username)){
			servletContext.getRequestDispatcher("/login").forward(request, resp);
		}else{
			filter.doFilter(request, resp);	
		}
		
	}
}

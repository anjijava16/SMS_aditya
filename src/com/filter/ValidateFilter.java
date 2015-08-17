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
	public void init(FilterConfig fg) throws ServletException {
	System.out.println("filter executed..");
	servletContext = fg.getServletContext();
		
	}
	
	public void destroy() {
		// TODO Auto-generated method stub

	}
       
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain filter) throws IOException, ServletException {
		try{
		HttpServletRequest request = (HttpServletRequest)req	;
		HttpServletResponse response = (HttpServletResponse)resp;
		HttpSession session = ((HttpServletRequest)request).getSession();
		
		String url = request.getRequestURL().toString();
		//http://localhost:8080/SMSApplication/sms/logout
		
		if(null !=url && url.indexOf("login") ==-1 && url.indexOf("home") == -1){
			
			String username= (String)session.getAttribute("username");
			if(null == username || "".equals(username)){
				servletContext.getRequestDispatcher("/login").forward(request, resp);
				return;
			}
			
		}
			filter.doFilter(request, resp);	
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

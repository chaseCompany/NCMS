package kr.co.chase.ncms.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AjaxSessionExpirationFilter implements Filter {
	private static final Logger LOGGER = LoggerFactory.getLogger(AjaxSessionExpirationFilter.class);
	private int customSessionExpiredErrorCode = 901;
	 
    @Override
	public void init(FilterConfig arg0) throws ServletException{
	    // Property check here
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filerChain) throws IOException, ServletException{
		
        String requestURI = ((HttpServletRequest)request).getRequestURI(); //요청 URI
        
        if(requestURI.toLowerCase().indexOf("admin") > 0){
        	HttpSession session = ((HttpServletRequest)request).getSession();
        	boolean LoginInfo = session.getAttribute(ConstantObject.LOGIN_ADMIN_SESSEION_INFO) == null ?false:true;
        	
        	if(!LoginInfo) {
        		LoginInfo = session.getAttribute(ConstantObject.LOGIN_USER_SESSEION_INFO) == null ?false:true;
        	}
        	
    	    if(LoginInfo == false){
    	        String ajaxHeader = ((HttpServletRequest) request).getHeader("X-Requested-With");
    	        if("XMLHttpRequest".equals(ajaxHeader))
    	        {
    	        	LOGGER.info("Ajax call detected, send {} error code", this.customSessionExpiredErrorCode);
    	            HttpServletResponse resp = (HttpServletResponse) response;
    	            resp.sendError(this.customSessionExpiredErrorCode);
    	        }else{
    	            // Redirect to login page
    	        	HttpServletResponse resp = (HttpServletResponse) response;
    	        	resp.sendRedirect(((HttpServletRequest) request).getContextPath() + "/adm00Login/login.do");
    	        }
    	    }else{
    	        // Redirect to login page
    	    	//HttpServletResponse resp = (HttpServletResponse) response;
    	    	//resp.sendRedirect(((HttpServletRequest) request).getContextPath() + "/adm00Login/login.do");
    	    	filerChain.doFilter(request, response);
    	    }        	
        } else {
        	filerChain.doFilter(request, response);
        }
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}

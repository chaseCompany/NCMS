package kr.co.chase.ncms.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CrossScriptingFilter implements Filter {
	public FilterConfig filterConfig;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	@Override
	public void destroy() {
		this.filterConfig = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		if(excludeUrl((HttpServletRequest) request)) {
			chain.doFilter(req, res); //걸러내는 URI일 경우 요청값 그대로 처리
		}
		else {
			chain.doFilter(new RequestWrapper(req), res);
		}
	}

	private boolean excludeUrl(HttpServletRequest request) {
		String uri = request.getRequestURI().toString().trim();
		String[] strExcludeUrl = {};

		for(int iIndex = 0; iIndex < strExcludeUrl.length; iIndex++) {
			if(uri.indexOf(strExcludeUrl[iIndex]) >= 0){
				return true;
			}
		}
		return false;
	}
}

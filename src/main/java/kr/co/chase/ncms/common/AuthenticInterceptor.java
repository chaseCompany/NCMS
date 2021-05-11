package kr.co.chase.ncms.common;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.co.chase.ncms.common.util.StringUtil;

/**
 * 로그인 인증 권한 체크
 * @author
 */
public class AuthenticInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String mainUrl = "/index.jsp";
		String requestURI = request.getRequestURI();	//요청 URI
		int requestPORT = request.getServerPort();		//요청 PORT
		String requestHOST = request.getServerName();

		boolean isPermittedURL = true;
		HttpSession session = request.getSession();

		
		
		if(requestURI.indexOf("/") == 0) {
			request.setAttribute("thisViewUrl", requestURI.substring(1));
		}else {
			request.setAttribute("thisViewUrl", requestURI);
		}

		if(requestURI.indexOf("login.do") < 0 && requestURI.indexOf("Login.do") < 0 ) {
			HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);
			ModelAndView modelAndView = new ModelAndView("redirect:" + mainUrl);
			if(usrInfo == null || StringUtil.nvl(usrInfo.get("USR_ID").toString(), "") == "") {
				
				if(requestURI.indexOf("ajax") < 0) {
					modelAndView.addObject("reDirect", requestURI);
				}

				throw new ModelAndViewDefiningException(modelAndView);
			}else {
				
				request.setAttribute("LoginUserId", StringUtil.nvl(usrInfo.get("USR_ID"), ""));
				request.setAttribute("LoginUserNm", StringUtil.nvl(usrInfo.get("USR_NM"), ""));
				request.setAttribute("LoginSiteCd", StringUtil.nvl(usrInfo.get("SITE_CD"), ""));
				request.setAttribute("LoginSiteNm", StringUtil.nvl(usrInfo.get("SITE_NM"), ""));
				request.setAttribute("LoginRoleCd", StringUtil.nvl(usrInfo.get("ROLE_CD"), ""));
				request.setAttribute("LoginSiteConsult", StringUtil.nvl(usrInfo.get("SITE_CONSULT"), ""));
				request.setAttribute("LoginSiteEdu", StringUtil.nvl(usrInfo.get("SITE_EDU"), ""));
				if(usrInfo != null) {
					if(StringUtil.nvl(usrInfo.get("SITE_CONSULT"), "").equals("1")) {
						if(!StringUtil.nvl(usrInfo.get("SITE_EDU"), "").equals("1")) {
							if(requestURI.indexOf("/nrds") >= 0) {
								modelAndView.setViewName("redirect:/counselMain.do");
								throw new ModelAndViewDefiningException(modelAndView);
							}
						}
						
						
					} else {
						if(StringUtil.nvl(usrInfo.get("SITE_EDU"), "").equals("1")) {
							if(requestURI.indexOf("/nrds") < 0 && requestURI.indexOf("/ajax") < 0) {
								modelAndView.setViewName("redirect:/nrds/recyclePrgMain.do");
								throw new ModelAndViewDefiningException(modelAndView);
							}
						}
					}
				}
			}
			
		}
		
		
	
		return isPermittedURL;
	}
}

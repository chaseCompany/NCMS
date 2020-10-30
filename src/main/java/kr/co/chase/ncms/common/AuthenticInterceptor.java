package kr.co.chase.ncms.common;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import egovframework.rte.fdl.property.EgovPropertyService;
import kr.co.chase.ncms.common.util.StringUtil;
import kr.co.chase.ncms.common.util.StringUtils;

/**
 * 로그인 인증 권한 체크
 * @author jhg
 *
 */
public class AuthenticInterceptor extends HandlerInterceptorAdapter{
//	@Resource(name = "ipControlMgrService")
//	private IpControlMgrService ipControlMgrService;
	@Resource(name = "propertiesService")
	private EgovPropertyService propertiesService;
//	@Resource(name = "visitLogMgrService")
//	private VisitLogMgrService visitLogMgrService;
	
	private String cmpnyCd = "1000";
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {	
		String requestURI = request.getRequestURI(); //요청 URI
		int requestPORT = request.getServerPort(); //요청 PORT
		String requestHOST = request.getServerName();
				
		boolean isPermittedURL = true;
/*
		writeVisitLog(request);
		HttpSession session = request.getSession();
		
		if(requestURI.startsWith("/admin") || requestURI.startsWith("/volleyAdmin")) {
			
			// 요청 URL 유효성 검사 (도박서울센터용)
			if(requestHOST.indexOf("sbirt.kcgp.or.kr") > -1) {
				if(requestPORT == 443 || requestPORT == 8581) {
					response.sendRedirect(request.getContextPath());
				}
			}
			// 요청 URL 유효성 검사 (도박서울센터용)	
			
			cmpnyCd = getCookie(request,"admCmpnyCd");
			if("".equals(cmpnyCd)) {
				if(requestURI.startsWith("/volleyAdmin")) {
					cmpnyCd = "1001";
				} else {
					cmpnyCd = "1000";
				}
			}
			
			AdminSessionInfo userInfoObj = (AdminSessionInfo) session.getAttribute(ConstantObject.LOGIN_ADMIN_SESSEION_INFO);
			if(userInfoObj != null) {
				cmpnyCd = userInfoObj.getCmpnyCd();
			}
			isPermittedURL = adminProc(requestURI, requestPORT, session);
			//System.out.println("2isPermittedURL=" + isPermittedURL);
			if (!isPermittedURL) {
				if(cmpnyCd.equals("1001")) {
					response.sendRedirect(request.getContextPath() + "/volleyAdmin/adm00Login/preLogin.do");
				} else {
					response.sendRedirect(request.getContextPath() + "/admin/adm00Login/preLogin.do");
				}				
			} else {				
				if(!requestURI.toLowerCase().endsWith("noaccesssite.do") && !isAdminIpAccess(cmpnyCd, StringUtils.getWAFRemoteAddr(request))) {
					if(cmpnyCd.equals("1001")) {
						response.sendRedirect(request.getContextPath() + "/volleyAdmin/common/noAccessSite.do");
					} else {
						response.sendRedirect(request.getContextPath() + "/admin/common/noAccessSite.do");
					}
					return false;
				}
			}
		} else if(requestURI.startsWith("/main") || requestURI.startsWith("/sp") || requestURI.startsWith("/st")) {
			
			// 요청 URL 유효성 검사 (도박서울센터용)
			if(requestHOST.indexOf("sbirt.kcgp.or.kr") > -1) {
				if(requestPORT == 8443 || requestPORT == 8583) {
					response.sendRedirect(request.getContextPath() + "/admin");
				}
			}
			// 요청 URL 유효성 검사 (도박서울센터용)	
			
			cmpnyCd = getCmpnyCd(request);
			isPermittedURL = userProc(requestURI, requestPORT, session);
			if (!isPermittedURL) {
				if(isAjaxRequest(request)) {
					response.sendError(400);
				} else {
					response.sendRedirect(request.getContextPath());
				}
			}
		} else if(requestURI.startsWith("/volley")) {
			cmpnyCd = getCmpnyCd(request);
			isPermittedURL = userProc(requestURI, requestPORT, session);
			if (!isPermittedURL) {
				if(isAjaxRequest(request)) {
					response.sendError(400);
				} else {
					response.sendRedirect(request.getContextPath() + "/volley/user/usr00Login/login.do");
				}
			}
		} else {
			// 요청 URL 유효성 검사 (도박서울센터용)
			if(requestHOST.indexOf("sbirt.kcgp.or.kr") > -1) {
				if(requestPORT == 8443 || requestPORT == 8583) {
					response.sendRedirect(request.getContextPath() + "/admin");
				}
			}
			// 요청 URL 유효성 검사 (도박서울센터용)	
			isPermittedURL = true;
		}
		
		//System.out.println("isPermittedURL = " + isPermittedURL + " , " + requestURI);
*/
		return isPermittedURL;
	}
		
	private boolean adminProc(String requestURI, int requestPORT, HttpSession session) throws Exception {
		
		if(session.getAttribute(ConstantObject.LOGIN_ADMIN_SESSEION_INFO) != null) {
			return true;
		}
		
		//관리자단 로그인 무시 URL
	    if(requestURI.toLowerCase().endsWith("login.do") ||
	       requestURI.toLowerCase().endsWith("logout.do") ||
	       requestURI.toLowerCase().endsWith("loginproc.do") ||
	       requestURI.toLowerCase().endsWith("/admin") ||
	       requestURI.toLowerCase().endsWith("/volley/admin") ||
	       requestURI.toLowerCase().endsWith("loginchk.do") ||
	       requestURI.toLowerCase().endsWith("file_uploader_html.do") ||
	       requestURI.toLowerCase().endsWith("noaccesssite.do") ||
	       requestURI.toLowerCase().endsWith("file_uploader_html_naver.do") ||
	       requestURI.toLowerCase().endsWith("file_uploader_html5_naver.ajax")) {
	    	return true;
	    }
		
		return false;
	}
	
	private boolean userProc(String requestURI, int requestPORT, HttpSession session) throws Exception {
		
		if( cmpnyCd.equals("1000") && session.getAttribute(ConstantObject.LOGIN_USER_SESSEION_INFO) != null ) {
			return true;
		}

	    //사용자단 Session Check할 URL
	    if(requestURI.toLowerCase().endsWith("login.do")) {
	    	return true;
	    } else if(requestURI.contains("Form")) {
	    	return false;
	    } else if(requestURI.contains("bsBoardWrite")) {	
	    	return true;
	    } else if(requestURI.contains("View")) {
	    	return true;
	    } else if(requestURI.contains("boardModify")) {
	    	return false;
	    } else if(requestURI.contains("usr03Mypage")) {
	    	return false;
	    } else if(requestURI.contains("usr13Issue")) {
	    	return false;
	    } else if(requestURI.contains("usr11App")) {
	    	return false;
	    }
	    
	    
		return true;
	}
	
	private boolean isAdminIpAccess(String cmpnyCd, String remoteIp) throws Exception {
		boolean isPermitted = true;//false;
		
		List<?> accessIpList = null;//ipControlMgrService.getSystemAccessIpList(cmpnyCd, remoteIp);
		
		if(accessIpList.size() > 0) {
			isPermitted = true;
		}

		return isPermitted;
	}

	private void writeVisitLog(HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			String visitYn = (String)session.getAttribute("visitYn");
			
			if(!"Y".equals(visitYn)) {
				String remoteIp = StringUtils.getWAFRemoteAddr(request)==null?"":StringUtils.getWAFRemoteAddr(request);
				String browser = request.getHeader("User-Agent")==null?"":request.getHeader("User-Agent");
				String url = request.getRequestURI();
//				visitLogMgrService.insertVisitLog(remoteIp, browser, url);
			}
			session.setAttribute("visitYn", "Y");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private String getCmpnyCd(HttpServletRequest request) {
		String requestURI = request.getRequestURI(); //요청 URI
		String p_idNm = "";
		String ret = "";
		
		// 게시판의 경우 게시판고유ID인 p_idNm 으로 cmpnyCd 를 결정한다
		if(requestURI.startsWith("/user/usr11Board/")) {
			if(!StringUtil.isNull(request.getParameter("p_idNm"))) {
				p_idNm = request.getParameter("p_idNm");
				if(p_idNm.toLowerCase().startsWith("spt")) {
					ret = "1001";
				}else {
					ret = "1000";
				}
			}
		}else {
			if(requestURI.startsWith("/user") || requestURI.startsWith("/inicis")) {
				ret = "1000";
			}
			else if(requestURI.startsWith("/volley/user")) {
				ret = "1001";
			}
		}
		
		return ret;
	}
	
	private String getCookie(HttpServletRequest request, String cookie_name) {
		Cookie[] cookies = request.getCookies();
		String ret = "";
		if (cookies != null) {
			for (int iIndex = 0; iIndex < cookies.length; iIndex++) {
				if(cookies[iIndex].getName().equals(cookie_name)) {
					ret = cookies[iIndex].getValue().equals("sports") ? "1001" : "1000";
				}
			}
		}
		return ret;
	}
	
	private boolean isAjaxRequest(HttpServletRequest req) {
		if("XMLHttpRequest".equals(req.getHeader("x-requested-with"))) {
			return true;
		} else {
			return false;
		}
	}
}

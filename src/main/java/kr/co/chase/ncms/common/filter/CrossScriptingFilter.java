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
		String[] strExcludeUrl = {
			"/admin/adm04Popup/newPopupMgr/saveNewPopupInfo.do",           // 새창 팝업관리 저장한다.
			"/admin/adm04Popup/newPopupMgr/updateNewPopupInfo.do",         // 새창 팝업관리 수정한다.
			"/admin/adm04Popup/popupZoneMgr/savePopupZoneInfo.do",         // 팝업존관리 저장한다.
			"/admin/adm04Popup/popupZoneMgr/updatePopupZoneInfo.do",       // 팝업존관리 수정한다.
			"/admin/adm05Banner/mainBannerMgr/saveMainBannerInfo.do",      // 메인배너관리 저장한다.
			"/admin/adm05Banner/mainBannerMgr/updateMainBannerInfo.do",    // 메인배너관리 수정한다.
			"/admin/adm07Other/aforgzMgr/saveAforgzInfo.do",               // 산하연맹관리 저장한다.
			"/admin/adm07Other/aforgzMgr/updateAforgzInfo.do",             // 산하연맹관리 수정한다.
			"/admin/adm07Other/contgrpMgr/saveContgrpInfo.do",             // 관련단체관리 저장한다.
			"/admin/adm07Other/contgrpMgr/updateContgrpInfo.do",           // 관련단체관리 수정한다.
			"/admin/adm07Other/spnsMgr/saveSpnsInfo.do",                   // 후원사관리 저장한다.
			"/admin/adm07Other/spnsMgr/updateSpnsInfo.do",                 // 후원사관리 수정한다.
			"/admin/adm11Board/boardMgr/atthfile_uploader_html.ajax",      // 게시판 첨부파일 업로드
			"/admin/adm11Board/boardMgr/boardSaveGalleryMgr.ajax",         // 메뉴유형이 갤러리형인 정보를 저장한다.
			"/admin/adm11Board/boardMgr/boardSaveRpstMgr.ajax",            // 국가대표팀명단 정보를 저장한다.
			"/admin/adm11Board/boardMgr/file_uploader_html_naver.do",      // smartEditor 사진을 업로드한다.
			"/admin/adm13Setup/setupMgr/setupSaveMgr.ajax",                // 관리자 환경설정을 저장한다.
			"/user/usr10Crtf/usrCrtfIssuMgr/saveUsrCrtfIssuCrqfcInfo.ajax" // 심판/기술지도 자격증 저장한다.
		};
		
		for(int iIndex = 0; iIndex < strExcludeUrl.length; iIndex++) {
			if(uri.indexOf(strExcludeUrl[iIndex]) >= 0){
				return true;
			}
		}
		return false;
	}
}

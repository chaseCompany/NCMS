package kr.co.chase.nrds.eduMng.web;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.co.chase.ncms.common.ConstantObject;
import kr.co.chase.ncms.common.service.SysCodeService;
import kr.co.chase.ncms.common.util.FileManagerUtil;
import kr.co.chase.ncms.login.service.LoginService;
import kr.co.chase.nrds.eduMng.service.EduMngService;
import kr.co.chase.nrds.recyclePrg.service.RecyclePrgService;

@Controller
public class EduMngController {
	@Resource(name = "sysCodeService")
	private SysCodeService sysCodeService;
	
	@Resource(name = "loginService")
	private LoginService loginService;
	
	@Resource(name = "RecyclePrgService")
	private RecyclePrgService recyclePrgService;
	
	@Resource(name = "FileManagerUtil")
	private FileManagerUtil fileUtil;
	
	@Resource(name= "EduMngService")
	private EduMngService eduMngService;
	
	/**
	 * 교육관리 메인 페이지
	 * @param model
	 * @param cslRcpVO
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/nrds/eduMngMain.do")
	public String eduMngMain(ModelMap model, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		@SuppressWarnings("unchecked")
		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);

		if(usrInfo == null || StringUtils.defaultIfEmpty((String)usrInfo.get("USR_ID"), "") == "") {
			return "redirect:/login.do";
		}
		Set<String> keys= usrInfo.keySet();
		for(String key : keys) {
			System.out.println("로그인 key::"+key);
		}
		Enumeration<?> paramsName= request.getParameterNames();
		HashMap<String, Object> map = new HashMap<String, Object>();
		while (paramsName.hasMoreElements()) {
			String name =  (String) paramsName.nextElement();
			System.out.println("name: "+name +"   &&   parameter:" + request.getParameter(name));
			map.put(name, request.getParameter(name));
		} 
		
		HashMap<String, Object> codeListMap = new HashMap<String, Object>();
		codeListMap.put("useYn", ConstantObject.Y);

		codeListMap.put("grpCd", "C4500");				// 프로그램
		model.put("pgmCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C3800");				// 구분
		model.put("pgmTpCdList", sysCodeService.getSysCdList(codeListMap));

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("grpCd", "NOT");
		paramMap.put("roleCd", new String[]{"90"});
		model.put("sysMbrList", loginService.getSysUsrList(paramMap));
		model.put("edPrmList", recyclePrgService.selectEdPrmList(map));
		return "nrds/eduMng/eduMngMain";
	}
	
	/**
	 * 재활교육프로그램 목록 조회
	 * @param model
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/nrds/eduMng/ajaxGetEduMngPrgList.do")
	public String ajaxGetEduMngPrgList(ModelMap model, @RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		String currentPageNo = StringUtils.defaultString((String)reqMap.get("pageNo"), "1");
		String recordCountPerPage = StringUtils.defaultString((String)reqMap.get("perPage"), ConstantObject.defaultRowSize);

		PaginationInfo paginginfo = new PaginationInfo();
		if(currentPageNo == "" || recordCountPerPage == ""){
			paginginfo.setCurrentPageNo(1);
			paginginfo.setPageSize(Integer.parseInt(ConstantObject.defaultPageSize));
			paginginfo.setRecordCountPerPage(Integer.parseInt(ConstantObject.defaultRowSize));
		} else {
			paginginfo.setCurrentPageNo(Integer.valueOf(currentPageNo));
			paginginfo.setPageSize(Integer.parseInt(ConstantObject.defaultPageSize));
			paginginfo.setRecordCountPerPage(Integer.valueOf(recordCountPerPage));
		}

		String schStrDt = StringUtils.defaultIfEmpty((String)reqMap.get("schStrDt"), "");
		String schEndDt = StringUtils.defaultIfEmpty((String)reqMap.get("schEndDt"), "");
		System.out.println("재활교육프로그램 검색 파라미터 목록");
		List<Object> pgmEdCdList = new ArrayList<Object>();
		List<Object> pgmClassNmList = new ArrayList<Object>();
		List<Object> pgmClassSubList = new ArrayList<Object>();
		for(String key : reqMap.keySet()) {
			if(reqMap.get(key) != null) {
				System.out.println("key: "+key + " || value: "+reqMap.get(key));	
			}
			HashMap<String, Object> searchMap= new HashMap<String, Object>();
			searchMap.put(key, reqMap.get(key));
			if(key.contains("searchPgmEdCd")) {
				pgmEdCdList.add(reqMap.get(key));
			} else if(key.contains("searchPgmClassNmCd")) {
				pgmClassNmList.add(reqMap.get(key));
			} else if(key.contains("searchPgmClassSubCd")){
				pgmClassSubList.add(reqMap.get(key));
			}
			
		}
		reqMap.put("pgmEdCdList", pgmEdCdList);
		reqMap.put("pgmClassNmList", pgmClassNmList);
		reqMap.put("pgmClassSubList", pgmClassSubList);
		if(!"".equals(schStrDt) && !"".equals(schEndDt)) {
			//reqMap.put("schStrDt", schStrDt.replaceAll("-", ""));
			//reqMap.put("schEndDt", schEndDt.replaceAll("-", ""));

			//reqMap.put("currentPageNo", paginginfo.getCurrentPageNo());
			//reqMap.put("recordCountPerPage", paginginfo.getRecordCountPerPage());

			//int totalCount = weeklyPrgService.getGrpPgmListCount(reqMap);
			//paginginfo.setTotalRecordCount(totalCount);

			//model.put("totalCount", totalCount);
			//model.put("paginationInfo", paginginfo);

			//if(totalCount > 0) {
			//	List<HashMap<String, Object>> resultList = weeklyPrgService.getGrpPgmList(reqMap);
			//	model.put("prgList", resultList);
			//}
		}else{
			//model.put("totalCount", 0);
			//model.put("paginationInfo", paginginfo);
		}	
		List<EgovMap> resultList = recyclePrgService.selectEdPrmList(reqMap);
		model.put("prgList", resultList);
		return "nrds/eduMng/layer/eduMngList";
	}
	
	/**
	 * 프로그램 삭제
	 * @param pgmCd
	 * @param pgmDt
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/nrds/eduMng/ajaxEduMngModifi.do")
	public @ResponseBody ModelAndView ajaxEduMngModifi(final MultipartHttpServletRequest multiRequest, @RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView ("jsonView");

		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);

		if(usrInfo == null || StringUtils.defaultString((String)usrInfo.get("USR_ID"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "로그인 후 이용 가능 합니다.");
			resultView.addObject("actUrl", "/login.do");

			return resultView;
		}

		if("".equals(StringUtils.defaultIfEmpty((String)reqMap.get("pgmEdCd"), ""))) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "교육분류는 필수 입력 항목입니다.");
			return resultView;
		}
		if("".equals(StringUtils.defaultIfEmpty((String)reqMap.get("pgmClassNmCd"), ""))) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "교육과정명은 필수 입력 항목입니다.");
			return resultView;
		}
		if("".equals(StringUtils.defaultIfEmpty((String)reqMap.get("pgmStartDt"), ""))) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "교육기간 시작시간은 필수 입력 항목입니다.");
			return resultView;
		}
		if("".equals(StringUtils.defaultIfEmpty((String)reqMap.get("pgmEndDt"), ""))) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "교육기간 종료시간은 필수 입력 항목입니다.");
			return resultView;
		}
		if("".equals(StringUtils.defaultIfEmpty((String)reqMap.get("pgmClassStartDt"), ""))) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "교육일시 시작시간은 필수 입력 항목입니다.");
			return resultView;
		}
		if("".equals(StringUtils.defaultIfEmpty((String)reqMap.get("pgmClassEndDt"), ""))) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "교육일시 종료시간은 필수 입력 항목입니다.");
			return resultView;
		}
		if("".equals(StringUtils.defaultIfEmpty((String)reqMap.get("pgmMngUsrId"), ""))) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "담당자는 필수 입력 항목입니다.");
			return resultView;
		}
		if("".equals(StringUtils.defaultIfEmpty((String)reqMap.get("pgmSession"), ""))) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "회기는 필수 입력 항목입니다.");
			return resultView;
		}
		if("".equals(StringUtils.defaultIfEmpty((String)reqMap.get("pgmMainLec"), ""))) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "주강사는 필수 입력 항목입니다.");
			return resultView;
		}
		if("".equals(StringUtils.defaultIfEmpty((String)reqMap.get("pgmSubject"), ""))) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "교육주제는 필수 입력 항목입니다.");
			return resultView;
		}
	
		String updId = StringUtils.defaultString((String)usrInfo.get("USR_ID"), "");
		reqMap.put("updId", updId);
		
		int resMap = eduMngService.updateEduMngInfo(reqMap);
		if(resMap >= 1) {
			resultView.addObject("err", ConstantObject.N);
			resultView.addObject("MSG", "등록");
		}
		return resultView;
	}
	
	/**
	 * 프로그램 삭제
	 * @param pgmCd
	 * @param pgmDt
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/nrds/eduMng/ajaxEduMngDel.do")
	public @ResponseBody ModelAndView ajaxEduMngDel(@RequestParam String pgmId, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView ("jsonView");

		if("".equals(StringUtils.defaultIfEmpty(pgmId, ""))) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수 정보 누락");
			return resultView;
		}
	

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pgmId", pgmId);
		int result = eduMngService.deleteEduMngInfo(paramMap);
		if(result <= 0) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "삭제 실패");
		}

		return resultView;
	}
}

package kr.co.chase.nrds.recyclePrg.web;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.co.chase.ncms.adminUsr.web.AdminUsrController;
import kr.co.chase.ncms.common.ConstantObject;
import kr.co.chase.ncms.common.service.SysCodeService;
import kr.co.chase.ncms.common.util.FileManagerUtil;
import kr.co.chase.ncms.login.service.LoginService;
import kr.co.chase.nrds.recyclePrg.service.RecyclePrgService;

@Controller
public class RecyclePrgController {
	@Resource(name = "sysCodeService")
	private SysCodeService sysCodeService;
	
	@Resource(name = "loginService")
	private LoginService loginService;
	
	@Resource(name = "RecyclePrgService")
	private RecyclePrgService recyclePrgService;
	
	@Resource(name = "FileManagerUtil")
	private FileManagerUtil fileUtil;

	private Logger LOGGER = LoggerFactory.getLogger(AdminUsrController.class);

	/**
	 * 재활교육프로그램 메인 페이지
	 * @param model
	 * @param cslRcpVO
	 * @param session
	 * @return
	 * @throws Exception
	 */
	
	@RequestMapping(value="/nrds/recyclePrgMain.do")
	public String recyclePrgMain(ModelMap model, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
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
		return "nrds/recyclePrg/recyclePrgMain";
	}
	
	/**
	 * 재활교육프로그램 등록
	 * @param reqMap
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/nrds/ajaxRecyclePrgAdd.do")
	public @ResponseBody ModelAndView ajaxRecyclePrgAdd(final MultipartHttpServletRequest multiRequest, @RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
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


		String cslId = StringUtils.defaultString((String)usrInfo.get("USR_ID"), "");
		String pgmId = StringUtils.defaultIfEmpty((String)reqMap.get("pgmId"), "");
		String[] pgmMbrNoList = multiRequest.getParameterValues("pgmMbrNo");
		String[] pgmUserCntList = multiRequest.getParameterValues("pgmUserCnt");

//		reqMap.put("pgmId", pgmId);

		// 첨부 파일 정보
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		if (!files.isEmpty()) {
			boolean flag = fileUtil.checkFiles(files);

			if(flag) {
				HashMap<String, Object> fileMng = fileUtil.parseFileInf(files, "RECYCLE", cslId);
				if(!fileMng.isEmpty()) {
					List<HashMap<String, Object>> fileList = (List<HashMap<String, Object>>)fileMng.get("fileList");

					reqMap.put("fileList", fileList);
					reqMap.put("fileId", StringUtils.defaultIfEmpty((String)fileMng.get("fileId"), ""));
				}
			}
		}

		// 회원 목록
		if(pgmMbrNoList != null && pgmMbrNoList.length > 0) {
			List<HashMap<String, Object>> mbrList = new ArrayList<HashMap<String, Object>>();

			for(int i=0 ; i<pgmMbrNoList.length ; i++) {
				HashMap<String, Object> mbrMap = new HashMap<String, Object>();
				mbrMap.put("pgmId", pgmId);
				mbrMap.put("mbrNo", pgmMbrNoList[i]);
				mbrMap.put("pgmUserCnt", pgmUserCntList[i]);

				mbrList.add(mbrMap);
			}

			reqMap.put("grpPgmMbrList", mbrList);
		}
		
		HashMap<String, Object> resMap = recyclePrgService.processEdPrm(reqMap);
		if(resMap != null) {
			resultView.addObject("err", resMap.get("err"));
			resultView.addObject("MSG", resMap.get("MSG"));
		}

		return resultView;
		
	}
	
	/**
	 * 재활교육프로그램 목록 조회
	 * @param model
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxGetRecyclePrgList.do")
	public String ajaxGetRecyclePrgList(ModelMap model, @RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
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
		return "nrds/recyclePrg/layer/recyclePrgList";
	}
	
	/**
	 * 재활교육프로그램 상세 조회
	 * @param model
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/nrds/ajaxGetRecyclePrgInfo.do")
	@ResponseBody
	public ModelAndView ajaxGetRecyclePrgInfo(ModelMap model, @RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView ("jsonView");

		@SuppressWarnings("unchecked")
		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);

		if(usrInfo == null || StringUtils.defaultString((String)usrInfo.get("USR_ID"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "로그인 후 이용 가능 합니다.");
			resultView.addObject("actUrl", "/login.do");

			return resultView;
		}

		String pgmId = StringUtils.defaultIfEmpty((String)reqMap.get("pgmId"), "");

		if(!"".equals(pgmId) ) {
			resultView.addObject("err", ConstantObject.N);
			resultView.addObject("recyclePrgInfo", recyclePrgService.selectEdPrmInfo(reqMap));
		}else {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수 입력 누락입니다.");
		}

		return resultView;
	}

	/**
	 * 교육프로그램 삭제
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/nrds/ajaxGrpPgmDel.do")
	public @ResponseBody ModelAndView ajaxGrpPgmDel(@RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView("jsonView");

		if(StringUtils.defaultString(reqMap.get("pgmId").toString(), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");

			return resultView;
		}

		int resultNum = recyclePrgService.deleteEdPrmInfo(reqMap);
		if(resultNum <= 0) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "삭제 오류");
		}

		return resultView;
	}

	/**
	 * 프로그램 참여회원 목록
	 * @param model
	 * @param reqMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/nrds/getEdMemList.do")
	public String getPgmMemList(ModelMap model, @RequestParam HashMap<String, Object> reqMap) throws Exception{
		String pgmId = StringUtils.defaultIfEmpty((String)reqMap.get("pgmId"), "");

		if(!"".equals(pgmId)) {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("pgmId", pgmId);

			List<HashMap<String, Object>> resultList = recyclePrgService.getEdPgmMbrList(paramMap);
			model.put("pgmMemListCount", resultList.size());
			model.put("pgmMemList", resultList);
		}else {
			model.put("pgmMemListCount", "0");
		}

		return "nrds/recyclePrg/layer/recyclePrgMemList";
	}
	
	/**
	 * 재활교육 프로그램 엑셀다운로드
	 * @param modelMap
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/recycleExcelDownload.do")
	public String recycleExcelDownload(@RequestParam HashMap<String, Object> reqMap, Map<String, Object> modelMap, HttpServletResponse response, HttpServletRequest request) throws Exception {
		String title = "재활교육 프로그램";
		String pgmId = StringUtils.defaultIfEmpty((String)reqMap.get("pgmId"), "");
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Pragma", "public");
		response.setHeader("Expires", "0");
		response.setHeader("Content-Disposition", "attachment; filename = " + URLEncoder.encode(title, "UTF-8").replaceAll("\\+", "%20") + "_" + pgmId + ".xlsx");
		modelMap.put("sheetName", title);

		HashMap<String, Object> cslInfo = recyclePrgService.getEdPrm(reqMap);
		
		List<HashMap<String, Object>> mbrList = recyclePrgService.getEdPgmMbrList(reqMap);

		modelMap.put("cslInfo", cslInfo);
		modelMap.put("mbrList", mbrList);
		modelMap.put("imagesPath", request.getServletContext().getRealPath("/images/excel_logo.png"));

		return "RecycleExcel";
	}
}

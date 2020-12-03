package kr.co.chase.ncms.weeklyprg.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.co.chase.ncms.common.ConstantObject;
import kr.co.chase.ncms.common.service.SysCodeService;
import kr.co.chase.ncms.login.service.LoginService;
import kr.co.chase.ncms.vo.CslRcpVO;
import kr.co.chase.ncms.weeklyprg.service.WeeklyPrgService;

@Controller
public class WeeklyPrgController {
	@Resource(name="sysCodeService")
	private SysCodeService sysCodeService;

	@Resource(name="loginService")
	private LoginService loginService;

	@Resource(name="weeklyPrgService")
	private WeeklyPrgService weeklyPrgService;

	/**
	 * 주간 프로그램 메인 페이지
	 * @param model
	 * @param cslRcpVO
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/weeklyPrgMain.do")
	public String weeklyPrgMain(ModelMap model, @ModelAttribute("cslRcpVO") CslRcpVO cslRcpVO, HttpSession session) throws Exception{
		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);

		if(usrInfo == null || StringUtils.defaultIfEmpty((String)usrInfo.get("USR_ID"), "") == "") {
			return "redirect:/login.do";
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
		List<HashMap<String, Object>> sysUsrList = loginService.getSysUsrList(paramMap);

		model.put("sysMbrList", sysUsrList);

		if(sysUsrList != null) {
			String loginUsrId = StringUtils.defaultIfEmpty((String)usrInfo.get("USR_ID"), "");

			for(HashMap<String, Object> info : sysUsrList) {
				String infoUsrId = StringUtils.defaultIfEmpty((String)info.get("USR_ID"), "");

				if(loginUsrId.equals(infoUsrId)) {
					model.put("loginSiteNm", StringUtils.defaultIfEmpty((String)info.get("SITE_NM"), ""));
				}
			}
		}

		return "weeklyPrg/weeklyPrgMain";
	}

	/**
	 * 주간 프로그램 목록 조회
	 * @param model
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxGetWeeklyPrgList.do")
	public String ajaxGetWeeklyPrgList(ModelMap model, @RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
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

		if(!"".equals(schStrDt) && !"".equals(schEndDt)) {
			reqMap.put("schStrDt", schStrDt.replaceAll("-", ""));
			reqMap.put("schEndDt", schEndDt.replaceAll("-", ""));

			reqMap.put("currentPageNo", paginginfo.getCurrentPageNo());
			reqMap.put("recordCountPerPage", paginginfo.getRecordCountPerPage());

			int totalCount = weeklyPrgService.getGrpPgmListCount(reqMap);
			paginginfo.setTotalRecordCount(totalCount);

			model.put("totalCount", totalCount);
			model.put("paginationInfo", paginginfo);

			if(totalCount > 0) {
				List<HashMap<String, Object>> resultList = weeklyPrgService.getGrpPgmList(reqMap);
				model.put("prgList", resultList);
			}
		}else{
			model.put("totalCount", 0);
			model.put("paginationInfo", paginginfo);
		}

		return "weeklyPrg/layer/weeklyPrgList";
	}

	/**
	 * 주간 프로그램 상세 정보 조회
	 * @param model
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxGetWeeklyPrg.do")
	public @ResponseBody ModelAndView ajaxGetWeeklyPrg(ModelMap model, @RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView ("jsonView");

		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);

		if(usrInfo == null || StringUtils.defaultString((String)usrInfo.get("USR_ID"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "로그인 후 이용 가능 합니다.");
			resultView.addObject("actUrl", "/login.do");

			return resultView;
		}

		String pgmDt = StringUtils.defaultIfEmpty((String)reqMap.get("pgmDt"), "");
		String pgmCd = StringUtils.defaultIfEmpty((String)reqMap.get("pgmCd"), "");

		if(!"".equals(pgmDt) && !"".equals(pgmCd)) {
			resultView.addObject("err", ConstantObject.N);
			resultView.addObject("prgInfo", weeklyPrgService.getGrpPgm(reqMap));
		}else {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수 입력 누락입니다.");
		}

		return resultView;
	}

	/**
	 * 주간 프로그램 등록
	 * @param reqMap
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxWeeklyPrgAdd.do")
	public @ResponseBody ModelAndView ajaxWeeklyPrgAdd(@RequestParam HashMap<String, Object> reqMap, HttpServletRequest request, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView ("jsonView");

		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);

		if(usrInfo == null || StringUtils.defaultString((String)usrInfo.get("USR_ID"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "로그인 후 이용 가능 합니다.");
			resultView.addObject("actUrl", "/login.do");

			return resultView;
		}

		if("".equals(StringUtils.defaultIfEmpty((String)reqMap.get("pgmCd"), ""))) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "프로그램 분류는 필수 입력 항목입니다.");
			return resultView;
		}
		if("".equals(StringUtils.defaultIfEmpty((String)reqMap.get("pgmDt"), ""))) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "프로그램 실시 일자는 필수 입력 항목입니다.");
			return resultView;
		}
		if("".equals(StringUtils.defaultIfEmpty((String)reqMap.get("pgmFmTm"), ""))) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "프로그램 실시 시작시간은 필수 입력 항목입니다.");
			return resultView;
		}
		if("".equals(StringUtils.defaultIfEmpty((String)reqMap.get("pgmToTm"), ""))) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "프로그램 실시 종료시간은 필수 입력 항목입니다.");
			return resultView;
		}
		if("".equals(StringUtils.defaultIfEmpty((String)reqMap.get("pgmToTm"), ""))) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "프로그램 실시 종료시간은 필수 입력 항목입니다.");
			return resultView;
		}
		if("".equals(StringUtils.defaultIfEmpty((String)reqMap.get("mngUsrId"), ""))) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "담당자는 필수 입력 항목입니다.");
			return resultView;
		}
		if("".equals(StringUtils.defaultIfEmpty((String)reqMap.get("pgmCtnt"), ""))) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "프로그램 내용은 필수 입력 항목입니다.");
			return resultView;
		}

		String pgmDt = StringUtils.defaultIfEmpty((String)reqMap.get("pgmDt"), "").replaceAll("-", "");
		String pgmCd = StringUtils.defaultIfEmpty((String)reqMap.get("pgmCd"), "");
		String[] pgmMbrNoList = request.getParameterValues("pgmMbrNo");
		String[] mbrSeqNoList = request.getParameterValues("mbrSeqNo");
		String[] mbrCtntList = request.getParameterValues("mbrCtnt");

		reqMap.put("pgmDt", pgmDt);
		reqMap.put("cslId", StringUtils.defaultString((String)usrInfo.get("USR_ID"), ""));

		// 회원 목록
		if(pgmMbrNoList != null && pgmMbrNoList.length > 0) {
			List<HashMap<String, Object>> mbrList = new ArrayList<HashMap<String, Object>>();

			for(int i=0 ; i<pgmMbrNoList.length ; i++) {
				HashMap<String, Object> mbrMap = new HashMap<String, Object>();
				mbrMap.put("pgmDt", pgmDt);
				mbrMap.put("pgmCd", pgmCd);
				mbrMap.put("seqNo", mbrSeqNoList[i]);
				mbrMap.put("mbrNo", pgmMbrNoList[i]);
				mbrMap.put("mbrCtnt", mbrCtntList[i]);

				mbrList.add(mbrMap);
			}

			reqMap.put("grpPgmMbrList", mbrList);
		}

		HashMap<String, Object> resMap = weeklyPrgService.addWeeklyPrg(reqMap);
		if(resMap != null) {
			resultView.addObject("err", resMap.get("err"));
			resultView.addObject("MSG", resMap.get("MSG"));
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
	@RequestMapping(value="/getPgmMemList.do")
	public String getPgmMemList(ModelMap model, @RequestParam HashMap<String, Object> reqMap) throws Exception{
		String pgmDt = StringUtils.defaultIfEmpty((String)reqMap.get("pgmDt"), "");
		String pgmCd = StringUtils.defaultIfEmpty((String)reqMap.get("pgmCd"), "");

		if(!"".equals(pgmDt) && !"".equals(pgmCd)) {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("pgmDt", pgmDt);
			paramMap.put("pgmCd", pgmCd);

			List<HashMap<String, Object>> resultList = weeklyPrgService.getGrpPgmMbrList(paramMap);
			model.put("pgmMemListCount", resultList.size());
			model.put("pgmMemList", resultList);
		}else {
			model.put("pgmMemListCount", "0");
		}

		return "weeklyPrg/layer/weeklyPrgMemList";
	}

	/**
	 * 프로그램 삭제
	 * @param pgmCd
	 * @param pgmDt
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxGrpPgmDel.do")
	public @ResponseBody ModelAndView ajaxGrpPgmDel(@RequestParam String pgmCd, @RequestParam String pgmDt, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView ("jsonView");

		if("".equals(StringUtils.defaultIfEmpty(pgmCd, ""))) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수 정보 누락");
			return resultView;
		}
		if("".equals(StringUtils.defaultIfEmpty(pgmDt, ""))) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수 정보 누락");
			return resultView;
		}

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pgmCd", pgmCd);
		paramMap.put("pgmDt", pgmDt.replaceAll("-", ""));
		int result = weeklyPrgService.deleteWeeklyPrg(paramMap);
		if(result <= 0) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "삭제 실패");
		}

		return resultView;
	}
}
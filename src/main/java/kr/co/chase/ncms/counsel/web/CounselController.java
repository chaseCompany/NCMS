package kr.co.chase.ncms.counsel.web;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
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
import kr.co.chase.ncms.common.util.DateUtil;
import kr.co.chase.ncms.counsel.service.CounselService;
import kr.co.chase.ncms.vo.CslRcpVO;

@Controller
public class CounselController {
	@Resource(name="sysCodeService")
	private SysCodeService sysCodeService;

	@Resource(name="counselService")
	private CounselService counselService;

	/**
	 * 일반상담 등록화면
	 * @param model
	 * @param cslRcpVO
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/counselMain.do")
	public String counselMain(ModelMap model, @ModelAttribute("cslRcpVO") CslRcpVO cslRcpVO, HttpSession session) throws Exception{
		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);

		if(usrInfo == null || StringUtils.defaultString((String)usrInfo.get("USR_ID"), "") == "") {
			return "redirect:/login.do";
		}

		cslRcpVO.setCslId(StringUtils.defaultString((String)usrInfo.get("USR_ID"), ""));
		cslRcpVO.setCslNm(StringUtils.defaultString((String)usrInfo.get("USR_NM"), ""));
		cslRcpVO.setIfpGbCd("10");
		cslRcpVO.setIfpGendCd("M");
		cslRcpVO.setTgpFrgCd("LO");
		cslRcpVO.setTgpGendCd("M");
		model.put("cslRcpInfo", cslRcpVO);

		HashMap<String, Object> codeListMap = new HashMap<String, Object>();
		codeListMap.put("useYn", ConstantObject.Y);

		codeListMap.put("grpCd", "C1000");				// 정보제공자/본인여부
		model.put("ifpGbList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C1200");				// 직업
		model.put("jobList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C3400");				// 지역
		model.put("areaList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C1400");				// 정보취득경로
		model.put("pathList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C1500");				// 주호소문제
		model.put("kndList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C1600");				// 상담유형
		model.put("tpList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C1800");				// 최초사용약물
		model.put("fstDrugList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C1900");				// 주요사용약물
		model.put("mainDrugList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C2000");				// 주요조치
		model.put("mjrMngList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C2100");				// Rating A: 위험성
		model.put("rskaTpList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C2200");				// Rating B: 지지체계
		model.put("rskbTpList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C2300");				// Rating C: 협조능력
		model.put("rskcTpList", sysCodeService.getSysCdList(codeListMap));

		return "counsel/counselMain";
	}

	/**
	 * 일반상담 등록 처리
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxCounselAdd.do")
	public @ResponseBody ModelAndView ajaxCounselAdd(@RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView ("jsonView");

		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);

		if(usrInfo == null || StringUtils.defaultString((String)usrInfo.get("USR_ID"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "로그인 후 이용 가능 합니다.");
			resultView.addObject("actUrl", "/login.do");

			return resultView;
		}

		if(StringUtils.defaultString((String)reqMap.get("cslDt"), "") == "" || 
		   StringUtils.defaultString((String)reqMap.get("cslFmTm"), "") == "" || 
		   StringUtils.defaultString((String)reqMap.get("cslToTm"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "상담일시를 입력하세요.");
			return resultView;
		}
		if(StringUtils.defaultString((String)reqMap.get("ifpGbCd"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "정보제공자/본인여부를 선택하세요.");
			return resultView;
		}
		if(StringUtils.defaultString((String)reqMap.get("ifpNm"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "정보제공자 성명를 입력하세요.");
			return resultView;
		}
		if(StringUtils.defaultString((String)reqMap.get("ifpNm"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "정보제공자 성명를 입력하세요.");
			return resultView;
		}
		if(StringUtils.defaultString((String)reqMap.get("ifpGendCd"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "정보제공자 성별를 선택하세요.");
			return resultView;
		}
		if(StringUtils.defaultString((String)reqMap.get("ifpAge"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "정보제공자 연령를 입력하세요.");
			return resultView;
		}
		if(StringUtils.defaultString((String)reqMap.get("ifpTelNo1"), "") == "" ||
		   StringUtils.defaultString((String)reqMap.get("ifpTelNo2"), "") == "" ||
		   StringUtils.defaultString((String)reqMap.get("ifpTelNo3"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "정보제공자 연락처를 입력하세요.");
			return resultView;
		}
		if(StringUtils.defaultString((String)reqMap.get("ifpJobCd"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "정보제공자 직업를 선택하세요.");
			return resultView;
		}
		if(StringUtils.defaultString((String)reqMap.get("ifpAreaCd"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "정보제공자 지역를 선택하세요.");
			return resultView;
		}else if(StringUtils.defaultString((String)reqMap.get("ifpAreaCd"), "") == "ZZZ"){
			if(StringUtils.defaultString((String)reqMap.get("ifpAreaEtc"), "") == "") {
				resultView.addObject("err", ConstantObject.Y);
				resultView.addObject("MSG", "정보제공자 지역를 입력하세요.");
				return resultView;
			}
		}
		if(StringUtils.defaultString((String)reqMap.get("tgpNm"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "대상자 성명를 입력하세요.");
			return resultView;
		}
		if(StringUtils.defaultString((String)reqMap.get("tgpGendCd"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "대상자 성별를 선택하세요.");
			return resultView;
		}
		if(StringUtils.defaultString((String)reqMap.get("tgpAge"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "대상자 연령를 입력하세요.");
			return resultView;
		}
		if(StringUtils.defaultString((String)reqMap.get("tgpTelNo1"), "") == "" ||
		   StringUtils.defaultString((String)reqMap.get("tgpTelNo2"), "") == "" ||
		   StringUtils.defaultString((String)reqMap.get("tgpTelNo3"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "대상자 연락처를 입력하세요.");
			return resultView;
		}
		if(StringUtils.defaultString((String)reqMap.get("tgpJobCd"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "대상자 직업를 선택하세요.");
			return resultView;
		}
		if(StringUtils.defaultString((String)reqMap.get("tgpFrgCd"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "대상자 내/외국인 여부를 선택하세요.");
			return resultView;
		}
		if(StringUtils.defaultString((String)reqMap.get("tgpAreaCd"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "대상자 지역를 선택하세요.");
			return resultView;
		}else if(StringUtils.defaultString((String)reqMap.get("tgpAreaCd"), "") == "ZZZ"){
			if(StringUtils.defaultString((String)reqMap.get("tgpAreaEtc"), "") == "") {
				resultView.addObject("err", ConstantObject.Y);
				resultView.addObject("MSG", "대상자 지역를 입력하세요.");
				return resultView;
			}
		}
		if(StringUtils.defaultString((String)reqMap.get("ifPathCd"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "정보취득경로를 선택하세요.");
			return resultView;
		}
		if(StringUtils.defaultString((String)reqMap.get("pbmKndCd"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "주호소문제를 선택하세요.");
			return resultView;
		}
		if(StringUtils.defaultString((String)reqMap.get("cslTpCd"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "상담유형를 선택하세요.");
			return resultView;
		}
		if(StringUtils.defaultString((String)reqMap.get("fstDrugCd"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "최초사용약물을 선택하세요.");
			return resultView;
		}
		if(StringUtils.defaultString((String)reqMap.get("mainDrugCd"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "주요사용약물을 선택하세요.");
			return resultView;
		}
		if(StringUtils.defaultString((String)reqMap.get("mjrMngCd"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "주요조치를 선택하세요.");
			return resultView;
		}
		if(StringUtils.defaultString((String)reqMap.get("rskSco"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "위기분류척도 점수를 입력하세요.");
			return resultView;
		}
		if(StringUtils.defaultString((String)reqMap.get("cslCtnt"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "상담내용을 입력하세요.");
			return resultView;
		}


		reqMap.put("cslDt", ((String)reqMap.get("cslDt")).replaceAll("-", ""));
		reqMap.put("cslId", StringUtils.defaultString((String)usrInfo.get("USR_ID"), ""));
		reqMap.put("cslNm", StringUtils.defaultString((String)usrInfo.get("USR_NM"), ""));

		String rcpNo = StringUtils.defaultIfEmpty(counselService.counselAdd(reqMap), "");
		if("".equals(rcpNo)){
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "등록 오류");
		}else{
			resultView.addObject("rcpNo", rcpNo);
		}

		return resultView;
	}

	/**
	 * 일반상담 목록 조회
	 * @param model
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getClsRcpList.do")
	public String getClsRcpList(ModelMap model, @RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
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

		String schStrCslDt = StringUtils.defaultIfEmpty((String)reqMap.get("schStrCslDt"), "");
		String schEndCslDt = StringUtils.defaultIfEmpty((String)reqMap.get("schEndCslDt"), "");
		String schMth = StringUtils.defaultIfEmpty((String)reqMap.get("schMth"), "");
		String schGb = StringUtils.defaultIfEmpty((String)reqMap.get("schGb"), "");
		String schNm = StringUtils.defaultIfEmpty((String)reqMap.get("schNm"), "");
		if(!"".equals(schStrCslDt)) {
			reqMap.put("schStrCslDt", schStrCslDt.replaceAll("-", ""));
		}else{
			reqMap.put("schStrCslDt", DateUtil.getFormattedDateMonthAdd(DateUtil.getToday("yyyyMMdd"), "yyyyMMdd", "yyyyMMdd", -3));
		}
		if(!"".equals(schEndCslDt)) {
			reqMap.put("schEndCslDt", schEndCslDt.replaceAll("-", ""));
		}else{
			reqMap.put("schEndCslDt", DateUtil.getToday("yyyyMMdd"));
		}

		model.put("schStrCslDt", reqMap.get("schStrCslDt"));
		model.put("schEndCslDt", reqMap.get("schEndCslDt"));
		model.put("schMth", schMth);
		model.put("schGb", schGb);
		model.put("schNm", schNm);
		model.put("pageNo", currentPageNo);

		reqMap.put("currentPageNo", paginginfo.getCurrentPageNo());
		reqMap.put("recordCountPerPage", paginginfo.getRecordCountPerPage());

		int totalCount = counselService.getCslRcpListCount(reqMap);
		paginginfo.setTotalRecordCount(totalCount);

		model.put("totalCount", totalCount);
		model.put("paginationInfo", paginginfo);

		if(totalCount > 0) {
			List<HashMap<String, Object>> resultList = counselService.getCslRcpList(reqMap);
			model.put("resultList", resultList);
		}

		return "counsel/layer/rcpListLayer";
	}

	/**
	 * 상담내용 삭제
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxClsRcpDel.do")
	public @ResponseBody ModelAndView ajaxClsRcpDel(@RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView("jsonView");

		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);

		if(usrInfo == null || StringUtils.defaultString((String)usrInfo.get("USR_ID"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "로그인 후 이용 가능 합니다.");
			resultView.addObject("actUrl", "/login.do");

			return resultView;
		}

		if(StringUtils.defaultString((String)reqMap.get("rcpNo"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "상담번호를 입력하세요.");

			return resultView;
		}

		int result = counselService.deleteCslRcp(StringUtils.defaultString((String)reqMap.get("rcpNo"), ""));

		return resultView;
	}

	/**
	 * 상담 내용 상세 조회
	 * @param rcpNo
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxClsRcpInfo.do")
	public @ResponseBody ModelAndView ajaxClsRcpInfo(@RequestParam String rcpNo, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView("jsonView");
		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);

		if(usrInfo == null || StringUtils.defaultString((String)usrInfo.get("USR_ID"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "로그인 후 이용 가능 합니다.");
			resultView.addObject("actUrl", "/login.do");

			return resultView;
		}

		if(StringUtils.defaultString(rcpNo, "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "상담번호를 입력하세요.");

			return resultView;
		}

		resultView.addObject("cslRcpInfo", counselService.getCslRcp(rcpNo));

		return resultView;
	}

	/**
	 * 회원 목록 조회
	 * @param model
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxMstMbrList.do")
	public String ajaxMstMbrList(ModelMap model, @RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		String currentPageNo = StringUtils.defaultString((String)reqMap.get("pageNo"), "");
		String recordCountPerPage = StringUtils.defaultString((String)reqMap.get("perPage"), ConstantObject.defaultRowSize);

		PaginationInfo paginginfo = new PaginationInfo();
		if(currentPageNo == "" || recordCountPerPage == ""){
			paginginfo.setCurrentPageNo(1);
			paginginfo.setPageSize(Integer.parseInt(ConstantObject.defaultPageSize));
			paginginfo.setRecordCountPerPage(Integer.parseInt(ConstantObject.defaultRowSize));
		}else{
			paginginfo.setCurrentPageNo(Integer.valueOf(currentPageNo));
			paginginfo.setPageSize(Integer.parseInt(ConstantObject.defaultPageSize));
			paginginfo.setRecordCountPerPage(Integer.valueOf(recordCountPerPage));
		}

		String mbrNm = StringUtils.defaultIfEmpty((String)reqMap.get("mbrNm"), "");
		String telNo = StringUtils.defaultIfEmpty((String)reqMap.get("telNo"), "").replaceAll("-", "");
		String closeFlg = StringUtils.defaultIfEmpty((String)reqMap.get("closeFlg"), ConstantObject.Y);
		model.put("mbrNm", mbrNm);
		model.put("telNo", telNo);
		model.put("pageNo", currentPageNo);
		model.put("closeFlg", closeFlg);

		reqMap.put("currentPageNo", paginginfo.getCurrentPageNo());
		reqMap.put("recordCountPerPage", paginginfo.getRecordCountPerPage());

		int totalCount = counselService.getMstMbrListCount(reqMap);
		paginginfo.setTotalRecordCount(totalCount);

		model.put("totalCount", totalCount);
		model.put("paginationInfo", paginginfo);

		if(totalCount > 0) {
			List<HashMap<String, Object>> resultList = counselService.getMstMbrList(reqMap);
			model.put("resultList", resultList);
		}

		return "counsel/layer/mbrSearchLayer";
	}
}
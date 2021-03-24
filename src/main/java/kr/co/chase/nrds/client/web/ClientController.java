package kr.co.chase.nrds.client.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.co.chase.ncms.common.ConstantObject;
import kr.co.chase.ncms.common.service.SysCodeService;
import kr.co.chase.nrds.client.service.ClientService;

@Controller
@RequestMapping(value="/nrds")
public class ClientController {
	@Resource(name="sysCodeService")
	private SysCodeService sysCodeService;

	@Resource(name="clientService")
	private ClientService clientService;

	/**
	 * 대상자 정보 관리
	 * @param model
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/clientMain.do")
	public String clientMain(ModelMap model, @RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		HashMap<String, Object> codeListMap = new HashMap<String, Object>();
		codeListMap.put("useYn", ConstantObject.Y);

		codeListMap.put("grpCd", "C1100");				// 성별
		model.put("gendCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C1300");				// 내/외국인
		model.put("frgCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C2800");				// 결혼여부
		model.put("mrgCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C8000");				// 최종학력
		model.put("eduCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C7900");				// 학력결과
		model.put("edu02CdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C3000");				// 종교
		model.put("rlgnCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C1200");				// 직업
		model.put("jobCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C1800");				// 최초사용약물
		model.put("fstDrugList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C1900");				// 주요사용약물
		model.put("mainDrugList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C2600");				// 사용빈도
		model.put("useFrqList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C4100");				// 사용원인
		model.put("useCauList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C3200");				// 약물관련 법적문제
		model.put("lawPbmList", sysCodeService.getSysCdList(codeListMap));

		return "nrds/client/clientMain";
	}

	/**
	 * 회원 정보 저장
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxEdMbrAdd.do")
	public @ResponseBody ModelAndView ajaxEdMbrAdd(@RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView("jsonView");

		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);
		String loginUsrId = StringUtils.defaultString((String)usrInfo.get("USR_ID"), "");
		String mbrNm = StringUtils.defaultIfEmpty((String)reqMap.get("mbrNm"), "");
		String gendCd = StringUtils.defaultIfEmpty((String)reqMap.get("gendCd"), "");
		String frgCd = StringUtils.defaultIfEmpty((String)reqMap.get("frgCd"), "");
		String juminNo1 = StringUtils.defaultIfEmpty((String)reqMap.get("juminNo1"), "");
		String age = StringUtils.defaultIfEmpty((String)reqMap.get("age"), "");
		String telNo1 = StringUtils.defaultIfEmpty((String)reqMap.get("telNo1"), "");
		String telNo2 = StringUtils.defaultIfEmpty((String)reqMap.get("telNo2"), "");
		String telNo3 = StringUtils.defaultIfEmpty((String)reqMap.get("telNo3"), "");
		String zipCd = StringUtils.defaultIfEmpty((String)reqMap.get("zipCd"), "");
		String addr1 = StringUtils.defaultIfEmpty((String)reqMap.get("addr1"), "");
		String mbrTp1 = StringUtils.defaultIfEmpty((String)reqMap.get("mbrTp1"), "");
		String mbrTp2 = StringUtils.defaultIfEmpty((String)reqMap.get("mbrTp2"), "");
		String mbrTp3 = StringUtils.defaultIfEmpty((String)reqMap.get("mbrTp3"), "");
		String mbrTp4 = StringUtils.defaultIfEmpty((String)reqMap.get("mbrTp4"), "");
		String mbrTp5 = StringUtils.defaultIfEmpty((String)reqMap.get("mbrTp5"), "");
		String mbrTp6 = StringUtils.defaultIfEmpty((String)reqMap.get("mbrTp6"), "");
		String fstDrugCd = StringUtils.defaultIfEmpty((String)reqMap.get("fstDrugCd"), "");
		String mainDrugCd = StringUtils.defaultIfEmpty((String)reqMap.get("mainDrugCd"), "");
		String fstAge = StringUtils.defaultIfEmpty((String)reqMap.get("fstAge"), "");
		String lstAge = StringUtils.defaultIfEmpty((String)reqMap.get("lstAge"), "");
		String useTerm = StringUtils.defaultIfEmpty((String)reqMap.get("useTerm"), "");
		String useFrqCd = StringUtils.defaultIfEmpty((String)reqMap.get("useFrqCd"), "");
		String useCauCd = StringUtils.defaultIfEmpty((String)reqMap.get("useCauCd"), "");
		String lawPbmCd = StringUtils.defaultIfEmpty((String)reqMap.get("lawPbmCd"), "");
		String creUsrDt = StringUtils.defaultIfEmpty((String)reqMap.get("creUsrDt"), "");

		if("".equals(loginUsrId)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "로그인 후 이용 가능 합니다.");
			return resultView;
		}
		if("".equals(mbrNm)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "성명을 입력하세요.");
			return resultView;
		}
		if("".equals(gendCd)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "성별을 선택하세요.");
			return resultView;
		}
		if("".equals(frgCd)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "내/외국인 유무를 선택하세요.");
			return resultView;
		}
		if("".equals(juminNo1)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "생년월일을 입력하세요.");
			return resultView;
		}
		if("".equals(age)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "연령을 입력하세요.");
			return resultView;
		}
		if("".equals(telNo1) || "".equals(telNo2) || "".equals(telNo3)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "연락처를 입력하세요.");
			return resultView;
		}
		if("".equals(zipCd) || "".equals(addr1)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "주소를 입력하세요.");
			return resultView;
		}
		if("".equals(mbrTp1) && "".equals(mbrTp2) && "".equals(mbrTp3) && "".equals(mbrTp4) && "".equals(mbrTp5) && "".equals(mbrTp6)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "대상자구분을 선택하세요.");
			return resultView;
		}
		if("".equals(fstDrugCd)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "최초사용약물을 선택하세요.");
			return resultView;
		}
		if("".equals(mainDrugCd)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "주요사용약물을 선택하세요.");
			return resultView;
		}
		if("".equals(fstAge)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "최초 사용시기를 입력하세요.");
			return resultView;
		}
		if("".equals(lstAge)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "마지막 사용시기를 입력하세요.");
			return resultView;
		}
		if("".equals(useTerm)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "사용기간을 입력하세요.");
			return resultView;
		}
		if("".equals(useFrqCd)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "사용빈도를 선택하세요.");
			return resultView;
		}
		if("".equals(useCauCd)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "사용원인을 선택하세요.");
			return resultView;
		}
		if("".equals(lawPbmCd)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "약물관련 법적문제를 선택하세요.");
			return resultView;
		}
		if("".equals(creUsrDt)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "최초등록일자을 입력하세요.");
			return resultView;
		}

		reqMap.put("loginId", loginUsrId);
		reqMap.put("creUsrDt", creUsrDt.replaceAll("-", ""));

		HashMap<String, Object> resultMap = clientService.saveEdMbr(reqMap);
		if(resultMap != null) {
			resultView.addObject("err", resultMap.get("err"));
			resultView.addObject("MSG", resultMap.get("MSG"));
			resultView.addObject("mbrNo", resultMap.get("mbrNo"));
		}

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
	@RequestMapping(value="/ajaxEdMbrList.do")
	public String ajaxEdMbrList(ModelMap model, @RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);
		String searchType = StringUtils.defaultIfEmpty((String)reqMap.get("searchType"), "");
		String currentPageNo = StringUtils.defaultIfEmpty((String)reqMap.get("pageNo"), "");
		String recordCountPerPage = StringUtils.defaultIfEmpty((String)reqMap.get("perPage"), ConstantObject.defaultRowSize);

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
		model.put("mbrNm", mbrNm);
		model.put("telNo", telNo);
		model.put("pageNo", currentPageNo);

		reqMap.put("currentPageNo", paginginfo.getCurrentPageNo());
		reqMap.put("recordCountPerPage", paginginfo.getRecordCountPerPage());

		// 관리자가 아닌 경우
		if(!ConstantObject.adminRoleCd.equals(StringUtils.defaultIfEmpty((String)usrInfo.get("ROLE_CD"), ""))) {
			if("S".equals(searchType)) {
				reqMap.put("searchSiteCd", StringUtils.defaultIfEmpty((String)usrInfo.get("SITE_CD"), "X"));
			}
		}

		int totalCount = clientService.getEdMbrListCount(reqMap);
		paginginfo.setTotalRecordCount(totalCount);

		model.put("totalCount", totalCount);
		model.put("paginationInfo", paginginfo);

		if(totalCount > 0) {
			List<HashMap<String, Object>> resultList = clientService.getEdMbrList(reqMap);
			model.put("resultList", resultList);
		}

		return "nrds/client/layer/mbrSearchLayer";
	}

	/**
	 * 회원 정보 상세 조회
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxMbrInfoJson.do")
	public @ResponseBody ModelAndView ajaxMbrInfoJson(@RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView("jsonView");
		String mbrNo = StringUtils.defaultIfEmpty((String)reqMap.get("mbrNo"), "");

		if(!"".equals(mbrNo)){
			resultView.addObject("mbrInfo", clientService.getEdMbrInfo(reqMap));
		}

		return resultView;
	}

	/**
	 * 회원 정보 삭제
	 * @param mbrNo
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxEdMbrDel.do")
	public @ResponseBody ModelAndView ajaxEdMbrDel(@RequestParam String mbrNo, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView("jsonView");

		int result = clientService.deleteEdMbr(mbrNo);
		if(result <= 0) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "삭제 처리 오류");
		}

		return resultView;
	}

	/**
	 * 의뢰 교육조건부 기소유예
	 * @param model
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/clientEduConMain.do")
	public String clientEduConMain(ModelMap model, @RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		HashMap<String, Object> codeListMap = new HashMap<String, Object>();
		codeListMap.put("useYn", ConstantObject.Y);

		codeListMap.put("grpCd", "C1100");				// 성별
		model.put("gendCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C1200");				// 직업
		model.put("jobCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "R0200");				// 범죄유형
		model.put("crimeTypeList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "R0300");				// 사용기간
		model.put("useTermList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "R0400");				// 교육의뢰경위
		model.put("reqDetailsList", sysCodeService.getSysCdList(codeListMap));

		return "nrds/client/clientEduConMain";
	}

	/**
	 * 의뢰 교육조건부 기소유예 타이틀 정보 조회
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="ajaxEdMbrEdLastInfoJson.do")
	public @ResponseBody ModelAndView ajaxEdMbrEdLastInfoJson(@RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView("jsonView");
		String mbrNo = StringUtils.defaultIfEmpty((String)reqMap.get("mbrNo"), "");

		if(!"".equals(mbrNo)){
			resultView.addObject("mbrEdInfo", clientService.getEdMbrEdLastInfo(mbrNo));
		}

		return resultView;
	}

	/**
	 * 의뢰 교육조건부 기소유예 상세
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxEdInfoJson.do")
	public @ResponseBody ModelAndView ajaxEdInfoJson(@RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView("jsonView");
		String mbrNo = StringUtils.defaultIfEmpty((String)reqMap.get("mbrNo"), "");
		String mbrEdId = StringUtils.defaultIfEmpty((String)reqMap.get("mbrEdId"), "");

		if(!"".equals(mbrNo)){
			if(!"".equals(mbrEdId)) {
				resultView.addObject("mbrEdInfo", clientService.getEdMbrEdInfo(reqMap));
			}else{
				resultView.addObject("mbrEdInfo", clientService.getEdMbrInfo(reqMap));
			}
		}

		return resultView;
	}

	/**
	 * 의뢰 교육조건부 기소유예 목록
	 * @param model
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxEdMbrEdList.do")
	public String ajaxEdMbrEdList(ModelMap model, @RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);
		String loginUsrId = StringUtils.defaultIfEmpty((String)usrInfo.get("USR_ID"), "");
		String mbrNo = StringUtils.defaultIfEmpty((String)reqMap.get("mbrNo"), "");

		if(!"".equals(loginUsrId) && !"".equals(mbrNo)) {
			model.put("resultList", clientService.getEdMbrEdList(reqMap));
		}

		return "nrds/client/layer/clientEduList";
	}

	/**
	 * 의뢰 교육조건부 기소유예 등록
	 * @param multiRequest
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxEmeAdd.do")
	public @ResponseBody ModelAndView ajaxMstMbrAdd(MultipartHttpServletRequest multiRequest, @RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView("jsonView");

		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);
		String loginUsrId = StringUtils.defaultString((String)usrInfo.get("USR_ID"), "");
		String mbrNo = StringUtils.defaultIfEmpty((String)reqMap.get("mbrNo"), "");
		String rZipCd = StringUtils.defaultIfEmpty((String)reqMap.get("rZipCd"), "");
		String rAddr1 = StringUtils.defaultIfEmpty((String)reqMap.get("rAddr1"), "");
		String reqDt = StringUtils.defaultIfEmpty((String)reqMap.get("reqDt"), "");
		String docNo = StringUtils.defaultIfEmpty((String)reqMap.get("docNo"), "");
		String crimeType01 = StringUtils.defaultIfEmpty((String)reqMap.get("crimeType01"), "");
		String crimeType02 = StringUtils.defaultIfEmpty((String)reqMap.get("crimeType02"), "");
		String crimeType03 = StringUtils.defaultIfEmpty((String)reqMap.get("crimeType03"), "");
		String crimeType04 = StringUtils.defaultIfEmpty((String)reqMap.get("crimeType04"), "");
		String drug1 = StringUtils.defaultIfEmpty((String)reqMap.get("drug1"), "");
		String drug2 = StringUtils.defaultIfEmpty((String)reqMap.get("drug2"), "");
		String drug3 = StringUtils.defaultIfEmpty((String)reqMap.get("drug3"), "");
		String drug4 = StringUtils.defaultIfEmpty((String)reqMap.get("drug4"), "");
		String useTerm = StringUtils.defaultIfEmpty((String)reqMap.get("useTerm"), "");
		String reqDetails01 = StringUtils.defaultIfEmpty((String)reqMap.get("reqDetails01"), "");
		String reqDetails02 = StringUtils.defaultIfEmpty((String)reqMap.get("reqDetails02"), "");
		String reqDetails03 = StringUtils.defaultIfEmpty((String)reqMap.get("reqDetails03"), "");
		String reqDetails04 = StringUtils.defaultIfEmpty((String)reqMap.get("reqDetails04"), "");
		String reqOrg = StringUtils.defaultIfEmpty((String)reqMap.get("reqOrg"), "");

		if("".equals(loginUsrId)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "로그인 후 이용");
			return resultView;
		}
		if("".equals(mbrNo)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "회원을 선택하세요.");
			return resultView;
		}
		if("".equals(rZipCd) || "".equals(rAddr1)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "실거주지를 입력하세요.");
			return resultView;
		}
		if("".equals(reqDt)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "의뢰일을 입력하세요.");
			return resultView;
		}
		if("".equals(docNo)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "문서번호를 입력하세요.");
			return resultView;
		}
		if("".equals(crimeType01) && "".equals(crimeType02) && "".equals(crimeType03) && "".equals(crimeType04)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "범죄유형을 선택하세요.");
			return resultView;
		}
		if("".equals(drug1) && "".equals(drug2) && "".equals(drug3) && "".equals(drug4)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "사용마약류를 선택하세요.");
			return resultView;
		}
		if("".equals(useTerm)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "사용기간을 선택하세요.");
			return resultView;
		}
		if("".equals(reqDetails01) && "".equals(reqDetails02) && "".equals(reqDetails03) && "".equals(reqDetails04)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "교육의뢰경위를 선택하세요.");
			return resultView;
		}
		if("".equals(reqOrg)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "의뢰처를 입력하세요.");
			return resultView;
		}

		// 첨부 파일 정보
		Map<String, MultipartFile> files = multiRequest.getFileMap();
		reqMap.put("loginId", loginUsrId);

		HashMap<String, Object> resultMap = clientService.saveEdMbrEd(files, reqMap);
		if(resultMap != null) {
			resultView.addObject("err", resultMap.get("err"));
			resultView.addObject("MSG", resultMap.get("MSG"));
		}

		return resultView;
	}

	/**
	 * 의뢰 교육조건부 기소유예 삭제
	 * @param mbrNo
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxEdMbrEdDel.do")
	public @ResponseBody ModelAndView ajaxEdMbrEdDel(@RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView("jsonView");
		String mbrNo = StringUtils.defaultIfEmpty((String)reqMap.get("mbrNo"), "");
		String mbrEdId = StringUtils.defaultIfEmpty((String)reqMap.get("mbrEdId"), "");
		int result = 0;

		if(!"".equals(mbrNo) && !"".equals(mbrEdId)) {
			result = clientService.deleteEdMbrEd(reqMap);
		}

		if(result <= 0) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "삭제 처리 오류");
		}

		return resultView;
	}

	/**
	 * 의뢰 선도조건부 기소유예
	 * @param model
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/clientLeadConMain.do")
	public String clientLeadConMain(ModelMap model, @RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		HashMap<String, Object> codeListMap = new HashMap<String, Object>();
		codeListMap.put("useYn", ConstantObject.Y);

		codeListMap.put("grpCd", "C1100");				// 성별
		model.put("gendCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C1200");				// 직업
		model.put("jobCdList", sysCodeService.getSysCdList(codeListMap));

		return "nrds/client/clientLeadConMain";
	}

	/**
	 * 의뢰 선도조건부 기소유에 등록
	 * @param multiRequest
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxEmgAdd.do")
	public @ResponseBody ModelAndView ajaxEmgAdd(MultipartHttpServletRequest multiRequest, @RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView("jsonView");

		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);
		String loginUsrId = StringUtils.defaultString((String)usrInfo.get("USR_ID"), "");
		String mbrNo = StringUtils.defaultIfEmpty((String)reqMap.get("mbrNo"), "");
		String reqDt = StringUtils.defaultIfEmpty((String)reqMap.get("reqDt"), "");
		String docNo = StringUtils.defaultIfEmpty((String)reqMap.get("docNo"), "");
		String reqOrg = StringUtils.defaultIfEmpty((String)reqMap.get("reqOrg"), "");

		if("".equals(loginUsrId)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "로그인 후 이용");
			return resultView;
		}
		if("".equals(mbrNo)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "회원을 선택하세요.");
			return resultView;
		}
		if("".equals(reqDt)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "의뢰일을 선택하세요.");
			return resultView;
		}
		if("".equals(docNo)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "문서번호를 입력하세요.");
			return resultView;
		}
		if("".equals(reqOrg)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "의뢰처를 입력하세요.");
			return resultView;
		}

		// 첨부 파일 정보
		Map<String, MultipartFile> files = multiRequest.getFileMap();
		reqMap.put("loginId", loginUsrId);

		HashMap<String, Object> resultMap = clientService.saveEdMbrGu(files, reqMap);
		if(resultMap != null) {
			resultView.addObject("err", resultMap.get("err"));
			resultView.addObject("MSG", resultMap.get("MSG"));
		}

		return resultView;
	}

	/**
	 * 의뢰 선도조건부 기소유예 타이틀 정보 조회
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxEdMbrGuLastInfoJson.do")
	public @ResponseBody ModelAndView ajaxEdMbrGuLastInfoJson(@RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView("jsonView");
		String mbrNo = StringUtils.defaultIfEmpty((String)reqMap.get("mbrNo"), "");

		if(!"".equals(mbrNo)){
			resultView.addObject("mbrEdInfo", clientService.getEdMbrGuLastInfo(mbrNo));
		}

		return resultView;
	}

	/**
	 * 의뢰 선도조건부 기소유예 상세
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxGuInfoJson.do")
	public @ResponseBody ModelAndView ajaxGuInfoJson(@RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView("jsonView");
		String mbrNo = StringUtils.defaultIfEmpty((String)reqMap.get("mbrNo"), "");
		String mbrGuId = StringUtils.defaultIfEmpty((String)reqMap.get("mbrGuId"), "");

		if(!"".equals(mbrNo)){
			if(!"".equals(mbrGuId)) {
				resultView.addObject("mbrEdInfo", clientService.getEdMbrGuInfo(reqMap));
			}else{
				resultView.addObject("mbrEdInfo", clientService.getEdMbrInfo(reqMap));
			}
		}

		return resultView;
	}

	/**
	 * 의뢰 선도조건부 기소유예 목록
	 * @param model
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxEdMbrGuList.do")
	public String ajaxEdMbrGuList(ModelMap model, @RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);
		String loginUsrId = StringUtils.defaultIfEmpty((String)usrInfo.get("USR_ID"), "");
		String mbrNo = StringUtils.defaultIfEmpty((String)reqMap.get("mbrNo"), "");

		if(!"".equals(loginUsrId) && !"".equals(mbrNo)) {
			model.put("resultList", clientService.getEdMbrGuList(reqMap));
		}

		return "nrds/client/layer/clientEduList";
	}

	/**
	 * 의뢰 선도조건부 기소유예 삭제
	 * @param mbrNo
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxEdMbrGuDel.do")
	public @ResponseBody ModelAndView ajaxEdMbrGuDel(@RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView("jsonView");
		String mbrNo = StringUtils.defaultIfEmpty((String)reqMap.get("mbrNo"), "");
		String mbrGuId = StringUtils.defaultIfEmpty((String)reqMap.get("mbrGuId"), "");
		int result = 0;

		if(!"".equals(mbrNo) && !"".equals(mbrGuId)) {
			result = clientService.deleteEdMbrGu(reqMap);
		}

		if(result <= 0) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "삭제 처리 오류");
		}

		return resultView;
	}

	/**
	 * 연계
	 * @param model
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/clientLinkMain.do")
	public String clientLinkMain(ModelMap model, @RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		HashMap<String, Object> codeListMap = new HashMap<String, Object>();
		codeListMap.put("useYn", ConstantObject.Y);

		codeListMap.put("grpCd", "C1100");				// 성별
		model.put("gendCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C1200");				// 직업
		model.put("jobCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "R0102");				// 교육의뢰경위
		model.put("reqDetailsList", sysCodeService.getSysCdList(codeListMap));

		return "nrds/client/clientLinkMain";
	}

	/**
	 * 연계 타이틀 정보 조회
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxEdMbrTrinsLastInfoJson.do")
	public @ResponseBody ModelAndView ajaxEdMbrTrinsLastInfoJson(@RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView("jsonView");
		String mbrNo = StringUtils.defaultIfEmpty((String)reqMap.get("mbrNo"), "");

		if(!"".equals(mbrNo)){
			resultView.addObject("mbrTransInfo", clientService.getEdMbrTransLastInfo(mbrNo));
		}

		return resultView;
	}

	/**
	 * 연계 상세정보 조회
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxTransInfoJson.do")
	public @ResponseBody ModelAndView ajaxTransInfoJson(@RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView("jsonView");
		String mbrNo = StringUtils.defaultIfEmpty((String)reqMap.get("mbrNo"), "");
		String mbrTransId = StringUtils.defaultIfEmpty((String)reqMap.get("mbrTransId"), "");

		if(!"".equals(mbrNo)){
			if(!"".equals(mbrTransId)) {
				resultView.addObject("mbrTransInfo", clientService.getEdMbrTransInfo(reqMap));
			}else{
				resultView.addObject("mbrTransInfo", clientService.getEdMbrInfo(reqMap));
			}
		}

		return resultView;
	}

	/**
	 * 연계 등록
	 * @param multiRequest
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxEmtAdd.do")
	public @ResponseBody ModelAndView ajaxEmtAdd(MultipartHttpServletRequest multiRequest, @RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView("jsonView");

		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);
		String loginUsrId = StringUtils.defaultString((String)usrInfo.get("USR_ID"), "");

		if("".equals(loginUsrId)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "로그인 후 이용");
			return resultView;
		}

		// 첨부 파일 정보
		Map<String, MultipartFile> files = multiRequest.getFileMap();
		reqMap.put("loginId", loginUsrId);
		reqMap.put("acceptOrg", StringUtils.defaultString((String)usrInfo.get("SITE_NM"), ""));
		reqMap.put("acceptMngr", StringUtils.defaultString((String)usrInfo.get("USR_NM"), ""));

		HashMap<String, Object> resultMap = clientService.saveEdMbrTrans(files, reqMap);
		if(resultMap != null) {
			resultView.addObject("err", resultMap.get("err"));
			resultView.addObject("MSG", resultMap.get("MSG"));
		}

		return resultView;
	}

	/**
	 * 연계 목록
	 * @param model
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxEmtList.do")
	public String ajaxEmtList(ModelMap model, @RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);
		String loginUsrId = StringUtils.defaultIfEmpty((String)usrInfo.get("USR_ID"), "");
		String mbrNo = StringUtils.defaultIfEmpty((String)reqMap.get("mbrNo"), "");

		if(!"".equals(loginUsrId) && !"".equals(mbrNo)) {
			model.put("resultList", clientService.getEdMbrTransList(reqMap));
		}

		return "nrds/client/layer/clientEduList";
	}

	/**
	 * 연계 정보 삭제
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxEmtDel.do")
	public @ResponseBody ModelAndView ajaxEmtDel(@RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView("jsonView");
		String mbrNo = StringUtils.defaultIfEmpty((String)reqMap.get("mbrNo"), "");
		String mbrTransId = StringUtils.defaultIfEmpty((String)reqMap.get("mbrTransId"), "");
		int result = 0;

		if(!"".equals(mbrNo) && !"".equals(mbrTransId)) {
			result = clientService.deleteEdMbrTrans(reqMap);
		}

		if(result <= 0) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "삭제 처리 오류");
		}

		return resultView;
	}
}
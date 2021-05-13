package kr.co.chase.ncms.member.web;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.co.chase.ncms.common.ConstantObject;
import kr.co.chase.ncms.common.service.FileInfoService;
import kr.co.chase.ncms.common.service.SysCodeService;
import kr.co.chase.ncms.common.util.DateUtil;
import kr.co.chase.ncms.common.util.FileManagerUtil;
import kr.co.chase.ncms.login.service.LoginService;
import kr.co.chase.ncms.member.service.MemberService;

@Controller
public class MemberController {
	@Resource(name="sysCodeService")
	private SysCodeService sysCodeService;

	@Resource(name="loginService")
	private LoginService loginService;

	@Resource(name="memberService")
	private MemberService memberService;

	@Resource(name = "FileManagerUtil")
	private FileManagerUtil fileUtil;

	@Resource(name="fileInfoService")
	private FileInfoService fileInfoService;

	/**
	 * 회원정보관리 메인
	 * @param model
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/memberMain.do")
	public String memberMain(ModelMap model, @RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		HashMap<String, Object> codeListMap = new HashMap<String, Object>();
		codeListMap.put("useYn", ConstantObject.Y);

		codeListMap.put("grpCd", "C3500");				// 전국지부
		model.put("spotList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C8600");				// 형태
		model.put("typeList", sysCodeService.getSysCdList(codeListMap));

		return "member/memberMain";
	}

	/**
	 * 회원정보관리 의뢰
	 * @param model
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/memberReq.do")
	public String memberReq(ModelMap model, @RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		HashMap<String, Object> codeListMap = new HashMap<String, Object>();
		codeListMap.put("useYn", ConstantObject.Y);

		codeListMap.put("grpCd", "C3500");				// 전국지부
		model.put("spotList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5010");				// 약물중독/알코올중독/도박중독/인터넷중독/기타중독
		model.put("evlItmSco01List", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5120");				// 정신과적 증상
		model.put("evlItmSco06List", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5110");				// 자해/자살위험/타해위험
		model.put("evlItmSco18List", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5130");				// 정신약물관리
		model.put("evlItmSco07List", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5140");				// 스트레스 상태
		model.put("evlItmSco08List", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5150");				// 신체질환
		model.put("evlItmSco09List", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5210");				// 가족관계
		model.put("evlItmSco10List", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5310");				// 주거/고용 및 교육가능성/법률적 문제
		model.put("evlItmSco12List", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C7700");				// 욕구
		model.put("evlItmSco14List", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5220");				// 사회적관계/회복환경 관계
		model.put("evlItmSco11List", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5320");				// 경제활동 지원
		model.put("evlItmSco13List", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C8400");				// 요청서비스
		model.put("reqServiceList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C8500");				// 연계상태
		model.put("linkStateList", sysCodeService.getSysCdList(codeListMap));

		return "member/memberReq";
	}

	/**
	 * 회원정보 조회
	 * @param mbrNo
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxMbrInfo.do")
	public String ajaxMbrInfo(ModelMap model, @RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		String viewType = StringUtils.defaultIfEmpty((String)reqMap.get("viewType"), "");
		String searchFlag = StringUtils.defaultIfEmpty((String)reqMap.get("searchFlag"), "");

		HashMap<String, Object> codeListMap = new HashMap<String, Object>();
		codeListMap.put("useYn", ConstantObject.Y);

		codeListMap.put("grpCd", "C1100");				// 성별
		model.put("gendCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C1300");				// 내/외국인
		model.put("frgCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C7800");				// 회원구분
		model.put("mbrTpCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C3900");				// 약물사용자와의 관계
		model.put("drgUseCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C3100");				// 의료보장
		model.put("medicCareCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C8000");				// 최종학력
		model.put("eduCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C7900");				// 학력결과
		model.put("edu02CdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C2800");				// 결혼여부
		model.put("mrgCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C2900");				// 상담요청경로
		model.put("reqPathCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C3000");				// 종교
		model.put("rlgnCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C1200");				// 직업
		model.put("jobCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C8100");				// 보호자 관계
		model.put("fmlyRelationList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C8200");				// 지지정도
		model.put("fmlyExpList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C8300");				// 보호자 동거여부
		model.put("fmlyTogetherList", sysCodeService.getSysCdList(codeListMap));

		if("".equals(viewType)) {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("grpCd", "NOT");
			paramMap.put("roleCd", new String[]{"90"});
			model.put("sysMbrList", loginService.getSysUsrList(paramMap));
		}

		String mbrNo = StringUtils.defaultIfEmpty((String)reqMap.get("mbrNo"), "");
		if(!"".equals(mbrNo)) {
			model.put("mbrInfo", memberService.getMstMbr(mbrNo));
		}

		model.put("viewType", viewType);
		model.put("searchFlag", searchFlag);

		return "member/layer/memInfo";
	}

	
	/**
	 * 회원정보 엑셀다운로드
	 * @param modelMap
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/memberExcelDownload.do")
	public String memberExcelDownload(@RequestParam HashMap<String, Object> reqMap, Map<String, Object> modelMap, HttpServletResponse response, HttpServletRequest request) throws Exception {
		String title = "회원정보관리 기록지";
		String mbrNo = StringUtils.defaultIfEmpty((String)reqMap.get("mbrNo"), "");

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Pragma", "public");
		response.setHeader("Expires", "0");
		response.setHeader("Content-Disposition", "attachment; filename = " + URLEncoder.encode(title, "UTF-8").replaceAll("\\+", "%20") + "_" + mbrNo + ".xlsx");
		modelMap.put("sheetName", title);

		HashMap<String, Object> mbrInfo = memberService.getMstMbr(mbrNo);
		List<HashMap<String, Object>> fmlyTreeFileList = (List<HashMap<String, Object>>) mbrInfo.get("fmlyTreeFileList");
		List<HashMap<String, Object>> personalInfoFileList = (List<HashMap<String, Object>>) mbrInfo.get("personalInfoFileList");
		modelMap.put("mbrInfo", mbrInfo);
		modelMap.put("fmlyTreeFileList", fmlyTreeFileList);
		modelMap.put("personalInfoFileList", personalInfoFileList);
		modelMap.put("imagesPath", request.getServletContext().getRealPath("/images/excel_logo.png"));

		return "MemberExcel";
	}
	
	/**
	 * 회원 퇴록 이력 조회
	 * @param model
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxMbrRegHistList.do")
	public String ajaxMbrRegHistList(ModelMap model, @RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		String mbrNo = StringUtils.defaultIfEmpty((String)reqMap.get("mbrNo"), "");
		if(!"".equals(mbrNo)) {
			model.put("mstRegHisList", memberService.getMstRegHisList(mbrNo));
		}

		return "member/layer/memRegHistList";
	}

	/**
	 * 회원 정보 저장
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxMstMbrAdd.do")
	public @ResponseBody ModelAndView ajaxMstMbrAdd(final MultipartHttpServletRequest multiRequest, @RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView("jsonView");

		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);

		String usrId = StringUtils.defaultString((String)usrInfo.get("USR_ID"), "");
		String mbrNm = StringUtils.defaultIfEmpty((String)reqMap.get("mbrNm"), "").trim();					// 성명
		String regDt = StringUtils.defaultIfEmpty((String)reqMap.get("regDt"), "").replaceAll("-", "");		// 최초등록일자
		if("".equals(mbrNm)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}
		if("".equals(StringUtils.defaultIfEmpty((String)reqMap.get("gendCd"), ""))) {						// 성별
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}
		if("".equals(StringUtils.defaultIfEmpty((String)reqMap.get("frgCd"), ""))) {						// 내/외국인
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}
		if("".equals(StringUtils.defaultIfEmpty((String)reqMap.get("juminNo1"), ""))) {						// 생년월일
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}
		if("".equals(StringUtils.defaultIfEmpty((String)reqMap.get("age"), ""))) {							// 연령
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}
		if("".equals(StringUtils.defaultIfEmpty((String)reqMap.get("telNo1"), ""))) {						// 연락처
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}
		if("".equals(StringUtils.defaultIfEmpty((String)reqMap.get("telNo2"), ""))) {						// 연락처
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}
		if("".equals(StringUtils.defaultIfEmpty((String)reqMap.get("telNo3"), ""))) {						// 연락처
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}
		if("".equals(StringUtils.defaultIfEmpty((String)reqMap.get("zipCd"), ""))) {						// 우편번호
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}
		if("".equals(StringUtils.defaultIfEmpty((String)reqMap.get("addr1"), ""))) {						// 주소
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}
		if("".equals(StringUtils.defaultIfEmpty((String)reqMap.get("mbrTpCd"), ""))) {						// 회원구분
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}
		if("".equals(StringUtils.defaultIfEmpty((String)reqMap.get("drgUseCd"), ""))) {						// 약물사용자와의 관계
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}
		if("".equals(StringUtils.defaultIfEmpty((String)reqMap.get("medicCareCd"), ""))) {					// 의료보장
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}
		if("".equals(StringUtils.defaultIfEmpty((String)reqMap.get("mngUsrId"), ""))) {						// 사례관리자
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}
		if("".equals(regDt)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}

		reqMap.put("mbrNm", mbrNm);
		reqMap.put("regDt", regDt);
		reqMap.put("creId", usrId);

		// 첨부 파일 정보
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		if (!files.isEmpty()) {
			boolean flag = fileUtil.checkFiles(files);

			if(flag) {
				HashMap<String, Object> fileMng = fileUtil.parseFileInf(files, "MBR", usrId);
				if(!fileMng.isEmpty()) {
					List<HashMap<String, Object>> fileList = (List<HashMap<String, Object>>)fileMng.get("fileList");
					Long newFileId = Long.parseLong(fileInfoService.getFileInfoId());

					if(fileList != null) {
						for(HashMap<String, Object> fileInfo : fileList) {
							fileInfo.put("fileId", Long.toString(newFileId));
							fileInfo.put("fileSeq", "1");
							reqMap.put((String)fileInfo.get("formName"), Long.toString(newFileId++));
						}
					}

					reqMap.put("fileList", fileList);
				}
			}
		}

		HashMap<String, Object> resultMap = memberService.saveMstMbr(reqMap);
		if(resultMap != null) {
			resultView.addObject("err", resultMap.get("err"));
			resultView.addObject("MSG", resultMap.get("MSG"));
			resultView.addObject("mbrNo", resultMap.get("mbrNo"));
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
	@RequestMapping(value="/ajaxMstMbrDel.do")
	public @ResponseBody ModelAndView ajaxMstMbrDel(@RequestParam String mbrNo, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView("jsonView");

		int result = memberService.mstMbrDel(mbrNo);
		if(result <= 0) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "탈퇴 처리 오류");
		}

		return resultView;
	}

	/**
	 * 회원 퇴록/재등록 처리
	 * @param mbrNo
	 * @param stsCd
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxMstMbrStsCdUpdate.do")
	public @ResponseBody ModelAndView ajaxMstMbrStsCdUpdate(@RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView("jsonView");

		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);
		if(usrInfo == null || StringUtils.defaultString((String)usrInfo.get("USR_ID"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "로그인 후 이용 가능 합니다.");
			resultView.addObject("actUrl", "/login.do");

			return resultView;
		}

		if("".equals(StringUtils.defaultIfEmpty((String)reqMap.get("stsMbrNo"), ""))) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");

			return resultView;
		}
		if("".equals(StringUtils.defaultIfEmpty((String)reqMap.get("instCd"), ""))) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");

			return resultView;
		}
		if("".equals(StringUtils.defaultIfEmpty((String)reqMap.get("stsCd"), ""))) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");

			return resultView;
		}
		if("".equals(StringUtils.defaultIfEmpty((String)reqMap.get("stpDt"), ""))) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");

			return resultView;
		}

		reqMap.put("mbrNo", StringUtils.defaultIfEmpty((String)reqMap.get("stsMbrNo"), ""));
		reqMap.put("stpDt", StringUtils.defaultIfEmpty((String)reqMap.get("stpDt"), "").replaceAll("-", ""));
		reqMap.put("creId", StringUtils.defaultString((String)usrInfo.get("USR_ID"), ""));

		int reuslt = memberService.StsCdUpdate(reqMap);
		if(reuslt > 0) {
			if(ConstantObject.rlMemStsCd.equals(StringUtils.defaultIfEmpty((String)reqMap.get("stsCd"), ""))) {
				resultView.addObject("MSG", "퇴록");
			}else {
				resultView.addObject("MSG", "재등록");
			}
		}else{
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "처리 오류");
		}

		return resultView;
	}

	/**
	 * 의뢰내용 등록
	 * @param multiRequest
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxTransAdd.do")
	public @ResponseBody ModelAndView ajaxTransAdd(final MultipartHttpServletRequest multiRequest, @RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView("jsonView");

		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);

		String usrId = StringUtils.defaultString((String)usrInfo.get("USR_ID"), "");
		String transDt = StringUtils.defaultIfEmpty((String)reqMap.get("transDt"), "").replaceAll("-", "");		// 의뢰일자
		if("".equals(StringUtils.defaultIfEmpty((String)reqMap.get("mbrNo"), ""))) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}
		if("".equals(StringUtils.defaultIfEmpty((String)reqMap.get("receiptInstCd"), ""))) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}
		if("".equals(transDt)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}
		if("".equals(StringUtils.defaultIfEmpty((String)reqMap.get("fmlyName"), ""))) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}
		if("".equals(StringUtils.defaultIfEmpty((String)reqMap.get("fmlyRelationCd"), ""))) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}
		if("".equals(StringUtils.defaultIfEmpty((String)reqMap.get("fmlySexCd"), ""))) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}
		if("".equals(StringUtils.defaultIfEmpty((String)reqMap.get("fmlyAge"), ""))) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}

		reqMap.put("transDt", transDt);
		reqMap.put("creId", usrId);
		reqMap.put("transInstCd", StringUtils.defaultIfEmpty((String)usrInfo.get("SITE_CD"), ""));
		reqMap.put("transUsrId", StringUtils.defaultIfEmpty((String)usrInfo.get("USR_ID"), ""));

		// 첨부 파일 정보
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		if (!files.isEmpty()) {
			boolean flag = fileUtil.checkFiles(files);

			if(flag) {
				HashMap<String, Object> fileMng = fileUtil.parseFileInf(files, "MBR", usrId);
				if(!fileMng.isEmpty()) {
					List<HashMap<String, Object>> fileList = (List<HashMap<String, Object>>)fileMng.get("fileList");
					Long newFileId = Long.parseLong(fileInfoService.getFileInfoId());

					if(fileList != null) {
						for(HashMap<String, Object> fileInfo : fileList) {
							fileInfo.put("fileId", Long.toString(newFileId));
							fileInfo.put("fileSeq", "1");
							reqMap.put((String)fileInfo.get("formName"), Long.toString(newFileId++));
						}
					}

					reqMap.put("fileList", fileList);
				}
			}
		}

		HashMap<String, Object> resultMap = memberService.saveTrans(reqMap);
		if(resultMap != null) {
			resultView.addObject("err", resultMap.get("err"));
			resultView.addObject("MSG", resultMap.get("MSG"));
		}

		return resultView;
	}

	/**
	 * 의뢰 목록 조회
	 * @param model
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getTransList.do")
	public String getTransList(ModelMap model, @RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);
		String listType = StringUtils.defaultIfEmpty((String)reqMap.get("listType"), "");
		String currentPageNo = StringUtils.defaultIfEmpty((String)reqMap.get("pageNo"), "1");
		String recordCountPerPage = StringUtils.defaultIfEmpty((String)reqMap.get("perPage"), "5");

		PaginationInfo paginginfo = new PaginationInfo();
		if(currentPageNo == "" || recordCountPerPage == ""){
			paginginfo.setCurrentPageNo(1);
			paginginfo.setPageSize(Integer.parseInt(ConstantObject.defaultPageSize));
			paginginfo.setRecordCountPerPage(5);
		} else {
			paginginfo.setCurrentPageNo(Integer.valueOf(currentPageNo));
			paginginfo.setPageSize(Integer.parseInt(ConstantObject.defaultPageSize));
			paginginfo.setRecordCountPerPage(5);
		}

		// 관리자가 아닌 경우
		if(!ConstantObject.adminRoleCd.equals(StringUtils.defaultIfEmpty((String)usrInfo.get("ROLE_CD"), ""))) {
			if("L".equals(listType)) {
				reqMap.put("receiptInstCd", StringUtils.defaultIfEmpty((String)usrInfo.get("SITE_CD"), "X"));
			}else {
				reqMap.put("transInstCd", StringUtils.defaultIfEmpty((String)usrInfo.get("SITE_CD"), "X"));
			}
		}

		model.put("pageNo", currentPageNo);
		reqMap.put("currentPageNo", paginginfo.getCurrentPageNo());
		reqMap.put("recordCountPerPage", paginginfo.getRecordCountPerPage());

		int totalCount = memberService.getMstTransListCount(reqMap);
		paginginfo.setTotalRecordCount(totalCount);

		model.put("totalCount", totalCount);
		model.put("paginationInfo", paginginfo);

		if(totalCount > 0) {
			List<HashMap<String, Object>> resultList = memberService.getMstTransList(reqMap);
			model.put("resultList", resultList);
		}

		model.put("listType", listType);

		return "member/layer/transList";
	}

	/**
	 * 의뢰 상세 정보 조회
	 * @param transNo
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxMstTransInfo.do")
	public @ResponseBody ModelAndView ajaxMstTransInfo(@RequestParam String transNo, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView("jsonView");
		HashMap<String, Object> map = new HashMap<String, Object>();

		if("".equals(StringUtils.defaultIfEmpty(transNo, ""))) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}

		map.put("transNo", transNo);

		resultView.addObject("transInfo", memberService.getMstTrans(map));

		return resultView;
	}

	/**
	 * 회원정보관리 의뢰
	 * @param model
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/memberLink.do")
	public String memberLink(ModelMap model, @RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		HashMap<String, Object> codeListMap = new HashMap<String, Object>();
		codeListMap.put("useYn", ConstantObject.Y);

		codeListMap.put("grpCd", "C3500");				// 전국지부
		model.put("spotList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5010");				// 약물중독/알코올중독/도박중독/인터넷중독/기타중독
		model.put("evlItmSco01List", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5120");				// 정신과적 증상
		model.put("evlItmSco06List", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5110");				// 자해/자살위험/타해위험
		model.put("evlItmSco18List", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5130");				// 정신약물관리
		model.put("evlItmSco07List", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5140");				// 스트레스 상태
		model.put("evlItmSco08List", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5150");				// 신체질환
		model.put("evlItmSco09List", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5210");				// 가족관계
		model.put("evlItmSco10List", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5310");				// 주거/고용 및 교육가능성/법률적 문제
		model.put("evlItmSco12List", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C7700");				// 욕구
		model.put("evlItmSco14List", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5220");				// 사회적관계/회복환경 관계
		model.put("evlItmSco11List", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5320");				// 경제활동 지원
		model.put("evlItmSco13List", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C8400");				// 요청서비스
		model.put("reqServiceList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C8500");				// 연계상태
		model.put("linkStateList", sysCodeService.getSysCdList(codeListMap));

		return "member/memberLink";
	}

	/**
	 * 연계내용 저장
	 * @param multiRequest
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxTransResult.do")
	public @ResponseBody ModelAndView ajaxTransResult(final MultipartHttpServletRequest multiRequest, @RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView("jsonView");

		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);

		String usrId = StringUtils.defaultString((String)usrInfo.get("USR_ID"), "");
		String transDt = StringUtils.defaultIfEmpty((String)reqMap.get("transDt"), "").replaceAll("-", "");		// 의뢰일자
		if("".equals(StringUtils.defaultIfEmpty((String)reqMap.get("transNo"), ""))) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}
		if("".equals(StringUtils.defaultIfEmpty((String)reqMap.get("mbrNo"), ""))) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}
		if("".equals(StringUtils.defaultIfEmpty((String)reqMap.get("receiptInstCd"), ""))) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}
		if("".equals(transDt)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}
		if("".equals(StringUtils.defaultIfEmpty((String)reqMap.get("fmlyName"), ""))) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}
		if("".equals(StringUtils.defaultIfEmpty((String)reqMap.get("fmlyRelationCd"), ""))) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}
		if("".equals(StringUtils.defaultIfEmpty((String)reqMap.get("fmlySexCd"), ""))) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}
		if("".equals(StringUtils.defaultIfEmpty((String)reqMap.get("fmlyAge"), ""))) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}

		reqMap.put("transDt", transDt);
		reqMap.put("creId", usrId);

		// 첨부 파일 정보
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		if (!files.isEmpty()) {
			boolean flag = fileUtil.checkFiles(files);

			if(flag) {
				HashMap<String, Object> fileMng = fileUtil.parseFileInf(files, "MBR", usrId);
				if(!fileMng.isEmpty()) {
					List<HashMap<String, Object>> fileList = (List<HashMap<String, Object>>)fileMng.get("fileList");
					Long newFileId = Long.parseLong(fileInfoService.getFileInfoId());

					if(fileList != null) {
						for(HashMap<String, Object> fileInfo : fileList) {
							fileInfo.put("fileId", Long.toString(newFileId));
							fileInfo.put("fileSeq", "1");
							reqMap.put((String)fileInfo.get("formName"), Long.toString(newFileId++));
						}
					}

					reqMap.put("fileList", fileList);
				}
			}
		}

		HashMap<String, Object> resultMap = memberService.saveTransState(reqMap);
		if(resultMap != null) {
			resultView.addObject("err", resultMap.get("err"));
			resultView.addObject("MSG", resultMap.get("MSG"));
		}

		return resultView;
	}
}
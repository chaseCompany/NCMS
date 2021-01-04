package kr.co.chase.ncms.individual.web;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import kr.co.chase.ncms.common.ConstantObject;
import kr.co.chase.ncms.common.service.SysCodeService;
import kr.co.chase.ncms.common.util.FileManagerUtil;
import kr.co.chase.ncms.individual.service.IndividualService;
import kr.co.chase.ncms.vo.CslAssVO;
import kr.co.chase.ncms.vo.CslIdvVO;
import kr.co.chase.ncms.vo.CslIspVO;
import kr.co.chase.ncms.vo.MstMbrVO;

@Controller
public class IndividualController {
	@Resource(name="sysCodeService")
	private SysCodeService sysCodeService;

	@Resource(name="individualService")
	private IndividualService individualService;

	@Resource(name = "FileManagerUtil")
	private FileManagerUtil fileUtil;

	/**
	 * 개별 회원 복지 서비스 메인
	 * @param model
	 * @param session
	 * @param mstMbrVO
	 * @param cslIdvVO
	 * @param cslIspVO
	 * @param cslAssVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/individualMain.do")
	public String individualMain(ModelMap model, HttpSession session
			, @ModelAttribute("mstMbrVO") MstMbrVO mstMbrVO
			, @ModelAttribute("cslIdvVO") CslIdvVO cslIdvVO
			, @ModelAttribute("cslIspVO") CslIspVO cslIspVO
			, @ModelAttribute("cslAssVO") CslAssVO cslAssVO) throws Exception{
		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);

		if(usrInfo == null || StringUtils.defaultString((String)usrInfo.get("USR_ID"), "") == "") {
			return "redirect:/login.do";
		}

		// 집중상담 기본값 셋팅
		cslIdvVO.setCslNm(StringUtils.defaultString((String)usrInfo.get("USR_NM"), ""));
		cslIdvVO.setCslDt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		cslIdvVO.setCslTgtCd("10");
		cslIdvVO.setCslTpCd("20");
		// ISP 수립 기본값 셋팅
		cslIspVO.setIspDt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		cslAssVO.setSvrRcgDgr("1");

		model.put("mstMbrInfo", mstMbrVO);				// 회원정보
		model.put("cslIdvInfo", cslIdvVO);				// 집중상담
		model.put("cslIspInfo", cslIspVO);				// ISP수립
		model.put("cslAssInfo", cslAssVO);				// 사정평가

		HashMap<String, Object> codeListMap = new HashMap<String, Object>();
		codeListMap.put("useYn", ConstantObject.Y);

		codeListMap.put("grpCd", "C2500");				// 상담대상
		model.put("cslTgtCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C1600");				// 상담유형
		model.put("cslTpCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C2100");				// Rating A: 위험성
		model.put("rskaTpList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C2200");				// Rating B: 지지체계
		model.put("rskbTpList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C2300");				// Rating C: 협조능력
		model.put("rskcTpList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C4200");				// 관리구분
		model.put("mngTpList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5010");				// 약물사용문제
		model.put("evlItmSco01List", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5110");				// 자해 및 타해 위험
		model.put("evlItmSco05List", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5210");				// 가족관계
		model.put("evlItmSco10List", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5310");				// 주거
		model.put("evlItmSco12List", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5020");				// 알코올사용문제
		model.put("evlItmSco02List", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5120");				// 정신과적 증상
		model.put("evlItmSco06List", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5220");				// 사회적관계
		model.put("evlItmSco11List", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5320");				// 경제활동 지원
		model.put("evlItmSco13List", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5030");				// 도박사용문제
		model.put("evlItmSco03List", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5130");				// 정신약물관리
		model.put("evlItmSco07List", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5330");				// 취업 및 학업욕구
		model.put("evlItmSco14List", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5040");				// 인터넷사용문제
		model.put("evlItmSco04List", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5140");				// 스트레스 상태
		model.put("evlItmSco08List", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5340");				// 고용 및 교육가능성
		model.put("evlItmSco15List", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5150");				// 신체질환
		model.put("evlItmSco09List", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5350");				// 법률적 문제
		model.put("evlItmSco16List", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C1800");				// 최초 사용약물
		model.put("fstDrugCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C1900");				// 주요 사용약물
		model.put("mainDrugCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C2600");				// 사용빈도
		model.put("useFrqCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C4100");				// 사용빈도
		model.put("useCauCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C3200");				// 사용빈도
		model.put("lawPbmCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C4000");				// 정신적 건강문제
		model.put("sprtPbmCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5360");				// 성격
		model.put("prsnCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5370");				// 정서-심리
		model.put("emtnCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5380");				// 행동
		model.put("actnCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5390");				// 가족
		model.put("fmlyCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5400");				// 대인관계
		model.put("itRlCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C2400");				// 평가도구
		model.put("evlTolCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5600");				// URS
		model.put("ursCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5700");				// 치료력
		model.put("cureCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5800");				// 선택
		model.put("selCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5900");				// 시도횟수
		model.put("actCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C6000");				// 시도방법
		model.put("actWayCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C6100");				// 연계구분
		model.put("linkCdList", sysCodeService.getSysCdList(codeListMap));

		return "individual/individualMain";
	}

	/**
	 * 집중 상담 이력 조회
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getClsIdvList.do")
	public @ResponseBody ModelAndView getClsIdvList(@RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView("jsonView");

		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);
		if(usrInfo == null || StringUtils.defaultString((String)usrInfo.get("USR_ID"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "로그인 후 이용 가능 합니다.");
			resultView.addObject("actUrl", "/login.do");

			return resultView;
		}

		if(StringUtils.defaultString((String)reqMap.get("mbrNo"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "회원번호를 선택하세요.");

			return resultView;
		}

		resultView.addObject("clsIdvList", individualService.getCslIdvList(reqMap));

		return resultView;
	}

	/**
	 * 집중 상담 상세 조회
	 * @param csNo
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxClsIdvInfo.do")
	public @ResponseBody ModelAndView ajaxClsIdvInfo(@RequestParam String cslNo, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView("jsonView");

		if(StringUtils.defaultString(cslNo, "").equals("")) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "상담번호를 입력하세요.");

			return resultView;
		}

		resultView.addObject("clsIdvInfo", individualService.getCslIdv(cslNo));

		return resultView;
	}

	/**
	 * 집중상담 내용 등록
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="ajaxClsIdvAdd.do")
	public @ResponseBody ModelAndView ajaxClsIdvAdd(final MultipartHttpServletRequest multiRequest, @RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView ("jsonView");

		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);

		if(usrInfo == null || StringUtils.defaultString((String)usrInfo.get("USR_ID"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "로그인 후 이용 가능 합니다.");
			resultView.addObject("actUrl", "/login.do");
			return resultView;
		}

		if(StringUtils.defaultString((String)reqMap.get("mbrNo"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}
		if(StringUtils.defaultString((String)reqMap.get("cslDt"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}
		if(StringUtils.defaultString((String)reqMap.get("cslFmTm"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}
		if(StringUtils.defaultString((String)reqMap.get("cslToTm"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}
		if(StringUtils.defaultString((String)reqMap.get("cslSbj"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}
		if(StringUtils.defaultString((String)reqMap.get("rskaTpCd"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}
		if(StringUtils.defaultString((String)reqMap.get("rskbTpCd"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}
		if(StringUtils.defaultString((String)reqMap.get("rskcTpCd"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}
		if(StringUtils.defaultString((String)reqMap.get("ursCd"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}
		if(StringUtils.defaultString((String)reqMap.get("cslCtnt"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}
		if(StringUtils.defaultString((String)reqMap.get("cslRst"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}

		String cslId = StringUtils.defaultString((String)usrInfo.get("USR_ID"), "");
		reqMap.put("cslDt", StringUtils.defaultIfEmpty((String)reqMap.get("cslDt"), "").replaceAll("-", ""));
		reqMap.put("cslId", cslId);

		// 첨부 파일 정보
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		if (!files.isEmpty()) {
			boolean flag = fileUtil.checkFiles(files);

			if(flag) {
				HashMap<String, Object> fileMng = fileUtil.parseFileInf(files, "IDV", cslId);
				if(!fileMng.isEmpty()) {
					List<HashMap<String, Object>> fileList = (List<HashMap<String, Object>>)fileMng.get("fileList");

					reqMap.put("fileList", fileList);
					reqMap.put("fileId", StringUtils.defaultIfEmpty((String)fileMng.get("fileId"), ""));
				}
			}
		}

		HashMap<String, Object> resMap = individualService.cslIdvAdd(reqMap);
		if(resMap != null) {
			resultView.addObject("err", resMap.get("err"));
			resultView.addObject("MSG", resMap.get("MSG"));
		}

		return resultView;
	}

	/**
	 * 집중상담 내역 삭제
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxClsIdvDel.do")
	public @ResponseBody ModelAndView ajaxClsIdvDel(@RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView("jsonView");

		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);

		if(usrInfo == null || StringUtils.defaultString((String)usrInfo.get("USR_ID"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "로그인 후 이용 가능 합니다.");
			resultView.addObject("actUrl", "/login.do");

			return resultView;
		}

		if(StringUtils.defaultString(reqMap.get("cslNo").toString(), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");

			return resultView;
		}

		int resultNum = individualService.deleteCslIdv(StringUtils.defaultString(reqMap.get("cslNo").toString(), ""));
		if(resultNum <= 0) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "삭제 오류");
		}

		return resultView;
	}

	/**
	 * ISP 수립 목록 조회
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxClsIspList.do")
	public @ResponseBody ModelAndView ajaxClsIspList(@RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView("jsonView");

		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);

		if(usrInfo == null || StringUtils.defaultString((String)usrInfo.get("USR_ID"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "로그인 후 이용 가능 합니다.");
			resultView.addObject("actUrl", "/login.do");

			return resultView;
		}

		if(StringUtils.defaultString((String)reqMap.get("mbrNo"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "회원번호를 선택하세요.");

			return resultView;
		}

		resultView.addObject("clsIspList", individualService.getCslIspList(StringUtils.defaultString((String)reqMap.get("mbrNo"), "")));

		return resultView;
	}

	/**
	 * ISP 수립 삭제
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxClsIspDel.do")
	public @ResponseBody ModelAndView ajaxClsIspDel(@RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView("jsonView");

		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);

		if(usrInfo == null || StringUtils.defaultString((String)usrInfo.get("USR_ID"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "로그인 후 이용 가능 합니다.");
			resultView.addObject("actUrl", "/login.do");

			return resultView;
		}

		if(StringUtils.defaultString((String)reqMap.get("mbrNo"), "") == "" && StringUtils.defaultString((String)reqMap.get("ispDt"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");

			return resultView;
		}

		int resultNum = individualService.deleteCslIsp(reqMap);
		if(resultNum <= 0) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "삭제 오류");
		}

		return resultView;
	}

	/**
	 * ISP 수립 상세 내역 조회
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxClsIspInfo.do")
	public @ResponseBody ModelAndView ajaxClsIspInfo(@RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView("jsonView");

		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);

		if(usrInfo == null || StringUtils.defaultString(usrInfo.get("USR_ID").toString(), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "로그인 후 이용 가능 합니다.");
			resultView.addObject("actUrl", "/login.do");

			return resultView;
		}

		if(StringUtils.defaultString(reqMap.get("mbrNo").toString(), "") == "" && StringUtils.defaultString(reqMap.get("ispDt").toString(), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");

			return resultView;
		}

		resultView.addObject("ispInfo", individualService.getCslIspInfo(reqMap));

		return resultView;
	}

	/**
	 * ISP 수립 등록
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxCslIspAdd.do")
	public @ResponseBody ModelAndView ajaxCslIspAdd(@RequestParam HashMap<String, Object> reqMap, HttpServletRequest request, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView ("jsonView");

		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);

		if(usrInfo == null || StringUtils.defaultString((String)usrInfo.get("USR_ID"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "로그인 후 이용 가능 합니다.");
			resultView.addObject("actUrl", "/login.do");

			return resultView;
		}

		if(StringUtils.defaultString(reqMap.get("mbrNo").toString(), "") == "" && StringUtils.defaultString(reqMap.get("ispDt").toString(), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}

		reqMap.put("ispDt", StringUtils.defaultIfEmpty(reqMap.get("ispDt").toString(), "").replaceAll("-", ""));
		reqMap.put("cslId", StringUtils.defaultString((String)usrInfo.get("USR_ID"), ""));
		String[] linkCdList = request.getParameterValues("linkCd");

		if(linkCdList != null && linkCdList.length > 0) {
			String linkCd = "[";
			for(String cd : linkCdList) {
				linkCd += cd + ", ";
			}
			linkCd = linkCd.substring(0, linkCd.length() - 2) + "]";

			reqMap.put("linkCd", linkCd);
		}

		HashMap<String, Object> resMap = individualService.cslIspAdd(reqMap);
		if(resMap != null) {
			resultView.addObject("err", resMap.get("err"));
			resultView.addObject("MSG", resMap.get("MSG"));
		}

		return resultView;
	}

	/**
	 * 사정평가 내용 조회
	 * @param mbrNo
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxCslAssInfo.do")
	public @ResponseBody ModelAndView ajaxCslAssInfo(@RequestParam String mbrNo, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView ("jsonView");

		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);
		if(usrInfo == null || StringUtils.defaultString((String)usrInfo.get("USR_ID"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "로그인 후 이용 가능 합니다.");
			resultView.addObject("actUrl", "/login.do");

			return resultView;
		}

		if(StringUtils.defaultString(mbrNo, "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");

			return resultView;
		}

		HashMap<String, Object> resultMap = individualService.getCslAssInfoView(mbrNo);
		if(resultMap != null) {
			resultView.addObject("cslAssInfo", resultMap.get("cslAssInfo"));
			resultView.addObject("cslAssEvlList", resultMap.get("cslAssEvlList"));
		}

		return resultView;
	}

	/**
	 * 사정 평가 내용 등록
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxCslAssAdd.do")
	public @ResponseBody ModelAndView ajaxCslAssAdd(@RequestParam HashMap<String, Object> reqMap, HttpServletRequest request, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView ("jsonView");

		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);
		if(usrInfo == null || StringUtils.defaultString((String)usrInfo.get("USR_ID"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "로그인 후 이용 가능 합니다.");
			resultView.addObject("actUrl", "/login.do");

			return resultView;
		}

		String fstDrugCd = StringUtils.defaultIfEmpty((String)reqMap.get("fstDrugCd"), "");						// 최초 사용약물
		String mainDrugCd = StringUtils.defaultIfEmpty((String)reqMap.get("mainDrugCd"), "");					// 주요 사용약물
		String fstAge = StringUtils.defaultIfEmpty((String)reqMap.get("fstAge"), "");							// 최초 사용시기
		String lstAge = StringUtils.defaultIfEmpty((String)reqMap.get("lstAge"), "");							// 마지막 사용시기
		String useTerm = StringUtils.defaultIfEmpty((String)reqMap.get("useTerm"), "");							// 사용기간
		String useFrqCd = StringUtils.defaultIfEmpty((String)reqMap.get("useFrqCd"), "");						// 사용빈도
		String useCauCd = StringUtils.defaultIfEmpty((String)reqMap.get("useCauCd"), "");						// 사용원인
		String[] lawPbmCdList = request.getParameterValues("lawPbmCd");											// 약물관련 법적문제
		String physPbm = StringUtils.defaultIfEmpty((String)reqMap.get("physPbm"), "");							// 신체적 건강문제
		String sprtPbmCd = StringUtils.defaultIfEmpty((String)reqMap.get("sprtPbmCd"), "");						// 정신적 건강문제
		String[] prsnCdList = request.getParameterValues("prsnCd");												// 성격
		String[] emtnCdList = request.getParameterValues("emtnCd");												// 정서-심리
		String[] actnCdList = request.getParameterValues("actnCd");												// 행동
		String[] fmlyCdList = request.getParameterValues("fmlyCd");												// 가족
		String[] itRlCdList = request.getParameterValues("itRlCd");												// 대인관계
		String[] evlTolCdList = request.getParameterValues("evlTolCdHidden");									// 평가 도구
		String[] evlScoList = request.getParameterValues("evlScoHidden");										// 평가 점수
		String[] evlCtntList = request.getParameterValues("evlCtntHidden");										// 평가 내용
		if("".equals(fstDrugCd)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");

			return resultView;
		}
		if("".equals(mainDrugCd)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");

			return resultView;
		}
		if("".equals(fstAge)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");

			return resultView;
		}
		if("".equals(lstAge)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");

			return resultView;
		}
		if("".equals(useTerm)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");

			return resultView;
		}
		if("".equals(useFrqCd)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");

			return resultView;
		}
		if("".equals(useCauCd)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");

			return resultView;
		}
		if(lawPbmCdList == null || lawPbmCdList.length <= 0) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");

			return resultView;
		}
		if("".equals(physPbm)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");

			return resultView;
		}
		if("".equals(sprtPbmCd)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");

			return resultView;
		}

		String lawPbmCd = "[";
		for(String pbmCd : lawPbmCdList) {
			lawPbmCd += pbmCd + ", ";
		}
		lawPbmCd = lawPbmCd.substring(0, lawPbmCd.length() - 2) + "]";

		if(prsnCdList != null && prsnCdList.length > 0) {
			String prsnCd = "[";
			for(String cd : prsnCdList) {
				prsnCd += cd + ", ";
			}
			prsnCd = prsnCd.substring(0, prsnCd.length() - 2) + "]";

			reqMap.put("prsnCd", prsnCd);
		}
		if(emtnCdList != null && emtnCdList.length > 0) {
			String emtnCd = "[";
			for(String cd : emtnCdList) {
				emtnCd += cd + ", ";
			}
			emtnCd = emtnCd.substring(0, emtnCd.length() - 2) + "]";

			reqMap.put("emtnCd", emtnCd);
		}
		if(actnCdList != null && actnCdList.length > 0) {
			String actnCd = "[";
			for(String cd : actnCdList) {
				actnCd += cd + ", ";
			}
			actnCd = actnCd.substring(0, actnCd.length() - 2) + "]";

			reqMap.put("actnCd", actnCd);
		}
		if(fmlyCdList != null && fmlyCdList.length > 0) {
			String fmlyCd = "[";
			for(String cd : fmlyCdList) {
				fmlyCd += cd + ", ";
			}
			fmlyCd = fmlyCd.substring(0, fmlyCd.length() - 2) + "]";

			reqMap.put("fmlyCd", fmlyCd);
		}
		if(itRlCdList != null && itRlCdList.length > 0) {
			String itRlCd = "[";
			for(String cd : itRlCdList) {
				itRlCd += cd + ", ";
			}
			itRlCd = itRlCd.substring(0, itRlCd.length() - 2) + "]";

			reqMap.put("itRlCd", itRlCd);
		}
		if(evlTolCdList != null && evlTolCdList.length > 0) {
			List<HashMap<String, Object>> evlList = new ArrayList<HashMap<String, Object>>();

			for(int i=0 ; i<evlTolCdList.length ; i++) {
				HashMap<String, Object> assEvlMap = new HashMap<String, Object>();
				assEvlMap.put("mbrNo", reqMap.get("mbrNo"));
				assEvlMap.put("evlTolCd", evlTolCdList[i]);
				assEvlMap.put("evlSco", evlScoList[i]);
				assEvlMap.put("evlCtnt", evlCtntList[i]);

				evlList.add(assEvlMap);
			}

			reqMap.put("evlList", evlList);
		}

		reqMap.put("creId", StringUtils.defaultString((String)usrInfo.get("USR_ID"), ""));
		reqMap.put("lawPbmCd", lawPbmCd);

		HashMap<String, Object> resMap = individualService.addCslAss(reqMap);
		if(resMap != null) {
			resultView.addObject("err", resMap.get("err"));
			resultView.addObject("MSG", resMap.get("MSG"));
		}

		return resultView;
	}
}

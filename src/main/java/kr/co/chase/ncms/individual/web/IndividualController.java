package kr.co.chase.ncms.individual.web;

import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import kr.co.chase.ncms.vo.CslIdvVO;
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
			, @ModelAttribute("cslIdvVO") CslIdvVO cslIdvVO) throws Exception{
		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);

		if(usrInfo == null || StringUtils.defaultString((String)usrInfo.get("USR_ID"), "") == "") {
			return "redirect:/login.do";
		}

		// 집중상담 기본값 셋팅
		cslIdvVO.setCslNm(StringUtils.defaultString((String)usrInfo.get("USR_NM"), ""));
		cslIdvVO.setCslDt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		cslIdvVO.setCslTgtCd("10");
		cslIdvVO.setCslTpCd("20");

		model.put("mstMbrInfo", mstMbrVO);				// 회원정보
		model.put("cslIdvInfo", cslIdvVO);				// 집중상담

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

		codeListMap.put("grpCd", "C5010");				// 약물중독/알코올중독/도박중독/인터넷중독/기타중독
		model.put("evlItmSco01List", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5110");				// 자해/자살위험/타해위험
		model.put("evlItmSco18List", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5210");				// 가족관계
		model.put("evlItmSco10List", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5310");				// 주거/고용 및 교육가능성/법률적 문제
		model.put("evlItmSco12List", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5120");				// 정신과적 증상
		model.put("evlItmSco06List", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5220");				// 사회적관계/회복환경 관계
		model.put("evlItmSco11List", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5320");				// 경제활동 지원
		model.put("evlItmSco13List", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5130");				// 정신약물관리
		model.put("evlItmSco07List", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5140");				// 스트레스 상태
		model.put("evlItmSco08List", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5150");				// 신체질환
		model.put("evlItmSco09List", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C7700");				// 욕구
		model.put("evlItmSco14List", sysCodeService.getSysCdList(codeListMap));

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

		codeListMap.put("grpCd", "C6200");				// 자살시도력 문제유형
		model.put("sudTypeList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C6300");				// 자살시도력 정신건강문제
		model.put("sudSoulList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C6400");				// 자살시도력 시도방법
		model.put("sudWayList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C6500");				// 발달력 영유아기 임신
		model.put("devBabyPregList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C6600");				// 발달력 영유아기 발육상태
		model.put("devBabyDevList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C6700");				// 발달력 영유아기 주양육자
		model.put("devBabyNurtList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C6800");				// 발달력 아동기 훈육방식
		model.put("devChildDiscList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C6900");				// 발달력 학습태도
		model.put("devLearnList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C7000");				// 발달력 대인관계
		model.put("devRelationList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C7100");				// 발달력 특이사항
		model.put("devTeenUniList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C7200");				// 발달력 이성교제
		model.put("devAdulSexList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C7300");				// 순응도
		model.put("conformList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C7400");				// 신체질환
		model.put("mediIllList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C7500");				// 고용형태
		model.put("jobFormList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C1200");				// 직업군
		model.put("jobTypeList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C7600");				// 기관유형
		model.put("organFormList", sysCodeService.getSysCdList(codeListMap));

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
	 * 병력정보 목록 조회
	 * @param mbrNo
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxCslAnmList.do")
	public @ResponseBody ModelAndView ajaxCslAnmList(@RequestParam String mbrNo, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView ("jsonView");

		if(StringUtils.defaultString(mbrNo, "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");

			return resultView;
		}

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("mbrNo", mbrNo);

		resultView.addObject("cslAnmList", individualService.getCslAnmList(paramMap));

		return resultView;
	}

	/**
	 * 병력정보 등록
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxCslAnmAdd.do")
	public @ResponseBody ModelAndView ajaxCslAssAdd(@RequestParam HashMap<String, Object> reqMap, HttpServletRequest request, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView ("jsonView");

		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);

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

		reqMap.put("creId", StringUtils.defaultString((String)usrInfo.get("USR_ID"), ""));
		reqMap.put("lawPbmCd", lawPbmCd);
		reqMap.put("sudIndt", StringUtils.defaultString((String)reqMap.get("sudIndt"), "").replaceAll("-", ""));

		HashMap<String, Object> resMap = individualService.addCslAns(reqMap);
		if(resMap != null) {
			resultView.addObject("err", resMap.get("err"));
			resultView.addObject("MSG", resMap.get("MSG"));
		}

		return resultView;
	}

	/**
	 * 병력정보 상세 조회
	 * @param mbrNo
	 * @param cslNo
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxCslAnmInfo.do")
	public @ResponseBody ModelAndView ajaxCslAnmInfo(@RequestParam String mbrNo, @RequestParam String cslNo, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView ("jsonView");

		if(StringUtils.defaultString(mbrNo, "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");

			return resultView;
		}
		if(StringUtils.defaultString(cslNo, "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");

			return resultView;
		}

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("mbrNo", mbrNo);
		paramMap.put("cslNo", cslNo);

		resultView.addObject("cslAnminfo", individualService.getCslAnmInfo(paramMap));

		return resultView;
	}

	/**
	 * 병력정보 삭제
	 * @param mbrNo
	 * @param cslNo
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxCslAnmDel.do")
	public @ResponseBody ModelAndView ajaxCslAnmDel(@RequestParam String mbrNo, @RequestParam String cslNo, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView ("jsonView");

		if(StringUtils.defaultString(mbrNo, "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");

			return resultView;
		}
		if(StringUtils.defaultString(cslNo, "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");

			return resultView;
		}

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("mbrNo", mbrNo);
		paramMap.put("cslNo", cslNo);

		int result = individualService.deleteCslAnm(paramMap);
		if(result <= 0) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "삭제 오류");
		}

		return resultView;
	}

	/**
	 * 치료재활정보 이력 조회
	 * @param mbrNo
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxCslHealList.do")
	public @ResponseBody ModelAndView ajaxCslHealList(@RequestParam String mbrNo, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView ("jsonView");

		if(StringUtils.defaultString(mbrNo, "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");

			return resultView;
		}

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("mbrNo", mbrNo);

		resultView.addObject("cslHealList", individualService.getCslHealList(paramMap));

		return resultView;
	}

	/**
	 * 치료재활정보 저장
	 * @param reqMap
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxCslHealAdd.do")
	public @ResponseBody ModelAndView ajaxCslHealAdd(@RequestParam HashMap<String, Object> reqMap, HttpServletRequest request, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView ("jsonView");

		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);

		String diagName = StringUtils.defaultIfEmpty((String)reqMap.get("diagName"), "");						// 진단명

		if("".equals(diagName)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");

			return resultView;
		}

		reqMap.put("creId", StringUtils.defaultString((String)usrInfo.get("USR_ID"), ""));
		reqMap.put("attDt", StringUtils.defaultString((String)reqMap.get("attDt"), "").replaceAll("-", ""));
		reqMap.put("jobStDt", StringUtils.defaultString((String)reqMap.get("jobStDt"), "").replaceAll("-", ""));
		reqMap.put("jobEndDt", StringUtils.defaultString((String)reqMap.get("jobEndDt"), "").replaceAll("-", ""));
		reqMap.put("healStDt", StringUtils.defaultString((String)reqMap.get("healStDt"), "").replaceAll("-", ""));
		reqMap.put("healEndDt", StringUtils.defaultString((String)reqMap.get("healEndDt"), "").replaceAll("-", ""));

		HashMap<String, Object> resMap = individualService.addCslHeal(reqMap);
		if(resMap != null) {
			resultView.addObject("err", resMap.get("err"));
			resultView.addObject("MSG", resMap.get("MSG"));
		}

		return resultView;
	}

	/**
	 * 치료재활정보 상세 보기
	 * @param mbrNo
	 * @param cslNo
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxCslHealInfo.do")
	public @ResponseBody ModelAndView ajaxCslHealInfo(@RequestParam String mbrNo, @RequestParam String cslNo, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView ("jsonView");

		if(StringUtils.defaultString(mbrNo, "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");

			return resultView;
		}
		if(StringUtils.defaultString(cslNo, "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");

			return resultView;
		}

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("mbrNo", mbrNo);
		paramMap.put("cslNo", cslNo);

		resultView.addObject("cslHealinfo", individualService.getCslHealInfo(paramMap));

		return resultView;
	}

	/**
	 * 치료재활정보 삭제
	 * @param mbrNo
	 * @param cslNo
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxCslHealDel.do")
	public @ResponseBody ModelAndView ajaxCslHealDel(@RequestParam String mbrNo, @RequestParam String cslNo, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView ("jsonView");

		if(StringUtils.defaultString(mbrNo, "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");

			return resultView;
		}
		if(StringUtils.defaultString(cslNo, "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");

			return resultView;
		}

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("mbrNo", mbrNo);
		paramMap.put("cslNo", cslNo);

		int result = individualService.deleteCslHeal(paramMap);
		if(result <= 0) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "삭제 오류");
		}

		return resultView;
	}

	/**
	 * 기관명 목록 조회
	 * @param model
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxOrganList.do")
	public String ajaxOrganList(ModelMap model, @RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		String currentPageNo = StringUtils.defaultIfEmpty((String)reqMap.get("pageNo"), "");
		String recordCountPerPage = StringUtils.defaultIfEmpty((String)reqMap.get("perPage"), ConstantObject.defaultRowSize);
/*
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

		model.put("listType", StringUtils.defaultIfEmpty((String)reqMap.get("listType"), ""));
*/
		return "counsel/layer/mbrSearchLayer";
	}
	

	/**
	 * 사례관리상담 엑셀다운로드
	 * @param modelMap
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/individualExcelDownload.do")
	public String individualExcelDownload(@RequestParam HashMap<String, Object> reqMap, Map<String, Object> modelMap, HttpServletResponse response, HttpServletRequest request) throws Exception {
		String title = "사례관리상담";
		String cslNo = StringUtils.defaultIfEmpty((String)reqMap.get("cslNo"), "");

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Pragma", "public");
		response.setHeader("Expires", "0");
		response.setHeader("Content-Disposition", "attachment; filename = " + URLEncoder.encode(title, "UTF-8") + "_" + cslNo + ".xlsx");
		modelMap.put("sheetName", title);

		HashMap<String, Object> cslInfo = individualService.getCslIdv(cslNo);

		modelMap.put("cslInfo", cslInfo);
		modelMap.put("imagesPath", request.getServletContext().getRealPath("/images/excel_logo.png"));

		return "IndividualExcel";
	}
	
	/**
	 * 치료재활정보 엑셀다운로드
	 * @param modelMap
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cureExcelDownload.do")
	public String cureExcelDownload(@RequestParam HashMap<String, Object> reqMap, Map<String, Object> modelMap, HttpServletResponse response, HttpServletRequest request) throws Exception {
		String title = "치료 재활정보";
		String mbrNo = StringUtils.defaultIfEmpty((String)reqMap.get("mbrNo"), "");
		String cslNo = StringUtils.defaultIfEmpty((String)reqMap.get("cslNo"), "");
		String mbrNm = StringUtils.defaultIfEmpty((String)reqMap.get("mbrNm"), "");
		String gendNm = StringUtils.defaultIfEmpty((String)reqMap.get("gendNm"), "");
		String age = StringUtils.defaultIfEmpty((String)reqMap.get("age"), "");
		String regDt = StringUtils.defaultIfEmpty((String)reqMap.get("regDt"), "");
		String medicCareNm = StringUtils.defaultIfEmpty((String)reqMap.get("medicCareNm"), "");
		String mngUsrId = StringUtils.defaultIfEmpty((String)reqMap.get("mngUsrId"), "");
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Pragma", "public");
		response.setHeader("Expires", "0");
		response.setHeader("Content-Disposition", "attachment; filename = " + URLEncoder.encode(title, "UTF-8") + "_" + cslNo + ".xlsx");
		modelMap.put("sheetName", title);

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("mbrNo", mbrNo);
		paramMap.put("cslNo", cslNo);

		HashMap<String, Object> cslInfo = individualService.getCslHealInfo(paramMap);
		
		modelMap.put("cslInfo", cslInfo);
		modelMap.put("mbrNm", mbrNm);
		modelMap.put("gendNm", gendNm);
		modelMap.put("age", age);
		modelMap.put("regDt", regDt);
		modelMap.put("medicCareNm", medicCareNm);
		modelMap.put("mngUsrId", mngUsrId);
		modelMap.put("imagesPath", request.getServletContext().getRealPath("/images/excel_logo.png"));
		
		return "CureExcel";
	}
}
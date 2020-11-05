package kr.co.chase.ncms.individual.web;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

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

import kr.co.chase.ncms.common.ConstantObject;
import kr.co.chase.ncms.common.service.SysCodeService;
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

		if(usrInfo == null || StringUtils.defaultString(usrInfo.get("USR_ID").toString(), "") == "") {
			return "redirect:/login.do";
		}

		// 집중상담 기본값 셋팅
		cslIdvVO.setCslNm(StringUtils.defaultString(usrInfo.get("USR_ID").toString(), ""));
		cslIdvVO.setCslDt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		cslIdvVO.setCslTgtCd("10");
		cslIdvVO.setCslTpCd("20");

		model.put("mstMbrInfo", mstMbrVO);				// 회원정보
		model.put("cslIdvInfo", cslIdvVO);				// 집중상담
		model.put("cslIspInfo", cslIspVO);				// ISP수립
		model.put("cslAssInfo", cslAssVO);				// 사정평가

		HashMap<String, Object> codeListMap = new HashMap<String, Object>();
		codeListMap.put("useYn", ConstantObject.Y);

		codeListMap.put("grpCd", "C3100");				// 의료보장
		model.put("medicCareCdList", sysCodeService.getSysCdList(codeListMap));

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

		if(usrInfo == null || StringUtils.defaultString(usrInfo.get("USR_ID").toString(), "") == "") {
//			return "redirect:/login.do";
		}

		if(StringUtils.defaultString((String)reqMap.get("mbrNo"), "") == "") {
			resultView.addObject("err", "Y");
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
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "상담번호를 입력하세요.");

			return resultView;
		}

		resultView.addObject("clsIdvInfo", individualService.getCslIdv(cslNo));

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

		if(usrInfo == null || StringUtils.defaultString(usrInfo.get("USR_ID").toString(), "") == "") {
//			return "redirect:/login.do";
		}

		if(StringUtils.defaultString(reqMap.get("mbrNo").toString(), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "회원번호를 선택하세요.");

			return resultView;
		}

		resultView.addObject("clsIspList", individualService.getCslIspList(StringUtils.defaultString(reqMap.get("mbrNo").toString(), "")));

		return resultView;
	}

	@RequestMapping(value="/ajaxDelClsIsp.do")
	public @ResponseBody ModelAndView ajaxDelClsIsp(@RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView("jsonView");

		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);

		if(usrInfo == null || StringUtils.defaultString(usrInfo.get("USR_ID").toString(), "") == "") {
//			return "redirect:/login.do";
		}

		if(StringUtils.defaultString(reqMap.get("mbrNo").toString(), "") == "" && StringUtils.defaultString(reqMap.get("ispDt").toString(), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "필수정보 누락");

			return resultView;
		}

		int resultNum = individualService.deleteCslIsp(reqMap);
		if(resultNum <= 0) {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "삭제 오류");
		}

		return resultView;
	}
}

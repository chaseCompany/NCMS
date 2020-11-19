package kr.co.chase.ncms.member.web;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.chase.ncms.common.ConstantObject;
import kr.co.chase.ncms.common.service.SysCodeService;
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

	@RequestMapping(value="/memberMain.do")
	public String memberMain(ModelMap model, @RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);

		if(usrInfo == null || StringUtils.defaultString((String)usrInfo.get("USR_ID"), "") == "") {
			return "redirect:/login.do";
		}

		HashMap<String, Object> codeListMap = new HashMap<String, Object>();
		codeListMap.put("useYn", ConstantObject.Y);

		codeListMap.put("grpCd", "C1100");				// 성별
		model.put("gendCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C1300");				// 내/외국인
		model.put("frgCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C1700");				// 회원구분
		model.put("mbrTpCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C3900");				// 약물사용자와의 관계
		model.put("drgUseCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C3100");				// 의료보장
		model.put("medicCareCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C2700");				// 학력
		model.put("eduCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C2800");				// 결혼여부
		model.put("mrgCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C2900");				// 의뢰경로
		model.put("reqPathCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C3000");				// 종교
		model.put("rlgnCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C1200");				// 직업
		model.put("jobCdList", sysCodeService.getSysCdList(codeListMap));

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("grpCd", "NOT");
		paramMap.put("roleCd", new String[]{"90"});
		model.put("sysMbrList", loginService.getSysUsrList(paramMap));

		String mbrNo = StringUtils.defaultIfEmpty((String)reqMap.get("mbrNo"), "");
		if(!"".equals(mbrNo)) {
			model.put("mbrInfo", memberService.getMstMbr(mbrNo));
			model.put("mstRegHisList", memberService.getMstRegHisList(mbrNo));
		}

		return "member/memberMain";
	}

	/**
	 * 회원 정보 저장
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/addMstMbr.do")
	public @ResponseBody ModelAndView addMstMbr(@RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView("jsonView");

		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);
		if(usrInfo == null || StringUtils.defaultString((String)usrInfo.get("USR_ID"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "로그인 후 이용 가능 합니다.");
			resultView.addObject("actUrl", "/login.do");

			return resultView;
		}

		String mbrNm = StringUtils.defaultIfEmpty((String)reqMap.get("mbrNm"), "").trim();					// 성명
		String gendCd = StringUtils.defaultIfEmpty((String)reqMap.get("gendCd"), "");						// 성별
		String frgCd = StringUtils.defaultIfEmpty((String)reqMap.get("frgCd"), "");							// 내/외국인
		String juminNo1 = StringUtils.defaultIfEmpty((String)reqMap.get("juminNo1"), "");					// 생년월일
		String age = StringUtils.defaultIfEmpty((String)reqMap.get("age"), "");								// 연령
		String telNo1 = StringUtils.defaultIfEmpty((String)reqMap.get("telNo1"), "");						// 연락처
		String telNo2 = StringUtils.defaultIfEmpty((String)reqMap.get("telNo2"), "");						// 연락처
		String telNo3 = StringUtils.defaultIfEmpty((String)reqMap.get("telNo3"), "");						// 연락처
		String zipCd = StringUtils.defaultIfEmpty((String)reqMap.get("zipCd"), "");							// 우편번호
		String addr1 = StringUtils.defaultIfEmpty((String)reqMap.get("addr1"), "");							// 주소
		String mbrTpCd = StringUtils.defaultIfEmpty((String)reqMap.get("mbrTpCd"), "");						// 회원구분
		String drgUseCd = StringUtils.defaultIfEmpty((String)reqMap.get("drgUseCd"), "");					// 약물사용자와의 관계
		String medicCareCd = StringUtils.defaultIfEmpty((String)reqMap.get("medicCareCd"), "");				// 의료보장
		String mngUsrId = StringUtils.defaultIfEmpty((String)reqMap.get("mngUsrId"), "");					// 사례관리자
		String regDt = StringUtils.defaultIfEmpty((String)reqMap.get("regDt"), "").replaceAll("-", "");		// 최초등록일자
		if("".equals(mbrNm)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");

			return resultView;
		}
		if("".equals(gendCd)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");

			return resultView;
		}
		if("".equals(frgCd)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");

			return resultView;
		}
		if("".equals(juminNo1)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");

			return resultView;
		}
		if("".equals(age)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");

			return resultView;
		}
		if("".equals(telNo1)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");

			return resultView;
		}
		if("".equals(telNo2)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");

			return resultView;
		}
		if("".equals(telNo3)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");

			return resultView;
		}
		if("".equals(zipCd)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");

//			return resultView;
		}
		if("".equals(addr1)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");

//			return resultView;
		}
		if("".equals(mbrTpCd)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");

			return resultView;
		}
		if("".equals(drgUseCd)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");

			return resultView;
		}
		if("".equals(medicCareCd)) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");

			return resultView;
		}
		if("".equals(mngUsrId)) {
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
		reqMap.put("creId", StringUtils.defaultString((String)usrInfo.get("USR_ID"), ""));

		memberService.saveMstMbr(reqMap);

		return resultView;
	}
}

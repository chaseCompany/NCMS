package kr.co.chase.nrds.eduCounsel.web;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import kr.co.chase.ncms.common.ConstantObject;
import kr.co.chase.ncms.common.service.SysCodeService;
import kr.co.chase.nrds.client.service.ClientService;

@Controller
@RequestMapping(value="/nrds")
public class EduCounselController {
	@Resource(name="sysCodeService")
	private SysCodeService sysCodeService;

	@Resource(name="clientService")
	private ClientService clientService;

	@RequestMapping(value="/eduCounselMain.do")
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

		return "nrds/eduCounsel/eduCounselMain";
	}

	/**
	 * 회원 정보 저장
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxEduCounsel.do")
	public @ResponseBody ModelAndView ajaxMstMbrAdd(final MultipartHttpServletRequest multiRequest, @RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView("jsonView");

		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);
		String loginUsrId = StringUtils.defaultString((String)usrInfo.get("USR_ID"), "");

		reqMap.put("loginId", loginUsrId);

		HashMap<String, Object> resultMap = null;//clientService.insertEdMbr(reqMap);
		if(resultMap != null) {
			resultView.addObject("err", resultMap.get("err"));
			resultView.addObject("MSG", resultMap.get("MSG"));
			resultView.addObject("mbrNo", resultMap.get("mbrNo"));
		}

		return resultView;
	}
}
package kr.co.chase.ncms.counsel.web;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.chase.ncms.common.ConstantObject;
import kr.co.chase.ncms.common.service.SysCodeService;
import kr.co.chase.ncms.vo.CslRcpVO;

@Controller
public class CounselController {
	@Resource(name="sysCodeService")
	private SysCodeService sysCodeService;

	@RequestMapping(value="/counselMain.do")
	public String counselMain(HttpSession session, @ModelAttribute("cslRcpVO") CslRcpVO cslRcpVO, ModelMap model) throws Exception{
		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);

		if(usrInfo == null || StringUtils.defaultString((String)usrInfo.get("USR_ID"), "") == "") {
			return "redirect:/login.do";
		}

		HashMap<String, Object> codeListMap = new HashMap<String, Object>();
		codeListMap.put("useYn", ConstantObject.Y);

		cslRcpVO.setCslId(StringUtils.defaultString((String)usrInfo.get("USR_ID"), ""));
		cslRcpVO.setCslNm(StringUtils.defaultString((String)usrInfo.get("USR_NM"), ""));
		cslRcpVO.setIfpGbCd("10");
		cslRcpVO.setIfpGendCd("M");
		cslRcpVO.setTgpFrgCd("LO");
		cslRcpVO.setTgpGendCd("M");
		model.put("cslRcpInfo", cslRcpVO);

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
}
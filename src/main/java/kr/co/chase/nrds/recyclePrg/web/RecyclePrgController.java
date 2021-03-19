package kr.co.chase.nrds.recyclePrg.web;

import java.util.Enumeration;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.chase.ncms.common.ConstantObject;
import kr.co.chase.ncms.common.service.SysCodeService;
import kr.co.chase.ncms.login.service.LoginService;
import kr.co.chase.nrds.recyclePrg.service.RecyclePrgService;

@Controller
public class RecyclePrgController {
	@Resource(name = "sysCodeService")
	private SysCodeService sysCodeService;
	
	@Resource(name = "loginService")
	private LoginService loginService;
	
	@Resource(name = "RecyclePrgService")
	private RecyclePrgService recyclePrgService;
	
	/**
	 * 주간 프로그램 메인 페이지
	 * @param model
	 * @param cslRcpVO
	 * @param session
	 * @return
	 * @throws Exception
	 */
	
	@RequestMapping(value="/nrds/recyclePrgMain.do")
	public String recyclePrgMain(ModelMap model, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		@SuppressWarnings("unchecked")
		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);

		if(usrInfo == null || StringUtils.defaultIfEmpty((String)usrInfo.get("USR_ID"), "") == "") {
			return "redirect:/login.do";
		}
		Enumeration<?> paramsName= request.getParameterNames();
		HashMap<String, Object> map = new HashMap<String, Object>();
		while (paramsName.hasMoreElements()) {
			String name =  (String) paramsName.nextElement();
			System.out.println("name: "+name +"   &&   parameter:" + request.getParameter(name));
			map.put(name, request.getParameter(name));
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
		model.put("sysMbrList", loginService.getSysUsrList(paramMap));
		model.put("edPrmList", recyclePrgService.selectEdPrmList(map));
		return "nrds/recyclePrg/recyclePrgMain";
	}
	
}

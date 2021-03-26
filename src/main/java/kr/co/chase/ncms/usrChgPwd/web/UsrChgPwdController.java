package kr.co.chase.ncms.usrChgPwd.web;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.chase.ncms.adminUsr.service.AdminUsrService;
import kr.co.chase.ncms.common.ConstantObject;
import kr.co.chase.ncms.common.service.SysCodeService;
import kr.co.chase.ncms.login.service.LoginService;
import kr.co.chase.ncms.usrChgPwd.service.UsrChgPwdService;
import kr.co.chase.ncms.vo.SysUsrVO;

@Controller
public class UsrChgPwdController {

	@Resource(name="usrChgPwdService")
	private UsrChgPwdService usrChgPwdService;

	@Resource(name="loginService")
	private LoginService loginService;

	private Logger LOGGER = LoggerFactory.getLogger(UsrChgPwdController.class);

	/**
	 * 유저 비밀번호 변경화면
	 * @param model
	 * @param 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/usrChgPwd.do")
	public String adminUsrMain(ModelMap model, @ModelAttribute("sysUsrVO") SysUsrVO sysUsrVO, HttpSession session) throws Exception{

		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);

		if(usrInfo == null || StringUtils.defaultIfEmpty((String)usrInfo.get("USR_ID"), "") == "") {
			return "redirect:/login.do";
		}
		
		return "userChgPwd/userChgPwd";
	}

	/**
	 * 유저 비밀번호 변경처리
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxUsrChgPwd.do")
	public @ResponseBody ModelAndView ajaxUsrChgPwd(@RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView ("jsonView");
		boolean flag = true;

		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);

		if(usrInfo == null || StringUtils.defaultString((String)usrInfo.get("USR_ID"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "로그인 후 이용 가능 합니다.");
			resultView.addObject("actUrl", "/login.do");

			return resultView;
		}
		
		if(StringUtils.defaultString((String)reqMap.get("currPwd"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "기존 비밀번호를 입력하세요.");

			flag = false;
		}
		if(StringUtils.defaultString((String)reqMap.get("newPwd1"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "신규 비밀번호를 입력하세요.");

			flag = false;
		}
		if(StringUtils.defaultString((String)reqMap.get("newPwd2"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "신규 비밀번호 확인을 입력하세요.");

			flag = false;
		}

		// 기존 비밀 번호 동일여부 확인
		HashMap<String, Object> currMap = new HashMap<String, Object>();
		
		String usrId = StringUtils.defaultString((String)usrInfo.get("USR_ID"), "");
		String passwd = StringUtils.defaultString((String)reqMap.get("currPwd"), "");
		
		currMap.put("usrId", usrId);
		currMap.put("passwd", passwd);

		HashMap<String, Object> usrInfoMap = loginService.getSysUsrInfo(currMap);

		if(null == usrInfoMap) {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "기존 비밀번호가 동일하지 않습니다.");
			flag = false;
		}
		
		if(flag){
			
			HashMap<String, Object> newMap = new HashMap<String, Object>();
			
			newMap.put("usrId", StringUtils.defaultString((String)usrInfo.get("USR_ID"), ""));
			newMap.put("passWd", StringUtils.defaultString((String)reqMap.get("newPwd1"), ""));
			newMap.put("updId", StringUtils.defaultString((String)usrInfo.get("USR_ID"), ""));

			usrChgPwdService.updateNewUsrPwd(newMap);
		}

		return resultView;
	}

}
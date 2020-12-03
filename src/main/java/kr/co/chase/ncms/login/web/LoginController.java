package kr.co.chase.ncms.login.web;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.chase.ncms.common.ConstantObject;
import kr.co.chase.ncms.login.service.LoginService;

@Controller
public class LoginController {
	@Resource(name="loginService")
	private LoginService loginService;

	/**
	 * 로그인 페이지 호출
	 * @param param
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/login.do")
	public String login(HttpSession session) throws Exception{
		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);

		if(usrInfo != null && StringUtils.defaultString((String)usrInfo.get("USR_ID"), "") != "") {
			return "redirect:/counselMain.do";
		}

		return "login";
	}

	/**
	 * 로그인 처리
	 * @param reqMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxLogin.do")
	public @ResponseBody ModelAndView ajaxLogin(@RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView ("jsonView");

		String usrId = StringUtils.defaultString((String)reqMap.get("usrId"), "");
		String passwd = StringUtils.defaultString((String)reqMap.get("passwd"), "");

		if(usrId == "" || passwd == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "사용자 ID 또는 비밀번호를 입력하세요.");
		}else{
			HashMap<String, Object> usrInfoMap = loginService.getSysUsrInfo(reqMap);

			if(usrInfoMap != null) {
				session.setAttribute(ConstantObject.LOGIN_SESSEION_INFO, usrInfoMap);

				resultView.addObject("err", "N");
				resultView.addObject("usrInfo", usrInfoMap);
			}else{
				resultView.addObject("err", "Y");
				resultView.addObject("MSG", "사용자 ID가 존재하지 않거나 비밀번호가 맞지 않습니다.");
			}
		}

		return resultView;
	}

	/**
	 * 로그아웃 처리
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("logout.do")
	public String logout(HttpSession session) throws Exception{
		session.invalidate();

		return "redirect:/login.do";
	}
}
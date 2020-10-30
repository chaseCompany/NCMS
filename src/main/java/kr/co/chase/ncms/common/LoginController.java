package kr.co.chase.ncms.common;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.chase.ncms.common.service.SysCodeService;

@Controller
public class LoginController {
	@Resource(name="sysCodeService")
	private SysCodeService sysCodeService;

	/**
	 * 로그인 페이지 호출
	 * @param param
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/login.do")
	public String login() throws Exception{
		return "login";
	}

	@RequestMapping(value="/ajaxLogin.do")
	public @ResponseBody ModelAndView ajaxLogin(@RequestParam HashMap<String, Object> reqMap) throws Exception{
		ModelAndView resultView = new ModelAndView ("jsonView"); 

		String usrId = StringUtils.defaultString((String)reqMap.get("usrId"), "");
		String passwd = StringUtils.defaultString((String)reqMap.get("passwd"), "");

		reqMap.put("grpCd", "C1000");
		reqMap.put("useYn", "Y");
		List<HashMap<String, Object>> resultList = sysCodeService.getSysCdList(reqMap);

		resultView.addObject("usrId", usrId);
		resultView.addObject("passwd", passwd);

		return resultView;
	}
}
package kr.co.chase.ncms.individual.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.chase.ncms.vo.CslRcpVO;

@Controller
public class IndividualController {
	@RequestMapping(value="/individualMain.do")
	public String individualMain(ModelMap model, @ModelAttribute("cslRcpVO") CslRcpVO cslRcpVO, HttpSession session) throws Exception{
		return "individual/individualMain";
	}
}

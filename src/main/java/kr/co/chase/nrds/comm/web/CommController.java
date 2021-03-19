package kr.co.chase.nrds.comm.web;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.chase.ncms.common.ConstantObject;
import kr.co.chase.ncms.common.service.SysCodeService;

@Controller
public class CommController {
	@Resource(name = "sysCodeService")
	private SysCodeService sysCodeService;
	
	/**
	 * 그룹코드에 해당하는 코드 목록 조회
	 * @param model
	 * @param cslRcpVO
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/nrds/SysCdList.do")
	@ResponseBody
	public ModelAndView sysCdList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav= new ModelAndView("jsonView");
		String grpCd= request.getParameter("grpCd");

		HashMap<String, Object> codeListMap = new HashMap<String, Object>();
		codeListMap.put("useYn", ConstantObject.Y);

		codeListMap.put("grpCd", grpCd);
		List<HashMap<String, Object>> resultList = sysCodeService.getSysCdList(codeListMap);
		
		mav.addObject("RESULT_LIST", resultList);
		return mav;
	}
}

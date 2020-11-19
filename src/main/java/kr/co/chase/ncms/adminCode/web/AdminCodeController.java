package kr.co.chase.ncms.adminCode.web;

import java.util.HashMap;
import java.util.List;

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

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.co.chase.ncms.adminCode.service.AdminCodeService;
import kr.co.chase.ncms.adminCode.service.impl.AdminCodeServiceImpl;
import kr.co.chase.ncms.common.ConstantObject;
import kr.co.chase.ncms.common.service.SysCodeService;
import kr.co.chase.ncms.counsel.service.impl.CounselServiceImpl;
import kr.co.chase.ncms.vo.SysCdVO;

@Controller
public class AdminCodeController {

	@Resource(name="adminCodeService")
	private AdminCodeService adminCodeService;
	
	private Logger LOGGER = LoggerFactory.getLogger(AdminCodeController.class);

	/**
	 * @param model
	 * @param sysCdVO
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/adminCode.do")
	public String adminCodeMain(ModelMap model, @ModelAttribute("sysCodeVO") SysCdVO sysCdVO, HttpSession session) throws Exception{
		
		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);

		if(usrInfo == null || StringUtils.defaultString((String)usrInfo.get("USR_ID"), "") == "") {
			return "redirect:/login.do";
		}

		HashMap<String, Object> codeListMap = new HashMap<String, Object>();
		codeListMap.put("grpCd", "C0000");
		
		model.put("grpCdList", adminCodeService.getSysCdAdminGroupTotalList(codeListMap));

		return "adminCode/adminCode";
	}

	/**
	 * 코드 목록 조회
	 * @param cdId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxAdminCodeList.do")
	public @ResponseBody ModelAndView ajaxCodeList(@RequestParam String cdId, HttpSession session) throws Exception{

		ModelAndView resultView = new ModelAndView("jsonView");

		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);

		if(usrInfo == null || StringUtils.defaultString((String)usrInfo.get("USR_ID"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "로그인 후 이용 가능 합니다.");
			resultView.addObject("actUrl", "/login.do");
			return resultView;
		}

		if(StringUtils.defaultString(cdId, "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "코드를 선택해 주세요.");

			return resultView;
		}

		resultView.addObject("codeList", adminCodeService.getSysCdAdminGroupCdList(cdId));

		return resultView;
	}

	/**
	 * 코드 수정 처리
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxCodeUpdate.do")
	public @ResponseBody ModelAndView ajaxCodeUpdate(@RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView ("jsonView");
		boolean flag = true;

		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);

		if(usrInfo == null || StringUtils.defaultString((String)usrInfo.get("USR_ID"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "로그인 후 이용 가능 합니다.");
			resultView.addObject("actUrl", "/login.do");

			return resultView;
		}
		
		if(StringUtils.defaultString((String)reqMap.get("grpCd"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "그룹 CD 오류입니다.");

			flag = false;
		}
		if(StringUtils.defaultString((String)reqMap.get("cdId"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "그룹 ID를 입력하세요.");

			flag = false;
		}
		if(StringUtils.defaultString((String)reqMap.get("cdNm"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "그룹명을 입력하세요.");

			flag = false;
		}
		if(StringUtils.defaultString((String)reqMap.get("dpSeq"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "보기 순서를 입력하세요.");

			flag = false;
		}
		if(StringUtils.defaultString((String)reqMap.get("useYn"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "사용여부를 선택하세요.");

			flag = false;
		}

		if(flag){
			reqMap.put("updId", StringUtils.defaultString((String)usrInfo.get("USR_ID"), ""));

			adminCodeService.updateSysCdAdmin(reqMap);
		}

		return resultView;
	}

	/**
	 * 코드 목록 검색
	 * @param map
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxAdminCodeGroupSearch.do")
	public @ResponseBody ModelAndView ajaxAdminCodeGroupSearch(ModelMap model, @RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{

		ModelAndView resultView = new ModelAndView ("jsonView");

		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);

		if(usrInfo == null || StringUtils.defaultString((String)usrInfo.get("USR_ID"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "로그인 후 이용 가능 합니다.");
			resultView.addObject("actUrl", "/login.do");

			return resultView;
		}

		String groupCdSearchID = StringUtils.defaultIfEmpty((String)reqMap.get("groupCdSearchID"), "");
		String groupCdSearchName = StringUtils.defaultIfEmpty((String)reqMap.get("groupCdSearchName"), "");
		model.put("grpCd", "C0000");
		model.put("cdId", groupCdSearchID);
		model.put("cdNm", groupCdSearchName);

		resultView.addObject("groupSearchList", adminCodeService.getSysGroupCdSearchList(model));

		return resultView;
	}

}
package kr.co.chase.ncms.adminUsr.web;

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
import kr.co.chase.ncms.vo.SysUsrVO;

@Controller
public class AdminUsrController {

	@Resource(name="sysCodeService")
	private SysCodeService sysCodeService;

	@Resource(name="adminUsrService")
	private AdminUsrService adminUsrService;

	private Logger LOGGER = LoggerFactory.getLogger(AdminUsrController.class);

	/**
	 * 일반상담 등록화면
	 * @param model
	 * @param cslRcpVO
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/adminUsr.do")
	public String adminUsrMain(ModelMap model, @ModelAttribute("sysUsrVO") SysUsrVO sysUsrVO, HttpSession session) throws Exception{
		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);

		if(usrInfo == null || StringUtils.defaultString((String)usrInfo.get("USR_ID"), "") == "") {
			return "redirect:/login.do";
		}

		HashMap<String, Object> userListMap = new HashMap<String, Object>();

		HashMap<String, Object> codeListMap = new HashMap<String, Object>();
		codeListMap.put("useYn", ConstantObject.Y);

		codeListMap.put("grpCd", "C3500");				// 전국 지부
		model.put("C3500", sysCodeService.getSysCdList(codeListMap));
		
		codeListMap.put("grpCd", "C3600");				// 사용자 권한
		model.put("C3600", sysCodeService.getSysCdList(codeListMap));

		model.put("usrList", adminUsrService.getAdminUsrList(userListMap));

		return "adminUsr/adminUsr";
	}

	/**
	 * 개별 유저 조회
	 * @param usrId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxUsrView.do")
	public @ResponseBody ModelAndView ajaxUsrView(@RequestParam String usrId, HttpSession session) throws Exception{

		ModelAndView resultView = new ModelAndView("jsonView");

		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);

		if(usrInfo == null || StringUtils.defaultString((String)usrInfo.get("USR_ID"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "로그인 후 이용 가능 합니다.");
			resultView.addObject("actUrl", "/login.do");
			return resultView;
		}

		if(StringUtils.defaultString(usrId, "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "사용자를 선택해 주세요.");

			return resultView;
		}

		resultView.addObject("usrView", adminUsrService.getAdminUsrView(usrId));

		return resultView;
	}

	/**
	 * 유저 수정 처리
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxUsrUpdate.do")
	public @ResponseBody ModelAndView ajaxUsrUpdate(@RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView ("jsonView");
		boolean flag = true;

		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);

		if(usrInfo == null || StringUtils.defaultString((String)usrInfo.get("USR_ID"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "로그인 후 이용 가능 합니다.");
			resultView.addObject("actUrl", "/login.do");

			return resultView;
		}
		
		if(StringUtils.defaultString((String)reqMap.get("usrNm"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "사용자 이름을 입력하세요.");

			flag = false;
		}
		if(StringUtils.defaultString((String)reqMap.get("siteCd"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "소속 본부를 입력하세요.");

			flag = false;
		}
		if(StringUtils.defaultString((String)reqMap.get("roleCd"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "사용자 권한을 입력하세요.");

			flag = false;
		}
		if(StringUtils.defaultString((String)reqMap.get("useYn"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "사용 여부를 입력하세요.");

			flag = false;
		}

		if(flag){
			reqMap.put("updId", StringUtils.defaultString((String)usrInfo.get("USR_ID"), ""));

			adminUsrService.updateSysUsrAdmin(reqMap);
		}

		return resultView;
	}

	/**
	 * 유저 목록 검색
	 * @param map
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxAdminUsrSearch.do")
	public @ResponseBody ModelAndView ajaxAdminUsrSearch(ModelMap model, @RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{

		ModelAndView resultView = new ModelAndView ("jsonView");

		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);

		if(usrInfo == null || StringUtils.defaultString((String)usrInfo.get("USR_ID"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "로그인 후 이용 가능 합니다.");
			resultView.addObject("actUrl", "/login.do");

			return resultView;
		}

		String searchUsrId = StringUtils.defaultIfEmpty((String)reqMap.get("searchUsrId"), "");
		String searchUsrNm = StringUtils.defaultIfEmpty((String)reqMap.get("searchUsrNm"), "");
		String searchRoleCd = StringUtils.defaultIfEmpty((String)reqMap.get("searchRoleCd"), "");
		String searchSiteCd = StringUtils.defaultIfEmpty((String)reqMap.get("searchSiteCd"), "");
		String searchUseYn = StringUtils.defaultIfEmpty((String)reqMap.get("searchUseYn"), "");
		model.put("usrId", searchUsrId);
		model.put("usrNm", searchUsrNm);
		if (searchRoleCd.equals("0")) {
			searchRoleCd = "";
		}
		model.put("roleCd", searchRoleCd);
		if (searchSiteCd.equals("0")) {
			searchSiteCd = "";
		}
		model.put("siteCd", searchSiteCd);
		if (searchUseYn.equals("0")) {
			searchUseYn = "";
		}
		model.put("useYn", searchUseYn);
		
		resultView.addObject("usrSearchList", adminUsrService.getSysUsrSearchList(model));

		return resultView;
	}

	/**
	 * 유저 비밀번호 초기화
	 * @param usrId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxResetPwd.do")
	public @ResponseBody ModelAndView ajaxResetPwd(@RequestParam String usrId, HttpSession session) throws Exception{

		ModelAndView resultView = new ModelAndView ("jsonView");

		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);

		if(usrInfo == null || StringUtils.defaultString((String)usrInfo.get("USR_ID"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "로그인 후 이용 가능 합니다.");
			resultView.addObject("actUrl", "/login.do");
			return resultView;
		}

		if(StringUtils.defaultString(usrId, "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "사용자를 선택해 주세요.");

			return resultView;
		}

		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("usrId", usrId);
		map.put("updId", StringUtils.defaultString((String)usrInfo.get("USR_ID"), ""));
		map.put("passWd", "df4810@#");

		adminUsrService.updatePwdReset(map);

		return resultView;
	}

	/**
	 * 유저 삭제
	 * @param usrId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxDeleteSysUsr.do")
	public @ResponseBody ModelAndView ajaxDeleteSysUsr(@RequestParam String usrId, HttpSession session) throws Exception{

		ModelAndView resultView = new ModelAndView ("jsonView");

		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);

		if(usrInfo == null || StringUtils.defaultString((String)usrInfo.get("USR_ID"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "로그인 후 이용 가능 합니다.");
			resultView.addObject("actUrl", "/login.do");
			return resultView;
		}

		if(StringUtils.defaultString(usrId, "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "사용자를 선택해 주세요.");

			return resultView;
		}

		adminUsrService.deleteSysUsr(usrId);

		return resultView;
	}

	/**
	 * 신규 유저 등록
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxUsrInsert.do")
	public @ResponseBody ModelAndView ajaxUsrInsert(@RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView ("jsonView");
		boolean flag = true;
		
		LOGGER.debug("rm={}", reqMap.toString());
		
		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);

		if(usrInfo == null || StringUtils.defaultString((String)usrInfo.get("USR_ID"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "로그인 후 이용 가능 합니다.");
			resultView.addObject("actUrl", "/login.do");

			return resultView;
		}
		
		if(StringUtils.defaultString((String)reqMap.get("usrNm"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "사용자 이름을 입력하세요.");

			flag = false;
		}
		if(StringUtils.defaultString((String)reqMap.get("siteCd"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "소속 본부를 입력하세요.");

			flag = false;
		}
		if(StringUtils.defaultString((String)reqMap.get("roleCd"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "사용자 권한을 입력하세요.");

			flag = false;
		}
		if(StringUtils.defaultString((String)reqMap.get("useYn"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "사용 여부를 입력하세요.");

			flag = false;
		}
		
		// 지부 아이디중 최대값 찾기
		String siteCd = (String)reqMap.get("siteCd");
		HashMap<String, Object> maxMap = adminUsrService.findMaxSiteUserId(siteCd);
		
		String usrId = null;
		
		// 해당 지부에 최초 유저일 경우
		if (null == maxMap) {
			usrId = "u" + siteCd + "001";
		}
		// 해당 지부에 유저가 있을 경우
		else {
			String dbMaxId = (String) maxMap.get("USR_ID");
			int maxNo = Integer.parseInt(dbMaxId.substring(4));
			maxNo ++;
			
			String maxId = String.format("%03d", maxNo);
			
			usrId = "u" + siteCd + maxId;
		}
		
		if(flag){
			reqMap.put("usrId", usrId);
			reqMap.put("passWd", "df4810@#");
			reqMap.put("creId", StringUtils.defaultString((String)usrInfo.get("USR_ID"), ""));
			reqMap.put("updId", StringUtils.defaultString((String)usrInfo.get("USR_ID"), ""));

			adminUsrService.insertSysUsr(reqMap);
		}

		return resultView;
	}
}
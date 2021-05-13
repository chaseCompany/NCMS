package kr.co.chase.nrds.eduCounsel.web;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import kr.co.chase.ncms.common.ConstantObject;
import kr.co.chase.ncms.common.service.SysCodeService;
import kr.co.chase.ncms.common.util.FileManagerUtil;
import kr.co.chase.nrds.eduCounsel.service.EduCounselService;

@Controller
@RequestMapping(value="/nrds")
public class EduCounselController {
	
	@Resource(name="sysCodeService")
	private SysCodeService sysCodeService;

	@Resource(name="eduCounselService")
	private EduCounselService eduCounselService;

	@Resource(name = "FileManagerUtil")
	private FileManagerUtil fileUtil;
	
	@RequestMapping(value="/eduCounselMain.do")
	public String mentalityMain(ModelMap model, @RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);
		
		HashMap<String, Object> codeListMap = new HashMap<String, Object>();
		codeListMap.put("useYn", ConstantObject.Y);

		codeListMap.put("grpCd", "C2500");				// 상담대상
		model.put("cslTgtCdList", sysCodeService.getSysCdList(codeListMap));
		
		codeListMap.put("grpCd", "C8800");				// 상담구분
		model.put("cslClsCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C1600");				// 상담유형
		model.put("cslTpCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C2100");				// Rating A: 위험성
		model.put("rskaTpList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C2200");				// Rating B: 지지체계
		model.put("rskbTpList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C2300");				// Rating C: 협조능력
		model.put("rskcTpList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5600");				// URS
		model.put("ursCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5700");				// 치료력
		model.put("cureCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5800");				// 선택
		model.put("selCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C5900");				// 시도횟수
		model.put("actCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C6000");				// 시도방법
		model.put("actWayCdList", sysCodeService.getSysCdList(codeListMap));

		// 기본값 셋팅
		model.put("cslTgtCd", "10");
		model.put("cslTpCd", "20");

		return "nrds/eduCounsel/eduCounselMain";
	}

	/**
	 * 집중 상담 이력 조회
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getClsCureList.do")
	public @ResponseBody ModelAndView getClsCureList(@RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView("jsonView");

		if(StringUtils.defaultString((String)reqMap.get("mbrNo"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "회원번호를 선택하세요.");

			return resultView;
		}

		resultView.addObject("clsCureList", eduCounselService.getCslCureList(reqMap));

		return resultView;
	}

	/**
	 * 집중상담 내역 삭제
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxClsCureDel.do")
	public @ResponseBody ModelAndView ajaxClsCureDel(@RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView("jsonView");

		if(StringUtils.defaultString(reqMap.get("cslNo").toString(), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");

			return resultView;
		}

		int resultNum = eduCounselService.deleteCslCure(StringUtils.defaultString(reqMap.get("cslNo").toString(), ""));
		if(resultNum <= 0) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "삭제 오류");
		}

		return resultView;
	}

	/**
	 * 집중상담 내용 등록
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="ajaxClsCureAdd.do")
	public @ResponseBody ModelAndView ajaxClsCureAdd(final MultipartHttpServletRequest multiRequest, @RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView ("jsonView");

		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);

		if(StringUtils.defaultString((String)reqMap.get("mbrNo"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}
		if(StringUtils.defaultString((String)reqMap.get("cslDt"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}
		if(StringUtils.defaultString((String)reqMap.get("cslFmTm"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}
		if(StringUtils.defaultString((String)reqMap.get("cslToTm"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}
		if(StringUtils.defaultString((String)reqMap.get("cslSbj"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}
		if(StringUtils.defaultString((String)reqMap.get("rskaTpCd"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}
		if(StringUtils.defaultString((String)reqMap.get("rskbTpCd"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}
		if(StringUtils.defaultString((String)reqMap.get("rskcTpCd"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}
		if(StringUtils.defaultString((String)reqMap.get("ursCd"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}
		if(StringUtils.defaultString((String)reqMap.get("cslCtnt"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}
		if(StringUtils.defaultString((String)reqMap.get("cslRst"), "") == "") {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "필수정보 누락");
			return resultView;
		}

		String creId = StringUtils.defaultString((String)usrInfo.get("USR_ID"), "");
		reqMap.put("creDt", StringUtils.defaultIfEmpty((String)reqMap.get("creDt"), "").replaceAll("-", ""));
		reqMap.put("creId", creId);

		// 첨부 파일 정보
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		if (!files.isEmpty()) {
			boolean flag = fileUtil.checkFiles(files);

			if(flag) {
				HashMap<String, Object> fileMng = fileUtil.parseFileInf(files, "CURE", creId);
				if(!fileMng.isEmpty()) {
					List<HashMap<String, Object>> fileList = (List<HashMap<String, Object>>)fileMng.get("fileList");

					reqMap.put("fileList", fileList);
					reqMap.put("fileId", StringUtils.defaultIfEmpty((String)fileMng.get("fileId"), ""));
				}
			}
		}

		HashMap<String, Object> resMap = eduCounselService.cslCureAdd(reqMap);
		if(resMap != null) {
			resultView.addObject("err", resMap.get("err"));
			resultView.addObject("MSG", resMap.get("MSG"));
		}

		return resultView;
	}

	/**
	 * 집중 상담 상세 조회
	 * @param csNo
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxClsCureInfo.do")
	public @ResponseBody ModelAndView ajaxClsCureInfo(@RequestParam String cslNo, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView("jsonView");

		if(StringUtils.defaultString(cslNo, "").equals("")) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "상담번호를 입력하세요.");

			return resultView;
		}

		resultView.addObject("clsCureInfo", eduCounselService.getCslCure(cslNo));

		return resultView;
	}
	
	/**
	 * 교육상담 엑셀다운로드
	 * @param modelMap
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/eduCounselDownload.do")
	public String counselExelDownload(@RequestParam HashMap<String, Object> reqMap, Map<String, Object> modelMap, HttpServletResponse response) throws Exception {
		String title = "교육상담";
		String cslNo = StringUtils.defaultIfEmpty((String)reqMap.get("cslNo"), "");

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Pragma", "public");
		response.setHeader("Expires", "0");
		response.setHeader("Content-Disposition", "attachment; filename = " + URLEncoder.encode(title, "UTF-8") + "_" + cslNo + ".xlsx");
		modelMap.put("sheetName", title);

		HashMap<String, Object> cslInfo = eduCounselService.getCslCure(cslNo);

		modelMap.put("cslInfo", cslInfo);

		HashMap<String, Object> codeListMap = new HashMap<String, Object>();
		codeListMap.put("useYn", ConstantObject.Y);		
		
		return "EduCounselExcel";
	}
	
}
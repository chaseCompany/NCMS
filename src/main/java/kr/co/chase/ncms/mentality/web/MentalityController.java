package kr.co.chase.ncms.mentality.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
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
import kr.co.chase.ncms.mentality.service.MentalityService;

@Controller
public class MentalityController {
	@Resource(name="sysCodeService")
	private SysCodeService sysCodeService;

	@Resource(name="mentalityService")
	private MentalityService mentalityService;

	@Resource(name = "FileManagerUtil")
	private FileManagerUtil fileUtil;

	/**
	 * 심리치유 메인
	 * @param model
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/mentalityMain.do")
	public String mentalityMain(ModelMap model, @RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		HashMap<String, Object> codeListMap = new HashMap<String, Object>();
		codeListMap.put("useYn", ConstantObject.Y);

		codeListMap.put("grpCd", "C2500");				// 상담대상
		model.put("cslTgtCdList", sysCodeService.getSysCdList(codeListMap));

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

		return "mentality/mentalityMain";
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

		resultView.addObject("clsCureList", mentalityService.getCslCureList(reqMap));

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

		int resultNum = mentalityService.deleteCslCure(StringUtils.defaultString(reqMap.get("cslNo").toString(), ""));
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

		String cslId = StringUtils.defaultString((String)usrInfo.get("USR_ID"), "");
		reqMap.put("cslDt", StringUtils.defaultIfEmpty((String)reqMap.get("cslDt"), "").replaceAll("-", ""));
		reqMap.put("cslId", cslId);

		// 첨부 파일 정보
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		if (!files.isEmpty()) {
			boolean flag = fileUtil.checkFiles(files);

			if(flag) {
				HashMap<String, Object> fileMng = fileUtil.parseFileInf(files, "CURE", cslId);
				if(!fileMng.isEmpty()) {
					List<HashMap<String, Object>> fileList = (List<HashMap<String, Object>>)fileMng.get("fileList");

					reqMap.put("fileList", fileList);
					reqMap.put("fileId", StringUtils.defaultIfEmpty((String)fileMng.get("fileId"), ""));
				}
			}
		}

		HashMap<String, Object> resMap = mentalityService.cslCureAdd(reqMap);
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

		resultView.addObject("clsCureInfo", mentalityService.getCslCure(cslNo));

		return resultView;
	}
}
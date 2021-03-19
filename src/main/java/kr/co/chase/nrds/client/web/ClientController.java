package kr.co.chase.nrds.client.web;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.co.chase.ncms.common.ConstantObject;
import kr.co.chase.ncms.common.service.SysCodeService;
import kr.co.chase.nrds.client.service.ClientService;

@Controller
@RequestMapping(value="/nrds")
public class ClientController {
	@Resource(name="sysCodeService")
	private SysCodeService sysCodeService;

	@Resource(name="clientService")
	private ClientService clientService;

	@RequestMapping(value="/clientMain.do")
	public String clientMain(ModelMap model, @RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		HashMap<String, Object> codeListMap = new HashMap<String, Object>();
		codeListMap.put("useYn", ConstantObject.Y);

		codeListMap.put("grpCd", "C1100");				// 성별
		model.put("gendCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C1300");				// 내/외국인
		model.put("frgCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C2800");				// 결혼여부
		model.put("mrgCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C8000");				// 최종학력
		model.put("eduCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C7900");				// 학력결과
		model.put("edu02CdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C3000");				// 종교
		model.put("rlgnCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C1200");				// 직업
		model.put("jobCdList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C1800");				// 최초사용약물
		model.put("fstDrugList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C1900");				// 주요사용약물
		model.put("mainDrugList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C2600");				// 사용빈도
		model.put("useFrqList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C4100");				// 사용원인
		model.put("useCauList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C3200");				// 약물관련 법적문제
		model.put("lawPbmList", sysCodeService.getSysCdList(codeListMap));

		return "nrds/client/clientMain";
	}

	/**
	 * 회원 정보 저장
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxEdMbrAdd.do")
	public @ResponseBody ModelAndView ajaxEdMbrAdd(@RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView("jsonView");

		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);
		String loginUsrId = StringUtils.defaultString((String)usrInfo.get("USR_ID"), "");

		reqMap.put("loginId", loginUsrId);

		HashMap<String, Object> resultMap = clientService.saveEdMbr(reqMap);
		if(resultMap != null) {
			resultView.addObject("err", resultMap.get("err"));
			resultView.addObject("MSG", resultMap.get("MSG"));
			resultView.addObject("mbrNo", resultMap.get("mbrNo"));
		}

		return resultView;
	}

	/**
	 * 회원 목록 조회
	 * @param model
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxEdMbrList.do")
	public String ajaxEdMbrList(ModelMap model, @RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);
		String searchType = StringUtils.defaultIfEmpty((String)reqMap.get("searchType"), "");
		String currentPageNo = StringUtils.defaultIfEmpty((String)reqMap.get("pageNo"), "");
		String recordCountPerPage = StringUtils.defaultIfEmpty((String)reqMap.get("perPage"), ConstantObject.defaultRowSize);

		PaginationInfo paginginfo = new PaginationInfo();
		if(currentPageNo == "" || recordCountPerPage == ""){
			paginginfo.setCurrentPageNo(1);
			paginginfo.setPageSize(Integer.parseInt(ConstantObject.defaultPageSize));
			paginginfo.setRecordCountPerPage(Integer.parseInt(ConstantObject.defaultRowSize));
		}else{
			paginginfo.setCurrentPageNo(Integer.valueOf(currentPageNo));
			paginginfo.setPageSize(Integer.parseInt(ConstantObject.defaultPageSize));
			paginginfo.setRecordCountPerPage(Integer.valueOf(recordCountPerPage));
		}

		String mbrNm = StringUtils.defaultIfEmpty((String)reqMap.get("mbrNm"), "");
		String telNo = StringUtils.defaultIfEmpty((String)reqMap.get("telNo"), "").replaceAll("-", "");
		model.put("mbrNm", mbrNm);
		model.put("telNo", telNo);
		model.put("pageNo", currentPageNo);

		reqMap.put("currentPageNo", paginginfo.getCurrentPageNo());
		reqMap.put("recordCountPerPage", paginginfo.getRecordCountPerPage());

		// 관리자가 아닌 경우
		if(!ConstantObject.adminRoleCd.equals(StringUtils.defaultIfEmpty((String)usrInfo.get("ROLE_CD"), ""))) {
			if("S".equals(searchType)) {
				reqMap.put("searchSiteCd", StringUtils.defaultIfEmpty((String)usrInfo.get("SITE_CD"), "X"));
			}
		}

		int totalCount = clientService.getEdMbrListCount(reqMap);
		paginginfo.setTotalRecordCount(totalCount);

		model.put("totalCount", totalCount);
		model.put("paginationInfo", paginginfo);

		if(totalCount > 0) {
			List<HashMap<String, Object>> resultList = clientService.getEdMbrList(reqMap);
			model.put("resultList", resultList);
		}

		return "nrds/client/layer/mbrSearchLayer";
	}

	/**
	 * 회원 정보 상세 조회
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxMbrInfoJson.do")
	public @ResponseBody ModelAndView ajaxMbrInfoJson(@RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView("jsonView");
		String mbrNo = StringUtils.defaultIfEmpty((String)reqMap.get("mbrNo"), "");

		if(!"".equals(mbrNo)){
			resultView.addObject("mbrInfo", clientService.getEdMbrInfo(reqMap));
		}

		return resultView;
	}

	/**
	 * 회원 정보 삭제
	 * @param mbrNo
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxEdMbrDel.do")
	public @ResponseBody ModelAndView ajaxEdMbrDel(@RequestParam String mbrNo, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView("jsonView");

		int result = clientService.deleteEdMbr(mbrNo);
		if(result <= 0) {
			resultView.addObject("err", ConstantObject.Y);
			resultView.addObject("MSG", "삭제 처리 오류");
		}

		return resultView;
	}
}
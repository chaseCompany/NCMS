package kr.co.chase.ncms.counsel.web;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.co.chase.ncms.common.ConstantObject;
import kr.co.chase.ncms.common.service.SysCodeService;
import kr.co.chase.ncms.common.util.DateUtil;
import kr.co.chase.ncms.counsel.service.CounselService;
import kr.co.chase.ncms.vo.CslRcpVO;

@Controller
public class CounselController {
	@Resource(name="sysCodeService")
	private SysCodeService sysCodeService;

	@Resource(name="counselService")
	private CounselService counselService;

	/**
	 * �Ϲݻ�� ���ȭ��
	 * @param model
	 * @param cslRcpVO
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/counselMain.do")
	public String counselMain(ModelMap model, @ModelAttribute("cslRcpVO") CslRcpVO cslRcpVO, HttpSession session) throws Exception{
		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);

		if(usrInfo == null || StringUtils.defaultString((String)usrInfo.get("USR_ID"), "") == "") {
//			return "redirect:/login.do";
		}

		cslRcpVO.setCslId(StringUtils.defaultString((String)usrInfo.get("USR_ID"), ""));
		cslRcpVO.setCslNm(StringUtils.defaultString((String)usrInfo.get("USR_NM"), ""));
		cslRcpVO.setIfpGbCd("10");
		cslRcpVO.setIfpGendCd("M");
		cslRcpVO.setTgpFrgCd("LO");
		cslRcpVO.setTgpGendCd("M");
		model.put("cslRcpInfo", cslRcpVO);

		HashMap<String, Object> codeListMap = new HashMap<String, Object>();
		codeListMap.put("useYn", ConstantObject.Y);

		codeListMap.put("grpCd", "C1000");				// ����������/���ο���
		model.put("ifpGbList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C1200");				// ����
		model.put("jobList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C3400");				// ����
		model.put("areaList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C1400");				// ���������
		model.put("pathList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C1500");				// ��ȣ�ҹ���
		model.put("kndList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C1600");				// �������
		model.put("tpList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C1800");				// ���ʻ��๰
		model.put("fstDrugList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C1900");				// �ֿ���๰
		model.put("mainDrugList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C2000");				// �ֿ���ġ
		model.put("mjrMngList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C2100");				// Rating A: ���輺
		model.put("rskaTpList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C2200");				// Rating B: ����ü��
		model.put("rskbTpList", sysCodeService.getSysCdList(codeListMap));

		codeListMap.put("grpCd", "C2300");				// Rating C: �����ɷ�
		model.put("rskcTpList", sysCodeService.getSysCdList(codeListMap));

		return "counsel/counselMain";
	}

	/**
	 * �Ϲݻ�� ��� ó��
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxCounselAdd.do")
	public @ResponseBody ModelAndView ajaxCounselAdd(@RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView ("jsonView");
		boolean flag = true;

		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);

		if(usrInfo == null || StringUtils.defaultString((String)usrInfo.get("USR_ID"), "") == "") {
//			resultView.addObject("err", "Y");
//			resultView.addObject("MSG", "�α��� �� �̿� ���� �մϴ�.");
//			resultView.addObject("actUrl", "/login.do");

//			return resultView;
		}

		if(StringUtils.defaultString((String)reqMap.get("cslDt"), "") == "" || 
		   StringUtils.defaultString((String)reqMap.get("cslFmTm"), "") == "" || 
		   StringUtils.defaultString((String)reqMap.get("cslToTm"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "����Ͻø� �Է��ϼ���.");

			flag = false;
		}
		if(StringUtils.defaultString((String)reqMap.get("ifpGbCd"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "����������/���ο��θ� �����ϼ���.");

			flag = false;
		}
		if(StringUtils.defaultString((String)reqMap.get("ifpNm"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "���������� ���� �Է��ϼ���.");

			flag = false;
		}
		if(StringUtils.defaultString((String)reqMap.get("ifpNm"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "���������� ���� �Է��ϼ���.");

			flag = false;
		}
		if(StringUtils.defaultString((String)reqMap.get("ifpGendCd"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "���������� ������ �����ϼ���.");

			flag = false;
		}
		if(StringUtils.defaultString((String)reqMap.get("ifpAge"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "���������� ���ɸ� �Է��ϼ���.");

			flag = false;
		}
		if(StringUtils.defaultString((String)reqMap.get("ifpTelNo1"), "") == "" ||
		   StringUtils.defaultString((String)reqMap.get("ifpTelNo2"), "") == "" ||
		   StringUtils.defaultString((String)reqMap.get("ifpTelNo3"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "���������� ����ó�� �Է��ϼ���.");

			flag = false;
		}
		if(StringUtils.defaultString((String)reqMap.get("ifpJobCd"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "���������� ������ �����ϼ���.");

			flag = false;
		}
		if(StringUtils.defaultString((String)reqMap.get("ifpAreaCd"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "���������� ������ �����ϼ���.");

			flag = false;
		}else if(StringUtils.defaultString((String)reqMap.get("ifpAreaCd"), "") == "ZZZ"){
			if(StringUtils.defaultString((String)reqMap.get("ifpAreaEtc"), "") == "") {
				resultView.addObject("err", "Y");
				resultView.addObject("MSG", "���������� ������ �Է��ϼ���.");

				flag = false;
			}
		}
		if(StringUtils.defaultString((String)reqMap.get("tgpNm"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "����� ���� �Է��ϼ���.");

			flag = false;
		}
		if(StringUtils.defaultString((String)reqMap.get("tgpGendCd"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "����� ������ �����ϼ���.");

			flag = false;
		}
		if(StringUtils.defaultString((String)reqMap.get("tgpAge"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "����� ���ɸ� �Է��ϼ���.");

			flag = false;
		}
		if(StringUtils.defaultString((String)reqMap.get("tgpTelNo1"), "") == "" ||
		   StringUtils.defaultString((String)reqMap.get("tgpTelNo2"), "") == "" ||
		   StringUtils.defaultString((String)reqMap.get("tgpTelNo3"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "����� ����ó�� �Է��ϼ���.");

			flag = false;
		}
		if(StringUtils.defaultString((String)reqMap.get("tgpJobCd"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "����� ������ �����ϼ���.");

			flag = false;
		}
		if(StringUtils.defaultString((String)reqMap.get("tgpFrgCd"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "����� ��/�ܱ��� ���θ� �����ϼ���.");

			flag = false;
		}
		if(StringUtils.defaultString((String)reqMap.get("tgpAreaCd"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "����� ������ �����ϼ���.");

			flag = false;
		}else if(StringUtils.defaultString((String)reqMap.get("tgpAreaCd"), "") == "ZZZ"){
			if(StringUtils.defaultString((String)reqMap.get("tgpAreaEtc"), "") == "") {
				resultView.addObject("err", "Y");
				resultView.addObject("MSG", "����� ������ �Է��ϼ���.");

				flag = false;
			}
		}
		if(StringUtils.defaultString((String)reqMap.get("ifPathCd"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "��������θ� �����ϼ���.");
			
			flag = false;
		}
		if(StringUtils.defaultString((String)reqMap.get("pbmKndCd"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "��ȣ�ҹ����� �����ϼ���.");

			flag = false;
		}
		if(StringUtils.defaultString((String)reqMap.get("cslTpCd"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "��������� �����ϼ���.");

			flag = false;
		}
		if(StringUtils.defaultString((String)reqMap.get("fstDrugCd"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "���ʻ��๰�� �����ϼ���.");

			flag = false;
		}
		if(StringUtils.defaultString((String)reqMap.get("mainDrugCd"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "�ֿ���๰�� �����ϼ���.");

			flag = false;
		}
		if(StringUtils.defaultString((String)reqMap.get("mjrMngCd"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "�ֿ���ġ�� �����ϼ���.");

			flag = false;
		}
		if(StringUtils.defaultString((String)reqMap.get("rskSco"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "����з�ô�� ������ �Է��ϼ���.");

			flag = false;
		}
		if(StringUtils.defaultString((String)reqMap.get("cslCtnt"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "��㳻���� �Է��ϼ���.");

			flag = false;
		}

		if(flag){
			reqMap.put("cslId", StringUtils.defaultString((String)usrInfo.get("USR_ID"), ""));
			reqMap.put("cslNm", StringUtils.defaultString((String)usrInfo.get("USR_NM"), ""));

			counselService.counselAdd(reqMap);
		}

		return resultView;
	}

	/**
	 * �Ϲݻ�� ��� ��ȸ
	 * @param model
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getClsRcpList.do")
	public String getClsRcpList(ModelMap model, @RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		String currentPageNo = StringUtils.defaultString((String)reqMap.get("pageNo"), "1");
		String recordCountPerPage = StringUtils.defaultString((String)reqMap.get("perPage"), ConstantObject.defaultRowSize);

		PaginationInfo paginginfo = new PaginationInfo();
		if(currentPageNo == "" || recordCountPerPage == ""){
			paginginfo.setCurrentPageNo(1);
			paginginfo.setPageSize(Integer.parseInt(ConstantObject.defaultPageSize));
			paginginfo.setRecordCountPerPage(Integer.parseInt(ConstantObject.defaultRowSize));
		} else {
			paginginfo.setCurrentPageNo(Integer.valueOf(currentPageNo));
			paginginfo.setPageSize(Integer.parseInt(ConstantObject.defaultPageSize));
			paginginfo.setRecordCountPerPage(Integer.valueOf(recordCountPerPage));
		}

		String schStrCslDt = StringUtils.defaultIfEmpty((String)reqMap.get("schStrCslDt"), "");
		String schEndCslDt = StringUtils.defaultIfEmpty((String)reqMap.get("schEndCslDt"), "");
		String schMth = StringUtils.defaultIfEmpty((String)reqMap.get("schMth"), "");
		String schGb = StringUtils.defaultIfEmpty((String)reqMap.get("schGb"), "");
		String schNm = StringUtils.defaultIfEmpty((String)reqMap.get("schNm"), "");
		if(!"".equals(schStrCslDt)) {
			reqMap.put("schStrCslDt", schStrCslDt.replaceAll("-", ""));
		}else{
			reqMap.put("schStrCslDt", DateUtil.getFormattedDateMonthAdd(DateUtil.getToday("yyyyMMdd"), "yyyyMMdd", "yyyyMMdd", -3));
		}
		if(!"".equals(schEndCslDt)) {
			reqMap.put("schEndCslDt", schEndCslDt.replaceAll("-", ""));
		}else{
			reqMap.put("schEndCslDt", DateUtil.getToday("yyyyMMdd"));
		}

		model.put("schStrCslDt", reqMap.get("schStrCslDt"));
		model.put("schEndCslDt", reqMap.get("schEndCslDt"));
		model.put("schMth", schMth);
		model.put("schGb", schGb);
		model.put("schNm", schNm);
		model.put("pageNo", currentPageNo);

		reqMap.put("currentPageNo", paginginfo.getCurrentPageNo());
		reqMap.put("recordCountPerPage", paginginfo.getRecordCountPerPage());

		int totalCount = counselService.getCslRcpListCount(reqMap);
		paginginfo.setTotalRecordCount(totalCount);

		model.put("totalCount", totalCount);
		model.put("paginationInfo", paginginfo);

		if(totalCount > 0) {
			List<HashMap<String, Object>> resultList = counselService.getCslRcpList(reqMap);
			model.put("resultList", resultList);
		}

		return "counsel/layer/rcpListLayer";
	}

	/**
	 * ��㳻�� ����
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxClsRcpDel.do")
	public @ResponseBody ModelAndView ajaxClsRcpDel(@RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView("jsonView");

		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);

		if(usrInfo == null || StringUtils.defaultString((String)usrInfo.get("USR_ID"), "") == "") {
//			resultView.addObject("err", "Y");
//			resultView.addObject("MSG", "�α��� �� �̿� ���� �մϴ�.");
//			resultView.addObject("actUrl", "/login.do");

//			return resultView;
		}

		if(StringUtils.defaultString((String)reqMap.get("rcpNo"), "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "����ȣ�� �Է��ϼ���.");

			return resultView;
		}

		int result = counselService.deleteCslRcp(StringUtils.defaultString((String)reqMap.get("rcpNo"), ""));

		return resultView;
	}

	/**
	 * ��� ���� �� ��ȸ
	 * @param rcpNo
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxClsRcpInfo.do")
	public @ResponseBody ModelAndView ajaxClsRcpInfo(@RequestParam String rcpNo, HttpSession session) throws Exception{
		ModelAndView resultView = new ModelAndView("jsonView");
		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);

		if(usrInfo == null || StringUtils.defaultString((String)usrInfo.get("USR_ID"), "") == "") {
//			resultView.addObject("err", "Y");
//			resultView.addObject("MSG", "�α��� �� �̿� ���� �մϴ�.");
//			resultView.addObject("actUrl", "/login.do");

//			return resultView;
		}

		if(StringUtils.defaultString(rcpNo, "") == "") {
			resultView.addObject("err", "Y");
			resultView.addObject("MSG", "����ȣ�� �Է��ϼ���.");

			return resultView;
		}

		resultView.addObject("cslRcpInfo", counselService.getCslRcp(rcpNo));

		return resultView;
	}

	/**
	 * ȸ�� ��� ��ȸ
	 * @param model
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajaxMstMbrList.do")
	public String ajaxMstMbrList(ModelMap model, @RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		String currentPageNo = StringUtils.defaultString((String)reqMap.get("pageNo"), "");
		String recordCountPerPage = StringUtils.defaultString((String)reqMap.get("perPage"), ConstantObject.defaultRowSize);

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

		int totalCount = counselService.getMstMbrListCount(reqMap);
		paginginfo.setTotalRecordCount(totalCount);

		model.put("totalCount", totalCount);
		model.put("paginationInfo", paginginfo);

		if(totalCount > 0) {
			List<HashMap<String, Object>> resultList = counselService.getMstMbrList(reqMap);
			model.put("resultList", resultList);
		}

		return "counsel/layer/mbrSearchLayer";
	}
}
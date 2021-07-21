package kr.co.chase.nrds.report.web;

import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.chase.ncms.common.ConstantObject;
import kr.co.chase.nrds.eduCounsel.service.EduCounselService;
import kr.co.chase.nrds.recyclePrg.service.RecyclePrgService;

@Controller
public class nrdsReportController {
	
	@Resource(name = "RecyclePrgService")
	private RecyclePrgService recyclePrgService;
	
	@Resource(name="eduCounselService")
	private EduCounselService eduCounselService;
	
	/**
	 * 재범방지 통계 관리 메인
	 * @param model
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/nrds/reportMain.do")
	public String reportMain(ModelMap model, @RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		@SuppressWarnings("unchecked")
		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);

		if(usrInfo == null || StringUtils.defaultString((String)usrInfo.get("USR_ID"), "") == "") {
			return "redirect:/login.do";
		}

		return "nrds/report/reportMain";
	}
	
	/**
	 * 재범방지 통계 엑셀다운로드
	 * @param modelMap
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/nrds/statisticsExcelDownload.do")
	public String statisticsExcelDownload(@RequestParam HashMap<String, Object> reqMap, Map<String, Object> modelMap, HttpServletResponse response, HttpServletRequest request, HttpSession session) throws Exception {
		@SuppressWarnings("unchecked")
		HashMap<String, Object> usrInfo = (HashMap<String, Object>) session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);
		
		String excelTitle = StringUtils.defaultIfEmpty((String)reqMap.get("excelTitle"), "");
		String excelName = StringUtils.defaultIfEmpty((String)reqMap.get("excelName"), "");
		String fmDt = StringUtils.defaultIfEmpty((String)reqMap.get("fmDt"), "").replaceAll("-", "");
		String toDt = StringUtils.defaultIfEmpty((String)reqMap.get("toDt"), "").replaceAll("-", "");
		reqMap.put("fmDt", fmDt);
		reqMap.put("toDt", toDt);
		reqMap.put("searchRoleCd", StringUtils.defaultIfEmpty((String)usrInfo.get("ROLE_CD"), ""));
		reqMap.put("searchSiteCd", StringUtils.defaultIfEmpty((String)usrInfo.get("SITE_CD"), ""));
		reqMap.put("searchUserId", StringUtils.defaultIfEmpty((String)usrInfo.get("USR_ID"), ""));
		
		List<HashMap<String, Object>> resultMap = new ArrayList<HashMap<String, Object>>();
		if("RecycleStatisticsExcel".equals(excelName)) {
			// 재활교육프로그램 edPrg.selectEdPrmInfoMap 교육일시:PGM_START_DT
			reqMap.put("joinYn", "Y");
			resultMap = recyclePrgService.getEdPrmStatistics(reqMap);
		}else if("EduCounselStatisticsExcel".equals(excelName)) {
			// 교육상담 eduCounsel.getCslCure 상담일자:CSL_DT
			resultMap = eduCounselService.getCslCureStatistics(reqMap);
		}else if("EduMngStatisticsExcel".equals(excelName)) {
			// 교육관리 edPrg.selectEdPrmInfoMap 교육일시:PGM_START_DT
			resultMap = recyclePrgService.getEdPrmStatistics(reqMap);
		}
		
		if(resultMap.size() < 1) {
			response.setContentType("text/html; charset=UTF-8");			 
			PrintWriter out = response.getWriter();			
			out.println("<script>alert('조회된 데이터가 없습니다.'); window.history.back();</script>");
			out.flush();
			return "report/reportMain";
		}
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Pragma", "public");
		response.setHeader("Expires", "0");
		response.setHeader("Content-Disposition", "attachment; filename = " + URLEncoder.encode(excelTitle, "UTF-8").replaceAll("\\+", "%20") + "_" + fmDt + "-" + toDt + ".xlsx");
		modelMap.put("sheetName", excelTitle);

		modelMap.put("cslInfo", resultMap);
		modelMap.put("imagesPath", request.getServletContext().getRealPath("/images/excel_logo.png"));

		return excelName;
	}
}
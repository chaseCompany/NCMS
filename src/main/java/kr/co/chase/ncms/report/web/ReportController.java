package kr.co.chase.ncms.report.web;

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
import kr.co.chase.ncms.counsel.service.CounselService;
import kr.co.chase.ncms.individual.service.IndividualService;
import kr.co.chase.ncms.mentality.service.MentalityService;
import kr.co.chase.ncms.weeklyprg.service.WeeklyPrgService;

@Controller
public class ReportController {
	
	@Resource(name="counselService")
	private CounselService counselService;
	
	@Resource(name="individualService")
	private IndividualService individualService;
	
	@Resource(name="mentalityService")
	private MentalityService mentalityService;

	@Resource(name="weeklyPrgService")
	private WeeklyPrgService weeklyPrgService;
	
	/**
	 * 통계 관리 메인
	 * @param model
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/reportMain.do")
	public String reportMain(ModelMap model, @RequestParam HashMap<String, Object> reqMap, HttpSession session) throws Exception{
		@SuppressWarnings("unchecked")
		HashMap<String, Object> usrInfo = (HashMap<String, Object>)session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);

		if(usrInfo == null || StringUtils.defaultString((String)usrInfo.get("USR_ID"), "") == "") {
			return "redirect:/login.do";
		}

		return "report/reportMain";
	}
	
	/**
	 * 통계 엑셀다운로드
	 * @param modelMap
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/statisticsExcelDownload.do")
	public String statisticsExcelDownload(@RequestParam HashMap<String, Object> reqMap, Map<String, Object> modelMap, HttpServletResponse response, HttpServletRequest request, HttpSession session) throws Exception {
		@SuppressWarnings("unchecked")
		HashMap<String, Object> usrInfo = (HashMap<String, Object>) session.getAttribute(ConstantObject.LOGIN_SESSEION_INFO);
		
		String excelTitle = StringUtils.defaultIfEmpty((String)reqMap.get("excelTitle"), "");
		String excelName = StringUtils.defaultIfEmpty((String)reqMap.get("excelName"), "");
		String fmDt = StringUtils.defaultIfEmpty((String)reqMap.get("fmDt"), "").replaceAll("-", "");
		String toDt = StringUtils.defaultIfEmpty((String)reqMap.get("toDt"), "").replaceAll("-", "");
		reqMap.put("fmDt", fmDt);
		reqMap.put("toDt", toDt);
		reqMap.put("sessionRoleCd", StringUtils.defaultIfEmpty((String)usrInfo.get("ROLE_CD"), ""));
		reqMap.put("sessionSiteCd", StringUtils.defaultIfEmpty((String)usrInfo.get("SITE_CD"), ""));
		
		List<HashMap<String, Object>> resultMap = new ArrayList<HashMap<String, Object>>();
		if("CounselStatisticsExcel".equals(excelName)) {
			// 중독예방상담
			resultMap = counselService.getCslRcpStatistics(reqMap);
		}else if("IndividualStatisticsExcel".equals(excelName)) {
			// 사례관리
			resultMap = individualService.getCslIdvStatistics(reqMap);
		}else if("IspStatisticsExcel".equals(excelName)) {
			// ISP수립
			resultMap = individualService.getCslIspInfoStatistics(reqMap);
		}else if("CslAnmStatisticsExcel".equals(excelName)) {
			// 병력정보
			resultMap = individualService.getCslAnmInfoStatistics(reqMap);
		}else if("CureStatisticsExcel".equals(excelName)) {
			// 치료재활정보
			resultMap = individualService.getCslHealInfoStatistics(reqMap);
		}else if("MentalityStatisticsExcel".equals(excelName)) {
			// 심리상담
			resultMap = mentalityService.getCslCureStatistics(reqMap);
		}else if("WeeklyStatisticsExcel".equals(excelName)) {
			// 주간프로그램
			resultMap = weeklyPrgService.getGrpPgmStatistics(reqMap);
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
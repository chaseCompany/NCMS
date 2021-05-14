package kr.co.chase.ncms.common.excel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import kr.co.chase.ncms.common.util.DateUtil;

public class CounselStatisticsExcel extends AbstractExcelView{
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook) throws Exception{
		@SuppressWarnings("unchecked")
		List<HashMap<String, Object>> cslInfo = (List<HashMap<String, Object>>)model.get("cslInfo");
		String sheetName = StringUtils.defaultIfEmpty((String)model.get("sheetName"), "");

		Sheet sheet = workbook.createSheet(sheetName);
		sheet.setColumnWidth(0, (short) (30*32));
		sheet.setColumnWidth(1, (short) (82*32));
		sheet.setColumnWidth(2, (short) (82*32));
		sheet.setColumnWidth(3, (short) (49*32));
		sheet.setColumnWidth(4, (short) (113*32));
		sheet.setColumnWidth(5, (short) (59*32));
		sheet.setColumnWidth(6, (short) (67*32));
		sheet.setColumnWidth(7, (short) (67*32));
		sheet.setColumnWidth(8, (short) (103*32));
		sheet.setColumnWidth(9, (short) (103*32));
		sheet.setColumnWidth(10, (short) (37*32));
		sheet.setColumnWidth(11, (short) (60*32));
		sheet.setColumnWidth(12, (short) (103*32));
		sheet.setColumnWidth(13, (short) (105*32));
		sheet.setColumnWidth(14, (short) (87*32));
		sheet.setColumnWidth(15, (short) (56*32));
		sheet.setColumnWidth(16, (short) (82*32));
		sheet.setColumnWidth(17, (short) (103*32));
		sheet.setColumnWidth(18, (short) (60*32));
		sheet.setColumnWidth(19, (short) (37*32));
		sheet.setColumnWidth(20, (short) (103*32));
		sheet.setColumnWidth(21, (short) (105*32));
		sheet.setColumnWidth(22, (short) (105*32));
		sheet.setColumnWidth(23, (short) (87*32));
		sheet.setColumnWidth(24, (short) (56*32));
		sheet.setColumnWidth(25, (short) (94*32));
		sheet.setColumnWidth(26, (short) (94*32));
		sheet.setColumnWidth(27, (short) (63*32));
		sheet.setColumnWidth(28, (short) (221*32));
		sheet.setColumnWidth(29, (short) (81*32));
		sheet.setColumnWidth(30, (short) (221*32));
		sheet.setColumnWidth(31, (short) (81*32));
		sheet.setColumnWidth(32, (short) (127*32));
		sheet.setColumnWidth(33, (short) (77*32));
		sheet.setColumnWidth(34, (short) (77*32));
		sheet.setColumnWidth(35, (short) (77*32));
		sheet.setColumnWidth(36, (short) (77*32));
		sheet.setColumnWidth(37, (short) (77*32));
		sheet.setColumnWidth(38, (short) (202*32));
		sheet.setColumnWidth(39, (short) (82*32));
		sheet.setColumnWidth(40, (short) (202*32));
		sheet.setColumnWidth(41, (short) (202*32));
		
		Row row = null;
		Cell cell = null;
		int rowCount = 0;

		if(cslInfo == null) {
			cslInfo = new ArrayList<HashMap<String, Object>>();
		}

		//폰트 설정 - 제목 
		Font titleFont = workbook.createFont();
		titleFont.setFontName("나눔고딕");											// 글씨체
		titleFont.setFontHeight((short)(9*20));								// 사이즈
		titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);						// 굵기
		
		//셀 스타일 - 제목
		CellStyle titleCellStyle = workbook.createCellStyle();
		titleCellStyle.setWrapText(true);
		titleCellStyle.setAlignment(CellStyle.ALIGN_CENTER);						// 정렬
		titleCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);			// 높이 정렬
		//테두리 선(우, 좌, 위, 아래)
		titleCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		titleCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		titleCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		titleCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		titleCellStyle.setFont(titleFont);
		titleCellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);  // 배경색
		titleCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);	   // 배경패턴
		

		//폰트 설정 - 내용 
		Font basicFont = workbook.createFont();
		basicFont.setFontName("나눔고딕");											// 글씨체
		basicFont.setFontHeight((short)(9*20));								// 사이즈
		basicFont.setBoldweight(Font.BOLDWEIGHT_NORMAL);						// 굵기

		//셀 스타일 - 내용 
		CellStyle contentCellStyle = workbook.createCellStyle();
		contentCellStyle.setWrapText(true);
		contentCellStyle.setAlignment(CellStyle.ALIGN_CENTER);						// 정렬
		contentCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);			// 높이 정렬
		//테두리 선(우, 좌, 위, 아래)
		contentCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		contentCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		contentCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		contentCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		contentCellStyle.setFont(basicFont);

		// 타이틀
		String[] titleArr = {"No", "상담일자", "상담\n시간", "소요\n시간", "기관명", "상담자", "본인여부", "본인여부\n세부항목", "정보제공자 성명", "회원번호", "성별", "나이", "연락처", "직업", "지역", "지역\n세부항목", "대상자 성명", "회원번호", "나이", "성별", "연락처", "직업", "내외국인", "지역", "지역\n세부항목", "정보취득경로", "주호소문제", "상담유형", "최초 사용약물", "최초 사용약물\n세부항목", "주요사용약물", "주요 사용약물\n세부항목", "주요조치분류", "ASSIST점수", "위험성(A)", "지지체계(B)", "협조능력(C)", "위기분류\n척도점수", "위기상담", "URS", "상담내용", "상담결과"};
		row = sheet.createRow(rowCount++);
		row.setHeight((short) (22*15));
		for(int i=0; i < titleArr.length; i++) {
			this.cellStyleLoop(i, i, titleCellStyle, row, cell, new XSSFRichTextString(titleArr[i]).toString());
		}
		row = sheet.createRow(rowCount++);
		row.setHeight((short) (22*15));
		for(int i=0; i < titleArr.length; i++) {
			this.cellStyleLoop(i, i, titleCellStyle, row, cell, "");
			sheet.addMergedRegion(new CellRangeAddress(0, 1, i, i));
		}

		// 내용
		for(int i=0; i < cslInfo.size(); i++) {
			row = sheet.createRow(rowCount++);
			row.setHeight((short) (18*15));
			this.cellStyleLoop(0, 0, contentCellStyle, row, cell, String.valueOf(i+1));
			this.cellStyleLoop(1, 1, contentCellStyle, row, cell, DateUtil.getDateFormat(StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("CSL_DT"), ""), "-"));
			this.cellStyleLoop(2, 2, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("CSL_FM_TM"), "")
					+ "~"
					+ StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("CSL_TO_TM"), ""));
			this.cellStyleLoop(3, 3, contentCellStyle, row, cell, (cslInfo.get(i).get("CSL_TERM_TM") == null ? "" : String.valueOf(cslInfo.get(i).get("CSL_TERM_TM"))));
			this.cellStyleLoop(4, 4, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("CSL_SITE_NM"), ""));
			this.cellStyleLoop(5, 5, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("CSL_NM"), ""));
			this.cellStyleLoop(6, 6, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("IFP_GP_NM"), ""));
			this.cellStyleLoop(7, 7, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("IFP_GB_ETC"), ""));
			this.cellStyleLoop(8, 8, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("IFP_NM"), ""));
			this.cellStyleLoop(9, 9, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("IFP_MBR_NO"), ""));
			this.cellStyleLoop(10, 10, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("IFP_GEND_NM"), ""));
			this.cellStyleLoop(11, 11, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("IFP_AGE_NM"), ""));
			String ifpTelNo = StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("IFP_TEL_NO1"), "");
			if(!"".equals(StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("IFP_TEL_NO2"), ""))) {
				ifpTelNo += "-" + StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("IFP_TEL_NO2"), "");
				if(!"".equals(StringUtils.defaultIfEmpty(cslInfo.get(i).get("IFP_TEL_NO3").toString(), ""))) {
					ifpTelNo += "-" + StringUtils.defaultIfEmpty(cslInfo.get(i).get("IFP_TEL_NO3").toString(), "");
				}
			}
			this.cellStyleLoop(12, 12, contentCellStyle, row, cell, ifpTelNo);
			this.cellStyleLoop(13, 13, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("IFP_JOB_NM"), ""));
			this.cellStyleLoop(14, 14, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("IFP_AREA_NM"), ""));
			this.cellStyleLoop(15, 15, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("IFP_AREA_ETC"), ""));
			this.cellStyleLoop(16, 16, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("TGP_NM"), ""));
			this.cellStyleLoop(17, 17, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("TGP_MBR_NO"), ""));
			this.cellStyleLoop(18, 18, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("TGP_AGE_NM"), ""));
			this.cellStyleLoop(19, 19, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("TGP_GEND_NM"), ""));
			String tgpTelNo = StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("TGP_TEL_NO1"), "");
			if(!"".equals(StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("TGP_TEL_NO2"), ""))) {
				tgpTelNo += "-" + StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("TGP_TEL_NO2"), "");
				if(!"".equals(StringUtils.defaultIfEmpty(cslInfo.get(i).get("TGP_TEL_NO3").toString(), ""))) {
					tgpTelNo += "-" + StringUtils.defaultIfEmpty(cslInfo.get(i).get("TGP_TEL_NO3").toString(), "");
				}
			}
			this.cellStyleLoop(20, 20, contentCellStyle, row, cell, tgpTelNo);
			this.cellStyleLoop(21, 21, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("TGP_JOB_NM"), ""));
			this.cellStyleLoop(22, 22, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("TGP_FRG_NM"), ""));
			this.cellStyleLoop(23, 23, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("TGP_AREA_NM"), ""));
			this.cellStyleLoop(24, 24, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("TGP_AREA_ETC"), ""));
			this.cellStyleLoop(25, 25, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("IF_PATH_NM"), ""));
			this.cellStyleLoop(26, 26, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("PBM_KND_NM"), ""));
			this.cellStyleLoop(27, 27, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("CSL_TP_NM"), ""));
			this.cellStyleLoop(28, 28, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("FST_DRUG_NM"), ""));
			this.cellStyleLoop(29, 29, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("FST_DRUG"), ""));
			this.cellStyleLoop(30, 30, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("MAIN_DRUG_NM"), ""));
			this.cellStyleLoop(31, 31, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("MAIN_DRUG"), ""));
			this.cellStyleLoop(32, 32, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("MJR_MNG_NM"), ""));
			this.cellStyleLoop(33, 33, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("AST_SCO"), ""));
			this.cellStyleLoop(34, 34, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("RSKA_TP_NM"), ""));
			this.cellStyleLoop(35, 35, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("RSKB_TP_NM"), ""));
			this.cellStyleLoop(36, 36, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("RSKC_TP_NM"), ""));
			this.cellStyleLoop(37, 37, contentCellStyle, row, cell, (cslInfo.get(i).get("RSK_SCO") == null ? "" : String.valueOf(cslInfo.get(i).get("RSK_SCO"))));
			this.cellStyleLoop(38, 38, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("CSL_EMER"), ""));
			this.cellStyleLoop(39, 39, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("URS_NM"), ""));
			this.cellStyleLoop(40, 40, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("CSL_CTNT"), "").replaceAll("\r\n", "\n"));
			this.cellStyleLoop(41, 41, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("CSL_RST"), "").replaceAll("\r\n", "\n"));
		}
	}

	@Override
	protected Workbook createWorkbook(){
		return new XSSFWorkbook();
	}
}
package kr.co.chase.ncms.common.excel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
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

public class MentalityStatisticsExcel extends AbstractExcelView{
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook) throws Exception{
		@SuppressWarnings("unchecked")
		List<HashMap<String, Object>> cslInfo = (List<HashMap<String, Object>>)model.get("cslInfo");
		String sheetName = StringUtils.defaultIfEmpty((String)model.get("sheetName"), "");

		Sheet sheet = workbook.createSheet(sheetName);
		sheet.setColumnWidth(0, (short) (30*32));
		sheet.setColumnWidth(1, (short) (59*32));
		sheet.setColumnWidth(2, (short) (103*32));
		sheet.setColumnWidth(3, (short) (37*32));
		sheet.setColumnWidth(4, (short) (60*32));
		sheet.setColumnWidth(5, (short) (82*32));
		sheet.setColumnWidth(6, (short) (67*32));
		sheet.setColumnWidth(7, (short) (113*32));
		sheet.setColumnWidth(8, (short) (73*32));
		sheet.setColumnWidth(9, (short) (82*32));
		sheet.setColumnWidth(10, (short) (82*32));
		sheet.setColumnWidth(11, (short) (49*32));
		sheet.setColumnWidth(12, (short) (67*32));
		sheet.setColumnWidth(13, (short) (63*32));
		sheet.setColumnWidth(14, (short) (221*32));
		sheet.setColumnWidth(15, (short) (221*32));
		sheet.setColumnWidth(16, (short) (77*32));
		sheet.setColumnWidth(17, (short) (77*32));
		sheet.setColumnWidth(18, (short) (77*32));
		sheet.setColumnWidth(19, (short) (77*32));
		sheet.setColumnWidth(20, (short) (202*32));
		sheet.setColumnWidth(21, (short) (82*32));
		sheet.setColumnWidth(22, (short) (202*32));
		sheet.setColumnWidth(23, (short) (202*32));
		sheet.setColumnWidth(24, (short) (72*32));
		sheet.setColumnWidth(25, (short) (72*32));
		sheet.setColumnWidth(26, (short) (202*32));
		
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
//		titleFont.setColor(Font.COLOR_NORMAL);
		
		//폰트 설정 - 제목 red 
//		Font redFont = titleFont;
//		redFont.setColor(Font.COLOR_RED);
		
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
		titleCellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);  // 배경색
		titleCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);	   // 배경패턴
		titleCellStyle.setFont(titleFont);
		
//		//셀 스타일 - 제목 red
//		CellStyle redCellStyle = workbook.createCellStyle();
//		redCellStyle.setWrapText(true);
//		redCellStyle.setAlignment(CellStyle.ALIGN_CENTER);						// 정렬
//		redCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);			// 높이 정렬
//		//테두리 선(우, 좌, 위, 아래)
//		redCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
//		redCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//		redCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
//		redCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//		redCellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);  // 배경색
//		redCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);	   // 배경패턴
//		redCellStyle.setFont(redFont);
		
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
		String[] titleArr = {"No", "회원명", "회원번호", "성별", "나이", "등록일자", "의료보장", "기관명", "상담자", "상담일자", "상담\n시간", "소요\n시간", "상담대상", "상담유형", "상담주제", "상담목표", "위험성(A)", "지지체계(B)", "협조능력(C)", "위기분류\n척도점수", "위기상담", "URS", "상담내용", "상담결과", "다음\n상담일자", "다음\n상담시간", "다음\n상담내용"};
		row = sheet.createRow(rowCount++);
		row.setHeight((short) (22*15));
		for(int i=0; i < titleArr.length; i++) {
//			if("위기상담".equals(titleArr[i]) || "상담내용".equals(titleArr[i]) || "상담결과".equals(titleArr[i]) || "다음\n상담내용".equals(titleArr[i])) {
//				this.cellStyleLoop(i, i, redCellStyle, row, cell, new XSSFRichTextString(titleArr[i]).toString());
//			}else {
				this.cellStyleLoop(i, i, titleCellStyle, row, cell, new XSSFRichTextString(titleArr[i]).toString());
//			}
		}
		row = sheet.createRow(rowCount++);
		row.setHeight((short) (22*15));
		for(int i=0; i < titleArr.length; i++) {
//			if("위기상담".equals(titleArr[i]) || "상담내용".equals(titleArr[i]) || "상담결과".equals(titleArr[i]) || "다음\n상담내용".equals(titleArr[i])) {
//				this.cellStyleLoop(i, i, redCellStyle, row, cell, "");
//			}else {
				this.cellStyleLoop(i, i, titleCellStyle, row, cell, "");
//			}
			sheet.addMergedRegion(new CellRangeAddress(0, 1, i, i));
		}

		// 내용
		for(int i=0; i < cslInfo.size(); i++) {
			row = sheet.createRow(rowCount++);
			row.setHeight((short) (18*15));
			this.cellStyleLoop(0, 0, contentCellStyle, row, cell, String.valueOf(i+1));
			this.cellStyleLoop(1, 1, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("MBR_NM"), ""));
			this.cellStyleLoop(2, 2, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("MBR_NO"), ""));
			this.cellStyleLoop(3, 3, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("GEND_NM"), ""));
			this.cellStyleLoop(4, 4, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("AGE_NM"), ""));
			this.cellStyleLoop(5, 5, contentCellStyle, row, cell, DateUtil.getDateFormat(StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("REG_DT"), ""), "-"));
			this.cellStyleLoop(6, 6, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("MEDIC_CARE_NM"), ""));
			this.cellStyleLoop(7, 7, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("SITE_NM"), ""));
			this.cellStyleLoop(8, 8, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("CSL_NM"), ""));
			this.cellStyleLoop(9, 9, contentCellStyle, row, cell, DateUtil.getDateFormat(StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("CSL_DT"), ""), "-"));
			this.cellStyleLoop(10, 10, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("CSL_FM_TM"), "")
					+ "~"
					+ StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("CSL_TO_TM"), ""));
			this.cellStyleLoop(11, 11, contentCellStyle, row, cell, (cslInfo.get(i).get("CSL_TERM_TM") == null ? "" : String.valueOf(cslInfo.get(i).get("CSL_TERM_TM"))));
			this.cellStyleLoop(12, 12, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("CSL_TGT_NM"), ""));
			this.cellStyleLoop(13, 13, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("CSL_TP_NM"), ""));
			this.cellStyleLoop(14, 14, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("CSL_SBJ"), ""));
			this.cellStyleLoop(15, 15, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("CSL_TGT"), ""));
			this.cellStyleLoop(16, 16, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("RSKA_TP_NM"), ""));
			this.cellStyleLoop(17, 17, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("RSKB_TP_NM"), ""));
			this.cellStyleLoop(18, 18, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("RSKC_TP_NM"), ""));
			this.cellStyleLoop(19, 19, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("RSK_SCO"), ""));
			this.cellStyleLoop(20, 20, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("CRISIS_COUNSEL"), ""));
			this.cellStyleLoop(21, 21, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("URS_NM"), ""));
			this.cellStyleLoop(22, 22, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("CSL_CTNT"), ""));
			this.cellStyleLoop(23, 23, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("CSL_RST"), ""));
			this.cellStyleLoop(24, 24, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("NXT_CSL_DT"), ""));
			this.cellStyleLoop(25, 25, contentCellStyle, row, cell, (cslInfo.get(i).get("NXT_CSL_TM") == null ? "" : String.valueOf(cslInfo.get(i).get("NXT_CSL_TM"))));
			this.cellStyleLoop(26, 26, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("NXT_CSL_CTNT"), ""));
		}
	}

	@Override
	protected Workbook createWorkbook(){
		return new XSSFWorkbook();
	}
}
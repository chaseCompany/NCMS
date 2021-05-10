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

public class CureStatisticsExcel extends AbstractExcelView{
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
		sheet.setColumnWidth(4, (short) (37*32));
		sheet.setColumnWidth(5, (short) (82*32));
		sheet.setColumnWidth(6, (short) (67*32));
		sheet.setColumnWidth(7, (short) (113*32));
		sheet.setColumnWidth(8, (short) (73*32));
		sheet.setColumnWidth(9, (short) (72*32));
		sheet.setColumnWidth(10, (short) (72*32));
		sheet.setColumnWidth(11, (short) (72*32));
		sheet.setColumnWidth(12, (short) (72*32));
		sheet.setColumnWidth(13, (short) (72*32));
		sheet.setColumnWidth(14, (short) (72*32));
		sheet.setColumnWidth(15, (short) (72*32));
		sheet.setColumnWidth(16, (short) (72*32));
		sheet.setColumnWidth(17, (short) (72*32));
		sheet.setColumnWidth(18, (short) (72*32));
		sheet.setColumnWidth(19, (short) (72*32));
		sheet.setColumnWidth(20, (short) (72*32));
		sheet.setColumnWidth(21, (short) (72*32));
		sheet.setColumnWidth(22, (short) (72*32));
		sheet.setColumnWidth(23, (short) (72*32));
		sheet.setColumnWidth(25, (short) (72*32));
		sheet.setColumnWidth(26, (short) (72*32));
		sheet.setColumnWidth(27, (short) (72*32));
		sheet.setColumnWidth(28, (short) (72*32));
		sheet.setColumnWidth(29, (short) (72*32));
		
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
		String[] titleArr = {"No", "회원명", "회원번호", "성별", "나이", "등록일자", "의료보장", "기관명", "사례관리자", "치료정보", "직업력", "재활서비스 이용정보"};
		String[] titleArr2 = {"", "", "", "", "", "", "", "", "", "진단명", "발병시기", "처방약물", "복용량", "순응도", "신체질환", "취업시작일", "취업종료일", "취업일수", "고용형태", "직업군", "직업군\r\n세부항목", "소득금액", "퇴사사유", "이용시작일", "이용종료일", "이용일수", "기관유형", "기관명", "내용"};
		row = sheet.createRow(rowCount++);
		row.setHeight((short) (22*15));
		int colCnt = 0;
		for(int i=0; i < titleArr.length; i++) {
			if("치료정보".equals(titleArr[i]) || "재활서비스 이용정보".equals(titleArr[i])) {
				int startCol = colCnt;
				colCnt += 5;
				this.cellStyleLoop(startCol, colCnt, titleCellStyle, row, cell, new XSSFRichTextString(titleArr[i]).toString());
				sheet.addMergedRegion(new CellRangeAddress(0, 0, startCol, colCnt));
			}else if("직업력".equals(titleArr[i])) {
				int startCol = colCnt;
				colCnt += 7;
				this.cellStyleLoop(startCol, colCnt, titleCellStyle, row, cell, new XSSFRichTextString(titleArr[i]).toString());
				sheet.addMergedRegion(new CellRangeAddress(0, 0, startCol, colCnt));
			}else {
				this.cellStyleLoop(colCnt, colCnt, titleCellStyle, row, cell, new XSSFRichTextString(titleArr[i]).toString());
			}
			colCnt++;
		}
		
		row = sheet.createRow(rowCount++);
		row.setHeight((short) (22*15));
		for(int i=0; i < titleArr2.length; i++) {
			this.cellStyleLoop(i, i, titleCellStyle, row, cell, new XSSFRichTextString(titleArr2[i]).toString());
		}
		
		row = sheet.createRow(rowCount++);
		row.setHeight((short) (22*15));
		for(int i=0; i < titleArr2.length; i++) {
			this.cellStyleLoop(i, i, titleCellStyle, row, cell, "");
			if(i < 9) {
				sheet.addMergedRegion(new CellRangeAddress(0, 2, i, i));
			}
			if(i > 8) {
				sheet.addMergedRegion(new CellRangeAddress(1, 2, i, i));
			}
		}
		
		// 내용
		for(int i=0; i < cslInfo.size(); i++) {
			row = sheet.createRow(rowCount++);
			row.setHeight((short) (18*15));
			this.cellStyleLoop(0, 0, contentCellStyle, row, cell, String.valueOf(i+1));
			this.cellStyleLoop(1, 1, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("MBR_NM"), ""));
			this.cellStyleLoop(2, 2, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("MBR_NO"), ""));
			this.cellStyleLoop(3, 3, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("GEND_NM"), ""));
			this.cellStyleLoop(4, 4, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("AGE"), ""));
			this.cellStyleLoop(5, 5, contentCellStyle, row, cell, DateUtil.getDateFormat(StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("REG_DT"), ""), "-"));
			this.cellStyleLoop(6, 6, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("MEDIC_CARE_NM"), ""));
			this.cellStyleLoop(7, 7, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("MNG_SITE_NM"), ""));
			this.cellStyleLoop(8, 8, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("MNG_USR_NM"), ""));
			this.cellStyleLoop(9, 9, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("DIAG_NAME"), ""));
			this.cellStyleLoop(10, 10, contentCellStyle, row, cell, DateUtil.getDateFormat(StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("HEAL_ST_DT"), ""), "-"));
			this.cellStyleLoop(11, 11, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("PRES_DRUG"), ""));
			this.cellStyleLoop(12, 12, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("DOSAGE"), ""));
			this.cellStyleLoop(13, 13, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("CONFORM_NM"), ""));
			this.cellStyleLoop(14, 14, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("MEDI_ILL_NM"), ""));
			this.cellStyleLoop(15, 15, contentCellStyle, row, cell, DateUtil.getDateFormat(StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("JOB_ST_DT"), ""), "-"));
			this.cellStyleLoop(16, 16, contentCellStyle, row, cell, DateUtil.getDateFormat(StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("JOB_END_DT"), ""), "-"));
			this.cellStyleLoop(17, 17, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("JOB_TERM"), ""));
			this.cellStyleLoop(18, 18, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("JOB_FORM_NM"), ""));
			this.cellStyleLoop(19, 19, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("JOB_TYPE_NM"), ""));
			this.cellStyleLoop(20, 20, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("JOB_TYPE_ETC"), ""));
			this.cellStyleLoop(21, 21, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("JOB_INCOME"), ""));
			this.cellStyleLoop(22, 22, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("JOB_RESIGN"), ""));
			this.cellStyleLoop(23, 23, contentCellStyle, row, cell, DateUtil.getDateFormat(StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("HEAL_ST_DT"), ""), "-"));
			this.cellStyleLoop(24, 24, contentCellStyle, row, cell, DateUtil.getDateFormat(StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("HEAL_END_DT"), ""), "-"));
			this.cellStyleLoop(25, 25, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("HEAL_TERM"), ""));
			this.cellStyleLoop(26, 26, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("ORGAN_FORM_NM"), ""));
			this.cellStyleLoop(27, 27, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("ORGAN_NAME"), ""));
			this.cellStyleLoop(28, 28, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("ORGAN_CTNT"), ""));
		}
	}

	@Override
	protected Workbook createWorkbook(){
		return new XSSFWorkbook();
	}
}
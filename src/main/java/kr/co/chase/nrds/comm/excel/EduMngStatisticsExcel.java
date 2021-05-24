package kr.co.chase.nrds.comm.excel;

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

import kr.co.chase.ncms.common.excel.AbstractExcelView;

public class EduMngStatisticsExcel extends AbstractExcelView{
	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook) throws Exception{
		List<HashMap<String, Object>> cslInfo = (List<HashMap<String, Object>>)model.get("cslInfo");
		String sheetName = StringUtils.defaultIfEmpty((String)model.get("sheetName"), "");

		Sheet sheet = workbook.createSheet(sheetName);
		sheet.setColumnWidth(0, (short) (30*32));
		sheet.setColumnWidth(1, (short) (113*32));
		sheet.setColumnWidth(2, (short) (150*32));
		sheet.setColumnWidth(3, (short) (150*32));
		sheet.setColumnWidth(4, (short) (81*32));
		sheet.setColumnWidth(5, (short) (221*32));
		sheet.setColumnWidth(6, (short) (60*32));
		sheet.setColumnWidth(7, (short) (60*32));
		sheet.setColumnWidth(8, (short) (60*32));
		sheet.setColumnWidth(9, (short) (60*32));
		sheet.setColumnWidth(10, (short) (221*32));
		sheet.setColumnWidth(11, (short) (221*32));
		sheet.setColumnWidth(12, (short) (81*32));
		sheet.setColumnWidth(13, (short) (81*32));
		
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
		String[] titleArr = {"No", "기관명", "교육분류", "교육과정명", "담당자", "교육기간", "교육일수", "교육시간", "회기", "기수", "교육일시", "교육주제", "주강사", "보조강사"};
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
			this.cellStyleLoop(1, 1, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("PGM_AGENT_NM"), ""));
			this.cellStyleLoop(2, 2, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("PGM_ED_NM"), ""));
			this.cellStyleLoop(3, 3, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("PGM_CLASS_NM_NM"), "")+" "+StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("PGM_CLASS_SUB_NM"), ""));
			this.cellStyleLoop(4, 4, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("PGM_MNG_USR_ID"), ""));
			this.cellStyleLoop(5, 5, contentCellStyle, row, cell, 
					StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("PGM_START_DT"), "") + " " + StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("PGM_START_TM"), "")
					+" ~ "+StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("PGM_END_DT"), "") + " " + StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("PGM_END_TM"), "")
			);
			this.cellStyleLoop(6, 6, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("PGM_DUR"), ""));
			this.cellStyleLoop(7, 7, contentCellStyle, row, cell, cslInfo.get(i).get("PGM_CLASS_DUR") == null ? "" : String.valueOf(cslInfo.get(i).get("PGM_CLASS_DUR")));
			this.cellStyleLoop(8, 8, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("PGM_SESSION"), ""));
			this.cellStyleLoop(9, 9, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("PGM_CLASS"), ""));
			this.cellStyleLoop(10, 10, contentCellStyle, row, cell,
					StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("PGM_CLASS_START_DT"), "") + " " + StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("PGM_CLASS_START_TM"), "")
					+" ~ "+StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("PGM_CLASS_END_DT"), "") + " " + StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("PGM_CLASS_END_TM"), "")
			);
			this.cellStyleLoop(11, 11, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("PGM_SUBJECT"), ""));
			this.cellStyleLoop(12, 12, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("PGM_MAIN_LEC"), ""));
			this.cellStyleLoop(13, 13, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("PGM_MAIN_LEC"), ""));
		}
	}

	@Override
	protected Workbook createWorkbook(){
		return new XSSFWorkbook();
	}
}
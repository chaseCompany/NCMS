package kr.co.chase.ncms.common.excel;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import kr.co.chase.ncms.common.util.DateUtil;

//교육상담 엑셀다운로드
public class EduCounselExcel extends AbstractExcelView{
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook) throws Exception{
		HashMap<String, Object> cslInfo = (HashMap<String, Object>)model.get("cslInfo");
		String sheetName = StringUtils.defaultIfEmpty((String)model.get("sheetName"), "");

		Sheet sheet = workbook.createSheet(sheetName);
		Row row = null;
		Cell cell = null;
		int rowCount = 0;
		int maxCellCount = 12;

		if(cslInfo == null) {
			cslInfo = new HashMap<String, Object>();
		}

		//셀 스타일
		CellStyle titleCellStyle = workbook.createCellStyle();
		titleCellStyle.setAlignment(CellStyle.ALIGN_CENTER);					// 가운데 정렬
		titleCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);			// 높이 가운데 정렬
		//폰트 설정
		Font titleFont = workbook.createFont();
		titleFont.setFontName("굴림");											// 글씨체
		titleFont.setFontHeight((short)(16*20));								// 사이즈
		titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);							// 볼드(굵게)
		titleCellStyle.setFont(titleFont);

		//셀 스타일
		CellStyle nameCellStyle = workbook.createCellStyle();
		nameCellStyle.setAlignment(CellStyle.ALIGN_CENTER);						// 가운데 정렬
		nameCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);			// 높이 가운데 정렬
		//테두리 선(우, 좌, 위, 아래)
		nameCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		nameCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		nameCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		nameCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		//폰트 설정
		Font nameFont = workbook.createFont();
		nameFont.setFontName("굴림");												// 글씨체
		nameFont.setFontHeight((short)(10*20));									// 사이즈
		nameFont.setBoldweight(Font.BOLDWEIGHT_BOLD);							// 볼드(굵게)
		nameCellStyle.setFont(nameFont);

		//셀 스타일
		CellStyle titlaValueCellStyle = workbook.createCellStyle();
		titlaValueCellStyle.setAlignment(CellStyle.ALIGN_LEFT);					// 정렬
		titlaValueCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);	// 높이 정렬
		//테두리 선(우, 좌, 위, 아래)
		titlaValueCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		//폰트 설정
		Font titleValueFont = workbook.createFont();
		titleValueFont.setFontName("굴림");										// 글씨체
		titleValueFont.setFontHeight((short)(10*20));							// 사이즈
		titleValueFont.setBoldweight(Font.BOLDWEIGHT_NORMAL);					// 굵기
		titlaValueCellStyle.setFont(titleValueFont);

		//셀 스타일
		CellStyle valueCellStyle = workbook.createCellStyle();
		valueCellStyle.setAlignment(CellStyle.ALIGN_LEFT);						// 정렬
		valueCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);			// 높이 정렬
		//테두리 선(우, 좌, 위, 아래)
		valueCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		valueCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		valueCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		valueCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		//폰트 설정
		Font valueFont = workbook.createFont();
		valueFont.setFontName("굴림");											// 글씨체
		valueFont.setFontHeight((short)(10*20));								// 사이즈
		valueFont.setBoldweight(Font.BOLDWEIGHT_NORMAL);						// 굵기
		valueCellStyle.setFont(valueFont);

		// 타이틀
		row = sheet.createRow(rowCount);
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 0, maxCellCount));
		this.cellStyleLoop(0, maxCellCount, titleCellStyle, row, cell, sheetName);

		rowCount++;
		row = sheet.createRow(rowCount);
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 8, 9));
		this.cellStyleLoop(8, 9, titlaValueCellStyle, row, cell, "상담번호 :");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 10, maxCellCount));
		this.cellStyleLoop(10, maxCellCount, titlaValueCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("CSL_NO"), ""));

		rowCount++;
		row = sheet.createRow(rowCount);
		this.cellStyleLoop(0, 0, nameCellStyle, row, cell, "상담일자");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 1, 2));
		this.cellStyleLoop(1, 2, valueCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("CSL_DT"), ""));
		this.cellStyleLoop(3, 3, nameCellStyle, row, cell, "상담시간");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 4, 9));
		this.cellStyleLoop(4, 9, valueCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("CSL_FM_TM"), "")
														  + " ~ "
														  + StringUtils.defaultIfEmpty((String)cslInfo.get("CSL_TO_TM"), "")
														  + "("
														  + StringUtils.defaultIfEmpty(cslInfo.get("CSL_TERM_TM").toString(), "") + "분)");
		this.cellStyleLoop(10, 10, nameCellStyle, row, cell, "상담자");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 11, maxCellCount));
		this.cellStyleLoop(11, maxCellCount, valueCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("MBR_NM"), "")
																	  + "("
																	  + StringUtils.defaultIfEmpty((String)cslInfo.get("MBR_NO"), "") + ")");

		rowCount++;
		row = sheet.createRow(rowCount);
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount+3, 0, 0));
		this.cellStyleLoop(0, 0, nameCellStyle, row, cell, "대상자");
		this.cellStyleLoop(1, 1, nameCellStyle, row, cell, "성명");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 2, 4));
		this.cellStyleLoop(2, 4, valueCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("MBR_NM"), ""));
		this.cellStyleLoop(5, 5, nameCellStyle, row, cell, "성별");
		this.cellStyleLoop(6, 6, valueCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("GEND"), ""));
		this.cellStyleLoop(7, 7, nameCellStyle, row, cell, "연령");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 8, 9));
		this.cellStyleLoop(8, 9, valueCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("AGE_NM"), ""));
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 10, 11));
		this.cellStyleLoop(10, 10, nameCellStyle, row, cell, "내/외국인");
		this.cellStyleLoop(maxCellCount, maxCellCount, valueCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("FRG"), ""));

		rowCount++;
		row = sheet.createRow(rowCount);
		this.cellStyleLoop(0, 0, nameCellStyle, row, cell, "");
		this.cellStyleLoop(1, 1, nameCellStyle, row, cell, "연락처");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 2, 4));
		this.cellStyleLoop(2, 4, valueCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("TEL_NO"), ""));
		this.cellStyleLoop(5, 5, nameCellStyle, row, cell, "직업");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 6, maxCellCount));
		this.cellStyleLoop(6, maxCellCount, valueCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("JOB"), ""));

		rowCount++;
		row = sheet.createRow(rowCount);
		this.cellStyleLoop(0, 0, nameCellStyle, row, cell, "");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount+1, 1, 1));
		this.cellStyleLoop(1, 1, nameCellStyle, row, cell, "주소");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 2, maxCellCount));
		this.cellStyleLoop(2, maxCellCount, valueCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("ADDR1"), ""));

		rowCount++;
		row = sheet.createRow(rowCount);
		this.cellStyleLoop(0, 0, nameCellStyle, row, cell, "");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 2, maxCellCount));
		this.cellStyleLoop(2, maxCellCount, valueCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("ADDR2"), ""));

		rowCount++;
		row = sheet.createRow(rowCount);
		this.cellStyleLoop(0, 0, nameCellStyle, row, cell, "상담대상");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 1, 4));
		this.cellStyleLoop(1, 4, valueCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("CSL_TGT"), ""));
		this.cellStyleLoop(5, 5, nameCellStyle, row, cell, "상담유형");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 6, maxCellCount));
		this.cellStyleLoop(6, maxCellCount, valueCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("CSL_TP"), ""));

		rowCount++;
		row = sheet.createRow(rowCount);
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount+2, 0, 0));
		this.cellStyleLoop(0, 0, nameCellStyle, row, cell, "위기분류척도");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 1, 2));
		this.cellStyleLoop(1, 2, titlaValueCellStyle, row, cell, "Rating A:위험성");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 3, maxCellCount));
		this.cellStyleLoop(3, maxCellCount, valueCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("RSKA"), ""));

		rowCount++;
		row = sheet.createRow(rowCount);
		this.cellStyleLoop(0, 0, nameCellStyle, row, cell, "");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 1, 2));
		this.cellStyleLoop(1, 2, titlaValueCellStyle, row, cell, "Rating B:지지체계");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 3, maxCellCount));
		this.cellStyleLoop(3, maxCellCount, valueCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("RSKB"), ""));
		
		rowCount++;
		row = sheet.createRow(rowCount);
		this.cellStyleLoop(0, 0, nameCellStyle, row, cell, "");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 1, 2));
		this.cellStyleLoop(1, 2, titlaValueCellStyle, row, cell, "Rating C:협조능력");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 3, maxCellCount));
		this.cellStyleLoop(3, maxCellCount, valueCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("RSKC"), ""));

		rowCount++;
		row = sheet.createRow(rowCount);
		this.cellStyleLoop(0, 0, nameCellStyle, row, cell, "위기상담");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 1, maxCellCount));
		this.cellStyleLoop(1, maxCellCount, valueCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("CRISIS_COUNSEL"), ""));
		
		rowCount++;
		row = sheet.createRow(rowCount);
		this.cellStyleLoop(0, 0, nameCellStyle, row, cell, "URS");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 1, maxCellCount));
		this.cellStyleLoop(1, maxCellCount, valueCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("USR"), ""));

		rowCount++;
		row = sheet.createRow(rowCount);
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount+1, 0, 0));
		this.cellStyleLoop(0, 0, nameCellStyle, row, cell, "자살관련");
		this.cellStyleLoop(1, 1, nameCellStyle, row, cell, "치료력");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 2, 3));
		this.cellStyleLoop(2, 3, valueCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("CURE"), ""));
		this.cellStyleLoop(4, 4, nameCellStyle, row, cell, "약물사용여부");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 5, 6));
		this.cellStyleLoop(5, 6, valueCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("DRUG_USE"), ""));
		this.cellStyleLoop(7, 7, nameCellStyle, row, cell, "과거 자살시도력");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 8, 9));
		this.cellStyleLoop(8, 9, valueCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("OLD_ACT"), ""));
		this.cellStyleLoop(10, 10, nameCellStyle, row, cell, "시도 횟수");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 11, 12));
		this.cellStyleLoop(11, 12, valueCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("ACT"), ""));

		rowCount++;
		row = sheet.createRow(rowCount);
		this.cellStyleLoop(0, 0, nameCellStyle, row, cell, "");
		this.cellStyleLoop(1, 1, nameCellStyle, row, cell, "주변인 자살");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 2, 3));
		this.cellStyleLoop(2, 3, valueCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("AROUND_SUICID"), ""));
		this.cellStyleLoop(4, 4, nameCellStyle, row, cell, "자살계획");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 5, 6));
		this.cellStyleLoop(5, 6, valueCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("SUICIDE_PLAN"), ""));
		this.cellStyleLoop(7, 7, nameCellStyle, row, cell, "과거 시도방법");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 8, 9));
		this.cellStyleLoop(8, 9, valueCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("OLE_ACT_WAY"), ""));
		this.cellStyleLoop(10, 10, nameCellStyle, row, cell, "시도계획방법");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 11, 12));
		this.cellStyleLoop(11, 12, valueCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("ACT_WAY"), ""));
		
		rowCount++;
		row = sheet.createRow(rowCount);
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount+5, 0, 0));
		this.cellStyleLoop(0, 0, nameCellStyle, row, cell, "상담내용");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount+5, 1, maxCellCount));
		this.cellStyleLoop(1, maxCellCount, valueCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("CSL_CTNT"), ""));
		rowCount++;rowCount++;rowCount++;rowCount++;
		
		rowCount++;
		row = sheet.createRow(rowCount);
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount+4, 0, 0));
		this.cellStyleLoop(0, 0, nameCellStyle, row, cell, "상담결과");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount+4, 1, maxCellCount));
		this.cellStyleLoop(1, maxCellCount, valueCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("CSL_RST"), ""));
		
		rowCount++;
		row = sheet.createRow(rowCount);
		this.cellStyleLoop(0, 1, nameCellStyle, row, cell, "다음상담일");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 2, 5));
		this.cellStyleLoop(2, 5, valueCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("NXT_CSL_DT"), ""));
		this.cellStyleLoop(6, 6, nameCellStyle, row, cell, "시간");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 7, 12));
		this.cellStyleLoop(7, 12, valueCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("NXT_CSL_TM"), ""));

		rowCount++;
		row = sheet.createRow(rowCount);
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount+4, 0, 0));
		this.cellStyleLoop(0, 0, nameCellStyle, row, cell, "다음상담내용");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount+4, 1, maxCellCount));
		this.cellStyleLoop(1, maxCellCount, valueCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("NXT_CSL_CTNT"), ""));
	}

	@Override
	protected Workbook createWorkbook(){
		return new XSSFWorkbook();
	}
}
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

public class CounselExel extends AbstractExcelView{
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
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 7, 8));
		this.cellStyleLoop(7, 8, titlaValueCellStyle, row, cell, "접수번호 :");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 9, maxCellCount));
		this.cellStyleLoop(9, maxCellCount, titlaValueCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("RCP_NO"), ""));

		rowCount++;
		row = sheet.createRow(rowCount);
		this.cellStyleLoop(0, 0, nameCellStyle, row, cell, "상담일자");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 1, 2));
		this.cellStyleLoop(1, 2, valueCellStyle, row, cell, DateUtil.getDateFormat(StringUtils.defaultIfEmpty((String)cslInfo.get("CSL_DT"), ""), "-"));
		this.cellStyleLoop(3, 3, nameCellStyle, row, cell, "상담시간");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 4, 9));
		this.cellStyleLoop(4, 9, valueCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("CSL_FM_TM"), "")
														  + " ~ "
														  + StringUtils.defaultIfEmpty((String)cslInfo.get("CSL_TO_TM"), "")
														  + "("
														  + StringUtils.defaultIfEmpty(cslInfo.get("CSL_TERM_TM").toString(), "") + "분)");
		this.cellStyleLoop(10, 10, nameCellStyle, row, cell, "상담자");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 11, maxCellCount));
		this.cellStyleLoop(11, maxCellCount, valueCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("CSL_NM"), "")
																	  + "("
																	  + StringUtils.defaultIfEmpty((String)cslInfo.get("CSL_ID"), "") + ")");

		rowCount++;
		row = sheet.createRow(rowCount);
		this.cellStyleLoop(0, 0, nameCellStyle, row, cell, "본인여부");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 1, maxCellCount));
		String ifpGpNm = StringUtils.defaultIfEmpty((String)cslInfo.get("IFP_GP_NM"), "");
		if(!"".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("IFP_GB_ETC"), ""))) {
			ifpGpNm += StringUtils.defaultIfEmpty((String)cslInfo.get("IFP_GB_ETC"), "");
		}
		this.cellStyleLoop(1, maxCellCount, valueCellStyle, row, cell, ifpGpNm);

		rowCount++;
		row = sheet.createRow(rowCount);
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount+3, 0, 0));
		this.cellStyleLoop(0, 0, nameCellStyle, row, cell, "정보제공자");
		this.cellStyleLoop(1, 1, nameCellStyle, row, cell, "성명");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 2, 4));
		this.cellStyleLoop(2, 4, valueCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("IFP_NM"), ""));
		this.cellStyleLoop(5, 5, nameCellStyle, row, cell, "성별");
		this.cellStyleLoop(6, 6, valueCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("IFP_GEND_NM"), ""));
		this.cellStyleLoop(7, 7, nameCellStyle, row, cell, "연령");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 8, maxCellCount));
		this.cellStyleLoop(8, maxCellCount, valueCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("IFP_AGE"), ""));

		rowCount++;
		row = sheet.createRow(rowCount);
		this.cellStyleLoop(0, 0, nameCellStyle, row, cell, "");
		this.cellStyleLoop(1, 1, nameCellStyle, row, cell, "연락처");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 2, 4));
		String ifpTel = StringUtils.defaultIfEmpty((String)cslInfo.get("IFP_TEL_NO1"), "");
		if(!"".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("IFP_TEL_NO2"), ""))) {
			ifpTel += "-" + StringUtils.defaultIfEmpty((String)cslInfo.get("IFP_TEL_NO2"), "");

			if(!"".equals(StringUtils.defaultIfEmpty(cslInfo.get("IFP_TEL_NO3").toString(), ""))) {
				ifpTel += "-" + StringUtils.defaultIfEmpty(cslInfo.get("IFP_TEL_NO3").toString(), "");
			}
		}
		this.cellStyleLoop(2, 4, valueCellStyle, row, cell, ifpTel);
		this.cellStyleLoop(5, 5, nameCellStyle, row, cell, "직업");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 6, maxCellCount));
		this.cellStyleLoop(6, maxCellCount, valueCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("IFP_JOB_NM"), ""));

		rowCount++;
		row = sheet.createRow(rowCount);
		this.cellStyleLoop(0, 0, nameCellStyle, row, cell, "");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount+1, 1, 1));
		this.cellStyleLoop(1, 1, nameCellStyle, row, cell, "주소");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 2, maxCellCount));
		this.cellStyleLoop(2, maxCellCount, valueCellStyle, row, cell, "test");

		rowCount++;
		row = sheet.createRow(rowCount);
		this.cellStyleLoop(0, 0, nameCellStyle, row, cell, "");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 2, maxCellCount));
		this.cellStyleLoop(2, maxCellCount, valueCellStyle, row, cell, "test");

		rowCount++;
		row = sheet.createRow(rowCount);
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount+3, 0, 0));
		this.cellStyleLoop(0, 0, nameCellStyle, row, cell, "대상자");
		this.cellStyleLoop(1, 1, nameCellStyle, row, cell, "성명");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 2, 4));
		this.cellStyleLoop(2, 4, valueCellStyle, row, cell, "test");
		this.cellStyleLoop(5, 5, nameCellStyle, row, cell, "성별");
		this.cellStyleLoop(6, 6, valueCellStyle, row, cell, "test");
		this.cellStyleLoop(7, 7, nameCellStyle, row, cell, "연령");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 8, 9));
		this.cellStyleLoop(8, 9, valueCellStyle, row, cell, "test");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 10, 11));
		this.cellStyleLoop(10, 10, nameCellStyle, row, cell, "내/외국인");
		this.cellStyleLoop(maxCellCount, maxCellCount, valueCellStyle, row, cell, "test");

		rowCount++;
		row = sheet.createRow(rowCount);
		this.cellStyleLoop(0, 0, nameCellStyle, row, cell, "");
		this.cellStyleLoop(1, 1, nameCellStyle, row, cell, "연락처");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 2, 4));
		this.cellStyleLoop(2, 4, valueCellStyle, row, cell, "test");
		this.cellStyleLoop(5, 5, nameCellStyle, row, cell, "직업");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 6, maxCellCount));
		this.cellStyleLoop(6, maxCellCount, valueCellStyle, row, cell, "test");

		rowCount++;
		row = sheet.createRow(rowCount);
		this.cellStyleLoop(0, 0, nameCellStyle, row, cell, "");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount+1, 1, 1));
		this.cellStyleLoop(1, 1, nameCellStyle, row, cell, "주소");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 2, maxCellCount));
		this.cellStyleLoop(2, maxCellCount, valueCellStyle, row, cell, "test");

		rowCount++;
		row = sheet.createRow(rowCount);
		this.cellStyleLoop(0, 0, nameCellStyle, row, cell, "");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 2, maxCellCount));
		this.cellStyleLoop(2, maxCellCount, valueCellStyle, row, cell, "test");

		rowCount++;
		row = sheet.createRow(rowCount);
		this.cellStyleLoop(0, 0, nameCellStyle, row, cell, "정보취득경로");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 1, 4));
		this.cellStyleLoop(1, 4, valueCellStyle, row, cell, "");
		this.cellStyleLoop(5, 5, nameCellStyle, row, cell, "문제종류");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 6, maxCellCount));
		this.cellStyleLoop(6, maxCellCount, valueCellStyle, row, cell, "");

		rowCount++;
		row = sheet.createRow(rowCount);
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount+1, 0, 0));
		this.cellStyleLoop(0, 0, nameCellStyle, row, cell, "사용약물");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 1, 2));
		this.cellStyleLoop(1, 2, titlaValueCellStyle, row, cell, "최초사용약물");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 3, maxCellCount));
		this.cellStyleLoop(3, maxCellCount, valueCellStyle, row, cell, "");

		rowCount++;
		row = sheet.createRow(rowCount);
		this.cellStyleLoop(0, 0, nameCellStyle, row, cell, "");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 1, 2));
		this.cellStyleLoop(1, 2, titlaValueCellStyle, row, cell, "주요사용약물");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 3, maxCellCount));
		this.cellStyleLoop(3, maxCellCount, valueCellStyle, row, cell, "");

		rowCount++;
		row = sheet.createRow(rowCount);
		this.cellStyleLoop(0, 0, nameCellStyle, row, cell, "상담유형");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 1, 4));
		this.cellStyleLoop(1, 4, valueCellStyle, row, cell, "");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 5, 6));
		this.cellStyleLoop(5, 6, nameCellStyle, row, cell, "상담이력");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 7, maxCellCount));
		this.cellStyleLoop(7, maxCellCount, valueCellStyle, row, cell, "");

		rowCount++;
		row = sheet.createRow(rowCount);
		this.cellStyleLoop(0, 0, nameCellStyle, row, cell, "주요조치");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 1, maxCellCount));
		this.cellStyleLoop(1, maxCellCount, valueCellStyle, row, cell, "");

		rowCount++;
		row = sheet.createRow(rowCount);
		this.cellStyleLoop(0, 0, nameCellStyle, row, cell, "위기분류척도");
		this.cellStyleLoop(1, 1, nameCellStyle, row, cell, "점수");
		this.cellStyleLoop(2, 2, valueCellStyle, row, cell, "");
		this.cellStyleLoop(3, 3, nameCellStyle, row, cell, "A");
		this.cellStyleLoop(4, 4, valueCellStyle, row, cell, "");
		this.cellStyleLoop(5, 5, nameCellStyle, row, cell, "B");
		this.cellStyleLoop(6, 6, valueCellStyle, row, cell, "");
		this.cellStyleLoop(7, 7, nameCellStyle, row, cell, "C");
		this.cellStyleLoop(8, 8, valueCellStyle, row, cell, "");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 9, maxCellCount));
		this.cellStyleLoop(9, maxCellCount, valueCellStyle, row, cell, "");

		rowCount++;
		row = sheet.createRow(rowCount);
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount+5, 0, 0));
		this.cellStyleLoop(0, 0, nameCellStyle, row, cell, "상담내용");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount+5, 1, maxCellCount));

		rowCount += 6;
		row = sheet.createRow(rowCount);
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount+4, 0, 0));
		this.cellStyleLoop(0, 0, nameCellStyle, row, cell, "상담결과");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount+4, 1, maxCellCount));

		rowCount += 5;
		row = sheet.createRow(rowCount);
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 0, maxCellCount));
		this.cellStyleLoop(0, maxCellCount, nameCellStyle, row, cell, "등록전 사정평가");

		rowCount++;
		row = sheet.createRow(rowCount);
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 0, 1));
		this.cellStyleLoop(0, 1, nameCellStyle, row, cell, "평가도구");
		this.cellStyleLoop(2, 2, nameCellStyle, row, cell, "점수");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 3, maxCellCount));
		this.cellStyleLoop(3, maxCellCount, nameCellStyle, row, cell, "내용");
	}

	@Override
	protected Workbook createWorkbook(){
		return new XSSFWorkbook();
	}
}
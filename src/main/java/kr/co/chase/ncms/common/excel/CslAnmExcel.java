package kr.co.chase.ncms.common.excel;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import kr.co.chase.ncms.common.ConstantObject;
import kr.co.chase.ncms.common.util.DateUtil;

public class CslAnmExcel extends AbstractExcelView{
	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook) throws Exception{
		HashMap<String, Object> cslInfo = (HashMap<String, Object>)model.get("cslInfo");		
		String mbrNm = StringUtils.defaultIfEmpty((String)model.get("mbrNm"), "");
		String gendNm = StringUtils.defaultIfEmpty((String)model.get("gendNm"), "");
		String age = StringUtils.defaultIfEmpty((String)model.get("age"), "");
		String regDt = StringUtils.defaultIfEmpty((String)model.get("regDt"), "");
		String medicCareNm = StringUtils.defaultIfEmpty((String)model.get("medicCareNm"), "");
		String mngUsrId = StringUtils.defaultIfEmpty((String)model.get("mngUsrId"), "");
		String lawPbmList = StringUtils.defaultIfEmpty((String)model.get("lawPbmList"), "");
		String sheetName = StringUtils.defaultIfEmpty((String)model.get("sheetName"), "");
		 
		Sheet sheet = workbook.createSheet(sheetName);
		sheet.setColumnWidth(0, (short) (27*32));
		sheet.setColumnWidth(1, (short) (87*32));
		sheet.setColumnWidth(2, (short) (122*32));
		sheet.setColumnWidth(3, (short) (150*32));
		sheet.setColumnWidth(4, (short) (122*32));
		sheet.setColumnWidth(5, (short) (150*32));
		
		Row row = null;
		Cell cell = null;
		int rowCount = 0;
		int tempRowCnt = 0;;
		int maxCellCount = 5;

		if(cslInfo == null) {
			cslInfo = new HashMap<String, Object>();
		}

		//셀 스타일 - title
		CellStyle titleCellStyle = workbook.createCellStyle();
		titleCellStyle.setAlignment(CellStyle.ALIGN_CENTER);					// 가운데 정렬
		titleCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);			// 높이 가운데 정렬
		//테두리 선(우, 좌, 위, 아래)
		titleCellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
		titleCellStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
		titleCellStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
		//폰트 설정
		Font titleFont = workbook.createFont();
		titleFont.setFontName("나눔고딕");											// 글씨체
		titleFont.setFontHeight((short)(24*20));								// 사이즈
//		titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);							// 볼드(굵게)
		titleFont.setBoldweight(Font.BOLDWEIGHT_NORMAL);							// 볼드(굵게)
		titleCellStyle.setFont(titleFont);
		
		//셀 스타일 - bottom
		CellStyle bottomCellStyle = workbook.createCellStyle();
		//테두리 선(우, 좌, 위, 아래)
		bottomCellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
		bottomCellStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
		bottomCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		bottomCellStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);

		
		//폰트 설정 - baisc 
		Font basicFont = workbook.createFont();
		basicFont.setFontName("나눔고딕");											// 글씨체
		basicFont.setFontHeight((short)(11*20));								// 사이즈
		basicFont.setBoldweight(Font.BOLDWEIGHT_NORMAL);						// 굵기

		//셀 스타일 - dot ALL 
		CellStyle basicCellStyle = workbook.createCellStyle();
		basicCellStyle.setWrapText(true);
		basicCellStyle.setAlignment(CellStyle.ALIGN_CENTER);						// 정렬
		basicCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);			// 높이 정렬
		//테두리 선(우, 좌, 위, 아래)
		basicCellStyle.setBorderRight(HSSFCellStyle.BORDER_HAIR);
		basicCellStyle.setBorderLeft(HSSFCellStyle.BORDER_HAIR);
		basicCellStyle.setBorderTop(HSSFCellStyle.BORDER_HAIR);
		basicCellStyle.setBorderBottom(HSSFCellStyle.BORDER_HAIR);
		basicCellStyle.setFont(basicFont);
		
		//셀 스타일 - dot & Left
		CellStyle basicLCellStyle = workbook.createCellStyle();
		basicLCellStyle.setWrapText(true);
		basicLCellStyle.setAlignment(CellStyle.ALIGN_CENTER);						// 정렬
		basicLCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);			// 높이 정렬
		//테두리 선(우, 좌, 위, 아래)
		basicLCellStyle.setBorderRight(HSSFCellStyle.BORDER_HAIR);
		basicLCellStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
		basicLCellStyle.setBorderTop(HSSFCellStyle.BORDER_HAIR);
		basicLCellStyle.setBorderBottom(HSSFCellStyle.BORDER_HAIR);
		basicLCellStyle.setFont(basicFont);
		
		//셀 스타일 - dot & right
		CellStyle basicRCellStyle = workbook.createCellStyle();
		basicRCellStyle.setWrapText(true);
		basicRCellStyle.setAlignment(CellStyle.ALIGN_CENTER);						// 정렬
		basicRCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);			// 높이 정렬
		//테두리 선(우, 좌, 위, 아래)
		basicRCellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
		basicRCellStyle.setBorderLeft(HSSFCellStyle.BORDER_HAIR);
		basicRCellStyle.setBorderTop(HSSFCellStyle.BORDER_HAIR);
		basicRCellStyle.setBorderBottom(HSSFCellStyle.BORDER_HAIR);
		basicRCellStyle.setFont(basicFont);
		
		//셀 스타일 - top Thin 
		CellStyle topCellStyle = workbook.createCellStyle();
		topCellStyle.setWrapText(true);
		topCellStyle.setAlignment(CellStyle.ALIGN_CENTER);						// 정렬
		topCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);			// 높이 정렬
		//테두리 선(우, 좌, 위, 아래)
		topCellStyle.setBorderRight(HSSFCellStyle.BORDER_HAIR);
		topCellStyle.setBorderLeft(HSSFCellStyle.BORDER_HAIR);
		topCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		topCellStyle.setBorderBottom(HSSFCellStyle.BORDER_HAIR);
		topCellStyle.setFont(basicFont);
		
		//셀 스타일 - top Thin & Left
		CellStyle topLCellStyle = workbook.createCellStyle();
		topLCellStyle.setWrapText(true);
		topLCellStyle.setAlignment(CellStyle.ALIGN_CENTER);						// 정렬
		topLCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);			// 높이 정렬
		//테두리 선(우, 좌, 위, 아래)
		topLCellStyle.setBorderRight(HSSFCellStyle.BORDER_HAIR);
		topLCellStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
		topLCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		topLCellStyle.setBorderBottom(HSSFCellStyle.BORDER_HAIR);
		topLCellStyle.setFont(basicFont);
		
		//셀 스타일 - top Thin & right
		CellStyle topRCellStyle = workbook.createCellStyle();
		topRCellStyle.setWrapText(true);
		topRCellStyle.setAlignment(CellStyle.ALIGN_CENTER);						// 정렬
		topRCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);			// 높이 정렬
		//테두리 선(우, 좌, 위, 아래)
		topRCellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
		topRCellStyle.setBorderLeft(HSSFCellStyle.BORDER_HAIR);
		topRCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		topRCellStyle.setBorderBottom(HSSFCellStyle.BORDER_HAIR);
		topRCellStyle.setFont(basicFont);
		
		//셀 스타일 - top Thin & Left & right
		CellStyle topLRCellStyle = workbook.createCellStyle();
		topLRCellStyle.setWrapText(true);
		topLRCellStyle.setAlignment(CellStyle.ALIGN_CENTER);						// 정렬
		topLRCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);			// 높이 정렬
		//테두리 선(우, 좌, 위, 아래)
		topLRCellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
		topLRCellStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
		topLRCellStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
		topLRCellStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
		topLRCellStyle.setFont(basicFont);
		
		// 타이틀
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (54*15));
		this.cellStyleLoop(1, maxCellCount, titleCellStyle, row, cell, "병력 정보기록지");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 1, maxCellCount));
		
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		tempRowCnt = rowCount;
		this.cellStyleLoop(1, 1, topLCellStyle, row, cell, "기본정보");
		this.cellStyleLoop(2, 2, topCellStyle, row, cell, "회원명");
		this.cellStyleLoop(3, 3, topCellStyle, row, cell, StringUtils.defaultIfEmpty(mbrNm, ""));
		this.cellStyleLoop(4, 4, topCellStyle, row, cell, "회원등록번호");
		this.cellStyleLoop(5, 5, topRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("MBR_NO"), ""));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "성별");
		this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty(gendNm, ""));
		this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "나이");
		this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("AGE"), ""));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "등록일자");
		this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty(regDt, ""));
		this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "의료보장");
		this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty(medicCareNm, ""));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "기관명");
		this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("MNG_SITE_NM"), ""));
		this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "사례관리자");
		this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty(mngUsrId, ""));
		sheet.addMergedRegion(new CellRangeAddress(tempRowCnt, tempRowCnt+3, 1, 1));
		
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		tempRowCnt = rowCount;
		this.cellStyleLoop(1, 1, topLCellStyle, row, cell, "중독력");
		this.cellStyleLoop(2, 2, topCellStyle, row, cell, "최초사용약물");
		this.cellStyleLoop(3, 5, topRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("FST_DRUG_NM"), ""));
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 3, 5));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "기타");
		this.cellStyleLoop(3, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("FST_DRUG"), ""));
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 3, 5));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "주요사용약물");
		this.cellStyleLoop(3, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("MAIN_DRUG_NM"), ""));
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 3, 5));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "기타");
		this.cellStyleLoop(3, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("MAIN_DRUG"), ""));
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 3, 5));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "최초 사용시기");
		this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("FST_AGE"), ""));
		this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "마지막 사용시기");
		this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("LST_AGE"), ""));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "사용기간");
		this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("USE_TERM"), ""));
		this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "사용빈도");
		this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("USE_FRQ_NM"), ""));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "사용원인");
		this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("USE_CAU_NM"), ""));
		this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "기타");
		this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("USE_CAU_ETC"), ""));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "약물관련법적문제");
		this.cellStyleLoop(3, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty(lawPbmList, ""));
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 3, 5));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "신체적 건강문제");
		this.cellStyleLoop(3, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("PHYS_PBM"), ""));
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 3, 5));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "정신적 건강문제");
		this.cellStyleLoop(3, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("SPRT_PBM_NM"), ""));
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 3, 5));
		sheet.addMergedRegion(new CellRangeAddress(tempRowCnt, tempRowCnt+9, 1, 1));
		
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		tempRowCnt = rowCount;
		this.cellStyleLoop(1, 1, topLCellStyle, row, cell, "자살시도력");
		this.cellStyleLoop(2, 2, topCellStyle, row, cell, "입력일자");
		this.cellStyleLoop(3, 3, topCellStyle, row, cell, DateUtil.getDateFormat(StringUtils.defaultIfEmpty((String)cslInfo.get("SUD_INDT"), ""), "-"));
		this.cellStyleLoop(4, 4, topCellStyle, row, cell, "시도나이");
		this.cellStyleLoop(5, 5, topRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("SUD_AGE"), ""));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "문제유형");
		this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("SUD_TYPE_NM"), ""));
		this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "정신건강문제");
		this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("SUD_SOUL_NM"), ""));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "시도방법");
		this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("SUD_WAY_NM"), ""));
		this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "기타");
		this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("SUD_WAY_ETC"), ""));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (139*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "상세내용");
		this.cellStyleLoop(3, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("SUD_CTNT"), ""));
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 3, 5));
		sheet.addMergedRegion(new CellRangeAddress(tempRowCnt, tempRowCnt+3, 1, 1));
		
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		tempRowCnt = rowCount;
		this.cellStyleLoop(1, 1, topLCellStyle, row, cell, "단약경험");
		this.cellStyleLoop(2, 2, topCellStyle, row, cell, "단약경험");
		this.cellStyleLoop(3, 3, topCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("CUREOFF_EXP"), ""));
		this.cellStyleLoop(4, 4, topCellStyle, row, cell, "단약횟수");
		this.cellStyleLoop(5, 5, topRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("CUREOFF_NUM"), ""));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "최장단약기간");
		this.cellStyleLoop(3, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("CUREOFF_DAY"), ""));
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 3, 5));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "단약이유");
		this.cellStyleLoop(3, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("CUREOFF_REASON"), ""));
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 3, 5));	
		sheet.addMergedRegion(new CellRangeAddress(tempRowCnt, tempRowCnt+2, 1, 1));
		
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		tempRowCnt = rowCount;
		this.cellStyleLoop(1, 5, topLRCellStyle, row, cell, "발달력");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 1, 5));
		
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		tempRowCnt = rowCount;
		this.cellStyleLoop(1, 1, topLCellStyle, row, cell, "영유아기");
		this.cellStyleLoop(2, 2, topCellStyle, row, cell, "임신");
		this.cellStyleLoop(3, 3, topCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("DEV_BABY_PREG_NM"), ""));
		this.cellStyleLoop(4, 4, topCellStyle, row, cell, "발육상태");
		this.cellStyleLoop(5, 5, topRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("DEB_BABY_DEV_NM"), ""));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "주양육자");
		this.cellStyleLoop(3, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("DEV_BABY_NURT_NM"), ""));
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 3, 5));	
		sheet.addMergedRegion(new CellRangeAddress(tempRowCnt, tempRowCnt+1, 1, 1));
		
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		tempRowCnt = rowCount;
		this.cellStyleLoop(1, 1, topLCellStyle, row, cell, "아동기");
		this.cellStyleLoop(2, 2, topCellStyle, row, cell, "훈육방식");
		this.cellStyleLoop(3, 3, topCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("DEV_CHILD_DISC_NM"), ""));
		this.cellStyleLoop(4, 4, topCellStyle, row, cell, "학습태도");
		this.cellStyleLoop(5, 5, topRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("DEV_CHILD_LEARN_NM"), ""));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "대인관계");
		this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("DEV_CHILD_RELATION_NM"), ""));
		this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "기타");
		this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("DEV_CHILD_TEC"), ""));
		sheet.addMergedRegion(new CellRangeAddress(tempRowCnt, tempRowCnt+1, 1, 1));
		
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		tempRowCnt = rowCount;
		this.cellStyleLoop(1, 1, topLCellStyle, row, cell, "청소년기");
		this.cellStyleLoop(2, 2, topCellStyle, row, cell, "학습태도");
		this.cellStyleLoop(3, 3, topCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("DEV_TEEN_LEARN_NM"), ""));
		this.cellStyleLoop(4, 4, topCellStyle, row, cell, "대인관계");
		this.cellStyleLoop(5, 5, topRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("DEV_TEEN_RELATION_NM"), ""));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "특이사항");
		this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("DEV_TEEN_UNI_NM"), ""));
		this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "기타");
		this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("DEV_TEEN_ETC"), ""));
		sheet.addMergedRegion(new CellRangeAddress(tempRowCnt, tempRowCnt+1, 1, 1));
		
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		tempRowCnt = rowCount;
		this.cellStyleLoop(1, 1, topLCellStyle, row, cell, "성인기");
		this.cellStyleLoop(2, 2, topCellStyle, row, cell, "대인관계");
		this.cellStyleLoop(3, 3, topCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("DEV_ADUL_RELATION_NM"), ""));
		this.cellStyleLoop(4, 4, topCellStyle, row, cell, "이성교제");
		this.cellStyleLoop(5, 5, topRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("DEV_ADUL_SEX_NM"), ""));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "기타");
		this.cellStyleLoop(3, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("DEV_ADUL_ETC"), ""));
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 3, 5));	
		sheet.addMergedRegion(new CellRangeAddress(tempRowCnt, tempRowCnt+1, 1, 1));
		
        rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (18*15));
		tempRowCnt = rowCount;
		this.cellStyleLoop(1, 5, bottomCellStyle, row, cell, "");
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (18*15));
		this.cellStyleLoop(1, 5, bottomCellStyle, row, cell, "");
		sheet.addMergedRegion(new CellRangeAddress(tempRowCnt, tempRowCnt+1, 1, 5));

		
		// 이미지
		String imagesPath = (String)model.get("imagesPath");
        InputStream is = new FileInputStream(imagesPath);
        byte[] bytes = IOUtils.toByteArray(is);
        int picIdx = workbook.addPicture(bytes, XSSFWorkbook.PICTURE_TYPE_PNG);
        is.close();

        XSSFCreationHelper helper = (XSSFCreationHelper) workbook.getCreationHelper();
        XSSFDrawing drawing = (XSSFDrawing) sheet.createDrawingPatriarch();
        XSSFClientAnchor anchor = helper.createClientAnchor();
          
        // 이미지 출력할 cell 위치
        anchor.setCol1(2);
        anchor.setRow1(tempRowCnt);
        // 이미지 그리기
        XSSFPicture pic = drawing.createPicture(anchor, picIdx);
        pic.resize();
        
	}

	@Override
	protected Workbook createWorkbook(){
		return new XSSFWorkbook();
	}
}
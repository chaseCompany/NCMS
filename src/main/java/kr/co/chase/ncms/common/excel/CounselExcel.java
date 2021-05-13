package kr.co.chase.ncms.common.excel;

import java.io.FileInputStream;
import java.io.InputStream;
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
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import kr.co.chase.ncms.common.util.DateUtil;

public class CounselExcel extends AbstractExcelView{
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook) throws Exception{
		@SuppressWarnings("unchecked")
		HashMap<String, Object> cslInfo = (HashMap<String, Object>)model.get("cslInfo");
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
		int tempRowCnt = 0;
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
		
		// 타이틀
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (54*15));
		this.cellStyleLoop(1, maxCellCount, titleCellStyle, row, cell, "중독예방 상담기록지");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 1, maxCellCount));
		
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		tempRowCnt = rowCount;
		this.cellStyleLoop(1, 1, topLCellStyle, row, cell, "기본정보");
		this.cellStyleLoop(2, 2, topCellStyle, row, cell, "상담일자");
		this.cellStyleLoop(3, 3, topCellStyle, row, cell, DateUtil.getDateFormat(StringUtils.defaultIfEmpty((String)cslInfo.get("CSL_DT"), ""), "-"));
		this.cellStyleLoop(4, 4, topCellStyle, row, cell, "상담시간");
		this.cellStyleLoop(5, 5, topRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("CSL_FM_TM"), "")
				  + "~"
				  + StringUtils.defaultIfEmpty((String)cslInfo.get("CSL_TO_TM"), "")
				  + "("
				  + (cslInfo.get("CSL_TERM_TM") == null ? "" : String.valueOf(cslInfo.get("CSL_TERM_TM")) + "분)"));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "상담기관");
		this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("CSL_SITE_NM"), ""));
		this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "상담자");
		this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("CSL_NM"), ""));
		sheet.addMergedRegion(new CellRangeAddress(tempRowCnt, tempRowCnt+1, 1, 1));
		
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, topLCellStyle, row, cell, "본인여부");
		this.cellStyleLoop(2, 2, topCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("IFP_GP_NM"), ""));
		this.cellStyleLoop(3, 3, topCellStyle, row, cell, "기타");
		this.cellStyleLoop(4, 4, topCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("IFP_GB_ETC"), ""));
		this.cellStyleLoop(5, 5, topRCellStyle, row, cell, "");
		
		rowCount++;
		row = sheet.createRow(rowCount);
		tempRowCnt = rowCount;
		row.setHeight((short) (26*15));
//		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount+3, 1, 1));
		this.cellStyleLoop(1, 1, topLCellStyle, row, cell, "정보제공자");
		this.cellStyleLoop(2, 2, topCellStyle, row, cell, "성명");
		this.cellStyleLoop(3, 3, topCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("IFP_NM"), ""));
		this.cellStyleLoop(4, 4, topCellStyle, row, cell, "회원번호");
		this.cellStyleLoop(5, 5, topRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("IFP_MBR_NO"), ""));
		
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "성별");
		this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("IFP_GEND_NM"), ""));
		this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "나이");
		this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("IFP_AGE_NM"), ""));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "연락처");
			String ifpTelNo = StringUtils.defaultIfEmpty((String)cslInfo.get("IFP_TEL_NO1"), "");
			if(!"".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("IFP_TEL_NO2"), ""))) {
				ifpTelNo += "-" + StringUtils.defaultIfEmpty((String)cslInfo.get("IFP_TEL_NO2"), "");
				if(!"".equals(StringUtils.defaultIfEmpty(cslInfo.get("IFP_TEL_NO3").toString(), ""))) {
					ifpTelNo += "-" + StringUtils.defaultIfEmpty(cslInfo.get("IFP_TEL_NO3").toString(), "");
				}
			}
		this.cellStyleLoop(3, 3, basicCellStyle, row, cell, ifpTelNo);
		this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "직업");
		this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("IFP_JOB_NM"), ""));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "지역");
		this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("IFP_AREA_NM"), ""));
		this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "기타");
		this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("IFP_AREA_ETC"), ""));
		sheet.addMergedRegion(new CellRangeAddress(tempRowCnt, tempRowCnt+3, 1, 1));
		
		rowCount++;
		row = sheet.createRow(rowCount);
		tempRowCnt = rowCount;
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, topLCellStyle, row, cell, "대상자");
		this.cellStyleLoop(2, 2, topCellStyle, row, cell, "성명");
		this.cellStyleLoop(3, 3, topCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("TGP_NM"), ""));
		this.cellStyleLoop(4, 4, topCellStyle, row, cell, "회원번호");
		this.cellStyleLoop(5, 5, topRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("TGP_MBR_NO"), ""));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "성별");
		this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("TGP_GEND_NM"), ""));
		this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "나이");
		this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("TGP_AGE_NM"), ""));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "연락처");
		String tgpTelNo = StringUtils.defaultIfEmpty((String)cslInfo.get("TGP_TEL_NO1"), "");
		if(!"".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("TGP_TEL_NO2"), ""))) {
			tgpTelNo += "-" + StringUtils.defaultIfEmpty((String)cslInfo.get("TGP_TEL_NO2"), "");
			if(!"".equals(StringUtils.defaultIfEmpty(cslInfo.get("TGP_TEL_NO3").toString(), ""))) {
				tgpTelNo += "-" + StringUtils.defaultIfEmpty(cslInfo.get("TGP_TEL_NO3").toString(), "");
			}
		}
		this.cellStyleLoop(3, 3, basicCellStyle, row, cell, tgpTelNo);
		this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "직업");
		this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("TGP_JOB_NM"), ""));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "지역");
		this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("TGP_AREA_NM"), ""));
		this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "기타");
		this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("TGP_AREA_ETC"), ""));
		sheet.addMergedRegion(new CellRangeAddress(tempRowCnt, tempRowCnt+3, 1, 1));
		
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		tempRowCnt = rowCount;
		this.cellStyleLoop(1, 1, topLCellStyle, row, cell, new XSSFRichTextString("상담관련\r\n정보").toString());
		this.cellStyleLoop(2, 2, topCellStyle, row, cell, "정보취득경로");
		this.cellStyleLoop(3, 3, topCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("IF_PATH_NM"), ""));
		this.cellStyleLoop(4, 4, topCellStyle, row, cell, "주호소문제");
		this.cellStyleLoop(5, 5, topRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("PBM_KND_NM"), ""));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "상담유형");
		this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("CSL_TP_NM"), ""));
		this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "주요조치");
		this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("MJR_MNG_NM"), ""));
		sheet.addMergedRegion(new CellRangeAddress(tempRowCnt, tempRowCnt+1, 1, 1));

		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		tempRowCnt = rowCount;
		this.cellStyleLoop(1, 1, topLCellStyle, row, cell, new XSSFRichTextString("약물관련\r\n정보").toString());
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
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "ASSIST점수");
		this.cellStyleLoop(3, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("AST_SCO"), ""));
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 3, 5));
		sheet.addMergedRegion(new CellRangeAddress(tempRowCnt, tempRowCnt+4, 1, 1));
		
		// 위기분류척도 미기입시 양식지 제외
		if( !( "0".equals(cslInfo.get("RSKA_TP_CD")) && "0".equals(cslInfo.get("RSKB_TP_CD")) && "0".equals(cslInfo.get("RSKC_TP_CD"))
				&& ( "0".equals(cslInfo.get("RSK_SCO")) || "".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("RSK_SCO"), "")) )
				&& "".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("CSL_EMER"), ""))
				&& "70".equals(cslInfo.get("URS_CD")) 
			) ){
		
			rowCount++;
			row = sheet.createRow(rowCount);
			row.setHeight((short) (26*15));
			tempRowCnt = rowCount;
			this.cellStyleLoop(1, 1, topLCellStyle, row, cell, new XSSFRichTextString("위기분류\r\n척도").toString());
			this.cellStyleLoop(2, 2, topCellStyle, row, cell, "위험성(A)");
			this.cellStyleLoop(3, 5, topRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("RSKA_TP_NM"), ""));
			sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 3, 5));
			rowCount++;
			row = sheet.createRow(rowCount);
			row.setHeight((short) (26*15));
			this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
			this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "지지체계(B)");
			this.cellStyleLoop(3, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("RSKB_TP_NM"), ""));
			sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 3, 5));
			rowCount++;
			row = sheet.createRow(rowCount);
			row.setHeight((short) (26*15));
			this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
			this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "협조능력(C)");
			this.cellStyleLoop(3, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("RSKC_TP_NM"), ""));
			sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 3, 5));
			rowCount++;
			row = sheet.createRow(rowCount);
			row.setHeight((short) (26*15));
			this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
			this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "점수");
			this.cellStyleLoop(3, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("RSK_SCO"), ""));
			sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 3, 5));
			rowCount++;
			row = sheet.createRow(rowCount);
			row.setHeight((short) (26*15));
			this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
			this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "위기상담");
			this.cellStyleLoop(3, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("CSL_EMER"), ""));
			sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 3, 5));
			rowCount++;
			row = sheet.createRow(rowCount);
			row.setHeight((short) (26*15));
			this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
			this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "URS");
			this.cellStyleLoop(3, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("URS_NM"), ""));
			sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 3, 5));
			sheet.addMergedRegion(new CellRangeAddress(tempRowCnt, tempRowCnt+5, 1, 1));
			
		}

		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (139*15));
		this.cellStyleLoop(1, 1, topLCellStyle, row, cell, "상담내용");
		this.cellStyleLoop(2, 5, topRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("CSL_CTNT"), ""));
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 2, 5));
		
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (139*15));
		this.cellStyleLoop(1, 1, topLCellStyle, row, cell, "상담결과");
		this.cellStyleLoop(2, 5, topRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("CSL_RST"), ""));
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 2, 5));
		
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
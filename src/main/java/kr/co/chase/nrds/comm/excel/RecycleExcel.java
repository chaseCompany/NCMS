package kr.co.chase.nrds.comm.excel;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

import kr.co.chase.ncms.common.excel.AbstractExcelView;
import kr.co.chase.ncms.common.util.DateUtil;

public class RecycleExcel extends AbstractExcelView{
	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook) throws Exception{
		HashMap<String, Object> cslInfo = (HashMap<String, Object>)model.get("cslInfo");
		List<HashMap<String, Object>> mbrList = (List<HashMap<String, Object>>)model.get("mbrList");
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
		if(mbrList == null) {
			mbrList = new ArrayList<HashMap<String, Object>>();
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
		this.cellStyleLoop(1, maxCellCount, titleCellStyle, row, cell, "재활교육 프로그램");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 1, maxCellCount));
		
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		tempRowCnt = rowCount;
		this.cellStyleLoop(1, 1, topLCellStyle, row, cell, new XSSFRichTextString("프로그램\n정보").toString());
		this.cellStyleLoop(2, 2, topCellStyle, row, cell, "교육분류");
		this.cellStyleLoop(3, 5, topRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("PGM_ED_NM"), ""));
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 3, 5));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "교육과정명");
		this.cellStyleLoop(3, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("PGM_CLASS_NM_NM"), "")+" "+StringUtils.defaultIfEmpty((String)cslInfo.get("PGM_CLASS_SUB_NM"), ""));
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 3, 5));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "회기");
		this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("PGM_SESSION"), ""));
		this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "기수");
		this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("PGM_CLASS"), ""));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "주강사");
		this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("PGM_MAIN_LEC"), ""));
		this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "보조강사");
		this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("PGM_SUB_LEC"), ""));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "교육기간");
		this.cellStyleLoop(3, 5, basicRCellStyle, row, cell, 
				StringUtils.defaultIfEmpty((String)cslInfo.get("PGM_START_DT"), "") + " " + StringUtils.defaultIfEmpty((String)cslInfo.get("PGM_START_TM"), "")
				+" ~ "+StringUtils.defaultIfEmpty((String)cslInfo.get("PGM_END_DT"), "") + " " + StringUtils.defaultIfEmpty((String)cslInfo.get("PGM_END_TM"), "")
		);		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 3, 5));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "교육일시");
		this.cellStyleLoop(3, 5, basicRCellStyle, row, cell, 
				StringUtils.defaultIfEmpty((String)cslInfo.get("PGM_CLASS_START_DT"), "") + " " + StringUtils.defaultIfEmpty((String)cslInfo.get("PGM_CLASS_START_TM"), "")
				+" ~ "+StringUtils.defaultIfEmpty((String)cslInfo.get("PGM_CLASS_END_DT"), "") + " " + StringUtils.defaultIfEmpty((String)cslInfo.get("PGM_CLASS_END_TM"), "")
		);		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 3, 5));		rowCount++;		row = sheet.createRow(rowCount);		row.setHeight((short) (26*15));		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "기관명");		this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("PGM_AGENT_NM"), ""));		this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "담당자");		this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("PGM_MNG_USR_ID"), ""));		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "교육주제");
		this.cellStyleLoop(3, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("PGM_SUBJECT"), ""));
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 3, 5));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "교육목표");
		this.cellStyleLoop(3, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("PGM_GOAL"), ""));
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 3, 5));
		sheet.addMergedRegion(new CellRangeAddress(tempRowCnt, tempRowCnt+8, 1, 1));
		
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (139*15));
		this.cellStyleLoop(1, 1, topLCellStyle, row, cell, new XSSFRichTextString("교육\n내용").toString());
		this.cellStyleLoop(2, 5, topRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("PGM_CTNT"), "").replaceAll("\r\n", "\n"));
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 2, 5));
		
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (139*15));
		this.cellStyleLoop(1, 1, topLCellStyle, row, cell, new XSSFRichTextString("교육\n결과").toString());
		this.cellStyleLoop(2, 5, topRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("PGM_RST"), "").replaceAll("\r\n", "\n"));
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 2, 5));				rowCount++;		row = sheet.createRow(rowCount);		row.setHeight((short) (26*15));
		tempRowCnt = rowCount;		this.cellStyleLoop(1, 1, topLCellStyle, row, cell, new XSSFRichTextString("인적자원\n등록").toString());		this.cellStyleLoop(2, 2, topCellStyle, row, cell, "직원");		this.cellStyleLoop(3, 5, topRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("PGM_EMP"), ""));
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 3, 5));
		rowCount++;		row = sheet.createRow(rowCount);		row.setHeight((short) (26*15));		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "자원봉사자");		this.cellStyleLoop(3, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("PGM_VOL"), ""));
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 3, 5));
		sheet.addMergedRegion(new CellRangeAddress(tempRowCnt, rowCount, 1, 1));

		if(mbrList.size() > 0) {
			for(int i=0; i<mbrList.size(); i++) {
				rowCount++;
				row = sheet.createRow(rowCount);
				row.setHeight((short) (26*15));
				if(i == 0) tempRowCnt = rowCount;
				this.cellStyleLoop(1, 1, (i == 0 ? topLCellStyle : basicLCellStyle), row, cell, (i == 0 ? new XSSFRichTextString("참여자\n관련").toString() : ""));
				this.cellStyleLoop(2, 2, (i == 0 ? topCellStyle : basicCellStyle), row, cell, StringUtils.defaultIfEmpty((String)mbrList.get(i).get("MBR_NM"), ""));
				this.cellStyleLoop(3, 5, (i == 0 ? topRCellStyle : basicRCellStyle), row, cell, StringUtils.defaultIfEmpty((String)mbrList.get(i).get("PGM_USER_CNT"), "").replaceAll("\r\n", "\n"));
				sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 3, 5));
			}
			sheet.addMergedRegion(new CellRangeAddress(tempRowCnt, tempRowCnt+(mbrList.size()-1), 1, 1));
		}
		
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		tempRowCnt = rowCount;
		this.cellStyleLoop(1, 1, topLCellStyle, row, cell, "첨부파일");
		this.cellStyleLoop(2, 5, topRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("ORIGNL_FILE_NM"), ""));
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
		sheet.addMergedRegion(new CellRangeAddress(tempRowCnt, rowCount, 1, 5));

		
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
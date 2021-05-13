package kr.co.chase.ncms.common.excel;

import java.io.FileInputStream;
import java.io.InputStream;
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

public class MemberExcel extends AbstractExcelView{
	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook) throws Exception{
		HashMap<String, Object> mbrInfo = (HashMap<String, Object>)model.get("mbrInfo");
		List<HashMap<String, Object>> fmlyTreeFileList = (List<HashMap<String, Object>>)model.get("fmlyTreeFileList");
		String fileList1 = "";
		if(fmlyTreeFileList!=null && fmlyTreeFileList.size()>0) {
			for(int i=0;i<fmlyTreeFileList.size();i++) {
				if(i>0) {
					fileList1 += ","+fmlyTreeFileList.get(i).get("ORIGNL_FILE_NM");
				}else {
					fileList1 += fmlyTreeFileList.get(i).get("ORIGNL_FILE_NM");
				}
			}
		}
		List<HashMap<String, Object>> personalInfoFileList = (List<HashMap<String, Object>>)model.get("personalInfoFileList");
		String fileList2 = "";
		if(personalInfoFileList!=null && personalInfoFileList.size()>0) {
			for(int i=0;i<personalInfoFileList.size();i++) {
				if(i>0) {
					fileList2 += ","+personalInfoFileList.get(i).get("ORIGNL_FILE_NM");
				}else {
					fileList2 += personalInfoFileList.get(i).get("ORIGNL_FILE_NM");
				}
			}
		}
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

		if(mbrInfo == null) {
			mbrInfo = new HashMap<String, Object>();
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
		this.cellStyleLoop(1, maxCellCount, titleCellStyle, row, cell, "회원정보관리 기록지");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 1, maxCellCount));
		
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		tempRowCnt = rowCount;
		this.cellStyleLoop(1, 1, topLCellStyle, row, cell, "회원정보");
		this.cellStyleLoop(2, 2, topCellStyle, row, cell, "성명");
		this.cellStyleLoop(3, 3, topCellStyle, row, cell, StringUtils.defaultIfEmpty((String)mbrInfo.get("MBR_NM"), ""));
		this.cellStyleLoop(4, 4, topCellStyle, row, cell, "회원등록번호");
		this.cellStyleLoop(5, 5, topRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)mbrInfo.get("MBR_NO"), ""));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "성별");
		this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty((String)mbrInfo.get("GEND_NM"), ""));
		this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "내/외국인");
		this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)mbrInfo.get("FRG_NM"), ""));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "생년월일");
		this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty((String)mbrInfo.get("JUMIN_NO1"), ""));
		this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "나이");
		this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)mbrInfo.get("AGE_NM"), ""));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "주소");
		this.cellStyleLoop(3, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)mbrInfo.get("ZIP_CD")+" "+(String)mbrInfo.get("ADDR1"), ""));
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 3, 5));
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount+1, 2, 2));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "");
		this.cellStyleLoop(3, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)mbrInfo.get("ADDR2"), ""));
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 3, 5));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "회원구분");
		this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty((String)mbrInfo.get("MBR_TP_NM"), ""));
		this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "약물사용자와의 관계");
		this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)mbrInfo.get("DRG_USE_NM"), ""));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "의료보장");
		this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty((String)mbrInfo.get("MEDI_CARE_NM"), ""));
		this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "학력");
		this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)mbrInfo.get("EDU_NM"), ""));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "결혼여부");
		this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty((String)mbrInfo.get("MRG_NM"), ""));
		this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "상담요청 경로");
		this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)mbrInfo.get("REQ_PATHG_NM"), ""));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "종교");
		this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty((String)mbrInfo.get("RLGN_NM"), ""));
		this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "직업");
		this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)mbrInfo.get("JOB_NM"), ""));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "재등록사유");
		this.cellStyleLoop(3, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)mbrInfo.get("DIS_ETC"), ""));
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 3, 5));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "첨부파일");
		this.cellStyleLoop(3, 5, basicRCellStyle, row, cell, fileList2);
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 3, 5));
		sheet.addMergedRegion(new CellRangeAddress(tempRowCnt, tempRowCnt+10, 1, 1));
		
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		tempRowCnt = rowCount;
		this.cellStyleLoop(1, 1, topLCellStyle, row, cell, new XSSFRichTextString("보호자\r\n정보").toString());
		this.cellStyleLoop(2, 2, topCellStyle, row, cell, "성명");
		this.cellStyleLoop(3, 3, topCellStyle, row, cell, StringUtils.defaultIfEmpty((String)mbrInfo.get("FMLY_NM"), ""));
		this.cellStyleLoop(4, 4, topCellStyle, row, cell, "관계");
		this.cellStyleLoop(5, 5, topRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)mbrInfo.get("FMLY_RELATION_NM"), ""));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "성별");
		this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty((String)mbrInfo.get("FMLY_GEND_NM"), ""));
		this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "연령");
		this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)mbrInfo.get("FMLY_AGE"), ""));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "지역");
		this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty((String)mbrInfo.get("FMLY_AREA"), ""));
		this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "연락처");
		this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)mbrInfo.get("FMLY_TEL_NO1")+(String)mbrInfo.get("FMLY_TEL_NO2")+(String)mbrInfo.get("FMLY_TEL_NO3"), ""));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "지지정도");
		this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty((String)mbrInfo.get("FMLY_GEND_NM"), ""));
		this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "동거여부");
		this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)mbrInfo.get("FMLY_TOGETHER_NM"), ""));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "가족사항");
		this.cellStyleLoop(3, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)mbrInfo.get("FMLY_RMK"), ""));
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 3, 5));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "가계도");
		this.cellStyleLoop(3, 5, basicRCellStyle, row, cell, fileList1);
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 3, 5));
		sheet.addMergedRegion(new CellRangeAddress(tempRowCnt, tempRowCnt+5, 1, 1));
		
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		tempRowCnt = rowCount;
		this.cellStyleLoop(1, 1, topLCellStyle, row, cell, "등록정보");
		this.cellStyleLoop(2, 2, topCellStyle, row, cell, "최초등록일자");
		this.cellStyleLoop(3, 3, topCellStyle, row, cell, StringUtils.defaultIfEmpty((String)mbrInfo.get("RG_CRE_DT"), ""));
		this.cellStyleLoop(4, 4, topCellStyle, row, cell, "최종수정일시");
		this.cellStyleLoop(5, 5, topRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)mbrInfo.get("RG_UPD_DT"), ""));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "사례관리자");
		this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty((String)mbrInfo.get("USR_NM"), ""));
		this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "최종수정자");
		this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)mbrInfo.get("UPD_NM"), ""));
		sheet.addMergedRegion(new CellRangeAddress(tempRowCnt, tempRowCnt+1, 1, 1));
		
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		tempRowCnt = rowCount;
		this.cellStyleLoop(1, 1, topLCellStyle, row, cell, "퇴록정보");
		this.cellStyleLoop(2, 2, topCellStyle, row, cell, "퇴록일자");
		this.cellStyleLoop(3, 5, topRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)mbrInfo.get("RL_CRE_DT"), ""));
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 3, 5));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "기관명");
		this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty((String)mbrInfo.get("INST_NM"), ""));
		this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "구분");
		this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)mbrInfo.get("REG_RLS_NM"), ""));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "형태");
		this.cellStyleLoop(3, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)mbrInfo.get("TYPE_NM"), ""));
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 3, 5));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (139*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "상세내용");
		this.cellStyleLoop(3, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)mbrInfo.get("DTL_CTNT"), ""));
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 3, 5));
		sheet.addMergedRegion(new CellRangeAddress(tempRowCnt, tempRowCnt+3, 1, 1));
		
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		tempRowCnt = rowCount;
		this.cellStyleLoop(1, 1, topLCellStyle, row, cell, "첨부파일");
		this.cellStyleLoop(2, 5, topRCellStyle, row, cell, "");
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
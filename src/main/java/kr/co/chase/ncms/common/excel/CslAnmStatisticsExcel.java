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

public class CslAnmStatisticsExcel extends AbstractExcelView{
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
		sheet.setColumnWidth(9, (short) (221*32));
		sheet.setColumnWidth(10, (short) (81*32));
		sheet.setColumnWidth(11, (short) (221*32));
		sheet.setColumnWidth(12, (short) (81*32));
		sheet.setColumnWidth(13, (short) (81*32));
		sheet.setColumnWidth(14, (short) (81*32));
		sheet.setColumnWidth(15, (short) (81*32));
		sheet.setColumnWidth(16, (short) (81*32));
		sheet.setColumnWidth(17, (short) (81*32));
		sheet.setColumnWidth(18, (short) (81*32));
		sheet.setColumnWidth(19, (short) (49*32));
		sheet.setColumnWidth(20, (short) (49*32));
		sheet.setColumnWidth(21, (short) (49*32));
		sheet.setColumnWidth(22, (short) (49*32));
		sheet.setColumnWidth(23, (short) (49*32));
		sheet.setColumnWidth(24, (short) (49*32));
		sheet.setColumnWidth(25, (short) (94*32));
		sheet.setColumnWidth(26, (short) (94*32));
		sheet.setColumnWidth(27, (short) (39*32));
		sheet.setColumnWidth(28, (short) (39*32));
		sheet.setColumnWidth(29, (short) (120*32));
		sheet.setColumnWidth(30, (short) (221*32));
		sheet.setColumnWidth(31, (short) (72*32));
		sheet.setColumnWidth(32, (short) (72*32));
		sheet.setColumnWidth(33, (short) (72*32));
		sheet.setColumnWidth(34, (short) (72*32));
		sheet.setColumnWidth(35, (short) (72*32));
		sheet.setColumnWidth(36, (short) (72*32));
		sheet.setColumnWidth(37, (short) (72*32));
		sheet.setColumnWidth(38, (short) (72*32));
		sheet.setColumnWidth(39, (short) (72*32));
		sheet.setColumnWidth(40, (short) (72*32));
		sheet.setColumnWidth(41, (short) (72*32));
		sheet.setColumnWidth(42, (short) (72*32));
		sheet.setColumnWidth(43, (short) (72*32));
		sheet.setColumnWidth(44, (short) (72*32));
		sheet.setColumnWidth(45, (short) (72*32));
		sheet.setColumnWidth(46, (short) (72*32));
		sheet.setColumnWidth(47, (short) (72*32));
		sheet.setColumnWidth(48, (short) (72*32));
		sheet.setColumnWidth(49, (short) (72*32));
		sheet.setColumnWidth(50, (short) (72*32));
		sheet.setColumnWidth(51, (short) (72*32));
		
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
		String[] titleArr = {"No", "회원명", "회원번호", "성별", "나이", "등록일자", "의료보장", "기관명", "사례관리자", "중독력", "단약경험", "자살시도력", "발달력"};
		String[] titleArr2 = {"", "", "", "", "", "", "", "", "", "최초 사용약물", "최초 사용약물\n세부항목", "주요사용약물", "주요 사용약물\n세부항목", "최초 사용시기", "마지막\n사용시기", "사용기간", "사용빈도", "사용원인", "사용원인\n세부항목", "약물관련 법적문제", "신체적\n건강문제", "정신적\n건강문제", "단약\n경험", "단약\n횟수", "최장 단약기간", "단약이유", "입력일자", "시도\n나이", "문제유형", "정신건강문제", "시도방법", "시도방법\n세부항목", "상세내용", "영유아기", "아동기", "청소년기", "성인기"};
		String[] titleArr3 = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "없음", "수사재판중", "기소유예", "집행유예", "실형", "기타", "", "", "", "", "", "", "", "", "", "", "", "", "", "임신", "발육상태", "주양육자", "훈육방식", "학습태도", "대인관계", "기타", "학습태도", "대인관계", "특이사항", "기타", "대인관계", "이성교제", "기타"};
		row = sheet.createRow(rowCount++);
		row.setHeight((short) (22*15));
		int colCnt = 0;
		for(int i=0; i < titleArr.length; i++) {
			if("중독력".equals(titleArr[i])) {
				int startCol = colCnt;
				colCnt += 17;
				this.cellStyleLoop(startCol, colCnt, titleCellStyle, row, cell, new XSSFRichTextString(titleArr[i]).toString());
				sheet.addMergedRegion(new CellRangeAddress(0, 0, startCol, colCnt));
			}else if("단약경험".equals(titleArr[i])) {
				int startCol = colCnt;
				colCnt += 3;
				this.cellStyleLoop(startCol, colCnt, titleCellStyle, row, cell, new XSSFRichTextString(titleArr[i]).toString());
				sheet.addMergedRegion(new CellRangeAddress(0, 0, startCol, colCnt));
			}else if("자살시도력".equals(titleArr[i])) {
				int startCol = colCnt;
				colCnt += 6;
				this.cellStyleLoop(startCol, colCnt, titleCellStyle, row, cell, new XSSFRichTextString(titleArr[i]).toString());
				sheet.addMergedRegion(new CellRangeAddress(0, 0, startCol, colCnt));
			}else if("발달력".equals(titleArr[i])) {
				int startCol = colCnt;
				colCnt += 13;
				this.cellStyleLoop(startCol, colCnt, titleCellStyle, row, cell, new XSSFRichTextString(titleArr[i]).toString());
				sheet.addMergedRegion(new CellRangeAddress(0, 0, startCol, colCnt));
			}else {
				this.cellStyleLoop(colCnt, colCnt, titleCellStyle, row, cell, new XSSFRichTextString(titleArr[i]).toString());
			}
			colCnt++;
		}
		
		row = sheet.createRow(rowCount++);
		row.setHeight((short) (22*15));
		int colCnt2 = 0;
		for(int i=0; i < titleArr2.length; i++) {
			if("약물관련 법적문제".equals(titleArr2[i])) {
				int startCol = colCnt2;
				colCnt2 += 5;
				this.cellStyleLoop(startCol, colCnt2, titleCellStyle, row, cell, new XSSFRichTextString(titleArr2[i]).toString());
				sheet.addMergedRegion(new CellRangeAddress(1, 1, startCol, colCnt2));
			}else if("영유아기".equals(titleArr2[i]) || "성인기".equals(titleArr2[i])) {
				int startCol = colCnt2;
				colCnt2 += 2;
				this.cellStyleLoop(startCol, colCnt2, titleCellStyle, row, cell, new XSSFRichTextString(titleArr2[i]).toString());
				sheet.addMergedRegion(new CellRangeAddress(1, 1, startCol, colCnt2));
			}else if("아동기".equals(titleArr2[i]) || "청소년기".equals(titleArr2[i])) {
				int startCol = colCnt2;
				colCnt2 += 3;
				this.cellStyleLoop(startCol, colCnt2, titleCellStyle, row, cell, new XSSFRichTextString(titleArr2[i]).toString());
				sheet.addMergedRegion(new CellRangeAddress(1, 1, startCol, colCnt2));
			}else {
				this.cellStyleLoop(colCnt2, colCnt2, titleCellStyle, row, cell, new XSSFRichTextString(titleArr2[i]).toString());
//				sheet.addMergedRegion(new CellRangeAddress(0, 1, colCnt2, colCnt2));
			}
			colCnt2++;
		}
		
		row = sheet.createRow(rowCount++);
		row.setHeight((short) (22*15));
		for(int i=0; i < titleArr3.length; i++) {
			this.cellStyleLoop(i, i, titleCellStyle, row, cell, new XSSFRichTextString(titleArr3[i]).toString());
			if(i < 9) {
				sheet.addMergedRegion(new CellRangeAddress(0, 2, i, i));
			}
			if((i > 8 && i < 19) || (i > 24 && i < 38)) {
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
			this.cellStyleLoop(9, 9, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("FST_DRUG_NM"), ""));
			this.cellStyleLoop(10, 10, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("FST_DRUG"), ""));
			this.cellStyleLoop(11, 11, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("MAIN_DRUG_NM"), ""));
			this.cellStyleLoop(12, 12, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("MAIN_DRUG"), ""));
			this.cellStyleLoop(13, 13, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("FST_AGE"), ""));
			this.cellStyleLoop(14, 14, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("LST_AGE"), ""));
			this.cellStyleLoop(15, 15, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("USE_TERM"), ""));
			this.cellStyleLoop(16, 16, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("USE_FRQ_NM"), ""));
			this.cellStyleLoop(17, 17, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("USE_CAU_NM"), ""));
			this.cellStyleLoop(18, 18, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("USE_CAU_ETC"), ""));
			String lawPbmCd = StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("LAW_PBM_CD"), "");
			this.cellStyleLoop(19, 19, contentCellStyle, row, cell, (lawPbmCd.indexOf("10") > 0 ? "v" : ""));
			this.cellStyleLoop(20, 20, contentCellStyle, row, cell, (lawPbmCd.indexOf("20") > 0 ? "v" : ""));
			this.cellStyleLoop(21, 21, contentCellStyle, row, cell, (lawPbmCd.indexOf("30") > 0 ? "v" : ""));
			this.cellStyleLoop(22, 22, contentCellStyle, row, cell, (lawPbmCd.indexOf("40") > 0 ? "v" : ""));
			this.cellStyleLoop(23, 23, contentCellStyle, row, cell, (lawPbmCd.indexOf("50") > 0 ? "v" : ""));
			this.cellStyleLoop(24, 24, contentCellStyle, row, cell, (lawPbmCd.indexOf("ZZ") > 0 ? "v" : ""));
			this.cellStyleLoop(25, 25, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("PHYS_PBM"), ""));
			this.cellStyleLoop(26, 26, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("SPRT_PBM_NM"), ""));
			this.cellStyleLoop(27, 27, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("CUREOFF_EXP"), ""));
			this.cellStyleLoop(28, 28, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("CUREOFF_NUM"), ""));
			this.cellStyleLoop(29, 29, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("CUREOFF_DAY"), ""));
			this.cellStyleLoop(30, 30, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("CUREOFF_REASON"), ""));
			this.cellStyleLoop(31, 31, contentCellStyle, row, cell, DateUtil.getDateFormat(StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("SUD_INDT"), ""), "-"));
			this.cellStyleLoop(32, 32, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("SUD_AGE"), ""));
			this.cellStyleLoop(33, 33, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("SUD_TYPE_NM"), ""));
			this.cellStyleLoop(34, 34, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("SUD_SOUL_NM"), ""));
			this.cellStyleLoop(35, 35, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("SUD_WAY_NM"), ""));
			this.cellStyleLoop(36, 36, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("SUD_WAY_ETC"), ""));
			this.cellStyleLoop(37, 37, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("SUD_CTNT"), ""));
			this.cellStyleLoop(38, 38, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("DEV_BABY_PREG_NM"), ""));
			this.cellStyleLoop(39, 39, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("DEB_BABY_DEV_NM"), ""));
			this.cellStyleLoop(40, 40, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("DEV_BABY_NURT_NM"), ""));
			this.cellStyleLoop(41, 41, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("DEV_CHILD_DISC_NM"), ""));
			this.cellStyleLoop(42, 42, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("DEV_CHILD_LEARN_NM"), ""));
			this.cellStyleLoop(43, 43, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("DEV_CHILD_RELATION_NM"), ""));
			this.cellStyleLoop(44, 44, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("DEV_CHILD_TEC"), ""));
			this.cellStyleLoop(45, 45, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("DEV_TEEN_LEARN_NM"), ""));
			this.cellStyleLoop(46, 46, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("DEV_TEEN_RELATION_NM"), ""));
			this.cellStyleLoop(47, 47, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("DEV_TEEN_UNI_NM"), ""));
			this.cellStyleLoop(48, 48, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("DEV_TEEN_ETC"), ""));
			this.cellStyleLoop(49, 49, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("DEV_ADUL_RELATION_NM"), ""));
			this.cellStyleLoop(50, 50, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("DEV_ADUL_SEX_NM"), ""));
			this.cellStyleLoop(51, 51, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("DEV_ADUL_ETC"), ""));
		}
	}

	@Override
	protected Workbook createWorkbook(){
		return new XSSFWorkbook();
	}
}
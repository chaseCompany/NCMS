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

public class IspStatisticsExcel extends AbstractExcelView{
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
		sheet.setColumnWidth(9, (short) (82*32));
		sheet.setColumnWidth(10, (short) (67*32));
		sheet.setColumnWidth(11, (short) (67*32));
		sheet.setColumnWidth(12, (short) (67*32));
		sheet.setColumnWidth(13, (short) (67*32));
		sheet.setColumnWidth(14, (short) (67*32));
		sheet.setColumnWidth(15, (short) (77*32));
		sheet.setColumnWidth(16, (short) (77*32));
		sheet.setColumnWidth(17, (short) (77*32));
		sheet.setColumnWidth(18, (short) (77*32));
		sheet.setColumnWidth(19, (short) (77*32));
		sheet.setColumnWidth(20, (short) (77*32));
		sheet.setColumnWidth(21, (short) (77*32));
		sheet.setColumnWidth(22, (short) (77*32));
		sheet.setColumnWidth(23, (short) (77*32));
		sheet.setColumnWidth(24, (short) (77*32));
		sheet.setColumnWidth(25, (short) (77*32));
		sheet.setColumnWidth(26, (short) (77*32));
		sheet.setColumnWidth(27, (short) (77*32));
		sheet.setColumnWidth(28, (short) (77*32));
		sheet.setColumnWidth(29, (short) (77*32));
		sheet.setColumnWidth(30, (short) (77*32));
		sheet.setColumnWidth(31, (short) (77*32));
		sheet.setColumnWidth(32, (short) (77*32));
		sheet.setColumnWidth(33, (short) (77*32));
		sheet.setColumnWidth(34, (short) (77*32));
		sheet.setColumnWidth(35, (short) (77*32));
		sheet.setColumnWidth(36, (short) (77*32));
		sheet.setColumnWidth(37, (short) (77*32));
		sheet.setColumnWidth(38, (short) (77*32));
		sheet.setColumnWidth(39, (short) (77*32));
		sheet.setColumnWidth(40, (short) (77*32));
		sheet.setColumnWidth(41, (short) (77*32));
		sheet.setColumnWidth(42, (short) (77*32));
		sheet.setColumnWidth(43, (short) (77*32));
		sheet.setColumnWidth(44, (short) (77*32));
		sheet.setColumnWidth(45, (short) (77*32));
		sheet.setColumnWidth(46, (short) (77*32));
		sheet.setColumnWidth(47, (short) (77*32));
		sheet.setColumnWidth(48, (short) (77*32));
		sheet.setColumnWidth(49, (short) (77*32));
		sheet.setColumnWidth(50, (short) (77*32));
		sheet.setColumnWidth(51, (short) (77*32));
		sheet.setColumnWidth(52, (short) (77*32));
		sheet.setColumnWidth(53, (short) (77*32));
		sheet.setColumnWidth(54, (short) (77*32));
		sheet.setColumnWidth(55, (short) (77*32));
		sheet.setColumnWidth(56, (short) (77*32));
		sheet.setColumnWidth(57, (short) (77*32));
		sheet.setColumnWidth(58, (short) (77*32));
		sheet.setColumnWidth(59, (short) (77*32));
		sheet.setColumnWidth(60, (short) (77*32));
		sheet.setColumnWidth(61, (short) (77*32));
		sheet.setColumnWidth(62, (short) (77*32));
		sheet.setColumnWidth(63, (short) (202*32));
		sheet.setColumnWidth(64, (short) (202*32));
		
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
		String[] titleArr = {"No", "회원명", "회원번호", "성별", "나이", "등록일자", "의료보장", "기관명", "사례관리자", "사정일자", "관리구분", "사례관리", "심리상담", "집단\r\n프로그램", "자원연계", "중독영역(급성중독과 금단)", "정신 및 신체영역", "위험성 영역", "사회적 관계영역", "사회서비스 평가영역", "기타영역", "ISP결과", "장단기 목표수립"};
		String[] titleArr2 = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "약물중독", "알코올중독", "도박중독", "인터넷중독", "기타중독", "정신과적 증상", "정신약물관리", "스트레스 상태", "신체질환", "자해/자살위험", "타해위험", "가족관계", "사회적 관계", "회복환경 관계", "주거", "경제활동 지원", "고용 및 교육가능성", "법률적문제", "취업 및 학업욕구", "영성", "봉사", "여가", "미래계획", "기타", "", ""};
		String[] titleArr3 = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "심각도", "자원연계", "심각도", "자원연계", "심각도", "자원연계", "심각도", "자원연계", "심각도", "자원연계", "심각도", "자원연계", "심각도", "자원연계", "심각도", "자원연계", "심각도", "자원연계", "심각도", "자원연계", "심각도", "자원연계", "심각도", "자원연계", "심각도", "자원연계", "심각도", "자원연계", "심각도", "자원연계", "심각도", "자원연계", "심각도", "자원연계", "", "자원연계", "욕구도", "자원연계", "욕구도", "자원연계", "욕구도", "자원연계", "욕구도", "자원연계", "욕구도", "자원연계", "욕구도", "자원연계", "", ""};
		row = sheet.createRow(rowCount++);
		row.setHeight((short) (22*15));
		int colCnt = 0;
		for(int i=0; i < titleArr.length; i++) {
			if("중독영역(급성중독과 금단)".equals(titleArr[i]) || "사회서비스 평가영역".equals(titleArr[i])  || "기타영역".equals(titleArr[i])) {
				int startCol = colCnt;
				colCnt += 9;
				this.cellStyleLoop(startCol, colCnt, titleCellStyle, row, cell, new XSSFRichTextString(titleArr[i]).toString());
				sheet.addMergedRegion(new CellRangeAddress(0, 0, startCol, colCnt));
			}else if("정신 및 신체영역".equals(titleArr[i])) {
				int startCol = colCnt;
				colCnt += 7;
				this.cellStyleLoop(startCol, colCnt, titleCellStyle, row, cell, new XSSFRichTextString(titleArr[i]).toString());
				sheet.addMergedRegion(new CellRangeAddress(0, 0, startCol, colCnt));
			}else if("위험성 영역".equals(titleArr[i])) {
				int startCol = colCnt;
				colCnt += 3;
				this.cellStyleLoop(startCol, colCnt, titleCellStyle, row, cell, new XSSFRichTextString(titleArr[i]).toString());
				sheet.addMergedRegion(new CellRangeAddress(0, 0, startCol, colCnt));
			}else if("사회적 관계영역".equals(titleArr[i])) {
				int startCol = colCnt;
				colCnt += 5;
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
			if(i > 14 && i < 39) {
				int startCol = colCnt2;
				colCnt2 += 1;
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
			if(i < 15 || i > 62) {
				sheet.addMergedRegion(new CellRangeAddress(0, 2, i, i));
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
			this.cellStyleLoop(7, 7, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("SITE_NM"), ""));
			this.cellStyleLoop(8, 8, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("MNG_USR_NM"), ""));
			this.cellStyleLoop(9, 9, contentCellStyle, row, cell, DateUtil.getDateFormat(StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("ISP_DT"), ""), "-"));
			this.cellStyleLoop(10, 10, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("MNG_TP_NM"), ""));
			String linkCd = StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("LINK_CD"), "");
			this.cellStyleLoop(11, 11, contentCellStyle, row, cell, (linkCd.indexOf("1") > 0 ? "v" : ""));
			this.cellStyleLoop(12, 12, contentCellStyle, row, cell, (linkCd.indexOf("2") > 0 ? "v" : ""));
			this.cellStyleLoop(13, 13, contentCellStyle, row, cell, (linkCd.indexOf("3") > 0 ? "v" : ""));
			this.cellStyleLoop(14, 14, contentCellStyle, row, cell, (linkCd.indexOf("4") > 0 ? "v" : ""));
			this.cellStyleLoop(15, 15, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("EVL_ITM_SCO01_NM"), ""));
			this.cellStyleLoop(16, 16, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("EVL_ITM_LNK01"), ""));
			this.cellStyleLoop(17, 17, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("EVL_ITM_SCO02_NM"), ""));
			this.cellStyleLoop(18, 18, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("EVL_ITM_LNK02"), ""));
			this.cellStyleLoop(19, 19, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("EVL_ITM_SCO03_NM"), ""));
			this.cellStyleLoop(20, 20, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("EVL_ITM_LNK03"), ""));
			this.cellStyleLoop(21, 21, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("EVL_ITM_SCO04_NM"), ""));
			this.cellStyleLoop(22, 22, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("EVL_ITM_LNK04"), ""));
			this.cellStyleLoop(23, 23, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("EVL_ITM_SCO17_NM"), ""));
			this.cellStyleLoop(24, 24, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("EVL_ITM_LNK17"), ""));
			this.cellStyleLoop(25, 25, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("EVL_ITM_SCO06_NM"), ""));
			this.cellStyleLoop(26, 26, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("EVL_ITM_LNK06"), ""));
			this.cellStyleLoop(27, 27, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("EVL_ITM_SCO07_NM"), ""));
			this.cellStyleLoop(28, 28, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("EVL_ITM_LNK07"), ""));
			this.cellStyleLoop(29, 29, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("EVL_ITM_SCO08_NM"), ""));
			this.cellStyleLoop(30, 30, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("EVL_ITM_LNK08"), ""));
			this.cellStyleLoop(31, 31, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("EVL_ITM_SCO09_NM"), ""));
			this.cellStyleLoop(32, 32, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("EVL_ITM_LNK09"), ""));
			this.cellStyleLoop(33, 33, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("EVL_ITM_SCO18_NM"), ""));
			this.cellStyleLoop(34, 34, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("EVL_ITM_LNK18"), ""));
			this.cellStyleLoop(35, 35, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("EVL_ITM_SCO19_NM"), ""));
			this.cellStyleLoop(36, 36, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("EVL_ITM_LNK19"), ""));
			this.cellStyleLoop(37, 37, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("EVL_ITM_SCO10_NM"), ""));
			this.cellStyleLoop(38, 38, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("EVL_ITM_LNK10"), ""));
			this.cellStyleLoop(39, 39, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("EVL_ITM_SCO11_NM"), ""));
			this.cellStyleLoop(40, 40, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("EVL_ITM_LNK11"), ""));
			this.cellStyleLoop(41, 41, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("EVL_ITM_SCO20_NM"), ""));
			this.cellStyleLoop(42, 42, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("EVL_ITM_LNK20"), ""));
			this.cellStyleLoop(43, 43, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("EVL_ITM_SCO12_NM"), ""));
			this.cellStyleLoop(44, 44, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("EVL_ITM_LNK12"), ""));
			this.cellStyleLoop(45, 45, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("EVL_ITM_SCO13_NM"), ""));
			this.cellStyleLoop(46, 46, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("EVL_ITM_LNK13"), ""));
			this.cellStyleLoop(47, 47, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("EVL_ITM_SCO15_NM"), ""));
			this.cellStyleLoop(48, 48, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("EVL_ITM_LNK15"), ""));
			this.cellStyleLoop(49, 49, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("EVL_ITM_SCO16_NM"), ""));
			this.cellStyleLoop(50, 50, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("EVL_ITM_LNK16"), ""));
			this.cellStyleLoop(51, 51, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("EVL_ITM_SCO14_NM"), ""));
			this.cellStyleLoop(52, 52, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("EVL_ITM_LNK14"), ""));
			this.cellStyleLoop(53, 53, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("EVL_ITM_SCO21_NM"), ""));
			this.cellStyleLoop(54, 54, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("EVL_ITM_LNK21"), ""));
			this.cellStyleLoop(55, 55, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("EVL_ITM_SCO22_NM"), ""));
			this.cellStyleLoop(56, 56, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("EVL_ITM_LNK22"), ""));
			this.cellStyleLoop(57, 57, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("EVL_ITM_SCO23_NM"), ""));
			this.cellStyleLoop(58, 58, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("EVL_ITM_LNK23"), ""));
			this.cellStyleLoop(59, 59, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("EVL_ITM_SCO24_NM"), ""));
			this.cellStyleLoop(60, 60, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("EVL_ITM_LNK24"), ""));
			this.cellStyleLoop(61, 61, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("EVL_ITM_SCO25_NM"), ""));
			this.cellStyleLoop(62, 62, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("EVL_ITM_LNK25"), ""));
			this.cellStyleLoop(63, 63, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("ISP_RST"), ""));
			this.cellStyleLoop(64, 64, contentCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get(i).get("TGT_CTNT"), ""));
		}
	}

	@Override
	protected Workbook createWorkbook(){
		return new XSSFWorkbook();
	}
}
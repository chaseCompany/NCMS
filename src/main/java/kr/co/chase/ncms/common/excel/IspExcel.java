package kr.co.chase.ncms.common.excel;

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

import kr.co.chase.ncms.common.util.DateUtil;

public class IspExcel extends AbstractExcelView{
	@Override
	@SuppressWarnings("unchecked")
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook) throws Exception{
		HashMap<String, Object> cslInfo = (HashMap<String, Object>)model.get("cslInfo");
		List<HashMap<String, Object>> linkCdList = (List<HashMap<String, Object>>)model.get("linkCdList");
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
		
		if(linkCdList == null) {
			linkCdList = new ArrayList<HashMap<String, Object>>();
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
		this.cellStyleLoop(1, maxCellCount, titleCellStyle, row, cell, "ISP수립 상담기록지");
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 1, maxCellCount));
		
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		tempRowCnt = rowCount;
		this.cellStyleLoop(1, 1, topLCellStyle, row, cell, "기본정보");
		this.cellStyleLoop(2, 2, topCellStyle, row, cell, "회원명");
		this.cellStyleLoop(3, 3, topCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("MBR_NM"), ""));
		this.cellStyleLoop(4, 4, topCellStyle, row, cell, "회원등록번호");
		this.cellStyleLoop(5, 5, topRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("MBR_NO"), ""));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "성별");
		this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("GEND_NM"), ""));
		this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "연령");
		this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("AGE_NM"), ""));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "등록일자");
		this.cellStyleLoop(3, 3, basicCellStyle, row, cell, DateUtil.getDateFormat(StringUtils.defaultIfEmpty((String)cslInfo.get("REG_DT"), ""), "-"));
		this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "의료보장");
		this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("MEDIC_CARE_NM"), ""));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "기관명");
		this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("MNG_SITE_NM"), ""));
		this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "사례관리자");
		this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("MNG_USR_NM"), ""));
		sheet.addMergedRegion(new CellRangeAddress(tempRowCnt, tempRowCnt+3, 1, 1));
		
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		tempRowCnt = rowCount;
		this.cellStyleLoop(1, 1, topLCellStyle, row, cell, new XSSFRichTextString("상담관련\r\n정보").toString());
		this.cellStyleLoop(2, 2, topCellStyle, row, cell, "사정일자");
		this.cellStyleLoop(3, 3, topCellStyle, row, cell, DateUtil.getDateFormat(StringUtils.defaultIfEmpty((String)cslInfo.get("ISP_DT"), ""), "-"));
		this.cellStyleLoop(4, 4, topCellStyle, row, cell, "관리구분");
		this.cellStyleLoop(5, 5, topRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("MNG_TP_NM"), ""));
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (26*15));
		this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
		this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "요청서비스");
		
		String linkCd = StringUtils.defaultIfEmpty((String)cslInfo.get("LINK_CD"), "");
		String linkNm= "";
		if(linkCd != ""){
			linkCd = linkCd.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(" ", "");
			String[] linkCdArr = linkCd.split(",");
			for (int i = 0; i < linkCdArr.length; i++) {
				String cd = linkCdArr[i];
				for (int j = 0; j < linkCdList.size(); j++) {
					if(linkCdList.get(j).get("CD_ID").equals(cd)) {
						linkNm += (linkNm != "" ?  ", " : "")+linkCdList.get(j).get("CD_NM");
					}
				}
			}
		}
		this.cellStyleLoop(3, 5, basicRCellStyle, row, cell, linkNm);
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 3, 5));
		sheet.addMergedRegion(new CellRangeAddress(tempRowCnt, tempRowCnt+1, 1, 1));
		
		if( !( "0".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_SCO01"), "")) && "".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_LNK01"), ""))
				&& "0".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_SCO02"), "")) && "".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_LNK02"), ""))
				&& "0".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_SCO03"), "")) && "".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_LNK03"), ""))
				&& "0".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_SCO04"), "")) && "".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_LNK04"), ""))
				&& "0".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_SCO17"), "")) && "".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_LNK17"), ""))
			) ){
			
			rowCount++;
			row = sheet.createRow(rowCount);
			row.setHeight((short) (26*15));
			tempRowCnt = rowCount;
			this.cellStyleLoop(1, 1, topLCellStyle, row, cell, new XSSFRichTextString("중독영역\r\n(급성중독과 금단)").toString());
			this.cellStyleLoop(2, 2, topCellStyle, row, cell, "약물중독");
			this.cellStyleLoop(3, 3, topCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_SCO01_NM"), ""));
			this.cellStyleLoop(4, 4, topCellStyle, row, cell, "자원연계");
			this.cellStyleLoop(5, 5, topRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_LNK01"), ""));
			rowCount++;
			row = sheet.createRow(rowCount);
			row.setHeight((short) (26*15));
			this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
			this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "알코올중독");
			this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_SCO02_NM"), ""));
			this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "자원연계");
			this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_LNK02"), ""));
			rowCount++;
			row = sheet.createRow(rowCount);
			row.setHeight((short) (26*15));
			this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
			this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "도박중독");
			this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_SCO03_NM"), ""));
			this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "자원연계");
			this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_LNK03"), ""));
			rowCount++;
			row = sheet.createRow(rowCount);
			row.setHeight((short) (26*15));
			this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
			this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "인터넷중독");
			this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_SCO04_NM"), ""));
			this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "자원연계");
			this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_LNK04"), ""));
			rowCount++;
			row = sheet.createRow(rowCount);
			row.setHeight((short) (26*15));
			this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
			this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "기타중독");
			this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_SCO17_NM"), ""));
			this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "자원연계");
			this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_LNK17"), ""));
			sheet.addMergedRegion(new CellRangeAddress(tempRowCnt, tempRowCnt+4, 1, 1));
		}
		
		if( !( "0".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_SCO06"), "")) && "".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_LNK06"), ""))
				&& "0".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_SCO07"), "")) && "".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_LNK07"), ""))
				&& "0".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_SCO08"), "")) && "".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_LNK08"), ""))
				&& "0".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_SCO09"), "")) && "".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_LNK09"), ""))
			) ){
			
			rowCount++;
			row = sheet.createRow(rowCount);
			row.setHeight((short) (26*15));
			tempRowCnt = rowCount;
			this.cellStyleLoop(1, 1, topLCellStyle, row, cell, new XSSFRichTextString("정신 및\r\n신체영역").toString());
			this.cellStyleLoop(2, 2, topCellStyle, row, cell, "정신과적 증상");
			this.cellStyleLoop(3, 3, topCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_SCO06_NM"), ""));
			this.cellStyleLoop(4, 4, topCellStyle, row, cell, "자원연계");
			this.cellStyleLoop(5, 5, topRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_LNK06"), ""));
			rowCount++;
			row = sheet.createRow(rowCount);
			row.setHeight((short) (26*15));
			this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
			this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "정신약물관리");
			this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_SCO07_NM"), ""));
			this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "자원연계");
			this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_LNK07"), ""));
			rowCount++;
			row = sheet.createRow(rowCount);
			row.setHeight((short) (26*15));
			this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
			this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "스트레스 상태");
			this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_SCO08_NM"), ""));
			this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "자원연계");
			this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_LNK08"), ""));
			rowCount++;
			row = sheet.createRow(rowCount);
			row.setHeight((short) (26*15));
			this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
			this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "신체질환");
			this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_SCO09_NM"), ""));
			this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "자원연계");
			this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_LNK09"), ""));
			sheet.addMergedRegion(new CellRangeAddress(tempRowCnt, tempRowCnt+3, 1, 1));
		}
		
		if( !( "0".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_SCO18"), "")) && "".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_LNK18"), ""))
				&& "0".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_SCO19"), "")) && "".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_LNK19"), ""))
			) ){
			
			rowCount++;
			row = sheet.createRow(rowCount);
			row.setHeight((short) (26*15));
			tempRowCnt = rowCount;
			this.cellStyleLoop(1, 1, topLCellStyle, row, cell, new XSSFRichTextString("위험성 영역").toString());
			this.cellStyleLoop(2, 2, topCellStyle, row, cell, "자해/자살위험");
			this.cellStyleLoop(3, 3, topCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_SCO18_NM"), ""));
			this.cellStyleLoop(4, 4, topCellStyle, row, cell, "자원연계");
			this.cellStyleLoop(5, 5, topRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_LNK18"), ""));
			rowCount++;
			row = sheet.createRow(rowCount);
			row.setHeight((short) (26*15));
			this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
			this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "타해위험");
			this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_SCO19_NM"), ""));
			this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "자원연계");
			this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_LNK19"), ""));
			sheet.addMergedRegion(new CellRangeAddress(tempRowCnt, tempRowCnt+1, 1, 1));
		}
		
		if( !( "0".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_SCO10"), "")) && "".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_LNK10"), ""))
				&& "0".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_SCO11"), "")) && "".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_LNK11"), ""))
				&& "0".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_SCO20"), "")) && "".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_LNK20"), ""))
				) ){
			
			rowCount++;
			row = sheet.createRow(rowCount);
			row.setHeight((short) (26*15));
			tempRowCnt = rowCount;
			this.cellStyleLoop(1, 1, topLCellStyle, row, cell, new XSSFRichTextString("사회적\r\n관계영역").toString());
			this.cellStyleLoop(2, 2, topCellStyle, row, cell, "가족관계");
			this.cellStyleLoop(3, 3, topCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_SCO10_NM"), ""));
			this.cellStyleLoop(4, 4, topCellStyle, row, cell, "자원연계");
			this.cellStyleLoop(5, 5, topRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_LNK10"), ""));
			rowCount++;
			row = sheet.createRow(rowCount);
			row.setHeight((short) (26*15));
			this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
			this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "사회적 관계");
			this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_SCO11_NM"), ""));
			this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "자원연계");
			this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_LNK11"), ""));
			rowCount++;
			row = sheet.createRow(rowCount);
			row.setHeight((short) (26*15));
			this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
			this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "회복환경 관계");
			this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_SCO20_NM"), ""));
			this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "자원연계");
			this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_LNK20"), ""));
			sheet.addMergedRegion(new CellRangeAddress(tempRowCnt, tempRowCnt+2, 1, 1));
		}
		
		if( !( "0".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_SCO12"), "")) && "".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_LNK12"), ""))
				&& "0".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_SCO13"), "")) && "".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_LNK13"), ""))
				&& "0".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_SCO15"), "")) && "".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_LNK15"), ""))
				&& "0".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_SCO16"), "")) && "".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_LNK16"), ""))
				&& "1".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_SCO14"), "")) && "".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_LNK14"), ""))
				) ){
			
			rowCount++;
			row = sheet.createRow(rowCount);
			row.setHeight((short) (26*15));
			tempRowCnt = rowCount;
			this.cellStyleLoop(1, 1, topLCellStyle, row, cell, new XSSFRichTextString("사회서비스\r\n평가영역").toString());
			this.cellStyleLoop(2, 2, topCellStyle, row, cell, "주거");
			this.cellStyleLoop(3, 3, topCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_SCO12_NM"), ""));
			this.cellStyleLoop(4, 4, topCellStyle, row, cell, "자원연계");
			this.cellStyleLoop(5, 5, topRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_LNK12"), ""));
			rowCount++;
			row = sheet.createRow(rowCount);
			row.setHeight((short) (26*15));
			this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
			this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "경제활동 지원");
			this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_SCO13_NM"), ""));
			this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "자원연계");
			this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_LNK13"), ""));
			rowCount++;
			row = sheet.createRow(rowCount);
			row.setHeight((short) (26*15));
			this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
			this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "고용 및 교육가능성");
			this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_SCO15_NM"), ""));
			this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "자원연계");
			this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_LNK15"), ""));
			rowCount++;
			row = sheet.createRow(rowCount);
			row.setHeight((short) (26*15));
			this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
			this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "법률적 문제");
			this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_SCO16_NM"), ""));
			this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "자원연계");
			this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_LNK16"), ""));
			rowCount++;
			row = sheet.createRow(rowCount);
			row.setHeight((short) (26*15));
			this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
			this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "취업 및 학업욕구");
			this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_SCO14_NM"), ""));
			this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "자원연계");
			this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_LNK14"), ""));
			sheet.addMergedRegion(new CellRangeAddress(tempRowCnt, tempRowCnt+4, 1, 1));
		}
		
		if( !( "1".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_SCO21"), "")) && "".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_LNK21"), ""))
				&& "1".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_SCO22"), "")) && "".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_LNK22"), ""))
				&& "1".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_SCO23"), "")) && "".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_LNK23"), ""))
				&& "1".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_SCO24"), "")) && "".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_LNK24"), ""))
				&& "1".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_SCO25"), "")) && "".equals(StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_LNK25"), ""))
				) ){
			
			rowCount++;
			row = sheet.createRow(rowCount);
			row.setHeight((short) (26*15));
			tempRowCnt = rowCount;
			this.cellStyleLoop(1, 1, topLCellStyle, row, cell, new XSSFRichTextString("기타영역").toString());
			this.cellStyleLoop(2, 2, topCellStyle, row, cell, "영성");
			this.cellStyleLoop(3, 3, topCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_SCO21_NM"), ""));
			this.cellStyleLoop(4, 4, topCellStyle, row, cell, "자원연계");
			this.cellStyleLoop(5, 5, topRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_LNK21"), ""));
			rowCount++;
			row = sheet.createRow(rowCount);
			row.setHeight((short) (26*15));
			this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
			this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "봉사");
			this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_SCO22_NM"), ""));
			this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "자원연계");
			this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_LNK22"), ""));
			rowCount++;
			row = sheet.createRow(rowCount);
			row.setHeight((short) (26*15));
			this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
			this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "여가");
			this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_SCO23_NM"), ""));
			this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "자원연계");
			this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_LNK23"), ""));
			rowCount++;
			row = sheet.createRow(rowCount);
			row.setHeight((short) (26*15));
			this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
			this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "미래계획");
			this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_SCO24_NM"), ""));
			this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "자원연계");
			this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_LNK24"), ""));
			rowCount++;
			row = sheet.createRow(rowCount);
			row.setHeight((short) (26*15));
			this.cellStyleLoop(1, 1, basicLCellStyle, row, cell, "");
			this.cellStyleLoop(2, 2, basicCellStyle, row, cell, "기타");
			this.cellStyleLoop(3, 3, basicCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_SCO25_NM"), ""));
			this.cellStyleLoop(4, 4, basicCellStyle, row, cell, "자원연계");
			this.cellStyleLoop(5, 5, basicRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("EVL_ITM_LNK25"), ""));
			sheet.addMergedRegion(new CellRangeAddress(tempRowCnt, tempRowCnt+4, 1, 1));
		}
		
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (139*15));
		this.cellStyleLoop(1, 1, topLCellStyle, row, cell, "ISP결과");
		this.cellStyleLoop(2, 5, topRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("ISP_RST"), ""));
		sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 2, 5));
		
		rowCount++;
		row = sheet.createRow(rowCount);
		row.setHeight((short) (139*15));
		this.cellStyleLoop(1, 1, topLCellStyle, row, cell, new XSSFRichTextString("장단기\r\n목표수립").toString());
		this.cellStyleLoop(2, 5, topRCellStyle, row, cell, StringUtils.defaultIfEmpty((String)cslInfo.get("TGT_CTNT"), ""));
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
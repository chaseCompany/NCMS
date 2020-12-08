package kr.co.chase.ncms.common.excel;

import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.AbstractView;

public abstract class AbstractExcelView extends AbstractView {
	private static final Logger LOGGER  = LoggerFactory.getLogger(AbstractExcelView.class);

	public AbstractExcelView(){}

	@Override
	protected boolean generatesDownloadContent(){
		return true;
	}

	@Override
	protected final void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		Workbook workbook = createWorkbook();

		setContentType("CONTENT_TYPE_XLSX");

		buildExcelDocument(model, workbook);

		response.setContentType(getContentType());

		ServletOutputStream out = response.getOutputStream();
		out.flush();
		workbook.write(out);
		out.flush();

		if(workbook instanceof SXSSFWorkbook){
			((SXSSFWorkbook) workbook).dispose();
		}
	}

	protected abstract Workbook createWorkbook();

	protected abstract void buildExcelDocument(Map<String, Object> model, Workbook workbook) throws Exception;

	public void cellStyleLoop(int strNum, int endNum, CellStyle style, Row row, Cell cell, String val) {
		for(int i=strNum ; i<=endNum ; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(style);

			if(i == strNum && !"".equals(val)) {
				cell.setCellValue(val);
			}
		}
	}
}
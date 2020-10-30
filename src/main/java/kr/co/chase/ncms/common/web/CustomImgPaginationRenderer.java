package kr.co.chase.ncms.common.web;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

import egovframework.rte.ptl.mvc.tags.ui.pagination.AbstractPaginationRenderer;

/**
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2019.08.27           최초생성
 * @author jhg
 */
public class CustomImgPaginationRenderer extends AbstractPaginationRenderer implements ServletContextAware{

	@SuppressWarnings("unused")
	private ServletContext servletContext;

	public CustomImgPaginationRenderer() {
		// no-op
	}

	/**
	* PaginationRenderer
	*/
	public void initVariables() {
		firstPageLabel = "<a class=\"fst\" href=\"#\" onclick=\"{0}({1}); return false;\"></a>&#160;";
		previousPageLabel = "<a class=\"prv\" href=\"#\" onclick=\"{0}({1}); return false;\"></a>&#160;";
		currentPageLabel = "<a class=\"on\" href=\"#\">{0}</a>&#160;";
		otherPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\">{2}</a>&#160;";
		nextPageLabel = "<a class=\"nxt\" href=\"#\" onclick=\"{0}({1}); return false;\"></a>&#160;";
		lastPageLabel = "<a class=\"lst\" href=\"#\" onclick=\"{0}({1}); return false;\"></a>&#160;";
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		initVariables();
	}
}

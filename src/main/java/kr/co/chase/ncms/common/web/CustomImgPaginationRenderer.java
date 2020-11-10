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
	public CustomImgPaginationRenderer() {
		// no-op
	}

	/**
	* PaginationRenderer
	*
	* @see 개발프레임웍크 실행환경 개발팀
	*/
	public void initVariables() {
		firstPageLabel = "";
		previousPageLabel = "<a href=\"javaScript:{0}({1});\"><i class=\"el-icon el-icon-arrow-left\"></i></a>&#160;";
		currentPageLabel = "<a href=\"#\" class=\"active\">{0}</a>&#160;";
		otherPageLabel = "<a href=\"javaScript:{0}({1});\">{2}</a>&#160;";
		nextPageLabel = "<a href=\"javaScript:{0}({1});\"><i class=\"el-icon el-icon-arrow-right\"></i></a>&#160;";
		lastPageLabel = "";
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		initVariables();
	}
}
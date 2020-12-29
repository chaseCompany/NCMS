package kr.co.chase.ncms.common;

import java.text.MessageFormat;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationRenderer;

public abstract class AbstractPaginationRenderer implements PaginationRenderer {
	protected String firstPageLabel;
	protected String previousPageLabel;
	protected String currentPageLabel;
	protected String otherPageLabel;
	protected String nextPageLabel;
	protected String lastPageLabel;

	public String renderPagination(PaginationInfo paginationInfo, String jsFunction) {
		StringBuffer strBuff = new StringBuffer();

		int firstPageNo = paginationInfo.getFirstPageNo();
		int firstPageNoOnPageList = paginationInfo.getFirstPageNoOnPageList();
		int totalPageCount = paginationInfo.getTotalPageCount();
		int pageSize = paginationInfo.getPageSize();
		int lastPageNoOnPageList = paginationInfo.getLastPageNoOnPageList();
		int currentPageNo = paginationInfo.getCurrentPageNo();
		int lastPageNo = paginationInfo.getLastPageNo();

		if(lastPageNoOnPageList > 1) {
			if (totalPageCount > pageSize) {
				if (firstPageNoOnPageList > pageSize) {
					strBuff.append(MessageFormat.format(previousPageLabel, new Object[] { jsFunction, Integer.toString(firstPageNoOnPageList - 1) }));
				} else {
					strBuff.append(firstPageLabel);
				}
			}

			for (int i = firstPageNoOnPageList; i <= lastPageNoOnPageList; i++) {
				if (i == currentPageNo) {
					strBuff.append(MessageFormat.format(currentPageLabel, new Object[] { Integer.toString(i) }));
				} else {
					strBuff.append(MessageFormat.format(otherPageLabel, new Object[] { jsFunction, Integer.toString(i), Integer.toString(i) }));
				}
			}

			if (totalPageCount > pageSize) {
				if (lastPageNoOnPageList < totalPageCount) {
					strBuff.append(MessageFormat.format(nextPageLabel, new Object[] { jsFunction, Integer.toString(firstPageNoOnPageList + pageSize) }));
				} else {
					strBuff.append(lastPageLabel);
				}
			}
		}

		return strBuff.toString();
	}
}
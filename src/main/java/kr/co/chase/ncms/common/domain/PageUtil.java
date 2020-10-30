package kr.co.chase.ncms.common.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("pageUtil")
public class PageUtil {

	/** 게시물 총 개수 */
	private int totalRowCount;
	
	/** 현재 페이지 */
	private int currentPage;
	
	/** 한 페이지에 보여줄 게시글 수 */
	private int rowSize = 10;
	
	/** 한 페이지에 보여줄 페이지 수 */
	private int pageSize = 10;
	
	/** data list */
	private List<HashMap<String, Object>> resultList;
	
	/**
	 * 페이지처리 init
	 * 
	 * @param dataList
	 * @param param
	 */
	public void pageInit(List<HashMap<String, Object>> dataList, HashMap<String, Object> param) {
		totalRowCount = dataList == null || dataList.size() == 0 ? 0 : Integer.parseInt(((Map<String, Object>)dataList.get(0)).get("TOTAL_COUNT").toString());
		currentPage = Integer.parseInt(String.valueOf(param.get("currentPage")));
		resultList = dataList;
	}
	public void pageInit(List<HashMap<String, Object>> dataList, int currentPage) {
		totalRowCount = dataList == null || dataList.size() == 0 ? 0 : Integer.parseInt(((Map<String, Object>)dataList.get(0)).get("TOTAL_COUNT").toString());
		this.currentPage = currentPage;
		resultList = dataList;
	}
	
	/**
	 * 총 페이지 수
	 * 
	 * @return
	 */
	public int getTotalPageCount() {
		return (int)Math.ceil(totalRowCount / (double)rowSize);
	}
	
	/**
	 * 화면에 보여지는 페이지 번호 시작
	 * 
	 * @return
	 */
	public int getStartPageNum() {
		return ((currentPage - 1) / pageSize) * pageSize + 1;
	}
	
	/**
	 * 화면에 보여지는 페이지 번호 끝
	 * 
	 * @return
	 */
	public int getEndPageNum() {
		int totalPageCount = getTotalPageCount();
		int endPageNum = (((getStartPageNum() - 1) +  pageSize) / pageSize) * pageSize;
		if(totalPageCount <= endPageNum) {
			endPageNum = totalPageCount;
		}
		return endPageNum;
	}
	
	public int getTotalRowCount() {
		return totalRowCount;
	}

	public void setTotalRowCount(int totalRowCount) {
		this.totalRowCount = totalRowCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getRowSize() {
		return rowSize;
	}

	public void setRowSize(int rowSize) {
		this.rowSize = rowSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<HashMap<String, Object>> getResultList() {
		return resultList;
	}

	public void setResultList(List<HashMap<String, Object>> resultList) {
		this.resultList = resultList;
	}
	
	/**
	 * 현재 페이지가 속한 블록
	 * 
	 * @return
	 */
	public int getCurrentPageBlock() {
		return (int)Math.ceil(currentPage / (double)pageSize);
	}
	
	/**
	 * 전체 페이지 블록 수
	 * 
	 * @return
	 */
	public int getTotalPageBlock() {
		return (int)Math.ceil(getTotalPageCount() / (double)pageSize);
	}
	
	/**
	 * 이전 블록의 마지막 페이지 번호
	 * @return
	 */
	public int getPrevBlockLastPage() {
		return (getCurrentPageBlock() - 1) * pageSize;
	}
	
	/**
	 * 다음 블록의 첫번째 페이지 번호
	 * 
	 * @return
	 */
	public int getNextBlockFirstPage() {
		return (getCurrentPageBlock() * pageSize) + 1;
	}
	
	/**
	 * 게시물 번호
	 * 
	 * @return
	 */
	public int getOrderNo() {
		return totalRowCount - (rowSize * (currentPage - 1));
	}
	
}

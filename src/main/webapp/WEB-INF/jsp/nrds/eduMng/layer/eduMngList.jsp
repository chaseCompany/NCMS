<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javaScript" language="javascript" defer="defer">
	$(document).ready(function(){
		<%-- 페이지처리 --%>
		goPage = function(pageNo){
			$("input[name='pgmPageNo']").val(pageNo);
			getWeeklyPrgList();
		},
		<%-- 프로그램정보 상세 보기 --%>
		viewPrgRow = function(PGM_ID){
			var pgmObj = {
				pgmId : PGM_ID
			}

			reSetPgmForm(pgmObj);
		}
	});
</script>
<div class="table-box">
	<div class="el-table_header-wrapper">
		<table>
			<colgroup>
				<col style="width:80px">
				<col style="width:200px">
				<col style="width:200px;">
				<col style="width: 120px;">
				<col style="width: 200px;">
				<col style="width: 200px;">
				<col>
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>기관명</th>
					<th>교육일시</th>
					<th>담당자</th>
					<th>교육분류</th>
					<th>교육과정명</th>
					<th>참여자수</th>
				</tr>
			</thead>
		</table>
	</div>
	<div class="el-table_body-wrapper" style="height: 300px;">
		<c:if test="${fn:length(prgList) > 0}">
		<table>
			<colgroup>
				<col style="width:80px">
				<col style="width:200px">
				<col style="width:200px;">
				<col style="width: 120px;">
				<col style="width: 200px;">
				<col style="width: 200px;">
				<col>
			</colgroup>
			<tbody>
				<c:forEach var="result" items="${prgList}" varStatus="status">
		<%-- <fmt:parseDate value="${result.PGM_DT}" var="regDt" pattern="yyyyMMdd"/> --%>
				<tr>
					<td><fmt:formatNumber value="${result.rownum}" pattern="#" /></td>
					<td><c:out value="${result.pgmAgentNm}" /></td>
					<td><%-- <fmt:formatDate value="${result.pgmClassStartDt}" pattern="yyyy-MM-dd"/> --%>${result.pgmClassStartDt}</td>
					<td><c:out value="${result.pgmMngUsrId}" /></td>
					<td><a href='javaScript:viewPrgRow("<c:out value="${result.pgmId}"/>");' class='row_link'><c:out value="${result.pgmEdNm}" /></a></td>
					<td><a href='javaScript:viewPrgRow("<c:out value="${result.pgmId}"/>");' class='row_link'><c:out value="${result.pgmClassNm}" /></a></td>
					<td><c:out value="${result.pgmMbrCount}" /></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		</c:if>
	<c:if test="${fn:length(prgList) == 0}">
		<div class="no-data">조회된 데이터가 없습니다.</div>
	</c:if>
	</div>
</div>
<%-- <div class="paging">
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="goPage" />
</div>  --%>
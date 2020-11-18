<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		viewPrgRow = function(PGM_CD, PGM_DT, PGM_FM_TM, PGM_TO_TM, PGM_TP_CD, MNG_USR_ID, PGM_CTNT){
			var pgmObj = {
				pgmCd : PGM_CD,
				pgmDt : PGM_DT,
				pgmFmTm : PGM_FM_TM,
				pgmToTm : PGM_TO_TM,
				pgmTpCd : PGM_TP_CD,
				mngUsrId : MNG_USR_ID,
				pgmCtnt : PGM_CTNT
			}

			reSetPgmForm(pgmObj);
		}
	});
</script>
<div class="table-box mgt10">
	<div class="el-table_header-wrapper">
		<table>
			<colgroup>
				<col style="width:46px">
				<col style="width:100px">
				<col style="width:230px">
				<col style="width:150px">
				<col>
			</colgroup>
			<thead>
			<tr>
				<th>#</th>
				<th>실시 일자</th>
				<th>주관 프로그램</th>
				<th>담당자</th>
				<th>회원수</th>
			</tr>
			</thead>
		</table>
	</div>
	<div class="el-table_body-wrapper" style="height: 630px;">
<c:if test="${totalCount > 0}">
		<table>
			<colgroup>
				<col style="width:46px">
				<col style="width:100px">
				<col style="width:230px">
				<col style="width:150px">
				<col>
			</colgroup>
			<tbody>
	<c:forEach var="result" items="${prgList}" varStatus="status">
		<fmt:parseDate value="${result.PGM_DT}" var="regDt" pattern="yyyyMMdd"/>
			<tr>
				<td><div class="cell"><fmt:formatNumber value="${result.ROWNUM}" pattern="#" /></div></td>
				<td><fmt:formatDate value="${regDt}" pattern="yyyy-MM-dd"/></td>
				<td class='txt-left'><a href='javaScript:viewPrgRow("<c:out value="${result.PGM_CD}"/>"
																  , "<c:out value="${result.PGM_DT}"/>"
																  , "<c:out value="${result.PGM_FM_TM}"/>"
																  , "<c:out value="${result.PGM_TO_TM}"/>"
																  , "<c:out value="${result.PGM_TP_CD}"/>"
																  , "<c:out value="${result.MNG_USR_ID}"/>"
																  , "<c:out value="${result.PGM_CTNT}"/>");' class='row_link'><c:out value="${result.PGM_NM}" /></a></td>
				<td><div class="cell"><c:out value="${result.MNG_USR_NM}" /></div></td>
				<td><div class="cell"><c:out value="${result.MBR_CNT}" /></div></td>
			</tr>
	</c:forEach>
			</tbody>
		</table>
</c:if>
<c:if test="${totalCount <= 0}">
		<div class="no-data">조회된 데이터가 없습니다.</div>
</c:if>
	</div>
</div>
<div class="paging">
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="goPage" />
</div>
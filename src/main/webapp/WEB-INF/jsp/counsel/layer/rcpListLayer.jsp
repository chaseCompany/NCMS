<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script>
	$(document).ready(function(){
		$(".datepicker").datepicker();
		<%-- 페이지 이동 --%>
		goPage = function(pageNo){
			$("input[name='rcpPageNo']").val(pageNo);
			getRcpNo();
		},
		<%-- 날짜 셋팅 --%>
		setStrDt = function(obj){
			var endDt = new Date($("input[name='rcpSchEndCslDt']").datepicker("getDate"));
			endDt.setMonth(endDt.getMonth() - $(obj).val());
			$("input[name='rcpSchStrCslDt']").datepicker('setDate', endDt);
		},
		<%-- 검색 --%>
		schRcpNo = function(){
			$("input[name='rcpPageNo']").val("1");
			getRcpNo();
		}

		var setNewStrDt = new Date('<c:out value="${fn:substring(schStrCslDt, 0, 4)}" />', '<c:out value="${fn:substring(schStrCslDt, 4, 6)}" />', '<c:out value="${fn:substring(schStrCslDt, 6, 8)}" />');
		var setNewEndDt = new Date('<c:out value="${fn:substring(schEndCslDt, 0, 4)}" />', '<c:out value="${fn:substring(schEndCslDt, 4, 6)}" />', '<c:out value="${fn:substring(schEndCslDt, 6, 8)}" />')
		setNewStrDt.setMonth(setNewStrDt.getMonth() - 1);
		setNewEndDt.setMonth(setNewEndDt.getMonth() - 1);
		$("input[name='rcpSchStrCslDt']").datepicker('setDate', setNewStrDt);
		$("input[name='rcpSchEndCslDt']").datepicker('setDate', setNewEndDt);
	});
</script>
<!-- 접수번호 검색 팝업 -->
<div class="pop-header">
	<span>접수번호 검색</span>
	<button type="button" class="el-dialog__headerbtn" onclick="layerPopupClose('rcptPopUp')">
		<i class="el-dialog__close el-icon el-icon-close"></i>
	</button>
</div>
<div class="pop-content">
	<!-- 검색 -->
	<div class="section bg">
		<table class="w-auto">
			<tbody>
				<form name="rcpNoSchForm" id="rcpNoSchForm">
				<input type="hidden" name="rcpPageNo" value="<c:out value='${pageNo}' />" />
				<tr>
					<th><span class="required">*</span> 상담일자</th>
					<td>
						<div class="dat-pk">
							<i class="el-input__icon el-icon-date"></i>
							<input type="text" name="rcpSchStrCslDt" class="el-input__inner datepicker" style="width: 130px;">
						</div>
						<span>~</span>
						<div class="dat-pk">
							<i class="el-input__icon el-icon-date"></i>
							<input type="text" name="rcpSchEndCslDt" class="el-input__inner datepicker" style="width: 130px;">
						</div>
						<div class="dsp-ibk">
							<select name="rcpSchMth" style="width: 80px;" onchange="javaScript:setStrDt(this);">
								<option value="">선택</option>
								<option value="1"<c:if test="${schMth eq '1'}"> selected</c:if>>1개월</option>
								<option value="3"<c:if test="${schMth eq '3'}"> selected</c:if>>3개월</option>
								<option value="6"<c:if test="${schMth eq '6'}"> selected</c:if>>6개월</option>
								<option value="12"<c:if test="${schMth eq '12'}"> selected</c:if>>1년</option>
							</select>
							<select name="rcpSchGb" style="width: 110px;">
								<option value="">전체</option>
								<option value="0"<c:if test="${schGb eq '0'}"> selected</c:if>>상담자</option>
								<option value="1"<c:if test="${schGb eq '1'}"> selected</c:if>>정보제공자</option>
								<option value="2"<c:if test="${schGb eq '2'}"> selected</c:if>>대상자</option>
							</select>
							<input type="text" name="rcpSchNm" value="<c:out value='${schNm}' />" class="el-input__inner" style="width: 120px;">
							<button type="button" onclick="javaScript:schRcpNo();" class="el-button el-button--primary el-button--small is-plain" style="height:32px;margin-left: 8px;">
								<i class="el-icon-search"></i> <span>검색</span>
							</button>
						</div>
					</td>
				</tr>
				</form>
			</tbody>
		</table>
	</div>
	<!-- // 검색 -->
	<!-- 목록 -->
	<div class="result-list">
		<div class="table-box">
			<div class="el-table_header-wrapper">
				<table>
					<colgroup>
						<col style="width:60px">
						<col style="width:70px">
						<col style="width:150px">
						<col style="width:100px">
						<col style="width:120px">
						<col style="width:80px">
						<col style="width:120px">
						<col style="width:120px">
						<col>
					</colgroup>
					<thead>
						<tr>
							<th>#</th>
							<th>선택</th>
							<th>접수 번호</th>
							<th>상담 일자</th>
							<th>시작/종료 시간</th>
							<th>소요(분)</th>
							<th>상담자</th>
							<th>정보 제공자</th>
							<th>대상자</th>
						</tr>
					</thead>
				</table>
			</div>
			<div class="el-table_body-wrapper">
				<table>
					<colgroup>
						<col style="width:60px">
						<col style="width:70px">
						<col style="width:150px">
						<col style="width:100px">
						<col style="width:120px">
						<col style="width:80px">
						<col style="width:120px">
						<col style="width:120px">
						<col>
					</colgroup>
					<tbody>
<c:if test="${totalCount > 0}">
	<c:forEach var="result" items="${resultList}" varStatus="status">
						<tr>
							<td><div class="cell"><c:out value="${result.ROWNUM}" /></div></td>
							<td>
								<div class="cell">
									<button type="button" class="el-button el-button--warning el-button--mini is-plain" style="margin-left: 1px; padding: 4px 9px;" onclick="javaScript:counselInfoViewSet('<c:out value="${result.RCP_NO}" />');">
										<span>선택</span>
									</button>
								</div>
							</td>
							<td><div class="cell"><c:out value="${result.RCP_NO}" /></div></td>
							<td><div class="cell"><c:out value="${result.CSL_DT}" /></div></td>
							<td><div class="cell"><c:out value="${result.CSL_FM_TM}" /> ~ <c:out value="${result.CSL_TO_TM}" /></div></td>
							<td><div class="cell"><c:out value="${result.CSL_TERM_TM}" /></div></td>
							<td><div class="cell"><c:out value="${result.CSL_NM}" /></div></td>
							<td><div class="cell"><c:out value="${result.IFP_NM}" /></div></td>
							<td><div class="cell"><c:out value="${result.TGP_NM}" /></div></td>
						</tr>
	</c:forEach>
</c:if>
<c:if test="${totalCount <= 0}">
						<tr>
							<td colspan="9">데이터 없음</td>
						</tr>
</c:if>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- // 목록 -->
	<!-- 페이징 -->
	<div class="paging">
		<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="goPage" />
	</div>
	<!-- // 페이징 -->
	<!-- 닫기 -->
	<div class="el-dialog__footer">
		<button type="button" class="el-button el-button--default el-button--small" onclick="layerPopupClose('rcptPopUp');">
			<span>닫기</span>
		</button>
	</div>
	<!-- // 닫기 -->
</div>
<!-- // 접수번호 검색 팝업 -->

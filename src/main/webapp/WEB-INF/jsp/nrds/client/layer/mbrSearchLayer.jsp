<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kr.co.chase.ncms.common.ConstantObject" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script>
	$(document).ready(function(){
		<%-- 페이지처리 --%>
		goPage = function(pageNo){
			$("input[name='memPageNo']").val(pageNo);
			mstMbrSearchPopup($("input[name='reFunName']").val());
		},
		<%-- 검색 --%>
		schPageList = function(){
			$("input[name='memPageNo']").val("1");
			mstMbrSearchPopup($("input[name='reFunName']").val());
		}
		<%-- 회원선택 --%>
		choiceMem = function(mbrNo){
			eval($("input[name='reFunName']").val() + "(mbrNo)");
			layerPopupClose('memberPopUp');
		}
	});
</script>
<!-- 회원 검색 팝업 -->
<div class="pop-header">
	<span>회원 검색</span>
	<button type="button" class="el-dialog__headerbtn" onclick="layerPopupClose('memberPopUp')">
		<i class="el-dialog__close el-icon el-icon-close"></i>
	</button>
</div>
<div class="pop-content">
	<!-- 검색 -->
	<div class="section bg">
		<table class="w-auto">
			<tbody>
				<form name="rcpNoSchForm" id="rcpNoSchForm">
				<input type="hidden" name="memPageNo" value="<c:out value='${pageNo}' />" />
				<input type="hidden" name="reFunName" />
				<tr>
					<th><span class="required">*</span> 회원명</th>
					<td><input type="text" name="memSchMbrNm" value="<c:out value='${mbrNm}' />" class="el-input__inner" style="width: 120px;"></td>
					<th><span class="required">*</span> 연락처</th>
					<td>
						<input type="text" name="memSchTelNo" value="<c:out value='${telNo}' />" class="el-input__inner" style="width: 120px;">
						<button type="button" onclick="javaScript:schPageList();" class="el-button el-button--primary el-button--small is-plain" style="height:32px;margin-left: 8px;">
							<i class="el-icon-search"></i> <span>검색</span>
						</button>
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
						<col style="width:66px">
						<col style="width:150px">
						<col style="width:120px">
						<col style="width:78px">
						<col style="width:50px">
						<col style="width:60px">
						<col style="width:175px">
						<col style="width:80px">
						<col>
					</colgroup>
					<thead>
						<tr>
							<th>#</th>
							<th>선택</th>
							<th>회원등록번호</th>
							<th>성명</th>
							<th>생년월일</th>
							<th>성별</th>
							<th>연령</th>
							<th>연락처</th>
							<th>퇴록여부</th>
							<th>최초등록일자</th>
						</tr>
					</thead>
				</table>
			</div>
			<div class="el-table_body-wrapper">
				<table>
					<colgroup>
						<col style="width:60px">
						<col style="width:66px">
						<col style="width:150px">
						<col style="width:120px">
						<col style="width:78px">
						<col style="width:50px">
						<col style="width:60px">
						<col style="width:175px">
						<col style="width:80px">
						<col>
					</colgroup>
					<tbody>
<c:if test="${totalCount > 0}">
	<c:forEach var="result" items="${resultList}" varStatus="status">
		<fmt:parseDate value="${result.REG_DT}" var="regDt" pattern="yyyyMMdd"/>
						<tr>
							<td><div class="cell"><fmt:formatNumber value="${result.ROWNUM}" pattern="#" /></div></td>
							<td>
								<div class="cell">
									<button type="button" onclick="javaScript:choiceMem('<c:out value="${result.MBR_NO}" />');" class="el-button el-button--warning el-button--mini is-plain" style="margin-left: 1px; padding: 4px 9px;">
										<span>선택</span>
									</button>
								</div>
							</td>
							<td><div class="cell"><c:out value="${result.MBR_NO}" /></div></td>
							<td><div class="cell"><c:out value="${result.MBR_NM}" /></div></td>
							<td><div class="cell"><c:out value="${result.JUMIN_NO1}" /></div></td>
							<td><div class="cell"><c:out value="${result.GEND_NM}" /></div></td>
							<td><div class="cell"><c:out value="${result.AGE}" /></div></td>
							<td><div class="cell"><c:out value="${result.TEL_NO1}" />-<c:out value="${result.TEL_NO2}" />-<c:out value="${result.TEL_NO3}" /></div></td>
		<c:if test="${result.STS_CD ne 'RG'}">
							<td><div class="cell"<c:if test="${result.STS_CD eq ConstantObject.rlMemStsCd}"> style="color: red;"</c:if>><c:out value="${result.STS_NM}" /></div></td>
		</c:if>
		<c:if test="${result.STS_CD eq 'RG'}">
							<td><div class="cell"></div></td>
		</c:if>
							<td><div class="cell"><fmt:formatDate value="${regDt}" pattern="yyyy-MM-dd"/></div></td>
						</tr>
	</c:forEach>
</c:if>
<c:if test="${totalCount <= 0}">
						<tr>
							<td colspan="10">데이터 없음</td>
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
		<button type="button" class="el-button el-button--default el-button--small" onclick="layerPopupClose('memberPopUp');">
			<span>닫기</span>
		</button>
	</div>
	<!-- // 닫기 -->
</div>
<!-- // 회원 검색 팝업 -->
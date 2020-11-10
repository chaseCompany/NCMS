<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<script>
	$(document).ready(function(){
		goPage = function(pageNo){
			console.log(pageNo);
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
				<tr>
					<th><span class="required">*</span> 회원명</th>
					<td><input type="text" class="el-input__inner" style="width: 120px;"></td>
					<th><span class="required">*</span> 연락처</th>
					<td>
						<input type="text" class="el-input__inner" style="width: 120px;">
						<button type="button" class="el-button el-button--primary el-button--small is-plain" style="height:32px;margin-left: 8px;">
							<i class="el-icon-search"></i> <span>검색</span>
						</button>
					</td>
				</tr>
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
						<tr>
							<td><div class="cell"><c:out value="${result.ROWNUM}" /></div></td>
							<td>
								<div class="cell">
									<button type="button" class="el-button el-button--warning el-button--mini is-plain" style="margin-left: 1px; padding: 4px 9px;">
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
							<td><div class="cell"><c:out value="${result.STS_NM}" /></div></td>
							<td><div class="cell"><c:out value="${result.REG_DT}" /></div></td>
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
		<button type="button" class="el-button el-button--default el-button--small" onclick="layerPopupClose('memberPopUp')">
			<span>닫기</span>
		</button>
	</div>
	<!-- // 닫기 -->
</div>
<!-- // 회원 검색 팝업 -->
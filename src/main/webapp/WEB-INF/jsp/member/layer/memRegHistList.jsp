<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 등/퇴록 내역 -->
<div class="wh-box">
	<div class="tt-bx"><i class="el-icon-s-order"></i> 등/퇴록 내역</div>
	<div class="table-box mgt10">
		<div class="el-table_header-wrapper">
			<table>
				<colgroup>
					<col style="width:46px">
					<col style="width:100px">
					<col style="width:100px">
					<col style="width:130px">
					<col style="width:100px">
					<col>
					<col style="width: 170px;">
				</colgroup>
				<thead>
				<tr>
					<th>#</th>
					<th>기관명</th>
					<th>구분</th>
					<th>등/퇴록일자</th>
					<th>형태</th>
					<th>상세내용</th>
					<th>상세</th>
				</tr>
				</thead>
			</table>
		</div>
		<div class="el-table_body-wrapper" style="height: 180px;">
<c:if test="${mstRegHisList ne null and mstRegHisList ne ''}">
			<table>
				<colgroup>
					<col style="width:46px">
					<col style="width:100px">
					<col style="width:100px">
					<col style="width:130px">
					<col style="width:100px">
					<col>
					<col style="width: 170px;">
				</colgroup>
				<tbody>
	<c:forEach var="result" items="${mstRegHisList}" varStatus="status">
		<fmt:parseDate value="${result.REG_RLS_DT}" var="regRlsDt" pattern="yyyyMMdd" />
				<tr>
					<td><div class="cell"><c:out value="${status.count}" /></div></td>
					<td><div class="cell" id="viewInst_<c:out value="${status.count}" />" cd="<c:out value="${result.INST_CD}" />"><c:out value="${result.INST_NM}" /></div></td>
					<td><div class="cell"<c:if test="${result.REG_RLS_CD eq ConstantObject.rlMemStsCd}"> style="color: red;"</c:if>><c:out value="${result.REG_RLS_NM}" /></div></td>
					<td><div class="cell" id="viewDtlCtnt_<c:out value="${status.count}" />"><i class="el-icon-time"></i><fmt:formatDate value="${regRlsDt}" pattern="yyyy-MM-dd" /></div></td>
					<td><div class="cell" id="viewType_<c:out value="${status.count}" />" cd="<c:out value="${result.TYPE_CD}" />"><c:out value="${result.TYPE_NM}" /></div></td>
					<td class="txt-left"><div class="cell"><c:out value="${result.DTL_CTNT}" /></div></td>
					<td>
						<div class="cell">
							<button type="button" onclick="javaScript:openStsPage('V', '<c:out value="${status.count}" />');" class="el-button el-button--warning el-button--mini is-plain" style="margin-left: 1px; padding: 4px 9px;">
								<span>보기</span>
							</button>
						</div>
					</td>
				</tr>
				<textarea name="viewDtlCtnt_<c:out value="${status.count}" />" style="display:none;"><c:out value="${result.DTL_CTNT}" /></textarea>
	</c:forEach>
				</tbody>
			</table>
</c:if>
<c:if test="${mstRegHisList eq null or mstRegHisList eq ''}">
			<div class="no-data">조회된 데이터가 없습니다.</div>
</c:if>
		</div>
	</div>
</div>
<!-- // 등/퇴록 내역 -->
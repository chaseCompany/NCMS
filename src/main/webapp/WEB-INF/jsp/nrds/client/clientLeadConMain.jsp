<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kr.co.chase.ncms.common.ConstantObject" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javaScript" language="javascript" defer="defer">
	$(document).ready(function(){
	});
</script>
<!-- 페이지 타이틀 -->
<div class="tit-area">
	<h1><i class="el-icon-s-order" style="color: rgb(0, 108, 185);"></i> 대상자관리</h1>
</div>
<!-- // 페이지 타이틀 -->
<!-- 상단 버튼 -->
<div class="top-right-btn">
	<button type="button" onclick="javaScript:infoSave();" class="el-button normal el-button--primary el-button--small is-plain">
		<i class="el-icon-check"></i><span>저장</span>
	</button>
	<button type="button" onclick="javaScript:newMemInfo();" class="el-button normal el-button--default el-button--small is-plain" style="margin-left: 8px;">
		<i class="el-icon-circle-plus-outline"></i><span>신규</span>
	</button>
	<button disabled="disabled" id="delButNo" type="button" class="el-button normal el-button--default el-button--small is-disabled is-plain">
		<i class="el-icon-delete-solid"></i><span>삭제</span>
	</button>
	<button type="button" id="delButNoYes" onclick="javaScript:delMemInfo();" class="el-button normal el-button--default el-button--small is-plain" style="display:none;">
		<i class="el-icon-delete-solid"></i><span>삭제</span>
	</button>
</div>
<!-- // 상단 버튼 -->
<div class="formline" style="min-width: 1600px;">
	<!-- 탭메뉴 -->
	<div class="el-tabs__header is-top">
		<div class="el-tabs__nav-wrap is-top">
			<div class="el-tabs__nav-scroll">
				<div role="tablist" class="el-tabs__nav is-top">
					<a href="<c:url value="/nrds/clientMain.do" />">
						<div class="el-tabs__item is-top" data-id="tab-mem">
							<span><i class="el-icon-s-help"></i> 대상자 정보관리</span>
						</div>
					</a>
					<a href="<c:url value="/nrds/clientEduConMain.do"/>">
						<div class="el-tabs__item is-top" data-id="tab-link">
							<span><i class="el-icon-s-management"></i> 의뢰 교육조건부 기소유예</span>
						</div>
					</a>
					<a href="<c:url value="/nrds/clientLeadConMain.do" />">
						<div class="el-tabs__item is-top is-active" data-id="tab-req">
							<span><i class="el-icon-platform-eleme"></i> 의뢰 선도조건부 기소유예</span>
						</div>
					</a>
					<a href="<c:url value="/nrds/clientLinkMain.do" />">
						<div class="el-tabs__item is-top" data-id="tab-req">
							<span><i class="el-icon-platform-eleme"></i> 연계</span>
						</div>
					</a>
				</div>
			</div>
		</div>
	</div>
	<!-- // 탭메뉴 -->
	<div class="section">
		<table class="w-auto">
			<tbody>
				<tr>
					<th>대상자등록번호</th>
					<td>
						<div class="search-input tac">
							<input type="text" class="el-input__inner" style="width: 142px;" placeholder="저장시 자동 생성" readonly="readonly"/>
							<button type="button" onclick="javaScript:getRcpNo();"><i class="el-icon-search"></i></button>
						</div>
					</td>
					<th><span class="required">*</span> 성명</th>
					<td><input type="text" class="el-input__inner datepicker" placeholder="성명" maxlength="8" style="width: 130px;" /></td>
					<th>성별</th>
					<td><input type="text" placeholder="성별" /></td>
					<th>연령</th>
					<td><input type="text" placeholder="연령" /> (세)</td>
					<th>의뢰기관</th>
					<td><input type="text" placeholder="의뢰기관" /></td>
					<th>연락처</th>
					<td><input type="text" placeholder="연락처" /></td>
					<th>접수기관</th>
					<td><input type="text" placeholder="접수기관" /></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div id="tab-focus" class="tab-form">
		<div class="tab-tb-box">
			<div class="table-box">
				<div class="el-table_header-wrapper">
					<table>
						<colgroup>
							<col style="width:46px">
							<col style="width:150px">
							<col style="width:100px">
							<col style="width:120px">
							<col style="width:80px">
							<col style="width:400px">
							<col style="width:690px">
							<col>
						</colgroup>
						<thead>
						<tr>
							<th>#</th>
							<th>대상자등록번호</th>
							<th>성명</th>
							<th>성별</th>
							<th>연령</th>
							<th>연락처</th>
							<th>의뢰기관</th>
							<th>접수기관</th>
						</tr>
						</thead>
					</table>
				</div>
				<div class="el-table_body-wrapper" style="height: 148px;" id="infoList">
					<div class="no-data">조회된 데이터가 없습니다.</div>
				</div>
			</div>
		</div>
	</div>
	<div class="el-row">
		<div class="row2">
			<div class="section pdn">
				<div class="el-card_header">
					<h2><i class="el-icon-s-opportunity"></i> 인적사항</h2>
				</div>
				<div class="el-card_body">
					<table class="w-auto wr-form">
						<tbody>
							<tr>
								<th>대상자등록번호</th>
								<td>
									<input name="mbrNo" type="text" readonly placeholder="저장시 자동 생성" class="el-input__inner" style="width: 142px;" />
								</td>
								<th><span class="required">*</span> 성명</th>
								<td><input name="mbrNm" type="text" class="el-input__inner" placeholder="성명" style="width: 130px;" /></td>
								<th><span class="required">*</span> 성별</th>
								<td>
									<div class="gen-check">
										<select name="gendCd">
<c:if test="${gendCdList ne null and gendCdList ne ''}">
	<c:forEach var="result" items="${gendCdList}" varStatus="status">
											<option value="<c:out value="${result.CD_ID}" />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
										</select>
									</div>
								</td>
							</tr>
							<tr>
								<th content="저장시 성명과 생년월일(6자)로 중복 체크"><span class="required">*</span> 생년월일</th>
								<td>
									<input name="juminNo1" type="text" class="el-input__inner" style="width: 90px;" placeholder="6자리">
									<button type="button" onclick="javaScript:checkAge();" class="el-button el-button--primary el-button--small is-plain" style="padding: 9px 10px 8px;">
										<i class="el-icon-d-arrow-right"></i>
									</button>
								</td>
								<th><span class="required">*</span> 연령</th>
								<td><input name="age" type="text" class="el-input__inner" style="width: 65px;" placeholder="연령"></td>
								<th><span class="required">*</span> 연락처</th>
								<td>
									<div class="dsp-ibk"><input name="telNo1" type="text" maxlength="4" class="el-input__inner" style="width:54px"></div>
									<span>-</span>
									<div class="dsp-ibk"><input name="telNo2" type="text" maxlength="4" class="el-input__inner" style="width:54px"></div>
									<span>-</span>
									<div class="dsp-ibk"><input name="telNo3" type="text" maxlength="4" class="el-input__inner" style="width:53px"></div>
								</td>
							</tr>
							<tr>
								<th class="v-top"><span class="required">*</span> 주소</th>
								<td colspan="5">
									<input type="text" name="zipCd" class="el-input__inner" readonly style="width: 65px;">
									<button type="button" onclick="javaScript:zipCodePop();" class="el-button el-button--primary el-button--small is-plain" style="padding: 9px 12px 8px;">
										<i class="el-icon-search"></i>
									</button>
									<input name="addr1" type="text" class="el-input__inner" readonly style="width: 525px;">
									<div style="margin-top:5px">
										<input name="addr2" type="text" class="el-input__inner" style="width: 634px;">
									</div>
								</td>
							</tr>
							<tr>
								<th class="v-top"><span class="required">*</span> 실거주지</th>
								<td colspan="5">
									<input type="text" name="zipCd" class="el-input__inner" readonly style="width: 65px;">
									<button type="button" onclick="javaScript:zipCodePop();" class="el-button el-button--primary el-button--small is-plain" style="padding: 9px 12px 8px;">
										<i class="el-icon-search"></i>
									</button>
									<input name="addr1" type="text" class="el-input__inner" readonly style="width: 525px;">
									<div style="margin-top:5px">
										<input name="addr2" type="text" class="el-input__inner" style="width: 634px;">
									</div>
								</td>
							</tr>
							<tr>
								<th>직업</th>
								<td>
									<select name="jobCd" style="width: 150px;">
										<option value="">선택</option>
<c:if test="${jobCdList ne null and jobCdList ne ''}">
	<c:forEach var="result" items="${jobCdList}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}" />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
									</select>
								</td>
								<th>죄명</th>
								<td colspan="3">
									<select>
										<option value="">선택</option>
									</select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="row2">
			<div class="section pdn">
				<div class="el-card_header">
					<h2><i class="el-icon-s-opportunity"></i> 의뢰정보</h2>
				</div>
				<div class="el-card_body">
					<table class="w-auto wr-form">
						<tbody>
							<tr>
								<th>의뢰일</th>
								<td><input type="text" class="el-input__inner" placeholder="의뢰일" style="width: 130px;" /></td>
								<th>문서번호</th>
								<td><input type="text" class="el-input__inner" placeholder="문서번호" style="width: 130px;" /></td>
								<th>사건번호</th>
								<td><input type="text" class="el-input__inner" placeholder="사건번호" style="width: 130px;" /></td>
							</tr>
							<tr>
								<th>등록지시일</th>
								<td><input type="text" class="el-input__inner" placeholder="등록지시일" style="width: 130px;" /></td>
								<th>등록기한</th>
								<td colspan="3"><input type="text" class="el-input__inner" placeholder="등록기한" style="width: 100%;" /></td>
							</tr>
							<tr>
								<th>의뢰처</th>
								<td><input type="text" class="el-input__inner" placeholder="의뢰처" style="width: 130px;" /></td>
								<th>담당자</th>
								<td colspan="3"><input type="text" class="el-input__inner" placeholder="담당자" style="width: 100%;" /></td>
							</tr>
							<tr>
								<th>첨부파일</th>
								<td colspan="5"><input type="file"></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
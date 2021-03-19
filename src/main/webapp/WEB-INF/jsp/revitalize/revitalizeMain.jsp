<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javaScript" language="javascript" defer="defer">
	$(document).ready(function(){
		$("input[name='cslDt']").datepicker('setDate', 'today');
	});
</script>
<!-- 페이지 타이틀 -->
<div class="tit-area">
	<h1><i class="el-icon-s-order" style="color: rgb(0, 108, 185);"></i> 재범방지교육</h1>
</div>
<!-- // 페이지 타이틀 -->
<form name="counselForm" id="counselForm">
<input type="hidden" name="viewTagName" value="" />
<!-- 상단 버튼 -->
<div class="top-right-btn">
	<button type="button" onclick="javaScript:counselSave();" class="el-button normal el-button--primary el-button--small is-plain">
		<i class="el-icon-download"></i><span>저장</span>
	</button>
	<button type="button" onclick="javaScript:counselNew();" class="el-button normal el-button--default el-button--small is-plain" style="margin-left: 8px;">
		<i class="el-icon-circle-plus-outline"></i><span>신규</span>
	</button>
	<button type="button" id="copyButNo" disabled="disabled" class="el-button normal el-button--default el-button--small is-plain"><i class="el-icon-document-copy">
		</i><span>복사</span>
	</button>
	<button type="button" onclick="javaScript:counselCopy();" id="copyButYes" class="el-button normal el-button--default el-button--small is-plain" style="display: none;">
		<i class="el-icon-document-copy"></i><span>복사</span>
	</button>
	<button type="button" id="delButNo" disabled="disabled" class="el-button normal el-button--default el-button--small is-plain">
		<i class="el-icon-delete-solid"></i><span>삭제</span>
	</button>
	<button type="button" onclick="javaScript:counselDel();" id="delButYes" class="el-button normal el-button--default el-button--small is-plain" style="display: none;">
		<i class="el-icon-delete-solid"></i><span>삭제</span>
	</button>
	<button type="button" id="excelButNo" disabled="disabled" class="el-button normal el-button--default el-button--small is-plain">
		<i class="el-icon-document"></i><span>엑셀다운로드</span>
	</button>
	<button type="button" onclick="javaScript:counselExel();" id="excelButYes" class="el-button normal el-button--default el-button--small is-plain" style="display: none;">
		<i class="el-icon-document"></i><span>엑셀다운로드</span>
	</button>
</div>
<!-- // 상단 버튼 -->

<div class="formline">
	<!-- 접수번호, 상담일시, 상담자 -->
	<div class="section">
		<table class="w-auto">
			<tbody>
				<tr>
					<th>접수번호</th>
					<td>
						<div class="search-input tac">
							<input type="text" name="rcpNo" class="el-input__inner" readonly="true" placeholder="저장시 자동 생성" style="width: 142px;" />
							<button type="button" onclick="javaScript:getRcpNo();"><i class="el-icon-search"></i></button>
						</div>
					</td>
					<th><span class="required">*</span> 상담일시</th>
					<td>
						<div class="dat-pk">
							<i class="el-input__icon el-icon-date"></i>
							<input type="text" name="cslDt" class="el-input__inner datepicker" format="yyyy-MM-dd" placeholder="날짜 선택" maxlength="8" style="width: 130px;" />
						</div>
						<div class="time-box">
							<div class="tm-in">
								<i class="el-input__icon el-icon-time"></i>
								<input type="text" name="cslFmTm" value="09:00" class="el-input__inner timepicker" format="HH:mm" placeholder="시작" maxlength="4" style="width: 96px;" />
							</div>
							<span>~</span>
							<div class="tm-in">
								<i class="el-input__icon el-icon-time"></i>
								<input type="text" name="cslToTm" value="09:00" class="el-input__inner timepicker" format="HH:mm" placeholder="종료" maxlength="4" style="width: 96px;" />
							</div>
							<div class="t-min">
								<span class="readonly" id="cslTermTm">0</span> 분 소요
								<input type="text" name="cslTermTm" hidden="true" />
							</div>
						</div>
					</td>
					<th>기관명</th>
					<td>
						<select name="cslSite" style="width: 140px;">
							<option value="">선택</option>
<c:if test="${cslSiteList ne null and cslSiteList ne ''}">
	<c:forEach var="result" items="${cslSiteList}" varStatus="status">
							<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
						</select>
					</td>
					<th>상담자</th>
					<td>
						<span class="tac"><input type="text" name="cslId" class="el-input__inner" readonly="true" style="width:100px;" /></span>
						<span class="tac"><input type="text" name="cslNm" class="el-input__inner" readonly="true" style="width:150px" /></span>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<!-- // 접수번호, 상담일시, 상담자 -->

	<!-- 정보제공자/본인여부-->
	<div class="section">
		<table class="w-auto">
			<tbody>
				<tr>
					<th><span class="required">*</span> 정보제공자/본인여부</th>
					<td>
<c:if test="${ifpGbList ne null and ifpGbList ne ''}">
	<c:forEach var="result" items="${ifpGbList}" varStatus="status">
						<span class="ck-bx">
							<input type="radio" class="el-radio__original" name="ifpGbCd" id="ifpGbCd-<c:out value='${status.count}'/>" value="${result.CD_ID}" onchange="javaScript:inputDisabledChang(this, 'ifpGbEtc');" />
							<label for="ifpGbCd-<c:out value='${status.count}'/>"><span class="el-radio__input"><span class="el-radio__inner"></span></span><c:out value="${result.CD_NM}" /></label>
						</span>
	</c:forEach>
</c:if>
						<div class="etc-txt">
							<input type="text" name="ifpGbEtc" class="el-input__inner" placeholder="기타 선택시 입력 가능" disabled="true" style="width: 256px;" />
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<!-- // 정보제공자/본인여부-->

	<!-- 정보 제공자 / 대상자 -->
	<div class="el-row" style="min-width: 1880px;">
		<div class="sec-inr">
			<div class="section pdn">
				<div class="el-card_header">
					<h2>
						<i class="el-icon-s-opportunity"></i> 정보 제공자
					</h2>
				</div>
				<div class="el-card_body">
					<table class="w-auto wr-form">
						<tbody>
							<tr>
								<th><span class="required">*</span> 성명</th>
								<td>
									<div class="search-input">
										<input type="text" name="ifpNm" class="el-input__inner" placeholder="정보제공자 성명" style="width: 142px;" />
										<button type="button" onclick="javaScript:mstMbrSearchPopup('ifpMbrSearchPopup');"><i class="el-icon-search"></i></button>
									</div>
								</td>
								<th>회원번호</th>
								<td>
									<span class="tac">
										<input type="text" name="ifpMbrNo" class="el-input__inner" placeholder="회원번호" readonly="true" style="width: 140px;" />
									</span>
								</td>
								<th><span class="required">*</span> 성별</th>
								<td>
									<div class="gen-check">
										<span>
											<input type="radio" class="el-radio-button__orig-radio" name="ifpGendCd" id="ifpGendCd-1" value="M" />
											<label for="ifpGendCd-1" class="el-radio-button el-radio-button--small">
												<span class="el-radio-button__inner">남</span>
											</label>
										</span>
										<span>
											<input type="radio" class="el-radio-button__orig-radio" name="ifpGendCd" id="ifpGendCd-2" value="F" />
											<label for="ifpGendCd-2" class="el-radio-button el-radio-button--small">
												<span class="el-radio-button__inner">여</span>
											</label>
										</span>
									</div>
								</td>
								<th><span class="required">*</span> 연령</th>
								<td class="tac"><input type="text" name="ifpAge" class="el-input__inner" placeholder="연령" maxlength="3" style="width: 60px;" /></td>
							</tr>
							<tr>
								<th><span class="required">*</span> 연락처</th>
								<td>
									<div class="dsp-ibk"><input type="text" name="ifpTelNo1" class="el-input__inner" maxlength="4" style="width:54px" /></div>
									<span>-</span>
									<div class="dsp-ibk"><input type="text" name="ifpTelNo2" class="el-input__inner" maxlength="4" style="width:54px" /></div>
									<span>-</span>
									<div class="dsp-ibk"><input type="text" name="ifpTelNo3" class="el-input__inner" maxlength="4" style="width:53px" /></div>
								</td>
								<th><span class="required">*</span> 직업</th>
								<td>
									<select name="ifpJobCd" style="width: 140px;">
										<option value="">선택</option>
<c:if test="${jobList ne null and jobList ne ''}">
	<c:forEach var="result" items="${jobList}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
									</select>
								</td>
							</tr>
							<tr>
								<th><span class="required">*</span> 지역</th>
								<td colspan="4">
									<span class="el-inp mgr3">
										<select name="ifpAreaCd" style="width: 178px;" onchange="javaScript:inputDisabledChang(this, 'ifpAreaEtc');">
											<option value="">선택</option>
<c:if test="${areaList ne null and areaList ne ''}">
	<c:forEach var="result" items="${areaList}" varStatus="status">
											<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
										</select>
									</span>
									<span class="dsp-ibk">
										<input type="text" name="ifpAreaEtc" class="el-input__inner" placeholder="기타 선택시 입력 가능" disabled="true" style="width: 220px;" />
									</span>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="cn-arw">
			<button type="button" onclick="javaScript:ifpCopy();" class="el-button normal el-button--primary el-button--small is-plain"> <span>▶▶<br><br>정보제공자와<br>동일</span> </button>
		</div>
		<div class="sec-inr">
			<div class="section pdn">
				<div class="el-card_header">
					<h2> <i class="el-icon-s-opportunity"></i> 대상자 </h2>
				</div>
				<div class="el-card_body">
					<table class="w-auto wr-form">
						<tbody>
							<tr>
								<th><span class="required">*</span> 성명</th>
								<td>
									<div class="search-input">
										<input type="text" name="tgpNm" class="el-input__inner" placeholder="대상자 성명" style="width: 142px;" />
										<button type="button" onclick="javaScript:mstMbrSearchPopup('tgpMbrSearchPopup');"><i class="el-icon-search"></i></button>
									</div>
								</td>
								<th>회원번호</th>
								<td>
									<span class="dsp-ibk tac">
										<input type="text" name="tgpMbrNo" class="el-input__inner" placeholder="회원번호" readonly="true" style="width: 140px;" />
									</span>
								</td>
								<th><span class="required">*</span> 성별</th>
								<td>
									<div class="gen-check">
										<span>
											<input type="radio" class="el-radio-button__orig-radio" name="tgpGendCd" value="M" id="tgpGendCd-1" />
											<label for="tgpGendCd-1" class="el-radio-button el-radio-button--small">
												<span class="el-radio-button__inner">남</span>
											</label>
										</span>
										<span>
											<input type="radio" class="el-radio-button__orig-radio" name="tgpGendCd" value="F" id="tgpGendCd-2" />
											<label for="tgpGendCd-2" class="el-radio-button el-radio-button--small">
												<span class="el-radio-button__inner">여</span>
											</label>
										</span>
									</div>
								</td>
								<th><span class="required">*</span> 연령</th>
								<td class="tac"><input type="text" name="tgpAge" class="el-input__inner" maxlength="3" placeholder="연령" style="width: 60px;" /></td>
							</tr>
							<tr>
								<th><span class="required">*</span> 연락처</th>
								<td>
									<div class="dsp-ibk"><input type="text" name="tgpTelNo1" class="el-input__inner" maxlength="4" style="width:54px" /></div>
									<span>-</span>
									<div class="dsp-ibk"><input type="text" name="tgpTelNo2" class="el-input__inner" maxlength="4" style="width:54px" /></div>
									<span>-</span>
									<div class="dsp-ibk"><input type="text" name="tgpTelNo3" class="el-input__inner" maxlength="4" style="width:53px" /></div>
								</td>
								<th><span class="required">*</span> 직업</th>
								<td>
									<select name="tgpJobCd" style="width: 140px;">
										<option value="">선택</option>
<c:if test="${jobList ne null and jobList ne ''}">
	<c:forEach var="result" items="${jobList}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
									</select>
								</td>
								<th><span class="required">*</span> 내/외국인</th>
								<td>
									<span class="ck-bx">
										<input type="radio" class="el-radio__original" name="tgpFrgCd" id="tgpFrgCd1" value="LO" />
										<label for="tgpFrgCd1"><span class="el-radio__input"><span class="el-radio__inner"></span></span> 내국인</label>
									</span>
									<span class="ck-bx">
										<input type="radio" class="el-radio__original" name="tgpFrgCd" id="tgpFrgCd2" value="FO" />
										<label for="tgpFrgCd2"><span class="el-radio__input"><span class="el-radio__inner"></span></span> 외국인</label>
									</span>
								</td>
							</tr>
							<tr>
								<th><span class="required">*</span> 지역</th>
								<td colspan="4">
									<span class="el-inp mgr3">
										<select name="tgpAreaCd" style="width: 178px;" onchange="javaScript:inputDisabledChang(this, 'tgpAreaEtc');">
											<option value="">선택</option>
<c:if test="${areaList ne null and areaList ne ''}">
	<c:forEach var="result" items="${areaList}" varStatus="status">
											<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
										</select>
									</span>
									<span class="dsp-ibk">
										<input type="text" name="tgpAreaEtc" class="el-input__inner" placeholder="기타 선택시 입력 가능" style="width: 220px;" disabled="true" />
									</span>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!-- // 정보 제공자 / 대상자 -->
	<!-- 정보취득경로 ~ 위기분류척도 / 상담내용-->
	<div class="section el-row" style="min-width: 1880px;">
		<div class="row">
			<table class="w-auto wr-form">
				<tbody>
					<tr>
						<th><span class="required">*</span> 정보취득경로</th>
						<td>
							<select name="ifPathCd" style="width: 132px;">
								<option value="">선택</option>
<c:if test="${pathList ne null and pathList ne ''}">
	<c:forEach var="result" items="${pathList}" varStatus="status">
								<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
							</select>
						</td>
						<th><span class="required">*</span> 주호소문제</th>
						<td>
							<select name="pbmKndCd" style="width: 112px;">
								<option value="">선택</option>
<c:if test="${kndList ne null and kndList ne ''}">
	<c:forEach var="result" items="${kndList}" varStatus="status">
								<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
							</select>
						</td>
						<th><span class="required">*</span> 상담유형</th>
						<td>
							<select name="cslTpCd" style="width: 100px;">
<c:if test="${tpList ne null and tpList ne ''}">
	<c:forEach var="result" items="${tpList}" varStatus="status">
								<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
							</select>
						</td>
					</tr>
					<tr>
						<th><span class="required">*</span> 최초사용약물</th>
						<td colspan="5">
							<select name="fstDrugCd" style="width: 100%;">
								<option value="">선택</option>
<c:if test="${fstDrugList ne null and fstDrugList ne ''}">
	<c:forEach var="result" items="${fstDrugList}" varStatus="status">
								<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
							</select>
							<div style="margin-top:5px"><input type="text" name="fstDrug" class="el-input__inner" placeholder="최초사용약물 입력" style="width: 100%;" /></div>
						</td>
					</tr>
					<tr>
						<th><span class="required">*</span> 주요사용약물</th>
						<td colspan="5">
							<select name="mainDrugCd" style="width: 100%;">
								<option value="">선택</option>
<c:if test="${mainDrugList ne null and mainDrugList ne ''}">
	<c:forEach var="result" items="${mainDrugList}" varStatus="status">
								<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
							</select>
							<div style="margin-top:5px"><input type="text" name="mainDrug" class="el-input__inner" placeholder="주요사용약물 입력" style="width: 100%;" /></div>
						</td>
					</tr>
					<tr>
						<th class="v-top"><span class="required">*</span> 주요조치</th>
						<td colspan="5">
							<select name="mjrMngCd" style="width: 132px;">
								<option value="">선택</option>
<c:if test="${mjrMngList ne null and mjrMngList ne ''}">
	<c:forEach var="result" items="${mjrMngList}" varStatus="status">
								<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
							</select>
						</td>
					</tr>
					<tr>
						<th>ASSIST 점수</th>
						<td colspan="5"><input type="text" name="astSco" class="el-input__inner" maxlength="4" style="width:75px" /></td>
					</tr>
				</tbody>
			</table>
			<table class="w-auto wr-form">
				<tbody>
					<tr>
						<th><span class="required">*</span> 위기분류척도</th>
						<td>
							<span class="el-tag">Rating A: 위험성</span>
							<select name="rskaTpCd" style="width: 414px;" onchange="javaScript:changRating();">
<c:if test="${rskaTpList ne null and rskaTpList ne ''}">
	<c:forEach var="result" items="${rskaTpList}" varStatus="status">
								<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
							</select>
						</td>
					</tr>
					<tr>
						<th class="txt-center">점수</th>
						<td>
							<span class="el-tag">Rating B: 지지체계</span>
							<select name="rskbTpCd" style="width: 414px;" onchange="javaScript:changRating();">
<c:if test="${rskbTpList ne null and rskbTpList ne ''}">
	<c:forEach var="result" items="${rskbTpList}" varStatus="status">
								<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
							</select>
						</td>
					</tr>
					<tr>
						<th class="txt-center"><span class="el-tag-danger" id="ratingNum"></span></th>
						<td>
							<span class="el-tag">Rating C: 협조능력</span>
							<select name="rskcTpCd" style="width: 414px;" onchange="javaScript:changRating();">
<c:if test="${rskcTpList ne null and rskcTpList ne ''}">
	<c:forEach var="result" items="${rskcTpList}" varStatus="status">
								<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
							</select>
						</td>
					</tr>
				</tbody>
			</table>
			<table class="w-auto wr-form">
				<tbody>
					<tr>
						<th style="width: 109px;">위기상담</th>
						<td style="width: 543px;"><input type="text" name="cslEmer" class="el-input__inner" placeholder="위기상담" style="width: 100%;"/></td>
					</tr>
					<tr>
						<th><span class="required">*</span> URS</th>
						<td>
							<select name="ursCd" style="width: 100%;">
								<option value="">선택</option>
<c:if test="${ursCdList ne null and ursCdList ne ''}">
	<c:forEach var="result" items="${ursCdList}" varStatus="status">
								<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
							</select>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="row">
			<table class="wr-form sig-form">
				<colgroup><col style="width: 76px;"><col></colgroup>
				<tbody>
					<tr>
						<th><span class="required">*</span> 상담내용<br>
							<button type="button" onclick="javaScript:areaPopup('cslCtnt');" class="el-button el-button--success el-button--mini is-plain" style="padding: 4px 6px;">
								<i class="el-icon-search"></i>
							</button>
						</th>
						<td><textarea name="cslCtnt" placeholder="상담내용" style="width:99%;height:195px"></textarea></td>
					</tr>
					<tr>
						<th><span class="required">*</span> 상담결과<br>
							<button type="button" onclick="javaScript:areaPopup('cslRst');" class="el-button el-button--success el-button--mini is-plain" style="padding: 4px 6px;">
								<i class="el-icon-search"></i>
							</button>
						</th>
						<td><textarea name="cslRst" placeholder="상담결과" style="width:99%;height:260px"></textarea></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<!-- // 정보취득경로 ~ 위기분류척도 / 상담내용-->
</div>
</form>
<!-- 상담내용 팝업 -->
<div class="layerpopup" data-popup="counwrite">
	<div class="pop-header">
		<span>상세내용</span>
		<button type="button" class="el-dialog__headerbtn" onclick="javaScript:layerPopupClose('counwrite');">
			<i class="el-dialog__close el-icon el-icon-close"></i>
		</button>
	</div>
	<div class="pop-content">
		<div class="section bg">
			<textarea name="viewCslCtnt" style="height: 430px;" placeholder="상세내용을 입력하세요."></textarea>
		</div>
		<!-- 닫기 -->
		<div class="el-dialog__footer">
			<button type="button" onclick="javaScript:setCslCtnt();" class="el-button el-button--primary el-button--small is-plain">
				<i class="el-icon-check"></i>
				<span>확인</span>
			</button>
			<button type="button" onclick="javaScript:layerPopupClose('counwrite');" class="el-button el-button--default el-button--small">
				<i class="el-icon-close"></i>
				<span>닫기</span>
			</button>
		</div>
		<!-- // 닫기 -->
	</div>
</div>
<!-- // 상담내용 팝업 -->
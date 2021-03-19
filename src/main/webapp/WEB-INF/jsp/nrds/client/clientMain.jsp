<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kr.co.chase.ncms.common.ConstantObject" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javaScript" language="javascript" defer="defer">
	$(document).ready(function(){
		$("input[name='creDt']").val(getToday());
		<%-- 나이계산 --%>
		checkAge = function(){
			var juminNo = $("input[name='juminNo1']").val();
			if(juminNo.length < 6){
				alert("생년월일을 정확히 입력해 주세요.");
				$("input[name='juminNo1']").focus();			return;
			}

			var year = Number(juminNo.substr(0, 2));
			var day = Number(juminNo.substr(2, 4));
			var dDayYear = Number(String(new Date().getFullYear()).substr(2, 2));
			var dDayDate = Number(String(new Date().getMonth() + 1) + String(new Date().getDate()));
			var age = 0;

			if(dDayYear > year){
				age = dDayYear - year;
			}else{
				age = dDayYear + 100 - year;
			}
			if(dDayDate < day){
				age = age - 1;
			}

			$("input[name='age']").val(age);
		}
		<%-- 우편번호 검색창 오픈 --%>
		zipCodePop = function(){
			new daum.Postcode({
				oncomplete: function(data) {
					$("input[name='zipCd']").val(data.zonecode);
					$("input[name='addr1']").val(data.roadAddress);
				}
			}).open();
		}
		<%-- 정보 저장 --%>
		infoSave = function(){
			$.ajax({
				url : '<c:url value="/nrds/ajaxEdMbrAdd.do"/>',
				type : 'POST',
				data : $('#mainForm').serialize(),
				success : function(res){
					if(res.err != "Y"){
						alert(res.MSG + " 성공");

						getMemInfo(res.mbrNo);
					}else{
						alert(res.MSG);
					}
				},
				error : function(xhr, status){
					console.log(xhr);
				}
			});
		}
		<%-- 회원 조회 팝업 --%>
		mstMbrSearchPopup = function(){
			$.ajax({
				url : '<c:url value="/nrds/ajaxEdMbrList.do"/>',
				type : 'POST',
				data : {
					pageNo : $("input[name='memPageNo']").val(),
					mbrNm : $("input[name='memSchMbrNm']").val(),
					telNo : $("input[name='memSchTelNo']").val()
				},
				success : function(res){
					$("div[id='layerpopup']").html(res);
					$("div[id='layerpopup']").attr("data-popup", "memberPopUp");
					$("input[name='reFunName']").val("viewMemInfo");
					layerPopupOpen('memberPopUp');
				},
				error : function(xhr, status){
					console.log(xhr);
				}
			});
		}
		<%-- 회원정보 상세 조회 --%>
		viewMemInfo = function(tagMbrNo){
			$.ajax({
				url : '<c:url value="/nrds/ajaxMbrInfoJson.do"/>',
				type : 'POST',
				data : {
					mbrNo : tagMbrNo
				},
				success : function(json){
					console.log(json);
				},
				error : function(xhr, status){
					console.log(xhr);
				}
			});
		}
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
	<button disabled="disabled" id="stsButNo" type="button" class="el-button normal el-button--danger el-button--small is-disabled is-plain" style="margin-left: 8px;">
		<i class="el-icon-s-claim"></i><span>퇴록 처리</span>
	</button>
	<button type="button" id="stsRlButYes" onclick="javaScript:openStsPage('A', '<%=ConstantObject.rlMemStsCd%>');" class="el-button normal el-button--danger el-button--small is-plain" style="margin-left: 8px;display:none;">
		<i class="el-icon-s-claim"></i><span>퇴록 처리</span>
	</button>
	<button type="button" id="stsRrButYes" onclick="javaScript:openStsPage('A', '<%=ConstantObject.rrMemStsCd%>');" class="el-button normal el-button--danger el-button--small is-plain" style="margin-left: 8px;display:none;">
		<i class="el-icon-s-claim"></i><span>재등록 처리</span>
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
						<div class="el-tabs__item is-top is-active" data-id="tab-mem">
							<span><i class="el-icon-s-help"></i> 대상자 정보관리</span>
						</div>
					</a>
					<a href="<c:url value="/nrds/clientEduConMain.do"/>">
						<div class="el-tabs__item is-top" data-id="tab-link">
							<span><i class="el-icon-s-management"></i> 의뢰 교육조건부 기소유예</span>
						</div>
					</a>
					<a href="<c:url value="/nrds/clientLeadConMain.do" />">
						<div class="el-tabs__item is-top" data-id="tab-req">
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
	<form name="mainForm" id="mainForm" method="post">
	<div class="el-row">
		<div class="row2">
			<div class="section pdn mgn">
				<div class="el-card_header">
					<h2><i class="el-icon-s-opportunity"></i> 기본 정보</h2>
				</div>
				<div class="el-card_body">
					<table class="w-auto wr-form">
						<tbody>
						<tr>
							<th>대상자등록번호</th>
							<td>
								<div class="search-input tac">
									<input name="mbrNo" type="text" readonly placeholder="저장시 자동 생성" class="el-input__inner" style="width: 142px;" />
<c:if test="${searchFlag ne 'N'}">
									<button type="button" onclick="javaScript:mstMbrSearchPopup();"><i class="el-icon-search"></i></button>
</c:if>
								</div>
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
							<th><span class="required">*</span> 내/외국인</th>
							<td>
<c:if test="${frgCdList ne null and frgCdList ne ''}">
	<c:forEach var="result" items="${frgCdList}" varStatus="status">
								<span class="ck-bx">
									<input type="radio" class="el-radio__original" name="frgCd" value="<c:out value="${result.CD_ID}" />" id="frgCd-<c:out value="${status.count}" />">
									<label for="frgCd-<c:out value="${status.count}" />">
										<span class="el-radio__input"><span class="el-radio__inner"></span></span> <c:out value="${result.CD_NM}" />
									</label>
								</span>
	</c:forEach>
</c:if>
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
							<td colspan="2">
								<div class="dsp-ibk"><input name="telNo1" type="text" maxlength="4" class="el-input__inner" style="width:54px"></div>
								<span>-</span>
								<div class="dsp-ibk"><input name="telNo2" type="text" maxlength="4" class="el-input__inner" style="width:54px"></div>
								<span>-</span>
								<div class="dsp-ibk"><input name="telNo3" type="text" maxlength="4" class="el-input__inner" style="width:53px"></div>
							</td>
						</tr>
						<tr>
							<th class="v-top"><span class="required">*</span> 주소</th>
							<td colspan="7">
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
						</tbody>
					</table>
					<table class="w-auto wr-form">
						<colgroup>
							<col style="width: 100px;">
						</colgroup>
						<tbody>
						<tr>
							<th><span class="required">*</span> 대상자구분</th>
							<td colspan="5">
								<span class="ck-bx"><input type="checkbox" name="mbrTp1" value="1"> 수강명령 과정</span>
								<span class="ck-bx"><input type="checkbox" name="mbrTp2" value="1"> 이수명령 과정</span>
								<span class="ck-bx"><input type="checkbox" name="mbrTp3" value="1"> 기본과정</span>
								<span class="ck-bx"><input type="checkbox" name="mbrTp4" value="1"> 집중과정</span>
								<span class="ck-bx"><input type="checkbox" name="mbrTp5" value="1"> 심화과정</span>
								<span class="ck-bx"><input type="checkbox" name="mbrTp6" value="1"> 단기과정</span>
							</td>
						</tr>
						<tr>
							<th> 결혼여부</th>
							<td>
								<select name="mrgCd" style="width: 150px;">
									<option value="">선택</option>
<c:if test="${mrgCdList ne null and mrgCdList ne ''}">
	<c:forEach var="result" items="${mrgCdList}" varStatus="status">
									<option value="<c:out value="${result.CD_ID}" />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
								</select>
							</td>
							<th>학력</th>
							<td colspan="5">
								<select name="eduCd" style="width: 135px;">
									<option value="">선택</option>
<c:if test="${eduCdList ne null and eduCdList ne ''}">
	<c:forEach var="result" items="${eduCdList}" varStatus="status">
									<option value="<c:out value="${result.CD_ID}" />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
								</select>
								<select name="edu02Cd" style="width: 135px;">
									<option value="">선택</option>
<c:if test="${edu02CdList ne null and edu02CdList ne ''}">
	<c:forEach var="result" items="${edu02CdList}" varStatus="status">
									<option value="<c:out value="${result.CD_ID}" />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
								</select>
							</td>
						</tr>
						<tr>
							<th>종교</th>
							<td>
								<select name="rlgnCd" style="width: 150px;">
<c:if test="${rlgnCdList ne null and rlgnCdList ne ''}">
	<c:forEach var="result" items="${rlgnCdList}" varStatus="status">
									<option value="<c:out value="${result.CD_ID}" />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
								</select>
							</td>
							<th>직업</th>
							<td colspan="5">
								<select name="jobCd" style="width: 270px;">
									<option value="">선택</option>
<c:if test="${jobCdList ne null and jobCdList ne ''}">
	<c:forEach var="result" items="${jobCdList}" varStatus="status">
									<option value="<c:out value="${result.CD_ID}" />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
								</select>
							</td>
						</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="row2">
			<div class="section pdn mgn">
				<div class="el-card_header">
					<h2><i class="el-icon-s-opportunity"></i> 중독력</h2>
				</div>
				<div class="el-card_body">
					<table class="w-auto wr-form">
						<colgroup>
							<col style="width: 70px;">
							<col style="width: 70px;">
							<col style="width: 70px;">
							<col style="width: 70px;">
							<col style="width: 70px;">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th class="v-top"><span class="required">*</span> 최초사용약물</th>
								<td colspan="5">
									<select name="fstDrugCd">
										<option value="">선택</option>
<c:if test="${fstDrugList ne null and fstDrugList ne ''}">
	<c:forEach var="result" items="${fstDrugList}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}" />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
									</select>
									<div style="margin-top:5px">
										<input type="text" name="fstDrug" class="el-input__inner" style="width: 100%;">
									</div>
								</td>
							</tr>
							<tr>
								<th class="v-top"><span class="required">*</span> 주요사용약물</th>
								<td colspan="5">
									<select name="mainDrugCd">
										<option value="">선택</option>
<c:if test="${mainDrugList ne null and mainDrugList ne ''}">
	<c:forEach var="result" items="${mainDrugList}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}" />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
									</select>
									<div style="margin-top:5px">
										<input type="text" name="mainDrug" class="el-input__inner" style="width: 100%;"/>
									</div>
								</td>
							</tr>
							<tr>
								<th><span class="required">*</span> 최초사용시기</th>
								<td><input type="text" name="fstAge" class="el-input__inner" style="width: 100%;"></td>
								<th>마지막 사용시기</th>
								<td><input type="text" name="lstAge" class="el-input__inner" style="width: 100%;"></td>
								<th>사용기간</th>
								<td><input type="text" name="useTerm" class="el-input__inner" style="width: 100%;"></td>
							</tr>
							<tr>
								<th>사용빈도</th>
								<td>
									<select name="useFrqCd">
										<option value="">선택</option>
<c:if test="${useFrqList ne null and useFrqList ne ''}">
	<c:forEach var="result" items="${useFrqList}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}" />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
									</select>
								</td>
								<th>사용원인</th>
								<td colspan="3">
									<select name="useCauCd" onchange="javaScript:inputDisabledChang(this, 'useCauEtc');">
										<option value="">선택</option>
<c:if test="${useCauList ne null and useCauList ne ''}">
	<c:forEach var="result" items="${useCauList}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}" />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
									</select>
									<input type="text" name="useCauEtc" class="el-input__inner" disabled style="width: 100%;"/>
								</td>
							</tr>
							<tr>
								<th>약물관련 법적문제</th>
								<td colspan="5">
									<select name="lawPbmCd" onchange="javaScript:inputDisabledChang(this, 'lawPbmEtc');">
										<option value="">선택</option>
<c:if test="${lawPbmList ne null and lawPbmList ne ''}">
	<c:forEach var="result" items="${lawPbmList}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}" />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
									</select>
									<input type="text" name="lawPbmEtc" class="el-input__inner" disabled style="width: 100%;"/>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	</form>
	<div class="section">
		<table class="w-auto">
			<tbody>
			<tr>
				<th><span class="required">*</span> 최초등록일자</th>
				<td><input name="creDt" type="text" class="el-input__inner" placeholder="날짜" style="width: 130px;"></td>
				<th>최종수정일시</th>
				<td><input name="updDt" type="text" class="el-input__inner" readonly style="width:160px"></td>
				<th>최종수정자</th>
				<td><input name="updNm" type="text" class="el-input__inner" readonly style="width:120px"></td>
			</tr>
			</tbody>
		</table>
	</div>
</div>
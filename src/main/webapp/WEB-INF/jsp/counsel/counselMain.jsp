<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javaScript" language="javascript" defer="defer">
	$(document).ready(function(){
		var tagMbrInfo = "";
		<%-- 상담정보 저장 --%>
		counselSave = function(){
			var str = "rcpNo:" + $("input[name='rcpNo']").val() + ", ";
				str += "cslDt:" + $("input[name='cslDt']").val() + ", ";
				str += "cslFmTm:" + $("input[name='cslFmTm']").val() + ", ";
				str += "cslToTm:" + $("input[name='cslToTm']").val() + ", ";
				str += "ifpGbCd:" + $("input[name='ifpGbCd']:radio:checked").val() + ", ";
				str += "ifpGbEtc:" + $("input[name='ifpGbEtc']").val() + ", ";
				str += "ifpNm:" + $("input[name='ifpNm']").val() + ", ";
				str += "ifpMbrNo:" + $("input[name='ifpMbrNo']").val() + ", ";
				str += "ifpGendCd:" + $("input[name='ifpGendCd']:radio:checked").val() + ", ";
				str += "ifpAge:" + $("input[name='ifpAge']").val() + ", ";
				str += "ifpTelNo1:" + $("input[name='ifpTelNo1']").val() + ", ";
				str += "ifpTelNo2:" + $("input[name='ifpTelNo2']").val() + ", ";
				str += "ifpTelNo3:" + $("input[name='ifpTelNo3']").val() + ", ";
				str += "ifpJobCd:" + $("select[name='ifpJobCd']").val() + ", ";
				str += "ifpAreaCd:" + $("select[name='ifpAreaCd']").val() + ", ";
				str += "ifpAreaEtc:" + $("input[name='ifpAreaEtc']").val() + ", ";
				str += "tgpNm:" + $("input[name='tgpNm']").val() + ", ";
				str += "tgpMbrNo:" + $("input[name='tgpMbrNo']").val() + ", ";
				str += "tgpGendCd:" + $("input[name='tgpGendCd']:radio:checked").val() + ", ";
				str += "tgpAge:" + $("input[name='tgpAge']").val() + ", ";
				str += "tgpTelNo1:" + $("input[name='tgpTelNo1']").val() + ", ";
				str += "tgpTelNo2:" + $("input[name='tgpTelNo2']").val() + ", ";
				str += "tgpTelNo3:" + $("input[name='tgpTelNo3']").val() + ", ";
				str += "tgpJobCd:" + $("select[name='tgpJobCd']").val() + ", ";
				str += "tgpFrgCd:" + $("input[name='tgpFrgCd']:radio:checked").val() + ", ";
				str += "tgpAreaCd:" + $("select[name='tgpAreaCd']").val() + ", ";
				str += "tgpAreaEtc:" + $("input[name='tgpAreaEtc']").val() + ", ";
				str += "ifPathCd:" + $("select[name='ifPathCd']").val() + ", ";
				str += "pbmKndCd:" + $("select[name='pbmKndCd']").val() + ", ";
				str += "cslTpCd:" + $("select[name='cslTpCd']").val() + ", ";
				str += "fstDrugCd:" + $("select[name='fstDrugCd']").val() + ", ";
				str += "mainDrugCd:" + $("select[name='mainDrugCd']").val() + ", ";
				str += "mainDrug:" + $("input[name='mainDrug']").val() + ", ";
				str += "mjrMngCd:" + $("select[name='mjrMngCd']").val() + ", ";
				str += "astSco:" + $("input[name='astSco']").val() + ", ";
				str += "rskSco:" + $("input[name='rskSco']").val() + ", ";
				str += "rskaTpCd:" + $("select[name='rskaTpCd']").val() + ", ";
				str += "rskbTpCd:" + $("select[name='rskbTpCd']").val() + ", ";
				str += "rskcTpCd:" + $("select[name='rskcTpCd']").val() + ", ";
				str += "cslCtnt:" + $("textarea[name='cslCtnt']").val() + ", ";
			console.log(str);

			$.ajax({
				url : '/ajaxCounselAdd.do',
				type : 'POST',
				data : $('#counselForm').serialize(),
				success : function(res){
					if(res.err != "Y"){
						alert("등록 완료");
					}else{
						alert(res.MSG);

						if(res.actUrl != "" && res.actUrl != undefined){
							window.location.href = res.actUrl;
						}
					}
				},
				error : function(xhr, status){
				}
			});

			console.log("저장");
		},
		<%-- 신규 --%>
		counselNew = function(){
			window.location.reload();
		},
		<%-- 복사 --%>
		counselCopy = function(){
			$("input[name='rcpNo']").val("");
		},
		<%-- 상담 내용 삭제 --%>
		counselDel = function(){
			$.ajax({
				url : '/ajaxClsRcpDel.do',
				type : 'POST',
				data : {
					rcpNo : $("input[name='rcpNo']").val()
				},
				success : function(res){
					if(res.err != "Y"){
						alert("삭제 완료");
					}else{
						alert(res.MSG);

						if(res.actUrl != "" && res.actUrl != undefined){
							window.location.href = res.actUrl;
						}
					}
				},
				error : function(xhr, status){
				}
			});
			console.log("삭제");
		},
		counselExel = function(){
			console.log("엑셀다운로드");
		},
		<%-- 상담 목록 조회 --%>
		getRcpNo = function(){
			$.ajax({
				url : '/getClsRcpList.do',
				type : 'POST',
				data : $('#rcpNoSchForm').serialize(),
				success : function(res){
					$("div[id='layerpopup']").html(res);
					$("div[id='layerpopup']").attr("data-popup", "rcptPopUp");

					layerPopupOpen('rcptPopUp');
				},
				error : function(xhr, status){
					console.log(xhr);
				}
			});
			console.log("상담조회");
		},
		<%-- 회원 조회 --%>
		mstMbrSearchPopup = function(resFuct){
			$.ajax({
				url : '/ajaxMstMbrList.do',
				type : 'POST',
				data : {},
				success : function(res){
					$("div[id='layerpopup']").html(res);
					$("div[id='layerpopup']").attr("data-popup", "memberPopUp");
					layerPopupOpen('memberPopUp');
/*
					if(res.totalCount > 0){

						tagMbrInfo = res.resultList[0];
						(new Function(resFuct + "('" + res.resultList[0].MBR_NO + "');"))();
					}else{
						tagMbrInfo = "";
						console.log("회원 정보 없음");
					}
*/
				},
				error : function(xhr, status){
					tagMbrInfo = "";
					console.log(xhr);
				}
			});
		},
		<%-- 정보 제공자 회원 정보 셋팅 --%>
		ifpMbrSearchPopup = function(tagMbrMbrNo){
			if(tagMbrInfo.MBR_NO == tagMbrMbrNo){
				$("input[name='ifpNm']").val(tagMbrInfo.MBR_NM);
				$("input[name='ifpMbrNo']").val(tagMbrInfo.MBR_NO);
				$("input[name='ifpGendCd']:radio[value='" + tagMbrInfo.GEND_CD + "']").prop("checked", true);
				$("input[name='ifpAge']").val(tagMbrInfo.AGE);
				$("input[name='ifpTelNo1']").val(tagMbrInfo.TEL_NO1);
				$("input[name='ifpTelNo2']").val(tagMbrInfo.TEL_NO2);
				$("input[name='ifpTelNo3']").val(tagMbrInfo.TEL_NO3);
				$("select[name='ifpJobCd']").val(tagMbrInfo.JOB_CD).prop("selected", true);
			}
		},
		<%-- 정보제공자 복사 --%>
		ifpCopy = function(){
			$("input[name='tgpNm']").val($("input[name='ifpNm']").val());
			$("input[name='tgpMbrNo']").val($("input[name='ifpMbrNo']").val());
			$("input[name='tgpGendCd']:radio[value='" + $("input[name='ifpGendCd']:radio:checked").val() + "']").prop("checked", true);
			$("input[name='tgpAge']").val($("input[name='ifpAge']").val());
			$("input[name='tgpTelNo1']").val($("input[name='ifpTelNo1']").val());
			$("input[name='tgpTelNo2']").val($("input[name='ifpTelNo2']").val());
			$("input[name='tgpTelNo3']").val($("input[name='ifpTelNo3']").val());
			$("select[name='tgpJobCd']").val($("select[name='ifpJobCd']").val()).prop("selected", true);
			$("select[name='tgpAreaCd']").val($("select[name='ifpAreaCd']").val()).prop("selected", true);
			$("input[name='tgpAreaEtc']").val($("input[name='ifpAreaEtc']").val());
		},
		<%-- 대상자 회원 정보 셋팅 --%>
		tgpMbrSearchPopup = function(tagMbrMbrNo){
			if(tagMbrInfo.MBR_NO == tagMbrMbrNo){
				$("input[name='tgpNm']").val(tagMbrInfo.MBR_NM);
				$("input[name='tgpMbrNo']").val(tagMbrInfo.MBR_NO);
				$("input[name='tgpGendCd']:radio[value='" + tagMbrInfo.GEND_CD + "']").prop("checked", true);
				$("input[name='tgpAge']").val(tagMbrInfo.AGE);
				$("input[name='tgpTelNo1']").val(tagMbrInfo.TEL_NO1);
				$("input[name='tgpTelNo2']").val(tagMbrInfo.TEL_NO2);
				$("input[name='tgpTelNo3']").val(tagMbrInfo.TEL_NO3);
				$("select[name='tgpJobCd']").val(tagMbrInfo.JOB_CD).prop("selected", true);
				$("input[name='tgpFrgCd']:radio[value='" + tagMbrInfo.FRG_CD + "']").prop("checked", true);
			}
		},
		<%-- 상담내용 상세조회 --%>
		counselInfoViewSet = function(tagRcpNo){
			$.ajax({
				url : '/ajaxClsRcpInfo.do',
				type : 'POST',
				data : {
					rcpNo : tagRcpNo
				},
				success : function(res){
					if(res.cslRcpInfo != null){
						$("input[name='rcpNo']").val(res.cslRcpInfo.RCP_NO);
						$("input[name='cslDt']").val(res.cslRcpInfo.CSL_DT);
						$("input[name='cslFmTm']").val(res.cslRcpInfo.CSL_FM_TM);
						$("input[name='cslToTm']").val(res.cslRcpInfo.CSL_TO_TM);
						$("#cslTermTm").text(res.cslRcpInfo.CSL_TERM_TM);
						$("input[name='cslId']").val(res.cslRcpInfo.CSL_ID);
						$("input[name='cslNm']").val(res.cslRcpInfo.CSL_NM);
						$("input[name='ifpGbCd']:radio[value='" + res.cslRcpInfo.IFP_GB_CD + "']").prop("checked", true);
						$("input[name='ifpGbEtc']").val(res.cslRcpInfo.IFP_GB_ETC);
						$("input[name='ifpNm']").val(res.cslRcpInfo.IFP_NM);
						$("input[name='ifpMbrNo']").val(res.cslRcpInfo.IFP_MBR_NO);
						$("input[name='ifpGendCd']:radio[value='" + res.cslRcpInfo.IFP_GEND_CD + "']").prop("checked", true);
						$("input[name='ifpAge']").val(res.cslRcpInfo.IFP_AGE);
						$("input[name='ifpTelNo1']").val(res.cslRcpInfo.IFP_TEL_NO1);
						$("input[name='ifpTelNo2']").val(res.cslRcpInfo.IFP_TEL_NO2);
						$("input[name='ifpTelNo3']").val(res.cslRcpInfo.IFP_TEL_NO3);
						$("select[name='ifpJobCd']").val(res.cslRcpInfo.IFP_JOB_CD).prop("selected", true);
						$("select[name='ifpAreaCd']").val(res.cslRcpInfo.IFP_AREA_CD).prop("selected", true);
						$("input[name='ifpAreaEtc']").val(res.cslRcpInfo.IFP_AREA_ETC);
						$("input[name='tgpNm']").val(res.cslRcpInfo.TGP_NM);
						$("input[name='tgpMbrNo']").val(res.cslRcpInfo.TGP_MBR_NO);
						$("input[name='tgpGendCd']:radio[value='" + res.cslRcpInfo.TGP_GEND_CD + "']").prop("checked", true);
						$("input[name='tgpAge']").val(res.cslRcpInfo.TGP_AGE);
						$("input[name='tgpTelNo1']").val(res.cslRcpInfo.TGP_TEL_NO1);
						$("input[name='tgpTelNo2']").val(res.cslRcpInfo.TGP_TEL_NO2);
						$("input[name='tgpTelNo3']").val(res.cslRcpInfo.TGP_TEL_NO3);
						$("select[name='tgpJobCd']").val(res.cslRcpInfo.TGP_JOB_CD).prop("selected", true);
						$("input[name='tgpFrgCd']:radio[value='" + res.cslRcpInfo.TGP_FRG_CD + "']").prop("checked", true);
						$("select[name='tgpAreaCd']").val(res.cslRcpInfo.TGP_AREA_CD).prop("selected", true);
						$("input[name='tgpAreaEtc']").val(res.cslRcpInfo.TGP_AREA_ETC);
						$("select[name='ifPathCd']").val(res.cslRcpInfo.IF_PATH_CD).prop("selected", true);
						$("select[name='pbmKndCd']").val(res.cslRcpInfo.PBM_KND_CD).prop("selected", true);
						$("select[name='cslTpCd']").val(res.cslRcpInfo.CSL_TP_CD).prop("selected", true);
						$("select[name='fstDrugCd']").val(res.cslRcpInfo.FST_DRUG_CD).prop("selected", true);
						$("select[name='mainDrugCd']").val(res.cslRcpInfo.MAIN_DRUG_CD).prop("selected", true);
						$("input[name='mainDrug']").val(res.cslRcpInfo.MAIN_DRUG);
						$("select[name='mjrMngCd']").val(res.cslRcpInfo.MJR_MNG_CD).prop("selected", true);
						$("input[name='astSco']").val(res.cslRcpInfo.AST_SCO);
						$("input[name='rskSco']").val(res.cslRcpInfo.RSK_SCO);
						$("select[name='rskaTpCd']").val(res.cslRcpInfo.RSKA_TP_CD).prop("selected", true);
						$("select[name='rskbTpCd']").val(res.cslRcpInfo.RSKB_TP_CD).prop("selected", true);
						$("select[name='rskcTpCd']").val(res.cslRcpInfo.RSKC_TP_CD).prop("selected", true);
						$("textarea[name='cslCtnt']").val(res.cslRcpInfo.CSL_CTNT);

						layerPopupClose('rcptPopUp');
					}else{
						console.log("상세내용 조회 오류");
					}
				},
				error : function(xhr, status){
					console.log(xhr);
				}
			});
		},
		<%-- 상담내용보기 --%>
		ctntPopup = function(){
			$("div[id='layerpopup']").html(res);
			$("div[id='layerpopup']").attr("data-popup", "counwrite");
			layerPopupOpen('counwrite');
		}
	});
</script>
<!-- 페이지 타이틀 -->
<div class="tit-area">
	<h1><i class="el-icon-s-order" style="color: rgb(0, 108, 185);"></i> 일반 상담</h1>
</div>
<!-- // 페이지 타이틀 -->
<form name="counselForm" id="counselForm">
<!-- 상단 버튼 -->
<div class="top-right-btn">
	<button type="button" onclick="javaScript:counselSave();" class="el-button normal el-button--primary el-button--small is-plain"><i class="el-icon-download"></i><span>저장</span></button>
	<button type="button" onclick="javaScript:counselNew();" class="el-button normal el-button--default el-button--small is-plain" style="margin-left: 8px;"><i class="el-icon-circle-plus-outline"></i><span>신규</span></button>
	<button type="button" onclick="javaScript:counselCopy();" class="el-button normal el-button--default el-button--small is-plain"><i class="el-icon-document-copy"></i><span>복사</span></button>
	<button type="button" onclick="javaScript:counselDel();" class="el-button normal el-button--default el-button--small is-plain"><i class="el-icon-delete-solid"></i><span>삭제</span></button>
	<button type="button" onclick="javaScript:counselExel();" class="el-button normal el-button--default el-button--small is-plain"><i class="el-icon-document"></i><span>엑셀다운로드</span></button>
	<button disabled="disabled" type="button" class="el-button normal el-button--default el-button--small is-disabled is-plain"><i class="el-icon-document-copy"></i><span>복사</span></button>
	<button disabled="disabled" type="button" class="el-button normal el-button--default el-button--small is-disabled is-plain"><i class="el-icon-delete-solid"></i><span>삭제</span></button>
	<button disabled="disabled" type="button" class="el-button normal el-button--default el-button--small is-disabled is-plain"><i class="el-icon-document"></i><span>엑셀다운로드</span></button>
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
							<form:input path="cslRcpInfo.rcpNo" cssClass="el-input__inner" readonly="true" placeholder="저장시 자동 생성" style="width: 142px;" />
							<button type="button" onclick="javaScript:getRcpNo();"><i class="el-icon-search"></i></button>
						</div>
					</td>
					<th><span class="required">*</span> 상담일시</th>
					<td>
						<div class="dat-pk">
							<i class="el-input__icon el-icon-date"></i>
							<form:input path="cslRcpInfo.cslDt" cssClass="el-input__inner datepicker" format="yyyy-MM-dd" placeholder="날짜 선택" maxlength="8" style="width: 130px;" />
						</div>
						<div class="time-box">
							<div class="tm-in">
								<i class="el-input__icon el-icon-time"></i>
								<form:input path="cslRcpInfo.cslFmTm" cssClass="el-input__inner timepicker" format="HH:mm" placeholder="시작" maxlength="4" style="width: 96px;" />
							</div>
							<span>~</span>
							<div class="tm-in">
								<i class="el-input__icon el-icon-time"></i>
								<form:input path="cslRcpInfo.cslToTm" cssClass="el-input__inner timepicker" format="HH:mm" placeholder="종료" maxlength="4" style="width: 96px;" />
							</div>
							<div class="t-min">
								<span class="readonly" id="cslTermTm">0</span> 분 소요
							</div>
						</div>
					</td>
					<th>상담자</th>
					<td>
						<span class="tac"><form:input path="cslRcpInfo.cslId" cssClass="el-input__inner" readonly="true" style="width: 100px;" /></span>
						<span class="tac"><form:input path="cslRcpInfo.cslNm" cssClass="el-input__inner" readonly="true" style="width:150px" /></span>
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
					<th>정보제공자/본인여부</th>
					<td>
<c:forEach var="result" items="${ifpGbList}" varStatus="status">
						<span class="ck-bx">
							<input type="radio" class="el-radio__original" name="ifpGbCd" id="ifpGbCd-<c:out value='${status.count}'/>" value="${result.CD_ID}"<c:if test="${result.CD_ID eq cslRcpInfo.ifpGbCd}"> checked</c:if> />
							<label for="ifpGbCd-<c:out value='${status.count}'/>"><span class="el-radio__input"><span class="el-radio__inner"></span></span><c:out value="${result.CD_NM}" /></label>
						</span>
</c:forEach>
						<div class="etc-txt">
							<form:input path="cslRcpInfo.ifpGbEtc" cssClass="el-input__inner" placeholder="기타 선택시 입력 가능" disabled="true" style="width: 256px;" />
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
										<form:input path="cslRcpInfo.ifpNm" cssClass="el-input__inner" placeholder="정보제공자 성명" style="width: 142px;" />
										<button type="button" onclick="javaScript:mstMbrSearchPopup('ifpMbrSearchPopup');"><i class="el-icon-search"></i></button>
									</div>
								</td>
								<th>회원번호</th>
								<td>
									<span class="tac">
										<form:input path="cslRcpInfo.ifpMbrNo" cssClass="el-input__inner" placeholder="회원번호" disabled="true" style="width: 140px;" />
									</span>
								</td>
								<th><span class="required">*</span> 성별</th>
								<td>
									<div class="gen-check">
										<span>
											<input type="radio" class="el-radio-button__orig-radio" name="ifpGendCd" id="ifpGendCd-1" value="M"<c:if test="${cslRcpInfo.ifpGendCd eq 'M'}"> checked</c:if> />
											<label for="ifpGendCd-1" class="el-radio-button el-radio-button--small">
												<span class="el-radio-button__inner">남</span>
											</label>
										</span>
										<span>
											<input type="radio" class="el-radio-button__orig-radio" name="ifpGendCd" id="ifpGendCd-2" value="F"<c:if test="${cslRcpInfo.ifpGendCd eq 'F'}"> checked</c:if> />
											<label for="ifpGendCd-2" class="el-radio-button el-radio-button--small">
												<span class="el-radio-button__inner">여</span>
											</label>
										</span>
									</div>
								</td>
								<th><span class="required">*</span> 연령</th>
								<td class="tac"><form:input path="cslRcpInfo.ifpAge" cssClass="el-input__inner" placeholder="연령" maxlength="3" style="width: 60px;" /></td>
							</tr>
							<tr>
								<th><span class="required">*</span> 연락처</th>
								<td>
									<div class="dsp-ibk"><form:input path="cslRcpInfo.ifpTelNo1" cssClass="el-input__inner" maxlength="4" style="width:54px" /></div>
									<span>-</span>
									<div class="dsp-ibk"><form:input path="cslRcpInfo.ifpTelNo2" cssClass="el-input__inner" maxlength="4" style="width:54px" /></div>
									<span>-</span>
									<div class="dsp-ibk"><form:input path="cslRcpInfo.ifpTelNo3" cssClass="el-input__inner" maxlength="4" style="width:53px" /></div>
								</td>
								<th><span class="required">*</span> 직업</th>
								<td>
									<select name="ifpJobCd" style="width: 140px;">
										<option value="">선택</option>
<c:forEach var="result" items="${jobList}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<th><span class="required">*</span> 지역</th>
								<td colspan="4">
									<span class="el-inp mgr3">
										<select name="ifpAreaCd" style="width: 178px;">
											<option value="">선택</option>
<c:forEach var="result" items="${areaList}" varStatus="status">
											<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
</c:forEach>
										</select>
									</span>
									<span class="dsp-ibk">
										<form:input path="cslRcpInfo.ifpAreaEtc" cssClass="el-input__inner" placeholder="기타 선택시 입력 가능" disabled="true" style="width: 220px;" />
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
										<form:input path="cslRcpInfo.tgpNm" cssClass="el-input__inner" placeholder="대상자 성명" style="width: 142px;" />
										<button type="button" onclick="javaScript:mstMbrSearchPopup('tgpMbrSearchPopup');"><i class="el-icon-search"></i></button>
									</div>
								</td>
								<th>회원번호</th>
								<td>
									<span class="dsp-ibk tac">
										<form:input path="cslRcpInfo.tgpMbrNo" cssClass="el-input__inner" placeholder="회원번호" disabled="true" style="width: 140px;" />
									</span>
								</td>
								<th><span class="required">*</span> 성별</th>
								<td>
									<div class="gen-check">
										<span>
											<input type="radio" class="el-radio-button__orig-radio" name="tgpGendCd" value="M" id="tgpGendCd-1"<c:if test="${cslRcpInfo.tgpGendCd eq 'M'}"> checked</c:if> />
											<label for="tgpGendCd-1" class="el-radio-button el-radio-button--small">
												<span class="el-radio-button__inner">남</span>
											</label>
										</span>
										<span>
											<input type="radio" class="el-radio-button__orig-radio" name="tgpGendCd" value="F" id="tgpGendCd-2"<c:if test="${cslRcpInfo.tgpGendCd eq 'F'}"> checked</c:if> />
											<label for="tgpGendCd-2" class="el-radio-button el-radio-button--small">
												<span class="el-radio-button__inner">여</span>
											</label>
										</span>
									</div>
								</td>
								<th><span class="required">*</span> 연령</th>
								<td class="tac"><form:input path="cslRcpInfo.tgpAge" cssClass="el-input__inner" maxlength="3" placeholder="연령" style="width: 60px;" /></td>
							</tr>
							<tr>
								<th><span class="required">*</span> 연락처</th>
								<td>
									<div class="dsp-ibk"><form:input path="cslRcpInfo.tgpTelNo1" cssClass="el-input__inner" maxlength="4" style="width:54px" /></div>
									<span>-</span>
									<div class="dsp-ibk"><form:input path="cslRcpInfo.tgpTelNo2" cssClass="el-input__inner" maxlength="4" style="width:54px" /></div>
									<span>-</span>
									<div class="dsp-ibk"><form:input path="cslRcpInfo.tgpTelNo3" cssClass="el-input__inner" maxlength="4" style="width:53px" /></div>
								</td>
								<th><span class="required">*</span> 직업</th>
								<td>
									<select name="tgpJobCd" style="width: 140px;">
										<option value="">선택</option>
<c:forEach var="result" items="${jobList}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
</c:forEach>
									</select>
								</td>
								<th><span class="required">*</span> 내/외국인</th>
								<td>
									<span class="ck-bx">
										<input type="radio" class="el-radio__original" name="tgpFrgCd" id="tgpFrgCd1" value="LO"<c:if test="${cslRcpInfo.tgpFrgCd eq 'LO'}"> checked</c:if> />
										<label for="tgpFrgCd1"><span class="el-radio__input"><span class="el-radio__inner"></span></span> 내국인</label>
									</span>
									<span class="ck-bx">
										<input type="radio" class="el-radio__original" name="tgpFrgCd" id="tgpFrgCd2" value="FO"<c:if test="${cslRcpInfo.tgpFrgCd eq 'FO'}"> checked</c:if> />
										<label for="tgpFrgCd2"><span class="el-radio__input"><span class="el-radio__inner"></span></span> 외국인</label>
									</span>
								</td>
							</tr>
							<tr>
								<th><span class="required">*</span> 지역</th>
								<td colspan="4">
									<span class="el-inp mgr3">
										<select name="tgpAreaCd" style="width: 178px;">
											<option value="">선택</option>
<c:forEach var="result" items="${areaList}" varStatus="status">
											<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
</c:forEach>
										</select>
									</span>
									<span class="dsp-ibk">
										<form:input path="cslRcpInfo.tgpAreaEtc" cssClass="el-input__inner" placeholder="기타 선택시 입력 가능" style="width: 220px;" disabled="true" />
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
	<div class="section el-row">
		<div class="row">
			<table class="w-auto wr-form">
				<tbody>
					<tr>
						<th><span class="required">*</span> 정보취득경로</th>
						<td>
							<select name="ifPathCd" style="width: 132px;">
								<option value="">선택</option>
<c:forEach var="result" items="${pathList}" varStatus="status">
								<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
</c:forEach>
							</select>
						</td>
						<th><span class="required">*</span> 주호소문제</th>
						<td>
							<select name="pbmKndCd" style="width: 112px;">
								<option value="">선택</option>
<c:forEach var="result" items="${kndList}" varStatus="status">
								<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
</c:forEach>
							</select>
						</td>
						<th><span class="required">*</span> 상담유형</th>
						<td>
							<select name="cslTpCd" style="width: 100px;">
								<option value="">선택</option>
<c:forEach var="result" items="${tpList}" varStatus="status">
								<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th><span class="required">*</span> 최초사용약물</th>
						<td colspan="5">
							<select name="fstDrugCd" style="width: 100%;">
								<option value="">선택</option>
<c:forEach var="result" items="${fstDrugList}" varStatus="status">
								<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th><span class="required">*</span> 주요사용약물</th>
						<td colspan="5">
							<select name="mainDrugCd" style="width: 100%;">
								<option value="">선택</option>
<c:forEach var="result" items="${mainDrugList}" varStatus="status">
								<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
</c:forEach>
							</select>
							<div style="margin-top:5px"><form:input path="cslRcpInfo.mainDrug" cssClass="el-input__inner" placeholder="주요사용약물 입력" style="width: 100%;" /></div>
						</td>
					</tr>
					<tr>
						<th class="v-top"><span class="required">*</span> 주요조치</th>
						<td colspan="5">
							<select name="mjrMngCd" style="width: 132px;">
								<option value="">선택</option>
<c:forEach var="result" items="${mjrMngList}" varStatus="status">
								<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th>ASSIST 점수</th>
						<td colspan="5"><form:input path="cslRcpInfo.astSco" cssClass="el-input__inner" maxlength="4" style="width:75px" /></td>
					</tr>
				</tbody>
			</table>
			<table class="w-auto wr-form">
				<tbody>
					<tr>
						<th><span class="required">*</span> 위기분류척도</th>
						<td>
							<span class="el-tag">Rating A: 위험성</span>
							<select name="rskaTpCd" style="width: 690px;">
<c:forEach var="result" items="${rskaTpList}" varStatus="status">
								<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th class="txt-center">점수</th>
						<td>
							<span class="el-tag">Rating B: 지지체계</span>
							<select name="rskbTpCd" style="width: 690px;">
<c:forEach var="result" items="${rskbTpList}" varStatus="status">
								<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th class="txt-center"><span class="el-tag-danger"><c:out value="${cslRcpInfo.rskSco}"/></span></th>
						<td>
							<span class="el-tag">Rating B: 지지체계</span>
							<select name="rskcTpCd" style="width: 690px;">
<c:forEach var="result" items="${rskcTpList}" varStatus="status">
								<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
</c:forEach>
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
						<th>
							<span class="required">*</span> 상담내용<br>
							<button type="button" onclick="javaScript:ctntPopup();" class="el-button el-button--success el-button--mini is-plain" style="padding: 4px 6px;">
								<i class="el-icon-search"></i>
							</button>
						</th>
						<td><textarea name="cslCtnt" placeholder="상담 내용" style="width:99%;height:385px"></textarea></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<!-- // 정보취득경로 ~ 위기분류척도 / 상담내용-->
</form>
</div>
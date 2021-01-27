<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javaScript" language="javascript" defer="defer">
	$(document).ready(function(){
		$("input[name='cslDt']").datepicker('setDate', 'today');
		<%-- 상담정보 저장 --%>
		counselSave = function(){
			if($("input[name='cslDt']").val() == ""){
				alert("상담일은 필수 입력 항목입니다.");
				$("input[name='cslDt']").focus();			return;
			}
			if($("input[name='cslFmTm']").val() == ""){
				alert("상담시는 필수 입력 항목입니다.");
				$("input[name='cslFmTm']").focus();			return;
			}
			if($("input[name='cslToTm']").val() == ""){
				alert("상담종료시 필수 입력 항목입니다.");
				$("input[name='cslToTm']").focus();			return;
			}
			if(needTime($("input[name='cslFmTm']").val(), $("input[name='cslToTm']").val()) <= 0){
				alert("상담 소요시간은 0보다 값이 커야 합니다.");
				$("input[name='cslToTm']").focus();			return;
			}
			if($("input[name='ifpNm']").val() == ""){
				alert("정보제공자 성명은 필수 입력 항목입니다.");
				$("input[name='ifpNm']").focus();			return;
			}
			if($("input[name='ifpAge']").val() == ""){
				alert("정보제공자 연령은 필수 입력 항목입니다.");
				$("input[name='ifpAge']").focus();			return;
			}
			if($("input[name='ifpTelNo1']").val() == ""){
				alert("정보제공자 전화번호1는 필수 입력 항목입니다.");
				$("input[name='ifpTelNo1']").focus();		return;
			}
			if($("input[name='ifpTelNo2']").val() == ""){
				alert("정보제공자 전화번호2는 필수 입력 항목입니다.");
				$("input[name='ifpTelNo2']").focus();		return;
			}
			if($("input[name='ifpTelNo3']").val() == ""){
				alert("정보제공자 전화번호3는 필수 입력 항목입니다.");
				$("input[name='ifpTelNo3']").focus();		return;
			}
			if($("select[name='ifpJobCd']").val() == ""){
				alert("정보제공자 직업은 필수 입력 항목입니다.");
				$("select[name='ifpJobCd']").focus();		return;
			}
			if($("select[name='ifpAreaCd']").val() == ""){
				alert("정보제공자의 지역은 필수 입력 항목입니다.");
				$("select[name='ifpAreaCd']").focus();		return;
			}
			if($("input[name='tgpNm']").val() == ""){
				alert("대상자 성명은 필수 입력 항목입니다.");
				$("input[name='tgpNm']").focus();			return;
			}
			if($("input[name='tgpAge']").val() == ""){
				alert("대상자 연령은 필수 입력 항목입니다.");
				$("input[name='tgpAge']").focus();			return;
			}
			if($("input[name='tgpTelNo1']").val() == ""){
				alert("대상자 전화번호1는 필수 입력 항목입니다.");
				$("input[name='tgpTelNo1']").focus();		return;
			}
			if($("input[name='tgpTelNo2']").val() == ""){
				alert("대상자 전화번호2는 필수 입력 항목입니다.");
				$("input[name='tgpTelNo2']").focus();		return;
			}
			if($("input[name='tgpTelNo3']").val() == ""){
				alert("대상자 전화번호3는 필수 입력 항목입니다.");
				$("input[name='tgpTelNo3']").focus();		return;
			}
			if($("select[name='tgpJobCd']").val() == ""){
				alert("대상자 직업은 필수 입력 항목입니다.");
				$("select[name='tgpJobCd']").focus();		return;
			}
			if($("select[name='tgpAreaCd']").val() == ""){
				alert("대상자 지역은 필수 입력 항목입니다.");
				$("select[name='tgpAreaCd']").focus();		return;
			}
			if($("select[name='ifPathCd']").val() == ""){
				alert("정보취득경로는 필수 입력 항목입니다.");
				$("select[name='ifPathCd']").focus();		return;
			}
			if($("select[name='pbmKndCd']").val() == ""){
				alert("주호소문제는 필수 입력 항목입니다.");
				$("select[name='pbmKndCd']").focus();		return;
			}
			if($("select[name='cslTpCd']").val() == ""){
				alert("상담유형은 필수 입력 항목입니다.");
				$("select[name='cslTpCd']").focus();		return;
			}
			if($("select[name='fstDrugCd']").val() == ""){
				alert("최초사용약물은 필수 입력 항목입니다.");
				$("select[name='fstDrugCd']").focus();		return;
			}
			if($("select[name='mainDrugCd']").val() == ""){
				alert("주사용약물은 필수 입력 항목입니다.");
				$("select[name='mainDrugCd']").focus();		return;
			}
			if($("select[name='mjrMngCd']").val() == ""){
				alert("주요조치는 필수 입력 항목입니다.");
				$("select[name='mjrMngCd']").focus();		return;
			}
			if($("select[name='ursCd']").val() == ""){
				alert("URS은 필수 입력 항목입니다.");
				$("select[name='ursCd']").focus();			return;
			}
			if($("textarea[name='cslCtnt']").val() == ""){
				alert("상담내용은 필수 입력 항목입니다.");
				$("textarea[name='cslCtnt']").focus();		return;
			}
			if($("textarea[name='cslRst']").val() == ""){
				alert("상담결과는 필수 입력 항목입니다.");
				$("textarea[name='cslRst']").focus();		return;
			}

			$.ajax({
				url : '/ajaxCounselAdd.do',
				type : 'POST',
				data : $('#counselForm').serialize(),
				success : function(res){
					if(res.err != "Y"){
						alert("상담 등록 완료");
						counselInfoViewSet(res.rcpNo);
					}else{
						alert(res.MSG);

						if(res.actUrl != "" && res.actUrl != undefined){
							window.location.href = res.actUrl;
						}
					}
				},
				error : function(xhr, status){}
			});
		},
		<%-- 신규 --%>
		counselNew = function(){
			window.location.reload();
		},
		<%-- 복사 --%>
		counselCopy = function(){
			$("input[name='rcpNo']").val("");
			$("input[name='cslId']").val("<c:out value="${cslRcpVO.cslId}" />");
			$("input[name='cslNm']").val("<c:out value="${cslRcpVO.cslNm}" />");

			$("button#delButNo").show();
			$("button#delButYes").hide();
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

						counselNew();
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
		<%-- 엑셀다운로드 --%>
		counselExel = function(){
			if($("input[name='rcpNo']").val() != ""){
				$("form#excelForm").append("<input type='hidden' name='rcpNo' value='" + $("input[name='rcpNo']").val() + "' />");
				$("form#excelForm").attr("action", "/counselExelDownload.do");
				$("form#excelForm").submit();
			}else{
				alert("접수번호를 선택 하세요.");
			}
		},
		<%-- 상담 목록 조회 --%>
		getRcpNo = function(){
			$.ajax({
				url : '/getClsRcpList.do',
				type : 'POST',
				data : {
					pageNo : $("input[name='rcpPageNo']").val(),
					schStrCslDt : $("input[name='rcpSchStrCslDt']").val(),
					schEndCslDt : $("input[name='rcpSchEndCslDt']").val(),
					schMth : $("select[name='rcpSchMth']").val(),
					schGb : $("select[name='rcpSchGb']").val(),
					schNm : $("input[name='rcpSchNm']").val()
				},
				success : function(res){
					$("div[id='layerpopup']").html(res);
					$("div[id='layerpopup']").attr("data-popup", "rcptPopUp");

					layerPopupOpen('rcptPopUp');
				},
				error : function(xhr, status){
					console.log(xhr);
				}
			});
		},
		<%-- 회원 조회 --%>
		mstMbrSearchPopup = function(resFuct){
			$.ajax({
				url : '/ajaxMstMbrList.do',
				type : 'POST',
				data : {
					pageNo : $("input[name='memPageNo']").val(),
					mbrNm : $("input[name='memSchMbrNm']").val(),
					telNo : $("input[name='memSchTelNo']").val()
				},
				success : function(res){
					$("div[id='layerpopup']").html(res);
					$("div[id='layerpopup']").attr("data-popup", "memberPopUp");
					$("input[name='reFunName']").val(resFuct);
					layerPopupOpen('memberPopUp');
				},
				error : function(xhr, status){
					console.log(xhr);
				}
			});
		},
		<%-- 정보 제공자 회원 정보 셋팅 --%>
		ifpMbrSearchPopup = function(obj){
			$("input[name='ifpNm']").val(obj.MBR_NM);
			$("input[name='ifpMbrNo']").val(obj.MBR_NO);
			$("input[name='ifpGendCd']:radio[value='" + obj.GEND_CD + "']").prop("checked", true);
			$("input[name='ifpAge']").val(obj.AGE);
			$("input[name='ifpTelNo1']").val(obj.TEL_NO1);
			$("input[name='ifpTelNo2']").val(obj.TEL_NO2);
			$("input[name='ifpTelNo3']").val(obj.TEL_NO3);
			$("select[name='ifpJobCd']").val(obj.JOB_CD).prop("selected", true);
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
			if($("select[name='ifpAreaCd']").val() == "ZZZ"){
				$("input[name='tgpAreaEtc']").attr("disabled", false);
			}else{
				$("input[name='tgpAreaEtc']").attr("disabled", true);
			}
		},
		<%-- 대상자 회원 정보 셋팅 --%>
		tgpMbrSearchPopup = function(obj){
			$("input[name='tgpNm']").val(obj.MBR_NM);
			$("input[name='tgpMbrNo']").val(obj.MBR_NO);
			$("input[name='tgpGendCd']:radio[value='" + obj.GEND_CD + "']").prop("checked", true);
			$("input[name='tgpAge']").val(obj.AGE);
			$("input[name='tgpTelNo1']").val(obj.TEL_NO1);
			$("input[name='tgpTelNo2']").val(obj.TEL_NO2);
			$("input[name='tgpTelNo3']").val(obj.TEL_NO3);
			$("select[name='tgpJobCd']").val(obj.JOB_CD).prop("selected", true);
			$("input[name='tgpFrgCd']:radio[value='" + obj.FRG_CD + "']").prop("checked", true);
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
						$("input[name='cslDt']").val(formatDate(res.cslRcpInfo.CSL_DT));
						$("input[name='cslFmTm']").val(formatTime(res.cslRcpInfo.CSL_FM_TM));
						$("input[name='cslToTm']").val(formatTime(res.cslRcpInfo.CSL_TO_TM));
						$("#cslTermTm").text(res.cslRcpInfo.CSL_TERM_TM);
						$("select[name='cslSite']").val(res.cslRcpInfo.CSL_SITE).prop("selected", true);
						$("input[name='cslTermTm']").val(res.cslRcpInfo.CSL_TERM_TM);
						$("input[name='cslId']").val(res.cslRcpInfo.CSL_ID);
						$("input[name='cslNm']").val(res.cslRcpInfo.CSL_NM);
						$("input[name='ifpGbCd']:radio[value='" + res.cslRcpInfo.IFP_GB_CD + "']").prop("checked", true);
						if(res.cslRcpInfo.IFP_GB_CD == "ZZ"){
							$("input[name='ifpGbEtc']").attr("disabled", false);
							$("input[name='ifpGbEtc']").val(res.cslRcpInfo.IFP_GB_ETC);
						}else{
							$("input[name='ifpGbEtc']").attr("disabled", true);
							$("input[name='ifpGbEtc']").val("");
						}
						$("input[name='ifpNm']").val(res.cslRcpInfo.IFP_NM);
						$("input[name='ifpMbrNo']").val(res.cslRcpInfo.IFP_MBR_NO);
						$("input[name='ifpGendCd']:radio[value='" + res.cslRcpInfo.IFP_GEND_CD + "']").prop("checked", true);
						$("input[name='ifpAge']").val(res.cslRcpInfo.IFP_AGE);
						$("input[name='ifpTelNo1']").val(res.cslRcpInfo.IFP_TEL_NO1);
						$("input[name='ifpTelNo2']").val(res.cslRcpInfo.IFP_TEL_NO2);
						$("input[name='ifpTelNo3']").val(res.cslRcpInfo.IFP_TEL_NO3);
						$("select[name='ifpJobCd']").val(res.cslRcpInfo.IFP_JOB_CD).prop("selected", true);
						$("select[name='ifpAreaCd']").val(res.cslRcpInfo.IFP_AREA_CD).prop("selected", true);
						if(res.cslRcpInfo.IFP_AREA_CD == "ZZZ"){
							$("input[name='ifpAreaEtc']").attr("disabled", false);
							$("input[name='ifpAreaEtc']").val(res.cslRcpInfo.IFP_AREA_ETC);
						}
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
						if(res.cslRcpInfo.TGP_AREA_CD == "ZZZ"){
							$("input[name='tgpAreaEtc']").attr("disabled", false);
							$("input[name='tgpAreaEtc']").val(res.cslRcpInfo.TGP_AREA_ETC);
						}
						$("select[name='ifPathCd']").val(res.cslRcpInfo.IF_PATH_CD).prop("selected", true);
						$("select[name='pbmKndCd']").val(res.cslRcpInfo.PBM_KND_CD).prop("selected", true);
						$("select[name='cslTpCd']").val(res.cslRcpInfo.CSL_TP_CD).prop("selected", true);
						$("select[name='fstDrugCd']").val(res.cslRcpInfo.FST_DRUG_CD).prop("selected", true);
						$("input[name='fstDrug']").val(res.cslRcpInfo.FST_DRUG);
						$("select[name='mainDrugCd']").val(res.cslRcpInfo.MAIN_DRUG_CD).prop("selected", true);
						$("input[name='mainDrug']").val(res.cslRcpInfo.MAIN_DRUG);
						$("select[name='mjrMngCd']").val(res.cslRcpInfo.MJR_MNG_CD).prop("selected", true);
						$("input[name='astSco']").val(res.cslRcpInfo.AST_SCO);
						$("input[name='rskSco']").val(res.cslRcpInfo.RSK_SCO);
						$("select[name='rskaTpCd']").val(res.cslRcpInfo.RSKA_TP_CD).prop("selected", true);
						$("select[name='rskbTpCd']").val(res.cslRcpInfo.RSKB_TP_CD).prop("selected", true);
						$("select[name='rskcTpCd']").val(res.cslRcpInfo.RSKC_TP_CD).prop("selected", true);
						$("input[name='cslEmer']").val(res.cslRcpInfo.CSL_EMER);
						$("select[name='ursCd']").val(res.cslRcpInfo.URS_CD).prop("selected", true);
						$("textarea[name='cslCtnt']").val(res.cslRcpInfo.CSL_CTNT);
						$("textarea[name='cslRst']").val(res.cslRcpInfo.CSL_RST);
						$("select[name='ursCd']").val(res.cslRcpInfo.URS_CD).prop("selected", true);

						layerPopupClose('rcptPopUp');
						changRating();

						$("button#copyButNo").hide();
						$("button#copyButYes").show();
						$("button#delButNo").hide();
						$("button#delButYes").show();
						$("button#excelButNo").hide();
						$("button#excelButYes").show();
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
		areaPopup = function(tagName){
			layerPopupOpen('counwrite');
			$("input[name='viewTagName']").val(tagName);
			$("textarea[name='viewCslCtnt']").val($("textarea[name='" + tagName + "']").val());
		},
		<%-- 상담내용상세보기 확인 --%>
		setCslCtnt = function(){
			$("textarea[name='" + $("input[name='viewTagName']").val() + "']").val($("textarea[name='viewCslCtnt']").val());
			layerPopupClose('counwrite');
		},
		<%-- 위기분류척도 점수 계산--%>
		changRating = function(){
			var ratingNum = 0;

			ratingNum += Number($("select[name='rskaTpCd'] option:selected").attr("rating"));
			ratingNum += Number($("select[name='rskbTpCd'] option:selected").attr("rating"));
			ratingNum += Number($("select[name='rskcTpCd'] option:selected").attr("rating"));

			$("#ratingNum").text(ratingNum);
			$("input[name='rskSco']").val(ratingNum);
		},
		<%-- 상담 소요시간 계산 --%>
		changTime = function(tagName, val){
			$("input[name='" + tagName + "']").val(val);

			if($("input[name='cslFmTm']").val() != "" && $("input[name='cslToTm']").val() != ""){
				var termTm = needTime($("input[name='cslFmTm']").val(), $("input[name='cslToTm']").val());

				if(termTm > 0){
					$("span#cslTermTm").text(termTm);
					$("input[name='cslTermTm']").val(termTm);
				}
			}
		}
	});
</script>
<!-- 페이지 타이틀 -->
<div class="tit-area">
	<h1><i class="el-icon-s-order" style="color: rgb(0, 108, 185);"></i> 중독예방상담</h1>
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
								<form:input path="cslRcpInfo.cslFmTm" value="09:00" cssClass="el-input__inner timepicker" format="HH:mm" placeholder="시작" maxlength="4" style="width: 96px;" />
							</div>
							<span>~</span>
							<div class="tm-in">
								<i class="el-input__icon el-icon-time"></i>
								<form:input path="cslRcpInfo.cslToTm" value="09:00" cssClass="el-input__inner timepicker" format="HH:mm" placeholder="종료" maxlength="4" style="width: 96px;" />
							</div>
							<div class="t-min">
								<span class="readonly" id="cslTermTm">0</span> 분 소요
								<form:input path="cslRcpInfo.cslTermTm" hidden="true" />
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
						<span class="tac"><form:input path="cslRcpInfo.cslId" cssClass="el-input__inner" readonly="true" style="width:100px;" /></span>
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
					<th><span class="required">*</span> 정보제공자/본인여부</th>
					<td>
<c:if test="${ifpGbList ne null and ifpGbList ne ''}">
	<c:forEach var="result" items="${ifpGbList}" varStatus="status">
						<span class="ck-bx">
							<input type="radio" class="el-radio__original" name="ifpGbCd" id="ifpGbCd-<c:out value='${status.count}'/>" value="${result.CD_ID}" onchange="javaScript:inputDisabledChang(this, 'ifpGbEtc');"<c:if test="${result.CD_ID eq cslRcpInfo.ifpGbCd}"> checked</c:if> />
							<label for="ifpGbCd-<c:out value='${status.count}'/>"><span class="el-radio__input"><span class="el-radio__inner"></span></span><c:out value="${result.CD_NM}" /></label>
						</span>
	</c:forEach>
</c:if>
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
										<form:input path="cslRcpInfo.ifpMbrNo" cssClass="el-input__inner" placeholder="회원번호" readonly="true" style="width: 140px;" />
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
										<form:input path="cslRcpInfo.tgpMbrNo" cssClass="el-input__inner" placeholder="회원번호" readonly="true" style="width: 140px;" />
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
							<div style="margin-top:5px"><form:input path="cslRcpInfo.fstDrug" cssClass="el-input__inner" placeholder="최초사용약물 입력" style="width: 100%;" /></div>
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
							<div style="margin-top:5px"><form:input path="cslRcpInfo.mainDrug" cssClass="el-input__inner" placeholder="주요사용약물 입력" style="width: 100%;" /></div>
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
						<td colspan="5"><form:input path="cslRcpInfo.astSco" cssClass="el-input__inner" maxlength="4" style="width:75px" /></td>
					</tr>
				</tbody>
			</table>
			<table class="w-auto wr-form">
				<input type="hidden" name="rskSco" value="<c:out value="${cslRcpInfo.rskSco}"/>" />
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
						<th class="txt-center"><span class="el-tag-danger" id="ratingNum"><c:out value="${cslRcpInfo.rskSco}"/></span></th>
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
						<td style="width: 543px;"><form:input path="cslRcpInfo.cslEmer" cssClass="el-input__inner" placeholder="위기상담" style="width: 100%;"/></td>
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
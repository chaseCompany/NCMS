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
				success : function(data){
					if(data.err != "Y"){
						alert("등록 완료");
					}else{
						alert(data.MSG);

						if(data.actUrl != "" && data.actUrl != undefined){
							window.location.href = data.actUrl;
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
				success : function(data){
					if(data.err != "Y"){
						alert("삭제 완료");
					}else{
						alert(data.MSG);

						if(data.actUrl != "" && data.actUrl != undefined){
							window.location.href = data.actUrl;
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
				data : $('#counselForm').serialize(),
				success : function(res){
					if(res.totalCount > 0){
						alert("조회 완료");
						console.log(res.resultList);
						counselInfoViewSet(res.resultList[0].RCP_NO);
					}else{
						alert("목록 없음");
					}
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
					if(res.totalCount > 0){
						console.log(res.resultList);

						tagMbrInfo = res.resultList[0];
						(new Function(resFuct + "('" + res.resultList[0].MBR_NO + "');"))();
					}else{
						tagMbrInfo = "";
						console.log("회원 정보 없음");
					}
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
					}else{
						console.log("상세내용 조회 오류");
					}
				},
				error : function(xhr, status){
					console.log(xhr);
				}
			});
		}
	});
</script>
<br/>
<form name="counselForm" id="counselForm">
일반상담			<a href="javaScript:counselSave();">저장</a> <a href="javaScript:counselNew();">신규</a> <a href="javaScript:counselCopy();">복사</a> <a href="javaScript:counselDel();">삭제</a> <a href="javaScript:counselExel();">엑셀다운로드</a><br/>
접수번호			<form:input path="cslRcpInfo.rcpNo" cssClass="txt" readonly="true" placeholder="저장시 자동 생성" /><a href="javaScript:getRcpNo();">조회</a><br/>
*상담일시			<form:input path="cslRcpInfo.cslDt" cssClass="txt" format="yyyy-MM-dd" placeholder="날짜 선택" maxlength="8" />
				<form:input path="cslRcpInfo.cslFmTm" cssClass="txt" format="HH:mm" placeholder="시작" maxlength="4" /> ~ <form:input path="cslRcpInfo.cslToTm" cssClass="txt" format="HH:mm" placeholder="종료" maxlength="4" />
				<span id="cslTermTm">0</span>분 소요<br/>
상담자			<form:input path="cslRcpInfo.cslId" cssClass="txt" readonly="true" /><form:input path="cslRcpInfo.cslNm" cssClass="txt" readonly="true" /><br/>
*정보제공자/본인여부	<c:forEach var="result" items="${ifpGbList}" varStatus="status">
					<input type="radio" name="ifpGbCd" value="${result.CD_ID}"<c:if test="${result.CD_ID eq cslRcpInfo.ifpGbCd}"> checked</c:if> /><c:out value="${result.CD_NM}" />
				</c:forEach>
				<form:input path="cslRcpInfo.ifpGbEtc" cssClass="txt" placeholder="기타 선택시 입력 가능" /><br/>
정보 제공자<br/>
*성명				<form:input path="cslRcpInfo.ifpNm" cssClass="txt" placeholder="정보제공자 성명" /><a href="javaScript:mstMbrSearchPopup('ifpMbrSearchPopup');">조회</a>
회원번호			<form:input path="cslRcpInfo.ifpMbrNo" cssClass="txt" placeholder="회원번호" readonly="true"/>
*성별				<input type="radio" name="ifpGendCd" value="M"<c:if test="${cslRcpInfo.ifpGendCd eq 'M'}"> checked</c:if> />남
				<input type="radio" name="ifpGendCd" value="F"<c:if test="${cslRcpInfo.ifpGendCd eq 'F'}"> checked</c:if> />여
*연령				<form:input path="cslRcpInfo.ifpAge" cssClass="txt" placeholder="연령" maxlength="3" /></br>
*연락처			<form:input path="cslRcpInfo.ifpTelNo1" cssClass="txt" maxlength="4" />-<form:input path="cslRcpInfo.ifpTelNo2" cssClass="txt" maxlength="4" />-<form:input path="cslRcpInfo.ifpTelNo3" cssClass="txt" maxlength="4" />
*직업				<select name="ifpJobCd">
					<option value="" />선택
				<c:forEach var="result" items="${jobList}" varStatus="status">
					<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
				</c:forEach>
				</select>
*지역				<select name="ifpAreaCd">
					<option value="" />선택
				<c:forEach var="result" items="${areaList}" varStatus="status">
					<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
				</c:forEach>
				</select>
				<form:input path="cslRcpInfo.ifpAreaEtc" cssClass="txt" placeholder="기타 선택시 입력 가능" /><br/>
<a href="javaScript:ifpCopy();">▶▶ 정보제공자와 동일</a><br/>
대상자<br/>
*성명				<form:input path="cslRcpInfo.tgpNm" cssClass="txt" placeholder="대상자 성명" /><a href="javaScript:mstMbrSearchPopup('tgpMbrSearchPopup');">조회</a>
회원번호			<form:input path="cslRcpInfo.tgpMbrNo" cssClass="txt" placeholder="회원번호" readonly="true" />
*성별				<input type="radio" name="tgpGendCd" value="M"<c:if test="${cslRcpInfo.tgpGendCd eq 'M'}"> checked</c:if> />남
				<input type="radio" name="tgpGendCd" value="F"<c:if test="${cslRcpInfo.tgpGendCd eq 'F'}"> checked</c:if> />여
*연령				<form:input path="cslRcpInfo.tgpAge" cssClass="txt" maxlength="3" placeholder="연령" /><br/>
*연락처			<form:input path="cslRcpInfo.tgpTelNo1" cssClass="txt" maxlength="4" />-<form:input path="cslRcpInfo.tgpTelNo2" cssClass="txt" maxlength="4" />-<form:input path="cslRcpInfo.tgpTelNo3" cssClass="txt" maxlength="4" />
*직업				<select name="tgpJobCd">
					<option value="" />선택
				<c:forEach var="result" items="${jobList}" varStatus="status">
					<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
				</c:forEach>
				</select>
*내/외국인			<input type="radio" name="tgpFrgCd" value="LO"<c:if test="${cslRcpInfo.tgpFrgCd eq 'LO'}"> checked</c:if> />내국인
				<input type="radio" name="tgpFrgCd" value="FO"<c:if test="${cslRcpInfo.tgpFrgCd eq 'FO'}"> checked</c:if> />외국인<br/>
*지역				<select name="tgpAreaCd">
					<option value="" />선택
				<c:forEach var="result" items="${areaList}" varStatus="status">
					<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
				</c:forEach>
				</select>
				<form:input path="cslRcpInfo.tgpAreaEtc" cssClass="txt" placeholder="기타 선택시 입력 가능" /><br/>
*정보취득경로		<select name="ifPathCd">
					<option value="" />선택
				<c:forEach var="result" items="${pathList}" varStatus="status">
					<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
				</c:forEach>
				</select>
*주호소문제			<select name="pbmKndCd">
					<option value="" />선택
				<c:forEach var="result" items="${kndList}" varStatus="status">
					<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
				</c:forEach>
				</select>
*상담유형			<select name="cslTpCd">
					<option value="" />선택
				<c:forEach var="result" items="${tpList}" varStatus="status">
					<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
				</c:forEach>
				</select><br/>
*최초사용약물		<select name="fstDrugCd">
					<option value="" />선택
				<c:forEach var="result" items="${fstDrugList}" varStatus="status">
					<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
				</c:forEach>
				</select><br/>
*주요사용약물		<select name="mainDrugCd">
					<option value="" />선택
				<c:forEach var="result" items="${mainDrugList}" varStatus="status">
					<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
				</c:forEach>
				</select><br/>
				<form:input path="cslRcpInfo.mainDrug" cssClass="txt" placeholder="주요사용약물 입력" /><br/>
*주요조치			<select name="mjrMngCd">
					<option value="" />선택
				<c:forEach var="result" items="${mjrMngList}" varStatus="status">
					<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
				</c:forEach>
				</select><br/>
ASSIST 점수		<form:input path="cslRcpInfo.astSco" cssClass="txt" maxlength="4" /><br/>
*위기분류척도 점수	<form:input path="cslRcpInfo.rskSco" cssClass="txt" /><br/>
Rating A: 위험성	<select name="rskaTpCd">
				<c:forEach var="result" items="${rskaTpList}" varStatus="status">
					<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
				</c:forEach>
				</select><br/>
Rating B: 지지체계	<select name="rskbTpCd">
				<c:forEach var="result" items="${rskbTpList}" varStatus="status">
					<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
				</c:forEach>
				</select><br/>
Rating C: 협조능력	<select name="rskcTpCd">
				<c:forEach var="result" items="${rskcTpList}" varStatus="status">
					<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
				</c:forEach>
				</select><br/>
상담내용 <a href="javaScript:ctntPopup();">팝업</a>
				<textarea name="cslCtnt" placeholder="상담 내용"></textarea>
</form>
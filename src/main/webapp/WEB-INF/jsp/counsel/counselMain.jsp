<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javaScript" language="javascript" defer="defer">
	$(document).ready(function(){
		counselSave = function(){
			var str = "crpNo:" + $("input[name='crpNo']").val() + ", ";
				str += "cslDt:" + $("input[name='cslDt']").val() + ", ";
				str += "cslFmTm:" + $("input[name='cslFmTm']").val() + ", ";
				str += "cslToTm:" + $("input[name='cslToTm']").val() + ", ";
				str += "ifpGbCd:" + $("input[name='ifpGbCd']").val() + ", ";
				str += "ifpGbEtc:" + $("input[name='ifpGbEtc']").val() + ", ";
				str += "ifpNm:" + $("input[name='ifpNm']").val() + ", ";
				str += "ifpMbrNo:" + $("input[name='ifpMbrNo']").val() + ", ";
				str += "ifpGendCd:" + $("input[name='ifpGendCd']").val() + ", ";
				str += "ifpAge:" + $("input[name='ifpAge']").val() + ", ";
				str += "ifpTelNo1:" + $("input[name='ifpTelNo1']").val() + ", ";
				str += "ifpTelNo2:" + $("input[name='ifpTelNo2']").val() + ", ";
				str += "ifpTelNo3:" + $("input[name='ifpTelNo3']").val() + ", ";
				str += "ifpJobCd:" + $("select[name='ifpJobCd']").val() + ", ";
				str += "ifpAreaCd:" + $("select[name='ifpAreaCd']").val() + ", ";
				str += "ifpAreaEtc:" + $("input[name='ifpAreaEtc']").val() + ", ";
				str += "tgpNm:" + $("input[name='tgpNm']").val() + ", ";
				str += "tgpMbrNo:" + $("input[name='tgpMbrNo']").val() + ", ";
				str += "tgpGendCd:" + $("input[name='tgpGendCd']").val() + ", ";
				str += "tgpAge:" + $("input[name='tgpAge']").val() + ", ";
				str += "tgpTelNo1:" + $("input[name='tgpTelNo1']").val() + ", ";
				str += "tgpTelNo2:" + $("input[name='tgpTelNo2']").val() + ", ";
				str += "tgpTelNo3:" + $("input[name='tgpTelNo3']").val() + ", ";
				str += "tgpJobCd:" + $("input[name='tgpJobCd']").val() + ", ";
				str += "tgpFrgCd:" + $("input[name='tgpFrgCd']").val() + ", ";
				str += "tgpAreaCd:" + $("input[name='tgpAreaCd']").val() + ", ";
				str += "tgpAreaEtc:" + $("input[name='tgpAreaEtc']").val() + ", ";
				str += "pbmKndCd:" + $("input[name='pbmKndCd']").val() + ", ";
				str += "cslTpCd:" + $("input[name='cslTpCd']").val() + ", ";
				str += "fstDrugCd:" + $("input[name='fstDrugCd']").val() + ", ";
				str += "mainDrugCd:" + $("input[name='mainDrugCd']").val() + ", ";
				str += "mainDrug:" + $("input[name='mainDrug']").val() + ", ";
				str += "astSco:" + $("input[name='astSco']").val() + ", ";
				str += "rskSco:" + $("input[name='rskSco']").val() + ", ";
				str += "rskaTpCd:" + $("input[name='rskaTpCd']").val() + ", ";
				str += "rskbTpCd:" + $("input[name='rskbTpCd']").val() + ", ";
				str += "rskcTpCd:" + $("input[name='rskcTpCd']").val() + ", ";
				str += "cslCtnt:" + $("input[name='cslCtnt']").val() + ", ";
			console.log(str);
			console.log("저장");
		},
		counselNew = function(){
			console.log("신규")
		},
		counselCopy = function(){
			console.log("복사");
		},
		counselDel = function(){
			console.log("삭제");
		},
		counselExel = function(){
			console.log("엑셀다운로드");
		},
		getRcpNo = function(){
			console.log("상담조회");
		},
		ifpMbrSearchPopup = function(){
			console.log("정보제공자 조회");
		},
		ifpCopy = function(){
			console.log("정보제공자 복사");
		},
		tgpMbrSearchPopup = function(){
			console.log("대상자 조회");
		}
	});
</script>
<br/>
일반상담			<a href="javaScript:counselSave();">저장</a> <a href="javaScript:counselNew();">신규</a> <a href="javaScript:counselCopy();">복사</a> <a href="javaScript:counselDel();">삭제</a> <a href="javaScript:counselExel();">엑셀다운로드</a><br/>
접수번호			<form:input path="cslRcpInfo.rcpNo" cssClass="txt" readonly="true" placeholder="저장시 자동 생성" /><a href="javaScript:getRcpNo();">조회</a><br/>
상담일시			<form:input path="cslRcpInfo.cslDt" cssClass="txt" format="yyyy-MM-dd" placeholder="날짜 선택" maxlength="8" />
				<form:input path="cslRcpInfo.cslFmTm" cssClass="txt" format="HH:mm" placeholder="시작" maxlength="4" /> ~ <form:input path="cslRcpInfo.cslToTm" cssClass="txt" format="HH:mm" placeholder="종료" maxlength="4" />
				<span id="cslTermTm">0</span>분 소요<br/>
상담자			<form:input path="cslRcpInfo.cslId" cssClass="txt" readonly="true" /><form:input path="cslRcpInfo.cslNm" cssClass="txt" readonly="true" /><br/>
정보제공자/본인여부	<c:forEach var="result" items="${ifpGbList}" varStatus="status">
					<input type="radio" name="ifpGbCd" value="${result.CD_ID}"<c:if test="${result.CD_ID == cslRcpInfo.ifpGbCd}"> checked</c:if> /><c:out value="${result.CD_NM}" />
				</c:forEach>
				<form:input path="cslRcpInfo.ifpGbEtc" cssClass="txt" placeholder="기타 선택시 입력 가능" /><br/>
정보 제공자<br/>
성명				<form:input path="cslRcpInfo.ifpNm" cssClass="txt" placeholder="정보제공자 성명" /><a href="javaScript:ifpMbrSearchPopup();">조회</a>
회원번호			<form:input path="cslRcpInfo.ifpMbrNo" cssClass="txt" placeholder="회원번호" readonly="true"/>
성별				<input type="radio" name="ifpGendCd" value="M"<c:if test="${cslRcpInfo.ifpGendCd == 'M'}"> checked</c:if> />남
				<input type="radio" name="ifpGendCd" value="F"<c:if test="${cslRcpInfo.ifpGendCd == 'F'}"> checked</c:if> />여
연령				<form:input path="cslRcpInfo.ifpAge" cssClass="txt" placeholder="연령" maxlength="3" /></br>
연락처			<form:input path="cslRcpInfo.ifpTelNo1" cssClass="txt" maxlength="4" />-<form:input path="cslRcpInfo.ifpTelNo2" cssClass="txt" maxlength="4" />-<form:input path="cslRcpInfo.ifpTelNo3" cssClass="txt" maxlength="4" />
직업				<select name="ifpJobCd">
					<option value="" />선택
				<c:forEach var="result" items="${jobList}" varStatus="status">
					<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
				</c:forEach>
				</select>
지역				<select name="ifpAreaCd">
					<option value="" />선택
				<c:forEach var="result" items="${areaList}" varStatus="status">
					<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
				</c:forEach>
				</select>
				<form:input path="cslRcpInfo.ifpAreaEtc" cssClass="txt" placeholder="기타 선택시 입력 가능" /><br/>
<a href="javaScript:ifpCopy();">▶▶ 정보제공자와 동일</a><br/>
대상자<br/>
성명				<form:input path="cslRcpInfo.tgpNm" cssClass="txt" placeholder="대상자 성명" /><a href="javaScript:tgpMbrSearchPopup();">조회</a>
회원번호			<form:input path="cslRcpInfo.tgpMbrNo" cssClass="txt" placeholder="회원번호" readonly="true" />
성별				<input type="radio" name="tgpGendCd" value="M"<c:if test="${cslRcpInfo.tgpGendCd == 'M'}"> checked</c:if> />남
				<input type="radio" name="tgpGendCd" value="F"<c:if test="${cslRcpInfo.tgpGendCd == 'F'}"> checked</c:if> />여
연령				<form:input path="cslRcpInfo.tgpAge" cssClass="txt" maxlength="3" placeholder="연령" /><br/>
연락처			<form:input path="cslRcpInfo.tgpTelNo1" cssClass="txt" maxlength="4" />-<form:input path="cslRcpInfo.tgpTelNo2" cssClass="txt" maxlength="4" />-<form:input path="cslRcpInfo.tgpTelNo3" cssClass="txt" maxlength="4" />
직업				<select name="tgpJobCd">
					<option value="" />선택
				<c:forEach var="result" items="${jobList}" varStatus="status">
					<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
				</c:forEach>
				</select>
내/외국인			<input type="radio" name="tgpFrgCd" value="LO"<c:if test="${cslRcpInfo.tgpFrgCd == 'LO'}"> checked</c:if> />내국인
				<input type="radio" name="tgpFrgCd" value="FO"<c:if test="${cslRcpInfo.tgpFrgCd == 'FO'}"> checked</c:if> />외국인<br/>
지역				<select name="tgpAreaCd">
					<option value="" />선택
				<c:forEach var="result" items="${areaList}" varStatus="status">
					<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
				</c:forEach>
				</select>
				<form:input path="cslRcpInfo.tgpAreaEtc" cssClass="txt" placeholder="기타 선택시 입력 가능" /><br/>
정보취득경로		<select name="ifPathCd">
					<option value="" />선택
				<c:forEach var="result" items="${pathList}" varStatus="status">
					<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
				</c:forEach>
				</select>
주호소문제			<select name="pbmKndCd">
					<option value="" />선택
				<c:forEach var="result" items="${kndList}" varStatus="status">
					<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
				</c:forEach>
				</select>
상담유형			<select name="cslTpCd">
					<option value="" />선택
				<c:forEach var="result" items="${tpList}" varStatus="status">
					<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
				</c:forEach>
				</select><br/>
최초사용약물		<select name="fstDrugCd">
					<option value="" />선택
				<c:forEach var="result" items="${fstDrugList}" varStatus="status">
					<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
				</c:forEach>
				</select><br/>
주요사용약물		<select name="mainDrugCd">
					<option value="" />선택
				<c:forEach var="result" items="${mainDrugList}" varStatus="status">
					<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
				</c:forEach>
				</select><br/>
				<form:input path="cslRcpInfo.mainDrug" cssClass="txt" placeholder="주요사용약물 입력" /><br/>
주요조치			<select name="mjrMngCd">
					<option value="" />선택
				<c:forEach var="result" items="${mjrMngList}" varStatus="status">
					<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
				</c:forEach>
				</select><br/>
ASSIST 점수		<form:input path="cslRcpInfo.astSco" cssClass="txt" maxlength="4" /><br/>
위기분류척도 점수	<form:input path="cslRcpInfo.rskSco" cssClass="txt" /><br/>
Rating A: 위험성	<select name="rskaTpCd">
					<option value="" />선택
				<c:forEach var="result" items="${rskaTpList}" varStatus="status">
					<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
				</c:forEach>
				</select><br/>
Rating B: 지지체계	<select name="rskbTpCd">
					<option value="" />선택
				<c:forEach var="result" items="${rskbTpList}" varStatus="status">
					<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
				</c:forEach>
				</select><br/>
Rating C: 협조능력	<select name="rskcTpCd">
					<option value="" />선택
				<c:forEach var="result" items="${rskcTpList}" varStatus="status">
					<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
				</c:forEach>
				</select><br/>
상담내용 <a href="javaScript:ctntPopup();">팝업</a>
				<textarea name="cslCtnt" placeholder="상담 내용"></textarea>
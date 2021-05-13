<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String loginUserNm = StringUtils.defaultIfEmpty((String)request.getAttribute("LoginUserNm"), "");
%>
<c:set var="loginUserNm" value="<%=loginUserNm%>" />
<script type="text/javascript" language="javascript" charset="utf-8" src="<c:url value='/js/jquery.form.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">
	$(document).ready(function(){
		$("input[name='cslDt']").datepicker('setDate', 'today');
		<%-- 초기화 --%>
		pageRest = function(){
			window.location.reload();
		},
		<%-- 회원 조회 --%>
		mstMbrSearchPopup = function(resFuct){
			$.ajax({
				url : '/ajaxMstMbrList.do',
				type : 'POST',
				data : {
					listType : "MEDIC",
					pageNo : $("input[name='memPageNo']").val(),
					mbrNm : $("input[name='memSchMbrNm']").val(),
					telNo : $("input[name='memSchTelNo']").val()
				},
				success : function(res){
					$("div[id='layerpopup']").html(res);
					$("div[id='layerpopup']").attr("data-popup", "memberPopUp");
					$("input[name='reFunName']").val("setMemInfo");
					layerPopupOpen('memberPopUp');
				},
				error : function(xhr, status){
					console.log(xhr);
				}
			});
		},
		<%-- 회원정보 셋팅 --%>
		setMemInfo = function(obj){
			$("input[name='mbrNo']").val(obj.MBR_NO);
			$("input[name='mbrNm']").val(obj.MBR_NM);
			$("input[name='gendNm']").val(obj.GEND_NM);
			$("input[name='ageNm']").val(obj.AGE_NM);
			$("input[name='regDt']").val(formatDate(obj.REG_DT));
			$("input[name='medicCareNm']").val(obj.MEDIC_CARE_NM);
			$("input[name='mngUsrId']").val(obj.MNG_USR_NM);

			getCslCureList(obj.MBR_NO);

			$("button#excelButNo").hide();
			$("button#excelButYes").show();
		},
		<%-- 심리치유 상담 이력 조회 --%>
		getCslCureList = function(tagMbrNo){
			$.ajax({
				url : '/getClsCureList.do',
				type : 'POST',
				data : {
					mbrNo : tagMbrNo
				},
				success : function(res){
					if(res.err != "Y"){
						$("div#idvList").html("");

						if(res.clsCureList.length > 0){
							var inHtml = "<table id='idvTableList'>"
									   + "	<colgroup>"
									   + "		<col style='width:46px'>"
									   + "		<col style='width:150px'>"
									   + "		<col style='width:100px'>"
									   + "		<col style='width:120px'>"
									   + "		<col style='width:80px'>"
									   + "		<col style='width:400px'>"
									   + "		<col style='width:690px'>"
									   + "		<col style='width:150px'>"
									   + "		<col>"
									   + "	</colgroup>"
									   + "	<tbody>";

							$(res.clsCureList).each(function(idx, obj){
								inHtml = inHtml
									   + "<tr id=" + idx + ">"
									   + "	<td><div class='cell'>" + (idx + 1) + "</div></td>"
									   + "	<td><a href='javaScript:viewIdvRow(\"" + obj.CSL_NO + "\");' class='row_link'>" + obj.CSL_NO + "</a></td>"
									   + "	<td><div class='cell'>" + formatDate(obj.CSL_DT) + "</div></td>"
									   + "	<td><div class='cell'>" + obj.CSL_FM_TM + " ~ " + obj.CSL_TO_TM + "</div></td>"
									   + "	<td><div class='cell'>" + obj.CSL_TERM_TM + "</div></td>"
									   + "	<td><div class='cell'>" + obj.CSL_SBJ + "</div></td>"
									   + "	<td><div class='cell'>" + obj.CSL_TGT + "</div></td>"
									   + "	<td><div class='cell'>" + obj.CSL_ID + "</div></td>"
									   + "	<td><div class='cell'>"
									   + "		<button type='button' onclick='javaScript:idvDel(\"" + obj.CSL_NO + "\", \"" + idx + "\");' class='el-button el-button--danger el-button--mini is-plain' style='margin-left: 1px; padding: 4px 9px;'> <span>삭제</span> </button>"
									   + "	</div></td>"
									   + "</tr>";
							});

							inHtml = inHtml
								   + "	</tbody>"
								   + "</table>";

							$("div#idvList").html(inHtml);
						}else{
							$("div#idvList").html("<div class='no-data'>조회된 데이터가 없습니다.</div>");
						}

						newIdv();
					}else{
						console.log("ERR")
					}
				},
				error : function(xhr, status){}
			});
		},
		<%-- 심리치유 상담내용 상세 조회 --%>
		viewIdvRow = function(tagCslNo){
			$.ajax({
				url : '/ajaxClsCureInfo.do',
				type : 'POST',
				data : {
					cslNo : tagCslNo
				},
				success : function(res){
					if(res.err != "Y"){
						var dataInfo = res.clsCureInfo;
						$("input[name='cslNo']").val(dataInfo.CSL_NO);
						$("input[name='cslNm']").val(dataInfo.CSL_NM);
						$("input[name='cslDt']").val(formatDate(dataInfo.CSL_DT));
						$("input[name='cslFmTm']").val(dataInfo.CSL_FM_TM);
						$("input[name='cslToTm']").val(dataInfo.CSL_TO_TM);
						$("input[name='cslTermTm']").val(dataInfo.CSL_TERM_TM);
						$("input[name='cslTgtCd']:radio[value='" + dataInfo.CSL_TGT_CD + "']").prop("checked", true);
						$("input[name='cslClsCd']:radio[value='" + dataInfo.CSL_CLS_CD + "']").prop("checked", true);
						$("input[name='cslTpCd']:radio[value='" + dataInfo.CSL_TP_CD + "']").prop("checked", true);
						$("input[name='cslSbj']").val(dataInfo.CSL_SBJ);
						$("input[name='cslTgt']").val(dataInfo.CSL_TGT);
						$("select[name='rskaTpCd']").val(dataInfo.RSKA_TP_CD).prop("selected", true);
						$("select[name='rskbTpCd']").val(dataInfo.RSKB_TP_CD).prop("selected", true);
						$("select[name='rskcTpCd']").val(dataInfo.RSKC_TP_CD).prop("selected", true);
						$("input[name='rskSco']").val(dataInfo.RSK_SCO);
						$("input[name='crisisCounsel']").val(dataInfo.CRISIS_COUNSEL);
						$("select[name='ursCd']").val(dataInfo.URS_CD).prop("selected", true);
						$("select[name='cureCd']").val(dataInfo.CURE_CD).prop("selected", true);
						$("select[name='drugUseCd']").val(dataInfo.DRUG_USE_CD).prop("selected", true);
						$("select[name='oldActCd']").val(dataInfo.OLD_ACT_CD).prop("selected", true);
						$("select[name='actCd']").val(dataInfo.ACT_CD).prop("selected", true);
						$("select[name='aroundSuicideCd']").val(dataInfo.AROUND_SUICIDE_CD).prop("selected", true);
						$("select[name='suicidePlanCd']").val(dataInfo.SUICIDE_PLAN_CD).prop("selected", true);
						$("select[name='oldActWayCd']").val(dataInfo.OLD_ACT_WAY_CD).prop("selected", true);
						$("select[name='actWayCd']").val(dataInfo.ACT_WAY_CD).prop("selected", true);
						$("textarea[name='cslCtnt']").val(dataInfo.CSL_CTNT);
						$("textarea[name='cslRst']").val(dataInfo.CSL_RST);
						$("input[name='nxtCslDt']").val(formatDate(dataInfo.NXT_CSL_DT));
						$("input[name='nxtCslTm']").val(dataInfo.NXT_CSL_TM);
						$("textarea[name='nxtCslCtnt']").val(dataInfo.NXT_CSL_CTNT);

						if(dataInfo.fileList != undefined && dataInfo.fileList != ''){
							for(let i=0 ; i<dataInfo.fileList.length ; i++){
								$("div#fileName").html("<a href='javaScript:downloadFile(\"" + dataInfo.fileList[i].FILE_ID + "\", \"" + dataInfo.fileList[i].FILE_SEQ + "\");'>" + dataInfo.fileList[i].ORIGNL_FILE_NM + "</a>"
													+  "&nbsp;&nbsp;<a href='javaScript:deleteFile(\"fileName\");'>삭제</a>"
													  );
							}
						}else{
							$("div#fileName").text("");
						}
						$("input[name='fileNameFlag']").val("N");

						var termTm = needTime($("input[name='cslFmTm']").val(), $("input[name='cslToTm']").val());
						if(termTm > 0){
							$("span#cslTermTm").text(termTm);
							$("input[name='cslTermTm']").val(termTm);
						}

						changRating();
					}else{
						console.log("ERR")
					}
				},
				error : function(xhr, status){}
			});
		},
		<%-- 심리치유 상담내용 삭제 --%>
		idvDel = function(tagCslNo, idx){
			$.ajax({
				url : '/ajaxClsCureDel.do',
				type : 'POST',
				data : {
					cslNo : tagCslNo
				},
				success : function(res){
					if(res.err != "Y"){
						$("#idvTableList tr").each(function(){
							if($(this).attr("id") == idx){
								$(this).remove();
							}
						});

						if($("#idvTableList tr").length <= 0){
							$("div#idvList").html("<div class='no-data'>조회된 데이터가 없습니다.</div>");
						}

						newIdv();
					}else{
						console.log(res.MSG);
					}
				},
				error : function(xhr, status){}
			});
		},
		<%-- 심리치유 상담 저장 --%>
		saveIdv = function(){
			if($("input[name='mbrNo']").val() == ""){
				alert("회원을 선택하세요.");
				return;
			}
			if($("input[name='cslDt']").val() == ""){
				alert("상담일는 필수 입력 항목입니다.");
				$("input[name='cslDt']").focus();					return;
			}
			if($("input[name='cslFmTm']").val() == ""){
				alert("상담 시간은 필수 입력 항목입니다.");
				$("input[name='cslFmTm']").focus();					return;
			}
			if($("input[name='cslToTm']").val() == ""){
				alert("상담 시간은 필수 입력 항목입니다.");
				$("input[name='cslToTm']").focus();					return;
			}
			if(needTime($("input[name='cslFmTm']").val(), $("input[name='cslToTm']").val()) <= 0){
				alert("상담 소요시간은 0보다 값이 커야 합니다.");
				$("input[name='cslToTm']").focus();					return;
			}
			if($("input[name='cslSbj']").val() == ""){
				alert("상담주제는 필수 입력 항목입니다.");
				$("input[name='cslSbj']").focus();					return;
			}
			if($("select[name='rskaTpCd']").val() == ""){
				alert("위기분류척도 Rating A: 위험성은 필수 입력 항목입니다.");
				$("select[name='rskaTpCd']").focus();				return;
			}
			if($("select[name='rskbTpCd']").val() == ""){
				alert("위기분류척도 Rating B: 지지체계는 필수 입력 항목입니다.");
				$("select[name='rskbTpCd']").focus();				return;
			}
			if($("select[name='rskcTpCd']").val() == ""){
				alert("위기분류척도 Rating C: 협조능력는 필수 입력 항목입니다.");
				$("select[name='rskcTpCd']").focus();				return;
			}
			if($("select[name='ursCd']").val() == ""){
				alert("USR은 필수 입력 항목입니다.");
				$("select[name='ursCd']").focus();					return;
			}
			if($("textarea[name='cslCtnt']").val() == ""){
				alert("상담내용은 필수 입력 항목입니다.");
				$("textarea[name='cslCtnt']").focus();				return;
			}
			if($("textarea[name='cslRst']").val() == ""){
				alert("상담결과는 필수 입력 항목입니다.");
				$("textarea[name='cslRst']").focus();				return;
			}

			$.ajax({
				url : '/ajaxClsCureAdd.do',
				type : 'POST',
				processData : false,
				contentType : false,
				enctype : 'multipart/form-data',
				data : new FormData($("#cslForm")[0]),
				success : function(res){
					if(res.err != "Y"){
						alert(res.MSG);
						getCslCureList($("input[name='mbrNo']").val());
						viewIdvRow($("input[name='cslNo']").val());
					}else{
						alert(res.MSG);
					}
				},
				error : function(xhr, status){}
			});
		},
		<%-- 심리치유 신규 --%>
		newIdv = function(){
			$("input[name='cslNo']").val("");
			$("input[name='cslDt']").datepicker('setDate', 'today');
			$("input[name='cslFmTm']").val("09:00");
			$("input[name='cslToTm']").val("09:00");
			$("span#cslTermTm").text("0");
			$("input[name='cslTermTm']").val("0");
			$("input[name='cslTgtCd']:radio[value='10']").prop("checked", true);
			$("input[name='cslClsCd']:radio[value='']").prop("checked", true);
			$("input[name='cslTpCd']:radio[value='20']").prop("checked", true);
			$("input[name='cslSbj']").val("");
			$("input[name='cslTgt']").val("");
			$("select[name='rskaTpCd']").val("0").prop("selected", true);
			$("select[name='rskbTpCd']").val("0").prop("selected", true);
			$("select[name='rskcTpCd']").val("0").prop("selected", true);
			$("span#ratingNum").text("0");
			$("input[name='crisisCounsel']").val("");
			$("select[name='ursCd']").val("").prop("selected", true);
			$("select[name='cureCd']").val("").prop("selected", true);
			$("select[name='drugUseCd']").val("").prop("selected", true);
			$("select[name='oldActCd']").val("").prop("selected", true);
			$("select[name='actCd']").val("").prop("selected", true);
			$("select[name='aroundSuicideCd']").val("").prop("selected", true);
			$("select[name='suicidePlanCd']").val("").prop("selected", true);
			$("select[name='oldActWayCd']").val("").prop("selected", true);
			$("select[name='actWayCd']").val("").prop("selected", true);
			$("textarea[name='cslCtnt']").val("");
			$("textarea[name='cslRst']").val("");
			$("input[name='nxtCslDt']").val("");
			$("input[name='nxtCslTm']").val("");
			$("textarea[name='nxtCslCtnt']").val("");
			$("div#fileName").text("");
			$("input[name='fileNameFlag']").val("N");
			$("input[name='file']").val("");
		},
		<%-- 상담내용보기 --%>
		ctntPopup = function(tagName){
			$("input[name='tagName']").val(tagName);
			$("textarea[name='viewCslCtnt']").val($("textarea[name='" + tagName + "']").val());
			layerPopupOpen('counwrite');
		},
		<%-- 상담내용상세보기 확인 --%>
		setCslCtnt = function(){
			$("textarea[name='" + $("input[name='tagName']").val() + "']").val($("textarea[name='viewCslCtnt']").val());
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
		},
		<%-- 엑셀다운로드 --%>
		mentalityExel = function(){
			if($("input[name='cslNo']").val() != ""){
				$("form#excelForm").empty();
				$("form#excelForm").append("<input type='hidden' name='cslNo' value='" + $("input[name='cslNo']").val() + "' />");
				$("form#excelForm").attr("action", "/mentalityExcelDownload.do");
				$("form#excelForm").submit();
			}else{
				alert("상담번호를 선택 하세요.");
			}
		}
	});
</script>
<!-- 페이지 타이틀 -->
<div class="tit-area">
	<h1><i class="el-icon-s-order" style="color: rgb(0, 108, 185);"></i> 상담</h1>
</div>
<!-- // 페이지 타이틀 -->
<!-- 상단 버튼 -->
<div class="top-right-btn">
	<button type="button" onclick="javaScript:saveIdv();" class="el-button normal el-button--primary el-button--small is-plain">
		<i class="el-icon-download"></i> <span>저장</span>
	</button>
	<button type="button" onclick="javaScript:newIdv();" class="el-button normal el-button--default el-button--small is-plain" style="margin-left: 8px;">
		<i class="el-icon-circle-plus-outline"></i> <span>신규</span>
	</button>
	<button type="button" onclick="javaScript:pageRest();" class="el-button normal el-button--default el-button--small is-plain" style="margin-left: 8px;">
		<i class="el-icon-refresh"></i> <span>초기화</span>
	</button>
	<button type="button" id="excelButNo" disabled="disabled" class="el-button normal el-button--default el-button--small is-plain">
		<i class="el-icon-document"></i><span>엑셀다운로드</span>
	</button>
	<button type="button" onclick="javaScript:mentalityExel();" id="excelButYes" class="el-button normal el-button--default el-button--small is-plain" style="display: none;">
		<i class="el-icon-document"></i><span>엑셀다운로드</span>
	</button>
</div>
<!-- // 상단 버튼 -->
<form name="cslForm" id="cslForm" enctype="multipart/form-data">
<input type="hidden" name="cslNo" value="" />
<div class="formline">
	<!-- 회원등록번호 ~ 사례관리자 -->
	<div class="section">
		<table class="w-auto">
			<tbody>
			<tr>
				<th>회원등록번호</th>
				<td>
					<div class="search-input tac">
						<input type="text" name="mbrNo" class="el-input__inner" readonly placeholder="회원등록번호" style="width: 142px;" />
						<button type="button" onclick="javaScript:mstMbrSearchPopup();"><i class="el-icon-search"></i></button>
					</div>
				</td>
				<th>성명</th>
				<td>
					<div class="dsp-ibk tac"><input type="text" name="mbrNm" class="el-input__inner" readonly placeholder="성명" style="width: 100px;" /></div>
				</td>
				<th>성별</th>
				<td>
					<div class="dsp-ibk tac"><input type="text" name="gendNm" class="el-input__inner" readonly placeholder="성별" style="width: 50px;" /></div>
				</td>
				<th>연령</th>
				<td>
					<div class="dsp-ibk tac"><input type="text" name="ageNm" class="el-input__inner" readonly placeholder="연령" style="width: 80px;" /></div>
				</td>
				<th>등록일자</th>
				<td>
					<div class="dsp-ibk tac"><input type="text" name="regDt" class="el-input__inner" readonly placeholder="등록일자" style="width: 100px;" /></div>
				</td>
				<th>의료보장</th>
				<td>
					<div class="dsp-ibk is-disabled"><input type="text" name="medicCareNm" class="el-input__inner" readonly placeholder="의료보장" style="width: 120px;" /></div>
				</td>
				<th>사례관리자</th>
				<td>
					<div class="dsp-ibk is-disabled"><input type="text" name="mngUsrId" class="el-input__inner" readonly placeholder="사례관리자" style="width: 130px;" /></div>
				</td>
			</tr>
			</tbody>
		</table>
	</div>
	<!-- // 회원등록번호 ~ 사례관리자 -->

	<div class="section-sha" style="min-width: 1840px;">
		<div class="el-tabs_content">
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
								<col style="width:150px">
								<col>
							</colgroup>
							<thead>
							<tr>
								<th>#</th>
								<th>상담 번호</th>
								<th>상담 일자</th>
								<th>시작/종료 시간</th>
								<th>소요(분)</th>
								<th>상담주제</th>
								<th>상담목표</th>
								<th>상담자</th>
								<th>작업</th>
							</tr>
							</thead>
						</table>
					</div>
					<div class="el-table_body-wrapper" style="height: 148px;" id="idvList">
						<div class="no-data">조회된 데이터가 없습니다.</div>
					</div>
				</div>
			</div>
			<div class="bottom-form el-row" id="idvInfoDiv">
				<div class="row">
					<table class="w-auto wr-form">
						<tbody>
						<tr>
							<th> 사례관리자</th>
							<td>
								<div class="dsp-ibk is-disabled"><input type="text" name="cslNm" value="<c:out value="${loginUserNm}" />" class="el-input__inner" readonly style="width: 120px;" /></div>
							</td>
							<th><span class="required">*</span> 상담일시</th>
							<td>
								<div class="dat-pk">
									<i class="el-input__icon el-icon-date"></i>
									<input type="text" name="cslDt" class="el-input__inner datepicker" placeholder="날짜 선택" style="width: 130px;" />
								</div>
								<div class="time-box">
									<div class="tm-in">
										<i class="el-input__icon el-icon-time"></i>
										<input type="text" name="cslFmTm" value="09:00" class="el-input__inner timepicker" placeholder="시작" style="width: 96px;" />
									</div>
									<span>~</span>
									<div class="tm-in">
										<i class="el-input__icon el-icon-time"></i>
										<input type="text" name="cslToTm" value="09:00" class="el-input__inner timepicker" placeholder="종료" style="width: 96px;" />
									</div>
									<div class="t-min">
										<span class="readonly" id="cslTermTm">0</span> 분 소요
										<input type="hidden" name="cslTermTm" value="0" />
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<th><span class="required">*</span> 상담구분</th>
							<td>
<c:if test="${cslClsCdList ne null and cslClsCdList ne ''}">
	<c:forEach var="result" items="${cslClsCdList}" varStatus="status">
								<span class="ck-bx">
									<input type="radio" class="el-radio__original" name="cslClsCd" id="cslClsCd-<c:out value='${status.count}'/>"  value="${result.CD_ID}"<c:if test="${result.CD_ID eq cslClsCd}"> checked</c:if> />
									<label for="cslClsCd-<c:out value='${status.count}'/>"><span class="el-radio__input"><span class="el-radio__inner"></span></span> <c:out value="${result.CD_NM}" /></label>
								</span>
	</c:forEach>
</c:if>
							</td>
						</tr>
						<tr>
							<th><span class="required">*</span> 상담대상</th>
							<td>
<c:if test="${cslTgtCdList ne null and cslTgtCdList ne ''}">
	<c:forEach var="result" items="${cslTgtCdList}" varStatus="status">
								<span class="ck-bx">
									<input type="radio" class="el-radio__original" name="cslTgtCd" id="cslTgtCd-<c:out value='${status.count}'/>"  value="${result.CD_ID}"<c:if test="${result.CD_ID eq cslTgtCd}"> checked</c:if> />
									<label for="cslTgtCd-<c:out value='${status.count}'/>"><span class="el-radio__input"><span class="el-radio__inner"></span></span> <c:out value="${result.CD_NM}" /></label>
								</span>
	</c:forEach>
</c:if>
							</td>
							<th><span class="required">*</span> 상담유형</th>
							<td>
<c:if test="${cslTpCdList ne null and cslTpCdList ne ''}">
	<c:forEach var="result" items="${cslTpCdList}" varStatus="status">
								<span class="ck-bx">
									<input type="radio" class="el-radio__original" name="cslTpCd" id="cslTpCd-<c:out value='${status.count}'/>"value="${result.CD_ID}"<c:if test="${result.CD_ID eq cslTpCd}"> checked</c:if> />
									<label for="cslTpCd-<c:out value='${status.count}'/>"><span class="el-radio__input"><span class="el-radio__inner"></span></span> <c:out value="${result.CD_NM}" /></label>
								</span>
	</c:forEach>
</c:if>
							</td>
						</tr>
						<tr>
							<th><span class="required">*</span> 상담주제</th>
							<td colspan="3"><input type="text" name="cslSbj" class="el-input__inner" placeholder="상담주제" style="width: 680px;" /></td>
						</tr>
						<tr>
							<th>상담목표</th>
							<td colspan="3"><input type="text" name="cslTgt" class="el-input__inner" placeholder="상담목표" style="width: 680px;" /></td>
						</tr>
						<tr>
							<th><span class="required">*</span> 위기분류척도</th>
							<td colspan="3">
								<span class="el-tag">Rating A: 위험성</span>
								<select name="rskaTpCd" style="width: 555px;" onchange="javaScript:changRating();">
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
							<td colspan="3">
								<span class="el-tag">Rating B: 지지체계</span>
								<select name="rskbTpCd" style="width: 555px;" onchange="javaScript:changRating();">
<c:if test="${rskbTpList ne null and rskbTpList ne ''}">
	<c:forEach var="result" items="${rskbTpList}" varStatus="status">
									<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
								</select>
							</td>
						</tr>
						<tr>
							<th class="txt-center">
								<span class="el-tag-danger" id="ratingNum">0</span>
								<input type="hidden" name="rskSco" value="0" />
							</th>
							<td colspan="3">
								<span class="el-tag">Rating C: 협조능력</span>
								<select name="rskcTpCd" style="width: 555px;" onchange="javaScript:changRating();">
<c:if test="${rskcTpList ne null and rskcTpList ne ''}">
	<c:forEach var="result" items="${rskcTpList}" varStatus="status">
									<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
								</select>
							</td>
						</tr>
						<tr>
							<th> 위기상담</th>
							<td colspan="3"><input type="text" name="crisisCounsel" class="el-input__inner" placeholder="위기상담" style="width: 680px;" /></td>
						</tr>
						<tr>
							<th><span class="required">*</span> URS</th>
							<td colspan="3">
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
<%--
						<tr>
							<th class="v-top" rowspan="8"> 자살관련</th>
							<td colspan="3">
								<span class="el-tag">치료력</span>
								<select name="cureCd" title="치료력" style="width: 557px;">
									<option value="">선택</option>
<c:if test="${cureCdList ne null and cureCdList ne ''}">
	<c:forEach var="result" items="${cureCdList}" varStatus="status">
									<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
								</select>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<span class="el-tag">약물사용여부</span>
								<select name="drugUseCd" title="약물사용여부" style="width: 557px;">
									<option value="">선택</option>
<c:if test="${selCdList ne null and selCdList ne ''}">
	<c:forEach var="result" items="${selCdList}" varStatus="status">
									<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
								</select>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<span class="el-tag">과거 자살시도력</span>
								<select name="oldActCd" title="과거 자살시도력" style="width: 557px;">
									<option value="">선택</option>
<c:if test="${actCdList ne null and actCdList ne ''}">
	<c:forEach var="result" items="${actCdList}" varStatus="status">
									<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
								</select>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<span class="el-tag">시도 횟수</span>
								<select name="actCd" title="시도 횟수" style="width: 557px;">
									<option value="">선택</option>
<c:if test="${actCdList ne null and actCdList ne ''}">
	<c:forEach var="result" items="${actCdList}" varStatus="status">
									<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
								</select>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<span class="el-tag">주변인 자살</span>
								<select name="aroundSuicideCd" title="주변인 자살" style="width: 557px;">
									<option value="">선택</option>
<c:if test="${selCdList ne null and selCdList ne ''}">
	<c:forEach var="result" items="${selCdList}" varStatus="status">
									<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
								</select>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<span class="el-tag">자살계획</span>
								<select name="suicidePlanCd" title="자살계획" style="width: 557px;">
									<option value="">선택</option>
<c:if test="${selCdList ne null and selCdList ne ''}">
	<c:forEach var="result" items="${selCdList}" varStatus="status">
									<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
								</select>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<span class="el-tag">(과거)시도방법</span>
								<select name="oldActWayCd" title="(과거)시도방법" style="width: 557px;">
									<option value="">선택</option>
<c:if test="${actWayCdList ne null and actWayCdList ne ''}">
	<c:forEach var="result" items="${actWayCdList}" varStatus="status">
									<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
								</select>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<span class="el-tag">시도계획방법</span>
								<select name="actWayCd" title="시도계획방법" style="width: 557px;">
									<option value="">선택</option>
<c:if test="${actWayCdList ne null and actWayCdList ne ''}">
	<c:forEach var="result" items="${actWayCdList}" varStatus="status">
									<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
								</select>
							</td>
						</tr>
--%>
						</tbody>
					</table>
				</div>
				<div class="row">
					<table class="w-auto wr-form">
						<tbody>
						<tr>
							<th class="v-top">
								<span class="required">*</span> 상담내용<br>
								<button type="button" onclick="javaScript:ctntPopup('cslCtnt');" class="el-button el-button--success el-button--mini is-plain" style="padding: 4px 6px;">
									<i class="el-icon-search"></i>
								</button>
							</th>
							<td style="width:690px;"><textarea name="cslCtnt" placeholder="상담 내용" style="width:100%;height:120px">${cslIdvInfo.cslCtnt}</textarea></td>
						</tr>
						<tr>
							<th class="v-top">
								<span class="required">*</span> 상담결과<br>
								<button type="button" onclick="javaScript:ctntPopup('cslRst');" class="el-button el-button--success el-button--mini is-plain" style="padding: 4px 6px;">
									<i class="el-icon-search"></i>
								</button>
							</th>
							<td style="width:690px;"><textarea name="cslRst" placeholder="상담 결과" style="width:100%;height:120px">${cslIdvInfo.cslRst}</textarea></td>
						</tr>
						<tr>
							<th>다음 상담일시</th>
							<td>
								<div class="dat-pk">
									<i class="el-input__icon el-icon-date"></i>
									<input type="text" name="nxtCslDt" class="el-input__inner datepicker" placeholder="날짜" style="width: 130px;" />
								</div>
								<div class="time-box">
									<div class="tm-in">
										<i class="el-input__icon el-icon-time"></i>
										<input type="text" name="nxtCslTm" class="el-input__inner timepicker" placeholder="시간" style="width: 96px;" />
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<th class="v-top">
								다음 상담내용<br>
								<button type="button" onclick="javaScript:ctntPopup('nxtCslCtnt');" class="el-button el-button--success el-button--mini is-plain" style="padding: 4px 6px;">
									<i class="el-icon-search"></i>
								</button>
							</th>
							<td><textarea name="nxtCslCtnt" placeholder="다음 상담내용" style="width:100%;height:120px"><c:out value="${cslIdvInfo.nxtCslCtnt}" /></textarea></td>
						</tr>
						<tr>
							<th> 첨부파일</th>
							<td>
								<div id="fileName"></div>
								<input type="hidden" name="fileNameFlag" value="N" />
								<input type="file" id="file" name="file" placeholder="첨부" style="width: 100%;" />
							</td>
						</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
</form>
<!-- 상담내용 팝업 -->
<div class="layerpopup" data-popup="counwrite">
	<input type="hidden" name="tagName" />
	<div class="popup">
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
					<i class="el-icon-check"></i> <span>확인</span>
				</button>
				<button type="button" onclick="javaScript:layerPopupClose('counwrite');" class="el-button el-button--default el-button--small">
					<i class="el-icon-close"></i> <span>닫기</span>
				</button>
			</div>
			<!-- // 닫기 -->
		</div>
	</div>
</div>
<!-- // 상담내용 팝업 -->
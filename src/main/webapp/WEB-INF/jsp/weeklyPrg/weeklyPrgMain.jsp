<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String loginUserId = StringUtils.defaultIfEmpty((String)request.getAttribute("LoginUserId"), "");
	String loginSiteNm = StringUtils.defaultIfEmpty((String)request.getAttribute("LoginSiteNm"), "");
%>
<c:set var="loginUserId" value="<%=loginUserId%>" />
<c:set var="loginSiteNm" value="<%=loginSiteNm%>" />
<script type="text/javascript" language="javascript" charset="utf-8" src="<c:url value='/js/jquery.form.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">
	$(document).ready(function(){
		$("input[name='schStrDt']").datepicker('setDate', '-3M');
		$("input[name='schEndDt']").datepicker('setDate', 'today');
		$("input[name='pgmDt']").datepicker('setDate', 'today');
		<%-- 페이지 초기화 --%>
		pageRest = function(){
			window.location.reload();
		},
		<%-- 프로그램 목록 조회 --%>
		getWeeklyPrgList = function(){
			$.ajax({
				url : '/ajaxGetWeeklyPrgList.do',
				type : 'POST',
				data : $("#prgListForm").serialize(),
				success : function(res){
					$("div[id='weeklyPrgList']").html(res);
				},
				error : function(xhr, status){
					console.log(xhr);
				}
			});
		},
		<%-- 검색버튼 클릭 --%>
		seachPrgList = function(){
			$("input[name='pgmPageNo']").val('1');
			getWeeklyPrgList();
		},
		<%-- 프로그램 정보 등록 --%>
		addGrpPgm = function(){
			if($("select[name='pgmCd']").val() == ""){
				alert("프로그램 분류는 필수 입력 항목입니다.");
				$("select[name='pgmCd']").focus();					return;
			}
			if($("input[name='pgmDt']").val() == ""){
				alert("프로그램 실시 일자는 필수 입력 항목입니다.");
				$("input[name='pgmDt']").focus();					return;
			}
			if($("input[name='pgmFmTm']").val() == ""){
				alert("프로그램 실시 시작시간은 필수 입력 항목입니다.");
				$("input[name='pgmFmTm']").focus();					return;
			}
			if($("input[name='pgmToTm']").val() == ""){
				alert("프로그램 실시 종료시간은 필수 입력 항목입니다.");
				$("input[name='pgmToTm']").focus();					return;
			}
			if($("select[name='mngUsrId']").val() == ""){
				alert("담당자는 필수 입력 항목입니다.");
				$("select[name='mngUsrId']").focus();				return;
			}
			if($("textarea[name='pgmCtnt']").val() == ""){
				alert("프로그램 내용은 필수 입력 항목입니다.");
				$("textarea[name='pgmCtnt']").focus();				return;
			}

			$.ajax({
				url : '/ajaxWeeklyPrgAdd.do',
				type : 'POST',
				processData : false,
				contentType : false,
				enctype : 'multipart/form-data',
				data : new FormData($("#pgmInfoForm")[0]),
				success : function(res){
					if(res.err != "Y"){
						alert(res.MSG + " 성공");

						seachPrgList();
						reSetPgmForm();
						getPgmMemList();
					}else{
						alert(res.MSG);
						if(res.actUrl != "" && res.actUrl != undefined){
							window.location.href = res.actUrl;
						}
					}
				},
				error : function(xhr, status){
					console.log(xhr);
				}
			});
		},
		<%-- 프로그램 정보 폼 초기화 --%>
		reSetPgmForm = function(obj){
			if(obj == undefined){
				$("button#delBtnYes").hide();
				$("button#delBtnNo").show();
				$("button#excelButNo").show();
				$("button#excelButYes").hide();

				$("select[name='pgmCd']").val("").prop("selected", true);
				$("input[name='pgmDt']").datepicker('setDate', 'today');
				$("input[name='pgmFmTm']").val("09:00");
				$("input[name='pgmToTm']").val("18:00");
				$("select[name='pgmTpCd']").val("").prop("selected", true);
				$("select[name='mngUsrId']").val("<c:out value="${loginUserId}" />").prop("selected", true);
				$("input[name='siteNm']").val("<c:out value="${loginSiteNm}" />");
				$("input[name='pgmSession']").val("");
				$("textarea[name='pgmCtnt']").val("");
				$("textarea[name='pgmRst']").val("");
				$("input[name='file']").val("");
				$("div#fileName").text("");
			}else{
				$.ajax({
					url : '/ajaxGetWeeklyPrg.do',
					type : 'POST',
					data : {
						pgmDt : obj.pgmDt,
						pgmCd : obj.pgmCd
					},
					success : function(res){
						$("button#delBtnYes").show();
						$("button#delBtnNo").hide();
						$("button#excelButNo").hide();
						$("button#excelButYes").show();

						var prgInfo = res.prgInfo;
						var setDt = new Date(prgInfo.PGM_DT.substr(0, 4), prgInfo.PGM_DT.substr(4, 2), prgInfo.PGM_DT.substr(6, 2));
						setDt.setMonth(setDt.getMonth() - 1);

						$("select[name='pgmCd']").val(prgInfo.PGM_CD).prop("selected", true);
						$("input[name='pgmDt']").datepicker('setDate', setDt);
						$("input[name='pgmFmTm']").val(prgInfo.PGM_FM_TM);
						$("input[name='pgmToTm']").val(prgInfo.PGM_TO_TM);
						$("select[name='pgmTpCd']").val(prgInfo.PGM_TP_CD).prop("selected", true);
						$("select[name='mngUsrId']").val(prgInfo.MNG_USR_ID).prop("selected", true);
						$("input[name='siteNm']").val(prgInfo.SITE_NM);
						$("input[name='pgmSession']").val(prgInfo.PGM_SESSION);
						$("textarea[name='pgmCtnt']").val(prgInfo.PGM_CTNT);
						$("textarea[name='pgmRst']").val(prgInfo.PGM_RST);

						if(prgInfo.fileList != undefined && prgInfo.fileList != ''){
							for(let i=0 ; i<prgInfo.fileList.length ; i++){
								$("div#fileName").html("<a href='javaScript:downloadFile(\"" + prgInfo.fileList[i].FILE_ID + "\", \"" + prgInfo.fileList[i].FILE_SEQ + "\");'>" + prgInfo.fileList[i].ORIGNL_FILE_NM + "</a>");
							}
						}else{
							$("div#fileName").text("");
						}
					},
					error : function(xhr, status){
						console.log(xhr);
					}
				});

				getPgmMemList(obj.pgmDt, obj.pgmCd);
			}
		},
		<%-- 프로그램회원 목록 --%>
		getPgmMemList = function(tagPgmDt, tagPgmCd){
			$.ajax({
				url : '/getPgmMemList.do',
				type : 'POST',
				data : {
					pgmDt : tagPgmDt,
					pgmCd : tagPgmCd
				},
				success : function(res){
					$("div#weeklyPgmMemList").html(res);
				},
				error : function(xhr, status){
					console.log(xhr);
				}
			});
		},
		<%-- 회원 조회 --%>
		mstMbrSearchPopup = function(){
			$.ajax({
				url : '/ajaxMstMbrList.do',
				type : 'POST',
				data : {
					pageNo : $("input[name='memPageNo']").val(),
					mbrNm : $("input[name='memSchMbrNm']").val(),
					telNo : $("input[name='memSchTelNo']").val(),
					closeFlg : "N"
				},
				success : function(res){
					$("div[id='layerpopup']").html(res);
					$("div[id='layerpopup']").attr("data-popup", "memberPopUp");
					$("input[name='reFunName']").val("addPgmMem");
					layerPopupOpen('memberPopUp');
				},
				error : function(xhr, status){
					console.log(xhr);
				}
			});
		},
		addPgmMem = function(obj){
			var addFlag = true;
			$("input[name='pgmMbrNo']").each(function(){
				if($(this).val() == obj.MBR_NO){
					alert("이미 등록된 회원입니다.");
					addFlag = false;							return;
				}
			});
			if(!addFlag){
				return;
			}

			var inHtml = "";
			var pgmMemCount = Number($("input[name='pgmMemCount']").val()) + 1;

			if(pgmMemCount <= 1){
				inHtml = "<table id='pgmMemTable'>"
					   + "	<colgroup>"
					   + "		<col style='width:46px'>"
					   + "		<col style='width:66px'>"
					   + "		<col style='width:160px'>"
					   + "		<col style='width:100px'>"
					   + "		<col style='width:650px'>"
					   + "		<col>"
					   + "	</colgroup>"
					   + "	<tbody>"
					   + "	<tr id='" + pgmMemCount + "'>"
					   + "	<input type='hidden' name='pgmMbrNo' value='" + obj.MBR_NO + "' />"
					   + "	<input type='hidden' name='mbrSeqNo' value='' />"
					   + "		<td><div class='cell'>" + pgmMemCount + "</div></td>"
					   + "		<td>"
					   + "			<div class='cell'>"
					   + "				<button type='button' onclick='javaScript:pgmMemDel(\"" + pgmMemCount + "\", \"0\");' class='el-button el-button--danger el-button--mini is-plain' slot='reference' style='margin-left: 1px; padding: 4px 9px;'>"
					   + "					<span>삭제</span>"
					   + "				</button>"
					   + "			</div>"
					   + "		</td>"
					   + "		<td><div class='cell'>" + obj.MBR_NO + "</div></td>"
					   + "		<td><div class='cell'>" + obj.MBR_NM + "</div></td>"
					   + "		<td class='txt-left'><div class='cell' id='ctntView'></div></td>"
					   + "		<td>"
					   + "			<div class='cell'>"
					   + "				<button type='button' onclick='javaScript:viewCtnt(\"mbrCtnt\", \"" + pgmMemCount + "\");' class='el-button el-button--success el-button--mini is-plain' slot='reference' style='margin-left: 1px; padding: 4px 9px;'>"
					   + "					<i class='el-icon-search'></i>"
					   + "				</button>"
					   + "			</div>"
					   + "			<textarea name='mbrCtnt' style='display:none;'></textarea>"
					   + "		</td>"
					   + "	</tr>"
					   + "	</tbody>"
					   + "</table>";

				$("div#pgmMemTableDiv").html(inHtml);
			}else{
				inHtml = "	<tr id='" + pgmMemCount + "'>"
					   + "	<input type='hidden' name='pgmMbrNo' value='" + obj.MBR_NO + "' />"
					   + "	<input type='hidden' name='mbrSeqNo' value='' />"
					   + "		<td><div class='cell'>" + pgmMemCount + "</div></td>"
					   + "		<td>"
					   + "			<div class='cell'>"
					   + "				<button type='button' onclick='javaScript:pgmMemDel(\"" + pgmMemCount + "\", \"0\");' class='el-button el-button--danger el-button--mini is-plain' slot='reference' style='margin-left: 1px; padding: 4px 9px;'>"
					   + "					<span>삭제</span>"
					   + "				</button>"
					   + "			</div>"
					   + "		</td>"
					   + "		<td><div class='cell'>" + obj.MBR_NO + "</div></td>"
					   + "		<td><div class='cell'>" + obj.MBR_NM + "</div></td>"
					   + "		<td class='txt-left'><div class='cell' id='ctntView'></div></td>"
					   + "		<td>"
					   + "			<div class='cell'>"
					   + "				<button type='button' onclick='javaScript:viewCtnt(\"mbrCtnt\", \"" + pgmMemCount + "\");' class='el-button el-button--success el-button--mini is-plain' slot='reference' style='margin-left: 1px; padding: 4px 9px;'>"
					   + "					<i class='el-icon-search'></i>"
					   + "				</button>"
					   + "			</div>"
					   + "			<textarea name='mbrCtnt' style='display:none;'></textarea>"
					   + "		</td>"
					   + "	</tr>";

				$("table#pgmMemTable > tbody:last").append(inHtml);
			}

			$("input[name='pgmMemCount']").val(pgmMemCount);
		},
		<%-- 내용 보기 팝업 --%>
		viewCtnt = function(tagName, num){
			var value;
			if(num != undefined && num != ''){
				$("textarea[name='" + tagName + "']").each(function(idx){
					if(num == (idx + 1)){
						value = $(this).val();
					}
				});
			}else{
				value = $("textarea[name='" + tagName + "']").val();
			}

			$("input[name='tagName']").val(tagName);
			$("input[name='tagNum']").val(num);

			$("textarea[name='viewText']").val(value);
			layerPopupOpen('counwrite');
		},
		<%-- 내용 확인 --%>
		sendOriginalVal = function(){
			var tagName = $("input[name='tagName']").val();
			var tagNum = $("input[name='tagNum']").val();

			if(tagNum != ""){
				$("textarea[name='" + tagName + "']").each(function(idx){
					if((idx + 1) == tagNum){
						$("div#ctntView").eq(idx).html($("textarea[name='viewText']").val());
						$(this).val($("textarea[name='viewText']").val());
					}
				});
			}else{
				$("textarea[name='" + tagName + "']").val($("textarea[name='viewText']").val());
			}

			layerPopupClose('counwrite');
		},
		<%-- 신규--%>
		newBtn = function(){
			reSetPgmForm();
			getPgmMemList();
		},
		<%-- 삭제 --%>
		pgmDel = function(){
			if($("select[name='pgmCd']").val() == ""){
				alert("프로그램이 선택 되지 않았습니다.");			return;
			}
			if($("input[name='pgmDt']").val() == ""){
				alert("프로그램이 선택 되지 않았습니다.");			return;
			}

			if(confirm("등록된 프로그램 실시 내역을 삭제하시겠습니까?")){
				$.ajax({
					url : '/ajaxGrpPgmDel.do',
					type : 'POST',
					data : {
						pgmCd : $("select[name='pgmCd']").val(),
						pgmDt : $("input[name='pgmDt']").val()
					},
					success : function(res){
						if(res.err == "Y"){
							alert(res.MSG);
						}else{
							alert("삭제 성공 하였습니다.");
							newBtn();
							seachPrgList();
						}
					},
					error : function(xhr, status){
						console.log(xhr);
					}
				});
			}
		},
		<%-- 담당자 변경에 따른 기관명 변경 --%>
		changSiteNm = function(obj){
			$("input[name='siteNm']").val($("select[name='" + $(obj).attr("name") + "'] option:selected").attr("siteNm"));
		},
		<%-- 엑셀다운로드 --%>
		weeklyExel = function(){
			alert("준비중");
		}

		getWeeklyPrgList();
		getPgmMemList();
	});
</script>
<!-- 페이지 타이틀 -->
<div class="tit-area">
	<h1><i class="el-icon-s-order" style="color: rgb(0, 108, 185);"></i> 주간재활 프로그램</h1>
</div>
<!-- // 페이지 타이틀 -->
<!-- 상단 버튼 -->
<div class="top-right-btn">
	<button type="button" onclick="javaScript:addGrpPgm();" class="el-button normal el-button--primary el-button--small is-plain">
		<i class="el-icon-download"></i> <span>저장</span>
	</button>
	<button type="button" onclick="javaScript:newBtn();" class="el-button normal el-button--default el-button--small is-plain" style="margin-left: 8px;">
		<i class="el-icon-circle-plus-outline"></i> <span>신규</span>
	</button>
	<button type="button" id="delBtnNo" disabled="disabled" class="el-button normal el-button--default el-button--small is-disabled is-plain">
		<i class="el-icon-delete-solid"></i> <span>삭제</span>
	</button>
	<button type="button" id="delBtnYes" onclick="javaScript:pgmDel();" class="el-button normal el-button--default el-button--small is-plain" style="display:none;">
		<i class="el-icon-delete-solid"></i> <span>삭제</span>
	</button>
	<button type="button" onclick="javaScript:pageRest();" class="el-button normal el-button--default el-button--small is-plain" style="margin-left: 8px;">
		<i class="el-icon-refresh"></i> <span>초기화</span>
	</button>
	<button type="button" id="excelButNo" disabled="disabled" class="el-button normal el-button--default el-button--small is-plain">
		<i class="el-icon-document"></i><span>엑셀다운로드</span>
	</button>
	<button type="button" onclick="javaScript:weeklyExel();" id="excelButYes" class="el-button normal el-button--default el-button--small is-plain" style="display: none;">
		<i class="el-icon-document"></i><span>엑셀다운로드</span>
	</button>
</div>
<!-- // 상단 버튼 -->
<div class="formline">
	<div class="week-pg">
		<form name="prgListForm" id="prgListForm">
		<input type="hidden" name="pgmPageNo" value="1" />
		<div class="l">
			<div class="section bg-sky">
				<table class="w-auto wr-form">
					<tbody>
					<tr>
						<th><span class="required">*</span> 검색기간</th>
						<td>
							<div class="dat-pk">
								<i class="el-input__icon el-icon-date"></i>
								<input type="text" name="schStrDt" class="el-input__inner datepicker" placeholder="시작" style="width: 105px;">
							</div>
							<span>~</span>
							<div class="dat-pk">
								<i class="el-input__icon el-icon-date"></i>
								<input type="text" name="schEndDt" class="el-input__inner datepicker" placeholder="종료" style="width: 105px;">
							</div>
						</td>
						<th>프로그램</th>
						<td>
							<select name="schTpCd" style="width:100px">
								<option value="">선택</option>
<c:if test="${pgmTpCdList ne null and pgmTpCdList ne ''}">
	<c:forEach var="result" items="${pgmTpCdList}" varStatus="status">
								<option value="<c:out value="${result.CD_ID}" />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
							</select>
							<select name="schPgmCd" style="width:110px">
								<option value="">선택</option>
<c:if test="${pgmCdList ne null and pgmCdList ne ''}">
	<c:forEach var="result" items="${pgmCdList}" varStatus="status">
								<option value="<c:out value="${result.CD_ID}" />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
							</select>
							<button type="button" onclick="javaScript:seachPrgList();" class="el-button el-button--primary el-button--small is-plain" style="margin-left: 8px; padding: 8px 15px;">
								<i class="el-icon-search"></i>
								<span>검색</span>
							</button>
						</td>
					</tr>
					</tbody>
				</table>
			</div>
			<div id="weeklyPrgList"></div>
		</div>
		</form>
		<div class="r">
			<!-- 프로그램 정보 -->
			<form name="pgmInfoForm" id="pgmInfoForm" enctype="multipart/form-data">
			<div class="section pdn">
				<div class="el-card_header">
					<h2><i class="el-icon-s-opportunity"></i> 프로그램 정보</h2>
				</div>
				<div class="el-card_body">
					<table class="w-auto wr-form">
						<tbody>
						<tr>
							<th>대분류</th>
							<td>
								<select name="pgmTpCd" style="width: 130px;">
									<option value="">선택</option>
<c:if test="${pgmTpCdList ne null and pgmTpCdList ne ''}">
	<c:forEach var="result" items="${pgmTpCdList}" varStatus="status">
									<option value="<c:out value="${result.CD_ID}" />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
								</select>
							</td>
							<th>중분류</th>
							<td>
								<select name="pgmCd" style="width: 150px;">
									<option value="">선택</option>
<c:if test="${pgmCdList ne null and pgmCdList ne ''}">
	<c:forEach var="result" items="${pgmCdList}" varStatus="status">
									<option value="<c:out value="${result.CD_ID}" />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
								</select>
							</td>
							<th><span class="required">*</span> 실시 일시</th>
							<td>
								<div class="dat-pk">
									<i class="el-input__icon el-icon-date"></i>
									<input type="text" name="pgmDt" class="el-input__inner datepicker" placeholder="날짜" style="width: 110px;">
								</div>
								<div class="time-box">
									<div class="tm-in">
										<i class="el-input__icon el-icon-time"></i>
										<input type="text" name="pgmFmTm" value="09:00" class="el-input__inner timepicker" placeholder="시작" style="width: 75px;">
									</div>
									<span>~</span>
									<div class="tm-in">
										<i class="el-input__icon el-icon-time"></i>
										<input type="text" name="pgmToTm" value="18:00" class="el-input__inner timepicker" placeholder="종료" style="width: 75px;">
									</div>
								</div>
							</td>
							<th><span class="required">*</span> 담당자</th>
							<td>
								<select name="mngUsrId" style="width: 140px;" onchange="javaScrpt:changSiteNm(this);">
									<option value="">선택</option>
<c:if test="${sysMbrList ne null and sysMbrList ne ''}">
	<c:forEach var="result" items="#{sysMbrList}" varStatus="status">
									<option value="<c:out value="${result.USR_ID}" />" siteNm="<c:out value="${result.SITE_NM}" />"<c:if test="${result.USR_ID eq loginUserId}"> selected</c:if>><c:out value="${result.USR_NM}" />(<c:out value="${result.USR_ID}" />)</option>
	</c:forEach>
</c:if>
								</select>
							</td>
							<th>기관명</th>
							<td>
								<span class="tac"><input type="text" name="siteNm" value="<c:out value="${loginSiteNm}" />" class="el-input__inner" readonly style="width:100px;" /></span>
							</td>
						</tr>
						</tbody>
					</table>
					<table class="wr-form sig-form">
						<colgroup>
							<col style="width:58px">
							<col>
						</colgroup>
						<tbody>
						<tr>
							<th>회기</th>
							<td><input type="text" name="pgmSession" placeholder="회기" style="width: 100%;" /></td>
						</tr>
						<tr>
							<th>
								<span class="required">*</span> 내용<br>
								<button type="button" onclick="javaScript:viewCtnt('pgmCtnt', '');" class="el-button el-button--success el-button--mini is-plain" style="padding: 4px 6px;">
									<i class="el-icon-search"></i>
								</button>
							</th>
							<td><textarea name="pgmCtnt" placeholder="내용" style="height: 110px;"></textarea></td>
						</tr>
						<tr>
							<th>결과<br>
								<button type="button" onclick="javaScript:viewCtnt('pgmRst', '');" class="el-button el-button--success el-button--mini is-plain" style="padding: 4px 6px;">
									<i class="el-icon-search"></i>
								</button>
							</th>
							<td><textarea name="pgmRst" placeholder="결과" style="height: 110px;"></textarea></td>
						</tr>
						<tr>
							<th>첨부</th>
							<td><div id="fileName"></div><input type="file" id="file" name="file" placeholder="첨부" style="width: 100%;" /></td>
						</tr>
						</tbody>
					</table>
				</div>
			</div>
			<!-- //  프로그램 정보 -->
			<!-- 회원 목록 -->
			<div class="section pdn">
				<div class="el-card_header">
					<h2><i class="el-icon-s-opportunity"></i> 회원 목록</h2>
				</div>
				<div class="el-card_body">
					<div>
						<button type="button" onclick="javaScript:mstMbrSearchPopup();" class="v-md el-button el-button--primary el-button--small is-plain" style="padding: 7px 13px;">
							<i class="el-icon-circle-plus-outline"></i> <span>회원 추가</span>
						</button>
						<span class="text">※ 회원팝업 후 선택 버튼을 클릭하시면 아래 목록에 계속 추가되며 완료된 후에는 닫기 버튼을 클릭하세요.</span>
					</div>
					<div class="table-box mgt10">
						<div class="el-table_header-wrapper">
							<table>
								<colgroup>
									<col style="width:46px">
									<col style="width:66px">
									<col style="width:160px">
									<col style="width:100px">
									<col style="width:650px">
									<col>
								</colgroup>
								<thead>
								<tr>
									<th>#</th>
									<th>작업</th>
									<th>회원번호</th>
									<th>이름</th>
									<th>진행기록</th>
									<th>보기</th>
								</tr>
								</thead>
							</table>
						</div>
						<div id="weeklyPgmMemList"></div>
					</div>
				</div>
			</div>
			<!-- // 회원 목록 -->
			</form>
		</div>
	</div>
</div>
<!-- 상담내용 팝업 -->
<input type="hidden" name="tagName" value="" />
<input type="hidden" name="tagNum" value="" />
<div class="layerpopup" data-popup="counwrite">
	<div class="popup">
		<div class="pop-header">
			<span>상세내용</span>
			<button type="button" class="el-dialog__headerbtn" onclick="layerPopupClose('counwrite');">
				<i class="el-dialog__close el-icon el-icon-close"></i>
			</button>
		</div>
		<div class="pop-content">
			<div class="section bg">
				<textarea name="viewText" style="height: 430px;" placeholder="상세내용을 입력하세요."></textarea>
			</div>
			<!-- // 검색 -->
			<!-- 닫기 -->
			<div class="el-dialog__footer">
				<button type="button" onclick="javaScript:sendOriginalVal();" class="el-button el-button--primary el-button--small is-plain">
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
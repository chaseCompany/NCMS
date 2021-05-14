<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String loginUserNm = StringUtils.defaultIfEmpty((String)request.getAttribute("LoginUserNm"), "");
	String loginSiteNm = StringUtils.defaultIfEmpty((String)request.getAttribute("LoginSiteNm"), "");
%>
<c:set var="loginUserNm" value="<%=loginUserNm%>" />
<c:set var="loginSiteNm" value="<%=loginSiteNm%>" />
<script type="text/javascript" language="javascript" charset="utf-8" src="<c:url value='/js/jquery.form.js'/>"></script>
<script type="text/javascript" language="javascript" charset="utf-8" src="<c:url value='/js/jquery.sumoselect.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">
	$(document).ready(function(){
		$("input[name='ispDt']").datepicker('setDate', 'today');
		// 멀티 셀렉트박스 생성
		var multipleSelect = $('#lawPbmCd').SumoSelect();

		<%-- 초기화 --%>
		individualNew = function(){
			window.location.reload();
		},
		<%-- 사례관리 상담 엑셀 --%>
		individualExel = function(){
			if($("input[name='cslNo']").val() != ""){
				$("form#excelForm").empty();
				$("form#excelForm").append("<input type='hidden' name='cslNo' value='" + $("input[name='cslNo']").val() + "' />");
				$("form#excelForm").attr("action", "/individualExcelDownload.do");
				$("form#excelForm").submit();
			}else{
				alert("상담번호를 선택 하세요.");
			}
		},
		<%-- ISP수립 엑셀 --%>
		ispExcel = function(){
			if($("input[name='choiceMbrNo']").val() != "" && $("input[name='choiceIspDt']").val() != ""){
				$("form#excelForm").empty();
				$("form#excelForm").append("<input type='hidden' name='mbrNo' value='" + $("input[name='choiceMbrNo']").val() + "' />");
				$("form#excelForm").append("<input type='hidden' name='ispDt' value='" + $("input[name='choiceIspDt']").val() + "' />");
				$("form#excelForm").attr("action", "/ispExcelDownload.do");
				$("form#excelForm").submit();
			}else{
				if($("input[name='choiceMbrNo']").val() == ""){
					alert("회원을 선택하세요.");
					return;
				}
				if($("input[name='choiceIspDt']").val() == ""){
					alert("수립일자를 선택하세요.");
					return;
				}
			}
		},
		<%-- 병력정보 엑셀 --%>
		cslAnmExel = function(){
			if($("input[name='cslNo']").val() != ""){
				$("form#excelForm").empty();
				$("form#excelForm").append("<input type='hidden' name='cslNo' value='" + $("input[name='cslNo']").val() + "' />");
				$("form#excelForm").append("<input type='hidden' name='mbrNo' value='" + $("input[name='mbrNo']").val() + "' />");
				$("form#excelForm").append("<input type='hidden' name='mbrNm' value='" + $("input[name='mbrNm']").val() + "' />");
				$("form#excelForm").append("<input type='hidden' name='gendNm' value='" + $("input[name='gendNm']").val() + "' />");
				$("form#excelForm").append("<input type='hidden' name='age' value='" + $("input[name='age']").val() + "' />");
				$("form#excelForm").append("<input type='hidden' name='regDt' value='" + $("input[name='regDt']").val() + "' />");
				$("form#excelForm").append("<input type='hidden' name='medicCareNm' value='" + $("input[name='medicCareNm']").val() + "' />");
				$("form#excelForm").append("<input type='hidden' name='mngUsrId' value='" + $("input[name='mngUsrId']").val() + "' />");
				$("form#excelForm").attr("action", "/cslAnmExcelDownload.do");
				$("form#excelForm").submit();
			}else{
				alert("중독력등록일자를 선택 하세요.");
			}
		},
		<%-- 치료 재활정보 엑셀 --%>
		cureExel = function(){
			if($("input[name='cslNo']").val() != ""){
				$("form#excelForm").empty();
				$("form#excelForm").append("<input type='hidden' name='cslNo' value='" + $("input[name='cslNo']").val() + "' />");
				$("form#excelForm").append("<input type='hidden' name='mbrNo' value='" + $("input[name='mbrNo']").val() + "' />");
				$("form#excelForm").append("<input type='hidden' name='mbrNm' value='" + $("input[name='mbrNm']").val() + "' />");
				$("form#excelForm").append("<input type='hidden' name='gendNm' value='" + $("input[name='gendNm']").val() + "' />");
				$("form#excelForm").append("<input type='hidden' name='age' value='" + $("input[name='age']").val() + "' />");
				$("form#excelForm").append("<input type='hidden' name='regDt' value='" + $("input[name='regDt']").val() + "' />");
				$("form#excelForm").append("<input type='hidden' name='medicCareNm' value='" + $("input[name='medicCareNm']").val() + "' />");
				$("form#excelForm").append("<input type='hidden' name='mngUsrId' value='" + $("input[name='mngUsrId']").val() + "' />");
				$("form#excelForm").attr("action", "/cureExcelDownload.do");
				$("form#excelForm").submit();
			}else{
				alert("치료정보등록일자를 선택 하세요.");
			}
		},
		<%-- 회원 조회 --%>
		mstMbrSearchPopup = function(){
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
			$("input[name='age']").val(obj.AGE);
			$("input[name='regDt']").val(formatDate(obj.REG_DT));
			$("input[name='medicCareNm']").val(obj.MEDIC_CARE_NM);
			$("input[name='mngUsrId']").val(obj.MNG_USR_NM);

			getCslIdvList(obj.MBR_NO);
			getCslIspList(obj.MBR_NO);
			getCslAnmList(obj.MBR_NO);
			getCslHealList(obj.MBR_NO);
			
			$("button#idvSaveBtnNo").hide();
			$("button#idvSaveBtnYes").show();
			$("button#ispSaveButNo").hide();
			$("button#ispSaveButYes").show();
			$("button#anmSaveButNo").hide();
			$("button#anmSaveButYes").show();
			$("button#healSaveButNo").hide();
			$("button#healSaveButYes").show();
		},
		<%-- 사례관리 상담 이력 조회 --%>
		getCslIdvList = function(tagMbrNo){
			$.ajax({
				url : '/getClsIdvList.do',
				type : 'POST',
				data : {
					mbrNo : tagMbrNo
				},
				success : function(res){
					if(res.err != "Y"){
						$("div#idvList").html("");

						if(res.clsIdvList.length > 0){
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

							$(res.clsIdvList).each(function(idx, obj){
								inHtml = inHtml
									   + "<tr id=" + idx + ">"
									   + "	<td><div class='cell'>" + (idx + 1) + "</div></td>"
									   + "	<td><a href='javaScript:viewIdvRow(\"" + obj.CSL_NO + "\");' class='row_link'>" + obj.CSL_NO + "</a></td>"
									   + "	<td><div class='cell'>" + formatDate(obj.CSL_DT) + "</div></td>"
									   + "	<td><div class='cell'>" + obj.CSL_FM_TM + " ~ " + obj.CSL_TO_TM + "</div></td>"
									   + "	<td><div class='cell'>" + obj.CSL_TERM_TM + "</div></td>"
									   + "	<td class='txt-left'><div class='cell'>" + obj.CSL_SBJ + "</div></td>"
									   + "	<td class='txt-left'><div class='cell'>" + obj.CSL_TGT + "</div></td>"
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
		<%-- 사례관리 상담내용 삭제 --%>
		idvDel = function(tagCslNo, idx){
			$.ajax({
				url : '/ajaxClsIdvDel.do',
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
		<%-- 사례관리 상담내용 상세 조회 --%>
		viewIdvRow = function(tagCslNo){
			$.ajax({
				url : '/ajaxClsIdvInfo.do',
				type : 'POST',
				data : {
					cslNo : tagCslNo
				},
				success : function(res){
					if(res.err != "Y"){
						var dataInfo = res.clsIdvInfo;
						$("input[name='cslNo']").val(dataInfo.CSL_NO);
						$("input[name='cslNm']").val(dataInfo.CSL_NM);
						$("input[name='cslDt']").val(formatDate(dataInfo.CSL_DT));
						$("input[name='cslFmTm']").val(dataInfo.CSL_FM_TM);
						$("input[name='cslToTm']").val(dataInfo.CSL_TO_TM);
						$("input[name='cslTermTm']").val(dataInfo.CSL_TERM_TM);
						$("input[name='cslTgtCd']:radio[value='" + dataInfo.CSL_TGT_CD + "']").prop("checked", true);
						$("input[name='cslTpCd']:radio[value='" + dataInfo.CSL_TP_CD + "']").prop("checked", true);
						$("input[name='cslSbj']").val(dataInfo.CSL_SBJ);
						$("input[name='cslTgt']").val(dataInfo.CSL_TGT);
						$("select[name='rskaTpCd']").val(dataInfo.RSKA_TP_CD).prop("selected", true);
						$("select[name='rskbTpCd']").val(dataInfo.RSKB_TP_CD).prop("selected", true);
						$("select[name='rskcTpCd']").val(dataInfo.RSKC_TP_CD).prop("selected", true);
						$("input[name='rskSco']").val(dataInfo.RSK_SCO);
						$("input[name='crisisCounsel']").val(dataInfo.CRISIS_COUNSEL);
						$("select[name='ursCd']").val(dataInfo.URS_CD).prop("selected", true);
<%--
						$("select[name='cureCd']").val(dataInfo.CURE_CD).prop("selected", true);
						$("select[name='drugUseCd']").val(dataInfo.DRUG_USE_CD).prop("selected", true);
						$("select[name='oldActCd']").val(dataInfo.OLD_ACT_CD).prop("selected", true);
						$("select[name='actCd']").val(dataInfo.ACT_CD).prop("selected", true);
						$("select[name='aroundSuicideCd']").val(dataInfo.AROUND_SUICIDE_CD).prop("selected", true);
						$("select[name='suicidePlanCd']").val(dataInfo.SUICIDE_PLAN_CD).prop("selected", true);
						$("select[name='oldActWayCd']").val(dataInfo.OLD_ACT_WAY_CD).prop("selected", true);
						$("select[name='actWayCd']").val(dataInfo.ACT_WAY_CD).prop("selected", true);
--%>
						$("textarea[name='cslCtnt']").val(dataInfo.CSL_CTNT);
						$("textarea[name='cslRst']").val(dataInfo.CSL_RST);
						$("input[name='nxtCslDt']").val(formatDate(dataInfo.NXT_CSL_DT));
						$("input[name='nxtCslTm']").val(dataInfo.NXT_CSL_TM);
						$("textarea[name='nxtCslCtnt']").val(dataInfo.NXT_CSL_CTNT);

						if(dataInfo.fileList != undefined && dataInfo.fileList != ''){
							for(let i=0 ; i<dataInfo.fileList.length ; i++){
								$("div#fileName").html("<a href='javaScript:downloadFile(\"" + dataInfo.fileList[i].FILE_ID + "\", \"" + dataInfo.fileList[i].FILE_SEQ + "\");'>" + dataInfo.fileList[i].ORIGNL_FILE_NM + "</a>"
													 + "&nbsp;&nbsp;<a href='javaScript:deleteFile(\"fileName\");'>삭제</a>"
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
						
						$("button#excelNo").hide();
						$("button#excelYes").show();
						
						changRating();
					}else{
						console.log("ERR")
					}
				},
				error : function(xhr, status){}
			});
		},
		<%-- 사례관리 상담 신규 --%>
		newIdv = function(){
			$("input[name='cslNo']").val("");
			$("input[name='cslDt']").datepicker('setDate', 'today');
			$("input[name='cslFmTm']").val("09:00");
			$("input[name='cslToTm']").val("09:00");
			$("span#cslTermTm").text("0");
			$("input[name='cslTermTm']").val("0");
			$("input[name='cslTgtCd']:radio[value='10']").prop("checked", true);
			$("input[name='cslTpCd']:radio[value='20']").prop("checked", true);
			$("input[name='cslSbj']").val("");
			$("input[name='cslTgt']").val("");
			$("select[name='rskaTpCd']").val("0").prop("selected", true);
			$("select[name='rskbTpCd']").val("0").prop("selected", true);
			$("select[name='rskcTpCd']").val("0").prop("selected", true);
			$("span#ratingNum").text("0");
			$("input[name='crisisCounsel']").val("");
			$("select[name='ursCd']").val("").prop("selected", true);
<%--
			$("select[name='cureCd']").val("").prop("selected", true);
			$("select[name='drugUseCd']").val("").prop("selected", true);
			$("select[name='oldActCd']").val("").prop("selected", true);
			$("select[name='actCd']").val("").prop("selected", true);
			$("select[name='aroundSuicideCd']").val("").prop("selected", true);
			$("select[name='suicidePlanCd']").val("").prop("selected", true);
			$("select[name='oldActWayCd']").val("").prop("selected", true);
			$("select[name='actWayCd']").val("").prop("selected", true);
--%>
			$("textarea[name='cslCtnt']").val("");
			$("textarea[name='cslRst']").val("");
			$("input[name='nxtCslDt']").val("");
			$("input[name='nxtCslTm']").val("");
			$("textarea[name='nxtCslCtnt']").val("");
			$("div#fileName").text("");
			$("input[name='fileNameFlag']").val("N");
			$("input[name='file']").val("");
		},
		<%-- 사례관리 상담 저장 --%>
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
				alert("URS은 필수 입력 항목입니다.");
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
				url : '/ajaxClsIdvAdd.do',
				type : 'POST',
				processData : false,
				contentType : false,
				enctype : 'multipart/form-data',
				data : new FormData($("#cslForm")[0]),
				success : function(res){
					if(res.err != "Y"){
						alert(res.MSG);
						getCslIdvList($("input[name='mbrNo']").val());
					}else{
						alert(res.MSG);
					}
				},
				error : function(xhr, status){}
			});
		},
		<%-- ISP 수립 이력 조회 --%>
		getCslIspList = function(tagMbrNo){
			$.ajax({
				url : '/ajaxClsIspList.do',
				type : 'POST',
				data : {
					mbrNo : tagMbrNo
				},
				success : function(res){
					if(res.err != "Y"){
						$("div#ispList").html("");

						if(res.clsIspList.length > 0){
							var inHtml = "<table id='clsIspTableList'>"
									   + "	<colgroup>"
									   + "		<col style='width:46px'>"
									   + "		<col style='width:100px'>"
									   + "		<col style='width:150px'>"
									   + "		<col style='width:1300px'>"
									   + "		<col style='width:150px'>"
									   + "		<col>"
									   + "	</colgroup>"
									   + "	<tbody>";

							$(res.clsIspList).each(function(idx, obj){
								inHtml = inHtml
									   + "	<tr id='" + idx + "'>"
									   + "		<td><div class='cell'>" + (idx + 1) + "</div></td>"
									   + "		<td><div class='cell'><a href='javaScript:viewIspRow(\"" + obj.ISP_DT + "\", \"" + obj.MBR_NO + "\");' class='row_link'>" + formatDate(obj.ISP_DT) + "</a></div></td>"
									   + "		<td><div class='cell'>" + obj.MNG_TP_NM + "</div></td>"
									   + "		<td class='txt-left'>"
									   + "			<div class='cell'>" + obj.ISP_RST + "</div>"
									   + "		</td>"
									   + "		<td><div class='cell'>" + obj.CRE_NM + "</div></td>"
									   + "		<td>"
									   + "			<div class='cell'>"
									   + "				<button type='button' onclick='javaScript:removeIsp(\"" + obj.ISP_DT + "\", \"" + obj.MBR_NO + "\", \"" + idx + "\");' class='el-button el-button--danger el-button--mini is-plain' style='margin-left: 1px; padding: 4px 9px;'>"
									   + "					<span>삭제</span>"
									   + "				</button>"
									   + "			</div>"
									   + "		</td>"
									   + "	</tr>";
							});

							inHtml = inHtml
								   + "	</tbody>"
								   + "</table>";

							$("div#ispList").html(inHtml);
						}else{
							$("div#ispList").html("<div class='no-data'>조회된 데이터가 없습니다.</div>");
						}

						newIsp();
					}else{
						console.log("ERR")
					}
				},
				error : function(xhr, status){}
			});
		},
		<%-- ISP 상세 보기 --%>
		viewIspRow = function(tagIspDt, tagMbrNo){
			$.ajax({
				url : '/ajaxClsIspInfo.do',
				type : 'POST',
				data : {
					mbrNo : tagMbrNo,
					ispDt : tagIspDt
				},
				success : function(res){
					if(res.ispInfo != null){
						var resultInfo = res.ispInfo;

						$("input[name='newFlag']").val("N");
						$("input[name='choiceMbrNo']").val(tagMbrNo);
						$("input[name='choiceIspDt']").val(tagIspDt);
						$("input[name='ispDt']").val(formatDate(resultInfo.ISP_DT));
						$("input[name='mngTpNm']").val(resultInfo.MNG_TP_NM);
						$("input[name='mngTpCd']").val(resultInfo.MNG_TP_CD);

						if(resultInfo.LINK_CD != undefined && resultInfo.LINK_CD != ""){
							$("input[name='linkCd']").attr("checked", false);
							var splVal = $.trim(resultInfo.LINK_CD).replace(/\[/g, "").replace(/\]/g, "");

							if(splVal.indexOf(",") >= 0){
								$.each(splVal.split(","), function(i, e){
									$("input[name='linkCd']").each(function(i){
										if($.trim(e) == $(this).val()){
											$(this).prop("checked", true);
											return;
										}
									});
								});
							}else{
								$("input[name='linkCd']").each(function(i){
									if(splVal == $(this).val()){
										$(this).prop("checked", true);
										return;
									}
								});
							}
						}else{
							$("input[name='linkCd']").attr("checked", false);
						}

						$("select[name='evlItmSco01']").val(resultInfo.EVL_ITM_SCO01).prop("selected", true);
						$("input[name='evlItmLnk01']").val(resultInfo.EVL_ITM_LNK01);
						$("select[name='evlItmSco02']").val(resultInfo.EVL_ITM_SCO02).prop("selected", true);
						$("input[name='evlItmLnk02']").val(resultInfo.EVL_ITM_LNK02);
						$("select[name='evlItmSco03']").val(resultInfo.EVL_ITM_SCO03).prop("selected", true);
						$("input[name='evlItmLnk03']").val(resultInfo.EVL_ITM_LNK03);
						$("select[name='evlItmSco04']").val(resultInfo.EVL_ITM_SCO04).prop("selected", true);
						$("input[name='evlItmLnk04']").val(resultInfo.EVL_ITM_LNK04);
//						$("select[name='evlItmSco05']").val(resultInfo.EVL_ITM_SCO05).prop("selected", true);
//						$("input[name='evlItmLnk05']").val(resultInfo.EVL_ITM_LNK05);
						$("select[name='evlItmSco06']").val(resultInfo.EVL_ITM_SCO06).prop("selected", true);
						$("input[name='evlItmLnk06']").val(resultInfo.EVL_ITM_LNK06);
						$("select[name='evlItmSco07']").val(resultInfo.EVL_ITM_SCO07).prop("selected", true);
						$("input[name='evlItmLnk07']").val(resultInfo.EVL_ITM_LNK07);
						$("select[name='evlItmSco08']").val(resultInfo.EVL_ITM_SCO08).prop("selected", true);
						$("input[name='evlItmLnk08']").val(resultInfo.EVL_ITM_LNK08);
						$("select[name='evlItmSco09']").val(resultInfo.EVL_ITM_SCO09).prop("selected", true);
						$("input[name='evlItmLnk09']").val(resultInfo.EVL_ITM_LNK09);
						$("select[name='evlItmSco10']").val(resultInfo.EVL_ITM_SCO10).prop("selected", true);
						$("input[name='evlItmLnk10']").val(resultInfo.EVL_ITM_LNK10);
						$("select[name='evlItmSco11']").val(resultInfo.EVL_ITM_SCO11).prop("selected", true);
						$("input[name='evlItmLnk11']").val(resultInfo.EVL_ITM_LNK11);
						$("select[name='evlItmSco12']").val(resultInfo.EVL_ITM_SCO12).prop("selected", true);
						$("input[name='evlItmLnk12']").val(resultInfo.EVL_ITM_LNK12);
						$("select[name='evlItmSco13']").val(resultInfo.EVL_ITM_SCO13).prop("selected", true);
						$("input[name='evlItmLnk13']").val(resultInfo.EVL_ITM_LNK13);
						$("select[name='evlItmSco14']").val(resultInfo.EVL_ITM_SCO14).prop("selected", true);
						$("input[name='evlItmLnk14']").val(resultInfo.EVL_ITM_LNK14);
						$("select[name='evlItmSco15']").val(resultInfo.EVL_ITM_SCO15).prop("selected", true);
						$("input[name='evlItmLnk15']").val(resultInfo.EVL_ITM_LNK15);
						$("select[name='evlItmSco16']").val(resultInfo.EVL_ITM_SCO16).prop("selected", true);
						$("input[name='evlItmLnk16']").val(resultInfo.EVL_ITM_LNK16);
						$("select[name='evlItmSco17']").val(resultInfo.EVL_ITM_SCO17).prop("selected", true);
						$("input[name='evlItmLnk17']").val(resultInfo.EVL_ITM_LNK17);
						$("select[name='evlItmSco18']").val(resultInfo.EVL_ITM_SCO18).prop("selected", true);
						$("input[name='evlItmLnk18']").val(resultInfo.EVL_ITM_LNK18);
						$("select[name='evlItmSco19']").val(resultInfo.EVL_ITM_SCO19).prop("selected", true);
						$("input[name='evlItmLnk19']").val(resultInfo.EVL_ITM_LNK19);
						$("select[name='evlItmSco20']").val(resultInfo.EVL_ITM_SCO20).prop("selected", true);
						$("input[name='evlItmLnk20']").val(resultInfo.EVL_ITM_LNK20);
						$("select[name='evlItmSco21']").val(resultInfo.EVL_ITM_SCO21).prop("selected", true);
						$("input[name='evlItmLnk21']").val(resultInfo.EVL_ITM_LNK21);
						$("select[name='evlItmSco22']").val(resultInfo.EVL_ITM_SCO22).prop("selected", true);
						$("input[name='evlItmLnk22']").val(resultInfo.EVL_ITM_LNK22);
						$("select[name='evlItmSco23']").val(resultInfo.EVL_ITM_SCO23).prop("selected", true);
						$("input[name='evlItmLnk23']").val(resultInfo.EVL_ITM_LNK23);
						$("select[name='evlItmSco24']").val(resultInfo.EVL_ITM_SCO24).prop("selected", true);
						$("input[name='evlItmLnk24']").val(resultInfo.EVL_ITM_LNK24);
						$("select[name='evlItmSco25']").val(resultInfo.EVL_ITM_SCO25).prop("selected", true);
						$("input[name='evlItmLnk25']").val(resultInfo.EVL_ITM_LNK25);

						$("textarea[name='ispRst']").val(resultInfo.ISP_RST);
						$("textarea[name='tgtCtnt']").val(resultInfo.TGT_CTNT);

						for(var i=1 ; i<=20 ; i++){
							if(i != 14){
								var tagName = "evlItmSco";
								if(i < 10){
									tagName = tagName + "0" + i;
								}else{
									tagName = tagName + i;
								}

								changEvlItemSco($("select[name='" + tagName + "']"));
							}
						}
						
						$("#ispExcelNo").hide();
						$("#ispExcelYes").show();
					}
				},
				error : function(xhr, status){}
			});
		},
		<%-- ISP 선택 삭제 --%>
		removeIsp = function(tagIspDt, tagMbrNo, idx){
			$.ajax({
				url : '/ajaxClsIspDel.do',
				type : 'POST',
				data : {
					mbrNo : tagMbrNo,
					ispDt : tagIspDt
				},
				success : function(res){
					if(res.err != "Y"){
						$("#clsIspTableList tr").each(function(){
							if($(this).attr("id") == idx){
								$(this).remove();
							}
						});

						if($("#clsIspTableList tr").length <= 0){
							$("div#ispList").html("<div class='no-data'>조회된 데이터가 없습니다.</div>");
						}
					}else{
						console.log(res.MSG);
					}
				},
				error : function(xhr, status){}
			});
		},
		<%-- ISP 저장 --%>
		saveIsp = function(){
			if($("input[name='mbrNo']").val() == ""){
				alert("회원을 선택하세요.");
				$("input[name='mbrNo']").focus();
				return;
			}
			if($("input[name='ispDt']").val() == ""){
				alert("사정일자(ISP)를 선택하세요.");
				$("input[name='ispDt']").focus();
				return;
			}

			$.ajax({
				url : '/ajaxCslIspAdd.do',
				type : 'POST',
				data : $('#cslForm').serialize(),
				success : function(res){
					if(res.err != "Y"){
						alert(res.MSG);
						getCslIspList($("input[name='mbrNo']").val());
					}else{
						alert(res.MSG);
					}
				},
				error : function(xhr, status){}
			});
		},
		<%-- ISP 신규 작성 --%>
		newIsp = function(){
			$("input[name='newFlag']").val("Y");
			$("input[name='ispDt']").datepicker('setDate', 'today');
			$("input[name='linkCd']").attr("checked", false);
			$("input[name='mngTpNm']").val("일시관리");
			$("input[name='mngTpCd']").val("10");
			$("select[name='evlItmSco01']").val("0").prop("selected", true);
			$("input[name='evlItmLnk01']").val("");
			$("select[name='evlItmSco02']").val("0").prop("selected", true);
			$("input[name='evlItmLnk02']").val("");
			$("select[name='evlItmSco03']").val("0").prop("selected", true);
			$("input[name='evlItmLnk03']").val("");
			$("select[name='evlItmSco04']").val("0").prop("selected", true);
			$("input[name='evlItmLnk04']").val("");
//			$("select[name='evlItmSco05']").val("0").prop("selected", true);
//			$("input[name='evlItmLnk05']").val("");
			$("select[name='evlItmSco06']").val("0").prop("selected", true);
			$("input[name='evlItmLnk06']").val("");
			$("select[name='evlItmSco07']").val("0").prop("selected", true);
			$("input[name='evlItmLnk07']").val("");
			$("select[name='evlItmSco08']").val("0").prop("selected", true);
			$("input[name='evlItmLnk08']").val("");
			$("select[name='evlItmSco09']").val("0").prop("selected", true);
			$("input[name='evlItmLnk09']").val("");
			$("select[name='evlItmSco10']").val("0").prop("selected", true);
			$("input[name='evlItmLnk10']").val("");
			$("select[name='evlItmSco11']").val("0").prop("selected", true);
			$("input[name='evlItmLnk11']").val("");
			$("select[name='evlItmSco12']").val("0").prop("selected", true);
			$("input[name='evlItmLnk12']").val("");
			$("select[name='evlItmSco13']").val("0").prop("selected", true);
			$("input[name='evlItmLnk13']").val("");
			$("select[name='evlItmSco14']").val("1").prop("selected", true);
			$("input[name='evlItmLnk14']").val("");
			$("select[name='evlItmSco15']").val("0").prop("selected", true);
			$("input[name='evlItmLnk15']").val("");
			$("select[name='evlItmSco16']").val("0").prop("selected", true);
			$("input[name='evlItmLnk16']").val("");
			$("select[name='evlItmSco17']").val("0").prop("selected", true);
			$("input[name='evlItmLnk17']").val("");
			$("select[name='evlItmSco18']").val("0").prop("selected", true);
			$("input[name='evlItmLnk18']").val("");
			$("select[name='evlItmSco19']").val("0").prop("selected", true);
			$("input[name='evlItmLnk19']").val("");
			$("select[name='evlItmSco20']").val("0").prop("selected", true);
			$("input[name='evlItmLnk20']").val("");
			$("select[name='evlItmSco21']").val("1").prop("selected", true);
			$("input[name='evlItmLnk21']").val("");
			$("select[name='evlItmSco22']").val("1").prop("selected", true);
			$("input[name='evlItmLnk22']").val("");
			$("select[name='evlItmSco23']").val("1").prop("selected", true);
			$("input[name='evlItmLnk23']").val("");
			$("select[name='evlItmSco24']").val("1").prop("selected", true);
			$("input[name='evlItmLnk24']").val("");
			$("select[name='evlItmSco25']").val("1").prop("selected", true);
			$("input[name='evlItmLnk25']").val("");

			$("textarea[name='ispRst']").val("");
			$("textarea[name='tgtCtnt']").val("");

			for(var i=1 ; i<=20 ; i++){
				if(i != 14){
					var tagName = "evlItmSco";
					if(i < 10){
						tagName = tagName + "0" + i;
					}else{
						tagName = tagName + i;
					}

					changEvlItemSco($("select[name='" + tagName + "']"));
				}
			}
		},
		<%-- 병력정보 이력 조회 --%>
		getCslAnmList = function(tagMbrNo){
			$.ajax({
				url : '/ajaxCslAnmList.do',
				type : 'POST',
				data : {
					mbrNo : tagMbrNo
				},
				success : function(res){
					var resultObj = res.cslAnmList;

					$("div#anmList").html("");
					if(resultObj != null && resultObj.length > 0){
						var inHtml = "<table id='anmEvlTableList'>"
								   + "	<colgroup>"
								   + "		<col style='width:46px'>"
								   + "		<col style='width:130px'>"
								   + "		<col style='width:130px'>"
								   + "		<col style='width:130px'>"
								   + "		<col style='width:1180px'>"
								   + "		<col style='width:120px'>"
								   + "		<col>"
								   + "	</colgroup>"
								   + "	<tbody>";

						$(resultObj).each(function(idx, obj){
							inHtml = inHtml
								   + "	<tr id='" + idx + "'>"
								   + "		<td><div class='cell'>" + (idx + 1) + "</div></td>"
								   + "		<td><div class='cell'><a href='javaScript:viewAnmRow(\"" + obj.CSL_NO + "\");' class='row_link'>" + formatDate(obj.CRE_DT) + "</a></div></td>"
								   + "		<td><div class='cell'>" + formatDate(obj.SUD_CRE_DT) + "</div></td>"
								   + "		<td><div class='cell'>" + formatDate(obj.DEV_CRE_DT) + "</div></td>"
								   + "		<td class='txt-left'><div class='cell'>" + obj.SUD_CTNT + "</div></td>"
								   + "		<td><div class='cell'>" + obj.CRE_NM + "</div></td>"
								   + "		<td>"
								   + "			<div class='cell'>"
								   + "				<button type='button' onclick='javaScript:removeAnm(\"" + obj.CSL_NO + "\");' class='el-button el-button--danger el-button--mini is-plain' slot='reference' style='margin-left: 1px; padding: 4px 9px;'>"
								   + "					<i class='el-icon-delete'></i> <span>삭제</span>"
								   + "				</button>"
								   + "			</div>"
								   + "		</td>"
								   + "	</tr>";
						});

						inHtml = inHtml
							   + "	</tbody>"
							   + "</table>";
						$("div#anmList").html(inHtml);
					}else{
						$("div#anmList").html("<div class='no-data'>조회된 데이터가 없습니다.</div>");
					}

					newAnm();
				},
				error : function(xhr, status){}
			});
		},
		<%-- 병력정보 등록 --%>
		saveAnm = function(){
			if($("input[name='mbrNo']").val() == ""){
				alert("회원을 선택하세요.");
				return;
			}
			if($("select[name='fstDrugCd']").val() == ""){
				alert("최초사용약물은 필수 입력 항목입니다.");
				$("select[name='fstDrugCd']").focus();						return;
			}
			if($("select[name='mainDrugCd']").val() == ""){
				alert("주요사용약물은 필수 입력 항목입니다.");
				$("select[name='mainDrugCd']").focus();						return;
			}
			if($("input[name='fstAge']").val() == ""){
				alert("최초사용시기는 필수 입력 항목입니다.");
				$("input[name='fstAge']").focus();							return;
			}
			if($("input[name='lstAge']").val() == ""){
				alert("마지막사용시기는 필수 입력 항목입니다.");
				$("input[name='lstAge']").focus();							return;
			}
			if($("input[name='useTerm']").val() == ""){
				alert("사용기간은 0보다 값이 커야 합니다.");
				$("input[name='useTerm']").focus();							return;
			}
			if($("select[name='useFrqCd']").val() == ""){
				alert("사용빈도는 필수 입력 항목입니다.");
				$("select[name='useFrqCd']").focus();						return;
			}
			if($("select[name='useCauCd']").val() == ""){
				alert("사용원인은 필수 입력 항목입니다.");
				$("select[name='useCauCd']").focus();						return;
			}
			if($("#lawPbmCd").val() == null || $("#lawPbmCd").val() == ""){
				alert("약물관련 법적문제는 필수 입력 항목입니다.");
				$("select[name='lawPbmCd']").focus();						return;
			}
			if($("input[name='physPbm']").val() == ""){
				alert("신체적 건강문제는 필수 입력 항목입니다.");
				$("input[name='physPbm']").focus();							return;
			}
			if($("select[name='sprtPbmCd']").val() == ""){
				alert("정신적 건강문제는 필수 입력 항목입니다.");
				$("select[name='sprtPbmCd']").focus();						return;
			}

			$.ajax({
				url : '/ajaxCslAnmAdd.do',
				type : 'POST',
				data : $('#cslForm').serialize(),
				success : function(res){
					if(res.err != 'Y'){
						alert("병력정보 " + res.MSG + " 완료");
						getCslAnmList($("input[name='mbrNo']").val());
					}else{
						alert(res.MSG);
					}
				},
				error : function(xhr, status){}
			});
		},
		<%-- 병력정보 상세 조회 --%>
		viewAnmRow = function(tagCslNo){
			$.ajax({
				url : '/ajaxCslAnmInfo.do',
				type : 'POST',
				data : {
					mbrNo : $("input[name='mbrNo']").val(),
					cslNo : tagCslNo
				},
				success : function(res){
					var resultObj = res.cslAnminfo;

					if(resultObj != "" && resultObj != null){
						$("input[name='cslNo']").val(resultObj.CSL_NO);
						$("input[name='sudCreDt']").val(resultObj.SUD_CRE_DT);
						$("input[name='devCreDt']").val(resultObj.DEV_CRE_DT);

						$("select[name='fstDrugCd']").val(resultObj.FST_DRUG_CD).prop("selected", true);
						$("input[name='fstDrug']").val(resultObj.FST_DRUG);
						$("select[name='mainDrugCd']").val(resultObj.MAIN_DRUG_CD).prop("selected", true);
						$("input[name='mainDrug']").val(resultObj.MAIN_DRUG);
						$("input[name='fstAge']").val(resultObj.FST_AGE);
						$("input[name='lstAge']").val(resultObj.LST_AGE);
						$("input[name='useTerm']").val(resultObj.USE_TERM);
						$("select[name='useFrqCd']").val(resultObj.USE_FRQ_CD).prop("selected", true);
						$("select[name='useCauCd']").val(resultObj.USE_CAU_CD).prop("selected", true);
						if(resultObj.USE_CAU_CD == "ZZ"){
							$("input[name='useCauEtc']").attr("disabled", false);
							$("input[name='useCauEtc']").val(resultObj.USE_CAU_ETC);
						}else{
							$("input[name='useCauEtc']").attr("disabled", true);
							$("input[name='useCauEtc']").val("");
						}

						var pmbFlag = false;
						$("select[name='lawPbmCd'] option").each(function(i, e){
							$(e).prop("selected", false);
						});
						if(resultObj.LAW_PBM_CD.indexOf(",") >= 0){
							$.each(resultObj.LAW_PBM_CD.split(","), function(i, e){
								var cdVal = $.trim(e).replace(/\[/g, "").replace(/\]/g, "");
								$("select#lawPbmCd option[value=" + cdVal + "]").prop('selected', true);

								if(cdVal == "ZZ"){
									pmbFlag = true;
								}
							});
						}else{
							var cdVal = $.trim(resultObj.LAW_PBM_CD).replace(/\[/g, "").replace(/\]/g, "");
							$("select[name='lawPbmCd']").val(cdVal).prop("selected", true);

							if(cdVal == "ZZ"){
								pmbFlag = true;
							}
						}

						multipleSelect = $("select[name='lawPbmCd']")[0].sumo.reload();

						if(pmbFlag){
							$("input[name='lawPbmEtc']").attr("disabled", false);
							$("input[name='lawPbmEtc']").val(resultObj.LAW_PBM_ETC);
						}else{
							$("input[name='lawPbmEtc']").attr("disabled", true);
							$("input[name='lawPbmEtc']").val("");
						}

						$("input[name='physPbm']").val(resultObj.PHYS_PBM);
						$("select[name='sprtPbmCd']").val(resultObj.SPRT_PBM_CD).prop("selected", true);
						if(resultObj.SPRT_PBM_CD == "ZZ"){
							$("input[name='sprtPbmEtc']").attr("disabled", false);
							$("input[name='sprtPbmEtc']").val(resultObj.SPRT_PBM_ETC);
						}else{
							$("input[name='sprtPbmEtc']").attr("disabled", true);
							$("input[name='sprtPbmEtc']").val("");
						}

						$("select[name='cureoffExp']").val(resultObj.CUREOFF_EXP).prop("selected", true);
						$("input[name='cureoffNum']").val(resultObj.CUREOFF_NUM);
						$("input[name='cureoffDay']").val(resultObj.CUREOFF_DAY);
						$("input[name='cureoffReason']").val(resultObj.CUREOFF_REASON);

						$("input[name='sudIndt']").val(formatDate(resultObj.SUD_INDT));
						$("input[name='sudAge']").val(resultObj.SUD_AGE);
						$("select[name='sudTypeCd']").val(resultObj.SUD_TYPE_CD).prop("selected", true);
						$("select[name='sudSoulCd']").val(resultObj.SUD_SOUL_CD).prop("selected", true);
						$("select[name='sudWayCd']").val(resultObj.SUD_WAY_CD).prop("selected", true);
						if(resultObj.SUD_WAY_CD == "ZZ"){
							$("input[name='sudWayEtc']").attr("disabled", false);
							$("input[name='sudWayEtc']").val(resultObj.SUD_WAY_ETC);
						}else{
							$("input[name='sudWayEtc']").attr("disabled", true);
							$("input[name='sudWayEtc']").val("");
						}
						$("textarea[name='sudCtnt']").val(resultObj.SUD_CTNT);

						$("select[name='devBabyPregCd']").val(resultObj.DEV_BABY_PREG_CD).prop("selected", true);
						$("select[name='devBabyDevCd']").val(resultObj.DEV_BABY_DEV_CD).prop("selected", true);
						$("select[name='devBabyNurtCd']").val(resultObj.DEV_BABY_NURT_CD).prop("selected", true);
						$("select[name='devChildDiscCd']").val(resultObj.DEV_CHILD_DISC_CD).prop("selected", true);
						$("select[name='devChildLearnCd']").val(resultObj.DEV_CHILD_LEARN_CD).prop("selected", true);
						$("select[name='devChildRelationCd']").val(resultObj.DEV_CHILD_RELATION_CD).prop("selected", true);
						$("input[name='devChildTec']").val(resultObj.DEV_CHILD_TEC);
						$("select[name='devTeenLearnCd']").val(resultObj.DEV_TEEN_LEARN_CD).prop("selected", true);
						$("select[name='devTeenRelationCd']").val(resultObj.DEV_TEEN_RELATION_CD).prop("selected", true);
						$("select[name='devTeenUniCd']").val(resultObj.DEV_TEEN_UNI_CD).prop("selected", true);
						$("input[name='devTeenEtc']").val(resultObj.DEV_TEEN_ETC);
						$("select[name='devAdulRelationCd']").val(resultObj.DEV_ADUL_RELATION_CD).prop("selected", true);
						$("select[name='devAdulSexCd']").val(resultObj.DEV_ADUL_SEX_CD).prop("selected", true);
						$("input[name='devAdulEtc']").val(resultObj.DEV_ADUL_ETC);
						
						$("#cslAnmExcelNo").hide();
						$("#cslAnmExcelYes").show();
					}else{
						newAnm();
						$("#cslAnmExcelNo").show();
						$("#cslAnmExcelYes").hide();
					}
				},
				error : function(xhr, status){}
			});
		},
		<%-- 병력정보 신규 --%>
		newAnm = function(){
			$("input[name='cslNo']").val("");
			$("input[name='sudCreDt']").val("");
			$("input[name='devCreDt']").val("");

			$("select[name='fstDrugCd']").val("");
			$("input[name='fstDrug']").val("");
			$("select[name='mainDrugCd']").val("");
			$("input[name='mainDrug']").val("");
			$("input[name='fstAge']").val("");
			$("input[name='lstAge']").val("");
			$("input[name='useTerm']").val("");
			$("select[name='useFrqCd']").val("");
			$("select[name='useCauCd']").val("");
			$("input[name='useCauEtc']").attr("disabled", true);
			$("input[name='useCauEtc']").val("");

			$("select[name='lawPbmCd'] option").each(function(i, e){
				$(e).prop("selected", false);
			});
			$("input[name='lawPbmEtc']").attr("disabled", true);
			$("input[name='lawPbmEtc']").val("");

			$("input[name='physPbm']").val("");
			$("select[name='sprtPbmCd']").val("");
			$("input[name='sprtPbmEtc']").attr("disabled", true);
			$("input[name='sprtPbmEtc']").val("");

			$("select[name='cureoffExp']").val("");
			$("input[name='cureoffNum']").val("");
			$("input[name='cureoffDay']").val("");
			$("input[name='cureoffReason']").val("");

			$("input[name='sudIndt']").val("");
			$("input[name='sudAge']").val("");
			$("select[name='sudTypeCd']").val("");
			$("select[name='sudSoulCd']").val("");
			$("select[name='sudWayCd']").val("");
			$("input[name='sudWayEtc']").attr("disabled", true);
			$("input[name='sudWayEtc']").val("");
			$("textarea[name='sudCtnt']").val("");

			$("select[name='devBabyPregCd']").val("");
			$("select[name='devBabyDevCd']").val("");
			$("select[name='devBabyNurtCd']").val("");
			$("select[name='devChildDiscCd']").val("");
			$("select[name='devChildLearnCd']").val("");
			$("select[name='devChildRelationCd']").val("");
			$("input[name='devChildTec']").val("");
			$("select[name='devTeenLearnCd']").val("");
			$("select[name='devTeenRelationCd']").val("");
			$("select[name='devTeenUniCd']").val("");
			$("input[name='devTeenEtc']").val("");
			$("select[name='devAdulRelationCd']").val("");
			$("select[name='devAdulSexCd']").val("");
			$("input[name='devAdulEtc']").val("");
		},
		<%-- 병력정보 삭제 --%>
		removeAnm = function(tagCslNo){
			$.ajax({
				url : '/ajaxCslAnmDel.do',
				type : 'POST',
				data : {
					mbrNo : $("input[name='mbrNo']").val(),
					cslNo : tagCslNo
				},
				success : function(res){
					if(res.err != "Y"){
						getCslAnmList($("input[name='mbrNo']").val());
						newAnm();
					}else{
						console.log(res.MSG);
					}
				},
				error : function(xhr, status){}
			});
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
		<%-- 위기분류척도 점수 계산--%>
		changRating = function(){
			var ratingNum = 0;

			ratingNum += Number($("select[name='rskaTpCd'] option:selected").attr("rating"));
			ratingNum += Number($("select[name='rskbTpCd'] option:selected").attr("rating"));
			ratingNum += Number($("select[name='rskcTpCd'] option:selected").attr("rating"));

			$("#ratingNum").text(ratingNum);
			$("input[name='rskSco']").val(ratingNum);
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
		<%-- ISP수립 문항 수정 --%>
		changEvlItemSco = function(obj){
			var tagName = $(obj).attr("name");
			var rating = $("select[name='" + tagName + "'] option:selected").attr("rating");

			if(rating == 4){
				$("th#" + tagName).attr("class", "bg-pr");
			}else if(rating >= 2 && rating <= 3){
				$("th#" + tagName).attr("class", "bg-og");
			}else{
				$("th#" + tagName).attr("class", "bg-pk");
			}

			checkMngTp();
		},
		<%-- 관리구분명 수정 --%>
		checkMngTp = function(){
			var maxRating = 1;
			var mngTpCd = Number($("input[name='mngTpCd']").val());

<c:if test="${mngTpList ne null && mngTpList ne ''}">
			var mngTpNmList = [
	<c:forEach var="result" items="${mngTpList}" varStatus="status">
				'<c:out value="${result.CD_NM}" />',
	</c:forEach>
			];
</c:if>

			$("table#mngTpTable").find("select").each(function(idx){
				var tagName = $(this).attr("name");
				if($("select[name='" + tagName + "'] option:selected").attr("rating") != undefined){
					var rating = $("select[name='" + tagName + "'] option:selected").attr("rating");

					if(rating > maxRating && rating < 5){
						maxRating = rating;
					}
				}
			});

			$("input[name='mngTpCd']").val(maxRating * 10);
			$("input[name='mngTpNm']").val(mngTpNmList[maxRating - 1]);
		},
		dtCheck = function(defaultDay, tagDay, tagVal){
			var fromTag = $("input[name='" + defaultDay + "']").val();
			var toTag = $("input[name='" + tagDay + "']").val();

			if(fromTag != "" && toTag != ""){
				var resultNum = checkDay(fromTag.replace(/-/g, ""), toTag.replace(/-/g, ""));
				$("input[name='" + tagVal + "']").val(resultNum);
			}
		},
		<%-- 치료재활정보 이력 조회 --%>
		getCslHealList = function(tagMbrNo){
			$.ajax({
				url : '/ajaxCslHealList.do',
				type : 'POST',
				data : {
					mbrNo : tagMbrNo
				},
				success : function(res){
					var resultObj = res.cslHealList;

					$("div#healList").html("");
					if(resultObj != null && resultObj.length > 0){
						var inHtml = "<table id='healTableList'>"
								   + "	<colgroup>"
								   + "		<col style='width:46px'>"
								   + "		<col style='width:130px'>"
								   + "		<col style='width:130px'>"
								   + "		<col style='width:130px'>"
								   + "		<col style='width:1180px'>"
								   + "		<col style='width:120px'>"
								   + "		<col>"
								   + "	</colgroup>"
								   + "	<tbody>";

						$(resultObj).each(function(idx, obj){
							inHtml = inHtml
								   + "	<tr id='" + idx + "'>"
								   + "		<td><div class='cell'>" + (idx + 1) + "</div></td>"
								   + "		<td><div class='cell'><a href='javaScript:viewHealRow(\"" + obj.CSL_NO + "\");' class='row_link'>" + formatDate(obj.CRE_DT) + "</a></div></td>"
								   + "		<td><div class='cell'>" + formatDate(obj.JOB_CRE_DT) + "</div></td>"
								   + "		<td><div class='cell'>" + formatDate(obj.HEAL_CRE_DT) + "</div></td>"
								   + "		<td class='txt-left'><div class='cell'>" + obj.ORGAN_CTNT + "</div></td>"
								   + "		<td><div class='cell'>" + obj.CRE_NM + "</div></td>"
								   + "		<td>"
								   + "			<div class='cell'>"
								   + "				<button type='button' onclick='javaScript:removeHeal(\"" + obj.CSL_NO + "\");' class='el-button el-button--danger el-button--mini is-plain' slot='reference' style='margin-left: 1px; padding: 4px 9px;'>"
								   + "					<i class='el-icon-delete'></i> <span>삭제</span>"
								   + "				</button>"
								   + "			</div>"
								   + "		</td>"
								   + "	</tr>";
						});

						inHtml = inHtml
							   + "	</tbody>"
							   + "</table>";
						$("div#healList").html(inHtml);
					}else{
						$("div#healList").html("<div class='no-data'>조회된 데이터가 없습니다.</div>");
					}

					newHeal();
				},
				error : function(xhr, status){}
			});
		},
		<%-- 치료재활정보 폼 초기화 --%>
		newHeal = function(){
			$("input[name='cslNo']").val("");
			$("input[name='jobCreDt']").val("");
			$("input[name='healCreDt']").val("");

			$("input[name='diagName']").val("");
			$("input[name='attDt']").val("");
			$("input[name='presDrug']").val("");
			$("select[name='conformCd']").val("");
			$("input[name='dosage']").val("");
			$("select[name='mediIllCd']").val("");

			$("input[name='jobStDt']").val("");
			$("input[name='jobEndDt']").val("");
			$("input[name='jobTerm']").val("");
			$("select[name='jobFormCd']").val("");
			$("select[name='jobTypeCd']").val("");
			$("input[name='jobTypeEtc']").attr("disabled", true);
			$("input[name='jobTypeEtc']").val("");
			$("input[name='jobIncome']").val("");
			$("textarea[name='jobResign']").val("");

			$("input[name='healStDt']").val("");
			$("input[name='healEndDt']").val("");
			$("input[name='healTerm']").val("");
			$("select[name='organFormCd']").val("");
			$("input[name='organName']").val("");
			$("textarea[name='organCtnt']").val("");
		},
		<%-- 치료재활정보 상세 보기 --%>
		viewHealRow = function(tagCslNo){
			$.ajax({
				url : '/ajaxCslHealInfo.do',
				type : 'POST',
				data : {
					mbrNo : $("input[name='mbrNo']").val(),
					cslNo : tagCslNo
				},
				success : function(res){
					var resultObj = res.cslHealinfo;

					if(resultObj != "" && resultObj != null){
						$("input[name='cslNo']").val(resultObj.CSL_NO);
						$("input[name='jobCreDt']").val(resultObj.JOB_CRE_DT);
						$("input[name='healCreDt']").val(resultObj.HEAL_CRE_DT);

						$("input[name='diagName']").val(resultObj.DIAG_NAME);
						$("input[name='attDt']").val(formatDate(resultObj.ATT_DT));
						$("input[name='presDrug']").val(resultObj.PRES_DRUG);
						$("select[name='conformCd']").val(resultObj.CONFORM_CD).prop("selected", true);
						$("input[name='dosage']").val(resultObj.DOSAGE);
						$("select[name='mediIllCd']").val(resultObj.MEDI_ILL_CD).prop("selected", true);

						$("input[name='jobStDt']").val(formatDate(resultObj.JOB_ST_DT));
						$("input[name='jobEndDt']").val(formatDate(resultObj.JOB_END_DT));
						$("input[name='jobTerm']").val(resultObj.JOB_TERM);
						$("select[name='jobFormCd']").val(resultObj.JOB_FORM_CD).prop("selected", true);
						$("select[name='jobTypeCd']").val(resultObj.JOB_TYPE_CD).prop("selected", true);
						if(resultObj.JOB_TYPE_CD == "ZZ"){
							$("input[name='jobTypeEtc']").attr("disabled", false);
							$("input[name='jobTypeEtc']").val(resultObj.JOB_TYPE_ETC);
						}else{
							$("input[name='jobTypeEtc']").attr("disabled", true);
							$("input[name='jobTypeEtc']").val("");
						}
						$("input[name='jobIncome']").val(resultObj.JOB_INCOME);
						$("textarea[name='jobResign']").val(resultObj.JOB_RESIGN);

						$("input[name='healStDt']").val(formatDate(resultObj.HEAL_ST_DT));
						$("input[name='healEndDt']").val(formatDate(resultObj.HEAL_END_DT));
						$("input[name='healTerm']").val(resultObj.HEAL_TERM);
						$("select[name='organFormCd']").val(resultObj.ORGAN_FORM_CD).prop("selected", true);
						$("input[name='organName']").val(resultObj.ORGAN_NAME);
						$("textarea[name='organCtnt']").val(resultObj.ORGAN_CTNT);
						
						$("#cureExcelNo").hide();
						$("#cureExcelYes").show();
					}else{
						newHeal();
						$("#cureExcelNo").show();
						$("#cureExcelYes").hide();
					}
				},
				error : function(xhr, status){}
			});
		},
		<%-- 치료재활정보 삭제 --%>
		removeHeal = function(tagCslNo){
			$.ajax({
				url : '/ajaxCslHealDel.do',
				type : 'POST',
				data : {
					mbrNo : $("input[name='mbrNo']").val(),
					cslNo : tagCslNo
				},
				success : function(res){
					if(res.err != "Y"){
						getCslHealList($("input[name='mbrNo']").val());
						newHeal();
					}else{
						console.log(res.MSG);
					}
				},
				error : function(xhr, status){}
			});
		},
		<%-- 치료재활정보 저장 --%>
		saveHeal = function(){
			if($("input[name='mbrNo']").val() == ""){
				alert("회원을 선택하세요.");
				return;
			}
			if($("input[name='diagName']").val() == ""){
				alert("진단명은 필수 입력 항목입니다.");
				$("input[name='diagName']").focus();						return;
			}
			if($("input[name='jobStDt']").val() != "" && $("input[name='jobEndDt']").val() != ""){
				if($("input[name='jobStDt']").val().replace(/-/g, "") > $("input[name='jobEndDt']").val().replace(/-/g, "")){
					alert("취업종료일은 취업시작일보다 크거나 같아야 합니다.");
					return;
				}
			}
			if($("input[name='healStDt']").val() != "" && $("input[name='healEndDt']").val() != ""){
				if($("input[name='healStDt']").val().replace(/-/g, "") > $("input[name='healEndDt']").val().replace(/-/g, "")){
					alert("이용종료일은 이용시작일보다 크거나 같아야 합니다.");
					return;
				}
			}

			$.ajax({
				url : '/ajaxCslHealAdd.do',
				type : 'POST',
				data : $('#cslForm').serialize(),
				success : function(res){
					if(res.err != 'Y'){
						alert("치료재활정보 " + res.MSG + " 완료");
						getCslHealList($("input[name='mbrNo']").val());
					}else{
						alert(res.MSG);
					}
				},
				error : function(xhr, status){}
			});
		}
		<%-- 탭 메뉴 활성화 --%>
		$('.el-tabs__item').on('click', function(){
			var id = $(this).attr('data-id');
			$(this).addClass('is-active').siblings().removeClass('is-active');

			$('.tab-form').hide();
			$('#' + id).show();

			$("input[name='cslNo']").val("");
		});
	});
</script>
<!-- 페이지 타이틀 -->
<div class="tit-area">
	<h1><i class="el-icon-s-order" style="color: rgb(0, 108, 185);"></i> 사례관리</h1>
</div>
<!-- // 페이지 타이틀 -->
<!-- 상단 버튼 -->
<div class="top-right-btn">
	<button type="button" onclick="javaScript:individualNew();" class="el-button normal el-button--default el-button--small is-plain" style="margin-left: 8px;">
		<i class="el-icon-refresh"></i> <span>초기화</span>
	</button>
</div>
<!-- // 상단 버튼 -->
<form name="cslForm" id="cslForm" enctype="multipart/form-data">
<form:input path="cslIdvInfo.cslNo" hidden="true" />
<form:input path="cslIdvInfo.cslTermTm" hidden="true" />
<input type="hidden" name="newFlag" value="Y" />
<input type="hidden" name="deleteEvlSeq" />
<input type="hidden" name="sudCreDt" />
<input type="hidden" name="devCreDt" />
<input type="hidden" name="jobCreDt" />
<input type="hidden" name="healCreDt" />
<input type="hidden" name="choiceMbrNo" />
<input type="hidden" name="choiceIspDt" />
<div class="formline">
	<!-- 회원등록번호 ~ 사례관리자 -->
	<div class="section">
		<table class="w-auto">
			<tbody>
			<tr>
				<th>회원등록번호</th>
				<td>
					<div class="search-input tac">
						<form:input path="mstMbrInfo.mbrNo" cssClass="el-input__inner" readonly="true" placeholder="회원등록번호" style="width: 142px;" />
						<button type="button" onclick="javaScript:mstMbrSearchPopup();"><i class="el-icon-search"></i></button>
					</div>
				</td>
				<th>성명</th>
				<td>
					<div class="dsp-ibk tac"><form:input path="mstMbrInfo.mbrNm" cssClass="el-input__inner" readonly="true" placeholder="성명" style="width: 100px;" /></div>
				</td>
				<th>성별</th>
				<td>
					<div class="dsp-ibk tac"><form:input path="mstMbrInfo.gendNm" cssClass="el-input__inner" readonly="true" placeholder="성별" style="width: 50px;" /></div>
				</td>
				<th>연령</th>
				<td>
					<div class="dsp-ibk tac"><form:input path="mstMbrInfo.age" csslass="el-input__inner" readonly="true" placeholder="연령" style="width: 80px;" /></div>
				</td>
				<th>등록일자</th>
				<td>
					<div class="dsp-ibk tac"><form:input path="mstMbrInfo.regDt" cssClass="el-input__inner" readonly="true" placeholder="등록일자" style="width: 100px;" /></div>
				</td>
				<th>의료보장</th>
				<td>
					<div class="dsp-ibk is-disabled"><form:input path="mstMbrInfo.medicCareNm" cssClass="el-input__inner" readonly="true" placeholder="의료보장" style="width: 120px;" /></div>
				</td>
				<th>사례관리자</th>
				<td>
					<div class="dsp-ibk is-disabled"><form:input path="mstMbrInfo.mngUsrId" cssClass="el-input__inner" readonly="true" placeholder="사례관리자" style="width: 130px;" /></div>
				</td>
			</tr>
			</tbody>
		</table>
	</div>
	<!-- // 회원등록번호 ~ 사례관리자 -->
	<!-- 사례관리 상담, ISP수립, 사정평가 -->
	<div class="section-sha" style="min-width: 1840px;">
		<!-- 탭메뉴 -->
		<div class="el-tabs__header is-top">
			<div class="el-tabs__nav-wrap is-top">
				<div class="el-tabs__nav-scroll">
					<div role="tablist" class="el-tabs__nav is-top">
						<div class="el-tabs__item is-top is-active" data-id="tab-focus">
							<span><i class="el-icon-s-help"></i> 사례관리 상담</span>
						</div>
						<div class="el-tabs__item is-top" data-id="tab-isp">
							<span><i class="el-icon-s-management"></i> ISP 수립/욕구항목</span>
						</div>
						<div class="el-tabs__item is-top" data-id="tab-assessment">
							<span><i class="el-icon-platform-eleme"></i> 병력정보</span>
						</div>
						<div class="el-tabs__item is-top" data-id="tab-cure">
							<span><i class="el-icon-platform-eleme"></i> 치료 재활정보</span>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- // 탭메뉴 -->
		<div class="el-tabs_content">
			<!-- 사례관리 상담 -->
			<div id="tab-focus" class="tab-form">
				<div class="in-tab-btn">
					<button disabled="disabled" id="idvSaveBtnNo" type="button" class="el-button normal el-button--primary el-button--small is-disabled is-plain" style="padding: 7px 13px;">
						<i class="el-icon-download"></i> <span>저장</span>
					</button>
					<button type="button" id="idvSaveBtnYes" onclick="javaScript:saveIdv();" class="el-button normal el-button--primary el-button--small is-plain" style="padding: 7px 13px;display: none;">
						<i class="el-icon-download"></i> <span>저장</span>
					</button>
					<button type="button" onclick="javaScript:newIdv();" class="el-button el-button--default el-button--small is-plain" style="padding: 7px 13px;">
						<i class="el-icon-circle-plus-outline"></i> <span>신규</span>
					</button>
					<button id="excelNo" type="button" disabled="disabled" class="el-button normal el-button--default el-button--small is-disabled is-plain">
						<i class="el-icon-document"></i> <span>엑셀다운로드</span>
					</button>
					<button id="excelYes" onclick="javaScript:individualExel();" type="button" class="el-button normal el-button--default el-button--small is-plain" style="display:none;">
						<i class="el-icon-document"></i> <span>엑셀다운로드</span>
					</button>
				</div>
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
									<div class="dsp-ibk is-disabled"><form:input path="cslIdvInfo.cslNm" cssClass="el-input__inner" readonly="true" style="width: 120px;" /></div>
								</td>
								<th><span class="required">*</span> 상담일시</th>
								<td>
									<div class="dat-pk">
										<i class="el-input__icon el-icon-date"></i>
										<form:input path="cslIdvInfo.cslDt" cssClass="el-input__inner datepicker" placeholder="날짜 선택" style="width: 130px;" />
									</div>
									<div class="time-box">
										<div class="tm-in">
											<i class="el-input__icon el-icon-time"></i>
											<form:input path="cslIdvInfo.cslFmTm" value="09:00" cssClass="el-input__inner timepicker" placeholder="시작" style="width: 96px;" />
										</div>
										<span>~</span>
										<div class="tm-in">
											<i class="el-input__icon el-icon-time"></i>
											<form:input path="cslIdvInfo.cslToTm" value="09:00" cssClass="el-input__inner timepicker" placeholder="종료" style="width: 96px;" />
										</div>
										<div class="t-min">
											<span class="readonly" id="cslTermTm">0</span> 분 소요
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<th><span class="required">*</span> 상담대상</th>
								<td>
<c:if test="${cslTgtCdList ne null and cslTgtCdList ne ''}">
	<c:forEach var="result" items="${cslTgtCdList}" varStatus="status">
									<span class="ck-bx">
										<input type="radio" class="el-radio__original" name="cslTgtCd" id="cslTgtCd-<c:out value='${status.count}'/>"  value="${result.CD_ID}"<c:if test="${result.CD_ID eq cslIdvInfo.cslTgtCd}"> checked</c:if> />
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
										<input type="radio" class="el-radio__original" name="cslTpCd" id="cslTpCd-<c:out value='${status.count}'/>"value="${result.CD_ID}"<c:if test="${result.CD_ID eq cslIdvInfo.cslTpCd}"> checked</c:if> />
										<label for="cslTpCd-<c:out value='${status.count}'/>"><span class="el-radio__input"><span class="el-radio__inner"></span></span> <c:out value="${result.CD_NM}" /></label>
									</span>
	</c:forEach>
</c:if>
								</td>
							</tr>
							<tr>
								<th><span class="required">*</span> 상담주제</th>
								<td colspan="3"><form:input path="cslIdvInfo.cslSbj" cssClass="el-input__inner" placeholder="상담주제" style="width: 680px;" /></td>
							</tr>
							<tr>
								<th>상담목표</th>
								<td colspan="3"><form:input path="cslIdvInfo.cslTgt" cssClass="el-input__inner" placeholder="상담목표" style="width: 680px;" /></td>
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
								<th class="txt-center"> <span class="el-tag-danger" id="ratingNum"><c:out value="${cslIdvInfo.rskSco}" /></span></th>
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
								<td colspan="3"><form:input path="cslIdvInfo.crisisCounsel" cssClass="el-input__inner" placeholder="위기상담" style="width: 680px;" /></td>
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
										<form:input path="cslIdvInfo.nxtCslDt" cssClass="el-input__inner datepicker" placeholder="날짜" style="width: 130px;" />
									</div>
									<div class="time-box">
										<div class="tm-in">
											<i class="el-input__icon el-icon-time"></i>
											<form:input path="cslIdvInfo.nxtCslTm" cssClass="el-input__inner timepicker" placeholder="시간" style="width: 96px;" />
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
			<!-- // 사례관리 상담 -->

			<!-- ISP 수립-->
			<div id="tab-isp" class="tab-form" style="display: none;">
				<div class="in-tab-btn">
					<button disabled="disabled" type="button" id="ispSaveButNo" class="el-button normal el-button--primary el-button--small is-disabled is-plain" style="padding: 7px 13px;">
						<i class="el-icon-download"></i> <span>저장</span>
					</button>
					<button type="button" onclick="javaScript:saveIsp();" id="ispSaveButYes" class="el-button normal el-button--primary el-button--small is-plain" style="padding: 7px 13px;display: none;">
						<i class="el-icon-download"></i> <span>저장</span>
					</button>
					<button type="button" onclick="javaScript:newIsp();" class="el-button el-button--default el-button--small is-plain" style="padding: 7px 13px;">
						<i class="el-icon-circle-plus-outline"></i> <span>신규</span>
					</button>
					<button id="ispExcelNo" type="button" disabled="disabled" class="el-button normal el-button--default el-button--small is-disabled is-plain">
						<i class="el-icon-document"></i> <span>엑셀다운로드</span>
					</button>
					<button id="ispExcelYes" onclick="javaScript:ispExcel();" type="button" class="el-button normal el-button--default el-button--small is-plain" style="display:none;">
						<i class="el-icon-document"></i> <span>엑셀다운로드</span>
					</button>
				</div>
				<div class="tab-tb-box">
					<div class="table-box">
						<div class="el-table_header-wrapper">
							<table>
								<colgroup>
									<col style="width:46px">
									<col style="width:100px">
									<col style="width:150px">
									<col style="width:1300px">
									<col style="width:150px">
									<col>
								</colgroup>
								<thead>
								<tr>
									<th>#</th>
									<th>수립 일자</th>
									<th>관리구분</th>
									<th>ISP 결과</th>
									<th>등록자</th>
									<th>작업</th>
								</tr>
								</thead>
							</table>
						</div>
						<div class="el-table_body-wrapper" style="height: 148px;" id="ispList">
							<div class="no-data">조회된 데이터가 없습니다.</div>
						</div>
					</div>
				</div>
				<div class="bottom-form el-row" id="ispInfoDiv">
					<div class="section">
						<table class="w-auto wr-form">
							<tbody>
							<tr>
								<th><span class="required">*</span> 사정일자(ISP)</th>
								<td>
									<div class="dat-pk">
										<i class="el-input__icon el-icon-date"></i>
										<input type="text" name="ispDt" class="el-input__inner datepicker" placeholder="날짜 선택" style="width: 130px;" />
									</div>
								</td>
								<th>관리구분</th>
								<td>
									<div class="dsp-ibk is-disabled">
										<input type="text" name="mngTpNm" class="el-input__inner" placeholder="일시관리" readonly="true" style="width: 150px;" />
										<input type="hidden" name="mngTpCd" id="mngTpCd" value="10" />
									</div>
								</td>
								<td>
<c:if test="${linkCdList ne null and linkCdList ne ''}">
	<c:forEach var="result" items="${linkCdList}" varStatus="status">
									&nbsp;<input type="checkbox" name="linkCd" value="<c:out value="${result.CD_ID}" />" />&nbsp;<c:out value="${result.CD_NM}" />&nbsp;
	</c:forEach>
</c:if>
								</td>
							</tr>
							</tbody>
						</table>
					</div>
					<div class="multi-table">
						<table class="wr-table" id="mngTpTable">
							<colgroup>
								<col style="width: 8%;">
								<col style="width: 4%;">
								<col style="width: 13%;">
								<col style="width: 8%;">
								<col style="width: 4%;">
								<col style="width: 13%;">
								<col style="width: 8%;">
								<col style="width: 4%;">
								<col style="width: 13%;">
							</colgroup>
							<thead>
							<tr>
								<th colspan="3">중독영역 (급성중독과 금단)</th>
								<th colspan="3">정신 및 신체영역</th>
								<th colspan="3">위험성 영역</th>
							</tr>
							<tr>
								<th>문항</th>
								<th>심각도</th>
								<th>자원연계</th>
								<th>문항</th>
								<th>심각도</th>
								<th>자원연계</th>
								<th>문항</th>
								<th>심각도</th>
								<th>자원연계</th>
							</tr>
							</thead>
							<tbody>
							<tr>
								<th id="evlItmSco01" class="bg-pk">약물중독</th>
								<td>
									<select name="evlItmSco01" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco01List ne null and evlItmSco01List ne ''}">
	<c:forEach var="result" items="${evlItmSco01List}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
									</select>
								</td>
								<td><input type="text" name="evlItmLnk01" class="el-input__inner" style="width: 100%;" /></td>
								<th id="evlItmSco06" class="bg-pk">정신과적 증상</th>
								<td>
									<select name="evlItmSco06" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco06List ne null and evlItmSco06List ne ''}">
	<c:forEach var="result" items="${evlItmSco06List}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
									</select>
								</td>
								<td><input type="text" name="evlItmLnk06" class="el-input__inner" style="width: 100%;" /></td>
								<th id="evlItmSco18" class="bg-pk">자해/자살위험</th>
								<td>
									<select name="evlItmSco18" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco18List ne null and evlItmSco18List ne ''}">
	<c:forEach var="result" items="${evlItmSco18List}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
									</select>
								</td>
								<td><input type="text" name="evlItmLnk18" class="el-input__inner" style="width: 100%;" /></td>
							</tr>
							<tr>
								<th id="evlItmSco02" class="bg-pk">알코올중독</th>
								<td>
									<select name="evlItmSco02" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco01List ne null and evlItmSco01List ne ''}">
	<c:forEach var="result" items="${evlItmSco01List}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
									</select>
								</td>
								<td><input type="text" name="evlItmLnk02" class="el-input__inner" style="width: 100%;" /></td>
								<th id="evlItmSco07" class="bg-pk">정신약물관리</th>
								<td>
									<select name="evlItmSco07" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco07List ne null and evlItmSco07List ne ''}">
	<c:forEach var="result" items="${evlItmSco07List}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
									</select>
								</td>
								<td><input type="text" name="evlItmLnk07" class="el-input__inner" style="width: 100%;" /></td>
								<th id="evlItmSco19" class="bg-pk">타해위험</th>
								<td>
									<select name="evlItmSco19" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco18List ne null and evlItmSco18List ne ''}">
	<c:forEach var="result" items="${evlItmSco18List}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
									</select>
								</td>
								<td><input type="text" name="evlItmLnk19" class="el-input__inner" style="width: 100%;" /></td>
							</tr>
							<tr>
								<th id="evlItmSco03" class="bg-pk">도박중독</th>
								<td>
									<select name="evlItmSco03" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco01List ne null and evlItmSco01List ne ''}">
	<c:forEach var="result" items="${evlItmSco01List}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
									</select>
								</td>
								<td><input type="text" name="evlItmLnk03" class="el-input__inner" style="width: 100%;" /></td>
								<th id="evlItmSco08" class="bg-pk">스트레스 상태</th>
								<td>
									<select name="evlItmSco08" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco08List ne null and evlItmSco08List ne ''}">
	<c:forEach var="result" items="${evlItmSco08List}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
									</select>
								</td>
								<td><input type="text" name="evlItmLnk08" class="el-input__inner" style="width: 100%;" /></td>
								<td colspan="3"></td>
							</tr>
							<tr>
								<th id="evlItmSco04" class="bg-pk">인터넷중독</th>
								<td>
									<select name="evlItmSco04" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco01List ne null and evlItmSco01List ne ''}">
	<c:forEach var="result" items="${evlItmSco01List}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
									</select>
								</td>
								<td><input type="text" name="evlItmLnk04" class="el-input__inner" style="width: 100%;" /></td>
								<th id="evlItmSco09" class="bg-pk">신체질환</th>
								<td>
									<select name="evlItmSco09" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco09List ne null and evlItmSco09List ne ''}">
	<c:forEach var="result" items="${evlItmSco09List}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
									</select>
								</td>
								<td><input type="text" name="evlItmLnk09" class="el-input__inner" style="width: 100%;" /></td>
								<td colspan="3"></td>
							</tr>
							<tr>
								<th id="evlItmSco17" class="bg-pk">기타중독</th>
								<td>
									<select name="evlItmSco17" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco01List ne null and evlItmSco01List ne ''}">
	<c:forEach var="result" items="${evlItmSco01List}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
									</select>
								</td>
								<td><input type="text" name="evlItmLnk17" class="el-input__inner" style="width: 100%;" /></td>
								<td colspan="3"></td>
								<td colspan="3"></td>
							</tr>
							</tbody>
						</table>
						<table class="wr-table" id="mngTpTable">
							<colgroup>
								<col style="width: 8%;">
								<col style="width: 4%;">
								<col style="width: 13%;">
								<col style="width: 8%;">
								<col style="width: 4%;">
								<col style="width: 13%;">
								<col style="width: 8%;">
								<col style="width: 4%;">
								<col style="width: 13%;">
							</colgroup>
							<thead>
							<tr>
								<th colspan="3">사회적 관계영역</th>
								<th colspan="3">사회서비스 평가영역</th>
								<th colspan="3">기타영역</th>
							</tr>
							<tr>
								<th>문항</th>
								<th>심각도</th>
								<th>자원연계</th>
								<th>문항</th>
								<th>심각도</th>
								<th>자원연계</th>
								<th>문항</th>
								<th>심각도</th>
								<th>자원연계</th>
							</tr>
							</thead>
							<tbody>
							<tr>
								<th id="evlItmSco10" class="bg-pk">가족관계</th>
								<td>
									<select name="evlItmSco10" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco10List ne null and evlItmSco10List ne ''}">
	<c:forEach var="result" items="${evlItmSco10List}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
									</select>
								</td>
								<td><input type="text" name="evlItmLnk10" class="el-input__inner" style="width: 100%;" /></td>
								<th id="evlItmSco12" class="bg-pk">주거</th>
								<td>
									<select name="evlItmSco12" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco12List ne null and evlItmSco12List ne ''}">
	<c:forEach var="result" items="${evlItmSco12List}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
									</select>
								</td>
								<td><input type="text" name="evlItmLnk12" class="el-input__inner" style="width: 100%;" /></td>
								<th id="evlItmSco21" class="bg-pk">영성</th>
								<td>
									<select name="evlItmSco21" style="width: 100%;">
<c:if test="${evlItmSco14List ne null and evlItmSco14List ne ''}">
	<c:forEach var="result" items="${evlItmSco14List}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
									</select>
								</td>
								<td><input type="text" name="evlItmLnk21" class="el-input__inner" style="width: 100%;" /></td>
							</tr>
							<tr>
								<th id="evlItmSco11" class="bg-pk">사회적관계</th>
								<td>
									<select name="evlItmSco11" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco11List ne null and evlItmSco11List ne ''}">
	<c:forEach var="result" items="${evlItmSco11List}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
									</select>
								</td>
								<td><input type="text" name="evlItmLnk11" class="el-input__inner" style="width: 100%;" /></td>
								<th id="evlItmSco13" class="bg-pk">경제활동 지원</th>
								<td>
									<select name="evlItmSco13" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco13List ne null and evlItmSco13List ne ''}">
	<c:forEach var="result" items="${evlItmSco13List}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
									</select>
								</td>
								<td><input type="text" name="evlItmLnk13" class="el-input__inner" style="width: 100%;" /></td>
								<th id="evlItmSco22" class="bg-pk">봉사</th>
								<td>
									<select name="evlItmSco22" style="width: 100%;">
<c:if test="${evlItmSco14List ne null and evlItmSco14List ne ''}">
	<c:forEach var="result" items="${evlItmSco14List}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
									</select>
								</td>
								<td><input type="text" name="evlItmLnk22" class="el-input__inner" style="width: 100%;" /></td>
							</tr>
							<tr>
								<th id="evlItmSco20" class="bg-pk">회복환경 관계</th>
								<td>
									<select name="evlItmSco20" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco11List ne null and evlItmSco11List ne ''}">
	<c:forEach var="result" items="${evlItmSco11List}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
									</select>
								</td>
								<td><input type="text" name="evlItmLnk20" class="el-input__inner" style="width: 100%;" /></td>
								<th id="evlItmSco15" class="bg-pk">고용 및 교육가능성</th>
								<td>
									<select name="evlItmSco15" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco12List ne null and evlItmSco12List ne ''}">
	<c:forEach var="result" items="${evlItmSco12List}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
									</select>
								</td>
								<td><input type="text" name="evlItmLnk15" class="el-input__inner" style="width: 100%;" /></td>
								<th id="evlItmSco23" class="bg-pk">여가</th>
								<td>
									<select name="evlItmSco23" style="width: 100%;">
<c:if test="${evlItmSco14List ne null and evlItmSco14List ne ''}">
	<c:forEach var="result" items="${evlItmSco14List}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
									</select>
								</td>
								<td><input type="text" name="evlItmLnk23" class="el-input__inner" style="width: 100%;" /></td>
							</tr>
							<tr>
								<td colspan="3"></td>
								<th id="evlItmSco16" class="bg-pk">법률적 문제</th>
								<td>
									<select name="evlItmSco16" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco12List ne null and evlItmSco12List ne ''}">
	<c:forEach var="result" items="${evlItmSco12List}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
									</select>
								</td>
								<td><input type="text" name="evlItmLnk16" class="el-input__inner" style="width: 100%;" /></td>
								<th id="evlItmSco24" class="bg-pk">미래계획</th>
								<td>
									<select name="evlItmSco24" style="width: 100%;">
<c:if test="${evlItmSco14List ne null and evlItmSco14List ne ''}">
	<c:forEach var="result" items="${evlItmSco14List}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
									</select>
								</td>
								<td><input type="text" name="evlItmLnk24" class="el-input__inner" style="width: 100%;" /></td>
							</tr>
							<tr>
								<td colspan="3"></td>
								<th id="evlItmSco14" class="bg-pk">취업 및 학업욕구</th>
								<td>
									<select name="evlItmSco14" style="width: 100%;">
<c:if test="${evlItmSco14List ne null and evlItmSco14List ne ''}">
	<c:forEach var="result" items="${evlItmSco14List}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
									</select>
								</td>
								<td><input type="text" name="evlItmLnk14" class="el-input__inner" style="width: 100%;" /></td>
								<th id="evlItmSco25" class="bg-pk">기타</th>
								<td>
									<select name="evlItmSco25" style="width: 100%;">
<c:if test="${evlItmSco14List ne null and evlItmSco14List ne ''}">
	<c:forEach var="result" items="${evlItmSco14List}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
									</select>
								</td>
								<td><input type="text" name="evlItmLnk25" class="el-input__inner" style="width: 100%;" /></td>
							</tr>
							</tbody>
						</table>
					</div>
					<div class="el-row">
						<div class="row2">
							<div class="section pdn">
								<div class="el-card_header">
									<h2><i class="el-icon-s-opportunity"></i> ISP 결과</h2>
								</div>
								<div class="el-card_body"><textarea name="ispRst" style="height: 162px;" placeholder="ISP 결과"></textarea></div>
							</div>
						</div>
						<div class="row2">
							<div class="section pdn">
								<div class="el-card_header">
									<h2><i class="el-icon-s-opportunity"></i> 장단기 목표수립</h2>
								</div>
								<div class="el-card_body"><textarea name="tgtCtnt" style="height: 162px;" placeholder="장단기 목표수립"></textarea></div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- // ISP 수립-->

			<!-- 병력정보 -->
			<div id="tab-assessment" class="tab-form" style="display: none;">
				<div id="anmDivView">
					<div class="in-tab-btn">
						<button disabled="disabled" type="button" id="anmSaveButNo" class="el-button normal el-button--primary el-button--small is-disabled is-plain" style="padding: 7px 13px;">
							<i class="el-icon-download"></i> <span>저장</span>
						</button>
						<button type="button" onclick="javaScript:saveAnm();" id="anmSaveButYes" class="el-button normal el-button--primary el-button--small is-plain" style="padding: 7px 13px;display: none;">
							<i class="el-icon-download"></i> <span>저장</span>
						</button>
						<button type="button" onclick="javaScript:newAnm();" class="el-button el-button--default el-button--small is-plain" style="padding: 7px 13px;">
							<i class="el-icon-circle-plus-outline"></i> <span>신규</span>
						</button>
						<button id="cslAnmExcelNo" type="button" disabled="disabled" class="el-button normal el-button--default el-button--small is-disabled is-plain">
							<i class="el-icon-document"></i> <span>엑셀다운로드</span>
						</button>
						<button id="cslAnmExcelYes" onclick="javaScript:cslAnmExel();" type="button" class="el-button normal el-button--default el-button--small is-plain" style="display:none;">
							<i class="el-icon-document"></i> <span>엑셀다운로드</span>
						</button>
					</div>
					<div class="tab-tb-box">
						<div class="table-box">
							<div class="el-table_header-wrapper">
								<table>
									<colgroup>
										<col style="width:46px">
										<col style="width:130px">
										<col style="width:130px">
										<col style="width:130px">
										<col style="width:1180px">
										<col style="width:120px">
										<col>
									</colgroup>
									<thead>
									<tr>
										<th>#</th>
										<th>중독력등록일자</th>
										<th>자살력등록일자</th>
										<th>발달력등록일자</th>
										<th>병력정보상세내용</th>
										<th>등록자</th>
										<th>작업</th>
									</tr>
									</thead>
								</table>
							</div>
							<div class="el-table_body-wrapper" style="height: 148px;" id="anmList">
								<div class="no-data">조회된 데이터가 없습니다.</div>
							</div>
						</div>
					</div>
					<div class="section">
						<table class="w-auto wr-form">
							<tbody>
							<tr>
								<th>상담자</th>
								<td><input type="text" name="userNm" value="<c:out value="${loginUserNm}"/>" class="el-input__inner" placeholder="상담자" readonly style="width: 150px;" /></td>
								<th>사정평가기관</th>
								<td><input type="text" name="siteNm" value="<c:out value="${loginSiteNm}" />" class="el-input__inner" placeholder="사정평가기관" readonly style="width: 150px;" /></td>
							</tr>
							</tbody>
						</table>
					</div>
					<div class="el-row">
						<div class="row2">
							<div class="section pdn">
								<div class="el-card_header">
									<h2><i class="el-icon-s-opportunity"></i> 중독력</h2>
								</div>
								<div class="el-card_body">
									<table class="w-auto wr-form">
										<tbody>
										<tr>
											<th><span class="required">*</span> 최초 사용약물</th>
											<td colspan="5">
												<select name="fstDrugCd" style="width: 100%;">
													<option value="">선택</option>
<c:if test="${fstDrugCdList ne null and fstDrugCdList ne ''}">
	<c:forEach var="result" items="${fstDrugCdList}" varStatus="status">
													<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
												</select>
												<div style="margin-top:5px"><input type="text" name="fstDrug" class="el-input__inner" style="width: 100%;" placeholder="최초사용약물 입력" /></div>
											</td>
										</tr>
										<tr>
											<th><span class="required">*</span> 주요 사용약물</th>
											<td colspan="5">
												<select name="mainDrugCd" style="width: 100%;">
													<option value="">선택</option>
<c:if test="${mainDrugCdList ne null and mainDrugCdList ne ''}">
	<c:forEach var="result" items="${mainDrugCdList}" varStatus="status">
													<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
												</select>
												<div style="margin-top:5px"><input type="text" name="mainDrug" class="el-input__inner" style="width: 100%;" placeholder="주요사용약물 입력" /></div>
											</td>
										</tr>
										<tr>
											<th><span class="required">*</span> 최초 사용시기</th>
											<td><input type="text" name="fstAge" class="el-input__inner" placeholder="나이" style="width: 100px;" /></td>
											<th><span class="required">*</span> 마지막 사용시기</th>
											<td><input type="text" name="lstAge" class="el-input__inner" placeholder="나이" style="width: 100px;" /></td>
											<th><span class="required">*</span> 사용기간</th>
											<td><input type="text" name="useTerm" class="el-input__inner" placeholder="사용기간" style="width: 113px;" /></td>
										</tr>
										<tr>
											<th><span class="required">*</span> 사용빈도</th>
											<td>
												<select name="useFrqCd" style="width:150px">
													<option value="">선택</option>
<c:if test="${useFrqCdList ne null and useFrqCdList ne ''}">
	<c:forEach var="result" items="${useFrqCdList}" varStatus="status">
													<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
												</select>
											</td>
											<th><span class="required">*</span> 사용원인</th>
											<td colspan="3">
												<select name="useCauCd" style="width: 150px;" onchange="javaScript:inputDisabledChang(this, 'useCauEtc');">
													<option value="">선택</option>
<c:if test="${useCauCdList ne null and useCauCdList ne ''}">
	<c:forEach var="result" items="${useCauCdList}" varStatus="status">
													<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
												</select>
												<div class="dsp-ibk"><input type="text" name="useCauEtc" class="el-input__inner" placeholder="기타 선택시 입력 가능" disabled style="width: 150px;" /></div>
											</td>
										</tr>
										<tr>
											<th><span class="required">*</span> 약물관련 법적문제</th>
											<td colspan="5">
												<select name="lawPbmCd" id="lawPbmCd" class="testselect2" style="width: 150px;" onchange="javaScript:inputDisabledChang(this, 'lawPbmEtc');" multiple="multiple">
<c:if test="${lawPbmCdList ne null and lawPbmCdList ne ''}">
	<c:forEach var="result" items="${lawPbmCdList}" varStatus="status">
													<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
												</select>
												<div class="dsp-ibk"><input type="text" name="lawPbmEtc" class="el-input__inner" disabled placeholder="기타 선택시 입력 가능" style="width: 432px;" /></div>
											</td>
										</tr>
										<tr>
											<th><span class="required">*</span> 신체적 건강문제</th>
											<td colspan="5"><input type="text" name="physPbm" class="el-input__inner" placeholder="신체적 건강문제" style="width: 100%;" /></td>
										</tr>
										<tr>
											<th><span class="required">*</span> 정신적 건강문제</th>
											<td colspan="5">
												<select name="sprtPbmCd" style="width: 150px;" onchange="javaScript:inputDisabledChang(this, 'sprtPbmEtc');">
													<option value="">선택</option>
<c:if test="${sprtPbmCdList ne null and sprtPbmCdList ne ''}">
	<c:forEach var="result" items="${sprtPbmCdList}" varStatus="status">
													<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
												</select>
												<span class="dsp-ibk"><input type="text" name="sprtPbmEtc" class="el-input__inner" disabled placeholder="기타 선택시 입력 가능" style="width: 432px;" /></span>
											</td>
										</tr>
										</tbody>
									</table>
								</div>
							</div>
							<div class="section pdn">
								<div class="el-card_header">
									<h2><i class="el-icon-s-opportunity"></i> 단약경험</h2>
								</div>
								<div class="el-card_body">
									<table class="w-auto wr-form">
										<tbody>
										<tr>
											<th>단약경험</th>
											<td>
												<select name="cureoffExp" style="width:150px">
													<option value="">선택</option>
													<option value="Y">유</option>
													<option value="N">무</option>
												</select>
											</td>
											<th>단약횟수</th>
											<td><input type="text" name="cureoffNum" class="el-input__inner" placeholder="단양횟수" style="width: 100px;" /></td>
											<th>최장단약기간</th>
											<td><input type="text" name="cureoffDay" class="el-input__inner" placeholder="최장단약기간" style="width: 113px;" /></td>
										</tr>
										<tr>
											<th>단약이유</th>
											<td colspan="5"><input type="text" name="cureoffReason" class="el-input__inner" placeholder="단약이유" style="width: 100%;" /></td>
										</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<div class="row2">
							<div class="section pdn" style="height: 548px;">
								<div class="el-card_header"><h2><i class="el-icon-s-opportunity"></i> 자살시도력</h2></div>
								<div class="el-card_body">
									<table class="w-auto wr-form">
										<tbody>
										<tr>
											<th>입력일자</th>
											<td>
												<div class="dat-pk">
													<i class="el-input__icon el-icon-date"></i>
													<input type="text" name="sudIndt" class="el-input__inner datepicker" placeholder="날짜" style="width: 130px;" />
												</div>
											</td>
											<th>시도나이</th>
											<td><input type="text" name="sudAge" placeholder="나이" style="width: 100%;" /></td>
										</tr>
										<tr>
											<th>문제유형</th>
											<td colspan="3">
												<select name="sudTypeCd" id="sudTypeCd" style="width: 100%;">
													<option value="">선택</option>
<c:if test="${sudTypeList ne null and sudTypeList ne ''}">
	<c:forEach var="result" items="${sudTypeList}" varStatus="status">
													<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
												</select>
											</td>
										</tr>
										<tr>
											<th>정신건강문제</th>
											<td colspan="3">
												<select name="sudSoulCd" id="sudSoulCd" style="width: 100%;">
													<option value="">선택</option>
<c:if test="${sudSoulList ne null and sudSoulList ne ''}">
	<c:forEach var="result" items="${sudSoulList}" varStatus="status">
													<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
												</select>
											</td>
										</tr>
										<tr>
											<th>시도방법</th>
											<td colspan="3">
												<select name="sudWayCd" id="sudWayCd" style="width: 230px;" onchange="javaScript:inputDisabledChang(this, 'sudWayEtc');">
													<option value="">선택</option>
<c:if test="${sudWayList ne null and sudWayList ne ''}">
	<c:forEach var="result" items="${sudWayList}" varStatus="status">
													<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
												</select>
												<span class="dsp-ibk"><input type="text" name="sudWayEtc" class="el-input__inner" disabled placeholder="기타 선택시 입력 가능" style="width: 372px;" /></span>
											</td>
										</tr>
										<tr>
											<th>상세내용</th>
											<td colspan="3"><textarea name="sudCtnt" placeholder="상세내용" style="width:100%;height: 325px;"></textarea></td>
										</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
					<div class="section pdn el-row">
						<div class="el-card_header">
							<h2><i class="el-icon-s-opportunity"></i> 발달력</h2>
						</div>
						<div class="multi-table">
							<table class="wr-table">
								<colgroup>
									<col style="width: 8%;">
									<col style="width: 17%;">
									<col style="width: 8%;">
									<col style="width: 17%;">
									<col style="width: 8%;">
									<col style="width: 17%;">
									<col style="width: 8%;">
									<col style="width: 17%;">
								</colgroup>
								<thead>
								<tr>
									<th colspan="2">영유아기(0~3세)</th>
									<th colspan="2">아동기(3~11세)</th>
									<th colspan="2">청소년기(11~13세)</th>
									<th colspan="2">성인기(13세 이상)</th>
								</tr>
								</thead>
								<tbody>
								<tr>
									<th>임신</th>
									<td>
										<select name="devBabyPregCd" id="devBabyPregCd" style="width: 230px;">
											<option value="">선택</option>
<c:if test="${devBabyPregList ne null and devBabyPregList ne ''}">
	<c:forEach var="result" items="${devBabyPregList}" varStatus="status">
													<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
										</select>
									</td>
									<th>훈육방식</th>
									<td>
										<select name="devChildDiscCd" id="devChildDiscCd" style="width: 230px;">
											<option value="">선택</option>
<c:if test="${devChildDiscList ne null and devChildDiscList ne ''}">
	<c:forEach var="result" items="${devChildDiscList}" varStatus="status">
													<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
										</select>
									</td>
									<th>학습태도</th>
									<td>
										<select name="devTeenLearnCd" id="devTeenLearnCd" style="width: 230px;">
											<option value="">선택</option>
<c:if test="${devLearnList ne null and devLearnList ne ''}">
	<c:forEach var="result" items="${devLearnList}" varStatus="status">
													<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
										</select>
									</td>
									<th>대인관계</th>
									<td>
										<select name="devAdulRelationCd" id="devAdulRelationCd" style="width: 230px;">
											<option value="">선택</option>
<c:if test="${devRelationList ne null and devRelationList ne ''}">
	<c:forEach var="result" items="${devRelationList}" varStatus="status">
													<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
										</select>
									</td>
								</tr>
								<tr>
									<th>발육상태</th>
									<td>
										<select name="devBabyDevCd" id="devBabyDevCd" style="width: 230px;">
											<option value="">선택</option>
<c:if test="${devBabyDevList ne null and devBabyDevList ne ''}">
	<c:forEach var="result" items="${devBabyDevList}" varStatus="status">
													<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
										</select>
									</td>
									<th>학습태도</th>
									<td>
										<select name="devChildLearnCd" id="devChildLearnCd" style="width: 230px;">
											<option value="">선택</option>
<c:if test="${devLearnList ne null and devLearnList ne ''}">
	<c:forEach var="result" items="${devLearnList}" varStatus="status">
													<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
										</select>
									</td>
									<th>대인관계</th>
									<td>
										<select name="devTeenRelationCd" id="devTeenRelationCd" style="width: 230px;">
											<option value="">선택</option>
<c:if test="${devRelationList ne null and devRelationList ne ''}">
	<c:forEach var="result" items="${devRelationList}" varStatus="status">
													<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
										</select>
									</td>
									<th>이성교제</th>
									<td>
										<select name="devAdulSexCd" id="devAdulSexCd" style="width: 230px;">
											<option value="">선택</option>
<c:if test="${devAdulSexList ne null and devAdulSexList ne ''}">
	<c:forEach var="result" items="${devAdulSexList}" varStatus="status">
													<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
										</select>
									</td>
								</tr>
								<tr>
									<th>주양육자</th>
									<td>
										<select name="devBabyNurtCd" id="devBabyNurtCd" style="width: 230px;">
											<option value="">선택</option>
<c:if test="${devBabyNurtList ne null and devBabyNurtList ne ''}">
	<c:forEach var="result" items="${devBabyNurtList}" varStatus="status">
													<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
										</select>
									</td>
									<th>대인관계</th>
									<td>
										<select name="devChildRelationCd" id="devChildRelationCd" style="width: 230px;">
											<option value="">선택</option>
<c:if test="${devRelationList ne null and devRelationList ne ''}">
	<c:forEach var="result" items="${devRelationList}" varStatus="status">
													<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
										</select>
									</td>
									<th>특이사항</th>
									<td>
										<select name="devTeenUniCd" id="devTeenUniCd" style="width: 230px;">
											<option value="">선택</option>
<c:if test="${devTeenUniList ne null and devTeenUniList ne ''}">
	<c:forEach var="result" items="${devTeenUniList}" varStatus="status">
													<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
										</select>
									</td>
									<th>기타</th>
									<td><input type="text" name="devAdulEtc" style="width: 100%;"/></td>
								</tr>
								<tr>
									<td colspan="2"></td>
									<th>기타</th>
									<td><input type="text" name="devChildTec" style="width: 100%;" /></td>
									<th>기타</th>
									<td><input type="text" name="devTeenEtc" style="width: 100%;" /></td>
									<td colspan="2"></td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
			<!-- // 병력정보 -->

			<!-- 치료 재활정보 -->
			<div id="tab-cure" class="tab-form" style="display: none;">
				<div id="healDivView">
					<div class="in-tab-btn">
						<button disabled="disabled" type="button" id="healSaveButNo" class="el-button normal el-button--primary el-button--small is-disabled is-plain" style="padding: 7px 13px;">
							<i class="el-icon-download"></i> <span>저장</span>
						</button>
						<button type="button" onclick="javaScript:saveHeal();" id="healSaveButYes" class="el-button normal el-button--primary el-button--small is-plain" style="padding: 7px 13px;display: none;">
							<i class="el-icon-download"></i> <span>저장</span>
						</button>
						<button type="button" onclick="javaScript:newHeal();" class="el-button el-button--default el-button--small is-plain" style="padding: 7px 13px;">
							<i class="el-icon-circle-plus-outline"></i> <span>신규</span>
						</button>
						<button id="cureExcelNo" type="button" disabled="disabled" class="el-button normal el-button--default el-button--small is-disabled is-plain">
							<i class="el-icon-document"></i> <span>엑셀다운로드</span>
						</button>
						<button id="cureExcelYes" onclick="javaScript:cureExel();" type="button" class="el-button normal el-button--default el-button--small is-plain" style="display:none;">
							<i class="el-icon-document"></i> <span>엑셀다운로드</span>
						</button>
					</div>
					<div class="tab-tb-box">
						<div class="table-box">
							<div class="el-table_header-wrapper">
								<table>
									<colgroup>
										<col style="width:46px">
										<col style="width:130px">
										<col style="width:130px">
										<col style="width:130px">
										<col style="width:1180px">
										<col style="width:120px">
										<col>
									</colgroup>
									<thead>
									<tr>
										<th>#</th>
										<th>치료정보등록일자</th>
										<th>직업력등록일자</th>
										<th>재활등록일자</th>
										<th>재활정보상세내용</th>
										<th>등록자</th>
										<th>작업</th>
									</tr>
									</thead>
								</table>
							</div>
							<div class="el-table_body-wrapper" style="height: 148px;" id="healList">
								<div class="no-data">조회된 데이터가 없습니다.</div>
							</div>
						</div>
					</div>
					<div class="el-row">
						<div class="row2">
							<div class="section pdn">
								<div class="el-card_header">
									<h2><i class="el-icon-s-opportunity"></i> 치료정보</h2>
								</div>
								<div class="el-card_body">
									<table class="w-auto wr-form">
										<tbody>
										<tr>
											<th><span class="required">*</span> 진단명</th>
											<td><input type="text" name="diagName" class="el-input__inner" style="width: 420px;" placeholder="진단명 입력" /></td>
											<th>발병시기</th>
											<td>
												<div class="dat-pk">
													<i class="el-input__icon el-icon-date"></i>
													<input type="text" name="attDt" class="el-input__inner datepicker" style="width: 100%;" placeholder="발병시기 입력" />
												</div>
											</td>
										</tr>
										<tr>
											<th>처방약물</th>
											<td colspan="3"><input type="text" name="presDrug" class="el-input__inner" style="width: 100%;" placeholder="처방약물 입력" /></td>
										</tr>
										<tr>
											<th>순응도</th>
											<td colspan="3">
												<select name="conformCd" id="conformCd" style="width: 100%;">
													<option value="">선택</option>
<c:if test="${conformList ne null and conformList ne ''}">
	<c:forEach var="result" items="${conformList}" varStatus="status">
													<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
												</select>
											</td>
										</tr>
										<tr>
											<th>복용량</th>
											<td colspan="3"><input type="text" name="dosage" class="el-input__inner" style="width: 100%;" placeholder="복용량 입력" /></td>
										</tr>
										<tr>
											<th>신체질환</th>
											<td colspan="3">
												<select name="mediIllCd" id="mediIllCd" style="width: 100%;">
													<option value="">선택</option>
<c:if test="${mediIllList ne null and mediIllList ne ''}">
	<c:forEach var="result" items="${mediIllList}" varStatus="status">
													<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
												</select>
											</td>
										</tr>
										</tbody>
									</table>
								</div>
							</div>
							<div class="section pdn">
								<div class="el-card_header">
									<h2><i class="el-icon-s-opportunity"></i> 직업력</h2>
								</div>
								<div class="el-card_body">
									<table class="w-auto wr-form">
										<tbody>
										<tr>
											<th>취업시작일</th>
											<td>
												<div class="dat-pk">
													<i class="el-input__icon el-icon-date"></i>
													<input type="text" name="jobStDt" class="el-input__inner datepicker" style="width: 140px;" placeholder="취업시작일 입력" onchange="javaScript:dtCheck('jobStDt', 'jobEndDt', 'jobTerm');" />
												</div>
											</td>
											<th>취업종료일</th>
											<td>
												<div class="dat-pk">
													<i class="el-input__icon el-icon-date"></i>
													<input type="text" name="jobEndDt" class="el-input__inner datepicker" style="width: 140px;" placeholder="취업종료일 입력" onchange="javaScript:dtCheck('jobStDt', 'jobEndDt', 'jobTerm');" />
												</div>
											</td>
											<th>일수</th>
											<td><input type="text" name="jobTerm" class="el-input__inner" style="width: 140px;" placeholder="일수 입력" /></td>
										</tr>
										<tr>
											<th>고용형태</th>
											<td colspan="5">
												<select name="jobFormCd" id="jobFormCd" style="width: 100%;">
													<option value="">선택</option>
<c:if test="${jobFormList ne null and jobFormList ne ''}">
	<c:forEach var="result" items="${jobFormList}" varStatus="status">
													<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
												</select>
											</td>
										</tr>
										<tr>
											<th>직업군</th>
											<td colspan="5">
												<select name="jobTypeCd" id="jobTypeCd" style="width: 230px;" onchange="javaScript:inputDisabledChang(this, 'jobTypeEtc');">
													<option value="">선택</option>
<c:if test="${jobTypeList ne null and jobTypeList ne ''}">
	<c:forEach var="result" items="${jobTypeList}" varStatus="status">
													<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
												</select>
												<span class="dsp-ibk"><input type="text" name="jobTypeEtc" class="el-input__inner" disabled placeholder="기타 선택시 입력 가능" style="width: 372px;" /></span>
											</td>
										</tr>
										<tr>
											<th>소득금액</th>
											<td colspan="5"><input type="text" name="jobIncome" class="el-input__inner" style="width: 100%;" placeholder="소득금액 입력" /></td>
										</tr>
										<tr>
											<th>퇴사사유</th>
											<td colspan="5"><textarea name="jobResign" placeholder="퇴사사유" style="width:100%;height: 190px;"></textarea></td>
										</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<div class="row2">
							<div class="section pdn" style="height: 673px;">
								<div class="el-card_header"><h2><i class="el-icon-s-opportunity"></i> 재활</h2></div>
								<div class="el-card_body">
									<table class="w-auto wr-form">
										<tbody>
										<tr>
											<th>이용시작일</th>
											<td>
												<div class="dat-pk">
													<i class="el-input__icon el-icon-date"></i>
													<input type="text" name="healStDt" class="el-input__inner datepicker" style="width: 140px;" placeholder="이용시작일자 입력" onchange="javaScript:dtCheck('healStDt', 'healEndDt', 'healTerm');" />
												</div>
											</td>
											<th>이용종료일</th>
											<td>
												<div class="dat-pk">
													<i class="el-input__icon el-icon-date"></i>
													<input type="text" name="healEndDt" class="el-input__inner datepicker" style="width: 140px;" placeholder="이용종료일자 입력" onchange="javaScript:dtCheck('healStDt', 'healEndDt', 'healTerm');" />
												</div>
											</td>
											<th>일수</th>
											<td><input type="text" name="healTerm" class="el-input__inner" style="width: 140px;" placeholder="일수 입력" /></td>
										</tr>
										<tr>
											<th>기관유형</th>
											<td colspan="5">
												<select name="organFormCd" id="organFormCd" style="width: 100%;">
													<option value="">선택</option>
<c:if test="${organFormList ne null and organFormList ne ''}">
	<c:forEach var="result" items="${organFormList}" varStatus="status">
													<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
												</select>
											</td>
										</tr>
										<tr>
											<th>기관명</th>
											<td colspan="5">
												<input type="text" name="organName" class="el-input__inner" style="width: 100%;" placeholder="기관명 입력" />
											</td>
										</tr>
										<tr>
											<th>내용</th>
											<td colspan="5"><textarea name="organCtnt" placeholder="내용" style="width:100%;height: 495px;"></textarea></td>
										</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- // 치료 재활정보 -->
		</div>
		<!-- // 사례관리 상담, ISP수립, 사정평가 -->
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
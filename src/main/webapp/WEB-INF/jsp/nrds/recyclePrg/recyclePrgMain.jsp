<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String loginUserId = StringUtils.defaultIfEmpty((String)request.getAttribute("LoginUserId"), "");
	String loginSiteNm = StringUtils.defaultIfEmpty((String)request.getAttribute("LoginSiteNm"), "");
	String loginUserNm = StringUtils.defaultIfEmpty((String)request.getAttribute("LoginUserNm"), "");
%>
<c:set var="loginUserId" value="<%=loginUserId%>" />
<c:set var="loginSiteNm" value="<%=loginSiteNm%>" />
<c:set var="loginUserNm" value="<%=loginUserNm%>" />

<script type="text/javascript" language="javascript" charset="utf-8" src="<c:url value='/js/jquery.form.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">
	$(document).ready(function(){
		$('.generate').each(function(index, item){
			var tagName = $(this).attr("gen-id");
			var tagType = $(this).attr("gen-type");
			var grpCd = $(this).attr("gen-cond1");
			var defaultValue = $(this).attr("gen-default-value");
		    var defaultText = $(this).attr("gen-default-text");
			var selectValue = $(this).attr("gen-select-value");			
			$.ajax({
				type: 'POST',
				url: '<c:url value="/nrds/SysCdList.do"/>',
				data : { 'grpCd' : grpCd },
				//async: false,
				dataType: "json",
				beforeSend:function(){
					if(tagType=='select')
					{
						$("span[gen-id='"+tagName+"']").html("<select><option>로딩중...</option></select>");
					}
					else
					{
						$("span[gen-id='"+tagName+"']").html("로딩중...");
					}
			    }, 
				success: function(data){
					var s = "";
					if(data.RESULT_LIST.length>0){					
						if(defaultValue==undefined) defaultValue = "";
						if(defaultText==undefined) defaultText = "선택";
						if(selectValue==undefined) selectValue = "";
						console.log("select : defaultValue=",defaultValue,"defaultText=",defaultText,"selectValue=",selectValue);						
						if(tagType == 'select')
						{							
							$("span[gen-id='"+tagName+"']").html("<select name='"+tagName+"' id='"+tagName+"'></select>");
							if(defaultValue != null){
								s += "<option value='" + defaultValue + "'>"+defaultText+"</option>";
							}							
							for(var i=0; i < data.RESULT_LIST.length; i++){								
								s += "<option value='"+data.RESULT_LIST[i].CD_ID+"'>"+data.RESULT_LIST[i].CD_NM+"</option>";
							}
							$("select[id="+tagName+"]").append(s);
							$("select[id="+tagName+"] option[value='"+selectValue+"']").prop('selected', true);
						}
						else if(tagType == 'radio')
						{							
							if(selectValue==undefined) selectValue = "";
							console.log("radio : selectValue=",selectValue);
														
							for(var i=0; i < data.RESULT_LIST.length; i++){
								s += "<span class='ck-bx'>";
								s += "<input type='radio' name='"+tagName+"' id='"+tagName+""+i+"' class='el-radio__original' value='"+data.RESULT_LIST[i].CD_ID+"'/>";
								s += "<label for='"+tagName+""+i+"'><span class='el-radio__input'><span class='el-radio__inner'></span></span>" + data.RESULT_LIST[i].CD_NM + "</label>";
								s += "</span>";
							}
							$("span[gen-id='"+tagName+"']").html(s);
							$("input:radio[name='"+tagName+"'][value='"+selectValue+"']").prop('checked', true);
						}
						else if(tagType == 'checkbox')
						{							
							if(selectValue==undefined) selectValue = "";
							console.log("checkbox : selectValue=",selectValue);
														
							for(var i=0; i < data.RESULT_LIST.length; i++){
								s += "<input type='checkbox' name='"+tagName+"' id='"+tagName+""+i+"' value='"+data.RESULT_LIST[i].CD_ID+"'/>";
								s += "&nbsp;" + data.RESULT_LIST[i].CD_NM + " &nbsp; ";
							}
							$("span[gen-id='"+tagName+"']").html(s);
							var selectArr = selectValue.split(',');
							for(var i in selectArr){
								$("input:checkbox[name='"+tagName+"'][value='"+selectArr[i]+"']").prop('checked', true);
							}
						} 						
					}
				}
					
			});
		});
	});
</script>		

<script type="text/javaScript" language="javascript" defer="defer">
	$(document).ready(function(){
		
		$("input[name='schStrDt']").datepicker('setDate', '-3M');
		$("input[name='schEndDt']").datepicker('setDate', 'today');
		$("input[name='pgmDt']").datepicker('setDate', 'today');
		
		<%-- 페이지 초기화 --%>
		pageRest = function(){
			window.location.reload();
		};
		
		<%-- SELECT BOX생성 --%>
		$.ajax({
			url: '/nrds/SysCdList.do',
			type: 'POST',
			data: {grpCd : 'R0100'},
			async: false,
			success: function(data){
				//검색조건 체크박스
				var s1 ="";
				for(var i=0; i < data.RESULT_LIST.length; i++){
					s1 += "<input type='checkbox' name='searchPgmEdCd"+i+"' id='searchPgmEdCd"+i+"' value='"+data.RESULT_LIST[i].CD_ID+"'/>";
					s1 += "&nbsp;" + data.RESULT_LIST[i].CD_NM + " &nbsp; ";
				}
				$("span[id=searchPgmEdCd]").html(s1);
				
				//프로그램정보 셀렉스박스
				$("span[id=pgmEdCdSpan]").html("<select name='pgmEdCd' id='pgmEdCd'></select>");
				var s2 = "<option value=''>선택</option>";						
				for(var i=0; i < data.RESULT_LIST.length; i++){								
					s2 += "<option value='"+data.RESULT_LIST[i].CD_ID+"'>"+data.RESULT_LIST[i].CD_NM+"</option>";
				}
				$("select[id=pgmEdCd]").append(s2);
				$("span[id=pgmClassNmCd]").html("<select name='pgmClassNmCd' id='pgmClassNmCd'><option>교육분류를 선택해주세요</option></select>");
			}
		});
		
		<%-- 프로그램 검색 조건(체크박스) 선택시 교육과정명 체크박스 표시 --%>
		$("#searchPgmEdCd").change(function(){
			//alert($("#searchPgmEdCd0").is(":checked") +":: "+ $("#searchPgmEdCd1").is(":checked") );
			if($("#searchPgmEdCd0").is(":checked")){
				$.ajax({
					url: '/nrds/SysCdList.do',
					type: 'POST',
					data: {grpCd : 'R0101'},
					async: false,
					success: function(data){
						$("span[id='searchPgmClassNmCd1']").html("");
						var s1 ="";
						for(var i=0; i < data.RESULT_LIST.length; i++){
							s1 += "<input type='checkbox' name='searchPgmClassNmCd"+i+"' id='searchPgmClassNmCd"+data.RESULT_LIST[i].CD_ID+"' value='"+data.RESULT_LIST[i].CD_ID+"'/>";
							s1 += "&nbsp;" + data.RESULT_LIST[i].CD_NM + " &nbsp; ";
						}
						$("span[id=searchPgmClassNmCd1]").html(s1);
					}
				});
				$.ajax({
					url: '/nrds/SysCdList.do',
					type: 'POST',
					data: {grpCd : 'R010101'},
					async: false,
					success: function(data){
						var s2 ="";
						for(var i=0; i < data.RESULT_LIST.length; i++){
							s2 += "<input type='checkbox' name='searchPgmClassSubCd"+i+"' id='searchPgmClassSubCd"+data.RESULT_LIST[i].CD_ID+"' value='"+data.RESULT_LIST[i].CD_ID+"'/>";
							s2 += "&nbsp;" + data.RESULT_LIST[i].CD_NM + " &nbsp; ";
						}
						$("span[id=searchPgmClassNmCd1]").append(s2);
						$("span[id=searchPgmClassNmCd1]").append("<br/>");
					}
				});
				
			} else{
				$("span[id=searchPgmClassNmCd1]").html("");
			}
			
			if($("#searchPgmEdCd1").is(":checked")){
				$.ajax({
					url: '/nrds/SysCdList.do',
					type: 'POST',
					data: {grpCd : 'R0102'},
					async: false,
					success: function(data){
						$("span[id='searchPgmClassNmCd2']").html("");
						var s1 ="";
						for(var i=0; i < data.RESULT_LIST.length; i++){
							s1 += "<input type='checkbox' name='searchPgmClassNmCd2"+i+"' id='searchPgmClassNmCd"+data.RESULT_LIST[i].CD_ID+"' value='"+data.RESULT_LIST[i].CD_ID+"'/>";
							s1 += "&nbsp;" + data.RESULT_LIST[i].CD_NM + " &nbsp; ";
						}
						$("span[id=searchPgmClassNmCd2]").html(s1);
					}
				});
				
				
			} else{
				$("span[id=searchPgmClassNmCd2]").html("");
			}
		});
		
		<%-- 프로그램 정보 교육분류 선택시 교육과정명 셀렉트박스 표시 --%>
		$("#pgmEdCdSpan").change(function(){
			if($("select#pgmEdCd").val() == "R0101"){
				$.ajax({
					url: '/nrds/SysCdList.do',
					type: 'POST',
					data: {grpCd : 'R0101'},
					success: function(data){
						$("span[id='pgmClassNmCd']").html("");
						$("span[id='pgmClassNmCd']").html("<select name='pgmClassNmCd' id='pgmClassNmCd'></select>");
						var s = "<option value=''>선택</option>";						
						for(var i=0; i < data.RESULT_LIST.length; i++){								
							s += "<option value='"+data.RESULT_LIST[i].CD_ID+"'>"+data.RESULT_LIST[i].CD_NM+"</option>";
						}
						$("select[id=pgmClassNmCd]").append(s);
						
					}
				});
				$.ajax({
					url: '/nrds/SysCdList.do',
					type: 'POST',
					data: {grpCd : 'R010101'},
					success: function(data){
						$("span[id='pgmClassSubSpan']").html("");
						$("span[id='pgmClassSubSpan']").html("<select name='pgmClassSubCd' id='pgmClassSubCd'></select>");
						var s = "<option value=''>선택</option>";						
						for(var i=0; i < data.RESULT_LIST.length; i++){								
							s += "<option value='"+data.RESULT_LIST[i].CD_ID+"'>"+data.RESULT_LIST[i].CD_NM+"</option>";
						}
						$("select[id=pgmClassSubCd]").append(s);
						
					}
				});
			}else if($("select#pgmEdCd").val() == "R0102"){
				$.ajax({
					url: '/nrds/SysCdList.do',
					type: 'POST',
					data: {grpCd : 'R0102'},
					success: function(data){
						$("span[id='pgmClassNmCd']").html("");
						$("span[id='pgmClassSubSpan']").html("");
						$("span[id='pgmClassNmCd']").html("<select name='pgmClassNmCd' id='pgmClassNmCd'></select>");
						var s = "<option value=''>선택</option>";						
						for(var i=0; i < data.RESULT_LIST.length; i++){								
							s += "<option value='"+data.RESULT_LIST[i].CD_ID+"'>"+data.RESULT_LIST[i].CD_NM+"</option>";
						}
						$("select[id=pgmClassNmCd]").append(s);
					}
				});
				
			}
		});
		
		<%-- 프로그램 목록 조회 --%>
		getRecyclePrgList = function(){
			$.ajax({
				url : '/ajaxGetRecyclePrgList.do',
				type : 'POST',
				data : $("#prgListForm").serialize(),
				success : function(res){
					$("div[id='recyclePrgList']").html(res);
				},
				error : function(xhr, status){
					console.log(xhr);
				}
			});
		},
		<%-- 검색버튼 클릭 --%>
		seachPrgList = function(){
			$("input[name='pgmPageNo']").val('1');
			getRecyclePrgList();
		},
		<%-- 프로그램 정보 등록 --%>
		addRecyclePrg = function(){
			 if($("select[name='pgmEdCd']").val() == "" || $("select[name='pgmEdCd']").val() == null){
				alert("교육분류는 필수 입력 항목입니다.");
				$("select[name='pgmEdCd']").focus();					return;
			}
			if($("select[name='pgmClassNmCd']").val() == "" || $("select[name='pgmClassNmCd']").val() == null){
				alert("교육과정명는 필수 입력 항목입니다.");
				$("select[name='pgmClassNmCd']").focus();					return;
			}
			if($("input[name='pgmStartDt']").val() == ""){
				alert("교육기간은 시작일필수 입력 항목입니다.");
				$("input[name='pgmStartDt']").focus();					return;
			}
			if($("input[name='pgmEndDt']").val() == ""){
				alert("교육기간 종료일은 필수 입력 항목입니다.");
				$("input[name='pgmEndDt']").focus();					return;
			}
			if($("input[name='pgmClassStartTm']").val() == ""){
				alert("교육일시 시작시간은 필수 입력 항목입니다.");
				$("input[name='pgmClassStartTm']").focus();					return;
			}
			if($("input[name='pgmClassEndTm']").val() == ""){
				alert("교육일시 종료시간은 필수 입력 항목입니다.");
				$("input[name='pgmClassEndTm']").focus();					return;
			}
			if($("select[name='pgmMngUsrId']").val() == ""){
				alert("담당자는 필수 입력 항목입니다.");
				$("select[name='pgmMngUsrId']").focus();				return;
			}
			if($("input[name='pgmSession']").val() == ""){
				alert("회기는 필수 입력 항목입니다.");
				$("input[name='pgmSession']").focus();					return;
			}
			if($("input[name='pgmClass']").val() == ""){
				alert("차수는 필수 입력 항목입니다.");
				$("input[name='pgmClass']").focus();					return;
			}
			if($("input[name='pgmMainLec']").val() == ""){
				alert("주강사는 필수 입력 항목입니다.");
				$("input[name='pgmMainLec']").focus();					return;
			}
			if($("input[name='pgmSubject']").val() == ""){
				alert("주제는 필수 입력 항목입니다.");
				$("input[name='pgmSubject']").focus();					return;
			}
			if($("textarea[name='pgmCtnt']").val() == ""){
				alert("프로그램 내용은 필수 입력 항목입니다.");
				$("textarea[name='pgmCtnt']").focus();				return;
			}
			if($("textarea[name='pgmRst']").val() == ""){
				alert("프로그램 결과는 필수 입력 항목입니다.");
				$("textarea[name='pgmRst']").focus();				return;
			} 

			$.ajax({
				url : '/ajaxRecyclePrgAdd.do',
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
		<%-- 재활교육 프로그램 정보 불러오기 --%>
		reSetPgmForm = function(obj){
			if(obj == undefined){
				$("button#delBtnYes").hide();
				$("button#delBtnNo").show();
				$("button#excelButNo").show();
				$("button#excelButYes").hide();

				$("select[name='pgmEdCd']").val("").prop("selected", true);
				$("select[name='pgmCd']").val("").prop("selected", true);
				$("input[name='pgmDt']").datepicker('setDate', 'today');
				$("input[name='pgmFmTm']").val("09:00");
				$("input[name='pgmToTm']").val("18:00");
				$("select[name='mngUsrId']").val("<c:out value="${loginUserId}" />").prop("selected", true);
				$("input[name='siteNm']").val("<c:out value="${loginSiteNm}" />");
				$("input[name='pgmSession']").val("");
				$("input[name='pgmTeacher']").val("");
				$("input[name='pgmSubject']").val("");
				$("input[name='pgmGoal']").val("");
				$("textarea[name='pgmCtnt']").val("");
				$("textarea[name='pgmRst']").val("");
				$("input[name='file']").val("");
				$("input[name='fileNameFlag']").val("N");
				$("div#fileName").text("");
			}else{
				$.ajax({
					url : '/ajaxGetRecyclePrgInfo.do',
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

						$("select[name='pgmEdCd']").val(prgInfo.PGM_TP_CD).prop("selected", true);
						$("select[name='pgmClassNmCd']").val(prgInfo.PGM_CD).prop("selected", true);
						$("select[name='pgmClassSubCd']").val(prgInfo.PGM_CD).prop("selected", true);
						$("input[name='pgmSession']").val(prgInfo.pgmSession);
						$("input[name='pgmClass']").val(prgInfo.pgmClass);
						$("input[name='pgmMainLec']").val(prgInfo.pgmMainLec);
						$("input[name='pgmSubLec']").val(prgInfo.pgmSubLec);
						$("input[name='pgmStartDt']").datepicker('setDate', setDt);
						$("input[name='pgmEndDt']").datepicker('setDate', setDt);
						$("input[name='pgmFmTm']").val(prgInfo.PGM_FM_TM);
						$("input[name='pgmToTm']").val(prgInfo.PGM_TO_TM);
						
						$("input[name='pgmAgent']").val(prgInfo.pgmAgent);
						$("select[name='pgmMngUsrId']").val(prgInfo.pgmMngUsrId);
						
						$("input[name='pgmClassStartDt']").datepicker('setDate', pgmClassStartDt);
						$("input[name='pgmClassEndDt']").datepicker('setDate', pgmClassEndDt);
						$("input[name='pgmClassStartTm']").val(prgInfo.pgmClassStartTm);
						$("input[name='pgmClassEndTm']").val(prgInfo.pgmClassEndTm);
						
						$("input[name='pgmSubject']").val(prgInfo.pgmSubject);
						$("input[name='pgmGoal']").val(prgInfo.pgmGoal);
						$("input[name='pgmCtnt']").val(prgInfo.pgmCtnt);
						$("input[name='pgmRst']").val(prgInfo.pgmRst);
						$("textarea[name='pgmEmp']").val(prgInfo.pgmEmp);
						$("textarea[name='pgmVol']").val(prgInfo.pgmVol);

						if(prgInfo.fileList != undefined && prgInfo.fileList != ''){
							for(let i=0 ; i<prgInfo.fileList.length ; i++){
								$("div#fileName").html("<a href='javaScript:downloadFile(\"" + prgInfo.fileList[i].FILE_ID + "\", \"" + prgInfo.fileList[i].FILE_SEQ + "\");'>" + prgInfo.fileList[i].ORIGNL_FILE_NM + "</a>"
													 + "&nbsp;&nbsp;<a href='javaScript:deleteFile(\"fileName\");'>삭제</a>"
													  );
							}
						}else{
							$("div#fileName").text("");
						}
						$("input[name='fileNameFlag']").val("N");
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
					listType : "MEDIC",
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

		
		
		
		getRecyclePrgList();
		getPgmMemList();
	});
	
	
</script>
<!-- 페이지 타이틀 -->
<div class="tit-area">
	<h1><i class="el-icon-s-order" style="color: rgb(0, 108, 185);"></i> 주간재활 프로그램 </h1>
</div>
<!-- // 페이지 타이틀 -->
<!-- 상단 버튼 -->
<div class="top-right-btn">
	<button type="button" onclick="javaScript:addRecyclePrg();" class="el-button normal el-button--primary el-button--small is-plain">
		<i class="el-icon-download"></i> <span>저장</span>
	</button>
	<button type="button" onclick="javaScript:newBtn();" class="el-button normal el-button--default el-button--small is-plain" style="margin-left: 8px;">
		<i class="el-icon-circle-plus-outline"></i> <span>신규</span>
	</button>
	<button disabled="disabled" type="button" id="delBtnNo" class="el-button normal el-button--default el-button--small is-disabled is-plain">
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
									<input type="text" name="schStrDt" class="el-input__inner datepicker" placeholder="시작" style="width: 110px;">
								</div>
								<span>~</span>
								<div class="dat-pk">
									<i class="el-input__icon el-icon-date"></i>
									<input type="text" name="schEndDt" class="el-input__inner datepicker" placeholder="종료" style="width: 110px;">
								</div>
							</td>
							
							<td>
								<span id="searchPgmEdCd"></span>
								<button type="button" onclick="javaScript:seachPrgList();" class="el-button el-button--primary el-button--small is-plain" style="margin-left: 8px; padding: 8px 15px;">
									<i class="el-icon-search"></i><span>검색</span>
								</button>
							</td>
						</tr>
						<tr>
							<td colspan="4"><span id="searchPgmClassNmCd1"></span>
											<span id="searchPgmClassNmCd2"></span>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div id="recyclePrgList"></div>
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
								<th><span class="required">*</span> 교육분류</th>
								<td><span id="pgmEdCdSpan"></span>
								</td>
								<th><span class="required">*</span> 교육과정명</th>
								<td>
									<span id="pgmClassNmCd"></span>
									<span id="pgmClassSubSpan"></span>
								</td>
								<th><span class="required">*</span> 회기</th>
								<td><input type="text" name="pgmSession" class="el-input__inner" placeholder="회기" style="width: 80px;"></td>
								<th><span class="required">*</span> 차수</th>
								<td><input type="text" name="pgmClass" class="el-input__inner" placeholder="차수" style="width: 80px;"></td>
							</tr>
						</tbody>
					</table>
					<table class="w-auto wr-form">
						<tbody>
							<tr>
								<th> <span class="required">*</span> &nbsp;주강사&nbsp;&nbsp;</th>
								<td>
									<input type="text" name="pgmMainLec" placeholder="강사이름" style="width: 120px;"> 
								</td>
								<th> <span class="required">*</span> 보조강사</th>
								<td> 
									<input type="text" name="pgmSubLec" placeholder="강사이름" style="width: 120px;">
								</td>
								<th> <span class="required">*</span> 교육기간</th>
								<td>
									<div class="dat-pk">
										<i class="el-input__icon el-icon-date"></i>
										<input type="text" name="pgmStartDt" class="el-input__inner datepicker" placeholder="날짜" style="width: 120px;">
									</div>
									<div class="tm-in">
										<i class="el-input__icon el-icon-time"></i>
										<input type="text" name="pgmFmTm" value="09:00" class="el-input__inner timepicker" placeholder="시작" style="width: 80px;">
									</div>
									<span>~</span>
									<div class="dat-pk">
										<i class="el-input__icon el-icon-date"></i>
										<input type="text" name="pgmEndDt" class="el-input__inner datepicker" placeholder="날짜" style="width: 120px;">
									</div>
									<div class="tm-in">
										<i class="el-input__icon el-icon-time"></i>
										<input type="text" name="pgmToTm" value="18:00" class="el-input__inner timepicker" placeholder="종료" style="width: 80px;">
									</div>
								</td>
							</tr>
							<tr>
								<th>기관명</th>
								<td>
									<div class="dsp-ibk tac">
										<input type="text" name="pgmAgent" value="<c:out value="${loginSiteNm}" />" readonly class="el-input__inner" style="width: 200px;" placeholder="기관명">
									</div>
								</td>
								<th> <span class="required">*</span> 담당자</th>
								<td>
									<input type="text" name="pgmMngUsrId" value="<c:out value="${loginUserNm}" />" readonly class="el-input__inner" style="width: 100px;" placeholder="담당자">
								</td>
								<th> <span class="required">*</span> 교육일시</th>
								<td>
									<div class="dat-pk">
										<i class="el-input__icon el-icon-date"></i>
										<input type="text" name="pgmClassStartDt" class="el-input__inner datepicker" placeholder="날짜" style="width: 120px;">
									</div>
									<div class="tm-in">
										<i class="el-input__icon el-icon-time"></i>
										<input type="text" name="pgmClassStartTm" value="09:00" class="el-input__inner timepicker" placeholder="시작" style="width: 80px;">
									</div>
										<span>~</span>
									<div class="dat-pk">
										<i class="el-input__icon el-icon-date"></i>
										<input type="text" name="pgmClassEndDt" class="el-input__inner datepicker" placeholder="날짜" style="width: 120px;">
									</div>
									<div class="tm-in">
										<i class="el-input__icon el-icon-time"></i>
										<input type="text" name="pgmClassEndTm" value="18:00" class="el-input__inner timepicker" placeholder="종료" style="width: 80px;">
									</div>
								</td>
							</tr>
							<tr>
								<th> <span class="required">*</span>교육주제</th>
								<td colspan='5'><input type="text" name="pgmSubject" placeholder="주제" style="width: 100%;" /></td>
							</tr>
							<tr>
								<th>교육목표</th>
								<td colspan='5'><input type="text" name="pgmGoal" placeholder="목표" style="width: 100%;" /></td>
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
								<th>
									<span class="required">*</span>교육내용<br>
									<button type="button" onclick="javaScript:viewCtnt('pgmCtnt', '');" class="el-button el-button--success el-button--mini is-plain" style="padding: 4px 6px;">
										<i class="el-icon-search"></i>
									</button>
								</th>
								<td><textarea name="pgmCtnt" placeholder="내용" style="height: 110px;"></textarea></td>
							</tr>
							<tr>
								<th>
									교육결과<br>
									<button type="button" onclick="javaScript:viewCtnt('pgmRst', '');" class="el-button el-button--success el-button--mini is-plain" style="padding: 4px 6px;">
										<i class="el-icon-search"></i>
									</button>
								</th>
								<td><textarea name="pgmRst" placeholder="결과" style="height: 110px;"></textarea></td>
							</tr>
							<tr>
								<th class="v-top">첨부</th>
								<td>
									<div id="fileName"></div>
									<input type="hidden" name="fileNameFlag" value="N" />
									<input type="file" id="file" name="file" placeholder="첨부" style="width: 100%;">
								</td>
							</tr>
						</tbody>
					</table>





				</div>
			</div>
			<!-- 인적자원등록 -->
			<div class="section pdn">
				<div class="el-card_header">
					<h2><i class="el-icon-s-opportunity"></i> 인적자원등록</h2>
				</div>
				<div class="el-card_body">
					<table class="wr-form">
						<colgroup>
							<col width="7%">
							<col width="*">
						</colgroup>
						<tbody>
							<tr>
								<th>직원</th>
								<td><input type="text" name="pgmEmp" style="width: 100%;" /></td>
							</tr>
							<tr>
								<th>자원봉사자</th>
								<td><input type="text" name="pgmVol" style="width: 100%;" /></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<!-- // 인적자원등록 -->
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
									<th>대상자등록번호</th>
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
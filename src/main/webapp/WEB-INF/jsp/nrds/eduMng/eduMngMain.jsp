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
				$("span[id=pgmEdCdSpan]").html("<select name='pgmEdCd' id='pgmEdCd' style='width: 200px;'></select>");
				var s2 = "<option value=''>선택</option>";						
				for(var i=0; i < data.RESULT_LIST.length; i++){								
					s2 += "<option value='"+data.RESULT_LIST[i].CD_ID+"'>"+data.RESULT_LIST[i].CD_NM+"</option>";
				}
				$("select[id=pgmEdCd]").append(s2);
				$("span[id=pgmClassNmCd]").html("<select name='pgmClassNmCd' id='pgmClassNmCd'><option>교육분류를 선택해주세요</option></select>");
			}
		});
		
		getSyscdCall = function(){
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
						}
					});
				}
			});
			
		
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
			
			seachPrgList();
		};
		
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
		
		<%-- 검색버튼 클릭 --%>
		seachPrgList = function(){
			$("input[name='pgmPageNo']").val('1');
			getEduMngPrgList();
		};
		
		<%-- 프로그램 목록 조회 --%>
		getEduMngPrgList = function(){
			$.ajax({
				url : '/nrds/eduMng/ajaxGetEduMngPrgList.do',
				type : 'POST',
				data : $("#prgListForm").serialize(),
				success : function(res){
					$("div[id='recyclePrgList']").html(res);
				},
				error : function(xhr, status){
					console.log(xhr);
				}
			});
		};
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
			if($("input[name='pgmId']").val() == ""){
				$.ajax({
					url : '/nrds/ajaxRecyclePrgAdd.do',
					type : 'POST',
					processData : false,
					contentType : false,
					enctype : 'multipart/form-data',
					data : new FormData($("#pgmInfoForm")[0]),
					success : function(res){
						if(res.err != "Y"){
							alert("데이터가 저장되었습니다.");

							seachPrgList();
							reSetPgmForm();
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
			} else if($("input[name='pgmId']").val() !== ""){
				$.ajax({
					url : '/nrds/eduMng/ajaxEduMngModifi.do',
					type : 'POST',
					processData : false,
					contentType : false,
					enctype : 'multipart/form-data',
					data : new FormData($("#pgmInfoForm")[0]),
					success : function(res){
						if(res.err != "Y"){
							alert("데이터가 저장되었습니다.");
							reSetPgmForm();
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
			}
			
		};
		
		<%-- 재활교육 프로그램 정보 불러오기 --%>
		reSetPgmForm = function(obj){
			if(obj == undefined){
				$("button#delBtnYes").hide();
				$("button#delBtnNo").show();
				$("button#excelButNo").show();
				$("button#excelButYes").hide();

				$("input[name='pgmId']").val("");
				$("select[name='pgmEdCd']").val("").prop("selected", true);
				$("select[name='pgmClassNmCd']").html("<option>교육분류를 선택해주세요</option>");
				$("#pgmClassSubSpan").html("");
				$("input[name='pgmSession']").val("");
				$("input[name='pgmClass']").val("");
				$("input[name='pgmClassDur']").val("");
				$("input[name='pgmDur']").val("");
				
				$("input[name='pgmMainLec']").val("");
				$("input[name='pgmSubLec']").val("");
				//$("input[name='pgmStartDt']").datepicker('setDate', 'today');
				$("input[name='pgmStartDt']").val("");
				$("input[name='pgmStartTm']").val("09:00");
				$("input[name='pgmEndDt']").val("");
				$("input[name='pgmEndTm']").val("18:00");
				$("input[name='pgmClassStartDt']").val("");
				$("input[name='pgmClassStartTm']").val("09:00");
				$("input[name='pgmClassEndDt']").val("");
				$("input[name='pgmClassEndTm']").val("18:00");
				
				$("input[name='pgmSubject']").val("");
				$("input[name='pgmGoal']").val("");
				$("textarea[name='pgmCtnt']").val("");
				$("textarea[name='pgmRst']").val("");
				
				$("input[name='file']").val("");
				$("input[name='fileNameFlag']").val("N");
				$("div#fileName").text("");
				
				$("#pgmEmp").val("");
				$("#pgmVol").val("");
			}else{
				$.ajax({
					url : '/nrds/ajaxGetRecyclePrgInfo.do',
					type : 'POST',
					data : {
						pgmId : obj.pgmId
					},
					success : function(res){
						$("button#delBtnYes").show();
						$("button#delBtnNo").hide();
						$("button#excelButNo").hide();
						$("button#excelButYes").show();

						var recyclePrgInfo = res.recyclePrgInfo;
						//var setDt = new Date(recyclePrgInfo.PGM_DT.substr(0, 4), recyclePrgInfo.PGM_DT.substr(4, 2), recyclePrgInfo.PGM_DT.substr(6, 2));
						//setDt.setMonth(setDt.getMonth() - 1);

						$("input[name='pgmId']").val(recyclePrgInfo.pgmId);
						$("select[name='pgmEdCd']").val(recyclePrgInfo.pgmEdCd).prop("selected", true);
						settingPgmEdCd(recyclePrgInfo.pgmClassNmCd, recyclePrgInfo.pgmClassSubCd);
						$("select[name='pgmClassNmCd']").val(recyclePrgInfo.pgmClassNmCd).prop("selected", true);
						$("select[name='pgmClassSubCd']").val(recyclePrgInfo.pgmClassSubCd).prop("selected", true);
						$("input[name='pgmSession']").val(recyclePrgInfo.pgmSession);
						$("input[name='pgmClass']").val(recyclePrgInfo.pgmClass);
						$("input[name='pgmDur']").val(recyclePrgInfo.pgmDur);
						$("input[name='pgmClassDur']").val(recyclePrgInfo.pgmClassDur);
						
						$("input[name='pgmMainLec']").val(recyclePrgInfo.pgmMainLec);
						$("input[name='pgmSubLec']").val(recyclePrgInfo.pgmSubLec);
						$("input[name='pgmStartDt']").datepicker('setDate', recyclePrgInfo.pgmStartDt);
						$("input[name='pgmEndDt']").datepicker('setDate', recyclePrgInfo.pgmEndDt);
						$("input[name='pgmStartTm']").val(recyclePrgInfo.pgmStartTm);
						$("input[name='pgmEndTm']").val(recyclePrgInfo.pgmEndTm);
						
						$("input[name='pgmAgent']").val(recyclePrgInfo.pgmAgent);
						$("select[name='pgmMngUsrId']").val(recyclePrgInfo.pgmMngUsrId);
						
						$("input[name='pgmClassStartDt']").datepicker('setDate', recyclePrgInfo.pgmClassStartDt);
						$("input[name='pgmClassEndDt']").datepicker('setDate', recyclePrgInfo.pgmClassEndDt);
						$("input[name='pgmClassStartTm']").val(recyclePrgInfo.pgmClassStartTm);
						$("input[name='pgmClassEndTm']").val(recyclePrgInfo.pgmClassEndTm);
						
						$("input[name='pgmSubject']").val(recyclePrgInfo.pgmSubject);
						$("input[name='pgmGoal']").val(recyclePrgInfo.pgmGoal);
						$("textarea[name='pgmCtnt']").val(recyclePrgInfo.pgmCtnt);
						$("textarea[name='pgmRst']").val(recyclePrgInfo.pgmRst);
						$("input[name='pgmEmp']").val(recyclePrgInfo.pgmEmp);
						$("input[name='pgmVol']").val(recyclePrgInfo.pgmVol);
						
						if(recyclePrgInfo.fileList != undefined && recyclePrgInfo.fileList != ''){
							for(let i=0 ; i<recyclePrgInfo.fileList.length ; i++){
								$("div#fileName").html("<a href='javaScript:downloadFile(\"" + recyclePrgInfo.fileList[i].FILE_ID + "\", \"" + recyclePrgInfo.fileList[i].FILE_SEQ + "\");'>" + recyclePrgInfo.fileList[i].ORIGNL_FILE_NM + "</a>"
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

				//getPgmMemList(obj.pgmDt, obj.pgmCd);
			}
		}
		
		<%-- 신규--%>
		newBtn = function(){
			reSetPgmForm();
		};
		
		<%-- 삭제 --%>
		pgmDel = function(){
			if($("input[name='pgmId']").val() == ""){
				alert("프로그램이 선택 되지 않았습니다.");			return;
			}

			if(confirm("선택한 재활교육프로그램을 삭제하시겠습니까?")){
				$.ajax({
					url : '/nrds/eduMng/ajaxEduMngDel.do',
					type : 'POST',
					data : {
						pgmId : $("input[name='pgmId']").val()
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
		};
		
		<%-- 상세보기에서 교육분류에 따른 교육과정명 표시 --%>
		var settingPgmEdCd = function(classNmCd, classSubCd){
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
						$("select[id=pgmClassNmCd]").val(classNmCd);
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
						$("select[id=pgmClassSubCd]").val(classSubCd);
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
						$("select[id=pgmClassNmCd]").val(classNmCd);
					}
				});
				
			}
		};
		
		getSyscdCall();
	});
</script>
<!-- 페이지 타이틀 -->
<div class="tit-area">
	<h1><i class="el-icon-s-order" style="color: rgb(0, 108, 185);"></i> 교육관리</h1>
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
<%-- <form name="cslForm" id="cslForm" enctype="multipart/form-data">
<input type="hidden" name="cslNo" value="" /> --%>
<div class="formline">
	<form name="prgListForm" id="prgListForm">
		<input type="hidden" name="pgmPageNo" value="1" />
	<!-- 재활교육관리 -->
	<div class="section">
		<table class="w-auto">
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
					<td colspan="3">
						<!-- <span id="searchPgmEdCd"></span> -->
						&nbsp;&nbsp;
						<span id="searchPgmClassNmCd1"></span>
						<span id="searchPgmClassNmCd2"></span>
					</td>
					<td>
						<button type="button" onclick="javaScript:seachPrgList();" class="el-button el-button--primary el-button--small is-plain" style="margin-left: 8px; padding: 8px 15px;">
							<i class="el-icon-search"></i><span>검색</span>
						</button>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	</form>
	<!-- // 재활교육관리 -->
	<div class="section-sha" style="min-width: 1840px;">
		<div class="el-tabs_content">
			<div class="tab-tb-box" style="height: 400px;" id="recyclePrgList">
				<div class="no-data">조회된 데이터가 없습니다.</div>
			</div>
		</div>
	</div>
	<%-- <div class="section-sha" style="min-width: 1840px;">
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
								<th>번호</th>
								<th>기관명</th>
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
					
				</div>
			</div>
			
		</div>
	</div> --%>
</div>
</form>
<!-- 상담내용 팝업 -->
	<div class="center-pg">
		<form name="pgmInfoForm" id="pgmInfoForm" enctype="multipart/form-data">
		<input type="hidden" name="pgmId" id="pgmId">		
		<div class="l">
				<div class="section pdn">
						<div class="el-card_header">
							<h2><i class="el-icon-s-opportunity"></i> 과정 정보</h2>					
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
									</tr>
									<tr>
										<th> <span class="required">*</span> 교육기간</th>
										<td colspan="3">
											<div class="dat-pk">
												<i class="el-input__icon el-icon-date"></i>
												<input type="text" name="pgmStartDt" class="el-input__inner datepicker" placeholder="날짜" style="width: 120px;">
											</div>
											<div class="tm-in">
												<i class="el-input__icon el-icon-time"></i>
												<input type="text" name="pgmStartTm" class="el-input__inner timepicker" placeholder="시작" style="width: 80px;">
											</div>
											<span>~</span>
											<div class="dat-pk">
												<i class="el-input__icon el-icon-date"></i>
												<input type="text" name="pgmEndDt" class="el-input__inner datepicker" placeholder="날짜" style="width: 120px;">
											</div>
											<div class="tm-in">
												<i class="el-input__icon el-icon-time"></i>
												<input type="text" name="pgmEndTm" class="el-input__inner timepicker" placeholder="종료" style="width: 80px;">
											</div>
										</td>
										<th><span class="required">*</span> 차수</th>
										<td><input type="text" name="pgmClass" class="el-input__inner" placeholder="차수" style="width: 80px;"></td>
									</tr>
									<tr>
										<th> <span class="required">*</span> 교육일수</th>
										<td><input type="text" name="pgmDur" class="el-input__inner" placeholder="교육일수" style="width: 80px;"></td>
										<th> 교육시간</th>
										<td><input type="text" name="pgmClassDur" class="el-input__inner" placeholder="교육시간" style="width: 80px;"></td>
									</tr>
								</tbody>
							</table>
						</div>
				</div>
		</div>
		<div class="r">
			<!-- 프로그램 정보 -->
			<div class="section pdn">
				<div class="el-card_header">
					<h2><i class="el-icon-s-opportunity"></i> 교육 정보</h2>					
				</div>
				<div class="el-card_body">
					<table class="w-auto wr-form">
						<tbody>
							<tr>
								<th> <span class="required">*</span> 교육일시</th>
								<td>
									<div class="dat-pk">
										<i class="el-input__icon el-icon-date"></i>
											<input type="text" name="pgmClassStartDt" class="el-input__inner datepicker" placeholder="날짜" style="width: 120px;">
									</div>
									<div class="tm-in">
										<i class="el-input__icon el-icon-time"></i>
										<input type="text" name="pgmClassStartTm" class="el-input__inner timepicker" placeholder="시작" style="width: 80px;">
									</div>
									<span>~</span>
									<div class="dat-pk">
										<i class="el-input__icon el-icon-date"></i>
										<input type="text" name="pgmClassEndDt" class="el-input__inner datepicker" placeholder="날짜" style="width: 120px;">
									</div>
									<div class="tm-in">
										<i class="el-input__icon el-icon-time"></i>
										<input type="text" name="pgmClassEndTm" class="el-input__inner timepicker" placeholder="종료" style="width: 80px;">
									</div>
								</td>
								<th><span class="required">*</span> 회기</th>
								<td><input type="text" name="pgmSession" class="el-input__inner" placeholder="회기" style="width: 80px;"></td>
								
							</tr>
	
							<tr>
								<th> <span class="required">*</span>교육주제</th>
								<td colspan='5'><input type="text" name="pgmSubject" placeholder="주제" style="width: 100%;" /></td>
							</tr>
							<tr>
								<th> <span class="required">*</span> &nbsp;주강사&nbsp;&nbsp;</th>
								<td>
									<input type="text" name="pgmMainLec" placeholder="강사이름" style="width: 120px;"> 
								</td>
								<th> <span class="required">*</span> 보조강사</th>
								<td> 
									<input type="text" name="pgmSubLec" placeholder="강사이름" style="width: 120px;">
								</td>
								
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		</form>
	</div>
<!-- // 상담내용 팝업 -->
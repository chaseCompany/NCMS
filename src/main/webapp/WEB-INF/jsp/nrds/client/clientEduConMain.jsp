<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kr.co.chase.ncms.common.ConstantObject" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javaScript" language="javascript" defer="defer">
	$(document).ready(function(){
		$("input[name='reqDt']").datepicker('setDate', 'today');
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
					$("input[name='reFunName']").val("getEdLastInfo");
					layerPopupOpen('memberPopUp');
				},
				error : function(xhr, status){
					console.log(xhr);
				}
			});
		}
		<%-- 회원의뢰 정보 조회 --%>
		getEdLastInfo = function(tagMbrNo){
			setButton();
			newMemInfo();

			$.ajax({
				url : '<c:url value="/nrds/ajaxEdMbrEdLastInfoJson.do"/>',
				type : 'POST',
				data : {
					mbrNo : tagMbrNo
				},
				success : function(json){
					var info = json.mbrEdInfo;

					if(info.MBR_NO != ""){
						$("input[name='infoMbrNo']").val(info.MBR_NO);
						$("input[name='infoMbrNm']").val(info.MBR_NM);
						$("input[name='infoGendNm']").val(info.GEND_NM);
						$("input[name='infoAge']").val(info.AGE);
						$("input[name='infoReqOrg']").val(info.REQ_ORG);
						if(info.TEL_NO1 != ''){
							$("input[name='infoTelNo']").val(info.TEL_NO1 + "-" + info.TEL_NO2 + "-" + info.TEL_NO3);
						}
						$("input[name='infoCreIdSiteCd']").val(info.SITE_NM);

						getMbrInfo(tagMbrNo);
						getEdMbrEdList(tagMbrNo);
					}
				},
				error : function(xhr, status){
					console.log(xhr);
				}
			});
		}
		<%-- 회원 기본 정보 조회 --%>
		getMbrInfo = function(tagMbrNo, tagMbrEdId){
			$.ajax({
				url : '<c:url value="/nrds/ajaxEdInfoJson.do"/>',
				type : 'POST',
				data : {
					mbrNo : tagMbrNo,
					mbrEdId : tagMbrEdId
				},
				success : function(json){
					var info = json.mbrEdInfo;

					if(info.MBR_NO != ""){
						$("input[name='mbrNo']").val(info.MBR_NO);
						$("input[name='mbrNm']").val(info.MBR_NM);
						$("select[name='gendCd']").val(info.GEND_CD).prop("selected", true);
						$("input[name='juminNo1']").val(info.JUMIN_NO1);
						$("input[name='age']").val(info.AGE);
						$("input[name='telNo1']").val(info.TEL_NO1);
						$("input[name='telNo2']").val(info.TEL_NO2);
						$("input[name='telNo3']").val(info.TEL_NO3);
						$("input[name='zipCd']").val(info.ZIP_CD);
						$("input[name='addr1']").val(info.ADDR1);
						$("input[name='addr2']").val(info.ADDR2);
						$("select[name='jobCd']").val(info.JOB_CD).prop("selected", true);

						if(info.MBR_ED_ID != '' && info.MBR_ED_ID != undefined){
							$("input[name='rZipCd']").val(info.R_ZIP_CD);
							$("input[name='rAddr1']").val(info.R_ADDR1);
							$("input[name='rAddr2']").val(info.R_ADDR2);

							$("input[name='mbrEdId']").val(info.MBR_ED_ID);
							$("input[name='reqDt']").val(info.REQ_DT);
							$("input[name='docNo']").val(info.DOC_NO);
							$("input[name='caseNo']").val(info.CASE_NO);
							if(info.CRIME_TYPE01 == "1"){
								$("input[name='crimeType01']").prop("checked", true);
							}else{
								$("input[name='crimeType01']").prop("checked", false);
							}
							if(info.CRIME_TYPE02 == "1"){
								$("input[name='crimeType02']").prop("checked", true);
							}else{
								$("input[name='crimeType02']").prop("checked", false);
							}
							if(info.CRIME_TYPE03 == "1"){
								$("input[name='crimeType03']").prop("checked", true);
							}else{
								$("input[name='crimeType03']").prop("checked", false);
							}
							if(info.CRIME_TYPE04 == "1"){
								$("input[name='crimeType04']").prop("checked", true);
								$("input[name='crimeTypeEtc']").val(info.CRIME_TYPE_ETC);
								$("input[name='crimeTypeEtc']").attr("disabled", false);
							}else{
								$("input[name='crimeType04']").prop("checked", false);
								$("input[name='crimeTypeEtc']").val("");
								$("input[name='crimeTypeEtc']").attr("disabled", true);
							}
							if(info.DRUG1 == "1"){
								$("input[name='drug1']").prop("checked", true);
								$("input[name='drug1Etc']").val(info.DRUG1_ETC);
								$("input[name='drug1Etc']").attr("disabled", false);
							}else{
								$("input[name='drug1']").prop("checked", false);
								$("input[name='drug1Etc']").val("");
								$("input[name='drug1Etc']").attr("disabled", true);
							}
							if(info.DRUG2 == "1"){
								$("input[name='drug2']").prop("checked", true);
								$("input[name='drug2Etc']").val(info.DRUG2_ETC);
								$("input[name='drug2Etc']").attr("disabled", false);
							}else{
								$("input[name='drug2']").prop("checked", false);
								$("input[name='drug2Etc']").val("");
								$("input[name='drug2Etc']").attr("disabled", true);
							}
							if(info.DRUG3 == "1"){
								$("input[name='drug3']").prop("checked", true);
								$("input[name='drug3Etc']").val(info.DRUG3_ETC);
								$("input[name='drug3Etc']").attr("disabled", false);
							}else{
								$("input[name='drug3']").prop("checked", false);
								$("input[name='drug3Etc']").val("");
								$("input[name='drug3Etc']").attr("disabled", true);
							}
							if(info.DRUG4 == "1"){
								$("input[name='drug4']").prop("checked", true);
								$("input[name='drug4Etc']").val(info.DRUG4_ETC);
								$("input[name='drug4Etc']").attr("disabled", false);
							}else{
								$("input[name='drug4']").prop("checked", false);
								$("input[name='drug4Etc']").val("");
								$("input[name='drug4Etc']").attr("disabled", true);
							}
							if(info.USE_TERM != ''){
								$("input[name='useTerm']").each(function(){
									if($(this).val() == info.USE_TERM){
										$(this).prop("checked", true);
									}
								});
							}
							if(info.REQ_DETAILS01 == "1"){
								$("input[name='reqDetails01']").prop("checked", true);
							}else{
								$("input[name='reqDetails01']").prop("checked", false);
							}
							if(info.REQ_DETAILS02 == "1"){
								$("input[name='reqDetails02']").prop("checked", true);
							}else{
								$("input[name='reqDetails02']").prop("checked", false);
							}
							if(info.REQ_DETAILS03 == "1"){
								$("input[name='reqDetails03']").prop("checked", true);
							}else{
								$("input[name='reqDetails03']").prop("checked", false);
							}
							if(info.REQ_DETAILS04 == "1"){
								$("input[name='reqDetails04']").prop("checked", true);
								$("input[name='reqDetailsEtc']").val(info.REQ_DETAILS_ETC);
								$("input[name='reqDetailsEtc']").attr("disabled", false);
							}else{
								$("input[name='reqDetails04']").prop("checked", false);
								$("input[name='reqDetailsEtc']").val("");
								$("input[name='reqDetailsEtc']").attr("disabled", true);
							}
							$("input[name='reqOrg']").val(info.REQ_ORG);
							$("input[name='prosNo']").val(info.PROS_NO);
							$("input[name='detective']").val(info.DETECTIVE);
							$("input[name='fileId']").val("");
							if(info.ORIGNL_FILE_NM != '' && info.ORIGNL_FILE_NM != undefined){
								var fileNameHtml = '<a href="javaScript:downloadFile(\'' + info.FILE_ID + '\', \'' + info.FILE_SEQ + '\');">'
												 + info.ORIGNL_FILE_NM
												 + "</a>&nbsp;&nbsp;&nbsp;<a href='javaScript:setFileDelFlag();'>삭제</a>";
								$("div#fileInfo").html(fileNameHtml);
							}else{
								$("div#fileInfo").html("");
							}

							setButton("E");
						}
					}
				},
				error : function(xhr, status){
					console.log(xhr);
				}
			});
		}
		<%-- 접수 (교육조건부) 목록--%>
		getEdMbrEdList = function(tagMbrNo){
			$.ajax({
				url : '<c:url value="/nrds/ajaxEdMbrEdList.do"/>',
				type : 'POST',
				data : {
					mbrNo : tagMbrNo
				},
				success : function(html){
					$("#infoList").html(html);
				},
				error : function(xhr, status){
					console.log(xhr);
				}
			});
		}
		<%-- 우편번호 검색창 오픈 --%>
		zipCodePop = function(){
			new daum.Postcode({
				oncomplete: function(data) {
					$("input[name='rZipCd']").val(data.zonecode);
					$("input[name='rAddr1']").val(data.roadAddress);
				}
			}).open();
		}
		<%-- 저장 --%>
		infoSave = function(){
			if($("input[name='mbrNo']").val() == ""){
				alert("대상자를 선택해 주세요.");
				mstMbrSearchPopup();							return;
			}
			if($("input[name='rZipCd']").val() == "" || $("input[name='rAddr1']").val() == ""){
				alert("실거주지를 입력하세요.");
				zipCodePop();									return;
			}
			if($("input[name='reqDt']").val() == ""){
				alert("의뢰일을 입력하세요.");
				$("input[name='reqDt']").focus();				return;
			}
			if($("input[name='docNo']").val() == ""){
				alert("문서번호를 입력하세요.");
				$("input[name='docNo']").focus();				return;
			}
			if($("input[name='docNo']").val() == ""){
				alert("문서번호를 입력하세요.");
				$("input[name='docNo']").focus();				return;
			}
			if(
				!($("input[name='crimeType01']").is(":checked") || $("input[name='crimeType02']").is(":checked") ||
				  $("input[name='crimeType03']").is(":checked") || $("input[name='crimeType04']").is(":checked"))
			  ){
				alert("범죄유형을 선택하세요.");
				$("input[name='crimeType01']").focus();			return;
			}
			if(
				!($("input[name='drug1']").is(":checked") || $("input[name='drug2']").is(":checked") ||
				  $("input[name='drug3']").is(":checked") || $("input[name='drug4']").is(":checked"))
			  ){
				alert("사용마약류를 선택하세요.");
				$("input[name='drug1']").focus();				return;
			}
			if(!$("input[name='useTerm']").is(":checked")){
				alert("사용기간을 선택하세요.");
				$("input[name='useTerm']").focus();				return;
			}
			if(
				!($("input[name='reqDetails01']").is(":checked") || $("input[name='reqDetails02']").is(":checked") ||
				  $("input[name='reqDetails03']").is(":checked") || $("input[name='reqDetails04']").is(":checked"))
			  ){
				alert("교육의뢰경위를 선택하세요.");
				$("input[name='reqDetails01']").focus();		return;
			}
			if($("input[name='reqOrg']").val() == ""){
				alert("의뢰처를 입력하세요.");
				$("input[name='reqOrg']").focus();				return;
			}

			$.ajax({
				url : '<c:url value="/nrds/ajaxEmeAdd.do"/>',
				type : 'POST',
				processData : false,
				contentType : false,
				enctype : 'multipart/form-data',
				data : new FormData($("#mainForm")[0]),
				success : function(res){
					if(res.err != "Y"){
						alert(res.MSG + "되었습니다.");

						setButton();
						getEdMbrEdList($("input[name='mbrNo']").val());
						newMemInfo();
					}else{
						alert(res.MSG);
					}
				},
				error : function(xhr, status){
					console.log(xhr);
				}
			});
		}
		<%-- 체크박스 선택 기타 입력창 활성화 --%>
		changValEtc = function(obj, chk, tag){
			if($(obj).attr("name") == chk){
				if($(obj).is(":checked")){
					$("input[name='" + tag + "']").attr("disabled", false);
				}else{
					$("input[name='" + tag + "']").val("");
					$("input[name='" + tag + "']").attr("disabled", true);
				}
			}
		}
		<%-- 첨부파일 삭제 플레그 --%>
		setFileDelFlag = function(){
			$("input[name='fileDelFlag']").val("Y");
			$("div#fileInfo").html("");
		}
		<%-- 정보 삭제 --%>
		delMemInfo = function(){
			if($("input[name='mbrEdId']").val() == ""){
				alert("의로 정보를 선택 하세요.");			return;
			}

			if(confirm("정말로 삭제하시겠습니까?\n삭제한 데이터는 복구가 불가합니다.")){
				$.ajax({
					url : '<c:url value="/nrds/ajaxEdMbrEdDel.do"/>',
					type : 'POST',
					data : {
						mbrNo : $("input[name='mbrNo']").val(),
						mbrEdId : $("input[name='mbrEdId']").val()
					},
					success : function(res){
						if(res.err != "Y"){
							alert("삭제되었습니다.");

							setButton();
							getEdMbrEdList($("input[name='mbrNo']").val());
							newMemInfo();
						}else{
							alert(res.MSG);
						}
					},
					error : function(xhr, status){
						console.log(xhr);
					}
				});
			}
		}
		<%-- 버튼 활성화 --%>
		setButton = function(flag){
			if(flag == "E"){
				$("button#delButNo").hide();
				$("button#delButNoYes").show();
			}else{
				$("button#delButNo").show();
				$("button#delButNoYes").hide();
			}
		}
		<%-- 폼 초기화 --%>
		newMemInfo = function(){
			$("input[name='rZipCd']").val("");
			$("input[name='rAddr1']").val("");
			$("input[name='rAddr2']").val("");

			$("input[name='mbrEdId']").val("");
			$("input[name='reqDt']").datepicker('setDate', 'today');
			$("input[name='docNo']").val("");
			$("input[name='caseNo']").val("");
			$("input[name='crimeType01']").prop("checked", false);
			$("input[name='crimeType02']").prop("checked", false);
			$("input[name='crimeType03']").prop("checked", false);
			$("input[name='crimeType04']").prop("checked", false);
			$("input[name='crimeTypeEtc']").val("");
			$("input[name='crimeTypeEtc']").attr("disabled", true);
			$("input[name='drug1']").prop("checked", false);
			$("input[name='drug1Etc']").val("");
			$("input[name='drug1Etc']").attr("disabled", true);
			$("input[name='drug2']").prop("checked", false);
			$("input[name='drug2Etc']").val("");
			$("input[name='drug2Etc']").attr("disabled", true);
			$("input[name='drug3']").prop("checked", false);
			$("input[name='drug3Etc']").val("");
			$("input[name='drug3Etc']").attr("disabled", true);
			$("input[name='drug4']").prop("checked", false);
			$("input[name='drug4Etc']").val("");
			$("input[name='drug4Etc']").attr("disabled", true);
			$("input[name='useTerm']").each(function(){
				$(this).prop("checked", false);
			});
			$("input[name='reqDetails01']").prop("checked", false);
			$("input[name='reqDetails02']").prop("checked", false);
			$("input[name='reqDetails03']").prop("checked", false);
			$("input[name='reqDetails04']").prop("checked", false);
			$("input[name='reqDetailsEtc']").val("");
			$("input[name='reqDetailsEtc']").attr("disabled", true);
			$("input[name='reqOrg']").val("");
			$("input[name='prosNo']").val("");
			$("input[name='detective']").val("");
			$("input[name='fileId']").val("");
			$("div#fileInfo").html("");

			setButton();
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
</div>
<!-- // 상단 버튼 -->
<div class="formline" style="min-width: 1600px;">
	<div class="section-sha" style="min-width: 1840px;">
		<!-- 탭메뉴 -->
		<div class="el-tabs__header is-top">
			<div class="el-tabs__nav-wrap is-top">
				<div class="el-tabs__nav-scroll">
					<div role="tablist" class="el-tabs__nav is-top">
						<a href="<c:url value="/nrds/clientMain.do" />">
							<div class="el-tabs__item is-top" data-id="tab-mem">
								<span><i class="el-icon-s-help"></i> 대상자 정보관리</span>
							</div>
						</a>
						<a href="<c:url value="/nrds/clientEduConMain.do"/>">
							<div class="el-tabs__item is-top is-active" data-id="tab-link">
								<span><i class="el-icon-s-management"></i> 접수 (교육조건부)</span>
							</div>
						</a>
						<a href="<c:url value="/nrds/clientLeadConMain.do" />">
							<div class="el-tabs__item is-top" data-id="tab-req">
								<span><i class="el-icon-platform-eleme"></i> 접수(선도조건부)</span>
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
		<div class="el-tabs_content">
			<div class="section">
				<table class="w-auto">
					<tbody>
						<tr>
							<th>대상자등록번호</th>
							<td>
								<div class="search-input tac">
									<input type="text" name="infoMbrNo" class="el-input__inner" style="width: 142px;" placeholder="대상자등록번호" readonly="readonly"/>
									<button type="button" onclick="javaScript:mstMbrSearchPopup();"><i class="el-icon-search"></i></button>
								</div>
							</td>
							<th>성명</th>
							<td><input type="text" name="infoMbrNm" class="el-input__inner datepicker" placeholder="성명" maxlength="8" style="width: 130px;" /></td>
							<th>성별</th>
							<td><input type="text" name="infoGendNm" placeholder="성별" /></td>
							<th>연령</th>
							<td><input type="text" name="infoAge" placeholder="연령" /> (세)</td>
							<th>의뢰기관</th>
							<td><input type="text" name="infoReqOrg" placeholder="의뢰기관" /></td>
							<th>연락처</th>
							<td><input type="text" name="infoTelNo" placeholder="연락처" /></td>
							<th>접수기관</th>
							<td><input type="text" name="infoCreIdSiteCd" placeholder="접수기관" /></td>
						</tr>
					</tbody>
				</table>
			</div>
			<div id="tab-focus" class="tab-form">
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
									<col>
								</colgroup>
								<thead>
								<tr>
									<th>#</th>
									<th>대상자등록번호</th>
									<th>성명</th>
									<th>성별</th>
									<th>연령</th>
									<th>연락처</th>
									<th>의뢰기관</th>
									<th>접수기관</th>
								</tr>
								</thead>
							</table>
						</div>
						<div class="el-table_body-wrapper" style="height: 148px;" id="infoList">
							<div class="no-data">조회된 데이터가 없습니다.</div>
						</div>
					</div>
				</div>
			</div>
			<div class="el-row">
				<form name="mainForm" id="mainForm" method="post" enctype="multipart/form-data">
				<input type="hidden" name="mbrEdId" />
				<input type="hidden" name="fileDelFlag" />
				<div class="row2">
					<div class="section pdn">
						<div class="el-card_header">
							<h2><i class="el-icon-s-opportunity"></i> 인적사항</h2>
						</div>
						<div class="el-card_body">
							<table class="w-auto wr-form">
								<tbody>
									<tr>
										<th>대상자등록번호</th>
										<td><input name="mbrNo" type="text" readonly placeholder="대상자등록번호" class="el-input__inner" style="width: 142px;" /></td>
										<th><span class="required">*</span> 성명</th>
										<td><input name="mbrNm" type="text" readonly class="el-input__inner" placeholder="성명" style="width: 130px;" /></td>
										<th><span class="required">*</span> 성별</th>
										<td>
											<div class="gen-check">
												<select name="gendCd" disabled="true">
<c:if test="${gendCdList ne null and gendCdList ne ''}">
	<c:forEach var="result" items="${gendCdList}" varStatus="status">
													<option value="<c:out value="${result.CD_ID}" />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
												</select>
											</div>
										</td>
									</tr>
									<tr>
										<th content="저장시 성명과 생년월일(6자)로 중복 체크"><span class="required">*</span> 생년월일</th>
										<td><input name="juminNo1" type="text" readonly class="el-input__inner" style="width: 90px;" placeholder="6자리"></td>
										<th><span class="required">*</span> 연령</th>
										<td><input name="age" type="text" readonly class="el-input__inner" style="width: 65px;" placeholder="연령"></td>
										<th><span class="required">*</span> 연락처</th>
										<td>
											<div class="dsp-ibk"><input name="telNo1" type="text" readonly maxlength="4" class="el-input__inner" style="width:54px"></div>
											<span>-</span>
											<div class="dsp-ibk"><input name="telNo2" type="text" readonly maxlength="4" class="el-input__inner" style="width:54px"></div>
											<span>-</span>
											<div class="dsp-ibk"><input name="telNo3" type="text" readonly maxlength="4" class="el-input__inner" style="width:53px"></div>
										</td>
									</tr>
									<tr>
										<th class="v-top"><span class="required">*</span> 주소</th>
										<td colspan="5">
											<input type="text" name="zipCd" readonly class="el-input__inner" readonly style="width: 65px;">
											<input name="addr1" type="text" readonly class="el-input__inner" readonly style="width: 525px;">
											<div style="margin-top:5px">
												<input name="addr2" type="text" readonly class="el-input__inner" style="width: 634px;">
											</div>
										</td>
									</tr>
									<tr>
										<th class="v-top">실거주지</th>
										<td colspan="5">
											<input type="text" name="rZipCd" class="el-input__inner" readonly style="width: 65px;">
											<button type="button" onclick="javaScript:zipCodePop();" class="el-button el-button--primary el-button--small is-plain" style="padding: 9px 12px 8px;">
												<i class="el-icon-search"></i>
											</button>
											<input name="rAddr1" type="text" class="el-input__inner" readonly style="width: 525px;">
											<div style="margin-top:5px">
												<input name="rAddr2" type="text" class="el-input__inner" maxlength="25" style="width: 634px;">
											</div>
										</td>
									</tr>
									<tr>
										<th>직업</th>
										<td colspan="5">
											<select name="jobCd" disabled="true" style="width: 150px;">
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
					<div class="section pdn">
						<div class="el-card_header">
							<h2><i class="el-icon-s-opportunity"></i> 의뢰정보</h2>
						</div>
						<div class="el-card_body">
							<table class="w-auto wr-form">
								<tbody>
									<tr>
										<th><span class="required">*</span> 의뢰일</th>
										<td>
											<div class="dat-pk">
												<i class="el-input__icon el-icon-date"></i>
												<input type="text" name="reqDt" class="el-input__inner datepicker" placeholder="의뢰일" style="width: 130px;" />
											</div>
										</td>
										<th><span class="required">*</span> 문서번호</th>
										<td><input type="text" name="docNo" class="el-input__inner" placeholder="문서번호" maxlength="50" style="width: 130px;" /></td>
										<th>사건번호</th>
										<td><input type="text" name="caseNo" class="el-input__inner" placeholder="사건번호" maxlength="50" style="width: 130px;" /></td>
									</tr>
									<tr>
										<th><span class="required">*</span> 범죄유형</th>
										<td colspan="5">
<c:if test="${crimeTypeList ne null and crimeTypeList ne ''}">
	<c:forEach var="result" items="${crimeTypeList}" varStatus="status">
											<span class="ck-bx">
												<input type="checkbox" name="crimeType0<c:out value="${status.count}" />" id="crimeType_<c:out value="${status.count}" />" value="1" onchange="javaScript:changValEtc(this, 'crimeType04', 'crimeTypeEtc');"/>
												<label for="crimeType_<c:out value="${status.count}" />"><c:out value="${result.CD_NM}" /></label>
											</span>
	</c:forEach>
</c:if>
											<input type="text" name="crimeTypeEtc" class="el-input__inner" placeholder="범죄유형 기타" maxlength="50" disabled style="width: 200px;" />
										</td>
									</tr>
									<tr>
										<th><span class="required">*</span> 사용마약류</th>
										<td colspan="5">
											<span class="ck-bx">
												<input type="checkbox" name="drug1" id="drug1" value="1" onchange="javaScript:changValEtc(this, 'drug1', 'drug1Etc');"/>
												<label for="drug1">마약</label>
											</span>
											<input type="text" name="drug1Etc" placeholder="사용 마약류 마약" maxlength="50" disabled style="width:130px;" />
											<span class="ck-bx">
												<input type="checkbox" name="drug2" id="drug2" value="1" onchange="javaScript:changValEtc(this, 'drug2', 'drug2Etc');"/>
												<label for="drug2">향정</label>
											</span>
											<input type="text" name="drug2Etc" placeholder="사용 마약류 향정" maxlength="50" disabled style="width:130px;" />
											<span class="ck-bx">
												<input type="checkbox" name="drug3" id="drug3" value="1" onchange="javaScript:changValEtc(this, 'drug3', 'drug3Etc');"/>
												<label for="drug3">대마</label>
											</span>
											<input type="text" name="drug3Etc" placeholder="사용 마약류 대마" maxlength="50" disabled style="width:130px;" />
											<span class="ck-bx">
												<input type="checkbox" name="drug4" id="drug4" value="1" onchange="javaScript:changValEtc(this, 'drug4', 'drug4Etc');"/>
												<label for="drug4">기타</label>
											</span>
											<input type="text" name="drug4Etc" placeholder="사용 마약류 기타" maxlength="50" disabled style="width:130px;" />
										</td>
									</tr>
									<tr>
										<th><span class="required">*</span> 사용기간</th>
										<td colspan="5">
<c:if test="${useTermList ne null and useTermList ne ''}">
	<c:forEach var="result" items="${useTermList}" varStatus="status">
											<span class="ck-bx">
												<input type="radio" name="useTerm" id="useTerm_<c:out value="${status.count}" />" value="<c:out value="${result.CD_ID}" />"/>
												<label for="useTerm_<c:out value="${status.count}" />"><c:out value="${result.CD_NM}" /></label>
											</span>
	</c:forEach>
</c:if>
										</td>
									</tr>
									<tr>
										<th><span class="required">*</span> 교육의뢰경위</th>
										<td colspan="5">
<c:if test="${reqDetailsList ne null and reqDetailsList ne ''}">
	<c:forEach var="result" items="${reqDetailsList}" varStatus="status">
											<span class="ck-bx">
												<input type="checkbox" name="reqDetails0<c:out value="${status.count}" />" id="reqDetails_<c:out value="${status.count}" />" value="1" onchange="javaScript:changValEtc(this, 'reqDetails04', 'reqDetailsEtc');"/>
												<label for="reqDetails_<c:out value="${status.count}" />"><c:out value="${result.CD_NM}" /></label>
											</span>
	</c:forEach>
</c:if>
											<input type="text" name="reqDetailsEtc" class="el-input__inner" placeholder="교육의뢰경위 기타" maxlength="50" disabled style="width: 200px;" />
										</td>
									</tr>
									<tr>
										<th><span class="required">*</span> 의뢰처</th>
										<td><input type="text" name="reqOrg" placeholder="의뢰처" maxlength="50" /></td>
										<th>검사실</th>
										<td><input type="text" name="prosNo" placeholder="검사실" maxlength="50" /></td>
										<th>수사관</th>
										<td><input type="text" name="detective" placeholder="수사관" maxlength="50" /></td>
									</tr>
									<tr>
										<th>첨부파일</th>
										<td colspan="5">
											<input type="file" name="fileId">
											<div id="fileInfo"></div>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				</form>
			</div>
		</div>
	</div>
</div>
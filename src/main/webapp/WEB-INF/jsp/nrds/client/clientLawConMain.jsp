<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kr.co.chase.ncms.common.ConstantObject" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" language="javascript" charset="utf-8" src="<c:url value='/js/jquery.sumoselect.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">
	$(document).ready(function(){
		var multipleSelect = $('#lawPbmCd').SumoSelect();
		var multipleSelect2 = $('#useCauCd').SumoSelect();
		
		<%-- 회원 조회 팝업 --%>
		mstMbrSearchPopup = function(){
			$.ajax({
				url : '<c:url value="/nrds/ajaxEdMbrList.do"/>',
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
					$("input[name='reFunName']").val("getLawLastInfo");
					layerPopupOpen('memberPopUp');
				},
				error : function(xhr, status){
					console.log(xhr);
				}
			});
		}
		<%-- 회원의뢰 정보 조회 --%>
		getLawLastInfo = function(tagMbrNo){
			setButton();
			newInfo();

			$.ajax({
				url : '<c:url value="/nrds/ajaxEdMbrLawLastInfoJson.do"/>',
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
						getEdMbrLawList(tagMbrNo);
					}
				},
				error : function(xhr, status){
					console.log(xhr);
				}
			});
		}
		<%-- 회원 기본 정보 조회 --%>
		getMbrInfo = function(tagMbrNo, tagMbrLawId){
			$.ajax({
				url : '<c:url value="/nrds/ajaxLawInfoJson.do"/>',
				type : 'POST',
				data : {
					mbrNo : tagMbrNo,
					mbrLawId : tagMbrLawId
				},
				success : function(json){
					var info = json.mbrEdInfo;

					if(info.MBR_NO != ""){
						$("input[name='mbrNo']").val(info.MBR_NO);
						$("input[name='mbrNm']").val(info.MBR_NM);
						$("select[name='gendCd']").val(info.GEND_CD).prop("selected", true);
						$("input[name='frgCd']:radio[value='" + info.FRG_CD + "']").prop("checked", true);
						$("input[name='juminNo1']").val(info.JUMIN_NO1);
						$("input[name='age']").val(info.AGE);
						$("input[name='telNo1']").val(info.TEL_NO1);
						$("input[name='telNo2']").val(info.TEL_NO2);
						$("input[name='telNo3']").val(info.TEL_NO3);
						$("input[name='zipCd']").val(info.ZIP_CD);
						$("input[name='addr1']").val(info.ADDR1);
						$("input[name='addr2']").val(info.ADDR2);
						$("input[name='mbrSt']:radio[value='" + info.MBR_ST + "']").prop("checked", true);
						$("input[name='creUsrDt']").val(formatDate(info.CRE_DT));
						$("input[name='updDt']").val(formatDate(info.UPD_DT));
						$("input[name='updNm']").val(info.UPD_NM);

						if(info.MBR_LAW_ID != '' && info.MBR_LAW_ID != undefined){
							$("input[name='mbrLawId']").val(info.MBR_LAW_ID);
							if(info.MBR_TP1 == '1'){
								$("input[name='mbrTp1']").prop("checked", true);
							}else{
								$("input[name='mbrTp1']").prop("checked", false);
							}
							if(info.MBR_TP2 == '1'){
								$("input[name='mbrTp2']").prop("checked", true);
							}else{
								$("input[name='mbrTp2']").prop("checked", false);
							}
							if(info.MBR_TP3 == '1'){
								$("input[name='mbrTp3']").prop("checked", true);
							}else{
								$("input[name='mbrTp3']").prop("checked", false);
							}
							if(info.MBR_TP4 == '1'){
								$("input[name='mbrTp4']").prop("checked", true);
							}else{
								$("input[name='mbrTp4']").prop("checked", false);
							}
							if(info.MBR_TP5 == '1'){
								$("input[name='mbrTp5']").prop("checked", true);
							}else{
								$("input[name='mbrTp5']").prop("checked", false);
							}
							if(info.MBR_TP6 == '1'){
								$("input[name='mbrTp6']").prop("checked", true);
							}else{
								$("input[name='mbrTp6']").prop("checked", false);
							}
							
							$("select[name='mrgCd']").val(info.MRG_CD).prop("selected", true);
							$("select[name='eduCd']").val(info.EDU_CD).prop("selected", true);
							$("select[name='edu02Cd']").val(info.EDU02_CD).prop("selected", true);
							$("select[name='rlgnCd']").val(info.RLGN_CD).prop("selected", true);
							$("select[name='jobCd']").val(info.JOB_CD).prop("selected", true);

							$("select[name='fstDrugCd']").val(info.FST_DRUG_CD).prop("selected", true);
							$("input[name='fstDrug']").val(info.FST_DRUG);
							$("select[name='mainDrugCd']").val(info.MAIN_DRUG_CD).prop("selected", true);
							$("input[name='mainDrug']").val(info.MAIN_DRUG);
							$("input[name='fstAge']").val(info.FST_AGE);
							$("input[name='lstAge']").val(info.LST_AGE);
							$("input[name='useTerm']").val(info.USE_TERM);
							$("select[name='useFrqCd']").val(info.USE_FRQ_CD).prop("selected", true);
							$("select[name='cureoffExpCd']").val(info.CUREOFF_EXP_CD).prop("selected", true);
							$("select[name='medicCareCd']").val(info.MEDIC_CARE_CD).prop("selected", true);
							
							var pmbFlag = false;
							$("select[name='lawPbmCd'] option").each(function(i, e){
								$(e).prop("selected", false);
							});
							if(info.LAW_PBM_CD.indexOf(",") >= 0){
								$.each(info.LAW_PBM_CD.split(","), function(i, e){
									var cdVal = $.trim(e).replace(/\[/g, "").replace(/\]/g, "");
									$("select#lawPbmCd option[value=" + cdVal + "]").prop('selected', true);

									if(cdVal == "ZZ"){
										pmbFlag = true;
									}
								});
							}else{
								var cdVal = $.trim(info.LAW_PBM_CD).replace(/\[/g, "").replace(/\]/g, "");
								$("select[name='lawPbmCd']").val(cdVal).prop("selected", true);

								if(cdVal == "ZZ"){
									pmbFlag = true;
								}
							}

							multipleSelect = $("select[name='lawPbmCd']")[0].sumo.reload();

							if(pmbFlag){
								$("input[name='lawPbmEtc']").attr("disabled", false);
								$("input[name='lawPbmEtc']").val(info.LAW_PBM_ETC);
							}else{
								$("input[name='lawPbmEtc']").attr("disabled", true);
								$("input[name='lawPbmEtc']").val("");
							}
							
							
							var cauFlag = false;
							$("select[name='useCauCd'] option").each(function(i, e){
								$(e).prop("selected", false);
							});
							if(info.USE_CAU_CD.indexOf(",") >= 0){
								$.each(info.USE_CAU_CD.split(","), function(i, e){
									var cdVal = $.trim(e).replace(/\[/g, "").replace(/\]/g, "");
									$("select#useCauCd option[value=" + cdVal + "]").prop('selected', true);

									if(cdVal == "ZZ"){
										cauFlag = true;
									}
								});
							}else{
								var cdVal = $.trim(info.USE_CAU_CD).replace(/\[/g, "").replace(/\]/g, "");
								$("select[name='useCauCd']").val(cdVal).prop("selected", true);

								if(cdVal == "ZZ"){
									cauFlag = true;
								}
							}

							multipleSelect2 = $("select[name='useCauCd']")[0].sumo.reload();

							if(cauFlag){
								$("input[name='useCauEtc']").attr("disabled", false);
								$("input[name='useCauEtc']").val(info.USE_CAU_ETC);
							}else{
								$("input[name='useCauEtc']").attr("disabled", true);
								$("input[name='useCauEtc']").val("");
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
		<%-- 정보 저장 --%>
		infoSave = function(){
			if($("input[name='mbrNo']").val() == ""){
				alert("대상자를 선택해 주세요.");
				mstMbrSearchPopup();							return;
			}
			if(
				!($("input[name='mbrTp1']").is(":checked") || $("input[name='mbrTp2']").is(":checked") || $("input[name='mbrTp3']").is(":checked") ||
				  $("input[name='mbrTp4']").is(":checked") || $("input[name='mbrTp5']").is(":checked") || $("input[name='mbrTp6']").is(":checked"))
			  ){
				alert("대상자구분을 선택하세요.");
				$("input[name='mbrTp1']").focus();				return;
			}
			if($("select[name='medicCareCd']").val() == ""){
				alert("의료보장을 선택하세요.");
				$("select[name='medicCareCd']").focus();				return;
			}
			if($("select[name='fstDrugCd']").val() == ""){
				alert("최초사용약물을 선택하세요.");
				$("select[name='fstDrugCd']").focus();				return;
			}
			if($("select[name='mainDrugCd']").val() == ""){
				alert("주요사용약물을 선택하세요.");
				$("select[name='mainDrugCd']").focus();				return;
			}
			if($("input[name='fstAge']").val() == ""){
				alert("최초 사용시기를 입력하세요.");
				$("input[name='fstAge']").focus();					return;
			}
			if($("input[name='lstAge']").val() == ""){
				alert("마지막 사용시기를 입력하세요.");
				$("input[name='lstAge']").focus();					return;
			}
			if($("input[name='useTerm']").val() == ""){
				alert("사용기간을 입력하세요.");
				$("input[name='useTerm']").focus();					return;
			}
			if($("select[name='useFrqCd']").val() == ""){
				alert("사용빈도를 선택하세요.");
				$("select[name='useFrqCd']").focus();				return;
			}
			if($("#useCauCd").val() == null || $("#useCauCd").val() == ""){
				alert("사용원인을 선택하세요.");
				$("select[name='useCauCd']").focus();				return;
			}
			if($("#lawPbmCd").val() == null || $("#lawPbmCd").val() == ""){
				alert("약물관련 법적문제를 선택하세요.");
				$("select[name='lawPbmCd']").focus();				return;
			}
			if($("select[name='cureoffExpCd']").val() == ""){
				alert("단약경험을 선택하세요.");
				$("select[name='cureoffExpCd']").focus();				return;
			}

			$.ajax({
				url : '<c:url value="/nrds/ajaxEmLawAdd.do"/>',
				type : 'POST',
				data : $('#mainForm').serialize(),
				success : function(res){
					if(res.err != "Y"){
						alert(res.MSG + "되었습니다.");

						setButton();
						getEdMbrLawList($("input[name='mbrNo']").val());
						newInfo();
					}else{
						alert(res.MSG);
					}
				},
				error : function(xhr, status){
					console.log(xhr);
				}
			});
		}
		<%-- 접수(선도조건부) 목록--%>
		getEdMbrLawList = function(tagMbrNo){
			$.ajax({
				url : '<c:url value="/nrds/ajaxEdMbrLawList.do"/>',
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
		<%-- 정보삭제 --%>
		delInfo = function(){
			if($("input[name='mbrLawId']").val() == ""){
				alert("대상자 정보를 선택 하세요.");			return;
			}

			if(confirm("정말로 삭제하시겠습니까?\n삭제한 데이터는 복구가 불가합니다.")){
				$.ajax({
					url : '<c:url value="/nrds/ajaxEdMbrLawDel.do"/>',
					type : 'POST',
					data : {
						mbrNo : $("input[name='mbrNo']").val(),
						mbrLawId : $("input[name='mbrLawId']").val()
					},
					success : function(res){
						if(res.err != "Y"){
							alert("삭제되었습니다.");

							setButton();
							getEdMbrLawList($("input[name='mbrNo']").val());
							newInfo();
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
		<%-- 폼 초기화 --%>
		newInfo = function(){
			$("input[name='mbrLawId']").val("");
			$("input[name='mbrTp1']").prop("checked", false);
			$("input[name='mbrTp2']").prop("checked", false);
			$("input[name='mbrTp3']").prop("checked", false);
			$("input[name='mbrTp4']").prop("checked", false);
			$("input[name='mbrTp5']").prop("checked", false);
			$("input[name='mbrTp6']").prop("checked", false);
			$("select[name='mrgCd']").val("");
			$("select[name='eduCd']").val("");
			$("select[name='edu02Cd']").val("");
			$("select[name='rlgnCd']").val("");
			$("select[name='jobCd']").val("");
			$("select[name='fstDrugCd']").val("");
			$("input[name='fstDrug']").val("");
			$("select[name='mainDrugCd']").val("");
			$("input[name='mainDrug']").val("");
			$("input[name='fstAge']").val("");
			$("input[name='lstAge']").val("");
			$("input[name='useTerm']").val("");
			$("select[name='useFrqCd']").val("");
			$("select[name='useCauCd'] option").each(function(i, e){
				$(e).prop("selected", false);
			});
			$("select[name='useCauCd']")[0].sumo.reload();
			$("input[name='useCauEtc']").attr("disabled", true);
			$("input[name='useCauEtc']").val("");
			$("select[name='lawPbmCd'] option").each(function(i, e){
				$(e).prop("selected", false);
			});
			$("select[name='lawPbmCd']")[0].sumo.reload();
			$("input[name='lawPbmEtc']").attr("disabled", true);
			$("input[name='lawPbmEtc']").val("");
			$("select[name='medicCareCd']").val("");
			$("select[name='cureoffExpCd']").val("");

			setButton();
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
	<button type="button" onclick="javaScript:newInfo();" class="el-button normal el-button--default el-button--small is-plain" style="margin-left: 8px;">
		<i class="el-icon-circle-plus-outline"></i><span>신규</span>
	</button>
	<button disabled="disabled" id="delButNo" type="button" class="el-button normal el-button--default el-button--small is-disabled is-plain">
		<i class="el-icon-delete-solid"></i><span>삭제</span>
	</button>
	<button type="button" id="delButNoYes" onclick="javaScript:delInfo();" class="el-button normal el-button--default el-button--small is-plain" style="display:none;">
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
								<span><i class="el-icon-s-help"></i> 대상자관리</span>
							</div>
						</a>
						<a href="<c:url value="/nrds/clientLawConMain.do" />">
							<div class="el-tabs__item is-top is-active" data-id="tab-mem">
								<span><i class="el-icon-s-help"></i> 접수(법정의무교육)</span>
							</div>
						</a>
						<a href="<c:url value="/nrds/clientEduConMain.do"/>">
							<div class="el-tabs__item is-top" data-id="tab-link">
								<span><i class="el-icon-s-management"></i> 접수(교육조건부)</span>
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
							<td><input type="text" name="infoMbrNm" placeholder="성명" maxlength="8" /></td>
							<th>성별</th>
							<td><input type="text" name="infoGendNm" placeholder="성별" /></td>
							<th>연령</th>
							<td><input type="text" name="infoAge" placeholder="연령" /></td>
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
			<form name="mainForm" id="mainForm" method="post">
				<input type="hidden" name="mbrLawId" />
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
									<td>
										<input name="mbrNo" type="text" readonly placeholder="대상자등록번호" class="el-input__inner" style="width: 142px;" />
									</td>
									<th><span class="required">*</span> 성명</th>
									<td><input name="mbrNm" type="text" class="el-input__inner" placeholder="성명" maxlength="5" style="width: 130px;" readonly="readonly"/></td>
									<th><span class="required">*</span> 성별</th>
									<td>
										<div class="gen-check">
											<select name="gendCd" disabled="disabled">
<c:if test="${gendCdList ne null and gendCdList ne ''}">
	<c:forEach var="result" items="${gendCdList}" varStatus="status">
												<option value="<c:out value="${result.CD_ID}" />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
											</select>
										</div>
									</td>
									<th><span class="required">*</span> 내/외국인</th>
									<td>
<c:if test="${frgCdList ne null and frgCdList ne ''}">
	<c:forEach var="result" items="${frgCdList}" varStatus="status">
										<span class="ck-bx">
											<input type="radio" class="el-radio__original" name="frgCd" value="<c:out value="${result.CD_ID}" />" id="frgCd-<c:out value="${status.count}" />"  disabled="disabled">
											<label for="frgCd-<c:out value="${status.count}" />">
												<span class="el-radio__input"><span class="el-radio__inner"></span></span> <c:out value="${result.CD_NM}" />
											</label>
										</span>
	</c:forEach>
</c:if>
									</td>
								</tr>
								<tr>
									<th content="저장시 성명과 생년월일(6자)로 중복 체크"><span class="required">*</span> 생년월일</th>
									<td>
										<input name="juminNo1" type="text" class="el-input__inner" style="width: 90px;" maxlength="6" placeholder="6자리" readonly="readonly">
									</td>
									<th><span class="required">*</span> 연령</th>
									<td><input name="age" type="text" class="el-input__inner" style="width: 65px;" maxlength="3" placeholder="연령" readonly="readonly"></td>
									<th><span class="required">*</span> 연락처</th>
									<td colspan="3">
										<div class="dsp-ibk"><input name="telNo1" type="text" maxlength="4" class="el-input__inner" style="width:54px" readonly="readonly"></div>
										<span>-</span>
										<div class="dsp-ibk"><input name="telNo2" type="text" maxlength="4" class="el-input__inner" style="width:54px" readonly="readonly"></div>
										<span>-</span>
										<div class="dsp-ibk"><input name="telNo3" type="text" maxlength="4" class="el-input__inner" style="width:53px" readonly="readonly"></div>
									</td>
								</tr>
								<tr>
									<th class="v-top"><span class="required">*</span> 주소</th>
									<td colspan="7">
										<input type="text" name="zipCd" class="el-input__inner" readonly style="width: 65px;">
										<input name="addr1" type="text" class="el-input__inner" readonly style="width: 525px;">
										<div style="margin-top:5px">
											<input name="addr2" type="text" class="el-input__inner" maxlength="25" style="width: 634px;" readonly="readonly">
										</div>
									</td>
								</tr>
								<tr>
									<th><span class="required">*</span> 대상자상태</th>
									<td colspan="7">
<c:if test="${mbrStCdList ne null and mbrStCdList ne ''}">
	<c:forEach var="result" items="${mbrStCdList}" varStatus="status">
										<span class="ck-bx">
											<input type="radio" class="el-radio__original" name="mbrSt" value="<c:out value="${result.CD_ID}" />" id="mbrSt-<c:out value="${status.count}" />" disabled="disabled">
											<label for="mbrSt-<c:out value="${status.count}" />">
												<span class="el-radio__input"><span class="el-radio__inner"></span></span> <c:out value="${result.CD_NM}" />
											</label>
										</span>
	</c:forEach>
</c:if>
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
							<h2><i class="el-icon-s-opportunity"></i> 중독력</h2>
						</div>
						<div class="el-card_body">
							<table class="w-auto wr-form">
								<colgroup>
									<col style="width: 70px;">
									<col style="width: 70px;">
									<col style="width: 70px;">
									<col style="width: 70px;">
									<col style="width: 70px;">
									<col>
								</colgroup>
								<tbody>
								<tr>
									<th><span class="required">*</span> 대상자구분</th>
									<td colspan="5">
										<span class="ck-bx">
											<input type="checkbox" name="mbrTp1" id="mbrTp1" value="1">
											<label for="mbrTp1">수강명령 과정</label>
										</span>
										<span class="ck-bx">
											<input type="checkbox" name="mbrTp2" id="mbrTp2" value="1">
											<label for="mbrTp2">이수명령 과정</label>
										</span>
										<span class="ck-bx">
											<input type="checkbox" name="mbrTp3" id="mbrTp3" value="1">
											<label for="mbrTp3">기본과정</label>
										</span>
										<span class="ck-bx">
											<input type="checkbox" name="mbrTp4" id="mbrTp4" value="1">
											<label for="mbrTp4">집중과정</label>
										</span>
										<span class="ck-bx">
											<input type="checkbox" name="mbrTp5" id="mbrTp5" value="1">
											<label for="mbrTp5">심화과정</label>
										</span>
										<span class="ck-bx">
											<input type="checkbox" name="mbrTp6" id="mbrTp6" value="1">
											<label for="mbrTp6">단기과정</label>
										</span>
									</td>
								</tr>
								<tr>
									<th><span class="required">*</span> 의료보장</th>
									<td>
										<select name="medicCareCd" style="width: 150px;">
											<option value="">선택</option>
											<c:if test="${medicCareCdList ne null and medicCareCdList ne ''}">
												<c:forEach var="result" items="${medicCareCdList}" varStatus="status">
													<option value="<c:out value="${result.CD_ID}" />"<c:if test="${mbrInfo.MEDIC_CARE_CD eq result.CD_ID}"> selected</c:if>><c:out value="${result.CD_NM}" /></option>
												</c:forEach>
											</c:if>
										</select>
									</td>
								</tr>
									<tr>
									<th> 결혼정보</th>
									<td>
										<select name="mrgCd" style="width: 150px;">
											<option value="">선택</option>
<c:if test="${mrgCdList ne null and mrgCdList ne ''}">
	<c:forEach var="result" items="${mrgCdList}" varStatus="status">
											<option value="<c:out value="${result.CD_ID}" />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
										</select>
									</td>
									<th>학력</th>
									<td colspan="3">
										<select name="eduCd" style="width: 150px;">
											<option value="">선택</option>
<c:if test="${eduCdList ne null and eduCdList ne ''}">
	<c:forEach var="result" items="${eduCdList}" varStatus="status">
											<option value="<c:out value="${result.CD_ID}" />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
										</select>
										<select name="edu02Cd" style="width: 135px;">
											<option value="">선택</option>
<c:if test="${edu02CdList ne null and edu02CdList ne ''}">
	<c:forEach var="result" items="${edu02CdList}" varStatus="status">
											<option value="<c:out value="${result.CD_ID}" />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
										</select>
									</td>
								</tr>
								<tr>
									<th>종교</th>
									<td>
										<select name="rlgnCd" style="width: 150px;">
											<option value="">선택</option>
<c:if test="${rlgnCdList ne null and rlgnCdList ne ''}">
	<c:forEach var="result" items="${rlgnCdList}" varStatus="status">
											<option value="<c:out value="${result.CD_ID}" />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
										</select>
									</td>
									<th>직업</th>
									<td colspan="3">
										<select name="jobCd" style="width: 150px;">
											<option value="">선택</option>
<c:if test="${jobCdList ne null and jobCdList ne ''}">
	<c:forEach var="result" items="${jobCdList}" varStatus="status">
											<option value="<c:out value="${result.CD_ID}" />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
										</select>
									</td>
								</tr>
								
									<tr>
										<th class="v-top"><span class="required">*</span> 최초사용약물</th>
										<td colspan="5">
											<select name="fstDrugCd">
												<option value="">선택</option>
<c:if test="${fstDrugList ne null and fstDrugList ne ''}">
	<c:forEach var="result" items="${fstDrugList}" varStatus="status">
												<option value="<c:out value="${result.CD_ID}" />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
											</select>
											<div style="margin-top:5px">
												<input type="text" name="fstDrug" class="el-input__inner" maxlength="250" style="width: 100%;">
											</div>
										</td>
									</tr>
									<tr>
										<th class="v-top"><span class="required">*</span> 주요사용약물</th>
										<td colspan="5">
											<select name="mainDrugCd">
												<option value="">선택</option>
<c:if test="${mainDrugList ne null and mainDrugList ne ''}">
	<c:forEach var="result" items="${mainDrugList}" varStatus="status">
												<option value="<c:out value="${result.CD_ID}" />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
											</select>
											<div style="margin-top:5px">
												<input type="text" name="mainDrug" class="el-input__inner" maxlength="250" style="width: 100%;"/>
											</div>
										</td>
									</tr>
									<tr>
										<th><span class="required">*</span> 최초 사용시기</th>
										<td><input type="text" name="fstAge" class="el-input__inner" maxlength="4" style="width: 100%;"></td>
										<th><span class="required">*</span> 마지막 사용시기</th>
										<td><input type="text" name="lstAge" class="el-input__inner" maxlength="4" style="width: 100%;"></td>
										<th><span class="required">*</span> 사용기간</th>
										<td><input type="text" name="useTerm" class="el-input__inner" maxlength="10" style="width: 100%;"></td>
									</tr>
									<tr>
										<th><span class="required">*</span> 사용빈도</th>
										<td>
											<select name="useFrqCd">
												<option value="">선택</option>
<c:if test="${useFrqList ne null and useFrqList ne ''}">
	<c:forEach var="result" items="${useFrqList}" varStatus="status">
												<option value="<c:out value="${result.CD_ID}" />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
											</select>
										</td>
										<th><span class="required">*</span> 사용원인</th>
										<td colspan="3">
											<select name="useCauCd" id="useCauCd" class="testselect2" style="width: 150px;" onchange="javaScript:inputDisabledChang(this, 'useCauEtc');" multiple="multiple">
<c:if test="${useCauList ne null and useCauList ne ''}">
	<c:forEach var="result" items="${useCauList}" varStatus="status">
												<option value="<c:out value="${result.CD_ID}" />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
											</select>
											<input type="text" name="useCauEtc" class="el-input__inner" disabled maxlength="200" style="width: 250px;"/>
										</td>
									</tr>
									<tr>
										<th><span class="required">*</span> 약물관련 법적문제</th>
										<td colspan="5">
											<select name="lawPbmCd" id="lawPbmCd" class="testselect2" style="width: 150px;" onchange="javaScript:inputDisabledChang(this, 'lawPbmEtc');" multiple="multiple">
<c:if test="${lawPbmList ne null and lawPbmList ne ''}">
	<c:forEach var="result" items="${lawPbmList}" varStatus="status">
												<option value="<c:out value="${result.CD_ID}" />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
											</select>
											<input type="text" name="lawPbmEtc" class="el-input__inner" disabled maxlength="200" style="width: 530px;"/>
										</td>
									</tr>
									<tr>
										<th><span class="required">*</span> 단약경험</th>
										<td colspan="5">
											<select name="cureoffExpCd">
												<option value="">선택</option>
<c:if test="${cureoffExpCdList ne null and cureoffExpCdList ne ''}">
	<c:forEach var="result" items="${cureoffExpCdList}" varStatus="status">
												<option value="<c:out value="${result.CD_ID}" />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
											</select>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			</form>
		</div>
	</div>
</div>
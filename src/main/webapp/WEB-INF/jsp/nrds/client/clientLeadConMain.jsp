<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kr.co.chase.ncms.common.ConstantObject" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javaScript" language="javascript" defer="defer">
	$(document).ready(function(){
		<%-- 우편번호 검색창 오픈 --%>
		zipCodePop = function(){
			new daum.Postcode({
				oncomplete: function(data) {
					$("input[name='rZipCd']").val(data.zonecode);
					$("input[name='rAddr1']").val(data.roadAddress);
				}
			}).open();
		}
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
					$("input[name='reFunName']").val("getGuLastInfo");
					layerPopupOpen('memberPopUp');
				},
				error : function(xhr, status){
					console.log(xhr);
				}
			});
		}
		<%-- 회원의뢰 정보 조회 --%>
		getGuLastInfo = function(tagMbrNo){
			setButton();
			newInfo();

			$.ajax({
				url : '<c:url value="/nrds/ajaxEdMbrGuLastInfoJson.do"/>',
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
						$("input[name='infoAgeNm']").val(info.AGE_NM);
						$("input[name='infoReqOrg']").val(info.REQ_ORG);
						if(info.TEL_NO1 != ''){
							$("input[name='infoTelNo']").val(info.TEL_NO1 + "-" + info.TEL_NO2 + "-" + info.TEL_NO3);
						}
						$("input[name='infoCreIdSiteCd']").val(info.SITE_NM);

						getMbrInfo(tagMbrNo);
						getEdMbrGuList(tagMbrNo);
					}
				},
				error : function(xhr, status){
					console.log(xhr);
				}
			});
		}
		<%-- 회원 기본 정보 조회 --%>
		getMbrInfo = function(tagMbrNo, tagMbrGuId){
			$.ajax({
				url : '<c:url value="/nrds/ajaxGuInfoJson.do"/>',
				type : 'POST',
				data : {
					mbrNo : tagMbrNo,
					mbrGuId : tagMbrGuId
				},
				success : function(json){
					var info = json.mbrEdInfo;

					if(info.MBR_NO != ""){
						$("input[name='mbrNo']").val(info.MBR_NO);
						$("input[name='mbrNm']").val(info.MBR_NM);
						$("select[name='gendCd']").val(info.GEND_CD).prop("selected", true);
						$("input[name='juminNo1']").val(info.JUMIN_NO1);
						$("input[name='ageNm']").val(info.AGE_NM);
						$("input[name='telNo1']").val(info.TEL_NO1);
						$("input[name='telNo2']").val(info.TEL_NO2);
						$("input[name='telNo3']").val(info.TEL_NO3);
						$("input[name='zipCd']").val(info.ZIP_CD);
						$("input[name='addr1']").val(info.ADDR1);
						$("input[name='addr2']").val(info.ADDR2);
						$("select[name='jobCd']").val(info.JOB_CD).prop("selected", true);

						if(info.MBR_GU_ID != '' && info.MBR_GU_ID != undefined){
							$("input[name='rZipCd']").val(info.R_ZIP_CD);
							$("input[name='rAddr1']").val(info.R_ADDR1);
							$("input[name='rAddr2']").val(info.R_ADDR2);
							$("input[name='crimeName']").val(info.CRIME_NAME);

							$("input[name='mbrGuId']").val(info.MBR_GU_ID);
							$("input[name='reqDt']").val(info.REQ_DT);
							$("input[name='docNo']").val(info.DOC_NO);
							$("input[name='caseNo']").val(info.CASE_NO);
							$("input[name='reqOrdDt']").val(info.REQ_ORD_DT);
							$("input[name='reqEndDt']").val(info.REQ_END_DT);
							$("input[name='reqOrg']").val(info.REQ_ORG);
							$("input[name='reqMngr']").val(info.REQ_MNGR);

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
		<%-- 정보 저장 --%>
		infoSave = function(){
			if($("input[name='mbrNo']").val() == ""){
				alert("대상자를 선택해 주세요.");
				mstMbrSearchPopup();							return;
			}
			if($("input[name='reqDt']").val() == ""){
				alert("의뢰일을 선택하세요.");
				$("input[name='reqDt']").focus();				return;
			}
			if($("input[name='docNo']").val() == ""){
				alert("문서번호를 입력하세요.");
				$("input[name='docNo']").focus();				return;
			}
			if($("input[name='reqOrg']").val() == ""){
				alert("의뢰처를 입력하세요.");
				$("input[name='reqOrg']").focus();				return;
			}

			$.ajax({
				url : '<c:url value="/nrds/ajaxEmgAdd.do"/>',
				type : 'POST',
				processData : false,
				contentType : false,
				enctype : 'multipart/form-data',
				data : new FormData($("#mainForm")[0]),
				success : function(res){
					if(res.err != "Y"){
						alert(res.MSG + "되었습니다.");

						setButton();
						getEdMbrGuList($("input[name='mbrNo']").val());
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
		getEdMbrGuList = function(tagMbrNo){
			$.ajax({
				url : '<c:url value="/nrds/ajaxEdMbrGuList.do"/>',
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
			if($("input[name='mbrGuId']").val() == ""){
				alert("의뢰 정보를 선택 하세요.");			return;
			}

			if(confirm("정말로 삭제하시겠습니까?\n삭제한 데이터는 복구가 불가합니다.")){
				$.ajax({
					url : '<c:url value="/nrds/ajaxEdMbrGuDel.do"/>',
					type : 'POST',
					data : {
						mbrNo : $("input[name='mbrNo']").val(),
						mbrGuId : $("input[name='mbrGuId']").val()
					},
					success : function(res){
						if(res.err != "Y"){
							alert("삭제되었습니다.");

							setButton();
							getEdMbrGuList($("input[name='mbrNo']").val());
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
			$("input[name='rZipCd']").val("");
			$("input[name='rAddr1']").val("");
			$("input[name='rAddr2']").val("");
			$("input[name='crimeName']").val("");

			$("input[name='mbrGuId']").val("");
			$("input[name='reqDt']").val("");
			$("input[name='docNo']").val("");
			$("input[name='caseNo']").val("");
			$("input[name='reqOrdDt']").val("");
			$("input[name='reqEndDt']").val("");
			$("input[name='reqOrg']").val("");
			$("input[name='reqMngr']").val("");
			$("input[name='fileId']").val("");
			$("div#fileInfo").html("");

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
		<%-- 첨부파일 삭제 플레그 --%>
		setFileDelFlag = function(){
			$("input[name='fileDelFlag']").val("Y");
			$("div#fileInfo").html("");
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
								<span><i class="el-icon-s-help"></i> 대상자 정보관리</span>
							</div>
						</a>
						<a href="<c:url value="/nrds/clientEduConMain.do"/>">
							<div class="el-tabs__item is-top" data-id="tab-link">
								<span><i class="el-icon-s-management"></i> 접수 (교육조건부)</span>
							</div>
						</a>
						<a href="<c:url value="/nrds/clientLeadConMain.do" />">
							<div class="el-tabs__item is-top is-active" data-id="tab-req">
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
							<td><input type="text" name="infoAgeNm" placeholder="연령" /></td>
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
				<input type="hidden" name="mbrGuId" />
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
										<td><input name="ageNm" type="text" readonly class="el-input__inner" style="width: 65px;" placeholder="연령"></td>
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
											<input type="text" name="zipCd" class="el-input__inner" readonly style="width: 65px;">
											<input name="addr1" type="text" class="el-input__inner" readonly style="width: 525px;">
											<div style="margin-top:5px">
												<input name="addr2" type="text" class="el-input__inner" readonly style="width: 634px;">
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
												<input name="rAddr2" type="text" class="el-input__inner" style="width: 634px;">
											</div>
										</td>
									</tr>
									<tr>
										<th>직업</th>
										<td>
											<select name="jobCd" disabled="true" style="width: 150px;">
												<option value="">선택</option>
<c:if test="${jobCdList ne null and jobCdList ne ''}">
	<c:forEach var="result" items="${jobCdList}" varStatus="status">
												<option value="<c:out value="${result.CD_ID}" />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
											</select>
										</td>
										<th>죄명</th>
										<td colspan="3"><input type="text" name="crimeName" maxlength="50" style="width: 300px;" /></td>
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
										<th>등록지시일</th>
										<td>
											<div class="dat-pk">
												<i class="el-input__icon el-icon-date"></i>
												<input type="text" name="reqOrdDt" class="el-input__inner datepicker" placeholder="등록지시일" style="width: 130px;" />
											</div>
										</td>
										<th>등록기한</th>
										<td colspan="3">
											<div class="dat-pk">
												<i class="el-input__icon el-icon-date"></i>
												<input type="text" name="reqEndDt" class="el-input__inner datepicker" placeholder="등록기한" style="width: 100%;" />
											</div>
										</td>
									</tr>
									<tr>
										<th><span class="required">*</span> 의뢰처</th>
										<td><input type="text" name="reqOrg" class="el-input__inner" placeholder="의뢰처" maxlength="50" style="width: 130px;" /></td>
										<th>담당자</th>
										<td colspan="3"><input type="text" name="reqMngr" class="el-input__inner" placeholder="담당자" maxlength="50" style="width: 100%;" /></td>
									</tr>
									<tr>
										<th>첨부파일</th>
										<td colspan="5">
											<input type="file" name="fileId" />
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
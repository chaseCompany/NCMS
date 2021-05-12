<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String loginUserNm = StringUtils.defaultIfEmpty((String)request.getAttribute("LoginUserNm"), "");
	String loginSiteNm = StringUtils.defaultIfEmpty((String)request.getAttribute("LoginSiteNm"), "");
%>
<c:set var="loginUserNm" value="<%=loginUserNm%>" />
<c:set var="loginSiteNm" value="<%=loginSiteNm%>" />
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javaScript" language="javascript" defer="defer">
	$(document).ready(function(){
		$("input[name='acceptDt']").datepicker('setDate', 'today');
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
					$("input[name='reFunName']").val("getTransLastInfo");
					layerPopupOpen('memberPopUp');
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
		<%-- 회원연계 정보 조회 --%>
		getTransLastInfo = function(tagMbrNo){
			setButton();
			newInfo();

			$.ajax({
				url : '<c:url value="/nrds/ajaxEdMbrTrinsLastInfoJson.do"/>',
				type : 'POST',
				data : {
					mbrNo : tagMbrNo
				},
				success : function(json){
					var info = json.mbrTransInfo;

					if(info.MBR_NO != ""){
						$("input[name='infoMbrNo']").val(info.MBR_NO);
						$("input[name='infoMbrNm']").val(info.MBR_NM);
						$("input[name='infoGendNm']").val(info.GEND_NM);
						$("input[name='infoAge']").val(info.AGE);
						$("input[name='infoReqOrg']").val(info.COOP_ORG);
						if(info.TEL_NO1 != ''){
							$("input[name='infoTelNo']").val(info.TEL_NO1 + "-" + info.TEL_NO2 + "-" + info.TEL_NO3);
						}
						$("input[name='infoCreIdSiteCd']").val(info.SITE_NM);

						getMbrInfo(tagMbrNo);
						getEdMbrTransList(tagMbrNo);
					}
				},
				error : function(xhr, status){
					console.log(xhr);
				}
			});
		}
		<%-- 회원 기본 정보 조회 --%>
		getMbrInfo = function(tagMbrNo, tagMbrTransId){
			$.ajax({
				url : '<c:url value="/nrds/ajaxTransInfoJson.do"/>',
				type : 'POST',
				data : {
					mbrNo : tagMbrNo,
					mbrTransId : tagMbrTransId
				},
				success : function(json){
					var info = json.mbrTransInfo;

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

						if(info.MBR_TRANS_ID != '' && info.MBR_TRANS_ID != undefined){
							$("input[name='rZipCd']").val(info.R_ZIP_CD);
							$("input[name='rAddr1']").val(info.R_ADDR1);
							$("input[name='rAddr2']").val(info.R_ADDR2);
							$("input[name='crimeName']").val(info.CRIME_NAME);

							$("input[name='mbrTransId']").val(info.MBR_TRANS_ID);
							$("input[name='coopOrg']").val(info.COOP_ORG);
							$("input[name='coopMngr']").val(info.COOP_MNGR);
							$("input[name='coopTel']").val(info.COOP_TEL);
							$("input[name='reqOrdDt']").val(info.REQ_ORD_DT);
							$("input[name='reqEndDt']").val(info.REQ_END_DT);
							$("input[name='reqDt']").val(info.REQ_DT);
							$("input[name='reqType']:radio[value='" + info.REQ_TYPE + "']").prop("checked", true);
							$("textarea[name='reqExample']").val(info.REQ_EXAMPLE);

							$("input[name='fileId']").val("");
							if(info.ORIGNL_FILE_NM != '' && info.ORIGNL_FILE_NM != undefined){
								var fileNameHtml = '<a href="javaScript:downloadFile(\'' + info.FILE_ID + '\', \'' + info.FILE_SEQ + '\');">'
												 + info.ORIGNL_FILE_NM
												 + "</a>&nbsp;&nbsp;&nbsp;<a href='javaScript:setFileDelFlag();'>삭제</a>";
								$("div#fileInfo").html(fileNameHtml);
							}else{
								$("div#fileInfo").html("");
							}

							$("input[name='acceptDt']").val(info.ACCEPT_DT);
							$("input[name='acceptOrg']").val(info.ACCEPT_ORG);
							$("input[name='acceptMngr']").val(info.ACCEPT_MNGR);

							setButton("E");
						}
					}
				},
				error : function(xhr, status){
					console.log(xhr);
				}
			});
		}
		<%-- 정보저장 --%>
		infoSave = function(){
			$.ajax({
				url : '<c:url value="/nrds/ajaxEmtAdd.do"/>',
				type : 'POST',
				processData : false,
				contentType : false,
				enctype : 'multipart/form-data',
				data : new FormData($("#mainForm")[0]),
				success : function(res){
					if(res.err != "Y"){
						alert(res.MSG + "되었습니다.");

						setButton();
						getEdMbrTransList($("input[name='mbrNo']").val());
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
		<%-- 연계 목록--%>
		getEdMbrTransList = function(tagMbrNo){
			$.ajax({
				url : '<c:url value="/nrds/ajaxEmtList.do"/>',
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
		<%-- 폼 초기화 --%>
		newInfo = function(){
			$("input[name='rZipCd']").val("");
			$("input[name='rAddr1']").val("");
			$("input[name='rAddr2']").val("");
			$("input[name='crimeName']").val("");

			$("input[name='mbrTransId']").val("");
			$("input[name='coopOrg']").val("");
			$("input[name='coopMngr']").val("");
			$("input[name='coopTel']").val("");
			$("input[name='reqOrdDt']").val("");
			$("input[name='reqEndDt']").val("");
			$("input[name='reqDt']").val("");
			$("input[name='reqType']").prop("checked", false);
			$("textarea[name='reqExample']").val("");

			$("input[name='fileId']").val("");
			$("div#fileInfo").html("");

			$("input[name='acceptDt']").datepicker('setDate', 'today');
			$("input[name='acceptOrg']").val("<c:out value="${loginSiteNm}" />");
			$("input[name='acceptMngr']").val("<c:out value="${loginUserNm}" />");

			setButton();
		}
		<%-- 정보삭제 --%>
		delInfo = function(){
			if($("input[name='mbrTransId']").val() == ""){
				alert("연계 정보를 선택 하세요.");			return;
			}

			if(confirm("정말로 삭제하시겠습니까?\n삭제한 데이터는 복구가 불가합니다.")){
				$.ajax({
					url : '<c:url value="/nrds/ajaxEmtDel.do"/>',
					type : 'POST',
					data : {
						mbrNo : $("input[name='mbrNo']").val(),
						mbrTransId : $("input[name='mbrTransId']").val()
					},
					success : function(res){
						if(res.err != "Y"){
							alert("삭제되었습니다.");

							setButton();
							getEdMbrTransList($("input[name='mbrNo']").val());
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
							<div class="el-tabs__item is-top" data-id="tab-req">
								<span><i class="el-icon-platform-eleme"></i> 접수(선도조건부)</span>
							</div>
						</a>
						<a href="<c:url value="/nrds/clientLinkMain.do" />">
							<div class="el-tabs__item is-top is-active" data-id="tab-req">
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
							<th><span class="required">*</span> 성명</th>
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
			<form name="mainForm" id="mainForm" method="post" enctype="multipart/form-data">
			<input type="hidden" name="mbrTransId" />
			<input type="hidden" name="fileDelFlag" />
			<div class="el-row">
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
										<td><input name="juminNo1" type="text" readonly class="el-input__inner" style="width: 90px;" placeholder="생년월일"></td>
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
				<div class="el-row">
					<div class="row2">
						<div class="section pdn">
							<div class="el-card_header">
								<h2><i class="el-icon-s-opportunity"></i> 의뢰정보</h2>
							</div>
							<div class="el-card_body">
								<table class="w-auto wr-form">
									<tbody>
										<tr>
											<th>연계기관</th>
											<td><input type="text" name="coopOrg" class="el-input__inner" placeholder="연계기관" maxlength="50" style="width: 130px;" /></td>
											<th>연계자</th>
											<td><input type="text" name="coopMngr" class="el-input__inner" placeholder="연계자" maxlength="10" style="width: 130px;" /></td>
											<th>연락처</th>
											<td><input type="text" name="coopTel" class="el-input__inner" placeholder="연락처" maxlength="15" style="width: 130px;" /></td>
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
									</tbody>
								</table>
							</div>
						</div>
						<div class="section pdn">
							<div class="el-card_header">
								<h2><i class="el-icon-s-opportunity"></i> 연계상세</h2>
							</div>
							<div class="el-card_body">
								<table class="w-auto wr-form">
									<tbody>
										<tr>
											<th>의뢰일자</th>
											<td>
												<div class="dat-pk">
													<i class="el-input__icon el-icon-date"></i>
													<input type="text" name="reqDt" class="el-input__inner datepicker" placeholder="의뢰일자" />
												</div>
											</td>
											<th>요청교육과정</th>
											<td>
<c:if test="${reqDetailsList ne null and reqDetailsList ne ''}">
	<c:forEach var="result" items="${reqDetailsList}" varStatus="status">
												<span class="ck-bx">
													<input type="radio" class="el-radio__original" name="reqType" id="reqType-<c:out value='${status.count}'/>" value="${result.CD_ID}" />
													<label for="reqType-<c:out value='${status.count}'/>"><span class="el-radio__input"><span class="el-radio__inner"></span></span><c:out value="${result.CD_NM}" /></label>
												</span>
	</c:forEach>
</c:if>
											</td>
										</tr>
										<tr>
											<th>의뢰사례</th>
											<td colspan="3"><textarea name="reqExample" rows="4"></textarea></td>
										</tr>
										<tr>
											<th>첨부파일</th>
											<td colspan="3">
												<input type="file" name="fileId" />
												<div id="fileInfo"></div>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="section pdn">
				<div class="el-card_header">
					<h2><i class="el-icon-s-opportunity"></i> 처리</h2>
				</div>
				<div class="el-card_body">
					<table class="w-auto">
						<tbody>
							<tr>
								<th>접수일자</th>
								<td>
									<div class="dat-pk">
										<i class="el-input__icon el-icon-date"></i>
										<input name="acceptDt" type="text" class="el-input__inner datepicker" placeholder="접수일자" style="width: 130px;">
									</div>
								</td>
								<th>접수기관</th>
								<td><input name="acceptOrg" value="<c:out value="${loginSiteNm}" />" type="text" class="el-input__inner" readonly placeholder="접수기관" style="width:160px"></td>
								<th>접수자</th>
								<td><input name="acceptMngr" value="<c:out value="${loginUserNm}" />" type="text" class="el-input__inner" readonly placeholder="접수자" style="width:120px"></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			</form>
		</div>
	</div>
</div>
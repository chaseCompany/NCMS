<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kr.co.chase.ncms.common.ConstantObject" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javaScript" language="javascript" defer="defer">
	$(document).ready(function(){
		$("input[name='creUsrDt']").datepicker('setDate', 'today');
		<%-- 나이계산 --%>
		checkAge = function(){
			var juminNo = $("input[name='juminNo1']").val();
			if(juminNo.length < 6){
				alert("생년월일을 정확히 입력해 주세요.");
				$("input[name='juminNo1']").focus();			return;
			}

			var year = Number(juminNo.substr(0, 2));
			var day = Number(juminNo.substr(2, 4));
			var dDayYear = Number(String(new Date().getFullYear()).substr(2, 2));
			var dDayDate = Number(String(new Date().getMonth() + 1) + String(new Date().getDate()));
			var age = 0;

			if(dDayYear > year){
				age = dDayYear - year;
			}else{
				age = dDayYear + 100 - year;
			}
			// 한국 나이로 계산
			age = age + 1;

			$("input[name='age']").val(age);
		}
		<%-- 우편번호 검색창 오픈 --%>
		zipCodePop = function(){
			new daum.Postcode({
				oncomplete: function(data) {
					$("input[name='zipCd']").val(data.zonecode);
					$("input[name='addr1']").val(data.roadAddress);
				}
			}).open();
		}
		<%-- 정보 저장 --%>
		infoSave = function(){
			if($("input[name='mbrNm']").val() == ""){
				alert("성명을 입력하세요.");
				$("input[name='mbrNm']").focus();				return;
			}
			if($("select[name='gendCd']").val() == ""){
				alert("성별을 선택하세요.");
				$("select[name='gendCd']").focus();				return;
			}
			if(!$("input[name='frgCd']").is(":checked")){
				alert("내/외국인 유무를 선택하세요.");
				$("input[name='frgCd']").focus();				return;
			}
			if($("input[name='juminNo1']").val() == ""){
				alert("생년월일을 입력하세요.");
				$("input[name='juminNo1']").focus();			return;
			}
			if($("input[name='age']").val() == ""){
				alert("연령을 입력하세요.");
				$("input[name='age']").focus();					return;
			}
			if($("input[name='telNo1']").val() == "" || $("input[name='telNo2']").val() == "" || $("input[name='telNo3']").val() == ""){
				alert("연락처를 입력하세요.");
				$("input[name='telNo1']").focus();				return;
			}
			if($("input[name='zipCd']").val() == "" || $("input[name='addr1']").val() == ""){
				alert("주소를 입력하세요.");
				zipCodePop();									return;
			}
			if(!$("input[name='mbrSt']").is(":checked")){
				alert("대상자상태를 선택하세요.");
				$("input[name='mbrSt']").focus();				return;
			}
			if($("input[name='creUsrDt']").val() == ""){
				alert("최초등록일자을 입력하세요.");
				$("input[name='creUsrDt']").focus();				return;
			}

			$.ajax({
				url : '<c:url value="/nrds/ajaxEdMbrAdd.do"/>',
				type : 'POST',
				data : $('#mainForm').serialize(),
				success : function(res){
					if(res.err != "Y"){
						alert(res.MSG + "되었습니다.");

						viewMemInfo(res.mbrNo);
					}else{
						alert(res.MSG);
					}
				},
				error : function(xhr, status){
					console.log(xhr);
				}
			});
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
					$("input[name='reFunName']").val("viewMemInfo");
					layerPopupOpen('memberPopUp');
				},
				error : function(xhr, status){
					console.log(xhr);
				}
			});
		}
		<%-- 회원정보 상세 조회 --%>
		viewMemInfo = function(tagMbrNo){
			$.ajax({
				url : '<c:url value="/nrds/ajaxMbrInfoJson.do"/>',
				type : 'POST',
				data : {
					mbrNo : tagMbrNo
				},
				success : function(json){
					var info = json.mbrInfo;

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

						setButton("E");
					}
				},
				error : function(xhr, status){
					console.log(xhr);
				}
			});
		}
		<%-- 회원정보 초기화 --%>
		newMemInfo = function(){
			$("input[name='mbrNo']").val("");
			$("input[name='mbrNm']").val("");
			$("select[name='gendCd']").val("M").prop("selected", true);
			$("input[name='frgCd']").prop("checked", false);
			$("input[name='juminNo1']").val("");
			$("input[name='age']").val("");
			$("input[name='telNo1']").val("");
			$("input[name='telNo2']").val("");
			$("input[name='telNo3']").val("");
			$("input[name='zipCd']").val("");
			$("input[name='addr1']").val("");
			$("input[name='addr2']").val("");
			$("input[name='mbrSt']").prop("checked", false);
			$("input[name='creUsrDt']").datepicker('setDate', 'today');
			$("input[name='updDt']").val("");
			$("input[name='updNm']").val("");

			setButton("N");
		}
		<%-- 회원 정보 삭제 --%>
		delMemInfo = function(){
			if(confirm("정말로 삭제하시겠습니까?\n삭제한 데이터는 복구가 불가합니다.")){
				$.ajax({
					url : '<c:url value="/nrds/ajaxEdMbrDel.do"/>',
					type : 'POST',
					data : {
						mbrNo : $("input[name='mbrNo']").val()
					},
					success : function(res){
						if(res.err != "Y"){
							alert("삭제되었습니다.");

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
				$("button#stsButNo").hide();
				$("button#stsRlButYes").show();
				$("button#excelButNo").hide();
				$("button#excelButYes").show();
			}else{
				$("button#delButNo").show();
				$("button#delButNoYes").hide();
				$("button#stsButNo").show();
				$("button#stsRlButYes").hide();
				$("button#excelButNo").show();
				$("button#excelButYes").hide();
			}
		}
		
		<%-- 엑셀다운로드 --%>
		clientExcel = function(){
			if($("input[name='mbrNo']").val() != ""){
				$("form#excelForm").empty();
				$("form#excelForm").append("<input type='hidden' name='mbrNo' value='" + $("input[name='mbrNo']").val() + "' />");
				$("form#excelForm").attr("action", "/nrds/clientExcelDownload.do");
				$("form#excelForm").submit();
			}else{
				alert("회원을 선택 하세요.");
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
	<button type="button" onclick="javaScript:newMemInfo();" class="el-button normal el-button--default el-button--small is-plain" style="margin-left: 8px;">
		<i class="el-icon-circle-plus-outline"></i><span>신규</span>
	</button>
	<button disabled="disabled" id="delButNo" type="button" class="el-button normal el-button--default el-button--small is-disabled is-plain">
		<i class="el-icon-delete-solid"></i><span>삭제</span>
	</button>
	<button type="button" id="delButNoYes" onclick="javaScript:delMemInfo();" class="el-button normal el-button--default el-button--small is-plain" style="display:none;">
		<i class="el-icon-delete-solid"></i><span>삭제</span>
	</button>
	<button disabled="disabled" id="stsButNo" type="button" class="el-button normal el-button--danger el-button--small is-disabled is-plain" style="margin-left: 8px;">
		<i class="el-icon-s-claim"></i><span>퇴록 처리</span>
	</button>
	<button type="button" id="stsRlButYes" onclick="javaScript:openStsPage('A', '<%=ConstantObject.rlMemStsCd%>');" class="el-button normal el-button--danger el-button--small is-plain" style="margin-left: 8px;display:none;">
		<i class="el-icon-s-claim"></i><span>퇴록 처리</span>
	</button>
	<button type="button" id="stsRrButYes" onclick="javaScript:openStsPage('A', '<%=ConstantObject.rrMemStsCd%>');" class="el-button normal el-button--danger el-button--small is-plain" style="margin-left: 8px;display:none;">
		<i class="el-icon-s-claim"></i><span>재등록 처리</span>
	</button>
	<button type="button" id="excelButNo" disabled="disabled" class="el-button normal el-button--default el-button--small is-plain">
		<i class="el-icon-document"></i><span>엑셀다운로드</span>
	</button>
	<button type="button" onclick="javaScript:clientExcel();" id="excelButYes" class="el-button normal el-button--default el-button--small is-plain" style="display: none;">
		<i class="el-icon-document"></i><span>엑셀다운로드</span>
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
							<div class="el-tabs__item is-top is-active" data-id="tab-mem">
								<span><i class="el-icon-s-help"></i> 대상자관리</span>
							</div>
						</a>
						<a href="<c:url value="/nrds/clientLawConMain.do" />">
							<div class="el-tabs__item is-top" data-id="tab-mem">
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
			<form name="mainForm" id="mainForm" method="post">
			<div class="el-row">
				<div class="row2">
					<div class="section pdn mgn">
						<div class="el-card_header">
							<h2><i class="el-icon-s-opportunity"></i> 기본 정보</h2>
						</div>
						<div class="el-card_body">
							<table class="w-auto wr-form">
								<tbody>
								<tr>
									<th>대상자등록번호</th>
									<td>
										<div class="search-input tac">
											<input name="mbrNo" type="text" readonly placeholder="저장시 자동 생성" class="el-input__inner" style="width: 142px;" />
											<button type="button" onclick="javaScript:mstMbrSearchPopup();"><i class="el-icon-search"></i></button>
										</div>
									</td>
									<th><span class="required">*</span> 성명</th>
									<td><input name="mbrNm" type="text" class="el-input__inner" placeholder="성명" maxlength="5" style="width: 130px;" /></td>
									<th><span class="required">*</span> 성별</th>
									<td>
										<div class="gen-check">
											<select name="gendCd">
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
											<input type="radio" class="el-radio__original" name="frgCd" value="<c:out value="${result.CD_ID}" />" id="frgCd-<c:out value="${status.count}" />">
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
										<input name="juminNo1" type="text" class="el-input__inner" style="width: 90px;" maxlength="6" placeholder="생년월일">
										<button type="button" onclick="javaScript:checkAge();" class="el-button el-button--primary el-button--small is-plain" style="padding: 9px 10px 8px;">
											<i class="el-icon-d-arrow-right"></i>
										</button>
									</td>
									<th><span class="required">*</span> 연령</th>
									<td><input name="age" type="text" class="el-input__inner" style="width: 65px;" maxlength="3" placeholder="연령"></td>
									<th><span class="required">*</span> 연락처</th>
									<td colspan="2">
										<div class="dsp-ibk"><input name="telNo1" type="text" maxlength="4" class="el-input__inner" style="width:54px"></div>
										<span>-</span>
										<div class="dsp-ibk"><input name="telNo2" type="text" maxlength="4" class="el-input__inner" style="width:54px"></div>
										<span>-</span>
										<div class="dsp-ibk"><input name="telNo3" type="text" maxlength="4" class="el-input__inner" style="width:53px"></div>
									</td>
								</tr>
								<tr>
									<th class="v-top"><span class="required">*</span> 주소</th>
									<td colspan="7">
										<input type="text" name="zipCd" class="el-input__inner" readonly style="width: 65px;">
										<button type="button" onclick="javaScript:zipCodePop();" class="el-button el-button--primary el-button--small is-plain" style="padding: 9px 12px 8px;">
											<i class="el-icon-search"></i>
										</button>
										<input name="addr1" type="text" class="el-input__inner" readonly style="width: 525px;">
										<div style="margin-top:5px">
											<input name="addr2" type="text" class="el-input__inner" maxlength="25" style="width: 634px;">
										</div>
									</td>
								</tr>
								</tbody>
							</table>
							<table class="w-auto wr-form">
								<colgroup>
									<col style="width: 100px;">
								</colgroup>
								<tbody>
								<tr>
									<th><span class="required">*</span> 대상자상태</th>
									<td>
<c:if test="${mbrStCdList ne null and mbrStCdList ne ''}">
	<c:forEach var="result" items="${mbrStCdList}" varStatus="status">
										<span class="ck-bx">
											<input type="radio" class="el-radio__original" name="mbrSt" value="<c:out value="${result.CD_ID}" />" id="mbrSt-<c:out value="${status.count}" />">
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
			</div>
			<div class="el-row">
				<div class="row2">
					<div class="section">
						<table class="w-auto">
						<tbody>
						<tr>
							<th><span class="required">*</span> 최초등록일자</th>
							<td>
								<div class="dat-pk">
									<i class="el-input__icon el-icon-date"></i>
									<input name="creUsrDt" type="text" class="el-input__inner datepicker" placeholder="날짜" style="width: 130px;">
								</div>
							</td>
							<th>최종수정일시</th>
							<td><input name="updDt" type="text" class="el-input__inner" readonly style="width:160px"></td>
							<th>최종수정자</th>
							<td><input name="updNm" type="text" class="el-input__inner" readonly style="width:120px"></td>
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
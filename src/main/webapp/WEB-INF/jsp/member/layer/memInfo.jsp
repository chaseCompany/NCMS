<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kr.co.chase.ncms.common.ConstantObject" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${viewType ne null and viewType ne ''}">
	<c:set var="inputFlagStr" value="readonly" />
	<c:set var="selectboxFlagStr" value="disabled" />
</c:if>
<script type="text/javaScript" language="javascript" defer="defer">
	$(document).ready(function(){
<c:if test="${mbrInfo eq null or mbrInfo eq ''}">
		$("input[name='regDt']").datepicker('setDate', 'today');
</c:if>
		<%-- 회원 조회 팝업 --%>
		mstMbrSearchPopup = function(){
			$.ajax({
				url : '/ajaxMstMbrList.do',
				type : 'POST',
				data : {
					pageNo : $("input[name='memPageNo']").val(),
					mbrNm : $("input[name='memSchMbrNm']").val(),
					telNo : $("input[name='memSchTelNo']").val()
<c:if test="${viewType ne null and viewType ne ''}">
				  , searchType : "S"
</c:if>
				},
				success : function(res){
					$("div[id='layerpopup']").html(res);
					$("div[id='layerpopup']").attr("data-popup", "memberPopUp");
					$("input[name='reFunName']").val("viewMemInfo");
					layerPopupOpen('memberPopUp');
					$("button#excelButNo").hide();
					$("button#excelButYes").show();
				},
				error : function(xhr, status){
					console.log(xhr);
				}
			});
		}
		<%-- 회원정보 선택 --%>
		viewMemInfo = function(obj){
			getMemInfo(obj.MBR_NO);
		}
		<%-- 정보 저장 --%>
		infoSave = function(){
			if($("input[name='mbrNm']").val() == ""){
				alert("성명은 필수 입력 항목입니다.");
				$("input[name='mbrNm']").focus();					return;
			}
			if($("input[name='juminNo1']").val() == ""){
				alert("생년월일은 필수 입력 항목입니다.");
				$("input[name='juminNo1']").focus();				return;
			}
			if($("input[name='age']").val() == ""){
				alert("연령은 필수 입력 항목입니다.");
				$("input[name='age']").focus();						return;
			}
			if($("input[name='telNo1']").val() == ""){
				alert("연락처1는 필수 입력 항목입니다.");
				$("input[name='telNo1']").focus();					return;
			}
			if($("input[name='telNo2']").val() == ""){
				alert("연락처2는 필수 입력 항목입니다.");
				$("input[name='telNo2']").focus();					return;
			}
			if($("input[name='telNo3']").val() == ""){
				alert("연락처3는 필수 입력 항목입니다.");
				$("input[name='telNo3']").focus();					return;
			}
			if($("input[name='zipCd']").val() == ""){
				alert("주소는 필수 입력 항목입니다.");						return;
			}
			if($("select[name='mbrTpCd']").val() == ""){
				alert("회원구분은 필수 입력 항목입니다.");
				$("select[name='mbrTpCd']").focus();				return;
			}
			if($("select[name='medicCareCd']").val() == ""){
				alert("의료보장은 필수 입력 항목입니다.");
				$("select[name='medicCareCd']").focus();			return;
			}
			if($("input[name='fmlyName']").val() == ""){
				alert("보호자 성명은 필수 입력 항목입니다.");
				$("input[name='fmlyName']").focus();			return;
			}
			if($("select[name='fmlyRelationCd']").val() == ""){
				alert("보호자 관계는 필수 입력 항목입니다.");
				$("select[name='fmlyRelationCd']").focus();			return;
			}
			if($("input[name='fmlyAge']").val() == ""){
				alert("보호자 연령은 필수 입력 항목입니다.");
				$("input[name='fmlyAge']").focus();					return;
			}
			if($("select[name='mngUsrId']").val() == ""){
				alert("사례관리자는 필수 입력 항목입니다.");
				$("select[name='mngUsrId']").focus();				return;
			}
			if($("input[name='regDt']").val() == ""){
				alert("최초등록일자는 필수 입력 항목입니다.");
				$("input[name='regDt']").focus();					return;
			}

			$.ajax({
				url : '/ajaxMstMbrAdd.do',
				type : 'POST',
				processData : false,
				contentType : false,
				enctype : 'multipart/form-data',
				data : new FormData($("#mbrInfoForm")[0]),
				success : function(res){
					if(res.err != "Y"){
						alert(res.MSG + " 성공");

						getMemInfo(res.mbrNo);
					}else{
						alert(res.MSG);
					}
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
					$("input[name='zipCd']").val(data.zonecode);
					$("input[name='addr1']").val(data.roadAddress);
				}
			}).open();
		}
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
<c:if test="${viewType eq null or viewType eq ''}">
	<c:if test="${mbrInfo ne null and mbrInfo ne ''}">
		setButton('L');
		getMbrRegHistList("<c:out value="${mbrInfo.MBR_NO}" />");
		<c:if test="${mbrInfo.STS_CD eq ConstantObject.defaultMemStsCd or mbrInfo.STS_CD eq ConstantObject.rrMemStsCd}">
		$("button#stsRlButYes").show();
		</c:if>
		<c:if test="${mbrInfo.STS_CD eq ConstantObject.rlMemStsCd}">
		$("button#stsRrButYes").show();
		</c:if>
	</c:if>
	<c:if test="${mbrInfo eq null or mbrInfo eq ''}">
		setButton('');
	</c:if>
</c:if>
	});
</script>
<!-- 기본정보 , 보호자 정보 -->
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
						<th>회원등록번호</th>
						<td>
							<div class="search-input tac">
								<input name="mbrNo" value="<c:out value="${mbrInfo.MBR_NO}" />" type="text" readonly placeholder="저장시 자동 생성" class="el-input__inner" style="width: 142px;" />
<c:if test="${searchFlag ne 'N'}">
								<button type="button" onclick="javaScript:mstMbrSearchPopup();"><i class="el-icon-search"></i></button>
</c:if>
							</div>
						</td>
						<th><span class="required">*</span> 성명</th>
						<td><input name="mbrNm" value="<c:out value="${mbrInfo.MBR_NM}" />" type="text" <c:out value="${inputFlagStr}"/> class="el-input__inner" placeholder="성명" style="width: 130px;" /></td>
						<th><span class="required">*</span> 성별</th>
						<td>
							<div class="gen-check">
<c:if test="${gendCdList ne null and gendCdList ne ''}">
	<c:if test="${mbrInfo.GEND_CD eq null or mbrInfo.GEND_CD eq ''}">
		<c:set var="gendCd" value="M" />
	</c:if>
	<c:if test="${mbrInfo.GEND_CD ne null and mbrInfo.GEND_CD ne ''}">
		<c:set var="gendCd" value="${mbrInfo.GEND_CD}" />
	</c:if>
	<c:forEach var="result" items="${gendCdList}" varStatus="status">
								<span>
									<input type="radio" name="gendCd" value="<c:out value="${result.CD_ID}" />" class="el-radio-button__orig-radio" id="gendCd-<c:out value="${status.count}" />"<c:if test="${gendCd eq result.CD_ID}"> checked</c:if> />
									<label for="gendCd-<c:out value="${status.count}" />" class="el-radio-button el-radio-button--small">
										<span class="el-radio-button__inner"><c:out value="${result.CD_NM}" /></span>
									</label>
								</span>
	</c:forEach>
</c:if>
							</div>
						</td>
						<th><span class="required">*</span> 내/외국인</th>
						<td>
<c:if test="${frgCdList ne null and frgCdList ne ''}">
	<c:if test="${mbrInfo.FRG_CD eq null or mbrInfo.FRG_CD eq ''}">
		<c:set var="frgCd" value="LO" />
	</c:if>
	<c:if test="${mbrInfo.FRG_CD ne null and mbrInfo.FRG_CD ne ''}">
		<c:set var="frgCd" value="${mbrInfo.FRG_CD}" />
	</c:if>
	<c:forEach var="result" items="${frgCdList}" varStatus="status">
							<span class="ck-bx">
								<input type="radio" class="el-radio__original" name="frgCd" value="<c:out value="${result.CD_ID}" />" id="frgCd-<c:out value="${status.count}" />"<c:if test="${frgCd eq result.CD_ID}"> checked</c:if>>
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
							<input name="juminNo1" value="<c:out value="${mbrInfo.JUMIN_NO1}" />" type="text" <c:out value="${inputFlagStr}"/> class="el-input__inner" style="width: 90px;" placeholder="6자리">
							<button type="button" onclick="javaScript:checkAge();" class="el-button el-button--primary el-button--small is-plain" style="padding: 9px 10px 8px;">
								<i class="el-icon-d-arrow-right"></i>
							</button>
						</td>
						<th><span class="required">*</span> 연령</th>
						<td><input name="age" value="<c:out value="${mbrInfo.AGE}" />" type="text" <c:out value="${inputFlagStr}"/> class="el-input__inner" style="width: 65px;" placeholder="연령"></td>
						<th><span class="required">*</span> 연락처</th>
						<td colspan="2">
							<div class="dsp-ibk"><input name="telNo1" value="<c:out value="${mbrInfo.TEL_NO1}" />" type="text" <c:out value="${inputFlagStr}"/> maxlength="4" class="el-input__inner" style="width:54px"></div>
							<span>-</span>
							<div class="dsp-ibk"><input name="telNo2" value="<c:out value="${mbrInfo.TEL_NO2}" />" type="text" <c:out value="${inputFlagStr}"/> maxlength="4" class="el-input__inner" style="width:54px"></div>
							<span>-</span>
							<div class="dsp-ibk"><input name="telNo3" value="<c:out value="${mbrInfo.TEL_NO3}" />" type="text" <c:out value="${inputFlagStr}"/> maxlength="4" class="el-input__inner" style="width:53px"></div>
						</td>
					</tr>
					<tr>
						<th class="v-top"><span class="required">*</span> 주소</th>
						<td colspan="7">
							<input type="text" name="zipCd" value="<c:out value="${mbrInfo.ZIP_CD}" />" class="el-input__inner" readonly style="width: 65px;">
							<button type="button" onclick="javaScript:zipCodePop();" class="el-button el-button--primary el-button--small is-plain" style="padding: 9px 12px 8px;">
								<i class="el-icon-search"></i>
							</button>
							<input name="addr1" value="<c:out value="${mbrInfo.ADDR1}" />" type="text" class="el-input__inner" readonly style="width: 525px;">
							<div style="margin-top:5px">
								<input name="addr2" value="<c:out value="${mbrInfo.ADDR2}" />" type="text" <c:out value="${inputFlagStr}"/> class="el-input__inner" style="width: 634px;">
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
						<th><span class="required">*</span> 회원구분</th>
						<td>
							<select name="mbrTpCd" <c:out value="${selectboxFlagStr}" /> style="width: 150px;">
<c:if test="${mbrTpCdList ne null and mbrTpCdList ne ''}">
	<c:forEach var="result" items="${mbrTpCdList}" varStatus="status">
								<option value="<c:out value="${result.CD_ID}" />"<c:if test="${mbrInfo.MBR_TP_CD eq result.CD_ID}"> selected</c:if>><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
							</select>
						</td>
						<th><span class="required">*</span> 약물사용자와의 관계</th>
						<td colspan="3">
<c:if test="${drgUseCdList ne null and drgUseCdList ne ''}">
	<c:if test="${mbrInfo.DRG_USE_CD eq null or mbrInfo.DRG_USE_CD eq ''}">
		<c:set var="drgUseCd" value="10" />
	</c:if>
	<c:if test="${mbrInfo.DRG_USE_CD ne null and mbrInfo.DRG_USE_CD ne ''}">
		<c:set var="drgUseCd" value="${mbrInfo.DRG_USE_CD}" />
	</c:if>
	<c:forEach var="result" items="${drgUseCdList}" varStatus="status">
							<span class="ck-bx">
								<input type="radio" class="el-radio__original" name="drgUseCd" value="<c:out value="${result.CD_ID}" />" id="drgUseCd-<c:out value="${status.count}" />"<c:if test="${drgUseCd eq result.CD_ID}"> checked</c:if>>
								<label for="drgUseCd-<c:out value="${status.count}" />"><span class="el-radio__input"><span class="el-radio__inner"></span></span> <c:out value="${result.CD_NM}" /></label>
							</span>
	</c:forEach>
</c:if>
						</td>
					</tr>
					<tr>
						<th><span class="required">*</span> 의료보장</th>
						<td>
							<select name="medicCareCd" <c:out value="${selectboxFlagStr}" /> style="width: 150px;">
								<option value="">선택</option>
<c:if test="${medicCareCdList ne null and medicCareCdList ne ''}">
	<c:forEach var="result" items="${medicCareCdList}" varStatus="status">
								<option value="<c:out value="${result.CD_ID}" />"<c:if test="${mbrInfo.MEDIC_CARE_CD eq result.CD_ID}"> selected</c:if>><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
							</select>
						</td>
						<th>학력</th>
						<td colspan="5">
							<select name="eduCd" <c:out value="${selectboxFlagStr}" /> style="width: 135px;">
								<option value="">선택</option>
<c:if test="${eduCdList ne null and eduCdList ne ''}">
	<c:forEach var="result" items="${eduCdList}" varStatus="status">
								<option value="<c:out value="${result.CD_ID}" />"<c:if test="${mbrInfo.EDU_CD eq result.CD_ID}"> selected</c:if>><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
							</select>
							<select name="edu02Cd" <c:out value="${selectboxFlagStr}" /> style="width: 135px;">
								<option value="">선택</option>
<c:if test="${edu02CdList ne null and edu02CdList ne ''}">
	<c:forEach var="result" items="${edu02CdList}" varStatus="status">
								<option value="<c:out value="${result.CD_ID}" />"<c:if test="${mbrInfo.EDU_CD eq result.CD_ID}"> selected</c:if>><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
							</select>
						</td>
					</tr>
					<tr>
						<th> 결혼여부</th>
						<td>
							<select name="mrgCd" <c:out value="${selectboxFlagStr}" /> style="width: 150px;">
								<option value="">선택</option>
<c:if test="${mrgCdList ne null and mrgCdList ne ''}">
	<c:forEach var="result" items="${mrgCdList}" varStatus="status">
								<option value="<c:out value="${result.CD_ID}" />"<c:if test="${mbrInfo.MRG_CD eq result.CD_ID}"> selected</c:if>><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
							</select>
						</td>
						<th>상담요청경로</th>
						<td colspan="5">
							<select name="reqPathCd" <c:out value="${selectboxFlagStr}" /> style="width: 270px;">
								<option value="">선택</option>
<c:if test="${reqPathCdList ne null and reqPathCdList ne ''}">
	<c:forEach var="result" items="${reqPathCdList}" varStatus="status">
								<option value="<c:out value="${result.CD_ID}" />"<c:if test="${mbrInfo.REQ_PATH_CD eq result.CD_ID}"> selected</c:if>><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
							</select>
						</td>
					</tr>
					<tr>
						<th>종교</th>
						<td>
							<select name="rlgnCd" <c:out value="${selectboxFlagStr}" /> style="width: 150px;">
								<option value="">없음</option>
<c:if test="${rlgnCdList ne null and rlgnCdList ne ''}">
	<c:forEach var="result" items="${rlgnCdList}" varStatus="status">
								<option value="<c:out value="${result.CD_ID}" />"<c:if test="${mbrInfo.RLGN_CD eq result.CD_ID}"> selected</c:if>><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
							</select>
						</td>
						<th>직업</th>
						<td colspan="5">
							<select name="jobCd" <c:out value="${selectboxFlagStr}" /> style="width: 270px;">
								<option value="">선택</option>
<c:if test="${jobCdList ne null and jobCdList ne ''}">
	<c:forEach var="result" items="${jobCdList}" varStatus="status">
								<option value="<c:out value="${result.CD_ID}" />"<c:if test="${mbrInfo.JOB_CD eq result.CD_ID}"> selected</c:if>><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
							</select>
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
						<th>재등록사유</th>
						<td><input name="disEtc" value="<c:out value="${mbrInfo.DIS_ETC}" />" type="text" <c:out value="${inputFlagStr}"/> class="el-input__inner" style="width: 576px;"></td>
					</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div class="row2">
		<div class="section pdn mgn">
			<div class="el-card_header">
				<h2><i class="el-icon-s-opportunity"></i> 보호자 정보</h2>
			</div>
			<div class="el-card_body">
				<table class="w-auto wr-form">
					<colgroup>
						<col style="width: 70px;">
						<col style="width: 150px;">
						<col style="width: 70px;">
						<col>
					</colgroup>
					<tbody>
					<tr>
						<th><span class="required">*</span> 성명</th>
						<td><input name="fmlyName" value="<c:out value="${mbrInfo.FMLY_NAME}"/>" type="text" class="el-input__inner" style="width: 100%;"></td>
						<th><span class="required">*</span> 관계</th>
						<td>
							<select name="fmlyRelationCd" style="width: 270px;">
								<option value="">선택</option>
<c:if test="${fmlyRelationList ne null and fmlyRelationList ne ''}">
	<c:forEach var="result" items="${fmlyRelationList}" varStatus="status">
								<option value="<c:out value="${result.CD_ID}" />"<c:if test="${mbrInfo.FMLY_RELATION_CD eq result.CD_ID}"> selected</c:if>><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
							</select>
						</td>
					</tr>
					<tr>
						<th><span class="required">*</span> 성별</th>
						<td>
<c:if test="${gendCdList ne null and gendCdList ne ''}">
	<c:if test="${mbrInfo.FMLY_SEX_CD eq null or mbrInfo.FMLY_SEX_CD eq ''}">
		<c:set var="fmlySexCd" value="M" />
	</c:if>
	<c:if test="${mbrInfo.FMLY_SEX_CD ne null and mbrInfo.FMLY_SEX_CD ne ''}">
		<c:set var="fmlySexCd" value="${mbrInfo.FMLY_SEX_CD}" />
	</c:if>
	<c:forEach var="result" items="${gendCdList}" varStatus="status">
							<span class="ck-bx">
								<input type="radio" class="el-radio__original" name="fmlySexCd" value="<c:out value="${result.CD_ID}" />" id="fmlySexCd-<c:out value="${status.count}" />"<c:if test="${fmlySexCd eq result.CD_ID}"> checked</c:if>>
								<label for="fmlySexCd-<c:out value="${status.count}" />"><span class="el-radio__input"><span class="el-radio__inner"></span></span> <c:out value="${result.CD_NM}" /></label>
							</span>
	</c:forEach>
</c:if>
						</td>
						<th><span class="required">*</span> 연령</th>
						<td><input name="fmlyAge" value="<c:out value="${mbrInfo.FMLY_AGE}"/>" type="text" class="el-input__inner" style="width: 100%;"></td>
					</tr>
					<tr>
						<th>지역</th>
						<td colspan="3"><input name="fmlyArea" value="<c:out value="${mbrInfo.FMLY_AREA}"/>" type="text" class="el-input__inner" style="width: 100%;"></td>
					</tr>
					<tr>
						<th>연락처</th>
						<td colspan="3">
							<div class="dsp-ibk"><input name="fmlyTelNo1" value="<c:out value="${mbrInfo.FMLY_TEL_NO1}"/>" type="text" maxlength="4" class="el-input__inner" style="width:54px"></div>
							<span>-</span>
							<div class="dsp-ibk"><input name="fmlyTelNo2" value="<c:out value="${mbrInfo.FMLY_TEL_NO2}"/>" type="text" maxlength="4" class="el-input__inner" style="width:54px"></div>
							<span>-</span>
							<div class="dsp-ibk"><input name="fmlyTelNo3" value="<c:out value="${mbrInfo.FMLY_TEL_NO3}"/>" type="text" maxlength="4" class="el-input__inner" style="width:53px"></div>
						</td>
					</tr>
					<tr>
						<th>지지정도</th>
						<td colspan="3">
<c:if test="${fmlyExpList ne null and fmlyExpList ne ''}">
	<c:if test="${mbrInfo.FMLY_EXP_CD eq null or mbrInfo.FMLY_EXP_CD eq ''}">
		<c:set var="fmlyExpCd" value="1" />
	</c:if>
	<c:if test="${mbrInfo.FMLY_EXP_CD ne null and mbrInfo.FMLY_EXP_CD ne ''}">
		<c:set var="fmlyExpCd" value="${mbrInfo.FMLY_EXP_CD}" />
	</c:if>
	<c:forEach var="result" items="${fmlyExpList}" varStatus="status">
							<span class="ck-bx">
								<input type="radio" class="el-radio__original" name="fmlyExpCd" value="<c:out value="${result.CD_ID}" />" id="fmlyExpCd-<c:out value="${status.count}" />"<c:if test="${fmlyExpCd eq result.CD_ID}"> checked</c:if>>
								<label for="fmlyExpCd-<c:out value="${status.count}" />"><span class="el-radio__input"><span class="el-radio__inner"></span></span> <c:out value="${result.CD_NM}" /></label>
							</span>
	</c:forEach>
</c:if>
						</td>
					</tr>
					<tr>
						<th>동거여부</th>
						<td colspan="3">
<c:if test="${fmlyTogetherList ne null and fmlyTogetherList ne ''}">
	<c:if test="${mbrInfo.FMLY_TOGETHER_CD eq null or mbrInfo.FMLY_TOGETHER_CD eq ''}">
		<c:set var="fmlyTogetherCd" value="ZZ" />
	</c:if>
	<c:if test="${mbrInfo.FMLY_TOGETHER_CD ne null and mbrInfo.FMLY_TOGETHER_CD ne ''}">
		<c:set var="fmlyTogetherCd" value="${mbrInfo.FMLY_TOGETHER_CD}" />
	</c:if>
	<c:forEach var="result" items="${fmlyTogetherList}" varStatus="status">
							<span class="ck-bx">
								<input type="radio" class="el-radio__original" name="fmlyTogetherCd" value="<c:out value="${result.CD_ID}" />" id="fmlyTogetherCd-<c:out value="${status.count}" />"<c:if test="${fmlyTogetherCd eq result.CD_ID}"> checked</c:if>>
								<label for="fmlyTogetherCd-<c:out value="${status.count}" />"><span class="el-radio__input"><span class="el-radio__inner"></span></span> <c:out value="${result.CD_NM}" /></label>
							</span>
	</c:forEach>
</c:if>
						</td>
					</tr>
					<tr>
						<th>가족사항</th>
						<td colspan="3"><input name="fmlyRmk" value="<c:out value="${mbrInfo.FMLY_RMK}"/>" type="text" class="el-input__inner" style="width: 100%;"></td>
					</tr>
					<tr>
						<th>가계도</th>
						<td colspan="3">
<c:if test="${mbrInfo.fmlyTreeFileList ne null and mbrInfo.fmlyTreeFileList ne ''}">
	<c:forEach var="result" items="${mbrInfo.fmlyTreeFileList}" varStatus="status">
							<div id="fmlyTreeFileName">
								<a href='javaScript:downloadFile("<c:out value="${result.FILE_ID}" />", "<c:out value="${result.FILE_SEQ}"/>");'><c:out value="${result.ORIGNL_FILE_NM}"/></a>
								&nbsp;&nbsp;<a href='javaScript:deleteFile("fmlyTreeFileName");'>삭제</a>
							</div>
							<input type="hidden" name="fmlyTreeFileNameFlag" value="N" />
	</c:forEach>
</c:if>
							<input name="fmlyTree" type="file" class="el-input__inner" style="width: 100%;">
						</td>
					</tr>
					<tr>
						<th>개인정보동의서</th>
						<td colspan="3">
<c:if test="${mbrInfo.personalInfoFileList ne null and mbrInfo.personalInfoFileList ne ''}">
	<c:forEach var="result" items="${mbrInfo.personalInfoFileList}" varStatus="status">
							<div id="personalInfoFileName">
								<a href='javaScript:downloadFile("<c:out value="${result.FILE_ID}" />", "<c:out value="${result.FILE_SEQ}"/>");'><c:out value="${result.ORIGNL_FILE_NM}"/></a>
								&nbsp;&nbsp;<a href='javaScript:deleteFile("personalInfoFileName");'>삭제</a>
							</div>
							<input type="hidden" name="personalInfoFileNameFlag" value="N" />
	</c:forEach>
</c:if>
							<input name="personalInfo" type="file" class="el-input__inner" style="width: 100%;">
						</td>
					</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<!-- // 기본정보 , 보호자 정보 -->
<c:if test="${viewType eq null or viewType eq ''}">
<!-- 사례관리자 ~ 최종수정자 -->
<div class="section">
	<table class="w-auto">
		<tbody>
		<tr>
			<th><span class="required">*</span> 사례관리자</th>
			<td>
				<select name="mngUsrId" style="width:130px">
					<option value="">선택</option>
	<c:if test="${sysMbrList ne null and sysMbrList ne ''}">
		<c:forEach var="result" items="${sysMbrList}" varStatus="status">
					<option value="<c:out value="${result.USR_ID}" />"<c:if test="${mbrInfo.MNG_USR_ID eq result.USR_ID}"> selected</c:if>><c:out value="${result.USR_NM}" />(<c:out value="${result.USR_ID}" />)</option>
		</c:forEach>
	</c:if>
				</select>
			</td>
	<fmt:parseDate value="${mbrInfo.REG_DT}" var="viewRegDt" pattern="yyyyMMdd" />
	<fmt:parseDate value="${mbrInfo.UPD_DT}" var="viewUpdDt" pattern="yyyy-MM-dd HH:mm:ss.s" />
			<th><span class="required">*</span> 최초등록일자</th>
			<td>
				<div class="dat-pk">
					<i class="el-input__icon el-icon-date"></i>
					<input name="regDt" value="<fmt:formatDate value="${viewRegDt}" pattern="yyyy-MM-dd" />" type="text" class="el-input__inner datepicker" placeholder="날짜" style="width: 130px;">
				</div>
			</td>
			<th>최종수정일시</th>
			<td><input name="updDt" value="<fmt:formatDate value="${viewUpdDt}" pattern="yyyy-MM-dd HH:mm:ss" />" type="text" class="el-input__inner" readonly style="width:160px"></td>
			<th>최종수정자</th>
			<td><input name="updNm" value="<c:out value="${mbrInfo.UPD_NM}" />" type="text" class="el-input__inner" readonly style="width:120px"></td>
		</tr>
		</tbody>
	</table>
</div>
<!-- // 사례관리자 ~ 최종수정자 -->
</c:if>
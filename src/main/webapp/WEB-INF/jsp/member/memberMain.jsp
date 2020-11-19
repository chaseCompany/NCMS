<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javaScript" language="javascript" defer="defer">
	$(document).ready(function(){
		<%-- 회원 조회 --%>
		mstMbrSearchPopup = function(){
			$.ajax({
				url : '/ajaxMstMbrList.do',
				type : 'POST',
				data : {
					pageNo : $("input[name='memPageNo']").val(),
					mbrNm : $("input[name='memSchMbrNm']").val(),
					telNo : $("input[name='memSchTelNo']").val()
				},
				success : function(res){
					$("div[id='layerpopup']").html(res);
					$("div[id='layerpopup']").attr("data-popup", "memberPopUp");
					$("input[name='reFunName']").val("getMemInfo");
					layerPopupOpen('memberPopUp');
				},
				error : function(xhr, status){
					console.log(xhr);
				}
			});
		},
		<%-- 정보 저장 --%>
		infoSave = function(){
			$.ajax({
				url : '/addMstMbr.do',
				type : 'POST',
				data : $("form#mbrInfoForm").serialize(),
				success : function(res){
					console.log(res);
				},
				error : function(xhr, status){
					console.log(xhr);
				}
			});
		},
		<%-- 회원 정보 삭제 --%>
		delMemInfo = function(){
		},
		<%-- 퇴록 처리 --%>
		changStsMem = function(){
		}
		<%-- 회원 정보 페이지 로딩 --%>
		getMemInfo = function(obj){
			$("input[name='mbrNo']").val(obj.MBR_NO);
			document.mbrInfoForm.submit();
		},
		<%-- 신규작성 --%>
		newMemInfo = function(){
			$("input[name='mbrNo']").val("");
			document.mbrInfoForm.submit();
		},
		<%-- 버튼 활성화 --%>
		setButton = function(flag){
			if(flag == 'L'){
				$("button#delButNo").hide();
				$("button#stsButNo").hide();
				$("button#delButNoYes").show();
				$("button#stsButYes").show();
			}else{
				$("button#delButNo").show();
				$("button#stsButNo").show();
				$("button#delButNoYes").hide();
				$("button#stsButYes").hide();
			}
		}
<c:if test="${mbrInfo ne null and mbrInfo ne ''}">
		setButton('L');
</c:if>
<c:if test="${mbrInfo eq null or mbrInfo eq ''}">
		setButton('');
</c:if>
	});
</script>
<!-- 페이지 타이틀 -->
<div class="tit-area">
	<h1><i class="el-icon-s-order" style="color: rgb(0, 108, 185);"></i> 회원정보 관리</h1>
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
	<button type="button" id="stsButYes" onclick="javaScript:changStsMem();" class="el-button normal el-button--danger el-button--small is-plain" style="margin-left: 8px;display:none;">
		<i class="el-icon-s-claim"></i><span>퇴록 처리</span>
	</button>
</div>
<!-- // 상단 버튼 -->
<div class="formline" style="min-width: 1600px;">
	<form name="mbrInfoForm" id="mbrInfoForm" method="post">
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
									<button type="button" onclick="javaScript:mstMbrSearchPopup();"><i class="el-icon-search"></i></button>
								</div>
							</td>
							<th><span class="required">*</span> 성명</th>
							<td><input name="mbrNm" value="<c:out value="${mbrInfo.MBR_NM}" />" type="text" class="el-input__inner" placeholder="성명" style="width: 130px;" /></td>
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
								<input name="juminNo1" value="<c:out value="${mbrInfo.JUMIN_NO1}" />" type="text" class="el-input__inner" style="width: 90px;" placeholder="6자리">
								<button type="button" class="el-button el-button--primary el-button--small is-plain" style="padding: 9px 10px 8px;">
									<i class="el-icon-d-arrow-right"></i>
								</button>
							</td>
							<th><span class="required">*</span> 연령</th>
							<td><input name="age" value="<c:out value="${mbrInfo.AGE}" />" type="text" class="el-input__inner" style="width: 65px;" placeholder="연령"></td>
							<th><span class="required">*</span> 연락처</th>
							<td colspan="2">
								<div class="dsp-ibk"><input name="telNo1" value="<c:out value="${mbrInfo.TEL_NO1}" />" type="text" maxlength="4" class="el-input__inner" style="width:54px"></div>
								<span>-</span>
								<div class="dsp-ibk"><input name="telNo2" value="<c:out value="${mbrInfo.TEL_NO2}" />" type="text" maxlength="4" class="el-input__inner" style="width:54px"></div>
								<span>-</span>
								<div class="dsp-ibk"><input name="telNo3" value="<c:out value="${mbrInfo.TEL_NO3}" />" type="text" maxlength="4" class="el-input__inner" style="width:53px"></div>
							</td>
						</tr>
						<tr>
							<th class="v-top"><span class="required">*</span> 주소</th>
							<td colspan="7">
								<input type="text" name="zipCd" value="<c:out value="${mbrInfo.ZIP_CD}" />" class="el-input__inner" readonly style="width: 65px;">
								<button type="button" class="el-button el-button--primary el-button--small is-plain" style="padding: 9px 12px 8px;">
									<i class="el-icon-search"></i>
								</button>
								<input name="addr1" value="<c:out value="${mbrInfo.ADDR1}" />" type="text" class="el-input__inner" readonly style="width: 525px;">
								<div style="margin-top:5px">
									<input name="addr2" value="<c:out value="${mbrInfo.ADDR2}" />" type="text" class="el-input__inner" style="width: 634px;">
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
								<select name="mbrTpCd" style="width: 150px;">
									<option value="">선택</option>
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
								<select name="medicCareCd" style="width: 150px;">
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
								<select name="eduCd" style="width: 270px;">
									<option value="">선택</option>
<c:if test="${eduCdList ne null and eduCdList ne ''}">
	<c:forEach var="result" items="${eduCdList}" varStatus="status">
									<option value="<c:out value="${result.CD_ID}" />"<c:if test="${mbrInfo.EDU_CD eq result.CD_ID}"> selected</c:if>><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
								</select>
							</td>
						</tr>
						<tr>
							<th> 결혼여부</th>
							<td>
								<select name="mrgCd" style="width: 150px;">
									<option value="">선택</option>
<c:if test="${mrgCdList ne null and mrgCdList ne ''}">
	<c:forEach var="result" items="${mrgCdList}" varStatus="status">
									<option value="<c:out value="${result.CD_ID}" />"<c:if test="${mbrInfo.MRG_CD eq result.CD_ID}"> selected</c:if>><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
								</select>
							</td>
							<th>의뢰경로</th>
							<td colspan="5">
								<select name="reqPathCd" style="width: 270px;">
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
								<select name="rlgnCd" style="width: 150px;">
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
								<select name="jobCd" style="width: 270px;">
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
							<th>기타장애</th>
							<td><input name="disEtc" value="<c:out value="${mbrInfo.DIS_ETC}" />" type="text" class="el-input__inner" style="width: 576px;"></td>
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
					<table class="wr-form sig-form">
						<colgroup>
							<col style="width: 40px;">
							<col>
						</colgroup>
						<tbody>
						<tr>
							<th>가족</th>
							<td><textarea name="fmlyRmk" style="height: 115px;"><c:out value="${mbrInfo.FMLY_RMK}" /></textarea></td>
						</tr>
						<tr>
							<th>메모</th>
							<td><textarea name="rmk" style="height: 228px;"><c:out value="${mbrInfo.RMK}" /></textarea></td>
						</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!-- // 기본정보 , 보호자 정보 -->
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
	</form>
	<!-- // 사례관리자 ~ 최종수정자 -->
	<!-- 등/퇴록 내역 -->
	<div class="wh-box">
		<div class="tt-bx"><i class="el-icon-s-order"></i> 등/퇴록 내역</div>
		<div class="table-box mgt10">
			<div class="el-table_header-wrapper">
				<table>
					<colgroup>
						<col style="width:46px">
						<col style="width:100px">
						<col style="width:130px">
						<col>
						<col style="width: 170px;">
					</colgroup>
					<thead>
					<tr>
						<th>#</th>
						<th>구분</th>
						<th>등/퇴록일자</th>
						<th>상세내용</th>
						<th>상세</th>
					</tr>
					</thead>
				</table>
			</div>
			<div class="el-table_body-wrapper" style="height: 180px;">
<c:if test="${mstRegHisList ne null and mstRegHisList ne ''}">
				<table>
					<colgroup>
						<col style="width:46px">
						<col style="width:100px">
						<col style="width:130px">
						<col>
						<col style="width: 170px;">
					</colgroup>
					<tbody>
	<c:forEach var="result" items="${mstRegHisList}" varStatus="status">
		<fmt:parseDate value="${result.CRE_DT}" var="mstCreDt" pattern="yyyy-MM-dd HH:mm:ss.s" />
					<tr>
						<td><div class="cell"><c:out value="${status.count}" /></div></td>
						<td><div class="cell"><c:out value="${result.REG_RLS_NM}" /></div></td>
						<td><div class="cell"><i class="el-icon-time"></i><fmt:formatDate value="${mstCreDt}" pattern="yyyy-MM-dd" /></div></td>
						<td class="txt-left"><div class="cell"><c:out value="${result.DTL_CTNT}" /></div></td>
						<td>
							<div class="cell">
								<button type="button" onclick="layerPopupOpen('RegRlsPopUp');" class="el-button el-button--warning el-button--mini is-plain" style="margin-left: 1px; padding: 4px 9px;">
									<span>보기</span>
								</button>
							</div>
						</td>
					</tr>
	</c:forEach>
					</tbody>
				</table>
</c:if>
<c:if test="${mstRegHisList eq null or mstRegHisList eq ''}">
				<div class="no-data">조회된 데이터가 없습니다.</div>
</c:if>
			</div>
		</div>
	</div>
	<!-- // 등/퇴록 내역 -->
</div>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kr.co.chase.ncms.common.ConstantObject" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javaScript" language="javascript" defer="defer">
	$(document).ready(function(){
		$("input[name='stpDt']").datepicker('setDate', 'today');

		<%-- 회원 정보 삭제 --%>
		delMemInfo = function(){
			if(confirm("정말로 삭제하시겠습니까?\n삭제한 데이터는 복구가 불가합니다.")){
				$.ajax({
					url : '/ajaxMstMbrDel.do',
					type : 'POST',
					data : {
						mbrNo : $("input[name='mbrNo']").val()
					},
					success : function(res){
						if(res.err != "Y"){
							alert("삭제 성공");

							getMemInfo();
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
		<%-- 퇴록 처리 팝업 --%>
		openStsPage = function(type, sts){
			if(type == "V"){
				$("button#stsSubButYes").hide();
				$("button#stsSubButNo").show();

				$("textarea[name='dtlCtnt']").val(($("textarea[name='viewDtlCtnt_" + sts + "']").val()));
				$("select[name='instCd']").val($("div#viewInst_" + sts).attr("cd")).prop("selected", true);
				$("input[name='stpDt']").val($("div#viewDtlCtnt_" + sts).text());
				$("select[name='typeCd']").val($("div#viewType_" + sts).attr("cd")).prop("selected", true);
			}else{
				$("button#stsSubButNo").hide();
				$("button#stsSubButYes").show();

				$("input[name='stsCd']").val(sts);
				$("textarea[name='dtlCtnt']").val("");
				$("select[name='instCd']").val("").prop("selected", true);
				$("input[name='stpDt']").datepicker('setDate', 'today');
				$("select[name='typeCd']").val("").prop("selected", true);
			}

			layerPopupOpen('RegRlsPopUp');
		}
		<%-- 퇴록 처리 --%>
		changStsMem = function(){
			$("input[name='stsMbrNo']").val($("input[name='mbrNo']").val());

			if($("input[name='stsMbrNo']").val() == ""){
				alert("회원은 필수 선택 입니다.");				return;
			}
			if($("select[name='instCd']").val() == ""){
				alert("기관명은 필수 선택 입니다.");
				$("select[name='instCd']").focus();			return;
			}
			if($("input[name='stpDt']").val() == ""){
				alert("일자는 필수 입력 입니다.");
				$("input[name='stpDt']").focus();			return;
			}

			$.ajax({
				url : '/ajaxMstMbrStsCdUpdate.do',
				type : 'POST',
				data : $("form#stsChangForm").serialize(),
				success : function(res){
					if(res.err != "Y"){
						alert(res.MSG + " 성공");

						getMemInfo($("input[name='mbrNo']").val());
					}else{
						alert(res.MSG);
					}

					layerPopupClose('RegRlsPopUp');
				},
				error : function(xhr, status){
					console.log(xhr);
				}
			});
		}
		<%-- 회원 정보 페이지 로딩 --%>
		getMemInfo = function(tagMbrNo){
			$.ajax({
				url : '/ajaxMbrInfo.do',
				type : 'POST',
				data : {
					mbrNo : tagMbrNo
				},
				success : function(res){
					$("div#memInfoView").html(res);

					 $("input[name='regDt']").datepicker();
				},
				error : function(xhr, status){
					console.log(xhr);
				}
			});
		}
		<%-- 등/퇴록 내역 조회 --%>
		getMbrRegHistList = function(tagMbrNo){
			$.ajax({
				url : '/ajaxMbrRegHistList.do',
				type : 'POST',
				data : {
					mbrNo : tagMbrNo
				},
				success : function(res){
					$("div#memRegHistView").html(res);
				},
				error : function(xhr, status){
					console.log(xhr);
				}
			});
		}
		<%-- 신규작성 --%>
		newMemInfo = function(){
			getMemInfo();
		}
		<%-- 버튼 활성화 --%>
		setButton = function(flag){
			$("button#stsRlButYes").hide();
			$("button#stsRrButYes").hide();
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

		getMemInfo();
		getMbrRegHistList();
	});
</script>
<!-- 페이지 타이틀 -->
<div class="tit-area">
	<h1><i class="el-icon-s-order" style="color: rgb(0, 108, 185);"></i> 회원정보관리</h1>
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
</div>
<!-- // 상단 버튼 -->
<div class="formline" style="min-width: 1600px;">
	<!-- 탭메뉴 -->
	<div class="el-tabs__header is-top">
		<div class="el-tabs__nav-wrap is-top">
			<div class="el-tabs__nav-scroll">
				<div role="tablist" class="el-tabs__nav is-top">
					<div class="el-tabs__item is-top is-active" data-id="tab-mem">
						<span><i class="el-icon-s-help"></i> <a href="/memberMain.do">회원정보관리</a></span>
					</div>
					<div class="el-tabs__item is-top" data-id="tab-link">
						<span><i class="el-icon-s-management"></i> <a href="/memberLink.do">연계</a></span>
					</div>
					<div class="el-tabs__item is-top" data-id="tab-req">
						<span><i class="el-icon-platform-eleme"></i> <a href="/memberReq.do">의뢰</a></span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- // 탭메뉴 -->
	<form name="mbrInfoForm" id="mbrInfoForm" method="post" enctype="multipart/form-data">
	<div id="memInfoView"></div>
	</form>
	<div id="memRegHistView"></div>
</div>
<!-- 등록 처리 팝업 -->
<form name="stsChangForm" id="stsChangForm">
<input type="hidden" name="stsCd" value="" />
<input type="hidden" name="stsMbrNo" value="" />
<div class="layerpopup" data-popup="RegRlsPopUp">
	<div class="popup RegRlsPopUp">
		<div class="pop-header">
			<span>등록 처리</span>
			<button type="button" class="el-dialog__headerbtn" onclick="layerPopupClose('RegRlsPopUp');">
				<i class="el-dialog__close el-icon el-icon-close"></i>
			</button>
		</div>
		<div class="pop-content">
			<div class="section">
				<table class="wr-form sig-form">
					<colgroup>
						<col style="width:100px">
						<col style="width:180px">
						<col style="width:100px">
						<col style="width:150px">
						<col style="width:80px">
						<col>
					</colgroup>
					<tbody>
					<tr>
						<th><span class="required">*</span> 기관명</th>
						<td>
							<select name="instCd">
								<option value="">선택</option>
<c:if test="${spotList ne null and spotList ne ''}">
	<c:forEach var="result" items="${spotList}" varStatus="status">
								<option value="<c:out value="${result.CD_ID}" />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
								<option value="ZZ">기타</option>
							</select>
						</td>
						<th><span class="required">*</span> 등퇴록일자</th>
						<td>
							<div class="dat-pk">
								<i class="el-input__icon el-icon-date"></i>
								<input type="text" name="stpDt" class="el-input__inner datepicker" placeholder="날짜" style="width: 130px;">
							</div>
						</td>
						<th>형태</th>
						<td>
							<select name="typeCd">
								<option value="">선택</option>
<c:if test="${typeList ne null and typeList ne ''}">
	<c:forEach var="result" items="${typeList}" varStatus="status">
								<option value="<c:out value="${result.CD_ID}" />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="6"><textarea name="dtlCtnt" style="height:178px;" placeholder="상세내용을 입력하세요."></textarea></td>
					</tr>
					</tbody>
				</table>
			</div>
			<!-- 닫기 -->
			<div class="el-dialog__footer">
				<button id="stsSubButYes" onclick="javaScript:changStsMem();" type="button" class="el-button el-button--primary el-button--small is-plain" style="display:none;">
					<i class="el-icon-check"></i><span>저장</span>
				</button>
				<button id="stsSubButNo" type="button" class="el-button el-button--primary el-button--small is-disabled is-plain" disabled="disabled" style="display:none;">
					<i class="el-icon-check"></i><span>저장</span>
				</button>
				<button type="button" onclick="layerPopupClose('RegRlsPopUp');" class="el-button el-button--default el-button--small">
					<i class="el-icon-close"></i><span>닫기</span>
				</button>
			</div>
			<!-- // 닫기 -->
		</div>
	</div>
</div>
</form>
<!-- // 등록 처리 팝업 -->
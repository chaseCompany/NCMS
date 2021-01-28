<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="kr.co.chase.ncms.common.ConstantObject" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String loginUserNm = StringUtils.defaultIfEmpty((String)request.getAttribute("LoginUserNm"), "");
%>
<c:set var="loginUserNm" value="<%=loginUserNm%>" />
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javaScript" language="javascript" defer="defer">
	$(document).ready(function(){
		$("input[name='transDt']").datepicker('setDate', 'today');
		<%-- 회원 정보 페이지 로딩 --%>
		getMemInfo = function(tagMbrNo){
			$.ajax({
				url : '/ajaxMbrInfo.do',
				type : 'POST',
				data : {
					mbrNo : tagMbrNo,
					viewType : "R",
					searchFlag : "N"
				},
				success : function(res){
					$("div#memInfoView").html(res);
				},
				error : function(xhr, status){
					console.log(xhr);
				}
			});
		}
		<%-- 연계목록 조회 --%>
		getLinkList = function(){
			$.ajax({
				url : '/getTransList.do',
				type : 'POST',
				data : {
					pageNo : $("input[name='transPageNo']").val(),
					listType : "L"
				},
				success : function(res){
					$("div#transList").html(res);
				},
				error : function(xhr, status){
					console.log(xhr);
				}
			});
		}
		<%-- 연계여부 저장 --%>
		transSave = function(type){
			if($("input[name='mbrNo']").val() == ""){
				alert("회원을 선택 하세요.");			return;
			}
			if($("input[name='transNo']").val() == ""){
				alert("처리대상을 선택 하세요.");			return;
			}

			$("input[name='linkStateCd']").val(type);

			$.ajax({
				url : '/ajaxTransResult.do',
				type : 'POST',
				processData : false,
				contentType : false,
				enctype : 'multipart/form-data',
				data : new FormData($("#transForm")[0]),
				success : function(res){
					if(res.err != "Y"){
						alert("성공");
						getLinkList();

						transView($("input[name='transNo']").val());
					}else{
						alert(res.MSG);
					}
				},
				error : function(xhr, status){
					console.log(xhr);
				}
			});
		}
		<%-- 수립 문항 수정 --%>
		changEvlItemSco = function(obj){
			var tagName = $(obj).attr("name");
			var rating = $("select[name='" + tagName + "'] option:selected").attr("rating");

			if(rating == 4){
				$("th#" + tagName).attr("class", "bg-pr");
			}else if(rating >= 2 && rating <= 3){
				$("th#" + tagName).attr("class", "bg-og");
			}else{
				$("th#" + tagName).attr("class", "bg-pk");
			}
		}

		getMemInfo();
		getLinkList();
	});
</script>
<!-- 페이지 타이틀 -->
<div class="tit-area">
	<h1><i class="el-icon-s-order" style="color: rgb(0, 108, 185);"></i> 회원정보관리</h1>
</div>
<!-- // 페이지 타이틀 -->
<!-- 상단 버튼 -->
<div class="top-right-btn">
	<button type="button" onclick="javaScript:transSave('2');" class="el-button normal el-button--primary el-button--small is-plain">
		<i class="el-icon-check"></i><span>접수</span>
	</button>
	<button type="button" onclick="javaScript:transSave('3');" class="el-button normal el-button--primary el-button--small is-plain">
		<i class="el-icon-check"></i><span>반려</span>
	</button>
</div>
<!-- // 상단 버튼 -->
<div class="formline" style="min-width: 1600px;">
	<!-- 탭메뉴 -->
	<div class="el-tabs__header is-top">
		<div class="el-tabs__nav-wrap is-top">
			<div class="el-tabs__nav-scroll">
				<div role="tablist" class="el-tabs__nav is-top">
					<div class="el-tabs__item is-top" data-id="tab-mem">
						<span><i class="el-icon-s-help"></i> <a href="/memberMain.do">회원정보관리</a></span>
					</div>
					<div class="el-tabs__item is-top is-active" data-id="tab-link">
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
	<div class="tab-tb-box">
		<div class="table-box">
			<div class="el-table_header-wrapper">
				<table>
					<colgroup>
						<col style="width:46px">
						<col style="width:150px">
						<col style="width:100px">
						<col style="width:100px">
						<col style="width:120px">
						<col style="width:150px">
						<col style="width:150px">
						<col>
					</colgroup>
					<thead>
					<tr>
						<th>#</th>
						<th>회원등록번호</th>
						<th>성명</th>
						<th>성별</th>
						<th>연락처</th>
						<th>사례관리자</th>
						<th>최초등록기관</th>
						<th>작업</th>
					</tr>
					</thead>
				</table>
			</div>
			<div class="el-table_body-wrapper" style="height: 148px;" id="transList">
				<div class="no-data">조회된 데이터가 없습니다.</div>
			</div>
		</div>
	</div>
	<form name="transForm" id="transForm" method="post" enctype="multipart/form-data">
	<input type="hidden" name="transPageNo" value="1" />
	<input type="hidden" name="transNo" value="" />
	<input type="hidden" name="linkStateCd" value="" />
	<div class="section">
		<table class="w-auto">
			<tbody>
			<tr>
				<th>연계기관</th>
				<td>
					<select name="receiptInstCd" style="width:130px">
						<option value="">선택</option>
<c:if test="${spotList ne null and spotList ne ''}">
	<c:forEach var="result" items="${spotList}" varStatus="status">
						<option value="<c:out value="${result.CD_ID}" />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
					</select>
				</td>
				<th>연계일자</th>
				<td>
					<div class="dat-pk">
						<i class="el-input__icon el-icon-date"></i>
						<input name="transDt" type="text" class="el-input__inner datepicker" placeholder="연계일자" style="width: 130px;">
					</div>
				</td>
				<th>연계유형</th>
				<td>
					<select style="width:130px">
						<option value="">선택</option>
					</select>
				</td>
				<th>요청서비스</th>
				<td>
<c:if test="${reqServiceList ne null and reqServiceList ne ''}">
	<c:set var="reqServiceCd" value="1" />
	<c:forEach var="result" items="${reqServiceList}" varStatus="status">
					<span class="ck-bx">
						<input type="radio" class="el-radio__original" name="reqServiceCd" value="<c:out value="${result.CD_ID}" />" id="reqServiceCd-<c:out value="${status.count}" />"<c:if test="${reqServiceCd eq result.CD_ID}"> checked</c:if>>
						<label for="reqServiceCd-<c:out value="${status.count}" />">
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
	<!-- 회원정보 -->
	<div id="memInfoView"></div>
	<!-- // 회원정보 -->
	<div class="multi-table">
		<table class="wr-table" id="mngTpTable">
			<colgroup>
				<col style="width: 8%;">
				<col style="width: 4%;">
				<col style="width: 13%;">
				<col style="width: 8%;">
				<col style="width: 4%;">
				<col style="width: 13%;">
				<col style="width: 8%;">
				<col style="width: 4%;">
				<col style="width: 13%;">
			</colgroup>
			<thead>
			<tr>
				<th colspan="3">중독영역 (급성중독과 금단)</th>
				<th colspan="3">정신 및 신체영역</th>
				<th colspan="3">위험성 영역</th>
			</tr>
			<tr>
				<th>문항</th>
				<th>심각도</th>
				<th>자원연계</th>
				<th>문항</th>
				<th>심각도</th>
				<th>자원연계</th>
				<th>문항</th>
				<th>심각도</th>
				<th>자원연계</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<th id="evlItmSco01" class="bg-pk">약물중독</th>
				<td>
					<select name="evlItmSco01" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco01List ne null and evlItmSco01List ne ''}">
	<c:forEach var="result" items="${evlItmSco01List}" varStatus="status">
						<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
					</select>
				</td>
				<td><input type="text" name="evlItmLnk01" class="el-input__inner" style="width: 100%;" /></td>
				<th id="evlItmSco06" class="bg-pk">정신과적 증상</th>
				<td>
					<select name="evlItmSco06" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco06List ne null and evlItmSco06List ne ''}">
	<c:forEach var="result" items="${evlItmSco06List}" varStatus="status">
						<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
					</select>
				</td>
				<td><input type="text" name="evlItmLnk06" class="el-input__inner" style="width: 100%;" /></td>
				<th id="evlItmSco10" class="bg-pk">자해/자살위험</th>
				<td>
					<select name="evlItmSco10" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco18List ne null and evlItmSco18List ne ''}">
	<c:forEach var="result" items="${evlItmSco18List}" varStatus="status">
						<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
					</select>
				</td>
				<td><input type="text" name="evlItmLnk10" class="el-input__inner" style="width: 100%;" /></td>
			</tr>
			<tr>
				<th id="evlItmSco02" class="bg-pk">알코올중독</th>
				<td>
					<select name="evlItmSco02" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco01List ne null and evlItmSco01List ne ''}">
	<c:forEach var="result" items="${evlItmSco01List}" varStatus="status">
						<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
					</select>
				</td>
				<td><input type="text" name="evlItmLnk02" class="el-input__inner" style="width: 100%;" /></td>
				<th id="evlItmSco07" class="bg-pk">정신약물관리</th>
				<td>
					<select name="evlItmSco07" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco07List ne null and evlItmSco07List ne ''}">
	<c:forEach var="result" items="${evlItmSco07List}" varStatus="status">
						<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
					</select>
				</td>
				<td><input type="text" name="evlItmLnk07" class="el-input__inner" style="width: 100%;" /></td>
				<th id="evlItmSco11" class="bg-pk">타해위험</th>
				<td>
					<select name="evlItmSco11" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco18List ne null and evlItmSco18List ne ''}">
	<c:forEach var="result" items="${evlItmSco18List}" varStatus="status">
						<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
					</select>
				</td>
				<td><input type="text" name="evlItmLnk11" class="el-input__inner" style="width: 100%;" /></td>
			</tr>
			<tr>
				<th id="evlItmSco03" class="bg-pk">도박중독</th>
				<td>
					<select name="evlItmSco03" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco01List ne null and evlItmSco01List ne ''}">
	<c:forEach var="result" items="${evlItmSco01List}" varStatus="status">
						<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
					</select>
				</td>
				<td><input type="text" name="evlItmLnk03" class="el-input__inner" style="width: 100%;" /></td>
				<th id="evlItmSco08" class="bg-pk">스트레스 상태</th>
				<td>
					<select name="evlItmSco08" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco08List ne null and evlItmSco08List ne ''}">
	<c:forEach var="result" items="${evlItmSco08List}" varStatus="status">
						<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
					</select>
				</td>
				<td><input type="text" name="evlItmLnk08" class="el-input__inner" style="width: 100%;" /></td>
				<td colspan="3"></td>
			</tr>
			<tr>
				<th id="evlItmSco04" class="bg-pk">인터넷중독</th>
				<td>
					<select name="evlItmSco04" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco01List ne null and evlItmSco01List ne ''}">
	<c:forEach var="result" items="${evlItmSco01List}" varStatus="status">
						<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
					</select>
				</td>
				<td><input type="text" name="evlItmLnk04" class="el-input__inner" style="width: 100%;" /></td>
				<th id="evlItmSco09" class="bg-pk">신체질환</th>
				<td>
					<select name="evlItmSco09" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco09List ne null and evlItmSco09List ne ''}">
	<c:forEach var="result" items="${evlItmSco09List}" varStatus="status">
						<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
					</select>
				</td>
				<td><input type="text" name="evlItmLnk09" class="el-input__inner" style="width: 100%;" /></td>
				<td colspan="3"></td>
			</tr>
			<tr>
				<th id="evlItmSco05" class="bg-pk">기타중독</th>
				<td>
					<select name="evlItmSco05" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco01List ne null and evlItmSco01List ne ''}">
	<c:forEach var="result" items="${evlItmSco01List}" varStatus="status">
						<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
					</select>
				</td>
				<td><input type="text" name="evlItmLnk05" class="el-input__inner" style="width: 100%;" /></td>
				<td colspan="3"></td>
				<td colspan="3"></td>
			</tr>
			</tbody>
		</table>
		<table class="wr-table" id="mngTpTable">
			<colgroup>
				<col style="width: 8%;">
				<col style="width: 4%;">
				<col style="width: 13%;">
				<col style="width: 8%;">
				<col style="width: 4%;">
				<col style="width: 13%;">
				<col style="width: 8%;">
				<col style="width: 4%;">
				<col style="width: 13%;">
			</colgroup>
			<thead>
			<tr>
				<th colspan="3">사회적 관계영역</th>
				<th colspan="3">사회서비스 평가영역</th>
				<th colspan="3">기타영역</th>
			</tr>
			<tr>
				<th>문항</th>
				<th>심각도</th>
				<th>자원연계</th>
				<th>문항</th>
				<th>심각도</th>
				<th>자원연계</th>
				<th>문항</th>
				<th>심각도</th>
				<th>자원연계</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<th id="evlItmSco12" class="bg-pk">가족관계</th>
				<td>
					<select name="evlItmSco12" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco10List ne null and evlItmSco10List ne ''}">
	<c:forEach var="result" items="${evlItmSco10List}" varStatus="status">
						<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
					</select>
				</td>
				<td><input type="text" name="evlItmLnk12" class="el-input__inner" style="width: 100%;" /></td>
				<th id="evlItmSco15" class="bg-pk">주거</th>
				<td>
					<select name="evlItmSco15" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco12List ne null and evlItmSco12List ne ''}">
	<c:forEach var="result" items="${evlItmSco12List}" varStatus="status">
						<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
					</select>
				</td>
				<td><input type="text" name="evlItmLnk15" class="el-input__inner" style="width: 100%;" /></td>
				<th id="evlItmSco20" class="bg-pk">영성</th>
				<td>
					<select name="evlItmSco20" style="width: 100%;">
<c:if test="${evlItmSco14List ne null and evlItmSco14List ne ''}">
	<c:forEach var="result" items="${evlItmSco14List}" varStatus="status">
						<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
					</select>
				</td>
				<td><input type="text" name="evlItmLnk20" class="el-input__inner" style="width: 100%;" /></td>
			</tr>
			<tr>
				<th id="evlItmSco13" class="bg-pk">사회적관계</th>
				<td>
					<select name="evlItmSco13" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco11List ne null and evlItmSco11List ne ''}">
	<c:forEach var="result" items="${evlItmSco11List}" varStatus="status">
						<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
					</select>
				</td>
				<td><input type="text" name="evlItmLnk13" class="el-input__inner" style="width: 100%;" /></td>
				<th id="evlItmSco16" class="bg-pk">경제활동 지원</th>
				<td>
					<select name="evlItmSco16" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco13List ne null and evlItmSco13List ne ''}">
	<c:forEach var="result" items="${evlItmSco13List}" varStatus="status">
						<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
					</select>
				</td>
				<td><input type="text" name="evlItmLnk16" class="el-input__inner" style="width: 100%;" /></td>
				<th id="evlItmSco21" class="bg-pk">봉사</th>
				<td>
					<select name="evlItmSco21" style="width: 100%;">
<c:if test="${evlItmSco14List ne null and evlItmSco14List ne ''}">
	<c:forEach var="result" items="${evlItmSco14List}" varStatus="status">
						<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
					</select>
				</td>
				<td><input type="text" name="evlItmLnk21" class="el-input__inner" style="width: 100%;" /></td>
			</tr>
			<tr>
				<th id="evlItmSco14" class="bg-pk">회복환경 관계</th>
				<td>
					<select name="evlItmSco14" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco11List ne null and evlItmSco11List ne ''}">
	<c:forEach var="result" items="${evlItmSco11List}" varStatus="status">
						<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
					</select>
				</td>
				<td><input type="text" name="evlItmLnk14" class="el-input__inner" style="width: 100%;" /></td>
				<th id="evlItmSco17" class="bg-pk">고용 및 교육가능성</th>
				<td>
					<select name="evlItmSco17" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco12List ne null and evlItmSco12List ne ''}">
	<c:forEach var="result" items="${evlItmSco12List}" varStatus="status">
						<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
					</select>
				</td>
				<td><input type="text" name="evlItmLnk17" class="el-input__inner" style="width: 100%;" /></td>
				<th id="evlItmSco22" class="bg-pk">여가</th>
				<td>
					<select name="evlItmSco22" style="width: 100%;">
<c:if test="${evlItmSco14List ne null and evlItmSco14List ne ''}">
	<c:forEach var="result" items="${evlItmSco14List}" varStatus="status">
						<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
					</select>
				</td>
				<td><input type="text" name="evlItmLnk22" class="el-input__inner" style="width: 100%;" /></td>
			</tr>
			<tr>
				<td colspan="3"></td>
				<th id="evlItmSco18" class="bg-pk">법률적 문제</th>
				<td>
					<select name="evlItmSco18" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco12List ne null and evlItmSco12List ne ''}">
	<c:forEach var="result" items="${evlItmSco12List}" varStatus="status">
						<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
					</select>
				</td>
				<td><input type="text" name="evlItmLnk18" class="el-input__inner" style="width: 100%;" /></td>
				<th id="evlItmSco23" class="bg-pk">미래계획</th>
				<td>
					<select name="evlItmSco23" style="width: 100%;">
<c:if test="${evlItmSco14List ne null and evlItmSco14List ne ''}">
	<c:forEach var="result" items="${evlItmSco14List}" varStatus="status">
						<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
					</select>
				</td>
				<td><input type="text" name="evlItmLnk23" class="el-input__inner" style="width: 100%;" /></td>
			</tr>
			<tr>
				<td colspan="3"></td>
				<th id="evlItmSco19" class="bg-pk">취업 및 학업욕구</th>
				<td>
					<select name="evlItmSco19" style="width: 100%;">
<c:if test="${evlItmSco14List ne null and evlItmSco14List ne ''}">
	<c:forEach var="result" items="${evlItmSco14List}" varStatus="status">
						<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
					</select>
				</td>
				<td><input type="text" name="evlItmLnk19" class="el-input__inner" style="width: 100%;" /></td>
				<th id="evlItmSco24" class="bg-pk">기타</th>
				<td>
					<select name="evlItmSco24" style="width: 100%;">
<c:if test="${evlItmSco14List ne null and evlItmSco14List ne ''}">
	<c:forEach var="result" items="${evlItmSco14List}" varStatus="status">
						<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
					</select>
				</td>
				<td><input type="text" name="evlItmLnk24" class="el-input__inner" style="width: 100%;" /></td>
			</tr>
			</tbody>
		</table>
	</div>
	<div class="section pdn">
		<div class="el-card_header">
			<h2><i class="el-icon-s-opportunity"></i> 연계사유</h2>
		</div>
		<div class="el-card_body"><textarea name="transCtnt" style="height: 120px;" placeholder="연계사유"></textarea></div>
		<div class="el-card_body">
			<div id="transfileName"></div>
			<input type="hidden" name="transfileNameFlag" value="N" />
			<input type="file" name="fileId" />
		</div>
	</div>
	</form>
</div>
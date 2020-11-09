<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javaScript" language="javascript" defer="defer">
	$(document).ready(function(){
		<%-- 초기화 --%>
		individualNew = function(){
			window.location.reload();
		},
		individualExel = function(){
			console.log("엑셀다운로드");
		},
		<%-- 회원조회 --%>
		searchMemberPopup = function(){
			$.ajax({
				url : '/ajaxMstMbrList.do',
				type : 'POST',
				data : {},
				success : function(res){
					if(res.totalCount > 0){
						var mbrInfo = res.resultList[0];
						console.log(mbrInfo);

						$("input[name='mbrNo']").val(mbrInfo.MBR_NO);
						$("input[name='mbrNm']").val(mbrInfo.MBR_NM);
						$("input[name='gendNm']").val(mbrInfo.GEND_NM);
						$("input[name='age']").val(mbrInfo.AGE);
						$("input[name='regDt']").val(mbrInfo.REG_DT);
						$("select[name='medicCareCd']").val(mbrInfo.MEDIC_CARE_CD).prop("selected", true);
						$("input[name='mngUsrId']").val(mbrInfo.MNG_USR_NM);

						getCslIdvList(mbrInfo.MBR_NO);
						getCslIspList(mbrInfo.MBR_NO);
						getCslAssList(mbrInfo.MBR_NO);
					}else{
						tagMbrInfo = "";
						console.log("회원 정보 없음");
					}
				},
				error : function(xhr, status){
					tagMbrInfo = "";
					console.log(xhr);
				}
			});
		},
		<%-- 메뉴별 내용 출력 --%>
		bodyView = function(tag){
			$("div#bodyView").each(function(idx){
				if($(this).attr("name") == tag){
					$(this).show();
				}else{
					$(this).hide();
				}
			});
		},
		<%-- 집중상담 이력 조회 --%>
		getCslIdvList = function(tagMbrNo){
			$.ajax({
				url : '/getClsIdvList.do',
				type : 'POST',
				data : {
					mbrNo : tagMbrNo
				},
				success : function(res){
					if(res.err != "Y"){
						if(res.clsIdvList.length > 0){
							$("#idvTableList > tr").remove();

							$(res.clsIdvList).each(function(idx, obj){
								var inHtml = "<tr id=" + idx + ">"
										   + "<td>" + (idx + 1) + "</td>"
										   + "<td><a href='javaScript:viewIdvRow(\"" + obj.CSL_NO + "\");'>" + obj.CSL_NO + "</td>"
										   + "<td>" + obj.CSL_DT + "</td>"
										   + "<td>" + obj.CSL_FM_TM + " ~ " + obj.CSL_TO_TM + "</td>"
										   + "<td>" + obj.CSL_TERM_TM + "</td>"
										   + "<td>" + obj.CSL_SBJ + "</td>"
										   + "<td>" + obj.CSL_TGT + "</td>"
										   + "<td>" + obj.CSL_ID + "</td>"
										   + "<td><a href='javaScript:idvDel(\"" + obj.CSL_NO + "\", \"" + idx + "\");'>삭제</a></td>"
										   + "</tr>";
								$("#idvTableList").append(inHtml);
							});
						}
					}else{
						console.log("ERR")
					}
				},
				error : function(xhr, status){
					
				}
			});
		},
		<%-- 집중상담내용 삭제 --%>
		idvDel = function(tagCslNo, idx){
			console.log("DEL : " + tagCslNo + "," + idx);

			$.ajax({
				url : '/ajaxClsIdvDel.do',
				type : 'POST',
				data : {
					cslNo : tagCslNo
				},
				success : function(res){
					if(res.err != "Y"){
						$("#idvTableList > tr").each(function(){
							if($(this).attr("id") == idx){
								$(this).remove();
							}
						});

						if($("#idvTableList > tr").length <= 0){
							var inHtml = "<tr><td colspan='9'>조회된 데이터가 없습니다.</td></tr>";
							$("#idvTableList").append(inHtml);
						}
					}else{
						console.log(res.MSG);
					}
				},
				error : function(xhr, status){}
			});
		},
		<%-- 집중상담내용 상세 조회 --%>
		viewIdvRow = function(tagCslNo){
			$.ajax({
				url : '/ajaxClsIdvInfo.do',
				type : 'POST',
				data : {
					cslNo : tagCslNo
				},
				success : function(res){
					if(res.err != "Y"){
						$("input[name='cslNo']").val(res.clsIdvInfo.CSL_NO);
						$("input[name='cslNm']").val(res.clsIdvInfo.CSL_NM);
						$("input[name='cslDt']").val(res.clsIdvInfo.CSL_DT);
						$("input[name='cslFmTm']").val(res.clsIdvInfo.CSL_FM_TM);
						$("input[name='cslToTm']").val(res.clsIdvInfo.CSL_TO_TM);
						$("input[name='cslTermTm']").val(res.clsIdvInfo.CSL_TERM_TM);
						$("input[name='cslTgtCd']:radio[value='" + res.clsIdvInfo.CSL_TGT_CD + "']").prop("checked", true);
						$("input[name='cslTpCd']:radio[value='" + res.clsIdvInfo.CSL_TP_CD + "']").prop("checked", true);
						$("input[name='cslSbj']").val(res.clsIdvInfo.CSL_SBJ);
						$("input[name='cslTgt']").val(res.clsIdvInfo.CSL_TGT);
						$("textarea[name='cslCtnt']").val(res.clsIdvInfo.CSL_CTNT);
						$("input[name='rskSco']").val(res.clsIdvInfo.RSK_SCO);
						$("select[name='rskaTpCd']").val(res.clsIdvInfo.RSKA_TP_CD).prop("selected", true);
						$("select[name='rskbTpCd']").val(res.clsIdvInfo.RSKB_TP_CD).prop("selected", true);
						$("select[name='rskcTpCd']").val(res.clsIdvInfo.RSKC_TP_CD).prop("selected", true);
						$("input[name='nxtCslTm']").val(res.clsIdvInfo.NXT_CSL_TM);
						$("textarea[name='nxtCslCtnt']").val(res.clsIdvInfo.NXT_CSL_CTNT);
					}else{
						console.log("ERR")
					}
				},
				error : function(xhr, status){
					
				}
			});
		},
		<%-- 집중상담 신규 --%>
		newIdv = function(){
			$("#idvInfoDiv").load(window.location.href + ' #idvInfoDiv');
		},
		<%-- 집중상담 저장 --%>
		saveIdv = function(){
			if($("input[name='mbrNo']").val() == ""){
				alert("회원을 선택하세요.");
				return;
			}

			$.ajax({
				url : '/ajaxClsIdvAdd.do',
				type : 'POST',
				data : $('#cslForm').serialize(),
				success : function(res){
					console.log(res);
					if(res.err != "Y"){
						
					}else{
						console.log("ERR")
					}
				},
				error : function(xhr, status){
					
				}
			});
		},
		<%-- ISP 수립 이력 조회 --%>
		getCslIspList = function(tagMbrNo){
			$.ajax({
				url : '/ajaxClsIspList.do',
				type : 'POST',
				data : {
					mbrNo : tagMbrNo
				},
				success : function(res){
					if(res.err != "Y"){
						if(res.clsIspList.length > 0){
							$("#ispTableList > tr").remove();

							$(res.clsIspList).each(function(idx, obj){
								var inHtml = "<tr id='" + idx + "'>"
										   + "<td>" + (idx + 1) + "</td>"
										   + "<td><a href='javaScript:viewIspRow(\"" + obj.ISP_DT + "\", \"" + obj.MBR_NO + "\");'>" + obj.ISP_DT + "</td>"
										   + "<td>" + obj.MNG_TP_NM + "</td>"
										   + "<td>" + obj.ISP_RST + "</td>"
										   + "<td>" + obj.CRE_NM + "</td>"
										   + "<td><a href='javaScript:removeIsp(\"" + obj.ISP_DT + "\", \"" + obj.MBR_NO + "\", \"" + idx + "\");'>삭제</a></td>"
										   + "</tr>";
								$("#ispTableList").append(inHtml);
							});
						}
					}else{
						console.log("ERR")
					}
				},
				error : function(xhr, status){}
			});
		},
		<%-- ISP 상세 보기 --%>
		viewIspRow = function(tagIspDt, tagMbrNo){
			$.ajax({
				url : '/ajaxClsIspInfo.do',
				type : 'POST',
				data : {
					mbrNo : tagMbrNo, 
					ispDt : tagIspDt
				},
				success : function(res){
					if(res.ispInfo != null){
						$("input[name='newFlag']").val("N");
						$("input[name='ispDt']").val(res.ispInfo.ISP_DT);
						$("input[name='mngTpNm']").val(res.ispInfo.MNG_TP_NM);
						$("select[name='evlItmSco01']").val(res.ispInfo.EVL_ITM_SCO01).prop("selected", true);
						$("input[name='evlItmLnk01']").val(res.ispInfo.EVL_ITM_LNK01);
						$("select[name='evlItmSco02']").val(res.ispInfo.EVL_ITM_SCO02).prop("selected", true);
						$("input[name='evlItmLnk02']").val(res.ispInfo.EVL_ITM_LNK02);
						$("select[name='evlItmSco03']").val(res.ispInfo.EVL_ITM_SCO03).prop("selected", true);
						$("input[name='evlItmLnk03']").val(res.ispInfo.EVL_ITM_LNK03);
						$("select[name='evlItmSco04']").val(res.ispInfo.EVL_ITM_SCO04).prop("selected", true);
						$("input[name='evlItmLnk04']").val(res.ispInfo.EVL_ITM_LNK04);
						$("select[name='evlItmSco05']").val(res.ispInfo.EVL_ITM_SCO05).prop("selected", true);
						$("input[name='evlItmLnk05']").val(res.ispInfo.EVL_ITM_LNK05);
						$("select[name='evlItmSco06']").val(res.ispInfo.EVL_ITM_SCO06).prop("selected", true);
						$("input[name='evlItmLnk06']").val(res.ispInfo.EVL_ITM_LNK06);
						$("select[name='evlItmSco07']").val(res.ispInfo.EVL_ITM_SCO07).prop("selected", true);
						$("input[name='evlItmLnk07']").val(res.ispInfo.EVL_ITM_LNK07);
						$("select[name='evlItmSco08']").val(res.ispInfo.EVL_ITM_SCO08).prop("selected", true);
						$("input[name='evlItmLnk08']").val(res.ispInfo.EVL_ITM_LNK08);
						$("select[name='evlItmSco09']").val(res.ispInfo.EVL_ITM_SCO09).prop("selected", true);
						$("input[name='evlItmLnk09']").val(res.ispInfo.EVL_ITM_LNK09);
						$("select[name='evlItmSco10']").val(res.ispInfo.EVL_ITM_SCO10).prop("selected", true);
						$("input[name='evlItmLnk10']").val(res.ispInfo.EVL_ITM_LNK10);
						$("select[name='evlItmSco11']").val(res.ispInfo.EVL_ITM_SCO11).prop("selected", true);
						$("input[name='evlItmLnk11']").val(res.ispInfo.EVL_ITM_LNK11);
						$("select[name='evlItmSco12']").val(res.ispInfo.EVL_ITM_SCO12).prop("selected", true);
						$("input[name='evlItmLnk12']").val(res.ispInfo.EVL_ITM_LNK12);
						$("select[name='evlItmSco13']").val(res.ispInfo.EVL_ITM_SCO13).prop("selected", true);
						$("input[name='evlItmLnk13']").val(res.ispInfo.EVL_ITM_LNK13);
						$("select[name='evlItmSco14']").val(res.ispInfo.EVL_ITM_SCO14).prop("selected", true);
						$("input[name='evlItmLnk14']").val(res.ispInfo.EVL_ITM_LNK14);
						$("select[name='evlItmSco15']").val(res.ispInfo.EVL_ITM_SCO15).prop("selected", true);
						$("input[name='evlItmLnk15']").val(res.ispInfo.EVL_ITM_LNK15);
						$("select[name='evlItmSco16']").val(res.ispInfo.EVL_ITM_SCO16).prop("selected", true);
						$("input[name='evlItmLnk16']").val(res.ispInfo.EVL_ITM_LNK16);
						$("textarea[name='ispRst']").val(res.ispInfo.ISP_RST);
						$("textarea[name='tgtCtnt']").val(res.ispInfo.TGT_CTNT);
					}
				},
				error : function(xhr, status){}
			});
		},
		<%-- ISP 선택 삭제 --%>
		removeIsp = function(tagIspDt, tagMbrNo, idx){
			$.ajax({
				url : '/ajaxClsIspDel.do',
				type : 'POST',
				data : {
					mbrNo : tagMbrNo, 
					ispDt : tagIspDt
				},
				success : function(res){
					if(res.err != "Y"){
						$("#ispTableList > tr").each(function(){
							if($(this).attr("id") == idx){
								$(this).remove();
							}
						});

						if($("#ispTableList > tr").length <= 0){
							var inHtml = "<tr><td colspan='6'>조회된 데이터가 없습니다.</td></tr>";
							$("#ispTableList").append(inHtml);
						}
					}else{
						console.log(res.MSG);
					}
				},
				error : function(xhr, status){}
			});
		},
		<%-- ISP 저장 --%>
		saveIsp = function(){
			if($("input[name='mbrNo']").val() == ""){
				alert("회원을 선택하세요.");
				return;
			}

			$.ajax({
				url : '/ajaxCslIspAdd.do',
				type : 'POST',
				data : $('#cslForm').serialize(),
				success : function(res){
					console.log(res);
				},
				error : function(xhr, status){
				}
			});
		},
		<%-- ISP 신규 작성 --%>
		newIsp = function(){
			$("#ispInfoDiv").load(window.location.href + ' #ispInfoDiv');
		},
		<%-- 사정평자 이력 조회 --%>
		getCslAssList = function(tagMbrNo){
			tagMbrNo = "M20273770366431";
			$.ajax({
				url : '/ajaxCslAssInfo.do',
				type : 'POST',
				data : {
					mbrNo : tagMbrNo
				},
				success : function(res){
					console.log(res);
				},
				error : function(xhr, status){
				}
			});
		},
		saveAss = function(){
			if($("input[name='mbrNo']").val() == ""){
				alert("회원을 선택하세요.");
				return;
			}

			console.log("SAVE");
		}
	});
</script>
<br/>
개별 회복지원 서비스<br/>
<form name="cslForm" id="cslForm">
<a href="javaScript:individualNew();">초기화</a> <a href="javaScript:individualExel();">엑셀다운로드</a><br/>
회원등록번호	<form:input path="mstMbrInfo.mbrNo" cssClass="txt" readonly="true" placeholder="회원등록번호" /><a href="javaScript:searchMemberPopup();">조회</a>
성명			<form:input path="mstMbrInfo.mbrNm" cssClass="txt" readonly="true" placeholder="성명" />
성별			<form:input path="mstMbrInfo.gendNm" cssClass="txt" readonly="true" placeholder="성별" />
연령			<form:input path="mstMbrInfo.age" cssClass="txt" readonly="true" maxlength="3" placeholder="연령" />(세)
등록일자		<form:input path="mstMbrInfo.regDt" cssClass="txt" placeholder="등록일자" />
의료보장		<select name="medicCareCd" disabled>
				<option value="" />선택
			<c:forEach var="result" items="${medicCareCdList}" varStatus="status">
				<option value="<c:out value="${result.CD_ID}"/>"<c:if test="${not empty mstMbrInfo.medicCareCd and rsult.CD_ID eq mstMbrInfo.medicCareCd}"> selected</c:if> /><c:out value="${result.CD_NM}" />
			</c:forEach>
			</select>
사례관리자		<form:input path="mstMbrInfo.mngUsrId" cssClass="txt" readonly="true" placeholder="사례관리자" /><br/>
<a href="javaScript:bodyView('body01');">집중상담</a> <a href="javaScript:bodyView('body02');">ISP 수립</a> <a href="javaScript:bodyView('body03');">사정평가</a><br/>
<div id="bodyView" name="body01" style="display:none;">
<a href="javaScript:saveIdv();">저장</a> <a href="javaScript:newIdv();">신규</a>
<table border="1">
	<tr>
		<td>#</td>
		<td>상담 번호</td>
		<td>상담 일자</td>
		<td>시작/종료 시간</td>
		<td>소요(분)</td>
		<td>상담주제</td>
		<td>상담목표</td>
		<td>상담자</td>
		<td>작업</td>
	</tr>
	<tbody id="idvTableList">
		<tr>
			<td colspan="9">조회된 데이터가 없습니다.</td>
		</tr>
	</tbody>
</table><br/>
<div id="idvInfoDiv">
			<form:input path="cslIdvInfo.cslNo" />
상담자		<form:input path="cslIdvInfo.cslNm" cssClass="txt" readonly="true" />
상담일시		<form:input path="cslIdvInfo.cslDt" cssClass="txt" placeholder="날짜 선택" />
			<form:input path="cslIdvInfo.cslFmTm" cssClass="txt" placeholder="시작"/> ~ <form:input path="cslIdvInfo.cslToTm" cssClass="txt" placeholder="종료" />
			<form:input path="cslIdvInfo.cslTermTm" cssClass="txt" readonly="true" />분 소요<br/>
상담대상		<c:forEach var="result" items="${cslTgtCdList}" varStatus="status">
				<input type="radio" name="cslTgtCd" value="${result.CD_ID}"<c:if test="${result.CD_ID eq cslIdvInfo.cslTgtCd}"> checked</c:if> /><c:out value="${result.CD_NM}" />
			</c:forEach>
상담유형		<c:forEach var="result" items="${cslTpCdList}" varStatus="status">
				<input type="radio" name="cslTpCd" value="${result.CD_ID}"<c:if test="${result.CD_ID eq cslIdvInfo.cslTpCd}"> checked</c:if> /><c:out value="${result.CD_NM}" />
			</c:forEach><br/>
상담주제		<form:input path="cslIdvInfo.cslSbj" cssClass="txt" placeholder="상담주제" /><br/>
상담목표		<form:input path="cslIdvInfo.cslTgt" cssClass="txt" placeholder="상담목표" /><br/>
상담내용<a href="javaScript:ctntPopup();">보기</a>
			<textarea rows="20" name="cslCtnt" placeholder="상담 내용">${cslIdvInfo.cslCtnt}</textarea><br/>
위기분류척도 점수<form:input path="cslIdvInfo.rskSco" cssClass="txt" />
Rating A: 위험성<select name="rskaTpCd">
			<c:forEach var="result" items="${rskaTpList}" varStatus="status">
				<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
			</c:forEach>
			</select><br/>
Rating B: 지지체계	<select name="rskbTpCd">
			<c:forEach var="result" items="${rskbTpList}" varStatus="status">
				<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
			</c:forEach>
			</select><br/>
Rating C: 협조능력	<select name="rskcTpCd">
			<c:forEach var="result" items="${rskcTpList}" varStatus="status">
				<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
			</c:forEach>
			</select><br/>
다음 상담일시	<form:input path="cslIdvInfo.nxtCslTm" cssClass="txt" placeholder="시간" /><br/>
다음 상담내용<a href="javaScript:nxtCtntPopup();">보기</a>
		<textarea name="nxtCslCtnt" rows="20" placeholder="다음 상담내용">${cslIdvInfo.nxtCslCtnt}</textarea>
</div>
</div>
<div id="bodyView" name="body02" style="display:none;">
<a href="javaScript:saveIsp();">저장</a> <a href="javaScript:newIsp();">신규</a>
<table border="1">
	<tr>
		<td>#</td>
		<td>수립 일자</td>
		<td>관리구분</td>
		<td>ISP 결과</td>
		<td>등록자</td>
		<td>작업</td>
	</tr>
	<tbody id="ispTableList">
		<tr>
			<td colspan="6">조회된 데이터가 없습니다.</td>
		</tr>
	</tbody>
</table><br/>
<div id="ispInfoDiv">
			<input type="hidden" name="newFlag" value="Y" />
ISP 일자		<form:input path="cslIspInfo.ispDt" cssClass="txt" placeholder="날짜 선택" />
관리구분		<form:input path="cslIspInfo.mngTpNm" cssClass="txt" placeholder="일시관리" />
	<table border="1">
		<tr>
			<th colspan="3">중독 평가</th>
			<th colspan="3">상태 평가</th>
			<th colspan="3">사회적 기능 평가</th>
			<th colspan="3">사회 서비스 평가</th>
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
			<th>문항</th>
			<th>심각도</th>
			<th>자원연계</th>
		</tr>
		<tr>
			<td>약물사용문제</td>
			<td>
				<select name="evlItmSco01">
	<c:forEach var="result" items="${evlItmSco01List}" varStatus="status">
					<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
	</c:forEach>
				</select>
			</td>
			<td><form:input path="cslIspInfo.evlItmLnk01" cssClass="txt" /></td>
			<td>자해 및 타해 위험</td>
			<td>
				<select name="evlItmSco05">
	<c:forEach var="result" items="${evlItmSco01List}" varStatus="status">
					<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
	</c:forEach>
				</select>
			</td>
			<td><form:input path="cslIspInfo.evlItmLnk05" cssClass="txt" /></td>
			<td>가족관계</td>
			<td>
				<select name="evlItmSco10">
	<c:forEach var="result" items="${evlItmSco10List}" varStatus="status">
					<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
	</c:forEach>
				</select>
			</td>
			<td><form:input path="cslIspInfo.evlItmLnk10" cssClass="txt" /></td>
			<td>주거</td>
			<td>
				<select name="evlItmSco12">
	<c:forEach var="result" items="${evlItmSco12List}" varStatus="status">
					<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
	</c:forEach>
				</select>
			</td>
			<td><form:input path="cslIspInfo.evlItmLnk12" cssClass="txt" /></td>
		</tr>
		<tr>
			<td>알코올사용문제</td>
			<td>
				<select name="evlItmSco02">
	<c:forEach var="result" items="${evlItmSco02List}" varStatus="status">
					<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
	</c:forEach>
				</select>
			</td>
			<td><form:input path="cslIspInfo.evlItmLnk02" cssClass="txt" /></td>
			<td>정신과적 증상</td>
			<td>
				<select name="evlItmSco06">
	<c:forEach var="result" items="${evlItmSco06List}" varStatus="status">
					<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
	</c:forEach>
				</select>
			</td>
			<td><form:input path="cslIspInfo.evlItmLnk06" cssClass="txt" /></td>
			<td>사회적관계</td>
			<td>
				<select name="evlItmSco11">
	<c:forEach var="result" items="${evlItmSco11List}" varStatus="status">
					<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
	</c:forEach>
				</select>
			</td>
			<td><form:input path="cslIspInfo.evlItmLnk11" cssClass="txt" /></td>
			<td>경제활동 지원</td>
			<td>
				<select name="evlItmSco13">
	<c:forEach var="result" items="${evlItmSco13List}" varStatus="status">
					<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
	</c:forEach>
				</select>
			</td>
			<td><form:input path="cslIspInfo.evlItmLnk13" cssClass="txt" /></td>
		</tr>
		<tr>
			<td>도박사용문제</td>
			<td>
				<select name="evlItmSco03">
	<c:forEach var="result" items="${evlItmSco03List}" varStatus="status">
					<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
	</c:forEach>
				</select>
			</td>
			<td><form:input path="cslIspInfo.evlItmLnk03" cssClass="txt" /></td>
			<td>정신약물관리</td>
			<td>
				<select name="evlItmSco07">
	<c:forEach var="result" items="${evlItmSco07List}" varStatus="status">
					<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
	</c:forEach>
				</select>
			</td>
			<td><form:input path="cslIspInfo.evlItmLnk07" cssClass="txt" /></td>
			<td colspan="3">&nbsp;</td>
			<td>취업 및 학업욕구</td>
			<td>
				<select name="evlItmSco14">
	<c:forEach var="result" items="${evlItmSco07List}" varStatus="status">
					<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
	</c:forEach>
				</select>
			</td>
			<td><form:input path="cslIspInfo.evlItmLnk14" cssClass="txt" /></td>
		</tr>
		<tr>
			<td>인터넷사용문제</td>
			<td>
				<select name="evlItmSco04">
	<c:forEach var="result" items="${evlItmSco04List}" varStatus="status">
					<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
	</c:forEach>
				</select>
			</td>
			<td><form:input path="cslIspInfo.evlItmLnk04" cssClass="txt" /></td>
			<td>스트레스 상태</td>
			<td>
				<select name="evlItmSco08">
	<c:forEach var="result" items="${evlItmSco08List}" varStatus="status">
					<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
	</c:forEach>
				</select>
			</td>
			<td><form:input path="cslIspInfo.evlItmLnk08" cssClass="txt" /></td>
			<td colspan="3">&nbsp;</td>
			<td>고용 및 교육가능성</td>
			<td>
				<select name="evlItmSco15">
	<c:forEach var="result" items="${evlItmSco15List}" varStatus="status">
					<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
	</c:forEach>
				</select>
			</td>
			<td><form:input path="cslIspInfo.evlItmLnk15" cssClass="txt" /></td>
		</tr>
		<tr>
			<td colspan="3">&nbsp;</td>
			<td>신체질환</td>
			<td>
				<select name="evlItmSco09">
	<c:forEach var="result" items="${evlItmSco09List}" varStatus="status">
					<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
	</c:forEach>
				</select>
			</td>
			<td><form:input path="cslIspInfo.evlItmLnk09" cssClass="txt" /></td>
			<td colspan="3">&nbsp;</td>
			<td>법률적 문제</td>
			<td>
				<select name="evlItmSco16">
	<c:forEach var="result" items="${evlItmSco16List}" varStatus="status">
					<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
	</c:forEach>
				</select>
			</td>
			<td><form:input path="cslIspInfo.evlItmLnk16" cssClass="txt" /></td>
		</tr>
	</table><br/>
ISP 결과		<textarea name="ispRst" placeholder="ISP 결과"rows="8"></textarea><br/>
장단기 목표수립	<textarea name="tgtCtnt" placeholder="장단기 목표수립" rows="8"></textarea><br/>
</div>
</div>
<div id="bodyView" name="body03">
<a href="javaScript:saveAss();">저장</a><br/>
중독력<br/>
최초 사용약물	<select name="fstDrugCd">
				<option value="" />선택
			<c:forEach var="result" items="${fstDrugCdList}" varStatus="status">
				<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
			</c:forEach>
			</select><br/>
			<form:input path="cslAssInfo.fstDrug" cssClass="txt" placeholder="최초사용약물 입력" /><br/>
주요 사용약물	<select name="mainDrugCd">
				<option value="" />선택
			<c:forEach var="result" items="${mainDrugCdList}" varStatus="status">
				<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
			</c:forEach>
			</select><br/>
			<form:input path="cslAssInfo.mainDrug" cssClass="txt" placeholder="주요사용약물 입력" />
최초 사용시기	<form:input path="cslAssInfo.fstAge" cssClass="txt" placeholder="나이" />
마지막 사용시기	<form:input path="cslAssInfo.lstAge" cssClass="txt" placeholder="나이" />
사용기간		<form:input path="cslAssInfo.useTerm" cssClass="txt" placeholder="사용기간" /><br/>
사용빈도		<select name="useFrqCd">
				<option value="" />선택
			<c:forEach var="result" items="${useFrqCdList}" varStatus="status">
				<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
			</c:forEach>
			</select>
사용원인		<select name="useCauCd">
				<option value="" />선택
			<c:forEach var="result" items="${useCauCdList}" varStatus="status">
				<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
			</c:forEach>
			</select>
			<form:input path="cslAssInfo.useCauEtc" cssClass="txt" placeholder="기타 선택시 입력 가능" /><br/>
약물관련 법적문제<select name="lawPbmCd" multiple>
				<option value="" />선택
			<c:forEach var="result" items="${lawPbmCdList}" varStatus="status">
				<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
			</c:forEach>
			</select>
			<form:input path="cslAssInfo.lawPbmEtc" cssClass="txt" placeholder="기타 선택시 입력 가능" /><br/>
신체적 건강문제	<form:input path="cslAssInfo.physPbm" cssClas="txt" placeholder="신체적 건강문제" /><br/>
정신적 건강문제	<select name="sprtPbmCd">
				<option value="" />선택
			<c:forEach var="result" items="${sprtPbmCdList}" varStatus="status">
				<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
			</c:forEach>
			</select>
			<form:input path="cslAssInfo.sprtPbmEtc" cssClass="txt" placeholder="기타 선택시 입력 가능" /><br/>
치료정보<br/>
성격			<select name="prsnCd" multiple>
				<option value="" />선택
			<c:forEach var="result" items="${prsnCdList}" varStatus="status">
				<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
			</c:forEach>
			</select>
정서-심리		<select name="emtnCd" multiple>
				<option value="" />선택
			<c:forEach var="result" items="${emtnCdList}" varStatus="status">
				<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
			</c:forEach>
			</select><br/>
행동			<select name="actnCd" multiple>
				<option value="" />선택
			<c:forEach var="result" items="${actnCdList}" varStatus="status">
				<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
			</c:forEach>
			</select>
가족			<select name="fmlyCd" multiple>
				<option value="" />선택
			<c:forEach var="result" items="${fmlyCdList}" varStatus="status">
				<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
			</c:forEach>
			</select>
대인관계		<select name="itRlCd" multiple>
				<option value="" />선택
			<c:forEach var="result" items="${itRlCdList}" varStatus="status">
				<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
			</c:forEach>
			</select><br/>
기타			<form:input path="cslAssInfo.miEtc" cssClass="txt" placeholder="기타" />
심각성인식정도	<select name="svrRcgDgr">
			<c:forEach var="i" begin="1" end="10" step="1">
				<option value="${i}" />${i}
			</c:forEach>
			</select><br/>
기대효과		<textarea name="expEfc" rows="11" placeholder="기대효과"></textarea>
메모			<textarea name="miMemo" rows="11" placeholder="메모"></textarea><br/>
평가도구<br/>
			<table border="1">
				<tr>
					<td>#</td>
					<td>작업</td>
					<td>평가 도구</td>
					<td>평가 점수</td>
					<td>평가 내용</td>
				</tr>
				<tr>
					<td colspan="5">데이터 없음</td>
				</tr>
				<tr>
					<td>1</td>
					<td><a href="javaScript:removeRowEval();">삭제</a></td>
					<td><a href="javaScript:choiceEval()">내용</a>
					<td></td>
					<td></td>
				</tr>
			</table>
			<a href="javaScript:addRowEval();">◀◀행 추가</a>
평가도구		<select name="evlTolCd">
				<option value="" />평가도구
			<c:forEach var="result" items="${evlTolCdList}" varStatus="status">
				<option value="<c:out value="${result.CD_ID}"/>" /><c:out value="${result.CD_NM}" />
			</c:forEach>
			</select>
평가점수		<input name="evlSco" type="text" placeholder="평가점수" /><br/>
평가내용		<textarea name="evlCtnt" rows="4" placeholder="평가 내용을 입력하세요."></textarea>
</div>
</form>
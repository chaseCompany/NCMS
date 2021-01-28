<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javaScript" language="javascript" defer="defer">
	$(document).ready(function(){
		<%-- 페이지 이동 --%>
		goTransPage = function(pageNo){
			$("input[name='transPageNo']").val(pageNo);
<c:if test="${listType ne 'L'}">
			getTransList();
</c:if>
<c:if test="${listType eq 'L'}">
			getLinkList();
</c:if>
		}
		<%-- 의뢰상세정보 --%>
		transView = function(tagTransNo){
			$.ajax({
				url : '/ajaxMstTransInfo.do',
				type : 'POST',
				data : {
					transNo : tagTransNo
				},
				success : function(res){
					var resultObj = res.transInfo;

					if(resultObj != null){
						getMemInfo(resultObj.MBR_NO);

						$("input[name='transNo']").val(resultObj.TRANS_NO);
						$("select[name='receiptInstCd']").val(resultObj.RECEIPT_INST_CD).prop("selected", true);
						$("input[name='transDt']").val(formatDate(resultObj.TRANS_DT));
						$("input[name='transMemNm']").val(resultObj.TRANS_USR_NM);
						$("input[name='reqServiceCd']:radio[value='" + resultObj.REQ_SERVICE_CD + "']").prop("checked", true);
						$("input[name='linkStateCd']:radio[value='" + resultObj.LINK_STATE_CD + "']").prop("checked", true);
						$("select[name='evlItmSco01']").val(resultObj.EVL_ITM_SCO01).prop("selected", true);
						$("input[name='evlItmLnk01']").val(resultObj.EVL_ITM_LNK01);
						$("select[name='evlItmSco02']").val(resultObj.EVL_ITM_SCO02).prop("selected", true);
						$("input[name='evlItmLnk02']").val(resultObj.EVL_ITM_LNK02);
						$("select[name='evlItmSco03']").val(resultObj.EVL_ITM_SCO03).prop("selected", true);
						$("input[name='evlItmLnk03']").val(resultObj.EVL_ITM_LNK03);
						$("select[name='evlItmSco04']").val(resultObj.EVL_ITM_SCO04).prop("selected", true);
						$("input[name='evlItmLnk04']").val(resultObj.EVL_ITM_LNK04);
						$("select[name='evlItmSco05']").val(resultObj.EVL_ITM_SCO05).prop("selected", true);
						$("input[name='evlItmLnk05']").val(resultObj.EVL_ITM_LNK05);
						$("select[name='evlItmSco06']").val(resultObj.EVL_ITM_SCO06).prop("selected", true);
						$("input[name='evlItmLnk06']").val(resultObj.EVL_ITM_LNK06);
						$("select[name='evlItmSco07']").val(resultObj.EVL_ITM_SCO07).prop("selected", true);
						$("input[name='evlItmLnk07']").val(resultObj.EVL_ITM_LNK07);
						$("select[name='evlItmSco08']").val(resultObj.EVL_ITM_SCO08).prop("selected", true);
						$("input[name='evlItmLnk08']").val(resultObj.EVL_ITM_LNK08);
						$("select[name='evlItmSco09']").val(resultObj.EVL_ITM_SCO09).prop("selected", true);
						$("input[name='evlItmLnk09']").val(resultObj.EVL_ITM_LNK09);
						$("select[name='evlItmSco10']").val(resultObj.EVL_ITM_SCO10).prop("selected", true);
						$("input[name='evlItmLnk10']").val(resultObj.EVL_ITM_LNK10);
						$("select[name='evlItmSco11']").val(resultObj.EVL_ITM_SCO11).prop("selected", true);
						$("input[name='evlItmLnk11']").val(resultObj.EVL_ITM_LNK11);
						$("select[name='evlItmSco12']").val(resultObj.EVL_ITM_SCO12).prop("selected", true);
						$("input[name='evlItmLnk12']").val(resultObj.EVL_ITM_LNK12);
						$("select[name='evlItmSco13']").val(resultObj.EVL_ITM_SCO13).prop("selected", true);
						$("input[name='evlItmLnk13']").val(resultObj.EVL_ITM_LNK13);
						$("select[name='evlItmSco14']").val(resultObj.EVL_ITM_SCO14).prop("selected", true);
						$("input[name='evlItmLnk14']").val(resultObj.EVL_ITM_LNK14);
						$("select[name='evlItmSco15']").val(resultObj.EVL_ITM_SCO15).prop("selected", true);
						$("input[name='evlItmLnk15']").val(resultObj.EVL_ITM_LNK15);
						$("select[name='evlItmSco16']").val(resultObj.EVL_ITM_SCO16).prop("selected", true);
						$("input[name='evlItmLnk16']").val(resultObj.EVL_ITM_LNK16);
						$("select[name='evlItmSco17']").val(resultObj.EVL_ITM_SCO17).prop("selected", true);
						$("input[name='evlItmLnk17']").val(resultObj.EVL_ITM_LNK17);
						$("select[name='evlItmSco18']").val(resultObj.EVL_ITM_SCO18).prop("selected", true);
						$("input[name='evlItmLnk18']").val(resultObj.EVL_ITM_LNK18);
						$("select[name='evlItmSco19']").val(resultObj.EVL_ITM_SCO19).prop("selected", true);
						$("input[name='evlItmLnk19']").val(resultObj.EVL_ITM_LNK19);
						$("select[name='evlItmSco20']").val(resultObj.EVL_ITM_SCO20).prop("selected", true);
						$("input[name='evlItmLnk20']").val(resultObj.EVL_ITM_LNK20);
						$("select[name='evlItmSco21']").val(resultObj.EVL_ITM_SCO21).prop("selected", true);
						$("input[name='evlItmLnk21']").val(resultObj.EVL_ITM_LNK21);
						$("select[name='evlItmSco22']").val(resultObj.EVL_ITM_SCO22).prop("selected", true);
						$("input[name='evlItmLnk22']").val(resultObj.EVL_ITM_LNK22);
						$("select[name='evlItmSco23']").val(resultObj.EVL_ITM_SCO23).prop("selected", true);
						$("input[name='evlItmLnk23']").val(resultObj.EVL_ITM_LNK23);
						$("select[name='evlItmSco24']").val(resultObj.EVL_ITM_SCO24).prop("selected", true);
						$("input[name='evlItmLnk24']").val(resultObj.EVL_ITM_LNK24);
						$("textarea[name='transCtnt']").val(resultObj.TRANS_CTNT);
						$("input[name='fileId']").val("");

						if(resultObj.transFileList != undefined && resultObj.transFileList != ''){
							for(let i=0 ; i<resultObj.transFileList.length ; i++){
								$("div#transfileName").html("<a href='javaScript:downloadFile(\"" + resultObj.transFileList[i].FILE_ID + "\", \"" + resultObj.transFileList[i].FILE_SEQ + "\");'>" + resultObj.transFileList[i].ORIGNL_FILE_NM + "</a>"
														  + "&nbsp;&nbsp;<a href='javaScript:deleteFile(\"transfileName\");'>삭제</a>"
														   );
							}
						}else{
							$("div#transfileName").text("");
						}
						$("input[name='transfileNameFlag']").val("N");
					}
				},
				error : function(xhr, status){
					console.log(xhr);
				}
			});
		}
	});
</script>
<!-- 목록 -->
<div class="el-table_body-wrapper">
<c:if test="${listType ne 'L'}">
	<table>
		<colgroup>
			<col style="width:46px">
			<col style="width:80px">
			<col style="width:100px">
			<col style="width:150px">
			<col style="width:100px">
			<col style="width:120px">
			<col style="width:150px">
			<col style="width:100px">
			<col style="width:150px">
			<col>
		</colgroup>
		<tbody>
	<c:if test="${totalCount > 0}">
		<c:forEach var="result" items="${resultList}" varStatus="status">
			<fmt:parseDate value="${result.TRANS_DT}" var="transDt" pattern="yyyyMMdd" />
			<fmt:parseDate value="${result.RECEIPT_DT}" var="receiptDt" pattern="yyyyMMdd" />
			<tr>
				<td><div class="cell"><fmt:formatNumber value="${result.ROWNUM}" pattern="#" /></div></td>
				<td><div class="cell">의뢰</div></td>
				<td><div class="cell"><c:out value="${result.MBR_NM}" /></div></td>
				<td><div class="cell"><c:out value="${result.TRANS_INST_NM}" /></div></td>
				<td><div class="cell"><fmt:formatDate value="${transDt}" pattern="yyyy-MM-dd" /></div></td>
				<td><div class="cell"><c:out value="${result.TRANS_USR_NM}" /></div></td>
				<td><div class="cell"><c:out value="${result.RECEIPT_INST_NM}" /></div></td>
				<td><div class="cell"><fmt:formatDate value="${receiptDt}" pattern="yyyy-MM-dd" /></div></td>
				<td><div class="cell"><c:out value="${result.RECEIPT_USR_NM}" /></div></td>
				<td><div class="cell"><c:out value="${result.LINK_STATE_NM}" /></div></td>
				<td>
					<div class="cell">
						<button type="button" class="el-button el-button--warning el-button--mini is-plain" style="margin-left: 1px; padding: 4px 9px;" onclick="javaScript:transView('<c:out value="${result.TRANS_NO}" />');">
							<span>선택</span>
						</button>
					</div>
				</td>
			</tr>
		</c:forEach>
	</c:if>
	<c:if test="${totalCount <= 0}">
			<tr>
				<td colspan="11">조회된 데이터가 없습니다.</td>
			</tr>
	</c:if>
		</tbody>
	</table>
</c:if>
<c:if test="${listType eq 'L'}">
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
		<tbody>
	<c:if test="${totalCount > 0}">
		<c:forEach var="result" items="${resultList}" varStatus="status">
			<tr>
				<td><div class="cell"><fmt:formatNumber value="${result.ROWNUM}" pattern="#" /></div></td>
				<td><div class="cell"><c:out value="${result.MBR_NO}" /></div></td>
				<td><div class="cell"><c:out value="${result.MBR_NM}" /></div></td>
				<td><div class="cell"><c:out value="${result.GEND_NM}" /></div></td>
				<td><div class="cell"><c:out value="${result.TEL_NO1}" />-<c:out value="${result.TEL_NO2}" />-<c:out value="${result.TEL_NO3}" /></div></td>
				<td><div class="cell"><c:out value="${result.MNG_USR_NM}" /></div></td>
				<td><div class="cell"><c:out value="${result.SITE_NM}" /></div></td>
				<td>
					<div class="cell">
						<button type="button" class="el-button el-button--warning el-button--mini is-plain" style="margin-left: 1px; padding: 4px 9px;" onclick="javaScript:transView('<c:out value="${result.TRANS_NO}" />');">
							<span>선택</span>
						</button>
					</div>
				</td>
			</tr>
		</c:forEach>
	</c:if>
	<c:if test="${totalCount <= 0}">
			<tr>
				<td colspan="8">조회된 데이터가 없습니다.</td>
			</tr>
	</c:if>
		</tbody>
	</table>
</c:if>
</div>
<!-- // 목록 -->
<!-- 페이징 -->
<div class="paging">
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="goTransPage" />
</div>
<!-- // 페이징 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javaScript" language="javascript" defer="defer">
	$(document).ready(function(){
		<%-- 프로그램 참여자 삭제 --%>
		pgmMemDel = function(rowNum, tagMbrNo){
			console.log(rowNum + ":" + tagMbrNo);
			if(!tagMbrNo){
				if($("input[name='deleteMbrNo']").val() != ""){
					$("input[name='deleteMbrNo']").val($("input[name='deleteMbrNo']").val() + "," + tagMbrNo);
				}else{
					$("input[name='deleteMbrNo']").val(tagMbrNo);
				}
			}
			$("table#pgmMemTable tr").each(function(){
				if($(this).attr("id") == rowNum){
					$(this).remove();
				}
			});

			if($("table#pgmMemTable tr").length <= 0){
				$("div#pgmMemTableDiv").html("<div class='no-data'>조회된 데이터가 없습니다.</div>");
			}
		}
	});
</script>
<input type="hidden" name="pgmMemCount" value="<c:out value="${pgmMemListCount}" />" />
<input type="hidden" name="deleteMbrNo" />
<div class="el-table_body-wrapper" style="height: 370px;" id="pgmMemTableDiv">
<c:if test="${pgmMemListCount > 0}">
	<c:if test="${pgmMemList ne null and pgmMemList ne ''}">
	<table id='pgmMemTable'>
		<colgroup>
			<col style="width:46px">
			<col style="width:66px">
			<col style="width:160px">
			<col style="width:100px">
			<col style="width:650px">
			<col>
		</colgroup>
		<tbody>
		<c:forEach var="result" items="${pgmMemList}" varStatus="status">
		<tr id='<c:out value="${status.index + 1}" />'>
			<input type="hidden" name="pgmMbrNo" value="<c:out value="${result.MBR_NO}" />" />
			<td><div class="cell"><c:out value="${status.index + 1}" /></div></td>
			<td>
				<div class="cell">
					<button type="button" onclick="javaScript:pgmMemDel('<c:out value="${status.index + 1}" />', \''<c:out value="${result.MBR_NO}" />\'');" class="el-button el-button--danger el-button--mini is-plain" slot="reference" style="margin-left: 1px; padding: 4px 9px;">
						<span>삭제</span>
					</button>
				</div>
			</td>
			<td><div class="cell"><c:out value="${result.MBR_NO}" /></div></td>
			<td><div class="cell"><c:out value="${result.MBR_NM}" /></div></td>
			<td class="txt-left"><div class="cell" id="ctntView"><c:out value="${result.PGM_USER_CNT}" /></div></td>
			<td>
				<div class="cell">
					<button type="button" onclick="javaScript:viewCtnt('pgmUserCnt', '<c:out value="${status.index + 1}" />');" class="el-button el-button--success el-button--mini is-plain" slot="reference" style="margin-left: 1px; padding: 4px 9px;">
						<i class="el-icon-search"></i>
					</button>
				</div>
				<textarea name="pgmUserCnt" style="display:none;"><c:out value="${result.PGM_USER_CNT}" /></textarea>
			</td>
		</tr>
		</c:forEach>
		</tbody>
	</table>
	</c:if>
</c:if>
<c:if test="${pgmMemListCount eq null or pgmMemListCount eq null or pgmMemListCount <= 0}">
	<div class="no-data">조회된 데이터가 없습니다.</div>
</c:if>
</div>
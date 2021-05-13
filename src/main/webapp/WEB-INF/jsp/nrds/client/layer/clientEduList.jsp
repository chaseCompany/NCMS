<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${fn:length(resultList) > 0}">
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
	<tbody>
	<c:forEach var="result" items="${resultList}" varStatus="status">
	<tr>
		<td><fmt:formatNumber value="${status.count}" pattern="#" /></td>
		<td><a href="javaScript:getMbrInfo('<c:out value="${result.MBR_NO}" />', '<c:out value="${result.MBR_ED_ID}" />');"><c:out value="${result.MBR_NO}" /></a></td>
		<td><c:out value="${result.MBR_NM}" /></td>
		<td><c:out value="${result.GEND_NM}" /></td>
		<td><c:out value="${result.AGE_NM}" /></td>
		<td><c:out value="${result.TEL_NO1}" />-<c:out value="${result.TEL_NO2}" />-<c:out value="${result.TEL_NO3}" /></td>
		<td><c:out value="${result.REQ_ORG}" /></td>
		<td><c:out value="${result.SITE_NM}" /></td>
	</tr>
	</c:forEach>
	</tbody>
</table>
</c:if>
<c:if test="${fn:length(resultList) <= 0}">
	<div class="no-data">조회된 데이터가 없습니다.</div>
</c:if>
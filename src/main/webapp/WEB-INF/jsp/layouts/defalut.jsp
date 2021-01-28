<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<!--[if IE 9 ]><html lang="ko" class="ie9"><![endif]-->
<!--[if (gt IE 9)]><!--><html lang="ko"><!--<![endif]-->
<head>
	<meta charset="UTF-8">
	<title><spring:message code="ncms.title" /></title>
	<link rel="shortcut icon" href="<c:url value='/images/favicon.ico'/>" />
	<tiles:insertAttribute name="head" />
</head>
<body>
	<tiles:insertAttribute name="topmenu" />
	<!-- content -->
	<div id="content">
		<tiles:insertAttribute name="content" />
		<div class="layerpopup" id="layerpopup"></div>
	</div>
	<!-- // content -->
	<form name="excelForm" id="excelForm" method="post"></form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title><spring:message code="ncms.title" /></title>
	<link rel="shortcut icon" href="<c:url value='/images/favicon.ico'/>" />
	<tiles:insertAttribute name="head" />
</head>
<body>
	<!-- content -->
	<div id="content">
		<tiles:insertAttribute name="topmenu" />
		<tiles:insertAttribute name="content" />

		<div class="layerpopup" id="layerpopup"></div>
	</div>
	<!-- // content -->
	<form name="excelForm" id="excelForm" method="post"></form>
</body>
</html>
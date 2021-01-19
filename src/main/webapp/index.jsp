<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%
	String reDirect = StringUtils.defaultIfEmpty((String)request.getAttribute("reDirect"), "");
	String url = "/login.do";
	if(!"".equals(reDirect)){
		url = url + "?" + reDirect;
	}
%>
<jsp:forward page="<%=url%>"/>
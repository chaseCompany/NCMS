<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String thisViewUrl = StringUtils.defaultIfEmpty((String)request.getAttribute("thisViewUrl"), "");
	String loginUserNm = StringUtils.defaultIfEmpty((String)request.getAttribute("LoginUserNm"), "");
	String LoginSiteConsult = StringUtils.defaultIfEmpty((String)request.getAttribute("LoginSiteConsult"), "0");
%>
<script type="text/javaScript" language="javascript" defer="defer">
	$(document).ready(function(){
		logOut = function(){
			window.location.href = '/logout.do';
		},
		goTopMenuPage = function(path){
			window.location.href = path;
		}
	});
</script>
<!-- header -->
<div id="header" style="background:#b1386b;">
	<div class="l">
		<button type="button"><img src="/images/icon_top_menu.png" alt=""></button>
	</div>
	<div class="c">
		<ul>
			<li><a href="javaScript:goTopMenuPage('/nrds/recyclePrgMain.do');"<c:if test="${thisViewUrl eq 'counselMain.do'}"> class="on"</c:if>>재활교육프로그램</a></li>
			<li><a href="javaScript:goTopMenuPage('/nrds/eduCounselMain.do');"<c:if test="${thisViewUrl eq 'individualMain.do'}"> class="on"</c:if>>교육상담</a></li>
			<li><a href="javaScript:goTopMenuPage('/nrds/eduMngMain.do');"<c:if test="${thisViewUrl eq 'mentalityMain.do'}"> class="on"</c:if>>교육관리</a></li>
			<li><a href="javaScript:goTopMenuPage('/nrds/clientMain.do');"<c:if test="${thisViewUrl eq 'weeklyPrgMain.do'}"> class="on"</c:if>>대상자관리</a></li>
			<li><a href="javaScript:goTopMenuPage('/nrds/surveyMain.do');"<c:if test="${thisViewUrl eq 'weeklyPrgMain.do'}"> class="on"</c:if>>설문지관리</a></li>
			<li><a href="javaScript:goTopMenuPage('/nrds/statMain.do');"<c:if test="${thisViewUrl eq 'reportMain.do'}"> class="on"</c:if>>통계관리</a></li>
		</ul>
	</div>
	<div class="r">
<c:if test="${LoginSiteConsult eq '1'}">
		<a href="<c:url value="/counselMain.do"/>" class="f">상담</a>&nbsp;&nbsp;
</c:if>
		<a href="#" class="f"> <%=loginUserNm%>님
			<i class="el-icon-arrow-down el-icon--right"></i>
			<div class="pwd-layer">
				<ul class="el-dropdown-menu el-popper" id="dropdown-menu-133">
					<li tabindex="-1" class="el-dropdown-menu__item"><i class="el-icon-lock"></i>비밀번호 변경</li>
				</ul>
			</div>
		</a>
		<a href="javaScript:logOut();" class="el-button el-button--default el-button--mini s"><i class="el-icon-switch-button"></i> <span>로그아웃</span></a>
	</div>
	<!-- 사이드 메뉴 -->
	<div class="side-menu-wrap">
		<div class="side-menu">
			<div class="sd-top">
				<span>상담관리시스템</span>
				<button aria-label="close 상담관리시스템" type="button" class="el-drawer__close-btn">
					<i class="el-dialog__close el-icon el-icon-close"></i>
				</button>
			</div>
			<div class="sd-mm-list">
				<ul>
					<li>
						<a href="javaScript:goTopMenuPage('/nrds/recyclePrgMain.do');"><i class="el-icon-s-order"></i>재활교육프로그램</a>
					</li>
					<li>
						<a href="javaScript:goTopMenuPage('/nrds/eduCounselMain.do');"><i class="el-icon-s-platform"></i>교육상담</a>
					</li>
					<li>
						<a href="javaScript:goTopMenuPage('/nrds/eduMngMain.do');"><i class="el-icon-s-platform"></i>교육관리</a>
					</li>
					<li>
						<a href="javaScript:goTopMenuPage('/nrds/clientMain.do');"><i class="el-icon-s-platform"></i>대상자관리</a>
					</li>
					<li>
						<a href="javaScript:goTopMenuPage('/nrds/surveyMain.do');"><i class="el-icon-s-custom"></i>설문지관리</a>
					</li>
					<li>
						<a href="javaScript:goTopMenuPage('/nrds/statMain.do');"><i class="el-icon-printer"></i>통계관리</a>
					</li>
					<li>
						<a href="#n">
							<i class="el-icon-setting"></i>기초자료 관리
							<span><i class="el-submenu__icon-arrow el-icon-arrow-down"></i></span>
						</a>
						<ul>
							<li><a href="javaScript:goTopMenuPage('/nrds/adminUsr.do');">사용자 관리</a></li>
							<li><a href="javaScript:goTopMenuPage('/nrds/adminCode.do');">코드 관리</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<!-- // 사이드 메뉴 -->
</div>
<!-- // header -->
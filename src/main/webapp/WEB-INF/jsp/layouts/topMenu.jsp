<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javaScript" language="javascript" defer="defer">
	$(document).ready(function(){
		logOut = function(){
			window.location.href = '/logout.do';
		},
		goPage = function(path){
			window.location.href = path;
		}
	});
</script>
<div id="header">
	<div class="l">
		<button type="button"><img src="images/icon_top_menu.png" alt="">&nbsp;</button>
	</div>
	<div class="c">
		<ul>
			<li><a href="javaScript:goPage('/counselMain.do');" class="on">일반 상담</a></li>
			<li><a href="javaScript:goPage('/individualMain.do');">개별 회복지원 서비스</a></li>
			<li><a href="javaScript:goPage('/');">주간 프로그램</a></li>
			<li><a href="javaScript:goPage('/');">회원정보 관리</a></li>
			<li><a href="javaScript:goPage('/');">통계 관리</a></li>
		</ul>
	</div>
	<div class="r">
		<a href="#" class="f">관리자님<i class="el-icon-arrow-down el-icon--right"></i>
			<div class="pwd-layer">
				<ul class="el-dropdown-menu el-popper" id="dropdown-menu-133">
					<li tabindex="-1" class="el-dropdown-menu__item"><i class="el-icon-lock"></i>비밀번호 변경</li>
				</ul>
			</div>
		</a>
		<a href="javaScript:logOut();" class="el-button el-button--default el-button--mini s">
			<i class="el-icon-switch-button"></i>
			<span>로그아웃</span>
		</a>
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
						<a href="일반상담.html"><i class="el-icon-s-order"></i>일반 상담</a>
					</li>
					<li>
						<a href="개별회복지원서비스.html"><i class="el-icon-s-platform"></i>개별 상담</a>
					</li>
					<li>
						<a href="회원정보관리.html"><i class="el-icon-s-custom"></i>회원정보 관리</a>
					</li>
					<li>
						<a href="통계관리.html"><i class="el-icon-printer"></i>통계 관리</a>
					</li>
					<li>
						<a href="#n">
							<i class="el-icon-setting"></i>기초자료 관리
							<span><i class="el-submenu__icon-arrow el-icon-arrow-down"></i></span>
						</a>
						<ul>
							<li><a href="사용자관리.html">사용자 관리</a></li>
							<li><a href="코드관리.html">코드 관리</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<!-- // 사이드 메뉴 -->
</div>
<!-- // header -->
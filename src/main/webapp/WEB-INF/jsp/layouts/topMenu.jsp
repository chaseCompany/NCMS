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
<a href="javaScript:goPage('/counselMain.do');">일반 상담</a>
<a href="javaScript:goPage('/');">개별 회복지원 서비스</a>
<a href="javaScript:goPage('/');">주간 프로그램</a>
<a href="javaScript:goPage('/');">회원정보 관리</a>
<a href="javaScript:goPage('/');">통계 관리</a>
<a href="javaScript:logOut();">로그아웃</a>
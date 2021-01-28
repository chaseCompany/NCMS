<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javaScript" language="javascript" defer="defer">
	$(document).ready(function(){
		goLogin = function(){
			var usrId = $("input[name='usrId']").val();
			var passwd = $("input[name='passwd']").val();
			var Data = {
				"usrId" : usrId,
				"passwd" : passwd
			};
			$.ajax({
				url : "/ajaxLogin.do",
				type : "POST",
				data : Data,
				success : function(data){
					if(data.err != "Y"){
<c:if test="${reDirect ne null && reDirect ne ''}">
						window.location.href = '<c:out value="${reDirect}" />';
</c:if>
<c:if test="${reDirect eq null || reDirect eq ''}">
						window.location.href = '/counselMain.do';
</c:if>
					}else{
						alert(data.MSG);
					}
				},
				error : function(){
					console.log("ER");
				}
			});
		}
	});
</script>
<div class="login_wrap">
	<div class="login_box">
		<img src="/images/logo_login.png" alt="한국마약퇴치운동본부" class="logo">
		<div class="commont">
			이곳은 업무담당자를 위한 영역입니다.<br>
			허가되지 않은 사용자의 접속이나 자료유출, 정보취득,<br>
			정보변경자에 대한 법적책임과 처벌을 받을 수 있습니다.
		</div>
		<form name='loginForm'>
		<div class="input_box">
			<div class="id_box">
				<input type="text" name="usrId">
			</div>
			<div class="pw_box">
				<input type="password" name="passwd">
			</div>
			<a href="javaScript:goLogin();" class="login_btn">로그인</a>
		</div>
		</form>
		<div class="copy">Copyrightⓒ 2015 Agency All rights reserved.</div>
	</div>
</div>
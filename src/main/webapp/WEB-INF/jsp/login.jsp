<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
	$(document).ready(function(){
		$("input[name='subBtn']").click(function(){
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
						window.location.href = '/counsel/counselMain.do';
					}else{
						alert(data.MSG);
					}
				},
				error : function(){
					console.log("ER");
				}
			});
		});
	});
</script>
<form name='loginForm'>
ID : <input type="text" name="usrId" />
PW : <input type="password" name="passwd" />
<input type="button" name="subBtn" id="subBtn" value="로그인"/>
</form>
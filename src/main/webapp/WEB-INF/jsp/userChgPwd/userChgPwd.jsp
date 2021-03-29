<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script type="text/javaScript" language="javascript" defer="defer">

	$(document).ready(function(){

		usrChgPwd = function(){

			if (fnCheckValidation() == false)
				return false;
			
			$.ajax({
				url : '/ajaxUsrChgPwd.do',
				type : 'POST',
				data : $('#formUsrPwdChg').serialize(),
				success : function(res){
					if(res.err != "Y"){
						alert("비밀번호 변경 완료");
						location.reload();
					}else{
						alert(res.MSG);
						if(res.actUrl != "" && res.actUrl != undefined){
							window.location.href = res.actUrl;
						}
					}
				},
				error : function(xhr, status){
				}
			});

		}
	});
	
	function fnCheckValidation() {

		var newPwd1 = $("#newPwd1").val();
		var newPwd2 = $("#newPwd2").val();
		var reg = /^(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;

		
		if (!($("#currPwd").val())) {
			alert("현재 비밀번호를 입력하세요.");
			return false;
		}
				
		if (newPwd1.length < 8) {
			alert("신규 비밀번호를 8자리 이상 입력하세요.");
			return false;
		}

		if (newPwd1.search(/\s/) != -1) {
			alert("신규 비밀번호에 공백 없이 입력하세요.");
			return false;
		}
		
		if(false === reg.test(newPwd1)) {
			alert('비밀번호는 8자 이상이어야 하며, 소문자/숫자/특수문자를 모두 포함해야 합니다.');
			return false;
		}

		if (newPwd1 != newPwd2) {
			alert("신규 비밀번호가 동일하지 않습니다.");
			return false;
		}

	}

</script>
<!-- 페이지 타이틀 -->
<div class="tit-area">
    <h1>
        <i class="el-icon-s-order" style="color: rgb(0, 108, 185);"></i>
        비밀번호 변경
    </h1>
</div>
<!-- // 페이지 타이틀 -->

<div class="formline">

    <!-- 사용자 목록, 사용자 정보 -->
    <div class="el-row">
        <div class="row2">
            <div class="section pdn mgn">
                <div class="el-card_header">
                    <h2>
                        <i class="el-icon-s-opportunity"></i>
                        비밀번호 변경 - 비밀번호는 8자 이상이어야 하며, 소문자/숫자/특수문자를 모두 포함해야 합니다.
                    </h2>
                </div>
                <div class="el-card_body">

                    <!-- 검색 -->
                    <div class="section bg-sky">
                    	<form name="formUsrPwdChg" id="formUsrPwdChg">
                        <table class="w-auto">
                            <tbody>
                                <tr>
                                    <th>현재 비밀 번호</th>
                                    <td>
                                        <input type="password" class="el-input__inner" style="width:100px" name="currPwd" id="currPwd">
                                    </td>
                                    <th>신규 비밀 번호</th>
                                    <td>
                                        <input type="password" class="el-input__inner" style="width:100px" name="newPwd1" id="newPwd1">
                                    </td>
                                    <th>신규 비밀 번호 확인</th>
                                    <td>
                                        <input type="password" class="el-input__inner" style="width:100px" name="newPwd2" id="newPwd2">
                                    </td>
                                    <th></th>
                                    <td>
                                        <button type="button" class="el-button el-button--primary el-button--small is-plain" style="margin-left: 8px;" onclick="javaScript:usrChgPwd();">
                                            <i class="el-icon-download"></i><span>저장</span>
                                        </button>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


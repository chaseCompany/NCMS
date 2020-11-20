<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script type="text/javaScript" language="javascript" defer="defer">
	$(document).ready(function(){
		
		<%-- 코드내용 상세조회 --%>
		sysAdminUsrView = function(tagUsrId){
			
			$.ajax({
				url : '/ajaxUsrView.do',
				type : 'POST',
				data : {
					usrId : tagUsrId
				},
				success : function(res){
					if(res.usrView != null){
						$("input[id='usrId']").val(res.usrView.USR_ID);
						$("input[id='usrNm']").val(res.usrView.USR_NM);
						$("select[id='siteCd']").val(res.usrView.SITE_CD);
						$("select[id='roleCd']").val(res.usrView.ROLE_CD);
						$("input[name='useYn']:radio[value='" + res.usrView.USE_YN + "']").prop("checked", true);
						$("input[id='regDt']").val(res.usrView.REG_DT);
					}else{
						console.log("상세내용 조회 오류");
					}
				},
				error : function(xhr, status){
					console.log(xhr);
				}
			});
		},
		usrUpdate = function(){

			$.ajax({
				url : '/ajaxUsrUpdate.do',
				type : 'POST',
				data : $('#formUsrUpdate').serialize(),
				success : function(res){
					if(res.err != "Y"){
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

		},
		sysAdminUsrSearch = function(){
			
			$.ajax({
				url : '/ajaxAdminUsrSearch.do',
				type : 'POST',
				data : $('#usrSearch').serialize(),
				success : function(res){
					console.log(res.usrSearchList);
				},
				error : function(xhr, status){
					console.log(xhr);
				}
			});
		},
		<%-- 초기화 --%>
		usrNew = function(){
			window.location.reload();
		}
	});
</script>
<!-- 페이지 타이틀 -->
<div class="tit-area">
    <h1>
        <i class="el-icon-s-order" style="color: rgb(0, 108, 185);"></i>
        사용자 관리
    </h1>
</div>
<!-- // 페이지 타이틀 -->

<div class="formline">
   
    <!-- 사용자 목록, 사용자 정보 -->
    <div class="el-row">
        <div class="row2">
            <div class="top-right-btn">
                <button type="button" onclick="javaScript:usrNew();" class="el-button el-button--primary el-button--small is-plain" style="margin-left: 8px;">
                    <i class="el-icon-refresh-right"></i><span>초기화</span>
                </button>
            </div>
            <div class="section pdn mgn">
                <div class="el-card_header">
                    <h2>
                        <i class="el-icon-s-opportunity"></i>
                        사용자 목록---
                    </h2>
                </div>
                <div class="el-card_body">

                    <!-- 검색 -->
                    <div class="section bg-sky">
                    	<form name="usrSearch" id="usrSearch">
                        <table class="w-auto">
                            <tbody>
                                <tr>
                                    <th>ID</th>
                                    <td>
                                        <input type="text" class="el-input__inner" style="width:100px" name="searchUsrId" id="searchUsrId">
                                    </td>
                                    <th>성명</th>
                                    <td>
                                        <input type="text" class="el-input__inner" style="width:100px" name="searchUsrNm" id="searchUsrNm">
                                    </td>
                                    <th>권한</th>
                                    <td>
                                        <select name="searchRoleCd" id="searchRoleCd" style="width:100px">
                                            <option value="0" selected="selected">선택</option>
<c:forEach var="result" items="${C3600}" varStatus="status">
											<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
</c:forEach>
                                        </select>
                                    </td>
                                    <th>소속 본부</th>
                                    <td>
                                        <select name="searchSiteCd" id="searchSiteCd" style="width:120px">
                                            <option value="0" selected="selected">선택</option>
<c:forEach var="result" items="${C3500}" varStatus="status">
											<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
</c:forEach>
                                        </select>
                                    </td>
                                    <th>사용 여부</th>
                                    <td>
                                        <select name="searchUseYn" id="searchUseYn" style="width:100px">
                                            <option value="0">선택</option>
                                            <option value="Y">예</option>
                                            <option value="N">아니요</option>
                                        </select>
                                        <button type="button" class="el-button el-button--primary el-button--small is-plain" style="margin-left: 8px;" onclick="javaScript:sysAdminUsrSearch()">
                                            <i class="el-icon-search"></i><span>검색</span>
                                        </button>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        </form>
                    </div>
                    <!-- // 검색 -->

                    <!-- 검색 결과 -->
                    <div class="table-box mgt10">
                        <div class="el-table_header-wrapper">
                            <table>
                                <colgroup>
                                    <col style="width:46px">
                                    <col style="width:120px">
                                    <col style="width:160px">
                                    <col style="width:200px">
                                    <col style="width:120px">
                                    <col style="width:80px">
                                    <col>
                                </colgroup>
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>사용자ID</th>
                                        <th>사용자명</th>
                                        <th>소속 본부</th>
                                        <th>사용 권한</th>
                                        <th>사용</th>
                                        <th>등록일시</th>
                                    </tr>
                                </thead>
                            </table>
                        </div>
                        <div class="el-table_body-wrapper" style="height: 540px;">
                            <table>
                                <colgroup>
                                    <col style="width:46px">
                                    <col style="width:120px">
                                    <col style="width:160px">
                                    <col style="width:200px">
                                    <col style="width:120px">
                                    <col style="width:80px">
                                    <col>
                                </colgroup>
                                <tbody id="usrGroup">
<c:forEach var="result" items="${usrList}" varStatus="status">
                                    <tr>
                                        <td>
                                            <div class="cell"><c:out value="${status.count}" /></div>
                                        </td>
                                        <td>
                                            <div class="cell">
                                                <a href="javascript:void(0);" onclick="javaScript:sysAdminUsrView('<c:out value="${result.USR_ID}" />');" class="row_link"><c:out value="${result.USR_ID}" /></a>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="cell"><c:out value="${result.USR_NM}" /></div>
                                        </td>
                                        <td>
                                            <div class="cell"><c:out value="${result.SITE_NM}" /></div>
                                        </td>
                                        <td>
                                            <div class="cell"><c:out value="${result.ROLE_NM}" /></div>
                                        </td>
                                        <td>
                                        	<c:if test="${result.USE_YN eq 'Y'}">
	                                            <div class="cell">예</div>
                                        	</c:if>
                                        	<c:if test="${result.USE_YN eq 'N'}">
	                                            <div class="cell">아니요</div>
                                        	</c:if>
                                        </td>
                                        <td>
                                            <div class="cell"><c:out value="${result.REG_DT}" /></div>
                                        </td>
                                    </tr>
</c:forEach>
                                </tbody>
                            </table>
                            <!-- <div class="no-data">조회된 데이터가 없습니다.</div> -->
                        </div>
                    </div>
                    <!-- // 검색 결과 -->

                </div>
            </div>
        </div>
        <div class="row2">
            <div class="top-right-btn">
                <button type="button" class="el-button el-button--primary el-button--small is-plain" onclick="javaScript:usrUpdate();">
                    <i class="el-icon-download"></i><span>저장</span>
                </button>
                <button type="button" class="el-button el-button--primary el-button--small is-plain">
                    <i class="el-icon-circle-plus-outline"></i><span>신규</span>
                </button>
                <button disabled="disabled" type="button" class="el-button el-button--primary el-button--small is-disabled is-plain">
                    <i class="el-icon-delete"></i><span>삭제</span>
                </button>
                <button type="button" class="el-button el-button--primary el-button--small is-plain">
                    <i class="el-icon-delete"></i><span>삭제</span>
                </button>
            </div>
            <div class="section pdn mgn" style="height: 707px;">
                <div class="el-card_header">
                    <h2>
                        <i class="el-icon-s-opportunity"></i>
                        사용자 정보
                    </h2>
                </div>
                <div class="el-card_body">
<form name="formUsrUpdate" id="formUsrUpdate">
                    <table class="w-auto wr-form">
                        <tbody>
                            <tr>
                                <th>
                                    <span class="required">*</span>
                                    사용자 ID
                                </th>
                                <td>
                                    <input type="text" class="el-input__inner v-md" readonly style="width: 200px;" placeholder="자동생성(u+지부코드+순번)" name="usrId" id="usrId">
                                    <span class="dsp ibk text">예시) u01001</span>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <span class="required">*</span>
                                    사용자 이름
                                </th>
                                <td>
                                    <input type="text" class="el-input__inner" style="width: 200px;" placeholder="사용자 이름" name="usrNm" id="usrNm">
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <span class="required">*</span>
                                    비밀번호
                                </th>
                                <td>
                                    <button disabled="disabled" type="button" class="el-button el-button--danger el-button--small is-disabled is-plain" style="padding: 9px 10px 8px; margin-left: 4px;" id="iInitializePwdDefault" >
                                        <i class="el-icon-refresh-left"></i><span>비밀번호 초기화</span>
                                    </button>
                                    <button type="button" class="el-button el-button--danger el-button--small is-plain" style="padding: 9px 10px 8px; margin-left: 4px;" id="iInitializePwdActive">
                                        <i class="el-icon-refresh-left"></i><span>비밀번호 초기화</span>
                                    </button>
                                    <div role="alert" class="el-alert el-alert--info is-light mgt10" style="width: 196px;">
                                        <i class="el-alert__icon el-icon-info"></i>
                                        <div class="el-alert__content">
                                            <span class="el-alert__title">초기 비밀번호는 12345</span>
                                            <i class="el-alert__closebtn el-icon-close" style="display: none;"></i>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <span class="required">*</span>
                                    소속 본부
                                </th>
                                <td>
                                    <select name="siteCd" id="siteCd" style="width:200px">
<c:forEach var="result" items="${C3500}" varStatus="status">
											<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
</c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <span class="required">*</span>
                                    사용자 권한
                                </th>
                                <td>
                                    <select name="roleCd" id="roleCd" style="width:200px">
<c:forEach var="result" items="${C3600}" varStatus="status">
											<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
</c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <span class="required">*</span>
                                    사용 여부
                                </th>
                                <td>
                                    <span class="ck-bx">
                                        <input type="radio" class="el-radio__original" value="Y" name="useYn" id="useYn1">
                                        <label for="ck1-1">
                                            <span class="el-radio__input"><span class="el-radio__inner"></span></span>
                                            예
                                        </label>
                                    </span>
                                    <span class="ck-bx">
                                        <input type="radio" class="el-radio__original" value="N" name="useYn" id="useYn2">
                                        <label for="ck1-2">
                                            <span class="el-radio__input"><span class="el-radio__inner"></span></span>
                                            아니요
                                        </label>
                                    </span>
                                </td>
                            </tr>
                            <tr>
                                <th>등록 일시</th>
                                <td>
                                    <input type="text" readonly class="el-input__inner" placeholder="등록 일시" style="width: 200px;" name="regDt" id="regDt">
                                </td>
                            </tr>
                        </tbody>
                    </table>
</form>
                </div>
            </div>
        </div>
    </div>
    <!-- 사용자 목록, 사용자 정보 -->

</div>

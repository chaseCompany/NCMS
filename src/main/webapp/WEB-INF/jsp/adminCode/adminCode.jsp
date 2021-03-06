<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javaScript" language="javascript" defer="defer">
	$(document).ready(function(){
		
		<%-- 코드내용 상세조회 --%>
		sysAdminCdView = function(tagGrpCd, tagCdId, tagCdNm, tagDpSeq, tagUseYn, tagCreDt){
			
			$("input[id='grpCd']").val(tagGrpCd);
			$("input[id='cdId']").val(tagCdId);
			$("input[id='cdNm']").val(tagCdNm);
			$("input[id='dpSeq']").val(tagDpSeq);
			$("select[id='useYn']").val(tagUseYn).prop("selected", true);
			
			$.ajax({
				url : '/ajaxAdminCodeList.do',
				type : 'POST',
				data : {
					cdId : tagCdId
				},
				success : function(res){
					if(res.codeList != null){
                    	var codeHtml = '';
                    	var index = 1;
                    	for (var i=0;i<res.codeList.length;i++) {
                        	codeHtml += '<tr>';
                        	codeHtml += '<td>';
                        	codeHtml += '<div class="cell">' + index + '</div>';
                        	codeHtml += '</td>';
                        	codeHtml += '<td>';
                        	codeHtml += '<div class="cell">' + res.codeList[i].CD_ID + '</div>';
                        	codeHtml += '</td>';
                        	codeHtml += '<td class="txt-left">';
                        	codeHtml += '<div class="cell">' + res.codeList[i].CD_NM + '</div>';
                        	codeHtml += '</td>';
                        	codeHtml += '<td>';
                        	codeHtml += '<div class="cell">' + res.codeList[i].DP_SEQ + '</div>';
                        	codeHtml += '</td>';
                        	codeHtml += '<td>';
                        	codeHtml += '<div class="cell">' + res.codeList[i].USE_YN + '</div>';
                        	codeHtml += '</td>';
                        	codeHtml += '</tr>';
                        	index++;
                    	}
                    	$("#codeDetailList").html(codeHtml);
					}else{
						console.log("상세내용 조회 오류");
					}
				},
				error : function(xhr, status){
					console.log(xhr);
				}
			});
		},
		codeSave = function(){

			$.ajax({
				url : '/ajaxCodeUpdate.do',
				type : 'POST',
				data : $('#codeForm').serialize(),
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
		sysAdminGroupCdSearch = function(){
			
			$.ajax({
				url : '/ajaxAdminCodeGroupSearch.do',
				type : 'POST',
				data : $('#groupCdSearch').serialize(),
				success : function(res){
					if(res.groupSearchList != null){
                    	var codeHtml = '';
                    	var index = 1;
                    	for (var i=0;i<res.groupSearchList.length;i++) {
                        	codeHtml += '<tr>';
                        	codeHtml += '<td>';
                        	codeHtml += '<div class="cell">' + index + '</div>';
                        	codeHtml += '</td>';
                        	codeHtml += '<td>';
                        	codeHtml += '<div class="cell"><a href="javascript:void(0);" onclick="javaScript:sysAdminCdView(\'' + res.groupSearchList[i].GRP_CD + '\', \'' + res.groupSearchList[i].CD_ID + '\', \'' + res.groupSearchList[i].CD_NM + '\', \'' + res.groupSearchList[i].DP_SEQ + '\', \'' + res.groupSearchList[i].USE_YN + '\');" class="row_link">' + res.groupSearchList[i].CD_ID + '</a></div>';
                        	codeHtml += '</td>';
                        	codeHtml += '<td class="txt-left">';
                        	codeHtml += '<div class="cell">' + res.groupSearchList[i].CD_NM + '</div>';
                        	codeHtml += '</td>';
                        	codeHtml += '<td>';
                        	codeHtml += '<div class="cell">' + res.groupSearchList[i].DP_SEQ + '</div>';
                        	codeHtml += '</td>';
                        	codeHtml += '<td>';
                        	codeHtml += '<div class="cell">' + res.groupSearchList[i].USE_YN + '</div>';
                        	codeHtml += '</td>';
                        	codeHtml += '</tr>';
                        	index++;
                    	}
                    	$("#codeGroup").html(codeHtml);
					}else{
						$("#codeGroup").html(codeHtml);
					}
				},
				error : function(xhr, status){
					console.log(xhr);
				}
			});
		},
		<%-- 초기화 --%>
		codeNew = function(){
			window.location.reload();
		}
	});
</script>
<!-- 페이지 타이틀 -->
<div class="tit-area">
    <h1>
        <i class="el-icon-s-order" style="color: rgb(0, 108, 185);"></i>
        코드 관리
    </h1>
</div>
<!-- // 페이지 타이틀 -->

<div class="formline">
   
    <!-- 코드 그룹, 코드 목록 -->
    <div class="el-row">
        <div class="row2">
            <div class="top-right-btn">
                <button type="button" onclick="javaScript:codeNew();" class="el-button el-button--primary el-button--small is-plain" style="margin-left: 8px;">
                    <i class="el-icon-refresh-right"></i><span>초기화</span>
                </button>
                <button type="button" class="el-button el-button--primary el-button--small is-plain" style="margin-left: 8px;">
                    <i class="el-icon-refresh-right"></i><span>엑셀업로드</span>
                </button>
            </div>
            <div class="section pdn mgn">
                <div class="el-card_header">
                    <h2>
                        <i class="el-icon-s-opportunity"></i>
                        코드 그룹
                    </h2>
                </div>
                <div class="el-card_body">

                    <!-- 검색 -->
                    <div class="section bg-sky">
                    	<form name="groupCdSearch" id="groupCdSearch">
                        <table class="w-auto">
                            <tbody>
                                <tr>
                                    <th>그룹ID</th>
                                    <td>
                                        <input type="text" class="el-input__inner" style="width:100px" name="groupCdSearchID" id="groupCdSearchID">
                                    </td>
                                    <th>그룹명</th>
                                    <td>
                                        <input type="text" class="el-input__inner" style="width:100px" name="groupCdSearchName" id="groupCdSearchName">
                                        <button type="button" class="el-button el-button--primary el-button--small is-plain" style="margin-left: 8px;" id="codeGroupSearch" onclick="javaScript:sysAdminGroupCdSearch()">
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
                                    <col style="width:567px">
                                    <col style="width:80px">
                                    <col>
                                </colgroup>
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>그룹ID</th>
                                        <th>그룹명</th>
                                        <th>정렬순서</th>
                                        <th>사용</th>
                                    </tr>
                                </thead>
                            </table>
                        </div>
                        <div class="el-table_body-wrapper" style="height: 360px;">
                            <table>
                                <colgroup>
                                    <col style="width:46px">
                                    <col style="width:120px">
                                    <col style="width:567px">
                                    <col style="width:80px">
                                    <col>
                                </colgroup>
                                <tbody id="codeGroup">
<c:forEach var="result" items="${grpCdList}" varStatus="status">
                                    <tr>
                                        <td>
                                            <div class="cell"><c:out value="${status.count}" /></div>
                                        </td>
                                        <td>
                                            <div class="cell">
                                                <a href="javascript:void(0);" onclick="javaScript:sysAdminCdView('<c:out value="${result.GRP_CD}" />', '<c:out value="${result.CD_ID}" />', '<c:out value="${result.CD_NM}" />', '<c:out value="${result.DP_SEQ}" />', '<c:out value="${result.USE_YN}" />');" class="row_link"><c:out value="${result.CD_ID}" /></a>
                                            </div>
                                        </td>
                                        <td class="txt-left">
                                            <div class="cell"><c:out value="${result.CD_NM}" /></div>
                                        </td>
                                        <td>
                                            <div class="cell"><c:out value="${result.DP_SEQ}" /></div>
                                        </td>
                                        <td>
                                            <div class="cell"><c:out value="${result.USE_YN}" /></div>
                                        </td>
                                    </tr>
</c:forEach>
                                </tbody>
                            </table>
                            <!-- <div class="no-data">조회된 데이터가 없습니다.</div> -->
                        </div>
                    </div>
                    <!-- // 검색 결과 -->
<form name="codeForm" id="codeForm">
                    <!-- 그룹정보 -->
                    <div class="section">
                        <table class="w-auto wr-form">
                            <tbody>
                                <tr>
                                    <th>
                                        <span class="required">*</span>
                                        그룹ID
                                    </th>
                                    <td>
	                                    <input type="hidden" name="grpCd" id="grpCd"/>
                                        <input type="text" class="el-input__inner" style="width:100px" name="cdId" id="cdId">
                                    </td>
                                    <th>
                                        <span class="required">*</span>
                                        그룹명
                                    </th>
                                    <td>
                                        <input type="text" class="el-input__inner" style="width:350px" name="cdNm" id="cdNm">
                                    </td>
                                </tr>
                                <tr>
                                    <th>
                                        <span class="required">*</span>
                                        순서
                                    </th>
                                    <td>
                                        <input type="text" class="el-input__inner" style="width:100px" name="dpSeq" id="dpSeq">
                                    </td>
                                    <th>
                                        <span class="required">*</span>
                                        사용여부
                                    </th>
                                    <td>
                                        <select name="useYn" id="useYn" style="width: 100px;">
                                        	<option value="Y">예</option>
                                        	<option value="N">아니오</option>
                                        </select>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="mgt10 txt-center">
                        <button type="button" class="el-button el-button--primary el-button--small is-plain" style="margin-left: 8px;" onclick="javaScript:codeSave();">
                            <i class="el-icon-refresh-right"></i><span>저장</span>
                        </button>
                    </div>
                    <!-- // 그룹정보 -->
</form>
                </div>
            </div>
        </div>
        <div class="row2">
            <div class="top-right-btn">
                <button type="button" onclick="javaScript:codeNew();" class="el-button el-button--primary el-button--small is-plain" style="margin-left: 8px;">
                    <i class="el-icon-refresh-right"></i><span>초기화</span>
                </button>
            </div>
            <div class="section pdn mgn">
                <div class="el-card_header">
                    <h2>
                        <i class="el-icon-s-opportunity"></i>
                        코드 목록
                    </h2>
                </div>
                <div class="el-card_body">

                    <!-- 검색 -->
                    <div class="section bg-sky">
                        <table class="w-auto">
                            <tbody>
                                <tr>
                                    <th>코드ID</th>
                                    <td>
                                        <input type="text" class="el-input__inner" style="width:100px">
                                    </td>
                                    <th>코드명</th>
                                    <td>
                                        <input type="text" class="el-input__inner" style="width:100px">
                                    </td>
                                    <th>사용여부</th>
                                    <td>
                                        <select name="" id="" style="width: 100px;">
                                            <option value="">선택</option>
                                        </select>
                                        <button type="button" class="el-button el-button--primary el-button--small is-plain" style="margin-left: 8px;">
                                            <i class="el-icon-search"></i><span>검색</span>
                                        </button>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <!-- // 검색 -->

                    <!-- 검색 결과 -->
                    <div class="table-box mgt10">
                        <div class="el-table_header-wrapper">
                            <table>
                                <colgroup>
                                    <col style="width:46px">
                                    <col style="width:120px">
                                    <col style="width:567px">
                                    <col style="width:80px">
                                    <col>
                                </colgroup>
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>코드ID</th>
                                        <th>코드명</th>
                                        <th>정렬순서</th>
                                        <th>사용</th>
                                    </tr>
                                </thead>
                            </table>
                        </div>
                        <div class="el-table_body-wrapper" style="height: 380px;">
                            <table>
                                <colgroup>
                                    <col style="width:46px">
                                    <col style="width:120px">
                                    <col style="width:567px">
                                    <col style="width:80px">
                                    <col>
                                </colgroup>
                                <tbody id="codeDetailList">
                                </tbody>
                            </table>
                            <!-- <div class="no-data">조회된 데이터가 없습니다.</div> -->
                        </div>
                    </div>
                    <!-- // 검색 결과 -->

                </div>
            </div>
        </div>
    </div>
    <!-- 코드 그룹, 코드 목록 -->

</div>


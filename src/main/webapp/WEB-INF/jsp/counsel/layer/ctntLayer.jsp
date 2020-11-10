<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="pop-header">
	<span>상세내용</span>
	<button type="button" class="el-dialog__headerbtn" onclick="layerPopupClose('counwrite')">
		<i class="el-dialog__close el-icon el-icon-close"></i>
	</button>
</div>
<div class="pop-content">
	<div class="section bg">
		<textarea name="" id="" style="height: 430px;" placeholder="상세내용을 입력하세요."></textarea>
	</div>
	<!-- 닫기 -->
	<div class="el-dialog__footer">
		<button type="button" class="el-button el-button--primary el-button--small is-plain">
			<i class="el-icon-check"></i>
			<span>확인</span>
		</button>
		<button type="button" onclick="layerPopupClose('counwrite')" class="el-button el-button--default el-button--small">
			<i class="el-icon-close"></i>
			<span>닫기</span>
		</button>
	</div>
	<!-- // 닫기 -->
</div>
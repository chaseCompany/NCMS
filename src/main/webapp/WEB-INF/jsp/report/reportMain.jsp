<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javaScript" language="javascript" defer="defer">
	$(document).ready(function(){
		$("input[name='fmDt']").datepicker('setDate', '-1M');
		$("input[name='toDt']").datepicker('setDate', 'today');
		<%-- 검색 기간 설정 --%>
		setDt = function(num){
			var thisDt = new Date($("input[name='toDt']").datepicker('getDate'));
			thisDt.setMonth(thisDt.getMonth() - num);
			$("input[name='fmDt']").datepicker('setDate', thisDt);
		}
	});
</script>
<!-- 페이지 타이틀 -->
<div class="tit-area">
	<h1><i class="el-icon-s-order" style="color: rgb(0, 108, 185);"></i> 통계관리</h1>
</div>
<!-- // 페이지 타이틀 -->
<div class="formline">
	<!-- 검색기간 -->
	<div class="section mgt5">
		<table class="w-auto">
			<tbody>
			<tr>
				<th><span class="required">*</span> 검색기간</th>
				<td>
					<div class="dat-pk">
						<i class="el-input__icon el-icon-date"></i>
						<input name="fmDt" type="text" class="el-input__inner datepicker" placeholder="날짜" style="width: 130px;">
					</div>
					<span>~</span>
					<div class="dat-pk">
						<i class="el-input__icon el-icon-date"></i>
						<input name="toDt" type="text" class="el-input__inner datepicker" placeholder="날짜" style="width: 130px;">
					</div>
					<div class="dsp-ibk">
						<button onclick="javaScript:setDt('1');" type="button" class="el-button el-button--default el-button--small is-plain" style="margin-left: 8px;">
							<span>1개월</span>
						</button>
						<button onclick="javaScript:setDt('2');" type="button" class="el-button el-button--default el-button--small is-plain" style="margin-left: 8px;">
							<span>2개월</span>
						</button>
						<button onclick="javaScript:setDt('3');" type="button" class="el-button el-button--default el-button--small is-plain" style="margin-left: 8px;">
							<span>3개월</span>
						</button>
						<button onclick="javaScript:setDt('6');" type="button" class="el-button el-button--default el-button--small is-plain" style="margin-left: 8px;">
							<span>6개월</span>
						</button>
						<button onclick="javaScript:setDt('12');" type="button" class="el-button el-button--default el-button--small is-plain" style="margin-left: 8px;">
							<span>1년</span>
						</button>
					</div>
				</td>
			</tr>
			</tbody>
		</table>
	</div>
	<!-- // 검색기간 -->
	<div class="section">
		<ul class="file-down-list">
			<li>
				<button onclick="javaScript:alert('준비중 입니다.');" type="button" class="el-button el-button--primary el-button--medium is-plain" style="padding: 15px 21px 14px;">
					<i class="el-icon-document"></i><span>접수상담대장</span>
				</button>
			</li>
			<li>
				<button onclick="javaScript:alert('준비중 입니다.');" type="button" class="el-button el-button--primary el-button--medium is-plain" style="padding: 15px 21px 14px;">
					<i class="el-icon-document"></i><span>인적정보내역</span>
				</button>
			</li>
			<li>
				<button onclick="javaScript:alert('준비중 입니다.');" type="button" class="el-button el-button--primary el-button--medium is-plain" style="padding: 15px 21px 14px;">
					<i class="el-icon-document"></i><span>개별상담내역</span>
				</button>
			</li>
		</ul>
	</div>
</div>
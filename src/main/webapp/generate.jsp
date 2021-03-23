<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--[if IE 9 ]><html lang="ko" class="ie9"><![endif]-->
<!--[if (gt IE 9)]><!--><html lang="ko"><!--<![endif]-->
<head>
	<meta charset="UTF-8">
	<title>한국마약퇴치운동본부</title>
	<link rel="shortcut icon" href="/images/favicon.ico" />
<link rel="stylesheet" href="/css/style.css" />
<link rel="stylesheet" href="/css/jquery/jquery-ui.min.css" />
<link rel="stylesheet" href="/css/timepicki.css" />
<script type="text/javascript" language="javascript" charset="utf-8" src="/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" language="javascript" src="/js/jquery-ui.js"></script>
<script type="text/javascript" language="javascript" src="/js/timepicki.js"></script>
<script type="text/javascript" language="javascript" src="/js/ui.js"></script>
<script type="text/javascript" language="javascript" charset="utf-8" src="/js/common.js"></script>
<script type="text/javaScript" language="javascript" defer="defer">
	$(document).ready(function(){
		$('.generate').each(function(index, item){
			var tagName = $(this).attr("gen-id");
			var tagType = $(this).attr("gen-type");
			var grpCd = $(this).attr("gen-cond1");
			var defaultValue = $(this).attr("gen-default-value");
		    var defaultText = $(this).attr("gen-default-text");
			var selectValue = $(this).attr("gen-select-value");			
			$.ajax({
				type: 'POST',
				url: '<c:url value="/nrds/SysCdList.do"/>',
				data : { 'grpCd' : grpCd },
				//async: false,
				dataType: "json",
				beforeSend:function(){
					if(tagType=='select')
					{
						$("span[gen-id='"+tagName+"']").html("<select><option>로딩중...</option></select>");
					}
					else
					{
						$("span[gen-id='"+tagName+"']").html("로딩중...");
					}
			    }, 
				success: function(data){
					var s = "";
					if(data.RESULT_LIST.length>0){					
						if(defaultValue==undefined) defaultValue = "";
						if(defaultText==undefined) defaultText = "선택";
						if(selectValue==undefined) selectValue = "";
						console.log("select : defaultValue=",defaultValue,"defaultText=",defaultText,"selectValue=",selectValue);						
						if(tagType == 'select')
						{							
							$("span[gen-id='"+tagName+"']").html("<select name='"+tagName+"' id='"+tagName+"'></select>");
							if(defaultValue != null){
								s += "<option value='" + defaultValue + "'>"+defaultText+"</option>";
							}							
							for(var i=0; i < data.RESULT_LIST.length; i++){								
								s += "<option value='"+data.RESULT_LIST[i].CD_ID+"'>"+data.RESULT_LIST[i].CD_NM+"</option>";
							}
							$("select[id="+tagName+"]").append(s);
							$("select[id="+tagName+"] option[value='"+selectValue+"']").prop('selected', true);
						}
						else if(tagType == 'radio')
						{							
							if(selectValue==undefined) selectValue = "";
							console.log("radio : selectValue=",selectValue);
														
							for(var i=0; i < data.RESULT_LIST.length; i++){
								s += "<span class='ck-bx'>";
								s += "<input type='radio' name='"+tagName+"' id='"+tagName+""+i+"' class='el-radio__original' value='"+data.RESULT_LIST[i].CD_ID+"'/>";
								s += "<label for='"+tagName+""+i+"'><span class='el-radio__input'><span class='el-radio__inner'></span></span>" + data.RESULT_LIST[i].CD_NM + "</label>";
								s += "</span>";
							}
							$("span[gen-id='"+tagName+"']").html(s);
							$("input:radio[name='"+tagName+"'][value='"+selectValue+"']").prop('checked', true);
						}
						else if(tagType == 'checkbox')
						{							
							if(selectValue==undefined) selectValue = "";
							console.log("checkbox : selectValue=",selectValue);
														
							for(var i=0; i < data.RESULT_LIST.length; i++){
								s += "<input type='checkbox' name='"+tagName+"' id='"+tagName+""+i+"' value='"+data.RESULT_LIST[i].CD_ID+"'/>";
								s += "&nbsp;" + data.RESULT_LIST[i].CD_NM + " &nbsp; ";
							}
							$("span[gen-id='"+tagName+"']").html(s);
							var selectArr = selectValue.split(',');
							for(var i in selectArr){
								$("input:checkbox[name='"+tagName+"'][value='"+selectArr[i]+"']").prop('checked', true);
							}
						} 						
					}
				}
					
			});
		});
	});
</script>		
<style>
.tarea {
	width:900px;height:30px;overflow:hidden;background-color:#ddd;
}
</style>
</head>
<body>

<h2><i class="el-icon-s-opportunity"></i> 코드 생성 샘플</h2>
<br/><br/>

<h3><i class="el-icon-s-opportunity"></i> SELECT-디폴트없음,미선택</h3>
<span class="generate" gen-id="sel01" gen-type="select" gen-cond1="C1200"></span>
<textarea class="tarea">
<span class="generate" gen-id="sel01" gen-type="select" gen-cond1="C1200"></span>
</textarea>
<br/><br/>

<h3><i class="el-icon-s-opportunity"></i> SELECT-디폴트지정,미선택</h3>
<span class="generate" gen-id="sel02" gen-type="select" gen-cond1="C1200" gen-default-value="" gen-default-text="선택하세요"></span>
<textarea class="tarea">
<span class="generate" gen-id="sel02" gen-type="select" gen-cond1="C1200" gen-default-value="" gen-default-text="선택하세요"></span>
</textarea>
<br/><br/>

<h3><i class="el-icon-s-opportunity"></i> SELECT-디폴트지정,선택</h3>
<span class="generate" gen-id="sel03" gen-type="select" gen-cond1="C0000" gen-default-value="" gen-default-text="선택해주세요" gen-select-value="C8300"></span>
<textarea class="tarea">
<span class="generate" gen-id="sel03" gen-type="select" gen-cond1="C0000" gen-default-value="" gen-default-text="선택해주세요" gen-select-value="C8300"></span>
</textarea>
<br/><br/>

<h3><i class="el-icon-s-opportunity"></i> RADIO-미선택</h3>
<span class="generate" gen-id="rad01" gen-type="radio" gen-cond1="C1600"></span>
<textarea class="tarea">
<span class="generate" gen-id="rad01" gen-type="radio" gen-cond1="C1600"></span>
</textarea>
<br/><br/>

<h3><i class="el-icon-s-opportunity"></i> RADIO-선택</h3>
<span class="generate" gen-id="rad02" gen-type="radio" gen-cond1="C1600" gen-select-value="30"></span>
<textarea class="tarea">
<span class="generate" gen-id="rad02" gen-type="radio" gen-cond1="C1600" gen-select-value="30"></span>
</textarea>
<br/><br/>

<h3><i class="el-icon-s-opportunity"></i> CHECKBOX-미선택</h3>
<span class="generate" gen-id="chk01" gen-type="checkbox" gen-cond1="C1600"></span>
<textarea class="tarea">
<span class="generate" gen-id="chk01" gen-type="checkbox" gen-cond1="C1600"></span>
</textarea>
<br/><br/>

<h3><i class="el-icon-s-opportunity"></i> CHECKBOX-1개선택</h3>
<span class="generate" gen-id="chk02" gen-type="checkbox" gen-cond1="C1600" gen-select-value="30"></span>
<textarea class="tarea">
<span class="generate" gen-id="chk02" gen-type="checkbox" gen-cond1="C1600" gen-select-value="30"></span>
</textarea>
<br/><br/>

<h3><i class="el-icon-s-opportunity"></i> CHECKBOX-다중선택</h3>
<span class="generate" gen-id="chk03" gen-type="checkbox" gen-cond1="C1600" gen-select-value="30,10,50"></span>
<textarea class="tarea">
<span class="generate" gen-id="chk03" gen-type="checkbox" gen-cond1="C1600" gen-select-value="30,10,50"></span>
</textarea>
<br/><br/>


<br/><br/>

</textarea>
</body>
</html>
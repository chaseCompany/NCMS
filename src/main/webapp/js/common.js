/**
 * 문자열을 변환한다.
 * @param str 원본 문자열
 * @param org 찾을 문자열
 * @param rep 바꿀 문자열
 * @returns
 */
function gfncReplaceAll(str, org, rep){
	return str.split(org).join(rep);
}
/**
 * 날짜형식을 체크한다.
 * @param dateValue 입력문자열
 * @returns {Boolean}
 */
function gfncDateFormatCheck(dateValue) {
	var tmp = gfncReplaceAll(dateValue, "/", "");
	if(tmp.length != 8){
		return false;
	}
	var pattern = /\d{4}\/\d{2}\/\d{2}/;
	return pattern.test(dateValue);
}
/**
 * 시간형식(HH:MM: 24시간)을 체크한다.
 * @param dateValue
 * @returns
 */
function gfncTimeFormatCheck(dateValue) {
	var tmp = gfncReplaceAll(dateValue, ":", "");
	if(tmp.length != 4){
		return false;
	}
	var hh = tmp.substring(0,2);
	if(parseInt(hh, 10) < 0 || parseInt(hh, 10) >= 24){
		return false;
	}
	var mm = tmp.substring(2,4);
	if(parseInt(mm, 10) < 0 || parseInt(mm, 10) >= 60){
		return false;
	}
	return true;
}
/**
 * 날짜 포맷
 * @param date
 * @returns
 */
function formatDate(date) {
	if(date == "" || date == undefined){
		return "";
	}
	if(date.length != 8){
		return date;
	}

	var d = new Date(date.substr(0, 4), parseInt(date.substr(4, 2)) - 1, date.substr(6));
	var month = '' + (d.getMonth() + 1);
	var day = '' + d.getDate();
	var year = d.getFullYear();

	if (month.length < 2) month = '0' + month;
	if (day.length < 2) day = '0' + day;

	return [year, month, day].join('-');
};
/**
 * 시간 포맷
 */
function formatTime(time){
	if(time.length != 4){
		return time;
	}

	return [time.substr(0, 2), time.substr(2, 2)].join(':');
}
/**
 * 두 시간의 분차이 계산
 * @param fmTm 00:00
 * @param toTm 00:00
 */
function needTime(fmTm, toTm){
	if(fmTm.length != 5){
		return 0;
	}
	if(toTm.length != 5){
		return 0;
	}
	if(fmTm.indexOf(":") < 0){
		return 0;
	}
	if(toTm.indexOf(":") < 0){
		return 0;
	}
	if(toTm.replace(":") <= fmTm.replace(":")){
		return 0;
	}

	var fmTmVal = fmTm.split(":");
	var toTmVal = toTm.split(":");

	var termTm = ((toTmVal[0] - fmTmVal[0]) * 60) + (toTmVal[1] - fmTmVal[1]);
	if(termTm > 0){
		return termTm;
	}else{
		return 0;
	}
}
/**
 * 타겟의 값에 val 값을 더한다.
 * @param tagName
 * @param val
 */
function tagNumChang(tagName, val){
	var num = Number($("input[name='" + tagName + "']").val()) + val;

	if($("input[name='" + tagName + "']").attr("maxVal") != undefined){
		if(Number($("input[name='" + tagName + "']").attr("maxVal")) < num){
			num = $("input[name='" + tagName + "']").attr("maxVal");
		}
	}
	if($("input[name='" + tagName + "']").attr("minVal") != undefined){
		if(Number($("input[name='" + tagName + "']").attr("minVal")) > num){
			num = $("input[name='" + tagName + "']").attr("minVal");
		}
	}

	$("input[name='" + tagName + "']").val(num);
}
/**
 * 객체의 값이 ZZZ일 경우 타겟 입력 활성화
 * @param obj
 * @param tagName
 */
function inputDisabledChang(obj, tagName){
	if($(obj).val().indexOf("ZZ") >= 0){
		$("input[name='" + tagName + "']").attr("disabled", false);
	}else{
		$("input[name='" + tagName + "']").val("");
		$("input[name='" + tagName + "']").attr("disabled", true);
	}
}

function nl2br(str, is_xhtml){
	var breakTag = (is_xhtml || typeof is_xhtml === 'undefined') ? '<br />' : '<br>';
	return (str + '').replace(/([^>\r\n]?)(\r\n|\n\r|\r|\n)/g, '$1'+ breakTag +'$2');
}
/**
 * 파일 다운로드
 */
function downloadFile(tagId, tagSeq){
	window.open("/fileDown.do?fileId=" + tagId + "&fileSeq=" + tagSeq);
}
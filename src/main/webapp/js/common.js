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
		return false
	}
	var mm = tmp.substring(2,4);
	if(parseInt(mm, 10) < 0 || parseInt(mm, 10) >= 60){
		return false
	}
	return true;
}
/**
 * 날짜 포맷
 * @param date
 * @returns
 */
function formatDate(date) {
	if(date.length != 8){
		return "";
	}

	var d = new Date(date.substr(0, 4), date.substr(4, 2), date.substr(6)), 
		month = '' + (d.getMonth() + 1), 
		day = '' + d.getDate(), 
		year = d.getFullYear();

	if (month.length < 2) month = '0' + month;
	if (day.length < 2) day = '0' + day;

	return [year, month, day].join('-');
};
/**
 * 시간 포맷
 */
function formatTime(time){
	if(time.length != 4){
		return "";
	}

	return [time.substr(0, 2), time.substr(2, 2)].join(':');
}
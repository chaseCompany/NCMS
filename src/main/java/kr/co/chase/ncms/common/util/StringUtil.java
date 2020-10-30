package kr.co.chase.ncms.common.util;


public class StringUtil {

	/**
	 * null 체크
	 *
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str) {
		if(str == null || "null".equals(str) || "".equals(str))
			return true;
		else
			return false;
	}
	public static boolean isNull(Object obj) {
		if(obj == null)
			return true;
		else
			return false;
	}

	/**
	 * null 또는 공백일때 def 리턴 아닐때 str 리턴
	 *
	 * @param str
	 * @param def
	 * @return
	 */
	public static String nvl(String str, String def) {
		if(isNull(str))
			return def;
		else
			return str;
	}
	public static int nvl(String str, int def) {
		if(isNull(str))
			return def;
		else
			return Integer.parseInt(str);
	}
	public static Object nvl(Object obj, Object def) {
		if(isNull(obj))
			return def;
		else
			return obj;
	}

	/**
	 * 날짜형식의 문자열에서 년도 리턴
	 *
	 * @param str
	 * @return
	 */
	public static String getYear(String str) {
		if(StringUtil.isNull(str)) return "";
		str = getOnlyNumber(str);
		return str.length() >= 4 ? str.substring(0,4) : "";
	}

	/**
	 * 날짜형식의 문자열에서 월 리턴
	 *
	 * @param str
	 * @return
	 */
	public static String getMonth(String str) {
		if(StringUtil.isNull(str)) return "";
		str = getOnlyNumber(str);
		return str.length() >= 6 ? str.substring(4,6) : "";
	}

	/**
	 * 날짜형식의 문자열에서 일 리턴
	 *
	 * @param str
	 * @return
	 */
	public static String getDate(String str) {
		if(StringUtil.isNull(str)) return "";
		str = getOnlyNumber(str);
		return str.length() >= 8 ? str.substring(6,8) : "";
	}

	/**
	 * 숫자만 추출
	 *
	 * @param str
	 * @return
	 */
	public static String getOnlyNumber(String str) {
		if(StringUtil.isNull(str)) return "";
		return str.replaceAll("[^0-9]", "");
	}
	
	
	public static String spliteConnectBar(String str){
		if(StringUtil.isNull(str)) return "";
		String str1 = str.substring(0, 4);
		String str2 = str.substring(4, 8);
		String str3 = str.substring(8, str.length());

		return str1+"-"+str2+"-"+str3;
	}
	
	/**
	 * 문자열이 비었을때.
	 * @param p
	 * @return
	 */
	public static boolean isEmptyObj( Object p ){
        return (p == null || p.toString().trim().equals(""));
    }
}

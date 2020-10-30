package kr.co.chase.ncms.common.util;

import javax.servlet.http.HttpServletRequest;

public abstract class StringUtils {
	
	
	 public static String ReplaceTag(String Expression, String type){
	        String result = "";
	        if (Expression==null || Expression.equals("")) return "";

	        if (type.equals("encode")) {
	            result = ReplaceString(Expression, "&","&amp;");
	            result = ReplaceString(result, "\"", "&quot;");
	    
	            result = ReplaceString(result, "'", "&apos;");
	            result = ReplaceString(result, "<", "&lt;");
	            result = ReplaceString(result, ">", "&gt;");
	            result = ReplaceString(result, "\r", "<br>");
	            result = ReplaceString(result, "\n", "<p>");
	            result = ReplaceString(result, "\n", "\\(");
	            result = ReplaceString(result, "\n", "<p>");
	        }
	        else if (type.equals("decode")) {
	            result = ReplaceString(Expression,"&amp;", "&");
	            /*result = ReplaceString(result, "&quot;", "\"");*/
	            result = ReplaceString(result, "&quot;", "'");
	    
	            result = ReplaceString(result, "&apos;", "'");
	            result = ReplaceString(result, "&lt;", "<");
	            result = ReplaceString(result, "&gt;", ">");
//	            result = ReplaceString(result, "<br>", "\r");
//	            result = ReplaceString(result, "<p>", "\n");        
	        }
	        else if (type.equals("etc")) {
	            result = ReplaceString(Expression, "&","&amp;");
	            result = ReplaceString(result, "\"", "&quot;");
	    
	            result = ReplaceString(result, "'", "&apos;");
	            result = ReplaceString(result, "<", "&lt;");
	            result = ReplaceString(result, ">", "&gt;");
	            result = ReplaceString(result, "\r", "<br>");
	            result = ReplaceString(result, "\n", "<p>");
	            result = ReplaceString(result, "\n", "\\(");
	            result = ReplaceString(result, "\n", "<p>");
	                   
	        	
	        	result = ReplaceString(result, " ", "&nbsp;");  
	        	result = ReplaceString(result, "\t ", "&nbsp;&nbsp;&nbsp;&nbsp;");
	            
	        }
	        else if (type.equals("etcExcel")) {
	        	result = ReplaceString(Expression, "&amp;","&");
	            result = ReplaceString(result, "<br>", "\r\n");
	            result = ReplaceString(result, "<p>", "\n");
	        	result = ReplaceString(result, "&nbsp;", " ");  
	            
	        }
	        return result;  
	    }

    public static String ReplaceString(String Expression, String Pattern, String Rep)
    {
        if (Expression==null || Expression.equals("")) return "";

        int s = 0;
        int e = 0;
        StringBuffer result = new StringBuffer();

        while ((e = Expression.indexOf(Pattern, s)) >= 0) {
            result.append(Expression.substring(s, e));
            result.append(Rep);
            s = e + Pattern.length();
        }
        result.append(Expression.substring(s));
        return result.toString();
    }
    
	public static String cleanXSS(String value) {      
		  if(value == null) return "";
		  value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");         
		  
		  //value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "&#41;");         
		  
		  value = value.replaceAll("'", "&#39;");
		  
		  value = value.replaceAll("\"", "&quot;");
		  
		  value = value.replaceAll("eval\\((.*)\\)", "");         
		  
		  value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");         
		  
		  value = value.replaceAll("script", "");         
		  
		  return value;     
		  
	} 
	
	
	public static String txtAreacleanXSS(String value) {      
		  
		  value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");         
		  
		  //value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "&#41;");         
		  
		  value = value.replaceAll("'", "&#39;");
		  
		  value = value.replaceAll("\"", "&quot;");
		  
		  value = value.replaceAll("eval\\((.*)\\)", "");         
		  
		  value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");         
		  
		 // value = value.replaceAll("script", "");         
		  
		  return value;     
		  
	} 
	
	public static String getSmartEditXSS(String value){
		
		//String contentstag = ReplaceTag(value, "decode"); // 치환
		String contents = cleanXSS(value); // script 삭제
		//contents = ReplaceTag(contents, "encode"); // 치환
		return contents;
	}
	
	public static boolean IsKorean(char ch)
	{
	    //( 한글자 || 자음 , 모음 )
	    if ((0xAC00 <= ch && ch <= 0xD7A3) || (0x3131 <= ch && ch <= 0x318E))
	        return true;
	    else
	        return false;
	}
	
	// 웹방화벽 설치 후 Client IP 가져오기 위한 처리
	public static String getWAFRemoteAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("Proxy-Client-IP"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("WL-Proxy-Client-IP"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("HTTP_CLIENT_IP"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getRemoteAddr(); 
        }
        return ip;
    }

	
}

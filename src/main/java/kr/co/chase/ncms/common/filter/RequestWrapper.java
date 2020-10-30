package kr.co.chase.ncms.common.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class RequestWrapper extends HttpServletRequestWrapper {
	 
	public RequestWrapper(HttpServletRequest servletRequest) {         
		super(servletRequest);     
	}     

	@Override
	public String[] getParameterValues(String parameter) {       
		String[] values = super.getParameterValues(parameter);       
		if (values==null)  {                  
			return null;          
		}      
		int count = values.length;      
		String[] encodedValues = new String[count];      
		for (int i = 0; i < count; i++) {                
			encodedValues[i] = cleanXSS(values[i]);        
		}       
		return encodedValues;    
	}     
  
	@Override
	public String getParameter(String parameter) {           
		String value = super.getParameter(parameter);           
		if (value == null) {                  
			return null;                   
		}           
		return cleanXSS(value);     
	}     
 
	@Override
	public String getHeader(String name) {         
		String value = super.getHeader(name);         
		if (value == null) {
			return null;         
		}
		return cleanXSS(value);     
	}  

	private String cleanXSS(String value) {  
		String returnValue = "";
		if (value == null || "".equals(value.trim())) {
			returnValue = "";
		}else{
			returnValue = value;
		}
		
		returnValue = returnValue.replaceAll("&", "&amp;");
		returnValue = returnValue.replaceAll("<", "&lt;");
		returnValue = returnValue.replaceAll(">", "&gt;");
		returnValue = returnValue.replaceAll("\"", "&#34;");
		returnValue = returnValue.replaceAll("\'", "&#39;");
		
		returnValue = returnValue.replaceAll("eval\\((.*)\\)", "");
		returnValue = returnValue.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
		 
		return returnValue;
	}
}
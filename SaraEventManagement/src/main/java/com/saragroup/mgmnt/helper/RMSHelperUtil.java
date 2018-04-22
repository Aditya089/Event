package com.saragroup.mgmnt.helper;

import javax.servlet.http.HttpServletRequest;

public class RMSHelperUtil {

	public static final String EMPTY = "";
	public static String getRequestParamValue(HttpServletRequest request,String varName) {
		String val="";
		if(request.getParameter(varName)!=null && !"".equalsIgnoreCase(request.getParameter(varName))){
			
			val=request.getParameter(varName);
		}
		return val;
	}
}

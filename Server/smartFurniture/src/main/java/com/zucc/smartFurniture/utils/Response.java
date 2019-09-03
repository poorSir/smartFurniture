package com.zucc.smartFurniture.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Response {
	public static void responseStr(HttpServletResponse response,String str){
		response.setContentType("application/json; charset=utf-8");
		response.setCharacterEncoding("UTF-8");   
		PrintWriter out;
		try {
			out = response.getWriter();
			out.write(str);  
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

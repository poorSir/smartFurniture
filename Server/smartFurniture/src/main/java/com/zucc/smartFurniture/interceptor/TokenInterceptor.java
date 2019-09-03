package com.zucc.smartFurniture.interceptor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zucc.smartFurniture.pojo.HttpResult;
import com.zucc.smartFurniture.service.UserServiceImpl;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.springframework.aop.interceptor.AbstractMonitoringInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class TokenInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//token检查
		Gson gson = new GsonBuilder().create();
		response.setContentType("application/json; charset=utf-8");
		response.setCharacterEncoding("UTF-8");   
		PrintWriter out;
		String token = request.getParameter("token");
		if(request.getParameter("userId") == null){
			return false;
		}
		int id = Integer.valueOf(request.getParameter("userId")).intValue();
		UserServiceImpl userService = new UserServiceImpl();
		HttpResult result = userService.checkToken(id, token);
		if(result.getCode().equals("200")) {
			return true;
		}else {
			out = response.getWriter();
			out.write(gson.toJson(result));  
			out.flush();
		}
		return false;
	}
}

package com.zucc.smartFurniture.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zucc.smartFurniture.pojo.HttpResult;
import com.zucc.smartFurniture.service.UserService;
import com.zucc.smartFurniture.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;


@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;

	/***
	 * 用户注册
	 * @param request
	 * @param response
	 */
	@RequestMapping("/register")
	public void register(HttpServletRequest request,HttpServletResponse response) {
		Gson gson = new GsonBuilder().create();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		HttpResult result = userService.register(username, password);
		Response.responseStr(response, gson.toJson(result));
	}

	/**
	 * 用户登录
	 * @param request
	 * @param response
	 */
	@RequestMapping("/login")
	public void login(HttpServletRequest request,HttpServletResponse response) {
		Gson gson = new GsonBuilder().create();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		HttpResult result = userService.login(username, password);
		Response.responseStr(response, gson.toJson(result));
	}

	/**
	 * 忘记用户密码
	 * @param request
	 * @param response
	 */
	@RequestMapping("/forgetPassword")
	public void forgetPassword(HttpServletRequest request,HttpServletResponse response) {
		Gson gson = new GsonBuilder().create();
		String username = request.getParameter("username");
		HttpResult result = userService.forgetPassword(username);
		Response.responseStr(response, gson.toJson(result));
	}

	/**
	 * 修改用户密码
	 * @param request
	 * @param response
	 */
	@RequestMapping("/changePassword")
	public void changePassword(HttpServletRequest request,HttpServletResponse response) {
		Gson gson = new GsonBuilder().create();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		HttpResult result = userService.changePassword(username,password);
		Response.responseStr(response, gson.toJson(result));
	}
	
}

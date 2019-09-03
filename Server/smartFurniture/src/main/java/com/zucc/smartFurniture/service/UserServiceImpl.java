package com.zucc.smartFurniture.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.zucc.smartFurniture.common.ReturnMsg;
import com.zucc.smartFurniture.interceptor.TokenInterceptor;
import com.zucc.smartFurniture.mapper.UserMapper;
import com.zucc.smartFurniture.pojo.HttpResult;
import com.zucc.smartFurniture.pojo.User;
import com.zucc.smartFurniture.utils.MD5Util;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserMapper userMapper;
	/**
	 * 登录
	 */
	@Override
	public HttpResult login(String username,String password) {
		User user =userMapper.queryByName(username);
		HttpResult result=new HttpResult();
		if(user == null) {
			result.setCode("201");
			result.setMsg(ReturnMsg.Status_201);
		}else if(user.getPassword().equals(password)){
			Gson gson = new GsonBuilder().create();
			result.setCode("200");
			result.setMsg(ReturnMsg.Status_200);
			JsonObject jsonObject=new JsonObject();
			jsonObject.addProperty("token",user.getToken());
			jsonObject.addProperty("userId",user.getId());
			result.setData(jsonObject);
		}else {
			result.setCode("202");
			result.setMsg(ReturnMsg.Status_202);
		}
		return result;
	}
	/***
	 * 注册
	 */
	@Override
	public HttpResult register(String name, String password) {
		HttpResult result = new HttpResult();
		User user = userMapper.queryByName(name);
		if(user!=null) {
			result.setCode("205");
			result.setMsg(ReturnMsg.Status_205);
		}else {
			user = new User();
			user.setUsername(name);
			user.setPassword(password);
			String token = MD5Util.md5(name+System.currentTimeMillis()+"");
			user.setToken(token);
			int a = userMapper.user_add(user);
			if(a != 1){
				result.setCode("206");
				result.setMsg(ReturnMsg.Status_206);
			}else{
				result.setCode("200");
				result.setMsg(ReturnMsg.Status_200);
				JsonObject obj =new JsonObject();
				User queryUser = userMapper.queryByName(name);
				obj.addProperty("token", token);
				obj.addProperty("userId", queryUser.getId());
				result.setData(obj);
			}
		}
		return result;
	}
	/**
	 * token检查
	 */
	@Override
	public HttpResult checkToken(int id,String token) {
		HttpResult result = new HttpResult();
		User user = userMapper.queryById(id);
		if(user == null) {
			result.setCode("201");
			result.setMsg(ReturnMsg.Status_201);
		}else if(!user.getToken().equals(token)) {
			result.setCode("204");
			result.setMsg(ReturnMsg.Status_204);
		}else {
			result.setCode("200");
			result.setMsg(ReturnMsg.Status_200);
		}
		return result;
	}
	/**
	 * 忘记密码
	 */
	@Override
	public HttpResult forgetPassword(String username) {
		HttpResult result = new HttpResult();
		User user = userMapper.queryByName(username);
		if(user == null) {
			result.setCode("201");
			result.setMsg(ReturnMsg.Status_201);
		}else {
			result.setCode("200");
			result.setMsg(ReturnMsg.Status_200);
			JsonObject obj =new JsonObject();
			obj.addProperty("token", user.getToken());
			obj.addProperty("userId", user.getId());
			result.setData(obj);
		}
		return result;
	}
	/***
	 * 修改密码
	 */
	@Override
	public HttpResult changePassword(String username, String password) {
		HttpResult result = new HttpResult();
		User user =new User();
		user.setUsername(username);
		user.setPassword(password);
		int a = userMapper.updatePassword(user);
		if(a != 1) {
			result.setCode("201");
			result.setMsg(ReturnMsg.Status_201);
		}else {
			result.setCode("200");
			result.setMsg(ReturnMsg.Status_200);
		}
		return result;
	}
	
}

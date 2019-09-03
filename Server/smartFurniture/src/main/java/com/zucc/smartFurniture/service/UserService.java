package com.zucc.smartFurniture.service;

import com.zucc.smartFurniture.pojo.HttpResult;

public interface UserService {
	/**用户注册*/
	HttpResult register(String name, String password);
	/**用户登录*/
	HttpResult login(String name, String password);
	/**token检查*/
	HttpResult checkToken(int id, String token);
	/**忘记密码*/
	HttpResult forgetPassword(String username);
	/**修改密码*/
	HttpResult changePassword(String username, String password);
}

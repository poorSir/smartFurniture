package com.zucc.smartfurniture.network.api;

import com.zucc.smartfurniture.module.user.model.rec.TokenRec;
import com.zucc.smartfurniture.network.HttpResult;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/10/25$ 16:39$
 * <p/>
 */
public interface UserService {
    /**登录*/
    @FormUrlEncoded
    @POST("user/login")
    Observable<HttpResult<TokenRec>> userLogin(@Field("username") String username,@Field("password") String password);

    /**注册*/
    @FormUrlEncoded
    @POST("user/register")
    Observable<HttpResult<TokenRec>> userRegister(@Field("username") String username,@Field("password") String password);

    /**忘记密码*/
    @FormUrlEncoded
    @POST("user/forgetPassword")
    Observable<HttpResult<TokenRec>> userForgetPassword(@Field("username") String username);

    /**修改密码*/
    @FormUrlEncoded
    @POST("user/changePassword")
    Observable<HttpResult<TokenRec>> userChangePassword(@Field("username") String username,@Field("password") String password);
}

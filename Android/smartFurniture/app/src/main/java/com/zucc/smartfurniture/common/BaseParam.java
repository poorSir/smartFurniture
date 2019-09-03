package com.zucc.smartfurniture.common;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/10/30$ 13:39$
 * <p/>
 */
public class BaseParam {
    //sharePreferences
    public static final String fileName = "smartFurniture";
    public static final String username = "username";
    public static final String userId = "userId";
    public static final String token ="token";
    public static final String cusId = "cusId";
    public static final String isLogin = "isLogin";
    public static final String deviceId = "deviceId";

    //ARouter Param
    public static final String type="type";
    //requestcode
    /**登录-注册-请求码*/
    public static final int login_register_request = 1;
    /**登录-忘记密码-请求码*/
    public static final int login_forget_request = 2;
    /**忘记密码-修改密码-请求码*/
    public static final int forget_change_request = 3;
    /**遥控-设备场景-请求码*/
    public static final int control_scene_request = 4;
    /**百度语音回调*/
    public static final int baidu_voice_request = 5;
}

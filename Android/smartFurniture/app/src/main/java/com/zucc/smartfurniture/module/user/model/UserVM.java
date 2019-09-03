package com.zucc.smartfurniture.module.user.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.zucc.smartfurniture.BR;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/10/25$ 16:58$
 * <p/>
 */
public class UserVM extends BaseObservable{
    /**用户名*/
    private String username;
    /**密码*/
    private String password;
    /**验证码*/
    private String identitfy_code;
    /**确认密码*/
    private String comfirmPassword;
    @Bindable
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        notifyPropertyChanged(BR.username);
    }
    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }
    @Bindable
    public String getIdentitfy_code() {
        return identitfy_code;
    }

    public void setIdentitfy_code(String identitfy_code) {
        this.identitfy_code = identitfy_code;
        notifyPropertyChanged(BR.identitfy_code);
    }
    @Bindable
    public String getComfirmPassword() {
        return comfirmPassword;
    }

    public void setComfirmPassword(String comfirmPassword) {
        this.comfirmPassword = comfirmPassword;
        notifyPropertyChanged(BR.comfirmPassword);
    }
}

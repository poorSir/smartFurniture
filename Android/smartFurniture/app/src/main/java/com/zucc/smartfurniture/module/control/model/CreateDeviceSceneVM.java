package com.zucc.smartfurniture.module.control.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.zucc.smartfurniture.BR;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/11/6$ 9:17$
 * Description:创建使用场景model
 * <p/>
 */
public class CreateDeviceSceneVM extends BaseObservable{
    /**设备码*/
    private String deviceCode;
    /**设备密码*/
    private String devicePassword;
    /**使用场景*/
    private String useScene;
    /**具体描述*/
    private String detail;
    @Bindable
    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
        notifyPropertyChanged(BR.deviceCode);
    }
    @Bindable
    public String getDevicePassword() {
        return devicePassword;
    }

    public void setDevicePassword(String devicePassword) {
        this.devicePassword = devicePassword;
        notifyPropertyChanged(BR.devicePassword);
    }
    @Bindable
    public String getUseScene() {
        return useScene;
    }

    public void setUseScene(String useScene) {
        this.useScene = useScene;
        notifyPropertyChanged(BR.useScene);
    }
    @Bindable
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
        notifyPropertyChanged(BR.detail);
    }
}

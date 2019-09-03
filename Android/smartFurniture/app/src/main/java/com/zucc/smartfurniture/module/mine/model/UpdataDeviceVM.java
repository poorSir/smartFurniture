package com.zucc.smartfurniture.module.mine.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import me.tatarka.bindingcollectionadapter2.BR;

/**
 * Author: shenzh
 * E-mail: shenzh@erongdu.com
 * Date: 2018/3/5$ 15:23$
 * <p/>
 */
public class UpdataDeviceVM extends BaseObservable{
    private String deviceId;
    private String oldDevicePassword;
    private String newDevicePassword;
    private String useScene;
    private String detail;
    @Bindable
    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
        notifyPropertyChanged(BR.deviceId);
    }
    @Bindable
    public String getOldDevicePassword() {
        return oldDevicePassword;
    }

    public void setOldDevicePassword(String oldDevicePassword) {
        this.oldDevicePassword = oldDevicePassword;
        notifyPropertyChanged(BR.oldDevicePassword);
    }
    @Bindable
    public String getNewDevicePassword() {
        return newDevicePassword;
    }

    public void setNewDevicePassword(String newDevicePassword) {
        this.newDevicePassword = newDevicePassword;
        notifyPropertyChanged(BR.newDevicePassword);
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

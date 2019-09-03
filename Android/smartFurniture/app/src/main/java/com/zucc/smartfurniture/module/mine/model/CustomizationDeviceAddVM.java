package com.zucc.smartfurniture.module.mine.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.zucc.smartfurniture.BR;

/**
 * Author: shenzh
 * E-mail: shenzh@erongdu.com
 * Date: 2018/3/6$ 14:30$
 * <p/>
 */
public class CustomizationDeviceAddVM extends BaseObservable{
    /**设备id*/
    private String deviceId;
    /**分类*/
    private String  classify;
    /**描述*/
    private String  detail;
    @Bindable
    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
        notifyPropertyChanged(BR.deviceId);
    }
    @Bindable
    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
        notifyPropertyChanged(BR.classify);
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

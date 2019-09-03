package com.zucc.smartfurniture.module.control.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.zucc.smartfurniture.BR;
import com.zucc.smartfurniture.common.Constant;
import com.zucc.smartfurniture.utils.TextUtil;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/11/2$ 14:16$
 * Description:远程控制item
 * <p/>
 */
public class ControlItemVM extends BaseObservable{
    /**设备id*/
    private String deviceId;
    /**分类*/
    private String  classify;
    /**描述*/
    private String  detail;
    /**开关状态 1-开 0-关*/
    private String  state;
    /**switch显示开关*/
    private boolean checked;
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
    @Bindable
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
        if(!TextUtil.isEmpty(state)){
            if(state.equals(Constant.String_1)){
                checked=true;
            }else {
                checked=false;
            }
        }
        notifyPropertyChanged(BR.checked);
        notifyPropertyChanged(BR.state);
    }
    @Bindable
    public boolean getChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
        if(checked){
            state = Constant.String_1;
        }else{
            state = Constant.String_0;
        }
        notifyPropertyChanged(BR.checked);
    }
    @Bindable
    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
        notifyPropertyChanged(BR.deviceId);
    }
}

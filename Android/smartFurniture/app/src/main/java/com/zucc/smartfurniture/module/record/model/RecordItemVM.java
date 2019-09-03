package com.zucc.smartfurniture.module.record.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.zucc.smartfurniture.BR;
import com.zucc.smartfurniture.R;
import com.zucc.smartfurniture.common.Constant;
import com.zucc.smartfurniture.utils.ContextUtil;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/11/6$ 10:27$
 * <p/>
 */
public class RecordItemVM extends BaseObservable{
    /**类型 1-遥控 0-手动*/
    private String type;
    /**状态 1-打开 0-关闭*/
    private String state;
    /**使用场景*/
    private String useScene;
    /**具体描述*/
    private String detail;
    /**时间*/
    private String time;
    private String typeStr;
    private String stateStr;
    @Bindable
    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
        notifyPropertyChanged(BR.typeStr);
    }
    @Bindable
    public String getStateStr() {
        return stateStr;
    }

    public void setStateStr(String stateStr) {
        this.stateStr = stateStr;
        notifyPropertyChanged(BR.stateStr);
    }

    @Bindable
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
        if(Constant.String_1.equals(type)){
          setTypeStr(ContextUtil.getString(R.string.records_type_1));
        }else{
            setTypeStr(ContextUtil.getString(R.string.records_type_0));
        }
        notifyPropertyChanged(BR.type);
    }
    @Bindable
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
        if(Constant.String_1.equals(state)){
           setStateStr(ContextUtil.getString(R.string.records_state_1));
        }else{
            setStateStr(ContextUtil.getString(R.string.records_state_0));
        }
        notifyPropertyChanged(BR.state);
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
    @Bindable
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
        notifyPropertyChanged(BR.time);
    }
}

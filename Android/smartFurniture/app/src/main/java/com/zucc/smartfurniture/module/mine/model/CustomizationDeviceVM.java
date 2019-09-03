package com.zucc.smartfurniture.module.mine.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.zucc.smartfurniture.BR;
import com.zucc.smartfurniture.R;
import com.zucc.smartfurniture.common.Constant;
import com.zucc.smartfurniture.module.mine.viewCtrl.CustomizationDeviceCtrl;
import com.zucc.smartfurniture.network.CallBackObserver;
import com.zucc.smartfurniture.network.HttpResult;
import com.zucc.smartfurniture.network.NetConnect;
import com.zucc.smartfurniture.network.api.Customization;
import com.zucc.smartfurniture.utils.ContextUtil;
import com.zucc.smartfurniture.utils.DialogUtils;

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Observable;

/**
 * Author: shenzh
 * E-mail: shenzh@erongdu.com
 * Date: 2018/3/6$ 10:10$
 * <p/>
 */
public class CustomizationDeviceVM extends BaseObservable{
    private String cusId;
    private String deviceId;
    private String operation;
    private String useScene;
    private String detail;
    private boolean isCheck;
    private CustomizationDeviceCtrl ctrl;
    public CustomizationDeviceVM(CustomizationDeviceCtrl ctrl){
        this.ctrl = ctrl;
    }
    public CustomizationDeviceVM(){
    }
    @Bindable
    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
        notifyPropertyChanged(BR.cusId);
    }

    @Bindable
    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
        notifyPropertyChanged(BR.deviceId);
    }
    @Bindable
    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
        if(Constant.String_0.equals(operation)){
            setCheck(false);
        }else if(Constant.String_1.equals(operation)){
            setCheck(true);
        }
        notifyPropertyChanged(BR.operation);
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
    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
        if(isCheck){
            operation = Constant.String_1;
        }else {
            operation = Constant.String_0;
        }
        notifyPropertyChanged(BR.check);
    }

    /**
     * 更改状态
     */
    public void changeStateClick(View view){
        DialogUtils.showDialog(view.getContext(), ContextUtil.getString(R.string.is_changestate), new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                Observable<HttpResult> call = NetConnect.getService(Customization.class).updataCusScene(cusId, deviceId, operation);
                call.compose(NetConnect.<HttpResult> setThread()).subscribe(new CallBackObserver<HttpResult>() {
                    @Override
                    public void onSuccess(HttpResult response) {
                        ctrl.reqData();
                    }
                });
                sweetAlertDialog.dismiss();
            }
        }, new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
              if(isCheck){
                  setCheck(false);
              }else{
                  setCheck(true);
              }
              sweetAlertDialog.dismiss();
            }
        });
    }
}

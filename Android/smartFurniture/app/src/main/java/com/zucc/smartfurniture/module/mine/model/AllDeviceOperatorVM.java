package com.zucc.smartfurniture.module.mine.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.zucc.smartfurniture.BR;
import com.zucc.smartfurniture.common.BaseParam;
import com.zucc.smartfurniture.common.Constant;
import com.zucc.smartfurniture.module.mine.viewCtrl.AllDeviceCtrl;
import com.zucc.smartfurniture.network.CallBackObserver;
import com.zucc.smartfurniture.network.HttpResult;
import com.zucc.smartfurniture.network.NetConnect;
import com.zucc.smartfurniture.network.RouterUrl;
import com.zucc.smartfurniture.network.api.OperateService;

import io.reactivex.Observable;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/11/17$ 14:18$
 * <p/>
 */
public class AllDeviceOperatorVM extends BaseObservable implements MultiItemEntity{
    private AllDeviceCtrl ctrl;
    /**设备id*/
    private String id;
    public AllDeviceOperatorVM(String id, AllDeviceCtrl ctrl){
        setId(id);
        this.ctrl = ctrl;
    }
    @Bindable
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Override
    public int getItemType() {
        return Constant.Number_1;
    }
    /**删除*/
    public void delete(View view){
        Observable<HttpResult> call = NetConnect.getService(OperateService.class).deleteBind(id);
        call.compose(NetConnect.<HttpResult>setThread()).subscribe(new CallBackObserver<HttpResult>() {
            @Override
            public void onSuccess(HttpResult response) {
                ctrl.reqData();
            }
        });

    }
    /**修改*/
    public void edit(View view){
        ARouter.getInstance().build(RouterUrl.updataDevice).withString(BaseParam.deviceId,id).navigation();
    }
}

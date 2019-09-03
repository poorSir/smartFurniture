package com.zucc.smartfurniture.module.mine.viewCtrl;

import android.view.View;

import com.zucc.smartfurniture.R;
import com.zucc.smartfurniture.module.mine.model.UpdataDeviceVM;
import com.zucc.smartfurniture.module.mine.ui.UpdataDeviceAct;
import com.zucc.smartfurniture.network.CallBackObserver;
import com.zucc.smartfurniture.network.HttpResult;
import com.zucc.smartfurniture.network.NetConnect;
import com.zucc.smartfurniture.network.api.OperateService;
import com.zucc.smartfurniture.utils.TextUtil;
import com.zucc.smartfurniture.utils.ToastUtil;

import io.reactivex.Observable;

/**
 * Author: shenzh
 * E-mail: shenzh@erongdu.com
 * Date: 2018/3/5$ 15:14$
 * {@link com.zucc.smartfurniture.module.mine.ui.UpdataDeviceAct}
 * <p/>
 */
public class UpdataDeviceCtrl {
    private UpdataDeviceVM vm;
    private UpdataDeviceAct act;
    public UpdataDeviceCtrl(UpdataDeviceAct act,String deviceId){
        this.act = act;
        vm =  new UpdataDeviceVM();
        vm.setDeviceId(deviceId);
    }

    public UpdataDeviceVM getVm() {
        return vm;
    }

    /**
     * 修改设备点击
     * @param view
     */
    public void updataDeviceClick(View view){
        if(TextUtil.isEmpty(vm.getOldDevicePassword())) {
            ToastUtil.show(R.string.error_oldpassword);
        }else if(TextUtil.isEmpty(vm.getNewDevicePassword())){
            ToastUtil.show(R.string.error_newpassword);
        }else if(TextUtil.isEmpty(vm.getUseScene())){
            ToastUtil.show(R.string.error_classify);
        }else if(TextUtil.isEmpty(vm.getDetail())){
            ToastUtil.show(R.string.error_detail);
        }else {
            Observable<HttpResult> call = NetConnect.getService(OperateService.class).updataDeviceInfo(vm.getDeviceId(),vm.getOldDevicePassword(),
                    vm.getNewDevicePassword(),vm.getUseScene(),vm.getDetail());
            call.compose(NetConnect.<HttpResult>setThread()).subscribe(new CallBackObserver<HttpResult>() {
                @Override
                public void onSuccess(HttpResult response) {
                    act.finish();
                }
            });
        }
        return;
    }
}

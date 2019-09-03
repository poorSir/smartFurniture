package com.zucc.smartfurniture.module.control.viewCtrl;

import android.app.Activity;

import com.zucc.smartfurniture.R;
import com.zucc.smartfurniture.module.control.model.CreateDeviceSceneVM;
import com.zucc.smartfurniture.network.CallBackObserver;
import com.zucc.smartfurniture.network.HttpResult;
import com.zucc.smartfurniture.network.NetConnect;
import com.zucc.smartfurniture.network.api.OperateService;
import com.zucc.smartfurniture.utils.TextUtil;
import com.zucc.smartfurniture.utils.ToastUtil;

import io.reactivex.Observable;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/11/3$ 17:10$
 * {@link com.zucc.smartfurniture.module.control.ui.CreateDeviceSceneAct}
 * <p/>
 */
public class CreateDeviceSceneCtrl {
    private CreateDeviceSceneVM vm;
    private Activity activity;
    public CreateDeviceSceneCtrl(Activity activity){
        vm=new CreateDeviceSceneVM();
        this.activity = activity;
    }

    public CreateDeviceSceneVM getVm() {
        return vm;
    }
    /**绑定设备*/
    public void bind(){
        if(TextUtil.isEmpty(vm.getDeviceCode())|| TextUtil.isEmpty(vm.getDevicePassword())||TextUtil.isEmpty(vm.getUseScene())||TextUtil.isEmpty(vm.getDetail())){
            ToastUtil.show(R.string.error_complete);
        }else{
            Observable<HttpResult> call = NetConnect.getService(OperateService.class).bindDeviceScene(vm.getDeviceCode(),vm.getDevicePassword(),vm.getUseScene(),vm.getDetail());
            call.compose(NetConnect.<HttpResult>setThread()).subscribe(new CallBackObserver<HttpResult>() {
                @Override
                public void onSuccess(HttpResult response) {
                    activity.setResult(Activity.RESULT_OK);
                    activity.finish();
                }
            });
        }
    }
}

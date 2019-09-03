package com.zucc.smartfurniture.module.user.viewCtrl;

import android.app.Activity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.zucc.smartfurniture.R;
import com.zucc.smartfurniture.common.BaseParam;
import com.zucc.smartfurniture.databinding.LoginActBinding;
import com.zucc.smartfurniture.module.user.model.UserVM;
import com.zucc.smartfurniture.module.user.model.rec.TokenRec;
import com.zucc.smartfurniture.network.CallBackObserver;
import com.zucc.smartfurniture.network.HttpResult;
import com.zucc.smartfurniture.network.NetConnect;
import com.zucc.smartfurniture.network.RouterUrl;
import com.zucc.smartfurniture.network.api.UserService;
import com.zucc.smartfurniture.utils.SharePreferenceInfo;
import com.zucc.smartfurniture.utils.TextUtil;
import com.zucc.smartfurniture.utils.ToastUtil;

import io.reactivex.Observable;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/10/25$ 11:18$
 * {@link com.zucc.smartfurniture.module.user.ui.LoginAct}
 * <p/>
 */
public class LoginCtrl {
    private LoginActBinding binding;
    private UserVM          vm;
    private Activity activity;
    public LoginCtrl(LoginActBinding binding,Activity activity){
        this.binding = binding;
        this.activity=activity;
        vm=new UserVM();
        if((boolean)SharePreferenceInfo.getInstance().getValue(BaseParam.isLogin, SharePreferenceInfo.DataType.BOOLEAN)){
            ARouter.getInstance().build(RouterUrl.Main_Act).navigation();
            activity.finish();
        }
    }
    /**登录*/
    public void Login(){
        if(!TextUtil.phoneCheck(vm.getUsername())){
            ToastUtil.show(R.string.error_phone);
        }else if(!TextUtil.passwordCheck(vm.getPassword())){
            ToastUtil.show(R.string.password_hint);
        }else{
            Observable<HttpResult<TokenRec>> call = NetConnect.getInstance().getService(UserService.class).userLogin(vm.getUsername(),vm.getPassword());
            call.compose(NetConnect.<HttpResult<TokenRec>> setThread()).subscribe(new CallBackObserver<HttpResult<TokenRec>>() {
                @Override
                public void onSuccess(HttpResult<TokenRec> response) {
                    SharePreferenceInfo.getInstance().setValue(BaseParam.userId, response.getData().getUserId());
                    SharePreferenceInfo.getInstance().setValue(BaseParam.token, response.getData().getToken());
                    SharePreferenceInfo.getInstance().setValue(BaseParam.isLogin,true);
                    ARouter.getInstance().build(RouterUrl.Main_Act).navigation();
                    activity.finish();
                }
            });
        }
    }
    /**注册*/
    public void register(){
        ARouter.getInstance().build(RouterUrl.Register_Act).navigation(activity,BaseParam.login_register_request);
    }
    /**忘记密码*/
    public void forgetPassword(){
        ARouter.getInstance().build(RouterUrl.ForgetPassword).navigation(activity,BaseParam.login_forget_request);
    }

    public UserVM getVm() {
        return vm;
    }
}

package com.zucc.smartfurniture.module.user.viewCtrl;

import android.app.Activity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.zucc.smartfurniture.R;
import com.zucc.smartfurniture.common.BaseParam;
import com.zucc.smartfurniture.databinding.RegisterActBinding;
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

import java.util.concurrent.TimeUnit;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/10/29$ 9:43$
 * {@link com.zucc.smartfurniture.module.user.ui.RegisterAct}
 * <p/>
 */
public class RegisterCtrl {
    private UserVM             vm;
    private RegisterActBinding binding;
    private Activity           activity;
    private Disposable         disposable;

    public RegisterCtrl(RegisterActBinding binding, Activity activity) {
        this.binding = binding;
        this.activity = activity;
        vm = new UserVM();
        // 注册监听器
        SMSSDK.registerEventHandler(eventHandler);
    }

    public UserVM getVm() {
        return vm;
    }

    /** 完成注册 */
    public void register() {
        if (!TextUtil.phoneCheck(vm.getUsername())) {
            ToastUtil.show(R.string.error_phone);
        } else if (!TextUtil.passwordCheck(vm.getPassword())) {
            ToastUtil.show(R.string.password_hint);
        } else if (TextUtil.isEmpty(vm.getIdentitfy_code())) {
            ToastUtil.show(R.string.error_identitfy_code);
        } else {
            SMSSDK.submitVerificationCode("86", vm.getUsername(), vm.getIdentitfy_code());
        }
    }

    /** 获取验证码 */
    public void getCode() {
        if (!TextUtil.phoneCheck(vm.getUsername())) {
            ToastUtil.show(R.string.error_phone);
        } else {
            Observable.interval(1, TimeUnit.SECONDS).take(61)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Long>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {
                    disposable = d;
                    binding.identitfyCodeGet.setClickable(false);
                    SMSSDK.getVerificationCode("86", vm.getUsername());
                }
                @Override
                public void onNext(@NonNull Long Long) {
                    binding.identitfyCodeGet.setText((60 - Long) + "秒");
                }
                @Override
                public void onError(@NonNull Throwable e) {
                }
                @Override
                public void onComplete() {
                    if (!disposable.isDisposed()) {
                        disposable.dispose();
                    }
                    binding.identitfyCodeGet.setClickable(true);
                    binding.identitfyCodeGet.setText(activity.getResources().getString(R.string.get_identitfy_code));
                }
            });
        }
    }
    public EventHandler eventHandler = new EventHandler() {
        public void afterEvent(int event, int result, Object data) {
            if (data instanceof Throwable) {
                io.reactivex.Observable.just(data).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object data) throws Exception {
                        if (!disposable.isDisposed()) {
                            disposable.dispose();
                        }
                        Throwable throwable = (Throwable) data;
                        String    msg       = throwable.getMessage();
                        ToastUtil.show(binding.getRoot().getContext(), msg);
                    }
                });
            } else {
                if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    Observable.just(event).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Integer>() {
                        @Override
                        public void accept(@NonNull Integer integer) throws Exception {
                            ToastUtil.show(binding.getRoot().getContext(), activity.getResources().getString(R.string.identitfy_code_send));
                        }
                    });
                } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    Observable.just(event).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Integer>() {
                        @Override
                        public void accept(@NonNull Integer integer) throws Exception {
                            Observable<HttpResult<TokenRec>> call = NetConnect.getInstance().getService(UserService.class).userRegister(vm.getUsername(), vm
                                    .getPassword());
                            call.compose(NetConnect.<HttpResult<TokenRec>> setThread()).subscribe(new CallBackObserver<HttpResult<TokenRec>>() {
                                @Override
                                public void onSuccess(HttpResult<TokenRec> response) {
                                    SharePreferenceInfo.getInstance().setValue(BaseParam.userId, response.getData().getUserId());
                                    SharePreferenceInfo.getInstance().setValue(BaseParam.token, response.getData().getToken());
                                    SharePreferenceInfo.getInstance().setValue(BaseParam.isLogin, true);
                                    activity.setResult(Activity.RESULT_OK);
                                    ARouter.getInstance().build(RouterUrl.Main_Act).navigation();
                                    activity.finish();
                                }
                            });
                        }
                    });
                }
            }
        }
    };

    /** 页面销毁调用 */
    public void onDestory() {
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
        SMSSDK.unregisterEventHandler(eventHandler);
    }
}

package com.zucc.smartfurniture.module.user.viewCtrl;

import android.app.Activity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.zucc.smartfurniture.R;
import com.zucc.smartfurniture.common.BaseParam;
import com.zucc.smartfurniture.databinding.ForgetpaswordActBinding;
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
 * Date: 2017/10/30$ 14:57$
 * {@link com.zucc.smartfurniture.module.user.ui.ForgetPasswordAct}
 * <p/>
 */
public class ForgetPasswordCtrl {
    private ForgetpaswordActBinding binding;
    private Activity                activity;
    private UserVM                  vm;
    private Disposable              disposable;

    public ForgetPasswordCtrl(ForgetpaswordActBinding binding, Activity activity) {
        this.binding = binding;
        this.activity = activity;
        vm = new UserVM();
        // 注册监听器
        SMSSDK.registerEventHandler(eventHandler);
    }

    /** 获取验证码 */
    public void getCode() {
        if (!TextUtil.phoneCheck(vm.getUsername())) {
            ToastUtil.show(R.string.error_phone);
        } else {
            //倒计时
            Observable.interval(1, TimeUnit.SECONDS).take(61).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Long>() {
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

    /** 确定按钮 */
    public void confirm() {
        if (!TextUtil.phoneCheck(vm.getUsername())) {
            ToastUtil.show(R.string.error_phone);
        } else if (TextUtil.isEmpty(vm.getIdentitfy_code())) {
            ToastUtil.show(R.string.error_identitfy_code);
        } else {
            SMSSDK.submitVerificationCode("86", vm.getUsername(), vm.getIdentitfy_code());
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
                            Observable<HttpResult<TokenRec>> call = NetConnect.getInstance().getService(UserService.class).userForgetPassword(vm.getUsername());
                            call.compose(NetConnect.<HttpResult<TokenRec>> setThread()).subscribe(new CallBackObserver<HttpResult<TokenRec>>() {
                                @Override
                                public void onSuccess(HttpResult<TokenRec> response) {
                                    SharePreferenceInfo.getInstance().setValue(BaseParam.userId, response.getData().getUserId());
                                    SharePreferenceInfo.getInstance().setValue(BaseParam.token, response.getData().getToken());
                                    ARouter.getInstance().build(RouterUrl.ChangePassword).navigation(activity, BaseParam.forget_change_request);
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
        if (null != disposable && !disposable.isDisposed()) {
            disposable.dispose();
        }
        SMSSDK.unregisterEventHandler(eventHandler);
    }

    public UserVM getVm() {
        return vm;
    }
}

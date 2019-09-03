package com.zucc.smartfurniture.module.user.viewCtrl;

import android.app.Activity;
import android.databinding.ObservableField;
import android.view.View;

import com.zucc.smartfurniture.R;
import com.zucc.smartfurniture.common.Constant;
import com.zucc.smartfurniture.databinding.ChangepasswordActBinding;
import com.zucc.smartfurniture.module.user.model.UserVM;
import com.zucc.smartfurniture.module.user.model.rec.TokenRec;
import com.zucc.smartfurniture.network.CallBackObserver;
import com.zucc.smartfurniture.network.HttpResult;
import com.zucc.smartfurniture.network.NetConnect;
import com.zucc.smartfurniture.network.api.UserService;
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
 * Date: 2017/10/31$ 9:47$
 * {@link com.zucc.smartfurniture.module.user.ui.ChangePasswordAct}
 * <p/>
 */
public class ChangePasswordCtrl {
    private UserVM                   vm;
    private Activity                 activity;
    private ChangepasswordActBinding binding;
    /** 0-展示验证码 */
    private String                   type;
    private Disposable               disposable;
    /** 显示获取验证码 */
    public ObservableField<Integer> showCode = new ObservableField<>(View.GONE);

    public ChangePasswordCtrl(Activity activity, String type, ChangepasswordActBinding binding) {
        this.activity = activity;
        this.binding = binding;
        this.type = type;
        vm = new UserVM();
        if (!TextUtil.isEmpty(type) && type.equals(Constant.String_0)) {
            showCode.set(View.VISIBLE);
            // 注册监听器
            SMSSDK.registerEventHandler(eventHandler);
        } else {
            showCode.set(View.GONE);
        }
    }

    public UserVM getVm() {
        return vm;
    }

    /** 提交 */
    public void submit() {
        if (!TextUtil.phoneCheck(vm.getUsername())) {
            ToastUtil.show(R.string.error_phone);
        } else if (!TextUtil.passwordCheck(vm.getPassword())) {
            ToastUtil.show(R.string.password_hint);
        } else if (!vm.getPassword().equals(vm.getComfirmPassword())) {
            ToastUtil.show(R.string.error_two_same);
        } else {
            if (!TextUtil.isEmpty(type) && type.equals(Constant.String_0)) {
                if (TextUtil.isEmpty(vm.getIdentitfy_code())) {
                    ToastUtil.show(R.string.error_identitfy_code);
                } else {
                    SMSSDK.submitVerificationCode("86", vm.getUsername(), vm.getIdentitfy_code());
                }
            } else {
                Observable<HttpResult<TokenRec>> call = NetConnect.getInstance().getService(UserService.class).userChangePassword(vm.getUsername(), vm
                        .getPassword());
                call.compose(NetConnect.<HttpResult<TokenRec>> setThread()).subscribe(new CallBackObserver<HttpResult<TokenRec>>() {
                    @Override
                    public void onSuccess(HttpResult<TokenRec> response) {
                        activity.setResult(Activity.RESULT_OK);
                        activity.finish();
                    }
                });
            }
        }
    }

    /** 获取验证码 */
    public void getCode() {
        if (!TextUtil.phoneCheck(vm.getUsername())) {
            ToastUtil.show(R.string.error_phone);
        } else {
            //60秒倒计时
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

    public EventHandler eventHandler = new EventHandler() {
        public void afterEvent(int event, int result, Object data) {
            if (data instanceof Throwable) {
                //错误异常
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
                    //验证码已发出
                    Observable.just(event).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Integer>() {
                        @Override
                        public void accept(@NonNull Integer integer) throws Exception {
                            ToastUtil.show(binding.getRoot().getContext(), activity.getResources().getString(R.string.identitfy_code_send));
                        }
                    });
                } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    //验证码验证通过
                    Observable.just(event).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Integer>() {
                        @Override
                        public void accept(@NonNull Integer integer) throws Exception {
                            Observable<HttpResult<TokenRec>> call = NetConnect.getInstance().getService(UserService.class).userChangePassword(vm.getUsername
                                    (), vm.getPassword());
                            call.compose(NetConnect.<HttpResult<TokenRec>> setThread()).subscribe(new CallBackObserver<HttpResult<TokenRec>>() {
                                @Override
                                public void onSuccess(HttpResult<TokenRec> response) {
                                    activity.finish();
                                }
                            });
                        }
                    });
                }
            }
            ;
        }
    };

    /** 页面销毁调用 */
    public void onDestory() {
        if (!TextUtil.isEmpty(type) && type.equals(Constant.String_0)) {
            if (null != disposable && !disposable.isDisposed()) {
                disposable.dispose();
            }
            SMSSDK.unregisterEventHandler(eventHandler);
        }
    }
}

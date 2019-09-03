package com.zucc.smartfurniture.module.user.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.mob.MobSDK;
import com.zucc.smartfurniture.R;
import com.zucc.smartfurniture.common.AppConfig;
import com.zucc.smartfurniture.common.BaseActivity;
import com.zucc.smartfurniture.databinding.RegisterActBinding;
import com.zucc.smartfurniture.module.user.viewCtrl.RegisterCtrl;
import com.zucc.smartfurniture.network.RouterUrl;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/10/28$ 20:44$
 * Description:注册页面
 * <p/>
 */
@Route(path = RouterUrl.Register_Act)
public class RegisterAct extends BaseActivity{
    private RegisterActBinding binding;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.register_act);
        // 通过代码注册你的AppKey和AppSecret
        MobSDK.init(this, AppConfig.mob_sms_appkey,AppConfig.mob_sms_appSecret);
        binding.setViewCtrl(new RegisterCtrl(binding,this));
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterAct.this.finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.getViewCtrl().onDestory();
    }
}

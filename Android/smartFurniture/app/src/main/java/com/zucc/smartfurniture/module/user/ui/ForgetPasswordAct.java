package com.zucc.smartfurniture.module.user.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zucc.smartfurniture.R;
import com.zucc.smartfurniture.common.BaseActivity;
import com.zucc.smartfurniture.common.BaseParam;
import com.zucc.smartfurniture.databinding.ForgetpaswordActBinding;
import com.zucc.smartfurniture.module.user.viewCtrl.ForgetPasswordCtrl;
import com.zucc.smartfurniture.network.RouterUrl;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/10/30$ 14:52$
 * Description:忘记密码界面
 * <p/>
 */
@Route(path = RouterUrl.ForgetPassword)
public class ForgetPasswordAct extends BaseActivity{
    private  ForgetpaswordActBinding binding;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.forgetpasword_act);
        binding.setViewCtrl(new ForgetPasswordCtrl(binding,this));
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ForgetPasswordAct.this.finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.getViewCtrl().onDestory();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == BaseParam.forget_change_request && resultCode == RESULT_OK){
            setResult(RESULT_OK);
            finish();
        }
    }
}

package com.zucc.smartfurniture.module.user.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zucc.smartfurniture.R;
import com.zucc.smartfurniture.common.BaseActivity;
import com.zucc.smartfurniture.common.BaseParam;
import com.zucc.smartfurniture.databinding.LoginActBinding;
import com.zucc.smartfurniture.module.user.viewCtrl.LoginCtrl;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/10/23$ 9:17$
 * Description:登录界面
 * <p/>
 */
public class LoginAct extends BaseActivity{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginActBinding binding = DataBindingUtil.setContentView(this,R.layout.login_act);
        binding.toolbar.setTitle("");
        binding.setViewCtrl(new LoginCtrl(binding,this));
        setSupportActionBar(binding.toolbar);
       /* binding.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginAct.this.finish();
            }
        });*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if((requestCode == BaseParam.login_register_request || requestCode == BaseParam.login_forget_request) && resultCode == RESULT_OK){
            finish();
        }
    }
}

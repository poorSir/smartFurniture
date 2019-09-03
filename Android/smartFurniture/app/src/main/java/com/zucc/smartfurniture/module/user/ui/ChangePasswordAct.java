package com.zucc.smartfurniture.module.user.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.zucc.smartfurniture.R;
import com.zucc.smartfurniture.common.BaseActivity;
import com.zucc.smartfurniture.databinding.ChangepasswordActBinding;
import com.zucc.smartfurniture.module.user.viewCtrl.ChangePasswordCtrl;
import com.zucc.smartfurniture.network.RouterUrl;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/10/31$ 9:45$
 * Description:修改密码
 * <p/>
 */
@Route(path = RouterUrl.ChangePassword)
public class ChangePasswordAct extends BaseActivity{
    private ChangepasswordActBinding binding;
    @Autowired
    public String type;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.changepassword_act);
        binding.setViewCtrl(new ChangePasswordCtrl(this,type,binding));
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangePasswordAct.this.finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.getViewCtrl().onDestory();
    }
}

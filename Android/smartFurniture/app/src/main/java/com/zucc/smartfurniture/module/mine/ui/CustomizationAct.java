package com.zucc.smartfurniture.module.mine.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zucc.smartfurniture.R;
import com.zucc.smartfurniture.common.BaseActivity;
import com.zucc.smartfurniture.databinding.MineActivityCustomizationBinding;
import com.zucc.smartfurniture.module.mine.viewCtrl.CustomizationCtrl;
import com.zucc.smartfurniture.network.RouterUrl;

/**
 * Author: shenzh
 * E-mail: shenzh@erongdu.com
 * Date: 2018/3/5$ 15:51$
 * Description:我的场景
 * <p/>
 */
@Route(path = RouterUrl.customization)
public class CustomizationAct extends BaseActivity{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MineActivityCustomizationBinding binding =  DataBindingUtil.setContentView(this, R.layout.mine_activity_customization);
        binding.setViewCtrl(new CustomizationCtrl());
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomizationAct.this.finish();
            }
        });
    }
}

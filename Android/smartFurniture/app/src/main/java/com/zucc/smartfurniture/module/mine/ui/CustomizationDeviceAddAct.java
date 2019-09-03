package com.zucc.smartfurniture.module.mine.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zucc.smartfurniture.R;
import com.zucc.smartfurniture.common.BaseActivity;
import com.zucc.smartfurniture.common.BaseParam;
import com.zucc.smartfurniture.databinding.MineActivityCustomizationAddBinding;
import com.zucc.smartfurniture.module.mine.viewCtrl.CustomizationDeviceAddCtrl;
import com.zucc.smartfurniture.network.RouterUrl;

/**
 * Author: shenzh
 * E-mail: shenzh@erongdu.com
 * Date: 2018/3/6$ 11:45$
 * Description:场景设备添加
 * <p/>
 */
@Route(path = RouterUrl.customizationDeviceAdd)
public class CustomizationDeviceAddAct extends BaseActivity{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MineActivityCustomizationAddBinding binding = DataBindingUtil.setContentView(this, R.layout.mine_activity_customization_add);
        binding.setViewCtrl(new CustomizationDeviceAddCtrl(binding,getIntent().getStringExtra(BaseParam.cusId),this));
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomizationDeviceAddAct.this.finish();
            }
        });
    }
}

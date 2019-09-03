package com.zucc.smartfurniture.module.mine.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zucc.smartfurniture.R;
import com.zucc.smartfurniture.common.BaseActivity;
import com.zucc.smartfurniture.common.BaseParam;
import com.zucc.smartfurniture.databinding.MineActivityCustomizationDeviceBinding;
import com.zucc.smartfurniture.module.mine.viewCtrl.CustomizationDeviceCtrl;
import com.zucc.smartfurniture.network.RouterUrl;

/**
 * Author: shenzh
 * E-mail: shenzh@erongdu.com
 * Date: 2018/3/5$ 17:57$
 * Description:场景设备
 * <p/>
 */
@Route(path = RouterUrl.customizationDevice)
public class CustomizationDeviceAct extends BaseActivity{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MineActivityCustomizationDeviceBinding binding = DataBindingUtil.setContentView(this, R.layout.mine_activity_customization_device);
        binding.setViewCtrl(new CustomizationDeviceCtrl(getIntent().getStringExtra(BaseParam.cusId)));
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomizationDeviceAct.this.finish();
            }
        });
        binding.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(RouterUrl.customizationDeviceAdd).withString(BaseParam.cusId,getIntent().getStringExtra(BaseParam.cusId)).navigation();
            }
        });
    }
}

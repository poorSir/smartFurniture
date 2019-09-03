package com.zucc.smartfurniture.module.control.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zucc.smartfurniture.R;
import com.zucc.smartfurniture.common.BaseActivity;
import com.zucc.smartfurniture.databinding.CreateDeviceSceneBinding;
import com.zucc.smartfurniture.module.control.viewCtrl.CreateDeviceSceneCtrl;
import com.zucc.smartfurniture.network.RouterUrl;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/11/3$ 15:42$
 * Description:创建设备使用场景
 * <p/>
 */
@Route(path = RouterUrl.createDeviceScene)
public class CreateDeviceSceneAct extends BaseActivity{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CreateDeviceSceneBinding binding = DataBindingUtil.setContentView(this, R.layout.create_device_scene);
        binding.setViewCtrl(new CreateDeviceSceneCtrl(CreateDeviceSceneAct.this));
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateDeviceSceneAct.this.finish();
            }
        });
    }
}

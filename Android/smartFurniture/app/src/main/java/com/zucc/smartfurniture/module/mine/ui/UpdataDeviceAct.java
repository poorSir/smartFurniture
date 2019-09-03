package com.zucc.smartfurniture.module.mine.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zucc.smartfurniture.R;
import com.zucc.smartfurniture.common.BaseActivity;
import com.zucc.smartfurniture.common.BaseParam;
import com.zucc.smartfurniture.databinding.MineActivityDeviceUpdataBinding;
import com.zucc.smartfurniture.module.mine.viewCtrl.UpdataDeviceCtrl;
import com.zucc.smartfurniture.network.RouterUrl;

/**
 * Author: shenzh
 * E-mail: shenzh@erongdu.com
 * Date: 2018/3/5$ 15:10$
 * Description:修改设备信息
 * <p/>
 */
@Route(path = RouterUrl.updataDevice)
public class UpdataDeviceAct extends BaseActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MineActivityDeviceUpdataBinding binding = DataBindingUtil.setContentView(this, R.layout.mine_activity_device_updata);
        binding.setViewCtrl(new UpdataDeviceCtrl(this,getIntent().getStringExtra(BaseParam.deviceId)));
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdataDeviceAct.this.finish();
            }
        });
    }
}

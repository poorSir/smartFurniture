package com.zucc.smartfurniture.module.mine.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zucc.smartfurniture.R;
import com.zucc.smartfurniture.common.BaseActivity;
import com.zucc.smartfurniture.databinding.AllDeviceActBinding;
import com.zucc.smartfurniture.module.mine.viewCtrl.AllDeviceCtrl;
import com.zucc.smartfurniture.network.RouterUrl;
import com.zucc.smartfurniture.utils.ContextUtil;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/11/16$ 15:41$
 * Description:所有设备
 * <p/>
 */
@Route(path = RouterUrl.allDevice)
public class AllDeviceAct extends BaseActivity {
    private AllDeviceActBinding binding;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.all_device_act);
        binding.setViewCtrl(new AllDeviceCtrl());
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContextUtil.getActivity().finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.getViewCtrl().reqData();
    }
}

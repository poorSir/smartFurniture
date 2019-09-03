package com.zucc.smartfurniture.module.control.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.zucc.smartfurniture.R;
import com.zucc.smartfurniture.common.BaseParam;
import com.zucc.smartfurniture.databinding.ControlFragmentBinding;
import com.zucc.smartfurniture.module.control.viewCtrl.ControlCtrl;
import com.zucc.smartfurniture.network.RouterUrl;

import static android.app.Activity.RESULT_OK;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/11/1$ 13:56$
 * Descriprion:远程控制
 * <p/>
 */
public class ControlFrag extends Fragment{
    private ControlFragmentBinding binding;
    public static ControlFrag newInstance() {
        Bundle args = new Bundle();
        ControlFrag fragment = new ControlFrag();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.control_fragment,null,false);
        binding.setViewCtrl(new ControlCtrl(binding));
        binding.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(RouterUrl.createDeviceScene).navigation(getActivity(), BaseParam.control_scene_request);
            }
        });
        return binding.getRoot();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == BaseParam.control_scene_request && resultCode == RESULT_OK){
            binding.getViewCtrl().reqData();
        }
    }
}

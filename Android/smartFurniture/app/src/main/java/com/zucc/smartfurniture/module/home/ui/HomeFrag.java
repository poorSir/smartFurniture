package com.zucc.smartfurniture.module.home.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zucc.smartfurniture.R;
import com.zucc.smartfurniture.databinding.HomeFragmentBinding;
import com.zucc.smartfurniture.module.home.viewCtrl.HomeCtrl;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/10/20$ 10:33$
 * Description:首页
 * <p/>
 */
public class HomeFrag extends Fragment{
   public static HomeFrag newInstance() {
        Bundle args = new Bundle();
        HomeFrag fragment = new HomeFrag();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        HomeFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment,null,false);
        binding.setViewCtrl(new HomeCtrl(binding,getActivity()));
        return binding.getRoot();
    }
}

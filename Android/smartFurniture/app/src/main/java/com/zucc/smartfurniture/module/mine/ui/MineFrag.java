package com.zucc.smartfurniture.module.mine.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zucc.smartfurniture.R;
import com.zucc.smartfurniture.databinding.MineFragmentBinding;
import com.zucc.smartfurniture.module.mine.viewCtrl.MineCtrl;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/11/1$ 14:30$
 * Description:我的
 * <p/>
 */
public class MineFrag extends Fragment {
    public static MineFrag newInstance() {
        Bundle args = new Bundle();
        MineFrag fragment = new MineFrag();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       MineFragmentBinding binding  = DataBindingUtil.inflate(inflater, R.layout.mine_fragment,null,false);
        binding.setViewCtrl(new MineCtrl());
        return binding.getRoot();
    }
}

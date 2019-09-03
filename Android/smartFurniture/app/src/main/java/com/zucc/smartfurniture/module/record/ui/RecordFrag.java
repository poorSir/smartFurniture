package com.zucc.smartfurniture.module.record.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zucc.smartfurniture.R;
import com.zucc.smartfurniture.databinding.RecordFragmentBinding;
import com.zucc.smartfurniture.module.record.viewCtrl.RecordCtrl;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/11/1$ 14:19$
 * <p/>
 */
public class RecordFrag extends Fragment{
    public static RecordFrag newInstance() {
        Bundle args = new Bundle();
        RecordFrag fragment = new RecordFrag();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RecordFragmentBinding binding  = DataBindingUtil.inflate(inflater, R.layout.record_fragment,null,false);
        binding.setViewCtrl(new RecordCtrl());
        return binding.getRoot();
    }
}

package com.zucc.smartfurniture.common;

import android.databinding.ObservableField;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zucc.smartfurniture.views.SwipeListener;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/11/2$ 11:27$
 * <p/>
 */
public abstract class BaseRevycleviewCtrl{
    /**页面布局显示*/
    public ObservableField<BaseRecycleViewVM> viewModel = new ObservableField<>();
    public ObservableField<SwipeListener>     listener  = new ObservableField<>();
    /**适配器*/
    public ObservableField<BaseQuickAdapter>  adapter   =new ObservableField<>();
    /** 下拉刷新控件 */
    private SwipeToLoadLayout swipeLayout;

    public SwipeToLoadLayout getSwipeLayout() {
        return swipeLayout;
    }

    public void setSwipeLayout(SwipeToLoadLayout swipeToLoadLayout) {
        this.swipeLayout = swipeToLoadLayout;
    }

}

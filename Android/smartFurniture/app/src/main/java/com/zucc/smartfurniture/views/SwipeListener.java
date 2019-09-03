package com.zucc.smartfurniture.views;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/11/3$ 10:06$
 * <p/>
 */
public abstract class SwipeListener implements OnRefreshListener,OnLoadMoreListener {
    @Override
    public void onLoadMore() {
        laodMore();
    }

    @Override
    public void onRefresh() {
        refresh();
    }
    public abstract void refresh();
    public abstract void laodMore();
    public abstract void swipeInit(SwipeToLoadLayout swipeLayout);
}

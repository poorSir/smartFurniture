package com.zucc.smartfurniture.common;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ScrollView;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.zucc.smartfurniture.R;
import com.zucc.smartfurniture.views.DividerLine;
import com.zucc.smartfurniture.views.SwipeListener;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/11/2$ 15:52$
 * Description: 自定义Setters
 * <p/>
 */
public class BindingAdapter {
    @android.databinding.BindingAdapter({"addItemDecoration"})
    public static void addItemDecoration(RecyclerView view, int type){
        DividerLine dividerLine;
        switch(type){
            case DividerLine.HORIZONTAL:
                dividerLine = new DividerLine(DividerLine.HORIZONTAL);
                dividerLine.setMarginStart(20);
                view.addItemDecoration(dividerLine);
                break;
            case DividerLine.VERTICAL:
                dividerLine = new DividerLine(DividerLine.VERTICAL);
                view.addItemDecoration(dividerLine);
                break;
            case DividerLine.CROSS:
                dividerLine = new DividerLine(DividerLine.CROSS);
                view.addItemDecoration(dividerLine);
                break;
            default:
                break;
        }
    }
    /**
     * 配置下拉刷新事件
     */
    @android.databinding.BindingAdapter(value = {"listener"})
    public static void listener(final SwipeToLoadLayout layout, SwipeListener listener) {
        if (listener != null) {
            LayoutInflater mInflater = (LayoutInflater) layout.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // 设置头部、底部加载刷新控件
            layout.setRefreshHeaderView(mInflater.inflate(R.layout.swipe_twitter_header, layout, false));
            layout.setLoadMoreFooterView(mInflater.inflate(R.layout.swipe_twitter_footer, layout, false));
            layout.setSwipeStyle(SwipeToLoadLayout.STYLE.CLASSIC);
            layout.setLoadMoreEnabled(true);
            //自动刷新
            layout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    layout.setRefreshing(true);
                }
            },200);
            if (layout.getChildCount() >= 2) {
                for (int i = 0; i < layout.getChildCount(); i++) {
                    View tClass = layout.getChildAt(i);
                    if (tClass instanceof RecyclerView) {// 判断滑动布局是否为recyclerView，添加自动加载事件
                        ((RecyclerView) tClass).setOnScrollListener(new RecyclerView.OnScrollListener() {
                            @Override
                            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                                    if (!ViewCompat.canScrollVertically(recyclerView, 2)) {
                                        layout.setLoadingMore(true);
                                    }
                                }
                            }
                        });
                        break;
                    } else if (tClass instanceof ListView) {// 判断滑动布局是否为ListView，添加自动加载事件
                        ((ListView) tClass).setOnScrollListener(new AbsListView.OnScrollListener() {
                            @Override
                            public void onScrollStateChanged(AbsListView view, int scrollState) {
                                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                                    if (view.getLastVisiblePosition() == view.getCount() - 1 && !ViewCompat.canScrollVertically(view, 1)) {
                                        layout.setLoadingMore(true);
                                    }
                                }
                            }

                            @Override
                            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                            }
                        });
                        break;
                    } else if (tClass instanceof ScrollView) {// 判断滑动布局是否为ScrollView，添加自动加载事件
                        final ScrollView scrollView = ((ScrollView) tClass);
                        layout.setLoadMoreEnabled(false);
                        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                            @Override
                            public void onScrollChanged() {
                                if (scrollView.getChildAt(0).getHeight() < scrollView.getScrollY() + scrollView.getHeight() && !ViewCompat
                                        .canScrollVertically(scrollView, 1)) {
                                    layout.setLoadingMore(true);
                                }
                            }
                        });
                        break;
                    }
                }
            }

            // 添加响应事件方法
            layout.setOnLoadMoreListener(listener);
            layout.setOnRefreshListener(listener);
            // 初始化刷新方法类
            listener.swipeInit(layout);
        }
    }
}

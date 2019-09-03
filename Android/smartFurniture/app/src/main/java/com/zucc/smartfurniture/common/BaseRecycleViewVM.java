package com.zucc.smartfurniture.common;

import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.zucc.smartfurniture.views.DividerLine;

import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/11/2$ 11:32$
 * <p/>
 */
public abstract class BaseRecycleViewVM<T> extends BaseObservable{
    /**数据源*/
    public final ObservableList<T> items = new ObservableArrayList<>();
    /**
     * 分割线
     * 水平方向 -0
     * 垂直方向 -1
     * 全方向 - 9
     */
    public       int     type  = DividerLine.HORIZONTAL;
    public abstract void setItemView (ItemBinding itemBinding, int position, T item);
    /**界面绑定*/
    public final OnItemBind<T> onItemBind = new OnItemBind<T>() {
        @Override
        public void onItemBind(ItemBinding itemBinding, int position, T item) {
            setItemView (itemBinding,position,item);
        }
    };

    /**事件监听*/
    public interface OnEvnetListener<T>{
        void onClickListener(T item);
    }
    public OnEvnetListener onEventListener;
    public OnEvnetListener getOnEvnetListener() {
        return onEventListener;
    }

    public void setOnEvnetListener(OnEvnetListener onEventListener) {
        this.onEventListener = onEventListener;
    }
}

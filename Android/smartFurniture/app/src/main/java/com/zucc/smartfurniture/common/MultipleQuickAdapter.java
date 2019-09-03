package com.zucc.smartfurniture.common;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/11/30$ 10:05$
 * <p/>
 */
public abstract class MultipleQuickAdapter<T extends MultiItemEntity,K extends BaseViewHolder> extends BaseMultiItemQuickAdapter<T,K>{
    public MultipleQuickAdapter(List<T> data) {
        super(data);
    }
    /**item点击事件*/
     public interface onItemClickListener{
         void onItemClick(int position);
     }
    public  onItemClickListener onItemClickListener;

    public MultipleQuickAdapter.onItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(MultipleQuickAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}

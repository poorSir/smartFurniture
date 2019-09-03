package com.zucc.smartfurniture.module.mine.model;

import android.databinding.ObservableField;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.zucc.smartfurniture.common.Constant;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/11/16$ 15:48$
 * Description:所有设备model
 * <p/>
 */
public class AllDeviceVM extends AbstractExpandableItem<AllDeviceOperatorVM> implements MultiItemEntity {
    /** 设备id */
    private ObservableField<String>  id       = new ObservableField<>();
    /** 分类 */
    private ObservableField<String>  classify = new ObservableField<>();
    /** 描述 */
    private ObservableField<String>  detail   = new ObservableField<>();
    /** 是否展开 */
    private ObservableField<Boolean> isOpen   = new ObservableField<>();

    public ObservableField<String> getId() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);

    }

    public ObservableField<String> getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify.set(classify);
    }

    public ObservableField<String> getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail.set(detail);
    }

    public ObservableField<Boolean> getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Boolean isOpen) {
        this.isOpen.set(isOpen);
    }

    @Override
    public int getItemType() {
        return Constant.Number_0;
    }

    /***
     * 从第几个item开始里边有嵌套,默认是0,从第一个开始
     * @return
     */
    @Override
    public int getLevel() {
        return Constant.Number_0;
    }
}
